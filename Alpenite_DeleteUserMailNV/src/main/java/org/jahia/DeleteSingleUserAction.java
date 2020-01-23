package org.jahia;

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
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.json.JSONObject;

public class DeleteSingleUserAction extends Action 
{	
	
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception 
            {
				String username = getParameter(parameters, "username");
				JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
				usrManager.deleteUser(usrManager.lookupUser(username));
		        
		        //ab - redirect        
		        return new ActionResult(HttpServletResponse.SC_ACCEPTED,parameters.get("userredirectpage").get(0), new JSONObject());  
            }
}