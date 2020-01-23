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

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.alpenite.gui.components.SelectCap;



public class GetCap extends Action {

    
    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(GetCap.class);
    
   
   
    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
 
    	// variables
    	JSONObject json = new JSONObject();
    	JSONArray jarray = new JSONArray();
    	
    	// create the json object
    	if(parameters.get("comune") != null){
    		String comune = parameters.get("comune").get(0);
    		comune = comune.replace(' ', '_');
    		comune = comune.concat("-");
    		List<String> list = (new SelectCap()).getValori(comune);
    		for(String cap : list){
        		jarray.put(cap);
        	}
    		json.put("lista", jarray.toString());
    	}

    	ActionResult ar = new ActionResult(HttpServletResponse.SC_OK, null, json);
    	
    	return ar;
	}
		
	
	}
