package com.alpenite.tea.elencoCondomini.actions;

import com.alpenite.tea.communicationLayer.WSClient;
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
public class SelezionaCondominio extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        req.getSession().setAttribute("BPCSESSION", getParameter(parameters, "selezione"));
        
        // cambia l'utenza nel client
        WSClient.getClient(req.getSession()).setBpCliente(getParameter(parameters, "selezione"));
        
        req.getSession().setAttribute("UINFSESSION", getParameter(parameters, "nomeCond"));
        
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html", true, null);
    }
    
}
