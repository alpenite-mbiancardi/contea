package com.alpenite.tea.datiCatastali;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.datiCatastali.dati.DatiCatastali;
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
 * Permette di gestire l'azione del form di modifica dei dati catastali
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DatiCatastaliAction extends Action {

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String cc = getParameter(parameters, "cc");
        String c = getParameter(parameters, "c");
        String servizio = getParameter(parameters, "servizio");
        try {
            DatiCatastali dc = new DatiCatastali(
                    getParameter(parameters, "comuneAmministrativo"),
                    getParameter(parameters, "comuneCatastale"),
                    getParameter(parameters, "codiceCatastale"),
                    getParameter(parameters, "sezioneUrbana"),
                    getParameter(parameters, "particella"),
                    getParameter(parameters, "particellaSistemaTavolare"),
                    getParameter(parameters, "tipoParticella"),
                    getParameter(parameters, "subalterno"),
                    getParameter(parameters, "foglio"));

            WSReturnManager.evaluate(
                    WSClient.getClient(req.getSession()).setDatiCatastali(
                    cc,
                    c,
                    dc),
                    req.getSession());
        } catch (Exception e) {
            e.printStackTrace();
            WSReturnManager.evaluate(
                    new WSReturn(Constants.ERRORE_CAMPI_NON_CORRETTI, "DatiCatastaliAction.doExecute", null, null),
                    req.getSession());
        }
        return new ActionResult(ActionResult.OK.getResultCode(), getParameter(parameters, "conferma")+".html?cc="+cc+"&c="+c+"&servizio="+servizio, true, null);    }
}
