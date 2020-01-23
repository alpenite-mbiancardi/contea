package com.alpenite.tea.domiciliazioneBancaria;

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
public class DomiciliazioneBancariaAction extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext,
            Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {

        // passo tutti i parametri alla request in modo da poterla usare nel modulo
        for( String parametro: parameters.keySet()) {
            session.getUser().setProperty("rid_"+parametro, getParameter(parameters, parametro));
        }

        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "path_rid"));
    }
}
