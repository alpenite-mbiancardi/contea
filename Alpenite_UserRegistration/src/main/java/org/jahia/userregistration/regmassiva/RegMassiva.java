package org.jahia.userregistration.regmassiva;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.bin.Render;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.content.JCRTemplate;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;

import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.communicationLayer.ws.crm.ZptStMess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.alpenite.tea.datiAnagrafici.dati.DatiAnagrafici;
import com.alpenite.tea.sapMessages.dati.Message;
import com.sun.xml.bind.v2.TODO;

import java.util.UUID;


public class RegMassiva extends Action{
	

	 	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(RegMassiva.class);
	 	public JahiaUserManagerService userManagerService;
	 	private MailService mailService;
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
    
    
		 //campi conferma validazione
		 
		 private static String USERNAME = "username";
			private static String CODE = "codice";
			private static String MAILCONF = "mail";
			private static String TEMPINFO = "tempInfo";
			private static String NUOVAMAIL = "j:nuovaMail";
			private static String CODICEVERIFICA = "j:codiceVerifica";
			private static String ADDMAIL = "addMail";
	 
    
    public boolean checkUsername (String username) {
    	
    	if(userManagerService.userExists(username)){
    		return true;
    	}else{
    		return false;
    	}
    	
    	
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
    
    private String paramsAreOk(HashMap<String, String> parameters, String tipoUtente) throws JSONException{
        logger.info("[Registrazione-Utente ] - Starting check registration parameters");
        String validato = Constants.SUCCESS;
       	if(tipoUtente.equals(TIPO_PRIVATO)){
       	     logger.info("[Registrazione-Utente ] - Analyze parameters for user type TipoPrivato");
       		if(parameters.get(NOME) == null || parameters.get(NOME).equals("")){
       			logger.info("Manca il nome");
       			validato+= "Manca il nome ";
       		}
       		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")){
       			logger.info("Manca il cognome");
       			validato+= "Manca il cognome ";
       		}
       		if(parameters.get(PAESE) == null || parameters.get(PAESE).equals("")){
       			logger.info("Manca il paese");
       			validato+= "Manca il paese ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).equals(""))){
       			logger.info("Manca la provincia");
       			validato+= "Manca la provincia ";
       		}
       		if(!parameters.get(PAESE).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).equals(""))){
       			logger.info("Manca la provincia estera");
       			validato+= "Manca la provincia estera ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).equals(""))){
       			logger.info("Manca il comune");
       			validato+= "Manca il comune ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).equals(""))){
       			logger.info("Manca il CAP");
       			validato+= "Manca il CAP ";
       		}
       		if(!parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).equals(""))){
       			logger.info("Manca il comune estero");
       			validato+= "Manca il comune estero";
       		}
       		if(parameters.get(VIA) == null || parameters.get(VIA).equals("")){
       			logger.info("Manca la via");
       			return "Manca la via ";
       		}
       		if(parameters.get(NCIV) == null || parameters.get(NCIV).equals("")){
       			logger.info("Manca il num. civico");
       			validato+= "Manca il num. civico ";
       		}
       	}
       	if(tipoUtente.equals(TIPO_AZIENDA)){
       		logger.info("[Registrazione-Utente ] - Analyze parameters for user type TipoAzienda");
       		if(parameters.get(AZIENDAENTE) == null || parameters.get(AZIENDAENTE).equals("")){
       			logger.info("Manca flag azienda/ente");
       			validato+= "Manca flag azienda/ente ";
       		}
       		if(parameters.get(NOME) == null || parameters.get(NOME).equals("")){
       			logger.info("Manca il nome ");
       			validato+= "Manca il nome ";
       		}
       		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")){
       			logger.info("Manca il cognome ");
       			validato+= "Manca il cognome";
       		}
       		if(parameters.get(PAESE) == null || parameters.get(PAESE).equals("")){
       			logger.info("Manca il paese");
       			validato+= "Manca il paese ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).equals(""))){
       			logger.info("Manca la provincia");
       			validato+= "Manca la provincia ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).equals(""))){
       			logger.info("Manca il CAP");
       			validato+= "Manca il CAP ";
       		}
       		if(!parameters.get(PAESE).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).equals(""))){
       			logger.info("Manca la provincia estera");
       			validato+= "Manca la provincia estera";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).equals(""))){
       			logger.info("Manca il comune");
       			validato+= "Manca il comune ";
       		}
       		if(!parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).equals(""))){
       			logger.info("Manca il comune estero");
       			validato+= "Manca il comune estero";
       		}
       		if(parameters.get(VIA) == null || parameters.get(VIA).equals("")){
       			logger.info("Manca la via");
       			validato+= "Manca la via ";
       		}
       		if(parameters.get(NCIV) == null || parameters.get(NCIV).equals("")){
       			logger.info("Manca il num. civico");
       			validato+= "Manca il num. civico ";
       		}
       		if(parameters.get(NCIV) == null ){
       			logger.info("Manca l'estanzione del num. civico");
       			validato+= "Manca l'estanzione del num. civico ";
       		}
       		
       		if(parameters.get(RAG_SOC) == null || parameters.get(RAG_SOC).equals("")){
       			logger.info("Manca la ragione sociale");
       			return "Manca la ragione sociale ";
       		}
       		if(parameters.get(PIVA) == null || parameters.get(PIVA).equals("")){
       			logger.info("Manca la piva");
       			validato+= "Manca la piva ";
       		}
       		//if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
       		//	logger.info("Piva non corretta");
       		//	return Constants.ERRORE_PIVA;
       		//}
       	}
       	if(tipoUtente.equals(TIPO_PRIV_CLI)){
       		logger.info("[Registrazione-Utente ] - Analyze parameters for user type cliente privato");
       		if(parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).equals("")){
       			logger.info("Manca il codice cliente");
       			validato+= "Manca il codice cliente ";
       		}
       		if(parameters.get(COD_FISC) == null || parameters.get(COD_FISC).equals("")){
       			logger.info("Manca il codice fiscale");
       			validato+= "Manca il codice fiscale ";
       		}
       	}
       	if(tipoUtente.equals(TIPO_AZ_CLI)){
       		logger.info("[Registrazione-Utente ] - Analyze parameters for user type azienda cliente ");
       		if(parameters.get(NOME) == null || parameters.get(NOME).equals("")){
       			logger.info("Manca il nome");
       			validato+= "Manca il nome ";
       		}
       		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")){
       			logger.info("Manca il cognome");
       			validato+= "Manca il cognome";
       		}
       		if(parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).equals("")){
       			logger.info("Manca il codice cliente");
       			validato+= "Manca il codice cliente ";
       		}
       		if(parameters.get(PIVA) == null || parameters.get(PIVA).equals("")){
       			logger.info("Manca la piva");
       			validato+= "Manca la piva ";
       		}
