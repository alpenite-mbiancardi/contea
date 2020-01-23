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
public class AggiungiCondomini extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        // prelevo la lista dei condomini attualmente inseriti
        Object o = req.getSession().getAttribute(AggiungiCondominioLista.LISTA_AGGIUNGI_CONDOMINI);
        ArrayList<Condominio> lista = new ArrayList<Condominio>();
        if (o != null && o instanceof ArrayList) {
            lista = (ArrayList<Condominio>)o;
        }
        
        // controllo quali di questi sono selezionati tramite la variabile seleziona
        // e li mantengo nella lista
        ArrayList<Condominio> condomini = new ArrayList<Condominio>();
        for (Condominio condominio : lista) {
            if (parameters.get("selezione").contains(condominio.getCodiceBP())) {
                condominio.setCodiceBP(null);
                condomini.add(condominio);
            }
        }
        
        // chiama il ws con la lista dei condomini selezionati
        WSReturnManager.evaluate(
                WSClient.getClient(req.getSession()).aggiungiRimuoviCondomini(
                    condomini, 
                    true),
                req.getSession());
        
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html", true, null);
    }
    
}
