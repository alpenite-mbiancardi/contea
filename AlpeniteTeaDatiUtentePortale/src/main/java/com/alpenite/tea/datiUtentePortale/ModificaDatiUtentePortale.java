package com.alpenite.tea.datiUtentePortale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.settings.SettingsBean;
import org.jahia.userregistration.utils.MD5Util;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;

public class ModificaDatiUtentePortale extends Action {

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ModificaDatiUtentePortale.class);
	final static String SUBSCRIBE_TAG = "LINK_VALIDAZIONE";
	private MailService mailService;
	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext, Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {

		if(parameters.get("username")==null || parameters.get("oldMail") == null || parameters.get("mailVerificationPage") == null){
			logger.error("ALPENITE ModificaDatiUtentePortale - Username parameter is null");
			// return error
			return ActionResult.BAD_REQUEST;
		}

		logger.info("ALPENITE ModificaDatiUtentePortale - ALPENITE PROVA CONNESSIONE DB");

		org.jahia.userregistration.utils.UserJahiaDB userJahiaDb = new org.jahia.userregistration.utils.UserJahiaDB();

		Connection connection = userJahiaDb.connectedDB();

		logger.info("ALPENITE ModificaDatiUtentePortale - check if username exist in db");
		boolean resultQueryUser = userJahiaDb.checkUsername(connection, parameters.get("username").get(0));;

		if (!resultQueryUser){
			logger.info("ALPENITE ModificaDatiUtentePortale - Username is not present in db");
			//return ActionResult.BAD_REQUEST;
		}

		String mailVerificationPageUuid = parameters.get("mailVerificationPage").get(0);
		boolean passwordOk = true;
		boolean mailOk = true;
		JahiaUserManagerService userManagerService = ServicesRegistry.getInstance().getJahiaUserManagerService();
		JahiaUser user = userManagerService.lookupUser(parameters.get("username").get(0));

		// modify password
		if(parameters.get("password") != null && parameters.get("password1")!=null){
			String password = parameters.get("password").get(0);
			String password1 = parameters.get("password1").get(0);
			if(!password.equals("") && password.equals(password1)){
				if(!user.setPassword(password)){
					// if something went wrong
					passwordOk = false;
				}else {
					String passwordMd5 = MD5Util.getMd5(password);
					//update password in db
					userJahiaDb.updateDBProperty(connection, "password", passwordMd5, parameters.get("username").get(0));

					//update password2 in db
					userJahiaDb.updateDBProperty(connection, "password2", passwordMd5, parameters.get("username").get(0));

					logger.info("ALPENITE ModificaDatiUtentePortale - Password changed in db");
				}

				logger.info("ALPENITE ModificaDatiUtentePortale - Password changed");
			}
		}


		// modify mail
		if(parameters.get("email") != null){
			String mail = parameters.get("email").get(0);
			String oldMail = parameters.get("oldMail").get(0);
			if(!mail.equals(oldMail)){
				// mail is changed
				// get random code
				String codice = UUID.randomUUID().toString();
				// set user properties
				user.setProperty("j:nuovaMail", mail);
				user.setProperty("j:codiceVerifica", codice);

				//update nuovaMail in db
				userJahiaDb.updateDBProperty(connection, "j_nuovaMail", mail, parameters.get("username").get(0));

				//update codiceVerifica in db
				userJahiaDb.updateDBProperty(connection, "j_codiceVerifica", codice, parameters.get("username").get(0));

				// mail preparation
		    	String from = parameters.get("from")==null?SettingsBean.getInstance().getMail_from():getParameter(parameters, "from");
				String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
				String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
				String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
				JCRNodeWrapper page = session.getNodeByIdentifier(mailVerificationPageUuid);
				String link = req.getScheme()+ "://" + req.getServerName() +":"+req.getServerPort() + page.getUrl() + "?username="+parameters.get("username").get(0)+"&codice="+codice+"&mail="+mail;
				logger.info("Info mail: "+parameters.get("lang").get(0));
				String testo_mail = "";
				try{
					testo_mail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_"+parameters.get("lang").get(0)).getPropertyAsString("mail");
				}catch(PathNotFoundException e){
					testo_mail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_en").getPropertyAsString("mail");
				}
				String mail_tea = testo_mail.replaceAll(SUBSCRIBE_TAG, link);
				logger.info("Mail from: "+from+" to: "+mail+" cc: "+cc);
				if(!mailService.sendHtmlMessage(from, mail, cc, bcc, subject, mail_tea)){
					// error
					return ActionResult.INTERNAL_ERROR;
				}

				logger.info("mail sent");
			}else{
				mailOk=false;
			}
		}

		connection.close();
		logger.info("ALPENITE ModificaDatiUtentePortale - Chiudo connessione db");

		// message preparation
		if(mailOk && passwordOk){
			// everything goes well
			WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
			return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		}
		if(mailOk && !passwordOk){
			WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_MODIFICATION_MAIL, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
			return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		}
		if(!mailOk && passwordOk){
			WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_MODIFICATION_PASSWORD, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
			return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		}

		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
	}
	public MailService getMailService() {
		return mailService;
	}
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}


}