//       		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//       			logger.info("Piva non corretta");
//       			return Constants.ERRORE_PIVA;
//       		}
       	}
       	if(tipoUtente.equals(TIPO_AMM_COND)){
       		logger.info("[Registrazione-Utente ] - Analyze parameters for user type amministratore condominio");
       		if(parameters.get(NOME) == null || parameters.get(NOME).equals("")){
       			logger.info("Manca il nome");
       			validato+= "Manca il nome ";
       		}
       		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")){
       			logger.info("Manca il cognome");
       			validato+= "Manca il cognome ";
       		}
       		if(parameters.get(PAESE) == null || parameters.get(PAESE).equals("")){
       			logger.info("Manca il paese");
       			validato+= "Manca il paese ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).equals(""))){
       			logger.info("Manca la provincia");
       			validato+= "Manca la provincia ";
       		}
       		if(!parameters.get(PAESE).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).equals(""))){
       			logger.info("Manca la provincia estera");
       			validato+= "Manca la provincia estera";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).equals(""))){
       			logger.info("Manca il comune");
       			validato+= "Manca il comune ";
       		}
       		if(parameters.get(PAESE).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).equals(""))){
       			logger.info("Manca il CAP");
       			validato+= "Manca il CAP ";
       		}
       		if(!parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).equals(""))){
       			logger.info("Manca il comune estero");
       			validato+= "Manca il comune estero";
       		}
       		if(parameters.get(VIA) == null || parameters.get(VIA).equals("")){
       			logger.info("Manca la via");
       			validato+= "Manca la via ";
       		}
       		if(parameters.get(NCIV) == null || parameters.get(NCIV).equals("")){
       			logger.info("Manca il num. civico");
       			validato+= "Manca il num. civico ";
       		}
       		if(parameters.get(PIVA) == null || parameters.get(PIVA).equals("")){
       			logger.info("Manca la piva");
       			validato+= "Manca la piva ";
       		}
