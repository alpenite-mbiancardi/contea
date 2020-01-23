package org.jahia;

import com.alpenite.tea.communicationLayer.*;

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

public class SbloccaAction extends Action {
	
	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(SbloccaAction.class);
	
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
		
		
		String getUser = getParameter(parameters, "getUser");
		String action = getParameter(parameters,"action");
		
		JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
        JahiaUser trovatoUser = usrManager.lookupUser(getUser);
        
        if(trovatoUser!=null && action.equals("unlock"))
        {    
        	logger.info("[UNLOCK] Username: "+getUser+" - bp: "+trovatoUser.getProperty("j:bp"));
        	logger.info("[UNLOCK] Opzione disabilitata");
        	//WSReturnManager.evaluate(
            //       WSClient.getClient(req.getSession()).creaUsername(trovatoUser.getProperty("j:bp"), getUser, trovatoUser.getProperty("j:email")),
            //        req.getSession());
        	
        	//trovatoUser.setProperty("j:accountLocked", "false");        	        	
        }
        
        if(trovatoUser!=null && action.equals("lock"))
        {    	
        	logger.info("[LOCK] Username: "+getUser+" - bp: "+trovatoUser.getProperty("j:bp"));
        	WSReturnManager.evaluate(
                    WSClient.getClient(req.getSession()).cancellaUsername(trovatoUser.getProperty("j:bp")),
                    req.getSession());
        	
        	trovatoUser.setProperty("j:accountLocked", "true");
        }
         
        return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
               
	}
}