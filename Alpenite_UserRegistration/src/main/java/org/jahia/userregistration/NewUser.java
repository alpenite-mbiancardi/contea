package org.jahia.userregistration;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRPropertyWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.sites.JahiaSitesService;

import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.settings.SettingsBean;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.sapMessages.dati.Message;
import com.sun.xml.bind.v2.TODO;

import java.util.UUID;


public class NewUser extends Action {

    private JahiaUserManagerService userManagerService;
    private MailService mailService;
    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(NewUser.class);
    
    // constants (parameters)
	final static String USER = "username";
	final static String PWD = "password";
	final static String PWD2 = "password2";
	final static String TIPO = "tipoUtente";
	final static String CONDATI = "conDatiRadio";
	final static String CONMAIL = "newsletterRadio";
	final static String CONTEL = "telefonoRadio";
	final static String CONPOSTA = "postaRadio";
	final static String NOME = "nome";
	final static String COGNOME = "cognome";
	final static String COD_FISC = "codice_fiscale";
	final static String TEL = "telefono";
	final static String CEL = "cellulare";
	final static String PAESE = "paese";
	final static String PROV = "provincia";
	final static String PROV_EST = "provincia_estera";
	final static String COMUNE = "comune";
	final static String COMUNE_EST = "comune_estero";
	final static String VIA = "via";
	final static String NCIV = "ncivico";
	final static String EXTNCIV = "extncivico";
	final static String MAIL = "email";
	final static String RUOLO = "ruolo";
	final static String RAG_SOC = "ragione_sociale";
	final static String COD_CLIENTE = "codice_cliente";
	final static String PIVA = "partita_iva";
	final static String AZIENDAENTE = "aziendaEnte";
	final static String CAP = "cap";
	final static String PREFIXTEL = "prefissoTel";
	final static String PREFIXCEL = "prefissoCel";
	final static String PIVAIT = "pivaIT";
	
	final static String TIPO_PRIVATO = "privato";
	final static String TIPO_AZIENDA = "azienda";
	final static String TIPO_PRIV_CLI = "privato_cliente";
	final static String TIPO_AZ_CLI = "azienda_cliente";
	final static String TIPO_AMM_COND = "amm_condominio";
	
	final static String EXTRA_INFO_TYPE = "jnt:extraInfo";
	final static String EXTRA_INFO_NAME = "extraInfo";
	final static String TEMP_INFO_TYPE = "jnt:tempInfo";
	final static String TEMP_INFO_NAME = "tempInfo";
	
	final static String GROUP_SPORTELLO = "SportelloUsers"; // indica il gruppo che contiene tutti gli utenti dello sportello
	final static String GROUP_CLIENTE = "GruppoCliente";
	final static String GROUP_PROSPECT = "GruppoProspect";
	final static String GROUP_AMMCOND = "GruppoAmmCond";
	final static String GROUP_INT = "GruppoInterlocutore";
	
	String TIPO_UTENTE_SAP = "";
	final static String SUBSCRIBE_TAG = "LINK_VALIDAZIONE";
	final static String ACCETTO = "si"; // per consensi
	final static String RIFIUTO = "no"; // per consensi
	
