package com.alpenite.tea.richiestabackoffice.ws;


import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;

import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;

import org.json.JSONObject;
import org.slf4j.Logger;

import com.alpenite.gui.components.SelectSecondLevelReq;


public class SecondDropDown extends Action {

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(SecondDropDown.class);

	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext, Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {

		//JCRNodeWrapper node = session.getNodeByUUID(resource.getNode().getIdentifier());
		JSONObject json = new JSONObject();
		
		
		
		
		logger.info("Properties file: AlpeniteTeaRichiestaBackOffice_"+resource.getLocale()+".properties");
		// leggo dal file le etichette
        InputStream is = getClass().getClassLoader().getResourceAsStream("AlpeniteTeaRichiestaBackOffice_"+resource.getLocale()+".properties");
        if(is==null){
        	is = getClass().getClassLoader().getResourceAsStream("AlpeniteTeaRichiestaBackOffice.properties");
        	logger.info("Lang not found -> AlpeniteTeaRichiestaBackOffice.properties");
        }
        if(is == null)
        	logger.info("ERRORE");
        Properties secondLevelReq = new Properties();
        secondLevelReq.load(is);

		if (parameters.get("level1") != null) {
			SelectSecondLevelReq sslr = new SelectSecondLevelReq();
			List<String> secondLevelMenu = sslr.getValues();
			if(secondLevelMenu.size() != 0){
				for(String key : secondLevelMenu){
					if(key.startsWith(parameters.get("level1").get(0)+"_")){
						json.put(key, secondLevelReq.getProperty("richiestaBackOffice."+key));
					}
				}
			}
			
			/*Hashtable<String, String> secondLevel = DropDownDatas.secondLevel.get(parameters.get("level1").get(0));
			if (secondLevel != null) {
				for (String key : secondLevel.keySet()) {
					json.put(key, HtmlUtils.htmlEscape(secondLevel.get(key)));
				}
			}*/
			
			//DEBUG::
			//json.put("k_level", parameters.get("level1").get(0));
		}

		ActionResult ar = new ActionResult(HttpServletResponse.SC_OK, null,
				json);
		// ar.setJson(json);

		return ar;
	}

}
