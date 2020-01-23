package com.alpenite.tea.invioFattura;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;

/**
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class InvioFattura extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String cc = getParameter(parameters, "cc");
        WSReturnManager.evaluate(
                WSClient.getClient(req.getSession()).setFlagCartaceoEmail(
                cc,
                getParameter(parameters, "copiaCartacea") != null,
                getParameter(parameters, "copiaEmail")    != null,
                getParameter(parameters, "pdfInEmail")    != null,
                getParameter(parameters, "detbol")        != null),
                req.getSession());
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html?cc="+cc, true, null);
    }
    
}