	final static String SAP_TRUE = "X";
	final static String SAP_FALSE = "";

	
    public void setUserManagerService(JahiaUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
    
    public void addUserToGroup(JahiaUser user, String tipoUtente, int siteID) {
    	JahiaGroupManagerService groupManagerService = ServicesRegistry.getInstance().getJahiaGroupManagerService(); 
        JahiaGroup teaGroup = groupManagerService.lookupGroup(siteID, GROUP_SPORTELLO); 
        // aggiungo l'utente al gruppo degli utenti che puo' accedere allo sportello
        if(teaGroup!=null)
        	teaGroup.addMember(user);
        // a seconda del tipo utente inserisco l'utente nel rispettivo gruppo
        String group_name = "";
        if(tipoUtente.equals(TIPO_PRIVATO) || tipoUtente.equals(TIPO_AZIENDA))
        	group_name = GROUP_PROSPECT;
        if(tipoUtente.equals(TIPO_PRIV_CLI) || tipoUtente.equals(TIPO_AZ_CLI))
        	group_name = GROUP_CLIENTE;
        if(tipoUtente.equals(TIPO_AMM_COND))
        	group_name = GROUP_AMMCOND;
        teaGroup = groupManagerService.lookupGroup(siteID, group_name);
        if(teaGroup != null)
        	teaGroup.addMember(user);
        if(tipoUtente.equals(TIPO_AZ_CLI) || tipoUtente.equals(TIPO_AZIENDA))
        	teaGroup = groupManagerService.lookupGroup(siteID, GROUP_INT);
        if(teaGroup != null)
        	teaGroup.addMember(user);
    } 
    
    private String paramsAreOk(Map<String, List<String>> parameters, String tipoUtente){
     logger.info("[Registrazione-Utente ] - Starting check registration parameters");
    	if(tipoUtente.equals(TIPO_PRIVATO)){
    	     logger.info("[Registrazione-Utente ] - Analyze parameters for user type TipoPrivato");
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE) == null || parameters.get(PAESE).get(0).equals("")){
    			logger.info("Manca il paese");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).get(0).equals(""))){
    			logger.info("Manca la provincia");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).get(0).equals(""))){
    			logger.info("Manca la provincia estera");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).get(0).equals(""))){
    			logger.info("Manca il comune");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).get(0).equals(""))){
    			logger.info("Manca il CAP");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).get(0).equals(""))){
    			logger.info("Manca il comune estero");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(VIA) == null || parameters.get(VIA).get(0).equals("")){
    			logger.info("Manca la via");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null || parameters.get(NCIV).get(0).equals("")){
    			logger.info("Manca il num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    	}
    	if(tipoUtente.equals(TIPO_AZIENDA)){
    		logger.info("[Registrazione-Utente ] - Analyze parameters for user type TipoAzienda");
    		if(parameters.get(AZIENDAENTE) == null || parameters.get(AZIENDAENTE).get(0).equals("")){
    			logger.info("Manca flag azienda/ente");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE) == null || parameters.get(PAESE).get(0).equals("")){
    			logger.info("Manca il paese");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).get(0).equals(""))){
    			logger.info("Manca la provincia");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).get(0).equals(""))){
    			logger.info("Manca il CAP");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).get(0).equals(""))){
    			logger.info("Manca la provincia estera");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).get(0).equals(""))){
    			logger.info("Manca il comune");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).get(0).equals(""))){
    			logger.info("Manca il comune estero");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(VIA) == null || parameters.get(VIA).get(0).equals("")){
    			logger.info("Manca la via");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null || parameters.get(NCIV).get(0).equals("")){
    			logger.info("Manca il num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null ){
    			logger.info("Manca l'estanzione del num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		
    		if(parameters.get(RAG_SOC) == null || parameters.get(RAG_SOC).get(0).equals("")){
    			logger.info("Manca la ragione sociale");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PIVA) == null || parameters.get(PIVA).get(0).equals("")){
    			logger.info("Manca la piva");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		//if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
    		//	logger.info("Piva non corretta");
    		//	return Constants.ERRORE_PIVA;
    		//}
    	}
    	if(tipoUtente.equals(TIPO_PRIV_CLI)){
    		logger.info("[Registrazione-Utente ] - Analyze parameters for user type cliente privato");
    		if(parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).get(0).equals("")){
    			logger.info("Manca il codice cliente");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COD_FISC) == null || parameters.get(COD_FISC).get(0).equals("")){
    			logger.info("Manca il codice fiscale");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    	}
    	if(tipoUtente.equals(TIPO_AZ_CLI)){
    		logger.info("[Registrazione-Utente ] - Analyze parameters for user type azienda cliente ");
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).get(0).equals("")){
    			logger.info("Manca il codice cliente");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PIVA) == null || parameters.get(PIVA).get(0).equals("")){
    			logger.info("Manca la piva");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
//    		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//    			logger.info("Piva non corretta");
//    			return Constants.ERRORE_PIVA;
//    		}
    	}
    	if(tipoUtente.equals(TIPO_AMM_COND)){
    		logger.info("[Registrazione-Utente ] - Analyze parameters for user type amministratore condominio");
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE) == null || parameters.get(PAESE).get(0).equals("")){
    			logger.info("Manca il paese");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).get(0).equals(""))){
    			logger.info("Manca la provincia");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).get(0).equals(""))){
    			logger.info("Manca la provincia estera");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).get(0).equals(""))){
    			logger.info("Manca il comune");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).get(0).equals(""))){
    			logger.info("Manca il CAP");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).get(0).equals(""))){
    			logger.info("Manca il comune estero");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(VIA) == null || parameters.get(VIA).get(0).equals("")){
    			logger.info("Manca la via");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null || parameters.get(NCIV).get(0).equals("")){
    			logger.info("Manca il num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PIVA) == null || parameters.get(PIVA).get(0).equals("")){
    			logger.info("Manca la piva");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
//    		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//    			logger.info("Piva non corretta");
//    			return Constants.ERRORE_PIVA;
//    		}
    		if(parameters.get(COD_FISC) == null || parameters.get(COD_FISC).get(0).equals("")){
    			logger.info("Manca codice fiscale");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    	}
    	
    	// common check
    	logger.info("[Registrazione-Utente ] - Analyze common parameters");
    	if(parameters.get(MAIL) == null || parameters.get(MAIL).get(0).equals("")){
    		logger.info("Manca la mail");
			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    	}
    	if(!mailIsOk(parameters.get(MAIL).get(0))){
    		return Constants.ERRORE_MAIL;
    	}
		if(parameters.get(USER) == null || parameters.get(USER).get(0).equals("")){
			logger.info("Manca il nome utente");
			return Constants.ERRORE_CAMPI_OBBLIGATORI;
		}
		if(!usernameIsOk(parameters.get(USER).get(0))){
			return Constants.ERRORE_USERNAME;
		}
		if(parameters.get(PWD) == null || parameters.get(PWD).get(0).equals(""))
			return Constants.ERRORE_PASSWORD;
		if(parameters.get(PWD2) == null || !parameters.get(PWD2).get(0).equals(parameters.get(PWD).get(0)))
			return Constants.ERRORE_PASSWORD;
		if(parameters.get(CONDATI) == null || !parameters.get(CONDATI).get(0).equals("si"))
			return Constants.ERRORE_CONSENSO;
    	
    	return Constants.SUCCESS;
    }
   
    private boolean usernameIsOk(String username) {
    	return !userManagerService.userExists(username);
	}

	private boolean mailIsOk(String mail) {
		if(!mail.contains("@"))
			return false;
		if(!mail.contains("."))
			return false;
		return true;
	}

	@Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
 
    	
    	// variables
    	String nome = "";
    	String cognome = "";
    	String ragione_sociale = "";
    	String bp = "";
    	Message messaggio = null;
    	
    	// get tipoUtente
    	if(parameters.get(TIPO) == null)
    		return ActionResult.BAD_REQUEST;
    	String tipoUtente = parameters.get(TIPO).get(0);
    	
    	// check the paramters
    	String result = paramsAreOk(parameters, tipoUtente);
    	if(result != Constants.SUCCESS){
    		WSReturnManager.evaluate(new WSReturn<String>(result, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
    		logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +"   email="+parameters.get(MAIL) +" - "+ result);
    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    	}
    	
    	logger.info("Parameters are ok");
    	
    	// PIVA - validation
    	String piva = getParameter(parameters, PIVA);
    	String pivaIT = getParameter(parameters, PIVAIT);
    	
    	if(piva == null)
    		piva = "";
    	
    	if (pivaIT == null)
    		pivaIT = "false";
    	
    	if(piva.length() == 11 && pivaIT.equals("false")){
    		piva = "IT"+piva;
    	}
    	
    	
    	// check SAP
    	if(tipoUtente.equals(TIPO_PRIVATO)){
    		logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    		WSReturn<Message> msg = WSClient.getClient().controllaProspect("P", getParameter(parameters, MAIL), null);
    		messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("TipoUtente: "+tipoUtente);
				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
		}
    	if(tipoUtente.equals(TIPO_AZIENDA)){
    		WSReturn<Message> msg = null;
    		if(parameters.get(AZIENDAENTE).get(0).equalsIgnoreCase("azienda")){
    			logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    			msg = WSClient.getClient().controllaProspect("A", getParameter(parameters, MAIL), piva);
    		}else{
    			logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
				msg = WSClient.getClient().controllaProspect("E", getParameter(parameters, MAIL), piva);
    		}
			messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("TipoUtente: "+tipoUtente);
				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
    	}
    	if(tipoUtente.equals(TIPO_PRIV_CLI)){
    		logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    		WSReturn<Message> msg = WSClient.getClient().controllaUsername("P", getParameter(parameters, COD_CLIENTE), getParameter(parameters, MAIL), getParameter(parameters, COD_FISC), "");
			messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("TipoUtente: "+tipoUtente);
				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			bp = messaggio.getBp();
		}
		if(tipoUtente.equals(TIPO_AZ_CLI)){
			WSReturn<Message> msg = null;
			if(parameters.get(AZIENDAENTE).get(0).equalsIgnoreCase("azienda"))
				msg = WSClient.getClient().controllaUsername("A", getParameter(parameters, COD_CLIENTE), getParameter(parameters, MAIL), getParameter(parameters, COD_FISC), piva);
			else
				msg = WSClient.getClient().controllaUsername("E", getParameter(parameters, COD_CLIENTE), getParameter(parameters, MAIL), getParameter(parameters, COD_FISC), piva);
			messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("TipoUtente: "+tipoUtente);
				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
		}
		if(tipoUtente.equals(TIPO_AMM_COND)){
			WSReturn<Message> msg = WSClient.getClient().controllaUsername("AM", null, getParameter(parameters, MAIL), getParameter(parameters, COD_FISC), piva);
			messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("TipoUtente: "+tipoUtente);
				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
			bp = messaggio.getBp();
		}
    	
    	logger.info("SAP call done");
    	
    	// all verification done
    	String codice = UUID.randomUUID().toString();
    	// build list of properties
    	Properties properties = new Properties();
    	properties.put("j:firstName", nome);
    	properties.put("j:lastName", cognome);
    	properties.put("j:accountLocked", "true");
    	properties.put("j:validato", "false");
    	properties.put("j:tipoBP", tipoUtente);
    	properties.put("j:nuovaMail", parameters.get(MAIL).get(0));
    	properties.put("j:codiceVerifica", codice);
    	properties.put("j:bp", bp);
    	properties.put("preferredLanguage", parameters.get("lang").get(0));
    	if(tipoUtente.equals(TIPO_AZIENDA))
    		properties.put("j:organization", ragione_sociale);
    	// check the username (fix 22/10/2013)
    	if(!userManagerService.isUsernameSyntaxCorrect(parameters.get(USER).get(0))){
    		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_USERNAME_UPPERCASE, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
    		logger.info(" [Registrazione-Utente ]- userName ="+ parameters.get(USER) +"   email="+parameters.get(MAIL) +" - "+ result + " - " +"Errore username con lettere minuscole ");
    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    	}
    	// create the user
    	final JahiaUser user = userManagerService.createUser(parameters.get(USER).get(0), parameters.get(PWD).get(0), properties);
		// add user to group
    	addUserToGroup(user, tipoUtente, renderContext.getSite().getID());
    	
    	logger.info(" [Registrazione-Utente ]-User preparation done");
    	
    	// mail preparation
    	String from = parameters.get("from")==null?SettingsBean.getInstance().getMail_from():getParameter(parameters, "from");
		String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
		String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
		String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
		
		logger.info("Mail preparation done");
		logger.info(" [Registrazione-Utente ]- Mail preparation done");
		logger.info("Ragione sociale" + getParameter(parameters, RAG_SOC));
		
		// tempNode		
		JCRNodeWrapper node = session.getNode(user.getLocalPath());
		JCRNodeWrapper tempInfoNode = node.addNode(TEMP_INFO_NAME,TEMP_INFO_TYPE);
		tempInfoNode.setProperty("j:aziendaEnte", parameters.get(AZIENDAENTE)==null?"":parameters.get(AZIENDAENTE).get(0));
		tempInfoNode.setProperty("j:ragione_sociale", getParameter(parameters, RAG_SOC));
		tempInfoNode.setProperty("j:telefono", parameters.get(TEL)==null?"":parameters.get(TEL).get(0));
		tempInfoNode.setProperty("j:cellulare", parameters.get(CEL)==null?"":parameters.get(CEL).get(0));
		tempInfoNode.setProperty("j:paese", parameters.get(PAESE)==null?"":parameters.get(PAESE).get(0));
		tempInfoNode.setProperty("j:provincia", parameters.get(PROV)==null?"":parameters.get(PROV).get(0));
		tempInfoNode.setProperty("j:comune", parameters.get(COMUNE)==null?"":parameters.get(COMUNE).get(0));
		tempInfoNode.setProperty("j:cap", parameters.get(CAP)==null?"":parameters.get(CAP).get(0));
		tempInfoNode.setProperty("j:via", parameters.get(VIA)==null?"":parameters.get(VIA).get(0));
		tempInfoNode.setProperty("j:ncivico", parameters.get(NCIV)==null?"":parameters.get(NCIV).get(0));
		tempInfoNode.setProperty("j:extncivico", parameters.get(EXTNCIV)==null?"":parameters.get(EXTNCIV).get(0));
		tempInfoNode.setProperty("j:codiceCliente", parameters.get(COD_CLIENTE)==null?"":parameters.get(COD_CLIENTE).get(0));
		tempInfoNode.setProperty("j:codice_fiscale", parameters.get(COD_FISC)==null?"":parameters.get(COD_FISC).get(0));
		tempInfoNode.setProperty("j:ruolo", parameters.get(RUOLO)==null?"":parameters.get(RUOLO).get(0));
		tempInfoNode.setProperty("j:partita_iva", piva);
		tempInfoNode.setProperty("j:conDati", parameters.get(CONDATI).get(0).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		tempInfoNode.setProperty("j:conNewsletter", parameters.get(CONMAIL).get(0).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		tempInfoNode.setProperty("j:conTelefono", parameters.get(CONTEL).get(0).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		tempInfoNode.setProperty("j:conPosta", parameters.get(CONPOSTA).get(0).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		tempInfoNode.setProperty("j:prefissoTel", parameters.get(PREFIXTEL)==null?"+39":parameters.get(PREFIXTEL).get(0).replaceAll(" ", ""));
		tempInfoNode.setProperty("j:prefissoCel", parameters.get(PREFIXCEL)==null?"+39":parameters.get(PREFIXCEL).get(0).replaceAll(" ", ""));
		
		logger.info(" [Registrazione-Utente ]- tempInfoNode done");
		
		Map<String,Object> bindings = new HashMap<String,Object>();
        bindings.put("newUser",user);
		
		// save the session
		session.save();
		
		logger.info("session saved");
		
		// send the mail
		// build the link
		JCRNodeWrapper page = session.getNodeByIdentifier(parameters.get("verificationPage").get(0));
		String link = req.getScheme()+ "://" + req.getServerName() +":"+req.getServerPort() + page.getUrl() + "?username="+parameters.get(USER).get(0)+"&codice="+codice+"&mail="+parameters.get(MAIL).get(0);
		logger.info(" [Registrazione-Utente ]- Info mail: "+parameters.get("lang").get(0));
		String mail = "";
		try{
			mail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_"+parameters.get("lang").get(0)).getPropertyAsString("mail");
		}catch(PathNotFoundException e){
			mail = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_en").getPropertyAsString("mail");
		}
		String mail_tea = mail.replaceAll(SUBSCRIBE_TAG, link);			
		mailService.sendHtmlMessage(from, parameters.get(MAIL).get(0), cc, bcc, subject, mail_tea);
		
		logger.info(" [Registrazione-Utente ]- mail sent");
		
		// message preparation
		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_REGISTRATION, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		// return the page
    	//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		logger.info(" [Registrazione-Utente ]- forward page: "+ (session.getNodeByIdentifier(parameters.get("finalPage").get(0))).getPath());
		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("finalPage").get(0))).getPath());
	}
		
	public void setMailService(MailService mailService) 
		{
			this.mailService = mailService;
		}	
    	
	private boolean isMessageValid(Message messaggio){
    	if(messaggio == null || messaggio.isAnError()){
    		return false;
    	}
    	return true;
    }
    	
	
	}
