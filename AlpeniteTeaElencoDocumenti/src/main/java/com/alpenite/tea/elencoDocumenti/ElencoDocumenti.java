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
public class ElencoDocumenti extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        System.out.println("Dentro Action ElencoDocumenti");
    	String anno = getParameter(parameters, "anno");
        String dataInizio = getParameter(parameters, "dataInizio");
        String dataFine = getParameter(parameters, "dataFine");
        dataInizio = dataInizio != null?"&dataInizio="+dataInizio:"";
        dataFine = dataFine != null?"&dataFine="+dataFine:"";
        String servizio = getParameter(parameters, "servizio");
        String e = getParameter(parameters, "e");
        
        if (anno != null) {
            dataInizio = "&dataInizio=01/01/"+anno;
            dataFine = "&dataFine=31/12/"+anno;
        }
        String azienda = getParameter(parameters, "compagnia");
        System.out.println("Dentro Action ElencoDocumenti"+azienda);
        String cc = getParameter(parameters, "cc");
        String displayTab = getParameter(parameters, "displayTab");
        return new ActionResult(
                ActionResult.OK.getResultCode(), 
                getParameter(parameters, "conferma")+".html?cc="+cc+"&servizio="+servizio+"&compagnia="+azienda+"&e="+e+"&displayTab="+displayTab+dataInizio+dataFine, 
                true, null);
    }
    
}
