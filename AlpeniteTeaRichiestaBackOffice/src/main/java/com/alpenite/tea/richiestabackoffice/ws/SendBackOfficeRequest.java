package com.alpenite.tea.richiestabackoffice.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.sapMessages.dati.Message;


public class SendBackOfficeRequest extends Action {

	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext, Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {

		WSReturn<Message> message = WSClient.getClient(req.getSession()).effetuaRichiestaBackoffice((Boolean.valueOf(parameters.get("isClient").get(0))).booleanValue(), parameters.get("richestaBackofficeNote").get(0), parameters.get("level1").get(0), parameters.get("level2").get(0));
		Message result = message.getRitorno();
		if(result.geteReturn().equals(com.alpenite.tea.communicationLayer.data.Constants.SAP_SUCCESS)){
			WSReturnManager.evaluate(new WSReturn<String>(Constants.SAP_SUCCESS, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
			return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		}else{
			if(result.geteReturn().equals(Constants.SAP_ERR_02)){
				WSReturnManager.evaluate(new WSReturn<String>(Constants.SAP_ERR_02, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
		}
		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
	}

}
