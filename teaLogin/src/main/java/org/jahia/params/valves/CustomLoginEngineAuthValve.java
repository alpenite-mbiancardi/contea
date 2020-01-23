package org.jahia.params.valves;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jahia.pipelines.PipelineException;
import org.jahia.pipelines.valves.ValveContext;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.usermanager.JahiaUser;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;

import java.util.ArrayList;
import java.util.Locale;


/**
 *
 * @author Matteo Zanioli <mzanioli@alpenite.com>
 */
public class CustomLoginEngineAuthValve extends AutoRegisteredBaseAuthValve {

    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(CustomLoginEngineAuthValve.class);

    public void invoke(Object context, ValveContext valveContext) throws PipelineException {
        AuthValveContext authContext = (AuthValveContext) context;
        HttpServletRequest httpServletRequest = authContext.getRequest();

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (username != null) {
            JahiaUser theUser = ServicesRegistry.getInstance().getJahiaUserManagerService().lookupUser(username);
           
            if (theUser != null && theUser.verifyPassword(password) && !isAccountLocked(theUser)) {
                logger.info("CustomLogin");
                HttpSession session = httpServletRequest.getSession(true);
                
                // se entra root le proprieta'possono essere nulle
                String bp = theUser.getProperty("j:bp");
                String userType = theUser.getProperty("j:tipoBP");
                
                // imposto dei dati di default per l'utente root o privo
                // delle informazioni bp e tipoBP (es. un utente jahia creato
                // dall'area di amministrazione)
                if (bp == null && userType == null) {
                   session.setAttribute("BPUSESSION", "000000000");
                   session.setAttribute("BPCSESSION", "000000000");
                }
                
                if (bp == null || bp.equals("")) {
                    bp = "000000000";
                }
                logger.info("BP: " + bp);
                
                if (bp != null && !bp.equals("")) {
                    if (session != null) {
                        logger.info("Set BPUSESSION");
                        if (session.getAttribute("BPUSESSION") != bp) {
                            session.setAttribute("BPUSESSION", bp);
                        }
                        if (userType != null && !userType.equals(Constants.TIPO_AMM_COND)) {
                            logger.info("Set BPCSESSION");
                            if (session.getAttribute("BPCSESSION") != bp) {
                                session.setAttribute("BPCSESSION", bp);
                            }
                        }
                        
                        Locale local = authContext.getRequest().getLocale();
                        String lang = local.getDisplayLanguage() == "it" ? "it" : local.getDisplayLanguage();
                        if (session.getAttribute("LANGSESSION") != lang) {
                            session.setAttribute("LANGSESSION", lang);
                        }
                        logger.info("Set lang"+lang);
                        logger.info("locale: " + local);
                        logger.info("Session id: " + session.getId());
                        try {
                            // creo il client relativo all'utente loggato
                            String bpU = session.getAttribute("BPUSESSION") == null ? null : session.getAttribute("BPUSESSION").toString();
                            String bpC = session.getAttribute("BPCSESSION") == null ? null : session.getAttribute("BPCSESSION").toString();
                            WSClient client = WSClient.getClient(bpU, bpC);
                            client.setLingua(lang);
                            session.setAttribute("WSCLIENT", client);
                            System.out.println(session.getAttribute("WSCLIENT"));
                            System.out.println(session.getAttribute("BPUSESSION").toString());
                        } catch (Exception e) {
                            // codice lingua obbligatorio
                            e.printStackTrace();
                        }
                    }
                }
            }else{
            	// message
            	/*if(theUser == null){
            		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_AUTH_GENERAL, this.getClass().getName(), "return" , new ArrayList<String>()), httpServletRequest.getSession(true));
            		return;
            	}
            	if(isAccountLocked(theUser))
            		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_AUTH_LOCKED_USER, this.getClass().getName(), "return" , new ArrayList<String>()), httpServletRequest.getSession(true));
            	else
            		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_AUTH_GENERAL, this.getClass().getName(), "return" , new ArrayList<String>()), httpServletRequest.getSession(true));
            	return;*/
            }
        }
        valveContext.invokeNext(context);
    }
}