//       		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//       			logger.info("Piva non corretta");
//       			return Constants.ERRORE_PIVA;
//       		}
       		if(parameters.get(COD_FISC) == null || parameters.get(COD_FISC).equals("")){
       			logger.info("Manca codice fiscale");
       			validato+= "Manca codice fiscale ";
       		}
       	}
       	
       	// common check
       	logger.info("[Registrazione-Utente ] - Analyze common parameters");
       	if(parameters.get(MAIL) == null || parameters.get(MAIL).equals("")){
       		logger.info("Manca la mail");
   			return "Manca la mail ";
       	}
       	if(!mailIsOk(parameters.get(MAIL))){
       		return "Mail in formato non corretto";
       	}
   		if(parameters.get(USER) == null || parameters.get(USER).equals("")){
   			logger.info("Manca il nome utente");
   			return "Manca il nome utente ";
   		}
   		if(!usernameIsOk(parameters.get(USER))){
   			return "Nome utente gia' presente ";
   		}
   		if(parameters.get(PWD) == null || parameters.get(PWD).equals(""))
   			return "Password non inserita ";
   		if(parameters.get(PWD2) == null || !parameters.get(PWD2).equals(parameters.get(PWD)))
   			return "Password non corrispondenti ";
   		if(parameters.get(CONDATI) == null || !parameters.get(CONDATI).equals("si"))
   			return "Inserire consenso dati con valore si obbligatorio ";
       	
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

    //	@Override
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
		// TODO Auto-generated method stub
		// variables
    	
    	HashMap<String, HashMap<String, String>> userFromJson = new HashMap<String, HashMap<String, String>>();
    	HashMap<String, String> val = new HashMap<String, String>();
    	HashMap<String, String> val2 = new HashMap<String, String>();
    	Message messaggio = null;
    	JSONObject risposta = new JSONObject();
    	
		//ricostruisco il json con gli user passati in parametro 
		// chiamata json restutisce json in errore
    	for (String key : parameters.keySet()) {		
    	    // gets the value
    	    List<String> value = parameters.get(key);    
    	    //logger.info("key"+key.toString());
    	    String[] pieces = key.toString().split("\\[");
    	    	String id = pieces[0];
    	    	String []keyArray = pieces[2].split("\\]");
    	    	String keyUserData = keyArray[0];
    	    	val.put(keyUserData, value.toString());
    	    	userFromJson.put(id, val);
    	    	val = new HashMap<String, String>();
    	}
    		
    	for (String key : parameters.keySet()) {
    	    List<String> value = parameters.get(key);
    	    String[] pieces = key.toString().split("\\[");
    	    	String id = pieces[0];
    	    	String []keyArray = pieces[2].split("\\]");
    	    	String keyUserData = keyArray[0]; 	    
    	    if(userFromJson.containsKey(id)){    	
	    	    val2 = userFromJson.get(id);
	    	    val2.put(keyUserData, value.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
	    	    userFromJson.put(id,val2)  ;
    	    }

    	}
    	//Map user ok
    	
    	

    	//check SAP	
    	for(String k : userFromJson.keySet()){
    		HashMap <String,String> value = userFromJson.get(k);
    		// variables
        	String nome = "";
        	String cognome = "";
        	String ragione_sociale = "";
        	String bp = "";
    		// validazione json
    		String tipoUtente = value.get(TIPO);
    		
    		//validazione controlli sap
    		boolean validazioneSap = true;

        	String result = paramsAreOk(value, tipoUtente);
        	
        	if(result.equals(Constants.SUCCESS)){
        	logger.info("risultato validazione --- "+result);

        	
        	// PIVA - validation
        	String piva = value.get(PIVA);
        	String pivaIT = value.get(PIVAIT);
        	
        	if(piva == null)
        		piva = "";
        	
        	if (pivaIT == null)
        		pivaIT = "false";
        	
        	if(piva.length() == 11 && pivaIT.equals("false")){
        		piva = "IT"+piva;
        	}
        	
    	
    		
    		if(tipoUtente.equals(TIPO_PRIVATO)){
        		logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
        		WSReturn<Message> msg = WSClient.getClient().controllaProspect("P", value.get( MAIL), null);
        		messaggio = msg.getRitorno();
        		if(!isMessageValid(messaggio)){
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
    				logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
    				WSReturnManager.evaluate(msg, req.getSession());
    				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    				validazioneSap = false;
    				risposta.put("validazione SAP elemento"+k, messaggio.getEtMess().toString());
    			}
    			nome = value.get(NOME);
        		cognome = value.get(COGNOME);
    		}
        	
        	if(tipoUtente.equals(TIPO_AZIENDA)){
        		WSReturn<Message> msg = null;
        		if(value.get(AZIENDAENTE).equalsIgnoreCase("azienda")){
        			logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
        			msg = WSClient.getClient().controllaProspect("A", value.get( MAIL), piva);
        		}else{
        			logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    				msg = WSClient.getClient().controllaProspect("E", value.get( MAIL), piva);
        		}
    			messaggio = msg.getRitorno();
        		if(!isMessageValid(messaggio)){
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
    				logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
    				WSReturnManager.evaluate(msg, req.getSession());
    				
    				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    				validazioneSap = false;
    				risposta.put("validazione SAP elemento"+k, messaggio.getEtMess().toString());
    			}
    			nome = value.get(NOME);
        		cognome = value.get(COGNOME);
        	}
        	if(tipoUtente.equals(TIPO_PRIV_CLI)){
        		logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
        		WSReturn<Message> msg = WSClient.getClient().controllaUsername("P", value.get( COD_CLIENTE), value.get( MAIL), value.get( COD_FISC), "");
    			messaggio = msg.getRitorno();
        		logger.info(messaggio.getEtMess().toString());

        		if(!isMessageValid(messaggio)){
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
    				
    				logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
    				WSReturnManager.evaluate(msg, req.getSession());
    				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(value.get("redirectPage").get(0))).getPath());
    				validazioneSap = false;
    				risposta.put("validazione SAP elemento"+k, messaggio.getEtMess().toString());
    			}
    			bp = messaggio.getBp();
    		}
    		if(tipoUtente.equals(TIPO_AZ_CLI)){
    			WSReturn<Message> msg = null;
    			if(value.get(AZIENDAENTE).equalsIgnoreCase("azienda"))
    				msg = WSClient.getClient().controllaUsername("A", value.get( COD_CLIENTE),value.get( MAIL), value.get( COD_FISC), piva);
    			else
    				msg = WSClient.getClient().controllaUsername("E", value.get( COD_CLIENTE), value.get( MAIL), value.get( COD_FISC), piva);
    			messaggio = msg.getRitorno();
        		if(!isMessageValid(messaggio)){
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
    				logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
    				WSReturnManager.evaluate(msg, req.getSession());
    				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    				validazioneSap = false;
    				risposta.put("validazione SAP elemento"+k, messaggio.getEtMess().toString());
    			}
    			nome = value.get(NOME);
        		cognome = value.get(COGNOME);
    		}
    		if(tipoUtente.equals(TIPO_AMM_COND)){
    			WSReturn<Message> msg = WSClient.getClient().controllaUsername("AM", null, value.get( MAIL), value.get( COD_FISC), piva);
    			messaggio = msg.getRitorno();
        		if(!isMessageValid(messaggio)){
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
    				logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +" email="+value.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
    				WSReturnManager.evaluate(msg, req.getSession());
    				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    				validazioneSap = false;
    				risposta.put("validazione SAP elemento"+k, messaggio.getEtMess().toString());
    			}
    			nome = value.get(NOME);
        		cognome = value.get(COGNOME);
    			bp = messaggio.getBp();
    			
    		}
        	
        	logger.info("SAP call done");
        	
	        	if(validazioneSap){
		        	// build list of properties
		        	Properties properties = new Properties();
		        	properties.put("j:firstName", nome);
		        	properties.put("j:lastName", cognome);
		        	properties.put("j:accountLocked", "true");
		        	properties.put("j:validato", "false");
		        	properties.put("j:tipoBP", tipoUtente);
		        	properties.put("j:nuovaMail", value.get(MAIL));
		        	//properties.put("j:codiceVerifica", codice);
		        	properties.put("j:bp", bp);
		        	//properties.put("preferredLanguage", parameters.get("lang").get(0));
		        	if(tipoUtente.equals(TIPO_AZIENDA))
		        		properties.put("j:organization", ragione_sociale);
		        	// check the username (fix 22/10/2013)
		        	if(!userManagerService.isUsernameSyntaxCorrect(value.get(USER))){
		        		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_USERNAME_UPPERCASE, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		        		logger.info(" [Registrazione-Utente ]- userName ="+ value.get(USER) +"   email="+value.get(MAIL) +" - "+ result + " - " +"Errore username con lettere minuscole ");
		        		//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        	}
		        	// create the user
		        	final JahiaUser user = userManagerService.createUser(value.get(USER), value.get(PWD), properties);
		    		// add user to group
		        	addUserToGroup(user, tipoUtente, renderContext.getSite().getID());
		        	
		        	logger.info(" [Registrazione-Utente ]-User preparation done");
		        	
		        	
		        	// tempNode		
		    		JCRNodeWrapper node = session.getNode(user.getLocalPath());
		    		JCRNodeWrapper tempInfoNode = node.addNode(TEMP_INFO_NAME,TEMP_INFO_TYPE);
		    		tempInfoNode.setProperty("j:aziendaEnte", value.get(AZIENDAENTE)==null?"":value.get(AZIENDAENTE));
		    		tempInfoNode.setProperty("j:ragione_sociale", getParameter(parameters, RAG_SOC));
		    		tempInfoNode.setProperty("j:telefono", value.get(TEL)==null?"":value.get(TEL));
		    		tempInfoNode.setProperty("j:cellulare", value.get(CEL)==null?"":value.get(CEL));
		    		tempInfoNode.setProperty("j:paese", value.get(PAESE)==null?"":value.get(PAESE));
		    		tempInfoNode.setProperty("j:provincia", value.get(PROV)==null?"":value.get(PROV));
		    		tempInfoNode.setProperty("j:comune", value.get(COMUNE)==null?"":value.get(COMUNE));
		    		tempInfoNode.setProperty("j:cap", value.get(CAP)==null?"":value.get(CAP));
		    		tempInfoNode.setProperty("j:via", value.get(VIA)==null?"":value.get(VIA));
		    		tempInfoNode.setProperty("j:ncivico", value.get(NCIV)==null?"":value.get(NCIV));
		    		tempInfoNode.setProperty("j:extncivico", value.get(EXTNCIV)==null?"":value.get(EXTNCIV));
		    		tempInfoNode.setProperty("j:codiceCliente", value.get(COD_CLIENTE)==null?"":value.get(COD_CLIENTE));
		    		tempInfoNode.setProperty("j:codice_fiscale", value.get(COD_FISC)==null?"":value.get(COD_FISC));
		    		tempInfoNode.setProperty("j:ruolo", value.get(RUOLO)==null?"":value.get(RUOLO));
		    		tempInfoNode.setProperty("j:partita_iva", piva);
		    		tempInfoNode.setProperty("j:conDati", value.get(CONDATI).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		    		tempInfoNode.setProperty("j:conNewsletter", value.get(CONMAIL).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		    		tempInfoNode.setProperty("j:conTelefono", value.get(CONTEL).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		    		tempInfoNode.setProperty("j:conPosta", value.get(CONPOSTA).equals(ACCETTO)?SAP_TRUE:SAP_FALSE);
		    		tempInfoNode.setProperty("j:prefissoTel", value.get(PREFIXTEL)==null?"+39":value.get(PREFIXTEL).replaceAll(" ", ""));
		    		tempInfoNode.setProperty("j:prefissoCel", value.get(PREFIXCEL)==null?"+39":value.get(PREFIXCEL).replaceAll(" ", ""));
		    		
		    		logger.info(" [Registrazione-Utente ]- tempInfoNode done");
		    		
		    		Map<String,Object> bindings = new HashMap<String,Object>();
		            bindings.put("newUser",user);
		    		
		    		// save the session
		    		session.save();
		    		
		    		logger.info("session saved");
		    		
		
		    		// crea i prospect lato sap
		    		logger.info("[Confirm-Registration]- Start operation");	
		        	// get the user
		        	JahiaUser userJ = userManagerService.lookupUser(value.get(USERNAME));
		        	if(user == null){
		        		return ActionResult.INTERNAL_ERROR_JSON;
		        	}
		        	
		        	// mail validation
		        	// get new value
		        	String nuovaMail = userJ.getProperty(NUOVAMAIL);
//		        	String codiceVerifica = userJ.getProperty(CODICEVERIFICA);
//		        	// verify new mail
//		        	logger.info("[Confirm-Registration]- nuovaMail: "+nuovaMail+" codiceVerifica: "+codiceVerifica+" mail: "+parameters.get(MAIL).get(0)+" codice: "+parameters.get(CODE).get(0));
//		        	if(nuovaMail.equals(parameters.get(MAIL).get(0)) && codiceVerifica.equals(parameters.get(CODE).get(0))){
//		        		logger.info("[Confirm-Registration]-new mail");
//		        		userJ.setProperty("j:email", nuovaMail);
//		        		userJ.setProperty(NUOVAMAIL, "");
//		        		userJ.setProperty(CODICEVERIFICA, "");
//		        		logger.info("[Confirm-Registration]-mail validated");
//		        		if(user.getProperty("j:validato").equals("true")){
//		        			// validation mail for update mail
//		        		 WSReturnManager.evaluate(WSClient.getClient().addEmail(user.getProperty("j:bp"), nuovaMail), req.getSession());
//		        			logger.info("[Confirm-Registration]- mail saved in SAP");  			
//		        			return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
//		        		}
//		        	}else{
//		        		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_PARAMETRI_NON_CORRETTI, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
//		        		logger.info("[Confirm-Registration]- Error path: "+session.getNodeByIdentifier(parameters.get("redirectPage").get(0)).getPath());
//		        		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
//		        	}
		        	if(userJ.getProperty("j:validato").equals("false")){
		        		// validation mail for the registration
		        		// get tempInfo
		        		JCRNodeWrapper tempInfo = null;
		            	try{
		            		tempInfo = (session.getNode(userJ.getLocalPath())).getNode(TEMPINFO);
		            	}catch(PathNotFoundException e){
		            		if(userJ.getProperty("j:validato").equals("false"))
		            			return ActionResult.INTERNAL_ERROR_JSON;
		            	}
		        		// SAP
		        		Message msg = null;
		        		//get tipoUtente
		        		String tipoUtenteJ = userJ.getProperty("j:tipoBP");
		        		
		        		// switch
		        		if(tipoUtenteJ.equals(Constants.TIPO_PRIVATO)){
		        			DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
		        					null, 
		        					"P", 
		        					null, 
		        					null, 
		        					null, 
		        					null, 
		        					userJ.getProperty("j:firstName"), 
		        					userJ.getProperty("j:lastName"), 
		        					tempInfo.getPropertyAsString("j:telefono"), 
		        					tempInfo.getPropertyAsString("j:cellulare"), 
		        					tempInfo.getPropertyAsString("j:paese"), 
		        					tempInfo.getPropertyAsString("j:provincia"), 
		        					tempInfo.getPropertyAsString("j:comune"), 
		        					null, 
		        					tempInfo.getPropertyAsString("j:via"), 
		        					tempInfo.getPropertyAsString("j:ncivico"), 
		        					tempInfo.getPropertyAsString("j:extncivico"), 
		        					tempInfo.getPropertyAsString("j:cap"), 
		        					tempInfo.getPropertyAsString("j:codice_fiscale"), 
		        					null, 
		        					tempInfo.getPropertyAsString("j:conDati"), 
		        					tempInfo.getPropertyAsString("j:conNewsletter"), 
		        					tempInfo.getPropertyAsString("j:conTelefono"), 
		        					tempInfo.getPropertyAsString("j:conPosta"), 
		        					null,
		        					tempInfo.getPropertyAsString("j:prefissoTel"), 
		        					tempInfo.getPropertyAsString("j:prefissoCel"));
		        			WSReturn<Message> message = WSClient.getClient().creaProspect(userJ.getUsername(), nuovaMail, datiAnagrafici, null);
		        			msg = message.getRitorno();
		        			if(!isMessageValid(msg)){
		        				WSReturnManager.evaluate(message, req.getSession());
		        				logger.info("TipoUtente: "+tipoUtente);
		        				logger.info("[Confirm-Registration]- Username: "+userJ.getUsername() + " TipoUtente: "+tipoUtente +" Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
		        				//errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
		        				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        			}
		        			// save the BP information
		            		userJ.setProperty("j:bp",msg.getBp());
		        		}
		        		if(tipoUtenteJ.equals(Constants.TIPO_AZIENDA)){

		        			DatiAnagrafici datiAnagraficiAzienda = null;
		        			if(tempInfo.getPropertyAsString("j:aziendaEnte").equals("azienda")){
		        				datiAnagraficiAzienda = new DatiAnagrafici(
		        						null, 
		        						"A", 
		        						tempInfo.getPropertyAsString("j:ragione_sociale"), 
		        						null, 
		        						null, 
		        						null, 
		        						null, 
		        						null, 
		        						tempInfo.getPropertyAsString("j:telefono"), 
		        						tempInfo.getPropertyAsString("j:cellulare"), 
		        						tempInfo.getPropertyAsString("j:paese"), 
		        						tempInfo.getPropertyAsString("j:provincia"), 
		        						tempInfo.getPropertyAsString("j:comune"), 
		        						null, 
		        						tempInfo.getPropertyAsString("j:via"), 
		        						tempInfo.getPropertyAsString("j:ncivico"), 
		        						tempInfo.getPropertyAsString("j:extncivico"),
		        						tempInfo.getPropertyAsString("j:cap"), 
		        						tempInfo.getPropertyAsString("j:codice_fiscale"), 
		        						tempInfo.getPropertyAsString("j:partita_iva"), 
		        						tempInfo.getPropertyAsString("j:conDati"), 
		            					tempInfo.getPropertyAsString("j:conNewsletter"), 
		            					tempInfo.getPropertyAsString("j:conTelefono"), 
		            					tempInfo.getPropertyAsString("j:conPosta"), 
		        						null,
		            					tempInfo.getPropertyAsString("j:prefissoTel"), 
		            					tempInfo.getPropertyAsString("j:prefissoCel"));
		        			}else{

		        				datiAnagraficiAzienda = new DatiAnagrafici(
		        						null, 
		        						"E", 
		        						tempInfo.getPropertyAsString("j:ragione_sociale"), 
		        						null, 
		        						null, 
		        						null, 
		        						null, 
		        						null, 
		        						tempInfo.getPropertyAsString("j:telefono"), 
		        						tempInfo.getPropertyAsString("j:cellulare"),  
		        						tempInfo.getPropertyAsString("j:paese"), 
		        						tempInfo.getPropertyAsString("j:provincia"), 
		        						tempInfo.getPropertyAsString("j:comune"), 
		        						null, 
		        						tempInfo.getPropertyAsString("j:via"), 
		        						tempInfo.getPropertyAsString("j:ncivico"), 
		        						tempInfo.getPropertyAsString("j:extncivico"),
		        						tempInfo.getPropertyAsString("j:cap"), 
		        						tempInfo.getPropertyAsString("j:codice_fiscale"), 
		        						tempInfo.getPropertyAsString("j:partita_iva"), 
		        						tempInfo.getPropertyAsString("j:conDati"), 
		            					tempInfo.getPropertyAsString("j:conNewsletter"), 
		            					tempInfo.getPropertyAsString("j:conTelefono"), 
		            					tempInfo.getPropertyAsString("j:conPosta"),
		        						null,
		            					tempInfo.getPropertyAsString("j:prefissoTel"), 
		            					tempInfo.getPropertyAsString("j:prefissoCel"));
		        			}
		        	
		        			DatiAnagrafici datiAnagraficiInterlocutore = new DatiAnagrafici(    					
		    						null, 
		    						"I", 
		    						null, 
		    						null, 
		    						null, 
		    						null, 
		    						userJ.getProperty("j:firstName"), 
		    						userJ.getProperty("j:lastName"), 
		    						tempInfo.getPropertyAsString("j:telefono"), 
		    						tempInfo.getPropertyAsString("j:cellulare"), 
		    						tempInfo.getPropertyAsString("j:paese"), 
		    						null, 
		    						null, 
		    						null, 
		    						null, 
		    						null, 
		    						null, 
		    						null, 
		    						null, 
		    						null,
		    						tempInfo.getPropertyAsString("j:conDati"), 
		    						tempInfo.getPropertyAsString("j:conNewsletter"), 
		    						tempInfo.getPropertyAsString("j:conTelefono"), 
		    						tempInfo.getPropertyAsString("j:conPosta"), 
		    						tempInfo.getPropertyAsString("j:ruolo"),
		        					tempInfo.getPropertyAsString("j:prefissoTel"), 
		        					tempInfo.getPropertyAsString("j:prefissoCel"));
		        			logger.info("[Confirm-Registration]-Interlocutore: "+datiAnagraficiInterlocutore==null?"null":datiAnagraficiInterlocutore.getNome());
		        			WSReturn<Message> message = WSClient.getClient().creaProspect(userJ.getUsername(), nuovaMail, datiAnagraficiInterlocutore, datiAnagraficiAzienda);
		        			msg = message.getRitorno();
		        			if(!isMessageValid(msg)){
		        				/*
		        				if(msg.geteReturn().equals("07"))
		        				{
		        					// mail preparation
		        					String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
		        					String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
		        					String from = parameters.get("from")==null?"":parameters.get("from").get(0);
		        					String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0); 
		        					String user_mail = parameters.get("mail")==null?"":parameters.get("mail").get(0);
		        					String backOffice = parameters.get("backOffice")==null?"":parameters.get("backOffice").get(0);
		        					String mailContent = parameters.get("mailContent")==null?"":parameters.get("mailContent").get(0);

		        					mailContent = mailContent.concat("<br />Dati Anagrafici Azienda: <br /><br />").concat(datiAnagraficiAzienda.toString()).concat("<br />Dati Anagrafici Interlocutore: <br /><br />").concat("Business Partner: "+msg.getBp()+"<br />").concat("E-mail: "+user_mail+"<br />").concat(datiAnagraficiInterlocutore.toString());
		        					
		        					mailService.sendHtmlMessage(from, backOffice , cc, bcc, subject, mailContent);

		    	
		        				}    				
		        				*/
		        				logger.info("[Confirm-Registration]-Username: "+userJ.getUsername()+"TipoUtente: "+tipoUtenteJ+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
		        				WSReturnManager.evaluate(message, req.getSession());
		        				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        			}
		        			// save the BP information
		        			userJ.setProperty("j:bp",msg.getBp());
		        		}
		        		if(tipoUtenteJ.equals(Constants.TIPO_PRIV_CLI)){
		        			DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
		        					"", // codiceBP, 
		        					"P",  // tipoBP, 
		        					"",// ragioneSociale1, 
		        					"", // ragioneSociale2, 
		        					"", // ragioneSociale3, 
		        					"", // ragioneSociale4,
		        					"", // nome, 
		        					"", // cognome, 
		        					"",// telefono, 
		        					"", // cellulare,
		        					tempInfo.getPropertyAsString("j:paese"),// paese, 
		        					"",// provincia, 
		        					"",// comune, 
		        					"",// frazione, 
		        					"",// via, 
		        					"",//cap,
		        					"",
		        					tempInfo.getPropertyAsString("j:codice_fiscale"),// codiceFiscale, 
		        					"",// partitaIVA, 
		        					tempInfo.getPropertyAsString("j:conDati"),// privacy, 
		        					tempInfo.getPropertyAsString("j:conNewsletter"),// privacyMail,
		        					tempInfo.getPropertyAsString("j:conTelefono"),// privacyEmail,
		        					tempInfo.getPropertyAsString("j:conPosta"),// privacyTel, 
		        					""); // ruolo;
		        			
		        			WSReturn<Message> message = WSClient.getClient().creaUsername(
		        					tempInfo.getPropertyAsString("j:codiceCliente"),
		        					userJ.getUsername(),
		        					nuovaMail, 
		        					tempInfo.getPropertyAsString("j:codice_fiscale"),
		        					null,
		        					datiAnagrafici,
		        					null,
		        					false);
		        			msg = message.getRitorno();
		        			if(!isMessageValid(msg)){
		        				logger.info("[Confirm-Registration]-Username: "+userJ.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
		        				//errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
		        				WSReturnManager.evaluate(message, req.getSession());
		        				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        			}
		        			WSClient.getClient().addEmail(msg.getBp(), nuovaMail);
		        			userJ.setProperty("j:firstName", msg.getName());
		        			userJ.setProperty("j:lastName", msg.getSurname());
		        			userJ.setProperty("j:bp",msg.getBp());
		        		}
		    			if(tipoUtente.equals(Constants.TIPO_AZ_CLI)){
		    				DatiAnagrafici datiAnagraficiInterlocutore = new DatiAnagrafici(
		    						null,
		    						"I",
		    						null,
		    						null,
		    						null,
		    						null,
		    						userJ.getProperty("j:firstName"),
		    						userJ.getProperty("j:lastName"),
		    						tempInfo.getPropertyAsString("j:telefono"),
		    						tempInfo.getPropertyAsString("j:cellulare"),
		    						tempInfo.getPropertyAsString("j:paese"),
		    						null,
		    						null,
		    						null,
		    						null,
		    						null,
		    						null,
		    						null,
		    						null,
		    						null,
		    						tempInfo.getPropertyAsString("j:conDati"),
		    						tempInfo.getPropertyAsString("j:conNewsletter"),
		    						tempInfo.getPropertyAsString("j:conTelefono"),
		    						tempInfo.getPropertyAsString("j:conPosta"),
		    						tempInfo.getPropertyAsString("j:ruolo"),
		        					tempInfo.getPropertyAsString("j:prefissoTel"), 
		        					tempInfo.getPropertyAsString("j:prefissoCel"));
		        			WSReturn<Message> message = WSClient.getClient().creaUsername(
		        					tempInfo.getPropertyAsString("j:codiceCliente"),
		        					userJ.getUsername(),
		        					nuovaMail,
		        					tempInfo.getPropertyAsString("j:codice_fiscale"),
		        					tempInfo.getPropertyAsString("j:partita_iva"),
		        					datiAnagraficiInterlocutore,
		        					null,
		        					false);
		        			msg = message.getRitorno();
		        			if(!isMessageValid(msg)){
		        				logger.info("TipoUtente: "+tipoUtente);
		        				logger.info("[Confirm-Registration]-Username: "+user.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
		        				if(msg.getBp() != null)
		        					userJ.setProperty("j:bp", msg.getBp());
		        				//errorMessageMail(datiAnagraficiInterlocutore, parameters, msg.getBp(), msg.getFirstMsg());
		        				WSReturnManager.evaluate(message, req.getSession());
		        				//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        			}	
		        			userJ.setProperty("j:bp", msg.getBp());
		    			}
		    			if(tipoUtenteJ.equals(Constants.TIPO_AMM_COND)){
		    				DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
		    						null,
		    						"AM",
		    						userJ.getProperty("j:firstName"),
		    						userJ.getProperty("j:lastName"),
		    						null, 
		    						null,
		    						null,
		    						null,
		    						tempInfo.getPropertyAsString("j:telefono"),
		    						tempInfo.getPropertyAsString("j:cellulare"),
		    						tempInfo.getPropertyAsString("j:paese"),
		    						tempInfo.getPropertyAsString("j:provincia"),
		    						tempInfo.getPropertyAsString("j:comune"),
		    						null,
		    						tempInfo.getPropertyAsString("j:via"),
		    						tempInfo.getPropertyAsString("j:ncivico"),
		    						tempInfo.getPropertyAsString("j:extncivico"),
		    						tempInfo.getPropertyAsString("j:cap"),
		    						tempInfo.getPropertyAsString("j:codice_fiscale"),
		    						tempInfo.getPropertyAsString("j:partita_iva"),
		    						tempInfo.getPropertyAsString("j:conDati"),
		    						tempInfo.getPropertyAsString("j:conNewsletter"),
		    						tempInfo.getPropertyAsString("j:conTelefono"),
		    						tempInfo.getPropertyAsString("j:conPosta"),
		    						null,
		        					tempInfo.getPropertyAsString("j:prefissoTel"), 
		        					tempInfo.getPropertyAsString("j:prefissoCel"));
		    				
		    				
		    				datiAnagrafici.setEstenzioneNumeroCivico(tempInfo.getPropertyAsString("j:extncivico"));
		    				WSReturn<Message> message = WSClient.getClient().creaUsername(
		    						null,
		    						userJ.getUsername(),
		    						nuovaMail, 
		    						tempInfo.getPropertyAsString("j:codice_fiscale"),
		    						tempInfo.getPropertyAsString("j:partita_iva"),
		    						datiAnagrafici,
		    						null,
		    						false);
		    				msg = message.getRitorno();
		    				if(!isMessageValid(msg)){
		    						/*
		    	    				if(msg.geteReturn().equals("07"))
		    	    				{
		    	    					// mail preparation
		    	    					String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
		    	    					String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
		    	    					String from = parameters.get("from")==null?"":parameters.get("from").get(0);
		    	    					String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0); 
		    	    					String user_mail = parameters.get("mail")==null?"":parameters.get("mail").get(0);
		    	    					String backOffice = parameters.get("backOffice")==null?"":parameters.get("backOffice").get(0);
		    	    					String mailContent = parameters.get("mailContent")==null?"":parameters.get("mailContent").get(0);
		    	    					
		    	    					
		    	    					mailContent = mailContent.concat("<br />Dati Anagrafici: <br /><br />").concat("Business Partner: "+msg.getBp()).concat("<br />").concat("E-mail: "+user_mail).concat("<br />"+datiAnagrafici.toString());
		    	    					
		    	    					mailService.sendHtmlMessage(from, backOffice, cc, bcc, subject, mailContent);

		    		
		    	    				}
		    	    				*/
		        				logger.info("TipoUtente: "+tipoUtenteJ);
		        				logger.info("Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
		        				WSReturnManager.evaluate(message, req.getSession());
		        				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        			}
		    				userJ.setProperty("j:bp", msg.getBp());
		    			}
		    			userJ.setProperty("j:validato", "true");
		    			userJ.setProperty("j:accountLocked", "false");
		    			logger.info("j:accountLocked: "+userJ.getProperty("j:accountLocked"));
		    			JCRNodeWrapper userNode = session.getNode(userJ.getLocalPath());
		    			userNode.saveSession();
		    			session.save();
		    			logger.info("[Confirm-Registration]-Username: "+userJ.getUsername()+" new user created");
		        	}else{
		        		logger.info("[Confirm-Registration]-Username: "+userJ.getUsername()+"Messaggio non valido: Errore: UTENTE "+userJ.getUsername()+" NON VALIDATO");
		        		//genericErrorMessageMail(parameters, "Errore:  UTENTE "+user.getUsername()+" NON VALIDATO");
		        		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		        		//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		        	}
		        	
		        	WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_VALIDATION, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		        	logger.info("[Confirm-Registration]-redirectPage:");
		    		
		    		risposta.put("Avviso elemento "+k, "Utente in posizione:"+k+" Salvato Correttamente");
	        	}
        	}else {
        		risposta.put("Errore elemento "+k, "rigaJson: "+k+" "+result);
        	}
    	}
    	
    	
    	logger.info(userFromJson.toString());
    	
    	
    	
    	
      
        	
        return new ActionResult(ActionResult.OK_JSON.getResultCode(), null, risposta);
        
    	
    	
    	
	}
	
	
	private boolean isMessageValid(Message messaggio){
    	if(messaggio == null || messaggio.isAnError()){
    		return false;
    	}
    	return true;
    }
	
	public JahiaUserManagerService getUserManagerService() {
		return userManagerService;
	}


	
	 public void setMailService(MailService mailService) 
		{
			this.mailService = mailService;
		}	

}
