package org.jahia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;


public class ConfermaRecuperoPasswordAction extends Action 
{		
	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfermaRecuperoPasswordAction.class);
	
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception 
            {
		
				String username = parameters.get("username")==null?"":parameters.get("username").get(0);
				String password = parameters.get("new_password")==null?"":parameters.get("new_password").get(0);
				String password2 = parameters.get("confirm_password")==null?"":parameters.get("confirm_password").get(0);
				String codice = parameters.get("codice")==null?"":parameters.get("codice").get(0);
				String group = parameters.get("group")==null?"users":parameters.get("group").get(0);
				System.out.println("Recupero Password User: "+username+" codice: "+codice+" group: "+group);
				if(username.equals("") || password.equals("")||!password2.equals(password)|| codice.equals("")){
					req.getSession().setAttribute("messaggio", com.alpenite.tea.communicationLayer.data.Constants.ERRORE_GENERICO);
			    	logger.info(username+ " - "+password+" - "+password2+" - "+ codice);
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				
				// get user
				JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();		
				JahiaUser user = usrManager.lookupUser(username);
				System.out.println("Confermato user:"+user.getName());
				if(user.getProperty("j:validato").equals("false") || !user.getProperty("j:codiceVerifica").equals(codice) || !user.isMemberOfGroup(renderContext.getSite().getID(), group)){
					req.getSession().setAttribute("messaggio", com.alpenite.tea.communicationLayer.data.Constants.ERRORE_GENERICO);
			    	logger.info(user.getProperty("j:validato")+" - "+user.getProperty("j:codiceVerifica")+" - "+codice+" - "+user.isMemberOfGroup(renderContext.getSite().getID(), group));
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				
				if(!user.setPassword(password)){
					req.getSession().setAttribute("messaggio", com.alpenite.tea.communicationLayer.data.Constants.ERRORE_GENERICO);
					logger.info("setPwd");
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
				}
				
				user.setProperty("j:codiceVerifica", "");
				
				// message preparation
		 		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_REGISTRATION, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		 		// return the page
			    return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
            }
}