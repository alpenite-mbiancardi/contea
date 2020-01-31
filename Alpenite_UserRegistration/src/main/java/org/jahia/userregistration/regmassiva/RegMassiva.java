package org.jahia.userregistration.regmassiva;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.jahia.userregistration.User;
import org.jahia.userregistration.utils.MD5Util;
import org.jahia.userregistration.utils.UserJahiaDB;
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


public class RegMassiva extends Action {


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


    public boolean checkUsername(String username) {

        if (userManagerService.userExists(username)) {
            return true;
        } else {
            return false;
        }


    }

    public void addUserToGroup(JahiaUser user, String tipoUtente, int siteID) {
        JahiaGroupManagerService groupManagerService = ServicesRegistry.getInstance().getJahiaGroupManagerService();
        JahiaGroup teaGroup = groupManagerService.lookupGroup(siteID, GROUP_SPORTELLO);
        // aggiungo l'utente al gruppo degli utenti che puo' accedere allo sportello
        if (teaGroup != null)
            teaGroup.addMember(user);
        // a seconda del tipo utente inserisco l'utente nel rispettivo gruppo
        String group_name = "";
        if (tipoUtente.equals(TIPO_PRIVATO) || tipoUtente.equals(TIPO_AZIENDA))
            group_name = GROUP_PROSPECT;
        if (tipoUtente.equals(TIPO_PRIV_CLI) || tipoUtente.equals(TIPO_AZ_CLI))
            group_name = GROUP_CLIENTE;
        if (tipoUtente.equals(TIPO_AMM_COND))
            group_name = GROUP_AMMCOND;
        teaGroup = groupManagerService.lookupGroup(siteID, group_name);
        if (teaGroup != null)
            teaGroup.addMember(user);
        if (tipoUtente.equals(TIPO_AZ_CLI) || tipoUtente.equals(TIPO_AZIENDA))
            teaGroup = groupManagerService.lookupGroup(siteID, GROUP_INT);
        if (teaGroup != null)
            teaGroup.addMember(user);
    }

    private void addUserToGroup(Connection connection, String username, String tipoUtente, int siteID) throws SQLException {

        // aggiungo l'utente al gruppo degli utenti che puo' accedere allo sportello
        String group_name = GROUP_SPORTELLO;
        int memberOk = addMember(connection, username, group_name, siteID);
        if (memberOk == 1) {
            logger.info("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): added user '" + username + "' in group '" + group_name + "'");
        } else {
            logger.error("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): Failed to added user '" + username + "' in group '" + group_name + "'");
        }
        // a seconda del tipo utente inserisco l'utente nel rispettivo gruppo
        if (tipoUtente.equals(TIPO_PRIVATO) || tipoUtente.equals(TIPO_AZIENDA))
            group_name = GROUP_PROSPECT;
        if (tipoUtente.equals(TIPO_PRIV_CLI) || tipoUtente.equals(TIPO_AZ_CLI))
            group_name = GROUP_CLIENTE;
        if (tipoUtente.equals(TIPO_AMM_COND))
            group_name = GROUP_AMMCOND;
        if (!group_name.equals(GROUP_SPORTELLO)) {
            memberOk = addMember(connection, username, group_name, siteID);
            if (memberOk == 1) {
                logger.info("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): added user '" + username + "' in group '" + group_name + "'");
            } else {
                logger.error("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): Failed to added user '" + username + "' in group '" + group_name + "'");
            }
        }

        if (tipoUtente.equals(TIPO_AZ_CLI) || tipoUtente.equals(TIPO_AZIENDA)) {
            group_name = GROUP_INT;
            memberOk = addMember(connection, username, group_name, siteID);
            if (memberOk == 1) {
                logger.info("ALPENITE NewUser - ALPENITE - addUserToGroup (DB): added user '" + username + "' in group '" + group_name + "'");
            } else {
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

    private String paramsAreOk(HashMap<String, String> parameters, String tipoUtente) throws JSONException {
        logger.info("[Registrazione-Utente ] - Starting check registration parameters");
        String validato = Constants.SUCCESS;
        if (tipoUtente.equals(TIPO_PRIVATO)) {
            logger.info("[Registrazione-Utente ] - Analyze parameters for user type TipoPrivato");
            if (parameters.get(NOME) == null || parameters.get(NOME).equals("")) {
                logger.info("Manca il nome");
                validato += "Manca il nome ";
            }
            if (parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")) {
                logger.info("Manca il cognome");
                validato += "Manca il cognome ";
            }
            if (parameters.get(PAESE) == null || parameters.get(PAESE).equals("")) {
                logger.info("Manca il paese");
                validato += "Manca il paese ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).equals(""))) {
                logger.info("Manca la provincia");
                validato += "Manca la provincia ";
            }
            if (!parameters.get(PAESE).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).equals(""))) {
                logger.info("Manca la provincia estera");
                validato += "Manca la provincia estera ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).equals(""))) {
                logger.info("Manca il comune");
                validato += "Manca il comune ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).equals(""))) {
                logger.info("Manca il CAP");
                validato += "Manca il CAP ";
            }
            if (!parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).equals(""))) {
                logger.info("Manca il comune estero");
                validato += "Manca il comune estero";
            }
            if (parameters.get(VIA) == null || parameters.get(VIA).equals("")) {
                logger.info("Manca la via");
                return "Manca la via ";
            }
            if (parameters.get(NCIV) == null || parameters.get(NCIV).equals("")) {
                logger.info("Manca il num. civico");
                validato += "Manca il num. civico ";
            }
        }
        if (tipoUtente.equals(TIPO_AZIENDA)) {
            logger.info("[Registrazione-Utente ] - Analyze parameters for user type TipoAzienda");
            if (parameters.get(AZIENDAENTE) == null || parameters.get(AZIENDAENTE).equals("")) {
                logger.info("Manca flag azienda/ente");
                validato += "Manca flag azienda/ente ";
            }
            if (parameters.get(NOME) == null || parameters.get(NOME).equals("")) {
                logger.info("Manca il nome ");
                validato += "Manca il nome ";
            }
            if (parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")) {
                logger.info("Manca il cognome ");
                validato += "Manca il cognome";
            }
            if (parameters.get(PAESE) == null || parameters.get(PAESE).equals("")) {
                logger.info("Manca il paese");
                validato += "Manca il paese ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).equals(""))) {
                logger.info("Manca la provincia");
                validato += "Manca la provincia ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).equals(""))) {
                logger.info("Manca il CAP");
                validato += "Manca il CAP ";
            }
            if (!parameters.get(PAESE).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).equals(""))) {
                logger.info("Manca la provincia estera");
                validato += "Manca la provincia estera";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).equals(""))) {
                logger.info("Manca il comune");
                validato += "Manca il comune ";
            }
            if (!parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).equals(""))) {
                logger.info("Manca il comune estero");
                validato += "Manca il comune estero";
            }
            if (parameters.get(VIA) == null || parameters.get(VIA).equals("")) {
                logger.info("Manca la via");
                validato += "Manca la via ";
            }
            if (parameters.get(NCIV) == null || parameters.get(NCIV).equals("")) {
                logger.info("Manca il num. civico");
                validato += "Manca il num. civico ";
            }
            if (parameters.get(NCIV) == null) {
                logger.info("Manca l'estanzione del num. civico");
                validato += "Manca l'estanzione del num. civico ";
            }

            if (parameters.get(RAG_SOC) == null || parameters.get(RAG_SOC).equals("")) {
                logger.info("Manca la ragione sociale");
                return "Manca la ragione sociale ";
            }
            if (parameters.get(PIVA) == null || parameters.get(PIVA).equals("")) {
                logger.info("Manca la piva");
                validato += "Manca la piva ";
            }
            //if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
            //	logger.info("Piva non corretta");
            //	return Constants.ERRORE_PIVA;
            //}
        }
        if (tipoUtente.equals(TIPO_PRIV_CLI)) {
            logger.info("[Registrazione-Utente ] - Analyze parameters for user type cliente privato");
            if (parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).equals("")) {
                logger.info("Manca il codice cliente");
                validato += "Manca il codice cliente ";
            }
            if (parameters.get(COD_FISC) == null || parameters.get(COD_FISC).equals("")) {
                logger.info("Manca il codice fiscale");
                validato += "Manca il codice fiscale ";
            }
        }
        if (tipoUtente.equals(TIPO_AZ_CLI)) {
            logger.info("[Registrazione-Utente ] - Analyze parameters for user type azienda cliente ");
            if (parameters.get(NOME) == null || parameters.get(NOME).equals("")) {
                logger.info("Manca il nome");
                validato += "Manca il nome ";
            }
            if (parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")) {
                logger.info("Manca il cognome");
                validato += "Manca il cognome";
            }
            if (parameters.get(COD_CLIENTE) == null || parameters.get(COD_CLIENTE).equals("")) {
                logger.info("Manca il codice cliente");
                validato += "Manca il codice cliente ";
            }
            if (parameters.get(PIVA) == null || parameters.get(PIVA).equals("")) {
                logger.info("Manca la piva");
                validato += "Manca la piva ";
            }
//       		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//       			logger.info("Piva non corretta");
//       			return Constants.ERRORE_PIVA;
//       		}
        }
        if (tipoUtente.equals(TIPO_AMM_COND)) {
            logger.info("[Registrazione-Utente ] - Analyze parameters for user type amministratore condominio");
            if (parameters.get(NOME) == null || parameters.get(NOME).equals("")) {
                logger.info("Manca il nome");
                validato += "Manca il nome ";
            }
            if (parameters.get(COGNOME) == null || parameters.get(COGNOME).equals("")) {
                logger.info("Manca il cognome");
                validato += "Manca il cognome ";
            }
            if (parameters.get(PAESE) == null || parameters.get(PAESE).equals("")) {
                logger.info("Manca il paese");
                validato += "Manca il paese ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(PROV) == null || parameters.get(PROV).equals(""))) {
                logger.info("Manca la provincia");
                validato += "Manca la provincia ";
            }
            if (!parameters.get(PAESE).equals("IT") && (parameters.get(PROV_EST) == null || parameters.get(PROV_EST).equals(""))) {
                logger.info("Manca la provincia estera");
                validato += "Manca la provincia estera";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE) == null || parameters.get(COMUNE).equals(""))) {
                logger.info("Manca il comune");
                validato += "Manca il comune ";
            }
            if (parameters.get(PAESE).equals("IT") && (parameters.get(CAP) == null || parameters.get(CAP).equals(""))) {
                logger.info("Manca il CAP");
                validato += "Manca il CAP ";
            }
            if (!parameters.get(PAESE).equals("IT") && (parameters.get(COMUNE_EST) == null || parameters.get(COMUNE_EST).equals(""))) {
                logger.info("Manca il comune estero");
                validato += "Manca il comune estero";
            }
            if (parameters.get(VIA) == null || parameters.get(VIA).equals("")) {
                logger.info("Manca la via");
                validato += "Manca la via ";
            }
            if (parameters.get(NCIV) == null || parameters.get(NCIV).equals("")) {
                logger.info("Manca il num. civico");
                validato += "Manca il num. civico ";
            }
            if (parameters.get(PIVA) == null || parameters.get(PIVA).equals("")) {
                logger.info("Manca la piva");
                validato += "Manca la piva ";
            }
