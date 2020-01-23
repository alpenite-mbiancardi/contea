package com.alpenite.tea.indirizzoFornitura;

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
public class IndirizzoFornitura extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String cc = getParameter(parameters, "cc");
        String c = getParameter(parameters, "c");
        String servizio = getParameter(parameters, "servizio");
        WSReturnManager.evaluate(
                WSClient.getClient(req.getSession()).setIndirizzoFornitura(
                    cc,
                    c,
                    getParameter(parameters, "numeroCivicoFornitura"),
                    getParameter(parameters, "estensioneNumeroCivicoFornitura")),
                req.getSession());
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html?cc="+cc+"&c="+c+"&servizio="+servizio, true, null);
    }
    
}



