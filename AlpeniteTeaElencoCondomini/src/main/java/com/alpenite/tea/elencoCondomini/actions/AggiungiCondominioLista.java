package com.alpenite.tea.elencoCondomini.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.json.JSONObject;

import com.alpenite.tea.condomini.dati.Condominio;

/**
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class AggiungiCondominioLista extends Action {

    /** 
     * il nome dell'attributo di sessione all'interno del quale memorizzare
     * temporaneamente la lista dei condomini da aggiungere
     */
    public static final String LISTA_AGGIUNGI_CONDOMINI = "listaAggiungiCondomini";
    
    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        HttpSession s = req.getSession();
        ArrayList<Condominio> lista = new ArrayList<Condominio>();
        // controllo se esiste in sessione l'attributo listaAggiuntaCondomini
        if (s.getAttribute(LISTA_AGGIUNGI_CONDOMINI) == null) {
            s.setAttribute(LISTA_AGGIUNGI_CONDOMINI, lista);
        } else {
            lista = (ArrayList<Condominio>)s.getAttribute(LISTA_AGGIUNGI_CONDOMINI);
        }
        
       // creo un condominio a partire dai dati passati dall'utente
        Condominio c = new Condominio(
                    ""+(lista.size()+1), 
                    getParameter(parameters, "ragioneSociale1"), 
                    getParameter(parameters, "ragioneSociale2"), 
                    getParameter(parameters, "paese"), 
                    getParameter(parameters, "provincia"), 
                    getParameter(parameters, "comune"),
                    getParameter(parameters, "frazione")==null?"":getParameter(parameters, "frazione"), 
                    getParameter(parameters, "cap"), 
                    getParameter(parameters, "via"), 
                    getParameter(parameters, "numeroCivico"),
                    getParameter(parameters, "estensioneNumeroCivico"),
                    getParameter(parameters, "contoContrattuale"));
        lista.add(c);
        
        return new ActionResult(ActionResult.OK_JSON.getResultCode(), null, new JSONObject(c));
    }
    
}
