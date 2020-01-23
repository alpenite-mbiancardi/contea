package com.alpenite.tea.documentale.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.jackrabbit.core.security.JahiaLoginModule;
import org.apache.log4j.Logger;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.tools.files.FileUpload;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.documentale.utils.DocumentUtils;

public class SalvataggioDocumento extends Action{
	private final transient static Logger	LOG							= Logger.getLogger(SalvataggioDocumento.class);
	private static final String				DOCUMENT_WRAPPER_TYPE		= "jnt:contenitoreDocumento";
	private static final String				DOCUMENT_WRAPPER_PREFIX		= "document";
	private static final String				DOCUMENT_WRAPPERS_FOLDER	= "documentale";
	private static final String				ID							= "id";
	// file
	private static final String				COMUNE						= "comune";
	private static final String				TIPO_DOCUMENTO				= "tipoDocumento";
	private static final String				SOCIETA						= "societa";
	/* Id del nodo che viene creato, da fornire all'action di invio e-mail */
	private String							id							= "";
	
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
		LOG.debug("Entering action: SalvataggioDocumento");
		
		// Lettura dati del form
		// - file (weakreference, picker[type='file']) i18n
		FileUpload fu = (FileUpload) req.getAttribute(FileUpload.FILEUPLOAD_ATTRIBUTE);
		DiskFileItem file = fu.getFileItems().get("documentoDaSalvare");
		boolean inviareEmail=Boolean.parseBoolean(getParameter(parameters, "sendEmail", "false"));
		// - id (string)
		// int id = getDocumentWrapperId(session);
		// - comune (string)
		List<String>  listaComune = parameters.get("comuni");
		String comune = org.apache.commons.lang.StringUtils.join(listaComune, InvioEmail.MULTI_VALUE_SEPARATOR);
		// - tipoDocumento (string)
		String documentType = getParameter(parameters, "tipoDocumento");
		// - societa (string)
		String societa = getParameter(parameters, "societa");
		
		//Recupero informazioni sessione di root
		String workspace = session.getWorkspace().getName();
		//I nodi vanno creati nel workspace di default per far funzionare i check standard jahia
		workspace="default";
		JCRSessionWrapper rootSession = initRootSession(workspace);
		// Salvataggio nel sistema
		JCRNodeWrapper contents = renderContext.getSite().getNode("contents");
		JCRNodeWrapper documentWrapperNode = prepareNodeWrapper(comune, documentType, societa, file, contents, rootSession);
		
		if (documentWrapperNode != null) {
			
			rootSession.save();
			
			// Invio e-mail
			if(inviareEmail){
				List<String> newParameters = new ArrayList<String>();
				newParameters.add(id);
				parameters.put(InvioEmail.getWrapperIds(), newParameters);
				InvioEmail invioEmail = new InvioEmail();
				invioEmail.setMailService(mailService);
				invioEmail.setMailTemplatePath(mailTemplatePath);
				invioEmail.setUserManagerService(userManagerService);
				ActionResult result = invioEmail.doExecute(req, renderContext, resource, session, parameters, urlResolver);
			}
		} else {
			WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
		}
		LOG.debug("Exiting action: SalvataggioDocumento");
		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
		rootSession.logout();
		return new ActionResult(ActionResult.OK.getResultCode(), req.getHeader("referer"), true, null);
	}
	
	private JCRNodeWrapper prepareNodeWrapper(String comune, String documentType, String societa, DiskFileItem file, JCRNodeWrapper contents, JCRSessionWrapper session) {
		JCRNodeWrapper folder = null;
		try {
			LOG.debug("Before loading documents contentFolder");
			// Se non c'e' il nodo contenitore, lo creo
			if (!contents.hasNode(DOCUMENT_WRAPPERS_FOLDER)) {
				session.getNode(contents.getPath()).addNode(DOCUMENT_WRAPPERS_FOLDER, "jnt:contentFolder");
				//contents.addNode(DOCUMENT_WRAPPERS_FOLDER, "jnt:contentFolder");
			}
			folder = contents.getNode(DOCUMENT_WRAPPERS_FOLDER);
			LOG.debug("After loading documents contentFolder");
			// Trovo il primo nome libero
			int id = 1;
			while (folder.hasNode(DOCUMENT_WRAPPER_PREFIX + "-" + id)) {
				id += 1;
			}
			LOG.debug("creating a document wrapper with id "+id);
			// Creo il nodo
			JCRNodeWrapper result = session.getNode(folder.getPath()).addNode(DOCUMENT_WRAPPER_PREFIX + "-" + id, DOCUMENT_WRAPPER_TYPE);
			//JCRNodeWrapper result = folder.addNode(DOCUMENT_WRAPPER_PREFIX + "-" + id, DOCUMENT_WRAPPER_TYPE);
			// file.
			// Inserisco le proprieta' del nodo
			result.setProperty(ID, Integer.toString(id));
			result.setProperty(COMUNE, comune);
			result.setProperty(TIPO_DOCUMENTO, getDocumentTypeId(session,documentType));
			result.setProperty(SOCIETA, societa);
			
			// Creo il nodo del documento
			result.uploadFile(file.getName(), file.getInputStream(), "application/octet-stream");
			/* Imposto l'id del nodo nella variabile di classe id */
			this.id = Integer.toString(id);
			session.save();
			ServicesRegistry.getInstance().getJCRPublicationService().publishByMainId(result.getIdentifier());
			return result;
		} catch (Exception ex) {
			LOG.error("Error while accessing the node: " + DOCUMENT_WRAPPERS_FOLDER, ex);
		}
		return null;
	}
	
	private String getDocumentTypeId(JCRSessionWrapper session, String documentType) {
		String query = "SELECT * FROM [jnt:tipoDocumento] WHERE [id] = '"+documentType+"'";
		List<JCRNodeWrapper> documentTypes = DocumentUtils.getNodes(session, query);
		if(documentTypes!=null){
			JCRNodeWrapper dtn=documentTypes.get(0);
			try {
				return dtn.getIdentifier();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Prende la sessione di root per l'utente corrente nel workspace
	 * @param workspace
	 * @return
	 */
	public static JCRSessionWrapper initRootSession(String workspace) {
		JCRSessionWrapper rootSession = null;
        if (rootSession == null) {
            Credentials c = JahiaLoginModule.getSystemCredentials();
            try {
                return rootSession = ServicesRegistry.getInstance().getJCRStoreService().getSessionFactory().login(c, workspace);
            } catch (Exception e) {
                LOG.warn("Impossibile inizializzare la sessione di root", e);
            }
        }
        return null;
    }

}
