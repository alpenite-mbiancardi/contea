package org.jahia.userregistration;

import java.util.List;
import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.bin.Render;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUserManagerService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.data.Comune;

public class CheckUsername extends Action {

    
    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(CheckUsername.class);
    private JahiaUserManagerService userManagerService;
   
   
    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
    	
    	JSONObject json = new JSONObject();
    	if(userManagerService.userExists(parameters.get("username").get(0))){
    		json.put("data", "false");
    	}else{
    		json.put("data", "true");
    	}
    	return new ActionResult(HttpServletResponse.SC_OK, null, json);
	}


	public JahiaUserManagerService getUserManagerService() {
		return userManagerService;
	}


	public void setUserManagerService(JahiaUserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
		
	
}