//       		if(getParameter(parameters, PIVA).length() != 13 && getParameter(parameters,PIVA).startsWith("IT")){
//       			logger.info("Piva non corretta");
//       			return Constants.ERRORE_PIVA;
//       		}
            if (parameters.get(COD_FISC) == null || parameters.get(COD_FISC).equals("")) {
                logger.info("Manca codice fiscale");
                validato += "Manca codice fiscale ";
            }
        }

        // common check
        logger.info("[Registrazione-Utente ] - Analyze common parameters");
        if (parameters.get(MAIL) == null || parameters.get(MAIL).equals("")) {
            logger.info("Manca la mail");
            return "Manca la mail ";
        }
        if (!mailIsOk(parameters.get(MAIL))) {
            return "Mail in formato non corretto";
        }
        if (parameters.get(USER) == null || parameters.get(USER).equals("")) {
            logger.info("Manca il nome utente");
            return "Manca il nome utente ";
        }
        if (!usernameIsOk(parameters.get(USER))) {
            return "Nome utente gia' presente ";
        }
        if (parameters.get(PWD) == null || parameters.get(PWD).equals(""))
            return "Password non inserita ";
        if (parameters.get(PWD2) == null || !parameters.get(PWD2).equals(parameters.get(PWD)))
            return "Password non corrispondenti ";
        if (parameters.get(CONDATI) == null || !parameters.get(CONDATI).equals("si"))
            return "Inserire consenso dati con valore si obbligatorio ";

        return Constants.SUCCESS;
    }

    private String paramsAreOkInDb(HashMap<String, String> value, String tipoUtente, Connection connection) throws SQLException {
        if (!usernameIsOkInDb(value.get(USER), connection)) {
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
        if (!mail.contains("@"))
            return false;
        if (!mail.contains("."))
            return false;
        return true;
    }

    //	@Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
								  JCRSessionWrapper session, final Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
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
            String[] keyArray = pieces[2].split("\\]");
            String keyUserData = keyArray[0];
            val.put(keyUserData, value.toString());
            userFromJson.put(id, val);
            val = new HashMap<String, String>();
        }

        for (String key : parameters.keySet()) {
            List<String> value = parameters.get(key);
            String[] pieces = key.toString().split("\\[");
            String id = pieces[0];
            String[] keyArray = pieces[2].split("\\]");
            String keyUserData = keyArray[0];
            if (userFromJson.containsKey(id)) {
                val2 = userFromJson.get(id);
                val2.put(keyUserData, value.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                userFromJson.put(id, val2);
            }

        }
        //Map user ok


        //check SAP
        for (String k : userFromJson.keySet()) {
            final HashMap<String, String> value = userFromJson.get(k);
            // variables
            String nome = "";
            String cognome = "";
            String ragione_sociale = "";
            String bp = "";
            // validazione json
            final String tipoUtente = value.get(TIPO);

            //validazione controlli sap
            boolean validazioneSap = true;

            String result = paramsAreOk(value, tipoUtente);

            if (result.equals(Constants.SUCCESS)) {
                logger.info("risultato validazione --- " + result);


                // PIVA - validation
                String piva = value.get(PIVA);
                String pivaIT = value.get(PIVAIT);

                if (piva == null)
                    piva = "";

                if (pivaIT == null)
                    pivaIT = "false";

                if (piva.length() == 11 && pivaIT.equals("false")) {
                    piva = "IT" + piva;
                }


                if (tipoUtente.equals(TIPO_PRIVATO)) {
                    logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente);
                    WSReturn<Message> msg = WSClient.getClient().controllaProspect("P", value.get(MAIL), null);
                    messaggio = msg.getRitorno();
                    if (!isMessageValid(messaggio)) {
                        logger.info("TipoUtente: " + tipoUtente);
                        logger.info("Messaggio non valido: " + messaggio.geteReturn() + " - " + messaggio.getFirstMsg());
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente + " - " + messaggio.getFirstMsg());
                        WSReturnManager.evaluate(msg, req.getSession());
                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                        validazioneSap = false;
                        risposta.put("validazione SAP elemento" + k, messaggio.getEtMess().toString());
                    }
                    nome = value.get(NOME);
                    cognome = value.get(COGNOME);
                }

                if (tipoUtente.equals(TIPO_AZIENDA)) {
                    WSReturn<Message> msg = null;
                    if (value.get(AZIENDAENTE).equalsIgnoreCase("azienda")) {
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente);
                        msg = WSClient.getClient().controllaProspect("A", value.get(MAIL), piva);
                    } else {
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente);
                        msg = WSClient.getClient().controllaProspect("E", value.get(MAIL), piva);
                    }
                    messaggio = msg.getRitorno();
                    if (!isMessageValid(messaggio)) {
                        logger.info("TipoUtente: " + tipoUtente);
                        logger.info("Messaggio non valido: " + messaggio.geteReturn() + " - " + messaggio.getFirstMsg());
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente + " - " + messaggio.getFirstMsg());
                        WSReturnManager.evaluate(msg, req.getSession());

                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                        validazioneSap = false;
                        risposta.put("validazione SAP elemento" + k, messaggio.getEtMess().toString());
                    }
                    nome = value.get(NOME);
                    cognome = value.get(COGNOME);
                }
                if (tipoUtente.equals(TIPO_PRIV_CLI)) {
                    logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente);
                    WSReturn<Message> msg = WSClient.getClient().controllaUsername("P", value.get(COD_CLIENTE), value.get(MAIL), value.get(COD_FISC), "");
                    messaggio = msg.getRitorno();
                    logger.info(messaggio.getEtMess().toString());

                    if (!isMessageValid(messaggio)) {
                        logger.info("TipoUtente: " + tipoUtente);
                        logger.info("Messaggio non valido: " + messaggio.geteReturn() + " - " + messaggio.getFirstMsg());

                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente + " - " + messaggio.getFirstMsg());
                        WSReturnManager.evaluate(msg, req.getSession());
                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(value.get("redirectPage").get(0))).getPath());
                        validazioneSap = false;
                        risposta.put("validazione SAP elemento" + k, messaggio.getEtMess().toString());
                    }
                    bp = messaggio.getBp();
                }
                if (tipoUtente.equals(TIPO_AZ_CLI)) {
                    WSReturn<Message> msg = null;
                    if (value.get(AZIENDAENTE).equalsIgnoreCase("azienda"))
                        msg = WSClient.getClient().controllaUsername("A", value.get(COD_CLIENTE), value.get(MAIL), value.get(COD_FISC), piva);
                    else
                        msg = WSClient.getClient().controllaUsername("E", value.get(COD_CLIENTE), value.get(MAIL), value.get(COD_FISC), piva);
                    messaggio = msg.getRitorno();
                    if (!isMessageValid(messaggio)) {
                        logger.info("TipoUtente: " + tipoUtente);
                        logger.info("Messaggio non valido: " + messaggio.geteReturn() + " - " + messaggio.getFirstMsg());
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente + " - " + messaggio.getFirstMsg());
                        WSReturnManager.evaluate(msg, req.getSession());
                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                        validazioneSap = false;
                        risposta.put("validazione SAP elemento" + k, messaggio.getEtMess().toString());
                    }
                    nome = value.get(NOME);
                    cognome = value.get(COGNOME);
                }
                if (tipoUtente.equals(TIPO_AMM_COND)) {
                    WSReturn<Message> msg = WSClient.getClient().controllaUsername("AM", null, value.get(MAIL), value.get(COD_FISC), piva);
                    messaggio = msg.getRitorno();
                    if (!isMessageValid(messaggio)) {
                        logger.info("TipoUtente: " + tipoUtente);
                        logger.info("Messaggio non valido: " + messaggio.geteReturn() + " - " + messaggio.getFirstMsg());
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + " email=" + value.get(MAIL) + " - " + "TipoUtente: " + tipoUtente + " - " + messaggio.getFirstMsg());
                        WSReturnManager.evaluate(msg, req.getSession());
                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                        validazioneSap = false;
                        risposta.put("validazione SAP elemento" + k, messaggio.getEtMess().toString());
                    }
                    nome = value.get(NOME);
                    cognome = value.get(COGNOME);
                    bp = messaggio.getBp();

                }

                logger.info("SAP call done");

                if (validazioneSap) {
                    // build list of properties
                    final Properties properties = new Properties();
                    properties.put("j:firstName", nome);
                    properties.put("j:lastName", cognome);
                    properties.put("j:accountLocked", "true");
                    properties.put("j:validato", "false");
                    properties.put("j:tipoBP", tipoUtente);
                    properties.put("j:nuovaMail", value.get(MAIL));
                    //properties.put("j:codiceVerifica", codice);
                    properties.put("j:bp", bp);
                    //properties.put("preferredLanguage", parameters.get("lang").get(0));
                    if (tipoUtente.equals(TIPO_AZIENDA))
                        properties.put("j:organization", ragione_sociale);
                    // check the username (fix 22/10/2013)
                    if (!userManagerService.isUsernameSyntaxCorrect(value.get(USER))) {
                        WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_USERNAME_UPPERCASE, this.getClass().getName(), "return", new ArrayList<String>()), req.getSession());
                        logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + "   email=" + value.get(MAIL) + " - " + result + " - " + "Errore username con lettere minuscole ");
                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                    }
					try {

						final int siteID = renderContext.getSite().getID();
						/*JCRNodeWrapper page = session.getNodeByIdentifier(parameters.get("verificationPage").get(0));
						final String link = req.getScheme()+ "://" + req.getServerName() +":"+req.getServerPort() + page.getUrl() + "?username="+parameters.get(USER).get(0)+"&mail="+parameters.get(MAIL).get(0);

						String mailProcess = "";

						try{
							mailProcess = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_"+parameters.get("lang").get(0)).getPropertyAsString("mail");
						}catch(PathNotFoundException e){
							mailProcess = session.getNodeByIdentifier(parameters.get("currentNode").get(0)).getNode("j:translation_en").getPropertyAsString("mail");
						}

						final String mail = mailProcess;*/

						//creo JahiaUser con thread separato
						Thread thread = new Thread() {
							public void run() {
								try {
									// create the user
									final JahiaUser user = userManagerService.createUser(value.get(USER), value.get(PWD), properties);
									// add user to group
									addUserToGroup(user, tipoUtente, siteID);

									//mail preparation
									/*String from = parameters.get("from")==null?SettingsBean.getInstance().getMail_from():getParameter(parameters, "from");
									String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
									String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
									String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
									logger.info("ALPENITE NewUser - Mail preparation done");
									logger.info("ALPENITE NewUser - [Registrazione-Utente ]- Mail preparation done");
									logger.info("ALPENITE NewUser - Ragione sociale" + getParameter(parameters, RAG_SOC));*/
									// send the mail
									// build the link
									//logger.info("ALPENITE NewUser - [Registrazione-Utente ]- Info mail: "+ value.get("lang"));
									/*if (StringUtils.isNotEmpty(mail)) {
										//String mail_tea = mail.replaceAll(SUBSCRIBE_TAG, link);
										//mailService.sendHtmlMessage(from, parameters.get(MAIL).get(0), cc, bcc, subject, mail_tea);

										logger.info("ALPENITE NewUser - [Registrazione-Utente ]- mail sent");
									}else {
										logger.error("ALPENITE NewUser - [Registrazione-Utente ]- mail is Empty");
									}
*/
									logger.info("ALPENITE NewUser THREAD - [Registrazione-Utente]- User preparation done");
								} catch (Exception ex) {
									logger.error("ALPENITE NewUser THREAD - ERROR: " + ex.getMessage());
									Thread.currentThread().interrupt();
								}
							}
						};
						thread.start();


						String securePasswordMD5 = MD5Util.getMd5(value.get(PWD));
						logger.info("ALPENITE securePasswordMD5: " + securePasswordMD5);
						String securePassword2MD5 = MD5Util.getMd5(value.get(PWD2));
						logger.info("ALPENITE securePassword2MD5: " + securePassword2MD5);

						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String registrationDate = formatter.format(new Date());

						UserJahiaDB userJahiaDB = new UserJahiaDB();

						Connection connection = userJahiaDB.connectedDB();

						logger.info("ALPENITE CHECK IF USERNAME EXIST IN TABLE");
						String resultDb = paramsAreOkInDb(value, tipoUtente, connection);
						if (!resultDb.equals(Constants.SUCCESS)) {
							WSReturnManager.evaluate(new WSReturn<String>(resultDb, this.getClass().getName(), "return", new ArrayList<String>()), req.getSession());
							logger.info(" [Registrazione-Utente ]- userName =" + value.get(USER) + "   email=" + value.get(MAIL) + " - " + resultDb);
							userJahiaDB.closeConnection(connection);
							return new ActionResult(HttpServletResponse.SC_ACCEPTED, (session.getNodeByIdentifier(value.get("redirectPage"))).getPath());
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
						prepared.setString(1, value.get(USER));
						prepared.setString(2, securePasswordMD5);
						prepared.setString(3, securePassword2MD5);
						prepared.setString(4, StringUtils.isNotEmpty(value.get(TIPO)) ? value.get(TIPO) : "");
						prepared.setString(5, StringUtils.isNotEmpty(value.get(NOME)) ? value.get(NOME) : "");
						prepared.setString(6, StringUtils.isNotEmpty(value.get(COGNOME)) ? value.get(COGNOME) : "");
						prepared.setString(7, StringUtils.isNotEmpty(value.get(MAIL)) ? value.get(MAIL) : "");
						prepared.setString(8, value.get(PREFIXTEL)==null?"+39":value.get(PREFIXTEL).replaceAll(" ", ""));
						prepared.setString(9, StringUtils.isNotEmpty(value.get(TEL)) ? value.get(TEL) : "");
						prepared.setString(10, value.get(PREFIXCEL)==null?"+39":value.get(PREFIXCEL).replaceAll(" ", ""));
						prepared.setString(11, StringUtils.isNotEmpty(value.get(CEL)) ? value.get(CEL) : "");
						prepared.setString(12, StringUtils.isNotEmpty(value.get(COD_FISC)) ? value.get(COD_FISC) : "");
						prepared.setString(13, StringUtils.isNotEmpty(value.get(RUOLO)) ? value.get(RUOLO) : "");
						prepared.setString(14, StringUtils.isNotEmpty(value.get("lang")) ? value.get(RUOLO) : "lang");
						prepared.setString(15, StringUtils.isNotEmpty(value.get(PAESE)) ? value.get(PAESE) : "");
						prepared.setString(16, StringUtils.isNotEmpty(value.get(PROV)) ? value.get(PROV) : "");
						prepared.setString(17, StringUtils.isNotEmpty(value.get(PROV_EST)) ? value.get(PROV_EST) : "");
						prepared.setString(18, StringUtils.isNotEmpty(value.get(COMUNE)) ? value.get(COMUNE) : "");
						prepared.setString(19, StringUtils.isNotEmpty(value.get(COMUNE_EST)) ? value.get(COMUNE_EST) : "");
						prepared.setString(20, StringUtils.isNotEmpty(value.get(VIA)) ? value.get(VIA) : "");
						prepared.setString(21, StringUtils.isNotEmpty(value.get(NCIV)) ? value.get(NCIV) : "");
						prepared.setString(22, StringUtils.isNotEmpty(value.get(CAP)) ? value.get(CAP) : "");
						prepared.setString(23, StringUtils.isNotEmpty(value.get(EXTNCIV)) ? value.get(EXTNCIV) : "");
						prepared.setString(24, StringUtils.isNotEmpty(value.get("subject")) ? value.get("subject") : "");
						prepared.setString(25, StringUtils.isNotEmpty(value.get(RAG_SOC)) ? value.get(RAG_SOC) : "");
						prepared.setString(26, StringUtils.isNotEmpty(value.get(COD_CLIENTE)) ? value.get(COD_CLIENTE) : "");
						prepared.setString(27, StringUtils.isNotEmpty(value.get(PIVA)) ? value.get(PIVA) : "");
						prepared.setString(28, StringUtils.isNotEmpty(value.get(AZIENDAENTE)) ? value.get(AZIENDAENTE) : "");
						prepared.setString(29, StringUtils.isNotEmpty(value.get(CONMAIL)) ? value.get(CONMAIL).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
						prepared.setString(30, StringUtils.isNotEmpty(value.get(CONTEL)) ? value.get(CONTEL).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
						prepared.setString(31, StringUtils.isNotEmpty(value.get(CONPOSTA)) ? value.get(CONPOSTA).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
						prepared.setString(32, StringUtils.isNotEmpty(value.get(CONDATI)) ? value.get(CONDATI).equals(ACCETTO) ? SAP_TRUE : SAP_FALSE : "");
						prepared.setString(33, StringUtils.isNotEmpty(value.get("from")) ? value.get("from") : "");
						prepared.setString(34, StringUtils.isNotEmpty(value.get("jcrResourceID")) ? value.get("jcrResourceID") : "");
						prepared.setString(35, StringUtils.isNotEmpty(value.get("jcrNodeType")) ? value.get("jcrNodeType") : "");
						prepared.setString(36, StringUtils.isNotEmpty(value.get("jcrRedirectTo")) ? value.get("jcrRedirectTo") : "");
						prepared.setString(37, StringUtils.isNotEmpty(value.get("finalPage")) ? value.get("finalPage") : "");
						prepared.setString(38, StringUtils.isNotEmpty(value.get("jcrCaptcha")) ? value.get("jcrCaptcha") : "");
						prepared.setString(39, StringUtils.isNotEmpty(value.get("jcrNewNodeOutputFormat")) ? value.get("jcrNewNodeOutputFormat") : "");
						prepared.setString(40, StringUtils.isNotEmpty(value.get("currentNode")) ? value.get("currentNode") : "");
						prepared.setString(41, StringUtils.isNotEmpty(value.get("verificationPage")) ? value.get("verificationPage") : "");
						prepared.setString(42, StringUtils.isNotEmpty(value.get("redirectPage")) ? value.get("redirectPage") : "");
						prepared.setString(43, StringUtils.isNotEmpty(value.get("form-token")) ? value.get("form-token") : "");
						prepared.setString(44, StringUtils.isNotEmpty(registrationDate) ? registrationDate : "");
						prepared.setString(45, "true");
						prepared.setString(46, "false");
						prepared.setString(47, tipoUtente);
						prepared.setString(48, StringUtils.isNotEmpty(value.get(MAIL)) ? value.get(MAIL) : "");
						prepared.setString(49, "");
						prepared.setString(50, bp);
						prepared.setString(51, tipoUtente.equals(TIPO_AZIENDA) ? ragione_sociale : "");

						logger.info("ALPENITE NewUser - ALPENITE INSERT: " + prepared.toString());

						prepared.executeUpdate();

						//close statement
						logger.info("ALPENITE NewUser - Chiudo PreparedStatement");
						prepared.close();

						//aggiungo nuovo user_registration a user_group
						addUserToGroup(connection, value.get(USER), tipoUtente, siteID);

						//chiudo connessione al db
						userJahiaDB.closeConnection(connection);

					} catch (SQLException e) {
						logger.error("ALPENITE ERROR SQLException: " + e.getMessage());
					} catch (Exception e) {
						logger.error("ALPENITE ERROR Exception: " + e.getMessage());
					}


                    // crea i prospect lato sap
                    logger.info("[Confirm-Registration]- Start operation");
                    final String usernameF = value.get(USER);
                    // get the user
                    UserJahiaDB userJahiaDB = new UserJahiaDB();
                    Connection connection = userJahiaDB.connectedDB();

                    logger.info("ALPENITE ConfirmRegistration - check if username exist in db");
                    Statement stm = connection.createStatement();
                    ResultSet rs = stm.executeQuery("select * from user_registration where username='" + usernameF + "'");
                    boolean resultQueryUser = rs.next();

                    if (!resultQueryUser){
                        logger.error("ALPENITE ConfirmRegistration - Username is not present in db");
                        return ActionResult.INTERNAL_ERROR_JSON;
                    }

                    logger.info("ALPENITE ConfirmRegistration - Username is present in db, prepare object user");

                    // iterate through the java resultset and populated user object
                    User user = new User();
                    user.setUsername(rs.getString(org.jahia.userregistration.utils.Constants.USERNAME));
                    user.setPassword(rs.getString(org.jahia.userregistration.utils.Constants.PASSWORD));
                    user.setPassword2(rs.getString(org.jahia.userregistration.utils.Constants.PASSWORD2));
                    user.setTipoUtente(rs.getString(org.jahia.userregistration.utils.Constants.TIPO_UTENTE));
                    user.setNome(rs.getString(org.jahia.userregistration.utils.Constants.NOME));
                    user.setCognome(rs.getString(org.jahia.userregistration.utils.Constants.COGNOME));
                    user.setEmail(rs.getString(org.jahia.userregistration.utils.Constants.EMAIL));
                    user.setPrefissoTel(rs.getString(org.jahia.userregistration.utils.Constants.PREFISSO_TEL));
                    user.setTelefono(rs.getString(org.jahia.userregistration.utils.Constants.TELEFONO));
                    user.setPrefissoCel(rs.getString(org.jahia.userregistration.utils.Constants.PREFISSO_CEL));
                    user.setCellulare(rs.getString(org.jahia.userregistration.utils.Constants.CELLULARE));
                    user.setCodice_fiscale(rs.getString(org.jahia.userregistration.utils.Constants.CODICE_FISCALE));
                    user.setRuolo(rs.getString(org.jahia.userregistration.utils.Constants.RUOLO));
                    user.setLang(rs.getString(org.jahia.userregistration.utils.Constants.LANG));
                    user.setPaese(rs.getString(org.jahia.userregistration.utils.Constants.PAESE));
                    user.setProvincia(rs.getString(org.jahia.userregistration.utils.Constants.PROVINCIA));
                    user.setProvincia_estera(rs.getString(org.jahia.userregistration.utils.Constants.PROVINCIA_ESTERA));
                    user.setComune(rs.getString(org.jahia.userregistration.utils.Constants.COMUNE));
                    user.setComune_estero(rs.getString(org.jahia.userregistration.utils.Constants.COMUNE_ESTERO));
                    user.setVia(rs.getString(org.jahia.userregistration.utils.Constants.VIA));
                    user.setNcivico(rs.getString(org.jahia.userregistration.utils.Constants.N_CIVICO));
                    user.setCap(rs.getString(org.jahia.userregistration.utils.Constants.CAP));
                    user.setExtncivico(rs.getString(org.jahia.userregistration.utils.Constants.EXT_CIVICO));
                    user.setSubject(rs.getString(org.jahia.userregistration.utils.Constants.SUBJECT));
                    user.setRagione_sociale(rs.getString(org.jahia.userregistration.utils.Constants.RAGIONE_SOCIALE));
                    user.setCodice_cliente(rs.getString(org.jahia.userregistration.utils.Constants.CODICE_CLIENTE));
                    user.setPartita_iva(rs.getString(org.jahia.userregistration.utils.Constants.PARTITA_IVA));
                    user.setAziendaEnte(rs.getString(org.jahia.userregistration.utils.Constants.AZIENDA_ENTE));
                    user.setNewsletterRadio(rs.getString(org.jahia.userregistration.utils.Constants.NEWSLETTER_RADIO));
                    user.setTelefonoRadio(rs.getString(org.jahia.userregistration.utils.Constants.TELEFONO_RADIO));
                    user.setPostaRadio(rs.getString(org.jahia.userregistration.utils.Constants.POSTA_RADIO));
                    user.setConDatiRadio(rs.getString(org.jahia.userregistration.utils.Constants.CON_DATI_RADIO));
                    user.setFrom_email(rs.getString(org.jahia.userregistration.utils.Constants.FROM_EMAIL));
                    user.setJcrResourceID(rs.getString(org.jahia.userregistration.utils.Constants.JCR_RESOURCE_ID));
                    user.setJcrNodeType(rs.getString(org.jahia.userregistration.utils.Constants.JCR_NODE_TYPE));
                    user.setJcrRedirectTo(rs.getString(org.jahia.userregistration.utils.Constants.JCR_REDIRECT_TO));
                    user.setFinalPage(rs.getString(org.jahia.userregistration.utils.Constants.FINAL_PAGE));
                    user.setJcrCaptcha(rs.getString(org.jahia.userregistration.utils.Constants.JCR_CAPTHA));
                    user.setJcrNewNodeOutputFormat(rs.getString(org.jahia.userregistration.utils.Constants.JCR_NEW_NODE_OUTPUT_FORMAT));
                    user.setCurrentNode(rs.getString(org.jahia.userregistration.utils.Constants.CURRENT_NODE));
                    user.setVerificationPage(rs.getString(org.jahia.userregistration.utils.Constants.VERIFICATION_PAGE));
                    user.setRedirectPage(rs.getString(org.jahia.userregistration.utils.Constants.REDIRECT_PAGE));
                    user.setForm_token(rs.getString(org.jahia.userregistration.utils.Constants.FORM_TOKEN));
                    user.setRegistration_date(rs.getString(org.jahia.userregistration.utils.Constants.REGISTRATION_DATE));
                    user.setJ_accountLocked(rs.getString(org.jahia.userregistration.utils.Constants.J_ACCOUNT_LOCKED));
                    user.setJ_validato(rs.getString(org.jahia.userregistration.utils.Constants.J_VALIDATO));
                    user.setJ_tipoBP(rs.getString(org.jahia.userregistration.utils.Constants.J_TIPO_BP));
                    user.setJ_nuovaMail(rs.getString(org.jahia.userregistration.utils.Constants.J_NUOVA_MAIL));
                    user.setJ_codiceVerifica(rs.getString(org.jahia.userregistration.utils.Constants.J_CODICE_VERIFICA));
                    user.setJ_bp(rs.getString(org.jahia.userregistration.utils.Constants.J_BP));
                    user.setJ_organization(rs.getString(org.jahia.userregistration.utils.Constants.J_ORGANIZZATION));

                    // print the results
                    logger.info("ALPENITE ConfirmRegistration - print User: " + user.toString());

                    logger.info("ALPENITE ConfirmRegistration - close Statement");
                    stm.close();

                    // mail validation
                    // get new value
                    String nuovaMail = user.getJ_nuovaMail();
//
                    if(user.getJ_validato().equals("false")) {
                        // validation mail for the registration
                        // SAP
                        Message msg = null;
                        //get tipoUtente
                        String tipoUtenteDB = user.getJ_tipoBP();

                        if(tipoUtenteDB.equals(Constants.TIPO_PRIVATO)){
                            DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
                                    null,
                                    "P",
                                    null,
                                    null,
                                    null,
                                    null,
                                    user.getNome(),
                                    user.getCognome(),
                                    user.getTelefono(),
                                    user.getCellulare(),
                                    user.getPaese(),
                                    user.getProvincia(),
                                    user.getComune(),
                                    null,
                                    user.getVia(),
                                    user.getNcivico(),
                                    user.getExtncivico(),
                                    user.getCap(),
                                    user.getCodice_fiscale(),
                                    null,
                                    user.getConDatiRadio(),
                                    user.getNewsletterRadio(),
                                    user.getTelefonoRadio(),
                                    user.getPostaRadio(),
                                    null,
                                    user.getPrefissoTel(),
                                    user.getPrefissoCel());
                            WSReturn<Message> message = WSClient.getClient().creaProspect(user.getUsername(), nuovaMail, datiAnagrafici, null);
                            msg = message.getRitorno();
                            if(!isMessageValid(msg)){
                                WSReturnManager.evaluate(message, req.getSession());
                                logger.info("tipoUtenteDB TIPO_PRIVATO: "+tipoUtenteDB);
                                logger.info("[Confirm-Registration]- Username TIPO_PRIVATO: "+user.getUsername() + " tipoUtenteDB: "+tipoUtenteDB +" Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
                                /*errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
                                return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());*/
                            }
                            // save the BP information

                            //update bp in db
                            userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), usernameF);

                            //update bp in jahia user
                            final String bpMsg = msg.getBp();
                            Thread thread = new Thread() {
                                public void run() {
                                    try {
                                        JahiaUser user = userManagerService.lookupUser(usernameF);
                                        user.setProperty("j:bp", bpMsg);
                                        logger.info("ALPENITE ConfirmRegistration THREAD TIPO_PRIVATO - [Registrazione-Utente]- Update bpMsg");
                                    } catch (Exception ex) {
                                        logger.error("ALPENITE ConfirmRegistration THREAD TIPO_PRIVATO - ERROR: " + ex.getMessage());
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            };
                            thread.start();
                        }
                        if(tipoUtenteDB.equals(Constants.TIPO_AZIENDA)){

                            DatiAnagrafici datiAnagraficiAzienda = null;
                            if(user.getAziendaEnte().equals("azienda")){
                                datiAnagraficiAzienda = new DatiAnagrafici(
                                        null,
                                        "A",
                                        user.getRagione_sociale(),
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        user.getTelefono(),
                                        user.getCellulare(),
                                        user.getPaese(),
                                        user.getProvincia(),
                                        user.getComune(),
                                        null,
                                        user.getVia(),
                                        user.getNcivico(),
                                        user.getExtncivico(),
                                        user.getCap(),
                                        user.getCodice_fiscale(),
                                        user.getPartita_iva(),
                                        user.getConDatiRadio(),
                                        user.getNewsletterRadio(),
                                        user.getTelefonoRadio(),
                                        user.getPostaRadio(),
                                        null,
                                        user.getPrefissoTel(),
                                        user.getPrefissoCel());
                            }else{

                                datiAnagraficiAzienda = new DatiAnagrafici(
                                        null,
                                        "E",
                                        user.getRagione_sociale(),
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        user.getTelefono(),
                                        user.getCellulare(),
                                        user.getPaese(),
                                        user.getProvincia(),
                                        user.getComune(),
                                        null,
                                        user.getVia(),
                                        user.getNcivico(),
                                        user.getExtncivico(),
                                        user.getCap(),
                                        user.getCodice_fiscale(),
                                        user.getPartita_iva(),
                                        user.getConDatiRadio(),
                                        user.getNewsletterRadio(),
                                        user.getTelefonoRadio(),
                                        user.getPostaRadio(),
                                        null,
                                        user.getPrefissoTel(),
                                        user.getPrefissoCel());
                            }

                            DatiAnagrafici datiAnagraficiInterlocutore = new DatiAnagrafici(
                                    null,
                                    "I",
                                    null,
                                    null,
                                    null,
                                    null,
                                    user.getNome(),
                                    user.getCognome(),
                                    user.getTelefono(),
                                    user.getCellulare(),
                                    user.getPaese(),
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    user.getConDatiRadio(),
                                    user.getNewsletterRadio(),
                                    user.getTelefonoRadio(),
                                    user.getPostaRadio(),
                                    user.getRuolo(),
                                    user.getPrefissoTel(),
                                    user.getPrefissoCel());
                            logger.info("[Confirm-Registration]-Interlocutore TIPO_AZIENDA: "+datiAnagraficiInterlocutore==null?"null":datiAnagraficiInterlocutore.getNome());
                            WSReturn<Message> message = WSClient.getClient().creaProspect(user.getUsername(), nuovaMail, datiAnagraficiInterlocutore, datiAnagraficiAzienda);
                            msg = message.getRitorno();
                            if(!isMessageValid(msg)){
                                /*if(msg.geteReturn().equals("07"))
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
                                }*/
                                logger.info("[Confirm-Registration]-Username TIPO_AZIENDA: "+user.getUsername()+"tipoUtenteDB: "+tipoUtenteDB+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
                                WSReturnManager.evaluate(message, req.getSession());
                                //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                            }
                            // save the BP information
                            //update bp in db
                            userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), usernameF);

                            //update bp in jahia user
                            final String bpMsg = msg.getBp();
                            Thread thread = new Thread() {
                                public void run() {
                                    try {
                                        JahiaUser user = userManagerService.lookupUser(usernameF);
                                        user.setProperty("j:bp", bpMsg);
                                        logger.info("ALPENITE ConfirmRegistration THREAD TIPO_AZIENDA - [Registrazione-Utente]- Update bpMsg");
                                    } catch (Exception ex) {
                                        logger.error("ALPENITE ConfirmRegistration THREAD TIPO_AZIENDA - ERROR: " + ex.getMessage());
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            };
                            thread.start();
                        }
                        if(tipoUtente.equals(Constants.TIPO_PRIV_CLI)){
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
                                    user.getPaese(),
                                    "",// provincia,
                                    "",// comune,
                                    "",// frazione,
                                    "",// via,
                                    "",//cap,
                                    "",
                                    user.getCodice_fiscale(),// codiceFiscale,
                                    "",// partitaIVA,
                                    user.getConDatiRadio(),
                                    user.getNewsletterRadio(),
                                    user.getTelefonoRadio(),
                                    user.getPostaRadio(),
                                    ""); // ruolo;

                            WSReturn<Message> message = WSClient.getClient().creaUsername(
                                    user.getCodice_cliente(),
                                    user.getUsername(),
                                    nuovaMail,
                                    user.getCodice_fiscale(),
                                    null,
                                    datiAnagrafici,
                                    null,
                                    false);
                            msg = message.getRitorno();
                            if(!isMessageValid(msg)){
                                logger.info("[Confirm-Registration]-Username TIPO_PRIV_CLI: "+user.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
                                //errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
                                WSReturnManager.evaluate(message, req.getSession());
                                //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                            }
                            WSClient.getClient().addEmail(msg.getBp(), nuovaMail);

                            //update name in db
                            userJahiaDB.updateDBProperty(connection, "nome", msg.getName(), usernameF);

                            //update lastName in db
                            userJahiaDB.updateDBProperty(connection, "cognome", msg.getSurname(), usernameF);

                            //update bp in db
                            userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), usernameF);

                            //update bp, name and firstname in jahia user
                            final String bpMsg = msg.getBp();
                            final String firstNameMsg = msg.getName();
                            final String lastNameMsg = msg.getSurname();
                            Thread thread = new Thread() {
                                public void run() {
                                    try {
                                        JahiaUser user = userManagerService.lookupUser(usernameF);
                                        user.setProperty("j:firstName", firstNameMsg);
                                        user.setProperty("j:lastName", lastNameMsg);
                                        user.setProperty("j:bp",bpMsg);
                                        logger.info("ALPENITE ConfirmRegistration THREAD TIPO_PRIV_CLI - [Registrazione-Utente]- Update bpMsg, firstName, lastName");
                                    } catch (Exception ex) {
                                        logger.error("ALPENITE ConfirmRegistration THREAD TIPO_PRIV_CLI - ERROR: " + ex.getMessage());
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            };
                            thread.start();
                        }
                        if(tipoUtente.equals(Constants.TIPO_AZ_CLI)){
                            DatiAnagrafici datiAnagraficiInterlocutore = new DatiAnagrafici(
                                    null,
                                    "I",
                                    null,
                                    null,
                                    null,
                                    null,
                                    user.getNome(),
                                    user.getCognome(),
                                    user.getTelefono(),
                                    user.getCellulare(),
                                    user.getPaese(),
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    user.getConDatiRadio(),
                                    user.getNewsletterRadio(),
                                    user.getTelefonoRadio(),
                                    user.getPostaRadio(),
                                    user.getRuolo(),
                                    user.getPrefissoTel(),
                                    user.getPrefissoCel());
                            WSReturn<Message> message = WSClient.getClient().creaUsername(
                                    user.getCodice_cliente(),
                                    user.getUsername(),
                                    nuovaMail,
                                    user.getCodice_fiscale(),
                                    user.getPartita_iva(),
                                    datiAnagraficiInterlocutore,
                                    null,
                                    false);
                            msg = message.getRitorno();
                            if(!isMessageValid(msg)){
                                logger.info("TipoUtente TIPO_AZ_CLI: "+tipoUtente);
                                logger.info("[Confirm-Registration]-Username TIPO_AZ_CLI: "+user.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
                                if(msg.getBp() != null){
                                    //update bp in db
                                    userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), usernameF);

                                    //update bp in jahia user
                                    final String bpMsg = msg.getBp();
                                    Thread thread = new Thread() {
                                        public void run() {
                                            try {
                                                JahiaUser user = userManagerService.lookupUser(usernameF);
                                                user.setProperty("j:bp", bpMsg);
                                                logger.info("ALPENITE ConfirmRegistration THREAD TIPO_AZ_CLI - [Registrazione-Utente]- Update bpMsg");
                                            } catch (Exception ex) {
                                                logger.error("ALPENITE ConfirmRegistration THREAD TIPO_AZ_CLI - ERROR: " + ex.getMessage());
                                                Thread.currentThread().interrupt();
                                            }
                                        }
                                    };
                                    thread.start();
                                }
                                //errorMessageMail(datiAnagraficiInterlocutore, parameters, msg.getBp(), msg.getFirstMsg());
                                WSReturnManager.evaluate(message, req.getSession());
                                //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                            }
                            //update bp in db
                            PreparedStatement updateBp = connection.prepareStatement("update user_registration set j_bp= '" + msg.getBp() + "' where username= ? ");
                            updateBp.setString(1, usernameF);
                            int updateBp_done = updateBp.executeUpdate();
                            logger.info("ALPENITE ConfirmRegistration TIPO_AZ_CLI - updateBp: " + updateBp_done);
                            updateBp.close();

                            //update bp in jahia user
                            final String bpMsg = msg.getBp();
                            Thread thread = new Thread() {
                                public void run() {
                                    try {
                                        JahiaUser user = userManagerService.lookupUser(usernameF);
                                        user.setProperty("j:bp", bpMsg);
                                        logger.info("ALPENITE ConfirmRegistration THREAD TIPO_AZ_CLI - [Registrazione-Utente]- Update bpMsg");
                                    } catch (Exception ex) {
                                        logger.error("ALPENITE ConfirmRegistration THREAD TIPO_AZ_CLI - ERROR: " + ex.getMessage());
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            };
                            thread.start();
                        }
                        if(tipoUtente.equals(Constants.TIPO_AMM_COND)){
                            DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
                                    null,
                                    "AM",
                                    user.getNome(),
                                    user.getCognome(),
                                    null,
                                    null,
                                    null,
                                    null,
                                    user.getTelefono(),
                                    user.getCellulare(),
                                    user.getPaese(),
                                    user.getProvincia(),
                                    user.getComune(),
                                    null,
                                    user.getVia(),
                                    user.getNcivico(),
                                    user.getExtncivico(),
                                    user.getCap(),
                                    user.getCodice_fiscale(),
                                    user.getPartita_iva(),
                                    user.getConDatiRadio(),
                                    user.getNewsletterRadio(),
                                    user.getTelefonoRadio(),
                                    user.getPostaRadio(),
                                    null,
                                    user.getPrefissoTel(),
                                    user.getPrefissoCel());


                            datiAnagrafici.setEstenzioneNumeroCivico(user.getExtncivico());
                            WSReturn<Message> message = WSClient.getClient().creaUsername(
                                    null,
                                    user.getUsername(),
                                    nuovaMail,
                                    user.getCodice_fiscale(),
                                    user.getPartita_iva(),
                                    datiAnagrafici,
                                    null,
                                    false);
                            msg = message.getRitorno();
                            if(!isMessageValid(msg)){

                                if(msg.geteReturn().equals("07"))
                                {
                                    // mail preparation
                                    /*String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
                                    String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
                                    String from = parameters.get("from")==null?"":parameters.get("from").get(0);
                                    String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
                                    String user_mail = parameters.get("mail")==null?"":parameters.get("mail").get(0);
                                    String backOffice = parameters.get("backOffice")==null?"":parameters.get("backOffice").get(0);
                                    String mailContent = parameters.get("mailContent")==null?"":parameters.get("mailContent").get(0);


                                    mailContent = mailContent.concat("<br />Dati Anagrafici: <br /><br />").concat("Business Partner: "+msg.getBp()).concat("<br />").concat("E-mail: "+user_mail).concat("<br />"+datiAnagrafici.toString());

                                    mailService.sendHtmlMessage(from, backOffice, cc, bcc, subject, mailContent);*/


                                }
                                logger.info("TipoUtente TIPO_AMM_COND: "+tipoUtente);
                                logger.info("Messaggio non valido TIPO_AMM_COND: "+msg.geteReturn()+" - "+msg.getFirstMsg());
                                //WSReturnManager.evaluate(message, req.getSession());
                                //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                            }
                            //update bp in db
                            userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), usernameF);

                            //update bp in jahia user
                            final String bpMsg = msg.getBp();
                            Thread thread = new Thread() {
                                public void run() {
                                    try {
                                        JahiaUser user = userManagerService.lookupUser(usernameF);
                                        user.setProperty("j:bp", bpMsg);
                                        logger.info("ALPENITE ConfirmRegistration THREAD TIPO_AMM_COND - [Registrazione-Utente]- Update bpMsg");
                                    } catch (Exception ex) {
                                        logger.error("ALPENITE ConfirmRegistration THREAD TIPO_AMM_COND - ERROR: " + ex.getMessage());
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            };
                            thread.start();
                        }

                        //update j_Validato in db
                        userJahiaDB.updateDBProperty(connection, "j_validato", "true", usernameF);

                        //update j_accountLocked in db
                        userJahiaDB.updateDBProperty(connection, "j_accountLocked", "false", usernameF);

                        //update validato, accountLocked in jahia user
                        Thread thread2 = new Thread() {
                            public void run() {
                                try {
                                    JahiaUser user = userManagerService.lookupUser(usernameF);
                                    user.setProperty("j:validato", "true");
                                    user.setProperty("j:accountLocked", "false");
                                    logger.info("j:accountLocked: "+user.getProperty("j:accountLocked"));

                                    logger.info("ALPENITE ConfirmRegistration THREAD - Update validato, accountLocked");
                                } catch (Exception ex) {
                                    logger.error("ALPENITE NewUser THREAD - ERROR: " + ex.getMessage());
                                    Thread.currentThread().interrupt();
                                }
                            }
                        };
                        thread2.start();

                        logger.info("[Confirm-Registration]-Username: "+user.getUsername()+" new user created");
                    } else {
                        logger.info("[Confirm-Registration]-Username: "+user.getUsername()+"Messaggio non valido: Errore: UTENTE "+user.getUsername()+" NON VALIDATO");
                        //genericErrorMessageMail(parameters, "Errore:  UTENTE "+user.getUsername()+" NON VALIDATO");
                        WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
                        //return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
                    }

                    WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_VALIDATION, this.getClass().getName(), "return", new ArrayList<String>()), req.getSession());
                    logger.info("[Confirm-Registration]-redirectPage:");

                    risposta.put("Avviso elemento " + k, "Utente in posizione:" + k + " Salvato Correttamente");
                }
            } else {
                risposta.put("Errore elemento " + k, "rigaJson: " + k + " " + result);
            }
        }


        logger.info(userFromJson.toString());


        return new ActionResult(ActionResult.OK_JSON.getResultCode(), null, risposta);


    }


    private boolean isMessageValid(Message messaggio) {
        if (messaggio == null || messaggio.isAnError()) {
            return false;
        }
        return true;
    }

    public JahiaUserManagerService getUserManagerService() {
        return userManagerService;
    }


    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

}
