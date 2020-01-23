package com.alpenite.tea.documentale.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.velocity.tools.generic.DateTool;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Render;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUserManagerService;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.documentale.utils.DocumentUtils;

public class InvioEmail extends Action{
	private final transient static Logger	LOG					= Logger.getLogger(InvioEmail.class);
	private final static String				WRAPPER_IDS			= "id";
	private boolean							messageSent			= false;
	public static final String	MULTI_VALUE_SEPARATOR	= ",";
	/** Nome della proprieta' dov'e' salvato il comune associato a un documento */
	private final static String				COMUNE_DOCUMENTO	= "comune";
	/** Nome della proprieta' dov'e' salvato il comune associato a un utente */
	private final static String				COMUNE_UTENTE		= "j:comuniDocumentale";
	/**
	 * Nome della proprieta' dov'e' salvato il tipo documento associato a un
	 * documento
	 */
	private final static String				TIPO_DOCUMENTO_D	= "tipoDocumento";
	/**
	 * Nome della proprieta' dov'e' salvato il tipo documento associato a un
	 * utente
	 */
	private final static String				TIPO_DOCUMENTO_U	= "j:listaTipiDocumentoDocumentale";
	private final static String				EMAIL				= "j:email";
	private final static String				FILE				= "file";
	private final static String				TIPO_DOCUMENTO_N	= "jnt:tipoDocumento";
	
