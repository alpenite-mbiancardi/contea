package org.jahia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.params.ProcessingContext;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.sites.JahiaSite;
import org.jahia.services.sites.JahiaSitesService;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.settings.SettingsBean;
import org.jahia.userregistration.utils.UserJahiaDB;
import org.json.JSONObject;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import org.slf4j.Logger;


public class RecuperoPasswordAction extends Action
{
	private MailService mailService;

	public void setMailService(MailService mailService)
	{
		this.mailService = mailService;
	}

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(RecuperoPasswordAction.class);

	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception
            {

				// parametri mail
				String from = parameters.get("from")==null?SettingsBean.getInstance().getMail_from():getParameter(parameters, "from");
				String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
				String group = parameters.get("group").get(0);

				if(parameters.get("email")==null || parameters.get("username") == null){
					WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}

				String mail = getParameter(parameters, "email");
				String username = getParameter(parameters, "username");

				String codice = UUID.randomUUID().toString();

				logger.info("ALPENITE RecuperoPasswordAction - Recupero Password, Mail: "+mail+" Username: "+username);

				//search username in db
				UserJahiaDB userJahiaDb = new UserJahiaDB();
				Connection connection = userJahiaDb.connectedDB();
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("" +
						"select user_registration.username " +
						"from user_registration left join user_group ug on user_registration.username = ug.username " +
						"where user_registration.username ='" + username + "' " +
						"and ug.group_name ='" + group + "' " +
						"and user_registration.email='" + mail + "' " +
						"and user_registration.j_validato='true'"
				);
				if (rs.isBeforeFirst()) {
					logger.info("ALPENITE RecuperoPasswordAction - user find in db");
					if (rs.next()) {
						logger.info("ALPENITE RecuperoPasswordAction - user '" + rs.getString("username") + "' in db");
					}
					//update property in db
					userJahiaDb.updateDBProperty(connection, "j_codiceVerifica", codice, username);
				}else {
					//search username in jahiauser
					logger.info("ALPENITE RecuperoPasswordAction - user NOT find in db, search in jahia user");
					// check user
					JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
					JahiaUser user = usrManager.lookupUser(username);

					logger.info("ALPENITE RecuperoPasswordAction - user == null: " + (user == null));
					if(user == null){
						WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
					}

					logger.info("ALPENITE RecuperoPasswordAction - !user.isMemberOfGroup(renderContext.getSite().getID(), group): " + (!user.isMemberOfGroup(renderContext.getSite().getID(), group)));
					if(!user.isMemberOfGroup(renderContext.getSite().getID(), group)){
						WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
					}
					logger.info("ALPENITE RecuperoPasswordAction - !user.getProperty(\"j:email\").equals(mail): " + (!user.getProperty("j:email").equals(mail)));
					if(!user.getProperty("j:email").equals(mail)){

						WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
					}
					logger.info("ALPENITE RecuperoPasswordAction - user == null: " + (user.getProperty("j:validato").equals("false")));
					if(user.getProperty("j:validato").equals("false")){
						WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
					}

					//get code
					user.setProperty("j:codiceVerifica", codice);
				}

				stm.close();
				userJahiaDb.closeConnection(connection);

		    	// create the mail
		    	// send the mail
				// build the link
				JCRNodeWrapper page = session.getNodeByIdentifier(parameters.get("verificationPage").get(0));
				String link = req.getScheme()+ "://" + req.getServerName() +":"+req.getServerPort() + page.getUrl() + "?username="+username+"&codice="+codice;
				//String contenutoMail = req.getParameter("contenuto_mail");
				String contenutoMail = "";
				try{
					contenutoMail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_"+parameters.get("lang").get(0)).getPropertyAsString("mail");
				}catch(PathNotFoundException e){
					contenutoMail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_en").getPropertyAsString("mail");
				}
				String mail_tea = contenutoMail.replaceAll("LINK_VALIDAZIONE", link);
				mailService.sendHtmlMessage(from, mail, null, null,subject, mail_tea);

				// message preparation
		 		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_REGISTRATION, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		 		// return the page
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());


            }
}
