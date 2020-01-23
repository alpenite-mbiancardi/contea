package com.alpenite.tea.documentale.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.documentale.utils.DocumentUtils;

public class EliminaDocumento extends Action{
	
	private final transient static Logger	LOG					= Logger.getLogger(EliminaDocumento.class);
	private final static String				WRAPPER_IDS			= "id";
	
	@Override
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
		LOG.info("Entering action: \"EliminaDocumento\"!");
		// Prendo la lista dei documentWrapper da gestire
		List<String> documentWrappersIds = parameters.get(WRAPPER_IDS);
		JCRSessionWrapper rootSession = SalvataggioDocumento.initRootSession("default");
		List<JCRNodeWrapper> documentWrappers = DocumentUtils.getDocumentWrappers(rootSession, documentWrappersIds);
		ArrayList<String> uuids = new ArrayList<String>();
		for(JCRNodeWrapper item:documentWrappers){
			item.markForDeletion("");//remove();//markForDeletion("Automated deletion from frontend");
			uuids.add(item.getIdentifier());
			LOG.debug("item remove");
		}
		rootSession.save();
		for(String uuid:uuids){
			ServicesRegistry.getInstance().getJCRPublicationService().publishByMainId(uuid);
			
		}
		rootSession.save();
		rootSession.logout();
		LOG.info("Exiting action EliminaDocumento");
		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
		
		return new ActionResult(ActionResult.OK.getResultCode(), req.getHeader("referer"), true, null);
	}
	
	
}
