package com.alpenite.tea.elencoDocumenti;

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
public class ElencoDocumentiCerca extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        System.out.println("Dentro Action ElencoDocumentiCerca");
    	
        String numeroFatturaInput = getParameter(parameters, "numeroFatturaInput");
       
        numeroFatturaInput = numeroFatturaInput != null?"numeroFatturaInput="+numeroFatturaInput:"";
       
       
   
        return new ActionResult(
                ActionResult.OK.getResultCode(), 
                getParameter(parameters, "conferma")+".html?"+numeroFatturaInput, 
                true, null);
    }
    
}
