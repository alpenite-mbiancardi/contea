package com.alpenite.tea.datiAnagrafici;


import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.datiAnagrafici.dati.DatiAnagrafici;
import com.alpenite.tea.datiCatastali.dati.DatiCatastali;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.slf4j.Logger;


public class SendDatiAnagrafici extends Action {

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(SendDatiAnagrafici.class);
	
	private boolean checkNull(String... parameters){
		for(String s: parameters){
			if(s==null)
				return true;
		}
		return false;
	}
	
    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        String bp = getParameter(parameters, "bp");
        String tipoBp = getParameter(parameters, "tipoBp");
        String tipoBpSAP = "";
        if(tipoBp.equals(Constants.TIPO_PRIVATO) || tipoBp.equals(Constants.TIPO_PRIV_CLI)) 
        	tipoBpSAP = "P";
        if(tipoBp.equals(Constants.TIPO_AZ_CLI) || tipoBp.equals(Constants.TIPO_AZIENDA))
        	tipoBpSAP = "I";
        if(tipoBp.equals(Constants.TIPO_AMM_COND))
        	tipoBpSAP = "AM";
        
        DatiAnagrafici datiAnagraficiOld = new DatiAnagrafici(
        		bp, 
        		tipoBpSAP, 
        		(getParameter(parameters, "ragioneSociale1Old") != null ) ? getParameter(parameters, "ragioneSociale1Old") : "",
        		(getParameter(parameters, "ragioneSociale2Old") != null ) ? getParameter(parameters, "ragioneSociale2Old") : "",
        		(getParameter(parameters, "ragioneSociale3Old") != null ) ? getParameter(parameters, "ragioneSociale3Old") : "",
        		(getParameter(parameters, "ragioneSociale4Old") != null ) ? getParameter(parameters, "ragioneSociale4Old") : "",
        		(getParameter(parameters, "nomeOld") != null ) ? getParameter(parameters, "nomeOld") : "",
        		(getParameter(parameters, "cognomeOld") != null ) ? getParameter(parameters, "cognomeOld") : "",
        		(getParameter(parameters, "telefonoOld") != null ) ? getParameter(parameters, "telefonoOld") : "",
                (getParameter(parameters, "cellulareOld") != null ) ? getParameter(parameters, "cellulareOld") : "",
                (getParameter(parameters, "paeseOld") != null ) ? getParameter(parameters, "paeseOld") : "",
                (getParameter(parameters, "provinciaOld") != null ) ? getParameter(parameters, "provinciaOld") : "",
                (getParameter(parameters, "comuneOld") != null ) ? getParameter(parameters, "comuneOld") : "",
                (getParameter(parameters, "frazioneOld") != null ) ? getParameter(parameters, "frazioneOld") : "",
                (getParameter(parameters, "viaOld") != null ) ? getParameter(parameters, "viaOld") : "",
                (getParameter(parameters, "ncivicoOld") != null ) ? getParameter(parameters, "ncivicoOld") : "",
                 (getParameter(parameters, "ncivicoOld") != null ) ? getParameter(parameters, "ncivicoExtOld") : "",		
                (getParameter(parameters, "capOld") != null ) ? getParameter(parameters, "capOld") : "",
                (getParameter(parameters, "codiceFiscaleOld") != null ) ? getParameter(parameters, "codiceFiscaleOld") : "",
                (getParameter(parameters, "partitaIVAOld") != null ) ? getParameter(parameters, "partitaIVAOld") : "",
                (getParameter(parameters, "privacyOld") != null ) ? getParameter(parameters, "privacyOld") : "X",
                (getParameter(parameters, "privacyMailOld") != null ) ? getParameter(parameters, "privacyMailOld") : "",
                (getParameter(parameters, "privacyEmailOld") != null ) ? getParameter(parameters, "privacyEmailOld") : "",
                (getParameter(parameters, "privacyTelOld") != null ) ? getParameter(parameters, "privacyTelOld") : "",
                (getParameter(parameters, "ruoloOld") != null) ? getParameter(parameters, "ruoloOld") : "",
        		(getParameter(parameters, "prefissoTelOld") != null) ? getParameter(parameters, "prefissoTelOld") : "+39",
				(getParameter(parameters, "prefissoCelOld") != null) ? getParameter(parameters, "prefissoCelOld") : "+39"
        				);
        
