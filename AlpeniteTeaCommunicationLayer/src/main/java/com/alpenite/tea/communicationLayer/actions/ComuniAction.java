package com.alpenite.tea.communicationLayer.actions;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Comune;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Azione che permette di tornare la lista dei comuni utilizzando Ajax
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class ComuniAction extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        List<Comune> comuni = WSClient.getClient().getComuni(getParameter(parameters, "provincia")).getRitorno();
    	JSONObject json = new JSONObject();
    	JSONArray arrayComuni = new JSONArray();
        for (Comune comune : comuni) {
            JSONObject datiComune = new JSONObject();
            datiComune.put("nome", comune.getNome());
            datiComune.put("codiceCatastale", comune.getCodiceCatastale());
            datiComune.put("cap", comune.getCap());
            arrayComuni.put(datiComune);
        }
        json.put("comuni", arrayComuni);
    	return new ActionResult(ActionResult.OK.getResultCode(), null, json);
    }
    
}
