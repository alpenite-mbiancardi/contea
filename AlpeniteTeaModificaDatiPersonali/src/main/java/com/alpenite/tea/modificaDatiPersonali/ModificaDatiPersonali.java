package com.alpenite.tea.modificaDatiPersonali;

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
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerRoutingService;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.sapMessages.dati.Message;


public class ModificaDatiPersonali extends Action {
	
	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ModificaDatiPersonali.class);

	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext, Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {
		
		String bp = req.getParameter("bp");
		String username = req.getParameter("username");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String azienda = req.getParameter("azienda");
		
		JahiaUserManagerService userManagerService = JahiaUserManagerRoutingService.getInstance();
		
		//this must be used if the user must be found by BP
		//Principal user = FindUserByBp(bp, userManagerService);

		JahiaUser user =  userManagerService.lookupUser(username);
		
		if (user != null) {
			if (nome != null && nome != "") user.setProperty("j:firstName", nome);
			if (cognome != null && cognome != "") user.setProperty("j:lastName", cognome);
			if (azienda != null && azienda != "") user.setProperty("j:organization", azienda);
		} 
		else
		{
			logger.info("no user found with name :" + username);
			return new ActionResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
			
		return new ActionResult(HttpServletResponse.SC_ACCEPTED);
		
	}


}