	private MailService						mailService;
	private JahiaUserManagerService			userManagerService;
	private String							mailTemplatePath;
	
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	public void setUserManagerService(JahiaUserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	
	public void setMailTemplatePath(String mailTemplatePath) {
		this.mailTemplatePath = mailTemplatePath;
	}
	
	@Override
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
		String defaultLanguage = renderContext.getSite().getDefaultLanguage();
		LOG.debug("Entering action: \"invioEmail\"!");
		// Prendo la lista dei documentWrapper da gestire
		List<String> documentWrappersIds = parameters.get(WRAPPER_IDS);
		List<JCRNodeWrapper> documentWrappers = DocumentUtils.getDocumentWrappers(session, documentWrappersIds);
		
		String workspace = session.getWorkspace().getName();
		JCRSessionWrapper rootSession = DocumentUtils.initRootSession(workspace);
		for (JCRNodeWrapper documentWrapper : documentWrappers) {
			// List<String> emailsList = getUserEmails(session,documentWrapper);
			String toMail = null;
			String ccMail = null;
			String bccMail = null; // = getUsers(session,documentWrapper);
			List<JCRNodeWrapper> recipients = getUsers(rootSession, documentWrapper);
			LOG.debug("Found " + recipients.size() + " recipients.");
			if (recipients != null && !recipients.isEmpty()) {
				// Prendo la descrizione del tipo di documento da fornire
				// all'e-mail
				String documentTypeDescription = getDocumentTypeDescription(rootSession, session.getLocale(), defaultLanguage, documentWrapper);
				for (JCRNodeWrapper recipient : recipients) {
					toMail = recipient.getPropertyAsString("j:email");
					if (toMail != null && !toMail.isEmpty()) {
						/*
						 * Define objects to be binded with the script engine to
						 * evaluate the scripts
						 * Same bindings for body and subject
						 */
						Map<String, Object> bindings = new HashMap<String, Object>();
						Map<String, List<String>> formDatas = filterFormDatas(parameters);
						bindings.put("recipientWrapper", recipient);
						bindings.put("documentWrapper", documentWrapper);
						bindings.put("documentType", documentTypeDescription);
						// bindings.put("formDatas",formDatas);
						// bindings.put("formNode",node.getParent());
						// bindings.put("formFields",
						// FormFunctions.getFormFields(node.getParent()));
						// bindings.put("helper", new FormBuilderHelper());
						bindings.put("submitter", renderContext.getUser());
						bindings.put("date", new DateTool());
						bindings.put("submissionDate", Calendar.getInstance());
						bindings.put("locale", resource.getLocale());
						LOG.info("Form data is sent by e-mail to user: " + recipient.getName());
						mailService.sendMessageWithTemplate(mailTemplatePath, bindings, toMail, mailService.getSettings().getFrom(), ccMail, bccMail, resource.getLocale(), "AlpeniteTeaDocumentale");
					} else {
						LOG.info("User \"" + recipient.getName() + "\" has no \"j:email\" property!");
						// WSReturnManager.evaluate(new
						// WSReturn<String>(Constants.ERRORE_MAIL,
						// this.getClass().getName(), "return" , new
						// ArrayList<Object>() ), req.getSession());
						// messageSent=true;
					}
				}
			} else {
				LOG.info("No user found as a recipient for the document.");
				WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_LIST_FILE_NULL, this.getClass().getName(), "return", new ArrayList<Object>()), req.getSession());
				messageSent = true;
			}
		}
		LOG.debug("Exiting action: \"invioEmail\"!");
		rootSession.logout();
		if (!messageSent) {
			WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS, this.getClass().getName(), "return", new ArrayList<Object>()), req.getSession());
		}
		return new ActionResult(ActionResult.OK.getResultCode(), req.getHeader("referer"), true, null);
	}
	
	/**
	 * Recupera la descrizione del documentType
	 * 
	 * @param session
	 * @param locale
	 * @param defaultLanguage
	 * @param documentWrapper
	 * @return
	 */
	private String getDocumentTypeDescription(JCRSessionWrapper session, Locale locale, String defaultLanguage, JCRNodeWrapper documentWrapper) {
		String documentTypeId = documentWrapper.getPropertyAsString("tipoDocumento");
		String query = "SELECT * FROM [" + TIPO_DOCUMENTO_N + "] WHERE [jcr:uuid] = '" + documentTypeId + "'";
		JCRNodeWrapper documentType = DocumentUtils.getNodes(session, query).get(0);
		try {
			if (documentType.hasI18N(locale) && documentType.getI18N(locale).hasProperty("name")) {
				return documentType.getI18N(locale).getProperty("name").getValue().getString();
			} else {
				return documentType.getI18N(new Locale(defaultLanguage)).getProperty("name").getValue().getString();
			}
		} catch (Exception ex) {
			LOG.error("Error loading the documentType name", ex);
			return "documentType";
		}
	}
	
	/**
	 * Toglie dai parametri in "parameters" i parametri riservati al rendering
	 * 
	 * @param parameters
	 * @return
	 */
	private Map<String, List<String>> filterFormDatas(Map<String, List<String>> parameters) {
		Set<String> reservedParameters = Render.getReservedParameters();
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		new HashMap<String, List<String>>();
		Set<Map.Entry<String, List<String>>> set = parameters.entrySet();
		for (Map.Entry<String, List<String>> entry : set) {
			String key = entry.getKey();
			if (!reservedParameters.contains(key)) {
				List<String> values = entry.getValue();
				result.put(key, values);
			}
		}
		return result;
	}
	
	/**
	 * Torna la lista dei nodi degli utenti a cui va comunicato l'upload
	 * 
	 * @param session sessione JCR
	 * @param documentWrapper documentWrapper
	 * @return
	 */
	private List<JCRNodeWrapper> getUsers(JCRSessionWrapper session, JCRNodeWrapper documentWrapper) {
		List<JCRNodeWrapper> result = new ArrayList<JCRNodeWrapper>();
		String listaComuniString = documentWrapper.getPropertyAsString(COMUNE_DOCUMENTO);
		String documenTypeId = documentWrapper.getPropertyAsString(TIPO_DOCUMENTO_D);
		String documenType = null;
		try {
			documenType = session.getNodeByIdentifier(documenTypeId).getPropertyAsString("id");
		} catch (Exception ex) {
			LOG.error("Error loading the documentTypeId", ex);
		}
		if(documenType==null){
			return result;
		}
		String comune = constructQueryFieldComuni(listaComuniString);
		
		String query = "SELECT * FROM [jnt:user] WHERE " +
				 comune + " AND " +
				"[" + TIPO_DOCUMENTO_U + "] LIKE '%" + documenType + "%'";
		LOG.debug("Query: " + query);
		//StringBuilder sb = new StringBuilder();
		List<JCRNodeWrapper> users = DocumentUtils.getNodes(session, query);
		// for(JCRNodeWrapper user:users){
		// String eMail = user.getPropertyAsString(EMAIL);
		// if(eMail!=null){
		// sb.append(eMail+";");
		// //resultList.add(eMail);
		// }
		// }
		// if(sb.length()>0){
		// result = sb.substring(0, sb.length()-1);
		// }
		result = users;
		return result;
	}
	
	private String constructQueryFieldComuni(String listaComuniString) {
		String result="";
		String[] comuni = org.apache.commons.lang.StringUtils.split(listaComuniString,MULTI_VALUE_SEPARATOR);
		if(comuni!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (int i=0;i<comuni.length;i++){
				String comune=comuni[i];
				sb.append("["+COMUNE_UTENTE+"] LIKE '%" + comune + "%'");
				if(i<comuni.length-1){
					sb.append(" OR ");
				}
			}
			sb.append(")");
			result = sb.toString();
		}
		return result;
	}

	/**
	 * @return the wrapperIds
	 */
	protected static String getWrapperIds() {
		return WRAPPER_IDS;
	}
	
}
