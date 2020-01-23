package com.alpenite.tea.autoletture;

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
public class AutolettureAction extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String c = getParameter(parameters, "c");
        String cc = getParameter(parameters, "cc");
        String servizio = getParameter(parameters, "servizio");
        boolean isAcqua = getParameter(parameters, "isAcqua")!=null&&getParameter(parameters, "isAcqua").equals("true")?true:false;
        // creo la richiesta
        WSReturnManager.evaluate(
                WSClient.getClient(req.getSession()).comunicaAutolettura(
                    c,
                    getParameter(parameters, "valoreAutolettura"),
                    isAcqua
                ),
                req.getSession());
        
        // eseguo un redirect alla pagina di conferma
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html?cc="+cc+"&c="+c+"&servizio="+servizio+"&isAcqua="+isAcqua+"&status=A", true, null);        
    }
    
}