        DatiAnagrafici datiAnagrafici = new DatiAnagrafici();
        datiAnagrafici.setCodiceBP(bp);
        datiAnagrafici.setTipoBP(tipoBpSAP);
        datiAnagrafici.setRagioneSociale1((getParameter(parameters, "ragioneSociale1") != null ) ? getParameter(parameters, "ragioneSociale1") : "");
        datiAnagrafici.setRagioneSociale2((getParameter(parameters, "ragioneSociale2") != null ) ? getParameter(parameters, "ragioneSociale2") : "");
        datiAnagrafici.setRagioneSociale3((getParameter(parameters, "ragioneSociale3") != null ) ? getParameter(parameters, "ragioneSociale3") : "");
        datiAnagrafici.setRagioneSociale4((getParameter(parameters, "ragioneSociale4") != null ) ? getParameter(parameters, "ragioneSociale4") : "");
        datiAnagrafici.setNome((getParameter(parameters, "nome") != null ) ? getParameter(parameters, "nome") : "");
        datiAnagrafici.setCognome((getParameter(parameters, "cognome") != null ) ? getParameter(parameters, "cognome") : "");
        logger.info("Rag.Soc.: "+datiAnagrafici.getRagioneSociale1() + " - "+getParameter(parameters, "ragioneSociale1"));
        
        // tel
        String tel = getParameter(parameters, "telefono");

        String prefissoTel = (getParameter(parameters, "prefixTel")==null?"+39":getParameter(parameters, "prefixTel"));
        if(datiAnagraficiOld.getPrefissoTel() == null){
        	datiAnagrafici.setPrefissoTel(prefissoTel);
        }
        if(datiAnagraficiOld.getTelefono() == null){
        	datiAnagrafici.setTelefono(tel);
        }
        if(prefissoTel != null && datiAnagraficiOld.getPrefissoTel() != null && !datiAnagraficiOld.getPrefissoTel().equals(prefissoTel)){
        	datiAnagrafici.setPrefissoTel(prefissoTel);
        }
        if(tel != null && datiAnagraficiOld.getTelefono() != null && !datiAnagraficiOld.getTelefono().equals(tel)){
        	datiAnagrafici.setTelefono(tel);
        }
        
        // end tel
        // cel
        String cel = getParameter(parameters, "cellulare");
        String prefissoCel = (getParameter(parameters, "prefixCel")==null?"+39":getParameter(parameters, "prefixCel"));
        if(datiAnagraficiOld.getPrefissoCel() == null){
        	datiAnagrafici.setPrefissoCel(prefissoCel);
        }
        if(datiAnagraficiOld.getCellulare() == null){
        	datiAnagrafici.setCellulare(cel);
        }
        if(prefissoCel != null && datiAnagraficiOld.getPrefissoCel() != null && !datiAnagraficiOld.getPrefissoCel().equals(prefissoCel)){
        	datiAnagrafici.setPrefissoCel(prefissoCel);
        }
        if(cel != null && datiAnagraficiOld.getCellulare() != null && !datiAnagraficiOld.getCellulare().equals(cel)){
        	datiAnagrafici.setCellulare(cel);
        }
        // end cel
        // check address
        String paese = (getParameter(parameters, "paese") != null  ) ? getParameter(parameters, "paese") : "";
        String provincia = (getParameter(parameters, "provincia") != null ) ? getParameter(parameters, "provincia") : "";
        String comune = (getParameter(parameters, "comune") != null ) ? getParameter(parameters, "comune") : "";
        String via = (getParameter(parameters, "via") != null ) ? getParameter(parameters, "via") : "";
        String ncivico = (getParameter(parameters, "ncivico") != null ) ? getParameter(parameters, "ncivico") : "";
        String extncivico = (getParameter(parameters, "extncivico") != null ) ? getParameter(parameters, "extncivico") : "";
        String cap = (getParameter(parameters, "cap") != null ) ? getParameter(parameters, "cap") : "";
       logger.info("Parametro " + getParameter(parameters, "extncivico")+" - Valore " );
        
