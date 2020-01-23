package com.alpenite.tea.welcomeModule;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;

public class ChangeLang extends Action{

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ChangeLang.class);
	
	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext, Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {
		
		String lang = getParameter(parameters, "lang");
		
		try{
		WSClient.getClient(req.getSession()).setLingua(lang);
		}catch(NullPointerException e){
			logger.info("Sessione non inizializzata correttamente");
		}
		
		return ActionResult.OK;
	}
	
}
