package org.jahia.impersonaBP;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;

public class ImpersonaBPAction extends Action {

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ImpersonaBPAction.class);
	
	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext, Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {
		
		String bp = getParameter(parameters, "bp");
		
		req.getSession().setAttribute("BPUSESSION", bp);
		
		WSClient.getClient(req.getSession()).setBpUtenza(bp);
		if(getParameter(parameters, "ammCond").equals("false")){
			WSClient.getClient(req.getSession()).setBpCliente(bp);
			req.getSession().setAttribute("BPCSESSION", bp);
		}
		
		
		WSClient.getClient(req.getSession()).setLingua(getParameter(parameters, "lang"));
		logger.info("Lang: "+WSClient.getClient(req.getSession()).getLingua());

		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
	}

}
