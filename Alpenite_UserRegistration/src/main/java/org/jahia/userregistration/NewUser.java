package org.jahia.userregistration;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Action;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;

import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.settings.SettingsBean;
import org.jahia.userregistration.utils.MD5Util;
import org.jahia.userregistration.utils.UserJahiaDB;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.sapMessages.dati.Message;


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

    private void addUserToGroup(Connection connection, String username, String tipoUtente, int siteID) throws SQLException {

        // aggiungo l'utente al gruppo degli utenti che puo' accedere allo sportello
        String group_name = GROUP_SPORTELLO;
        int memberOk = addMember(connection, username, group_name, siteID);
        if (memberOk == 1){
            logger.info("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): added user '" + username + "' in group '" + group_name + "'");
        }else {
            logger.error("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): Failed to added user '" + username + "' in group '" + group_name + "'");
        }
        // a seconda del tipo utente inserisco l'utente nel rispettivo gruppo
        if(tipoUtente.equals(TIPO_PRIVATO) || tipoUtente.equals(TIPO_AZIENDA))
            group_name = GROUP_PROSPECT;
        if(tipoUtente.equals(TIPO_PRIV_CLI) || tipoUtente.equals(TIPO_AZ_CLI))
            group_name = GROUP_CLIENTE;
        if(tipoUtente.equals(TIPO_AMM_COND))
            group_name = GROUP_AMMCOND;
        if (!group_name.equals(GROUP_SPORTELLO)){
            memberOk = addMember(connection, username, group_name, siteID);
            if (memberOk == 1){
                logger.info("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): added user '" + username + "' in group '" + group_name + "'");
            }else {
                logger.error("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): Failed to added user '" + username + "' in group '" + group_name + "'");
            }
        }

        if(tipoUtente.equals(TIPO_AZ_CLI) || tipoUtente.equals(TIPO_AZIENDA)){
            group_name =  GROUP_INT;
            memberOk = addMember(connection, username, group_name, siteID);
            if (memberOk == 1){
                logger.info("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): added user '" + username + "' in group '" + group_name + "'");
            }else {
                logger.error("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): Failed to added user '" + username + "' in group '" + group_name + "'");
            }
        }
    }

    private int addMember(Connection connection, String username, String groupName, int siteId) throws SQLException {
        int addMember = -1;
        PreparedStatement prepared = connection.prepareStatement("" +
                "insert into user_group " +
                "(group_name, username, site_id) " +
                "values (?,?,?)"
        );
        prepared.setString(1, groupName);
        prepared.setString(2, username);
        prepared.setInt(3, siteId);

        logger.info("ALPENITE NewUser - ALPENITE - addMember - INSERT: " + prepared.toString());

        addMember = prepared.executeUpdate();

        //close statement
        logger.info("ALPENITE NewUser - addMember - Chiudo PreparedStatement");
        prepared.close();

        return addMember;
    }

    private String paramsAreOk(Map<String, List<String>> parameters, String tipoUtente){
     logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Starting check registration parameters");
    	if(tipoUtente.equals(TIPO_PRIVATO)){
    	     logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Analyze parameters for user type TipoPrivato");
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE) == null || parameters.get(PAESE).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il paese");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca la provincia");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca la provincia estera");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il comune");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il CAP");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il comune estero");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(VIA) == null || parameters.get(VIA).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la via");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null || parameters.get(NCIV).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    	}
    	if(tipoUtente.equals(TIPO_AZIENDA)){
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Analyze parameters for user type TipoAzienda");
    		if(parameters.get(AZIENDAENTE) == null || parameters.get(AZIENDAENTE).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca flag azienda/ente");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE) == null || parameters.get(PAESE).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il paese");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca la provincia");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il CAP");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca la provincia estera");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il comune");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il comune estero");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(VIA) == null || parameters.get(VIA).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la via");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null || parameters.get(NCIV).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null ){
    			logger.info("ALPENITE NewUser - Manca l'estanzione del num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}

    		if(parameters.get(RAG_SOC) == null || parameters.get(RAG_SOC).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la ragione sociale");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PIVA) == null || parameters.get(PIVA).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la piva");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		//if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
    		//	logger.info("Piva non corretta");
    		//	return Constants.ERRORE_PIVA;
    		//}
    	}
    	if(tipoUtente.equals(TIPO_PRIV_CLI)){
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Analyze parameters for user type cliente privato");
    		if(parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il codice cliente");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COD_FISC) == null || parameters.get(COD_FISC).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il codice fiscale");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    	}
    	if(tipoUtente.equals(TIPO_AZ_CLI)){
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Analyze parameters for user type azienda cliente ");
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il codice cliente");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PIVA) == null || parameters.get(PIVA).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la piva");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
//    		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//    			logger.info("Piva non corretta");
//    			return Constants.ERRORE_PIVA;
//    		}
    	}
    	if(tipoUtente.equals(TIPO_AMM_COND)){
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Analyze parameters for user type amministratore condominio");
    		if(parameters.get(NOME) == null || parameters.get(NOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il nome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(COGNOME) == null || parameters.get(COGNOME).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il cognome");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE) == null || parameters.get(PAESE).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il paese");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).get(0).equals(""))){
    			logger.info("Manca la provincia");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca la provincia estera");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il comune");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PAESE).get(0).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il CAP");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(!parameters.get(PAESE).get(0).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).get(0).equals(""))){
    			logger.info("ALPENITE NewUser - Manca il comune estero");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(VIA) == null || parameters.get(VIA).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la via");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(NCIV) == null || parameters.get(NCIV).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca il num. civico");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    		if(parameters.get(PIVA) == null || parameters.get(PIVA).get(0).equals("")){
    			logger.info("ALPENITE NewUser - Manca la piva");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
//    		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//    			logger.info("Piva non corretta");
//    			return Constants.ERRORE_PIVA;
//    		}
    		if(parameters.get(COD_FISC) == null || parameters.get(COD_FISC).get(0).equals("")){
				logger.info("ALPENITE NewUser - Manca codice fiscale");
    			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    		}
    	}

    	// common check
    	logger.info("ALPENITE NewUser - [Registrazione-Utente ] - Analyze common parameters");
    	if(parameters.get(MAIL) == null || parameters.get(MAIL).get(0).equals("")){
    		logger.info("ALPENITE NewUser - Manca la mail");
			return Constants.ERRORE_CAMPI_OBBLIGATORI;
    	}
    	if(!mailIsOk(parameters.get(MAIL).get(0))){
    		return Constants.ERRORE_MAIL;
    	}
		if(parameters.get(USER) == null || parameters.get(USER).get(0).equals("")){
			logger.info("ALPENITE NewUser - Manca il nome utente");
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

	private String paramsAreOkInDb(Map<String, List<String>> parameters, String tipoUtente, Connection connection) throws SQLException {
		if(!usernameIsOkInDb(parameters.get(USER).get(0), connection)){
			return Constants.ERRORE_USERNAME;
		}

		return Constants.SUCCESS;
	}

    private boolean usernameIsOk(String username) {
    	return !userManagerService.userExists(username);
	}

	private boolean usernameIsOkInDb(String username, Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("select * from user_registration where username='" + username + "'");
        return !rs.next();
	}

	private boolean mailIsOk(String mail) {
		if(!mail.contains("@"))
			return false;
		if(!mail.contains("."))
			return false;
		return true;
	}

	@Override
    public ActionResult doExecute(HttpServletRequest req, final RenderContext renderContext, Resource resource,
								  JCRSessionWrapper session, final Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {


    	// variables
    	String nome = "";
    	String cognome = "";
    	String ragione_sociale = "";
    	String bp = "";
    	Message messaggio = null;

    	// get tipoUtente
    	if(parameters.get(TIPO) == null)
    		return ActionResult.BAD_REQUEST;
    	final String tipoUtente = parameters.get(TIPO).get(0);

    	// check the paramters
    	String result = paramsAreOk(parameters, tipoUtente);
    	if(result != Constants.SUCCESS){
    		WSReturnManager.evaluate(new WSReturn<String>(result, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +"   email="+parameters.get(MAIL) +" - "+ result);
    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    	}

    	logger.info("ALPENITE NewUser - Parameters are ok");

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
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    		WSReturn<Message> msg = WSClient.getClient().controllaProspect("P", getParameter(parameters, MAIL), null);
    		messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("ALPENITE NewUser - TipoUtente: "+tipoUtente);
				logger.info("ALPENITE NewUser - Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
		}
    	if(tipoUtente.equals(TIPO_AZIENDA)){
    		WSReturn<Message> msg = null;
    		if(parameters.get(AZIENDAENTE).get(0).equalsIgnoreCase("azienda")){
    			logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    			msg = WSClient.getClient().controllaProspect("A", getParameter(parameters, MAIL), piva);
    		}else{
    			logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
				msg = WSClient.getClient().controllaProspect("E", getParameter(parameters, MAIL), piva);
    		}
			messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("ALPENITE NewUser - TipoUtente: "+tipoUtente);
				logger.info("ALPENITE NewUser - Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
    	}
    	if(tipoUtente.equals(TIPO_PRIV_CLI)){
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente);
    		WSReturn<Message> msg = WSClient.getClient().controllaUsername("P", getParameter(parameters, COD_CLIENTE), getParameter(parameters, MAIL), getParameter(parameters, COD_FISC), "");
			messaggio = msg.getRitorno();
    		if(!isMessageValid(messaggio)){
				logger.info("ALPENITE NewUser - TipoUtente: "+tipoUtente);
				logger.info("ALPENITE NewUser - Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
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
				logger.info("ALPENITE NewUser - TipoUtente: "+tipoUtente);
				logger.info("ALPENITE NewUser - Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
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
				logger.info("ALPENITE NewUser - TipoUtente: "+tipoUtente);
				logger.info("ALPENITE NewUser - Messaggio non valido: "+messaggio.geteReturn()+" - "+messaggio.getFirstMsg());
				logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +" email="+parameters.get(MAIL) +" - "+"TipoUtente: "+tipoUtente+" - "+ messaggio.getFirstMsg());
				WSReturnManager.evaluate(msg, req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			nome = parameters.get(NOME).get(0);
    		cognome = parameters.get(COGNOME).get(0);
			bp = messaggio.getBp();
		}

    	logger.info("ALPENITE NewUser - SAP call done");

    	// all verification done
    	String codice = UUID.randomUUID().toString();
    	// build list of properties
    	final Properties properties = new Properties();
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
    		logger.info("ALPENITE NewUser - [Registrazione-Utente ]- userName ="+ parameters.get(USER) +"   email="+parameters.get(MAIL) +" - "+ result + " - " +"Errore username con lettere minuscole ");
    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    	}

		logger.info("ALPENITE NewUser - ALPENITE PROVA CONNESSIONE DB");


		try {

			final int siteID = renderContext.getSite().getID();
			JCRNodeWrapper page = session.getNodeByIdentifier(parameters.get("verificationPage").get(0));
			final String link = req.getScheme()+ "://" + req.getServerName() +":"+req.getServerPort() + page.getUrl() + "?username="+parameters.get(USER).get(0)+"&codice="+codice+"&mail="+parameters.get(MAIL).get(0);

			String mailProcess = "";

			try{
				mailProcess = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_"+parameters.get("lang").get(0)).getPropertyAsString("mail");
			}catch(PathNotFoundException e){
				mailProcess = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_en").getPropertyAsString("mail");
			}

			final String mail = mailProcess;

			//creo JahiaUser con thread separato
			Thread thread = new Thread() {
				public void run() {
					try {
						// create the user
						final JahiaUser user = userManagerService.createUser(parameters.get(USER).get(0), parameters.get(PWD).get(0), properties);
						// add user to group
						addUserToGroup(user, tipoUtente, siteID);

						//mail preparation
						String from = parameters.get("from")==null?SettingsBean.getInstance().getMail_from():getParameter(parameters, "from");
						String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
						String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
						String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
						logger.info("ALPENITE NewUser - Mail preparation done");
						logger.info("ALPENITE NewUser - [Registrazione-Utente ]- Mail preparation done");
						logger.info("ALPENITE NewUser - Ragione sociale" + getParameter(parameters, RAG_SOC));
						// send the mail
						// build the link
						logger.info("ALPENITE NewUser - [Registrazione-Utente ]- Info mail: "+parameters.get("lang").get(0));
						if (StringUtils.isNotEmpty(mail)) {
							String mail_tea = mail.replaceAll(SUBSCRIBE_TAG, link);
							mailService.sendHtmlMessage(from, parameters.get(MAIL).get(0), cc, bcc, subject, mail_tea);

							logger.info("ALPENITE NewUser - [Registrazione-Utente ]- mail sent");
						}else {
							logger.error("ALPENITE NewUser - [Registrazione-Utente ]- mail is Empty");
						}

						logger.info("ALPENITE NewUser THREAD - [Registrazione-Utente]- User preparation done");
					} catch (Exception ex) {
						logger.error("ALPENITE NewUser THREAD - ERROR: " + ex.getMessage());
						Thread.currentThread().interrupt();
					}
				}
			};
			thread.start();


			String securePasswordMD5 = MD5Util.getMd5(req.getParameter(PWD));
			logger.info("ALPENITE securePasswordMD5: " + securePasswordMD5);
			String securePassword2MD5 = MD5Util.getMd5(req.getParameter(PWD2));
			logger.info("ALPENITE securePassword2MD5: " + securePassword2MD5);

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String registrationDate = formatter.format(new Date());

			UserJahiaDB userJahiaDB = new UserJahiaDB();

			Connection connection = userJahiaDB.connectedDB();

			logger.info("ALPENITE CHECK IF USERNAME EXIST IN TABLE");
			String resultDb = paramsAreOkInDb(parameters, tipoUtente, connection);
			if (!resultDb.equals(Constants.SUCCESS)) {
				WSReturnManager.evaluate(new WSReturn<String>(resultDb, this.getClass().getName(), "return", new ArrayList<String>()), req.getSession());
				logger.info(" [Registrazione-Utente ]- userName =" + parameters.get(USER) + "   email=" + parameters.get(MAIL) + " - " + resultDb);
				userJahiaDB.closeConnection(connection);
				return new ActionResult(HttpServletResponse.SC_ACCEPTED, (session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}

			PreparedStatement prepared = connection.prepareStatement("" +
					"insert into user_registration " +
					"(username, password, password2, tipoUtente, nome, cognome, email, prefissoTel, telefono," +
					" prefissoCel, cellulare, codice_fiscale, ruolo, lang, paese, provincia, provincia_estera," +
					" comune, comune_estero, via, ncivico, cap, extncivico, subject, ragione_sociale, codice_cliente," +
					" partita_iva, aziendaEnte, newsletterRadio, telefonoRadio, postaRadio, conDatiRadio, from_email," +
					" jcrResourceID, jcrNodeType, jcrRedirectTo, finalPage, jcrCaptcha, jcrNewNodeOutputFormat," +
					" currentNode, verificationPage, redirectPage, form_token, registration_date, j_accountLocked, " +
					" j_validato, j_tipoBP, j_nuovaMail, j_codiceVerifica, j_bp, j_organization) " +
					"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
			);
			prepared.setString(1, req.getParameter(USER));
			prepared.setString(2, securePasswordMD5);
			prepared.setString(3, securePassword2MD5);
			prepared.setString(4, StringUtils.isNotEmpty(req.getParameter(TIPO)) ? req.getParameter(TIPO) : "");
			prepared.setString(5, StringUtils.isNotEmpty(req.getParameter(NOME)) ? req.getParameter(NOME) : "");
			prepared.setString(6, StringUtils.isNotEmpty(req.getParameter(COGNOME)) ? req.getParameter(COGNOME) : "");
			prepared.setString(7, StringUtils.isNotEmpty(req.getParameter(MAIL)) ? req.getParameter(MAIL) : "");
			prepared.setString(8, parameters.get(PREFIXTEL)==null?"+39":parameters.get(PREFIXTEL).get(0).replaceAll(" ", ""));
			prepared.setString(9, StringUtils.isNotEmpty(req.getParameter(TEL)) ? req.getParameter(TEL) : "");
			prepared.setString(10, parameters.get(PREFIXCEL)==null?"+39":parameters.get(PREFIXCEL).get(0).replaceAll(" ", ""));
			prepared.setString(11, StringUtils.isNotEmpty(req.getParameter(CEL)) ? req.getParameter(CEL) : "");
			prepared.setString(12, StringUtils.isNotEmpty(req.getParameter(COD_FISC)) ? req.getParameter(COD_FISC) : "");
			prepared.setString(13, StringUtils.isNotEmpty(req.getParameter(RUOLO)) ? req.getParameter(RUOLO) : "");
			prepared.setString(14, StringUtils.isNotEmpty(req.getParameter("lang")) ? req.getParameter(RUOLO) : "lang");
			prepared.setString(15, StringUtils.isNotEmpty(req.getParameter(PAESE)) ? req.getParameter(PAESE) : "");
			prepared.setString(16, StringUtils.isNotEmpty(req.getParameter(PROV)) ? req.getParameter(PROV) : "");
			prepared.setString(17, StringUtils.isNotEmpty(req.getParameter(PROV_EST)) ? req.getParameter(PROV_EST) : "");
			prepared.setString(18, StringUtils.isNotEmpty(req.getParameter(COMUNE)) ? req.getParameter(COMUNE) : "");
			prepared.setString(19, StringUtils.isNotEmpty(req.getParameter(COMUNE_EST)) ? req.getParameter(COMUNE_EST) : "");
			prepared.setString(20, StringUtils.isNotEmpty(req.getParameter(VIA)) ? req.getParameter(VIA) : "");
			prepared.setString(21, StringUtils.isNotEmpty(req.getParameter(NCIV)) ? req.getParameter(NCIV) : "");
			prepared.setString(22, StringUtils.isNotEmpty(req.getParameter(CAP)) ? req.getParameter(CAP) : "");
			prepared.setString(23, StringUtils.isNotEmpty(req.getParameter(EXTNCIV)) ? req.getParameter(EXTNCIV) : "");
			prepared.setString(24, StringUtils.isNotEmpty(req.getParameter("subject")) ? req.getParameter("subject") : "");
			prepared.setString(25, StringUtils.isNotEmpty(req.getParameter(RAG_SOC)) ? req.getParameter(RAG_SOC) : "");
			prepared.setString(26, StringUtils.isNotEmpty(req.getParameter(COD_CLIENTE)) ? req.getParameter(COD_CLIENTE) : "");
			prepared.setString(27, StringUtils.isNotEmpty(req.getParameter(PIVA)) ? req.getParameter(PIVA) : "");
			prepared.setString(28, StringUtils.isNotEmpty(req.getParameter(AZIENDAENTE)) ? req.getParameter(AZIENDAENTE) : "");
			prepared.setString(29, StringUtils.isNotEmpty(req.getParameter(CONMAIL)) ? req.getParameter(CONMAIL).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
			prepared.setString(30, StringUtils.isNotEmpty(req.getParameter(CONTEL)) ? req.getParameter(CONTEL).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
			prepared.setString(31, StringUtils.isNotEmpty(req.getParameter(CONPOSTA)) ? req.getParameter(CONPOSTA).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
			prepared.setString(32, StringUtils.isNotEmpty(req.getParameter(CONDATI)) ? req.getParameter(CONDATI).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
			prepared.setString(33, StringUtils.isNotEmpty(req.getParameter("from")) ? req.getParameter("from") : "");
			prepared.setString(34, StringUtils.isNotEmpty(req.getParameter("jcrResourceID")) ? req.getParameter("jcrResourceID") : "");
			prepared.setString(35, StringUtils.isNotEmpty(req.getParameter("jcrNodeType")) ? req.getParameter("jcrNodeType") : "");
			prepared.setString(36, StringUtils.isNotEmpty(req.getParameter("jcrRedirectTo")) ? req.getParameter("jcrRedirectTo") : "");
			prepared.setString(37, StringUtils.isNotEmpty(req.getParameter("finalPage")) ? req.getParameter("finalPage") : "");
			prepared.setString(38, StringUtils.isNotEmpty(req.getParameter("jcrCaptcha")) ? req.getParameter("jcrCaptcha") : "");
			prepared.setString(39, StringUtils.isNotEmpty(req.getParameter("jcrNewNodeOutputFormat")) ? req.getParameter("jcrNewNodeOutputFormat") : "");
			prepared.setString(40, StringUtils.isNotEmpty(req.getParameter("currentNode")) ? req.getParameter("currentNode") : "");
			prepared.setString(41, StringUtils.isNotEmpty(req.getParameter("verificationPage")) ? req.getParameter("verificationPage") : "");
			prepared.setString(42, StringUtils.isNotEmpty(req.getParameter("redirectPage")) ? req.getParameter("redirectPage") : "");
			prepared.setString(43, StringUtils.isNotEmpty(req.getParameter("form-token")) ? req.getParameter("form-token") : "");
			prepared.setString(44, StringUtils.isNotEmpty(registrationDate) ? registrationDate : "");
			prepared.setString(45, "true");
			prepared.setString(46, "false");
			prepared.setString(47, tipoUtente);
			prepared.setString(48, StringUtils.isNotEmpty(parameters.get(MAIL).get(0)) ? parameters.get(MAIL).get(0) : "");
			prepared.setString(49, codice);
			prepared.setString(50, bp);
			prepared.setString(51, tipoUtente.equals(TIPO_AZIENDA) ? ragione_sociale : "");

			logger.info("ALPENITE NewUser - ALPENITE INSERT: " + prepared.toString());

			prepared.executeUpdate();

			//close statement
            logger.info("ALPENITE NewUser - Chiudo PreparedStatement");
			prepared.close();

			//aggiungo nuovo user_registration a user_group
            addUserToGroup(connection, req.getParameter(USER), tipoUtente, siteID);

			//chiudo connessione al db
			userJahiaDB.closeConnection(connection);

		} catch (SQLException e) {
			logger.error("ALPENITE ERROR SQLException: " + e.getMessage());
		} catch (Exception e) {
			logger.error("ALPENITE ERROR Exception: " + e.getMessage());
		}

    	logger.info("ALPENITE NewUser - [Registrazione-Utente ]- User preparation done");

		// message preparation
		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_REGISTRATION, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		// return the page
    	//return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
		logger.info("ALPENITE NewUser - [Registrazione-Utente ]- forward page: "+ (session.getNodeByIdentifier(parameters.get("finalPage").get(0))).getPath());
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
