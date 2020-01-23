package org.jahia;

import java.util.Iterator;
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
import org.json.JSONObject;

public class DeleteUserMNVAction extends Action 
{	
	
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception 
            {
		
				JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
				//ab - recupero la lista finale degli utenti				
				String group = parameters.get("group").get(0);
				int siteID = renderContext.getSite().getID();
				List<String> userNameListToDelete = DeleteUserMNV.getUsernameList(group, siteID);				      
		        Iterator<String> iterUserList = userNameListToDelete.iterator(); 
		        
		        while(iterUserList.hasNext())
		        {
			        String currentUser = iterUserList.next();     
				    JahiaUser trovatoUser = usrManager.lookupUser(currentUser);
				    usrManager.deleteUser(trovatoUser);	    
		        }
		        
		        //ab - redirect        
		        return new ActionResult(HttpServletResponse.SC_ACCEPTED,parameters.get("userredirectpage").get(0), new JSONObject());  
            }
}