        if(tipoBp.equals(Constants.TIPO_AMM_COND) || tipoBp.equals(Constants.TIPO_PRIV_CLI) || tipoBp.equals(Constants.TIPO_PRIVATO)){
        if(!checkNull(paese, provincia, comune, via, ncivico,extncivico, cap)){
        	if(checkNull(datiAnagraficiOld.getProvincia(),datiAnagraficiOld.getPaese(),datiAnagraficiOld.getComune(),datiAnagraficiOld.getVia(), datiAnagraficiOld.getNumeroCivico(),datiAnagraficiOld.getEstenzioneNumeroCivico(),datiAnagraficiOld.getCap())){
        		datiAnagrafici.setPaese(paese);
        		datiAnagrafici.setProvincia(provincia);
        		datiAnagrafici.setComune(comune);
        		datiAnagrafici.setCap(cap);
        		datiAnagrafici.setVia(via);
        		datiAnagrafici.setNumeroCivico(ncivico);
        		datiAnagrafici.setEstenzioneNumeroCivico(extncivico);
        		logger.info("datiAnagraficiOld == null");
        	}
        	if(!paese.equals(datiAnagraficiOld.getPaese()) 
        			|| !provincia.equals(datiAnagraficiOld.getProvincia())
        			|| !comune.equals(datiAnagraficiOld.getComune())
        			|| !via.equals(datiAnagraficiOld.getVia())
        			|| !ncivico.equals(datiAnagraficiOld.getNumeroCivico())
        			|| !extncivico.equals(datiAnagraficiOld.getEstenzioneNumeroCivico())
        			|| !cap.equals(datiAnagraficiOld.getCap())){
        		datiAnagrafici.setPaese(paese);
        		datiAnagrafici.setProvincia(provincia);
        		datiAnagrafici.setComune(comune);
        		datiAnagrafici.setCap(cap);
        		datiAnagrafici.setVia(via);
        		datiAnagrafici.setNumeroCivico(ncivico);
        		datiAnagrafici.setEstenzioneNumeroCivico(extncivico);
        		logger.info("datiAnagrafici != datiAnagraficiOld");
        	}
        }
        }else{
        	
        }
        // end address
        datiAnagrafici.setCodiceFiscale((getParameter(parameters, "codiceFiscale") != null ) ? getParameter(parameters, "codiceFiscale") : "");
        datiAnagrafici.setPartitaIVA((getParameter(parameters, "partitaIVA") != null ) ? getParameter(parameters, "partitaIVA") : "");
        datiAnagrafici.setPrivacy((getParameter(parameters, "privacy") != null ) ? getParameter(parameters, "privacy") : "X");
        datiAnagrafici.setPrivacyEmail((getParameter(parameters, "privacyEmail") != null ) ? getParameter(parameters, "privacyEmail") : "");
        datiAnagrafici.setPrivacyMail((getParameter(parameters, "privacyMail") != null ) ? getParameter(parameters, "privacyMail") : "");
        datiAnagrafici.setPrivacyTel((getParameter(parameters, "privacyTel") != null ) ? getParameter(parameters, "privacyTel") : "");
        datiAnagrafici.setRuolo((getParameter(parameters, "ruolo") != null) ? getParameter(parameters, "ruolo") : "");
        
        String srRequest = "";
        logger.info("tipo bp: " + tipoBp + " = Constants.TIPO_AZ_CLI: " +Constants.TIPO_AZ_CLI);
        if(!tipoBp.equals(Constants.TIPO_AMM_COND) && !tipoBp.equals(Constants.TIPO_AZ_CLI) && !tipoBp.equals(Constants.TIPO_AZIENDA))
        	srRequest = "SR";
        datiAnagrafici.setEstenzioneNumeroCivico(extncivico);
        logger.debug("Verifica Dati Utente :"+datiAnagrafici.getEstenzioneNumeroCivico() );
        WSClient client;
        try {
        	client = WSClient.getClient(req.getSession());
		} catch (Exception e) {
			client = WSClient.getClient(bp);
		}
        WSReturn result = client.setDatiAnagrafici(bp, srRequest, datiAnagrafici, Constants.getJahiaType(tipoBp));
        WSReturnManager.evaluate(result, req.getSession());
        if(!srRequest.equals("SR") && result.getCodice().equals("00")){
        	// update jahia information
        	JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
            JahiaUser user = usrManager.lookupUser(getParameter(parameters, "username"));
        	if(tipoBp.equals(Constants.TIPO_PRIVATO) || tipoBp.equals(Constants.TIPO_PRIV_CLI) || tipoBp.equals(Constants.TIPO_AZIENDA) || tipoBp.equals(Constants.TIPO_AZ_CLI)){
        		user.setProperty("j:firstName", datiAnagrafici.getNome());
        		user.setProperty("j:lastName", datiAnagrafici.getCognome());
        	}else{
        		// amministratore di condominio
        		user.setProperty("j:firstName", datiAnagrafici.getRagioneSociale1());
        		user.setProperty("j:lastName", datiAnagrafici.getRagioneSociale2());
        	}
        	
        }
        
        return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("conferma").get(0))).getPath());
        }
    
}


