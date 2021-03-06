package com.alpenite.tea.elencoCondomini.actions;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.condomini.dati.Condominio;
import java.util.ArrayList;
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
public class RimuoviCondomini extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        ArrayList<Condominio> condomini = new ArrayList<Condominio>();
        if (parameters.containsKey("selezione")) {
            for (String codiceBP : parameters.get("selezione")) {
                condomini.add(new Condominio(codiceBP, null, null, null, null, null, null, null, null, null));
            }
            WSReturnManager.evaluate(
                    WSClient.getClient(req.getSession()).aggiungiRimuoviCondomini(
                    condomini,
                    false),
                    req.getSession());
        }

        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma") + ".html", true, null);
    }
}
