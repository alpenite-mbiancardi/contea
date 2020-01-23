/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpenite.tea.recapito;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;

/**
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Recapito extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String cc = getParameter(parameters, "cc");
        com.alpenite.tea.recapito.dati.Recapito r = new com.alpenite.tea.recapito.dati.Recapito(
                getParameter(parameters, "paese"), 
                getParameter(parameters, "provincia"), 
                getParameter(parameters, "comune"), 
                getParameter(parameters, "presso"), 
                getParameter(parameters, "cap"), 
                getParameter(parameters, "via"), 
                getParameter(parameters, "numeroCivico"),
                getParameter(parameters, "extnumeroCivico")
        		);
        WSReturnManager.evaluate(
                WSClient.getClient(req.getSession()).modificaRecapito(
                    cc, 
                    r),
                req.getSession());
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html?cc="+cc, true, null); 
    }
    
}
