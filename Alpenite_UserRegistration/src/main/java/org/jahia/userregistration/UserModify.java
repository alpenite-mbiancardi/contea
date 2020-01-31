package org.jahia.userregistration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.jcr.Credentials;
import javax.jcr.ItemNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jackrabbit.core.security.JahiaLoginModule;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.userregistration.utils.Constants;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.WSReturn;

//import com.alpenite.tea.communicationLayer.data.Constants;

public class UserModify extends Action {
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserModify.class);
	private JahiaUserManagerService userManagerService;
	private MailService mailService;


	@Override
	public ActionResult doExecute(HttpServletRequest req,RenderContext renderContext, Resource resource,JCRSessionWrapper session, Map<String, List<String>> parameters,URLResolver urlResolver) throws Exception {
		LOG.debug("ALPENITE UserModify - Entering action: \""+this.getName()+"\"");
		boolean changed = false;
		HttpSession httpSession              = req.getSession();
		String      impersonaUtenteMessaggio = "";
		String      username                 = (String) httpSession.getAttribute(Constants.USER_IMPERSONATED);

		//Get Parameters
		if(username==null||username.length()==0){
			impersonaUtenteMessaggio = com.alpenite.tea.communicationLayer.data.Constants.ERRORE_GENERICO; //no username impersonated
			LOG.error("ALPENITE UserModify - No username impersonated");
			return new ActionResult(ActionResult.OK.getResultCode(),req.getHeader("referer"));
		}

		JahiaUser userImpersonated = ServicesRegistry.getInstance().getJahiaUserManagerService().lookupUser(username);
		if(userImpersonated==null){
			impersonaUtenteMessaggio = com.alpenite.tea.communicationLayer.data.Constants.ERRORE_GENERICO; //no username impersonated
			LOG.error("ALPENITE UserModify - No username impersonated");
			return new ActionResult(ActionResult.OK.getResultCode(),req.getHeader("referer"));
		}
		String workspace = session.getWorkspace().getName();
		JCRSessionWrapper rootSession = initRootSession(workspace);
		changed|=modificaEmail(userImpersonated, req, renderContext, resource, rootSession, parameters, urlResolver);
		boolean mailChanged = changed;
		changed|=modificaDatiDocumentale(userImpersonated, parameters);

		if(changed){
			LOG.error("ALPENITE UserModify - Changed true");
			rootSession.getNode(userImpersonated.getLocalPath()).saveSession();
			rootSession.save();
			//session.refresh(false);
			if(mailChanged){
				ConfirmRegistration aR = new ConfirmRegistration();
				aR.setMailService(mailService);
				aR.setRequiredPermission(getRequiredPermission());
				aR.setUserManagerService(userManagerService);
				try{
				aR.doExecute(req, renderContext, resource, session, parameters, urlResolver);
				}catch(ItemNotFoundException ex){
					LOG.info("ConfirmRegistration did not find the correct result node");
					LOG.debug("ExceptionOutput:",ex);
				}catch (Exception ex) {
					WSReturnManager.evaluate(new WSReturn<String>(com.alpenite.tea.communicationLayer.data.Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
					return new ActionResult(ActionResult.OK.getResultCode(),req.getHeader("referer"),true,null);
				}
			}
		}
		LOG.debug("Exiting action: \""+this.getName()+"\"");
		rootSession.logout();
        WSReturnManager.evaluate(new WSReturn<String>(com.alpenite.tea.communicationLayer.data.Constants.SUCCESS, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
		return new ActionResult(ActionResult.OK.getResultCode(),req.getHeader("referer"),true,null);
	}

	private boolean modificaEmail(JahiaUser user, HttpServletRequest req,RenderContext renderContext, Resource resource,JCRSessionWrapper session, Map<String, List<String>> parameters,URLResolver urlResolver) throws Exception{
		LOG.info("ALPENITE UserModify - modifica Email");
		boolean result = false;
		String newEmail = getParameter(parameters, Constants.MAIL, "");

		if(!newEmail.equals("")&&(user.getProperty("j:email")==null||!user.getProperty("j:email").equals(newEmail))){
			modifyActionPreparation(newEmail,user,parameters,req.getHeader("referer"));
			result=true;
			//userImpersonated.setProperty("j:email", newEmail);
		}
		return result;
	}

	private void modifyActionPreparation(String newEmail,JahiaUser user,Map<String, List<String>> parameters,String referer){
		List<String> listaParametri = new ArrayList<String>();
		//Adattamento parameters
		listaParametri.add(user.getUsername());
		parameters.put(Constants.USERNAME, listaParametri);
		listaParametri = new ArrayList<String>();
		String codiceDiVerifica="internalModificationByUserModify";
		listaParametri.add(codiceDiVerifica);
		parameters.put(Constants.CODE, listaParametri);
		listaParametri = new ArrayList<String>();
		listaParametri.add(newEmail);
		parameters.put(Constants.MAIL, listaParametri);
		listaParametri = new ArrayList<String>();
		listaParametri.add(referer);
		parameters.put("redirectPage", listaParametri);
		listaParametri = new ArrayList<String>();
		//Adattamento user
		user.setProperty(Constants.CODICEVERIFICA, codiceDiVerifica);
		user.setProperty(Constants.NUOVAMAIL, newEmail);
	}

	private boolean modificaDatiDocumentale(JahiaUser user,Map<String, List<String>> parameters){
		LOG.info("ALPENITE UserModify - modificaDatiDocumentale");
		boolean result = false;
		List<String> comuni        = new ArrayList<String>();
		List<String> documentTypes = new ArrayList<String>();

		comuni=parameters.get("comuni");
		documentTypes=parameters.get("tipiDocumento");

		if(comuni!=null&&documentTypes!=null){
			Collections.sort(comuni);
			Collections.sort(documentTypes);
			String listaComuni        = org.apache.commons.lang.StringUtils.join(comuni,Constants.MULTI_VALUE_SEPARATOR);
			String listaDocumentTypes = org.apache.commons.lang.StringUtils.join(documentTypes,Constants.MULTI_VALUE_SEPARATOR);
			String oldListaComuni     = user.getProperty("j:comuniDocumentale");
			String oldListaDocumentTypes = user.getProperty("j:listaTipiDocumentoDocumentale");
			if(!listaComuni.equals(oldListaComuni)){
				result = true;
				user.setProperty("j:comuniDocumentale", listaComuni);
			}
			if(!listaDocumentTypes.equals(oldListaDocumentTypes)){
				result=true;
				user.setProperty("j:listaTipiDocumentoDocumentale", listaDocumentTypes);
			}
		}

		return result;
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
	public void setUserManagerService(JahiaUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
    public void setMailService(MailService mailService){
		this.mailService = mailService;
	}
}
