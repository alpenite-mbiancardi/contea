package org.jahia.userregistration;

import java.sql.Connection;
import java.util.List;
import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.bin.Render;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUserManagerService;

import org.jahia.userregistration.utils.UserJahiaDB;
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

		UserJahiaDB userJahiaDB = new UserJahiaDB();
		Connection connection = userJahiaDB.connectedDB();

    	JSONObject json = new JSONObject();
    	String username = StringUtils.isNotEmpty(parameters.get("username").get(0)) ? parameters.get("username").get(0) : "";

    	if(userManagerService.userExists(username) || userJahiaDB.checkUsername(connection, username)){
    		json.put("data", "false");
    	}else{
    		json.put("data", "true");
    	}

    	userJahiaDB.closeConnection(connection);
    	return new ActionResult(HttpServletResponse.SC_OK, null, json);
	}


	public JahiaUserManagerService getUserManagerService() {
		return userManagerService;
	}


	public void setUserManagerService(JahiaUserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}


}
