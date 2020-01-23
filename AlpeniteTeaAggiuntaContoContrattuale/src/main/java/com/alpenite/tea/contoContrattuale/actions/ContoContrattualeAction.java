package com.alpenite.tea.contoContrattuale.actions;

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
 * Permette di inviare al server una SR con la quale richiedere l'associazione
 * di un conto contrattuale
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class ContoContrattualeAction extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        WSReturnManager.evaluate(
            WSClient.getClient(req.getSession()).aggiungiContoContrattuale(getParameter(parameters, "contoContrattuale")),
            req.getSession());
        
        
        // eseguo un redirect alla pagina di conferma
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html", true, null);
    }
    
}
