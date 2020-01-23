package org.jahia;

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
import org.json.JSONObject;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;


public class RecuperoPasswordAction extends Action 
{	
	private MailService mailService;
	
	public void setMailService(MailService mailService) 
	{        
		this.mailService = mailService;    
	}
	
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
				System.out.println("Recupero Password Mail: "+mail+" Username: "+username);
			    // check user
				JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();		
				JahiaUser user = usrManager.lookupUser(username);
				
				if(user == null){
					WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				
				if(!user.isMemberOfGroup(renderContext.getSite().getID(), group)){
					WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				if(!user.getProperty("j:email").equals(mail)){
					
					WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				if(user.getProperty("j:validato").equals("false")){
					WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				
				//get code
		    	String codice = UUID.randomUUID().toString();
		    	user.setProperty("j:codiceVerifica", codice);
		    	
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