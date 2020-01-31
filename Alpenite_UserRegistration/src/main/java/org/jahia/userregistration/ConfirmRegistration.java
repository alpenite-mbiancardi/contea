package org.jahia.userregistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.userregistration.utils.UserJahiaDB;
import org.slf4j.Logger;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.datiAnagrafici.dati.DatiAnagrafici;
import com.alpenite.tea.sapMessages.dati.Message;

public class ConfirmRegistration extends Action {

	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfirmRegistration.class);
	private JahiaUserManagerService userManagerService;
    private MailService mailService;

	private static String USERNAME = "username";
	private static String CODE = "codice";
	private static String MAIL = "mail";
	private static String TEMPINFO = "tempInfo";
	private static String NUOVAMAIL = "j:nuovaMail";
	private static String CODICEVERIFICA = "j:codiceVerifica";
	private static String ADDMAIL = "addMail";

	private String username;

	public void setUserManagerService(JahiaUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {

    	logger.info("[Confirm-Registration]- Start operation");

    	if (StringUtils.isNotEmpty(parameters.get(org.jahia.userregistration.utils.Constants.USERNAME).get(0))) {
    	    this.username = parameters.get(org.jahia.userregistration.utils.Constants.USERNAME).get(0);
			final String usernameF = this.username;

			UserJahiaDB userJahiaDB = new UserJahiaDB();
			Connection connection = userJahiaDB.connectedDB();

            logger.info("ALPENITE ConfirmRegistration - check if username exist in db");
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from user_registration where username='" + this.username + "'");
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

			String nuovaMail = user.getJ_nuovaMail();
			String codiceVerifica = user.getJ_codiceVerifica();

			logger.info("ALPENITE ConfirmRegistration - nuovaMail: "+nuovaMail+" codiceVerifica: "+codiceVerifica+" mail: "+parameters.get(MAIL).get(0)+" codice: "+parameters.get(CODE).get(0));
			if(nuovaMail.equals(parameters.get(MAIL).get(0)) && codiceVerifica.equals(parameters.get(CODE).get(0))){
				logger.info("ALPENITE ConfirmRegistration - new mail");

				//update mail in db
				userJahiaDB.updateDBProperty(connection, "email", nuovaMail, this.username);

				//update nuovaMail in db
				userJahiaDB.updateDBProperty(connection, "j_nuovaMail", "", this.username);

				//update codiceVerifica in db
				userJahiaDB.updateDBProperty(connection, "j_codiceVerifica", "", this.username);

				final String nuovaMailF = nuovaMail;
				Thread thread = new Thread() {
					public void run() {
						try {
							JahiaUser user = userManagerService.lookupUser(usernameF);
							user.setProperty("j:email", nuovaMailF);
							user.setProperty(NUOVAMAIL, "");
							user.setProperty(CODICEVERIFICA, "");
							logger.info("ALPENITE ConfirmRegistration THREAD - [Registrazione-Utente]- update Email, nuovaMail, codiceVerifica");
						} catch (Exception ex) {
							logger.error("ALPENITE ConfirmRegistration THREAD - ERROR: " + ex.getMessage());
							Thread.currentThread().interrupt();
						}
					}
				};
				thread.start();

				logger.info("[Confirm-Registration]-mail validated");
				if(user.getJ_validato().equals("true")){
					// validation mail for update mail
					WSReturnManager.evaluate(WSClient.getClient().addEmail(user.getJ_bp(), nuovaMail), req.getSession());
					logger.info("[Confirm-Registration]- mail saved in SAP");
					return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
				}

			}else{
				WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_PARAMETRI_NON_CORRETTI, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
				logger.info("[Confirm-Registration]- Error path: "+session.getNodeByIdentifier(parameters.get("redirectPage").get(0)).getPath());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}
			if(user.getJ_validato().equals("false")) {
				// validation mail for the registration

				// SAP
				Message msg = null;
				//get tipoUtente
				String tipoUtente = user.getJ_tipoBP();

				if(tipoUtente.equals(Constants.TIPO_PRIVATO)){
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
						logger.info("TipoUtente TIPO_PRIVATO: "+tipoUtente);
						logger.info("[Confirm-Registration]- Username TIPO_PRIVATO: "+user.getUsername() + " TipoUtente: "+tipoUtente +" Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
						errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
					}
					// save the BP information

					//update bp in db
					userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), this.username);

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
				if(tipoUtente.equals(Constants.TIPO_AZIENDA)){

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
						logger.info("[Confirm-Registration]-Username TIPO_AZIENDA: "+user.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
						WSReturnManager.evaluate(message, req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
					}
					// save the BP information
					//update bp in db
					userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), this.username);

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
						errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
						WSReturnManager.evaluate(message, req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
					}
					WSClient.getClient().addEmail(msg.getBp(), nuovaMail);

					//update name in db
					userJahiaDB.updateDBProperty(connection, "nome", msg.getName(), this.username);

					//update lastName in db
					userJahiaDB.updateDBProperty(connection, "cognome", msg.getSurname(), this.username);

					//update bp in db
					userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), this.username);

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
							userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), this.username);

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
						errorMessageMail(datiAnagraficiInterlocutore, parameters, msg.getBp(), msg.getFirstMsg());
						WSReturnManager.evaluate(message, req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
					}
					//update bp in db
					PreparedStatement updateBp = connection.prepareStatement("update user_registration set j_bp= '" + msg.getBp() + "' where username= ? ");
					updateBp.setString(1, this.username);
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
						logger.info("TipoUtente TIPO_AMM_COND: "+tipoUtente);
						logger.info("Messaggio non valido TIPO_AMM_COND: "+msg.geteReturn()+" - "+msg.getFirstMsg());
						WSReturnManager.evaluate(message, req.getSession());
						return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
					}
					//update bp in db
					userJahiaDB.updateDBProperty(connection, "j_bp", msg.getBp(), this.username);

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
				userJahiaDB.updateDBProperty(connection, "j_validato", "true", this.username);

				//update j_accountLocked in db
				userJahiaDB.updateDBProperty(connection, "j_accountLocked", "false", this.username);

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

			}else{
				logger.info("[Confirm-Registration]-Username: "+user.getUsername()+"Messaggio non valido: Errore: UTENTE "+user.getUsername()+" NON VALIDATO");
				genericErrorMessageMail(parameters, "Errore:  UTENTE "+user.getUsername()+" NON VALIDATO");
				WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
			}

			logger.info("ALPENITE ConfirmRegistration - close Connection");
			userJahiaDB.closeConnection(connection);

        }else {
    	    logger.error("ALPENITE ConfirmRegistration - Parameter username is empty");
            return ActionResult.INTERNAL_ERROR_JSON;
        }


		WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_VALIDATION, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
		logger.info("[Confirm-Registration]-redirectPage: "+ parameters.get("redirectPage").get(0));

		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
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

    private void errorMessageMail(DatiAnagrafici datiAnagrafici,Map<String, List<String>> parameters,String Bp, String msg){

    	logger.info("Confirm-Registration]: invio mail al back-office per errore nella registrazione");

		String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
		String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
		String from = parameters.get("from")==null?"":parameters.get("from").get(0);
		String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
		String user_mail = parameters.get("mail")==null?"":parameters.get("mail").get(0);
		String backOffice = parameters.get("backOffice")==null?"":parameters.get("backOffice").get(0);
		String mailContent = parameters.get("mailContent")==null?"":parameters.get("mailContent").get(0);


		mailContent = mailContent.concat("<br />Dati Anagrafici: <br /><br />").concat("Business Partner: "+Bp.concat("<br />").concat("E-mail: "+user_mail).concat("<br />"
		+datiAnagrafici.toString()).concat("<br /> "+msg != null ? msg :""));

		mailService.sendHtmlMessage(from, backOffice, cc, bcc, subject, mailContent);
    }

 private void genericErrorMessageMail(Map<String, List<String>> parameters, String msg){

    	logger.info("Confirm-Registration]: invio mail al back-office per errore nella registrazione");

		String cc = parameters.get("cc")==null?"":parameters.get("cc").get(0);
		String bcc = parameters.get("bcc")==null?"":parameters.get("bcc").get(0);
		String from = parameters.get("from")==null?"":parameters.get("from").get(0);
		String subject = parameters.get("subject")==null?"":parameters.get("subject").get(0);
		String user_mail = parameters.get("mail")==null?"":parameters.get("mail").get(0);
		String backOffice = parameters.get("backOffice")==null?"":parameters.get("backOffice").get(0);
		String mailContent = parameters.get("mailContent")==null?"":parameters.get("mailContent").get(0);


		mailContent = mailContent.concat("E-mail: "+user_mail).concat("<br /> "+msg != null ? msg :"");

		mailService.sendHtmlMessage(from, backOffice, cc, bcc, subject, mailContent);
    }

}
