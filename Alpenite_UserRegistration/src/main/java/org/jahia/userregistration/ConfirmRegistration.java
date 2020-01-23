package org.jahia.userregistration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.PathNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	public void setUserManagerService(JahiaUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
	
    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource,
            JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
    	
    	logger.info("[Confirm-Registration]- Start operation");	
    	// get the user
    	JahiaUser user = userManagerService.lookupUser(parameters.get(USERNAME).get(0));
    	if(user == null){
    		return ActionResult.INTERNAL_ERROR_JSON;
    	}
    	
    	// mail validation
    	// get new value
    	String nuovaMail = user.getProperty(NUOVAMAIL);
    	String codiceVerifica = user.getProperty(CODICEVERIFICA);
    	// verify new mail
    	logger.info("[Confirm-Registration]- nuovaMail: "+nuovaMail+" codiceVerifica: "+codiceVerifica+" mail: "+parameters.get(MAIL).get(0)+" codice: "+parameters.get(CODE).get(0));
    	if(nuovaMail.equals(parameters.get(MAIL).get(0)) && codiceVerifica.equals(parameters.get(CODE).get(0))){
    		logger.info("[Confirm-Registration]-new mail");
    		user.setProperty("j:email", nuovaMail);
    		user.setProperty(NUOVAMAIL, "");
    		user.setProperty(CODICEVERIFICA, "");
    		logger.info("[Confirm-Registration]-mail validated");
    		if(user.getProperty("j:validato").equals("true")){
    			// validation mail for update mail
    		 WSReturnManager.evaluate(WSClient.getClient().addEmail(user.getProperty("j:bp"), nuovaMail), req.getSession());
    			logger.info("[Confirm-Registration]- mail saved in SAP");  			
    			return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    		}
    	}else{
    		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_PARAMETRI_NON_CORRETTI, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
    		logger.info("[Confirm-Registration]- Error path: "+session.getNodeByIdentifier(parameters.get("redirectPage").get(0)).getPath());
    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    	}
    	if(user.getProperty("j:validato").equals("false")){
    		// validation mail for the registration
    		// get tempInfo
    		JCRNodeWrapper tempInfo = null;
        	try{
        		tempInfo = (session.getNode(user.getLocalPath())).getNode(TEMPINFO);
        	}catch(PathNotFoundException e){
        		if(user.getProperty("j:validato").equals("false"))
        			return ActionResult.INTERNAL_ERROR_JSON;
        	}
    		// SAP
    		Message msg = null;
    		//get tipoUtente
    		String tipoUtente = user.getProperty("j:tipoBP");
    		
    		// switch
    		if(tipoUtente.equals(Constants.TIPO_PRIVATO)){
    			DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
    					null, 
    					"P", 
    					null, 
    					null, 
    					null, 
    					null, 
    					user.getProperty("j:firstName"), 
    					user.getProperty("j:lastName"), 
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
    			WSReturn<Message> message = WSClient.getClient().creaProspect(user.getUsername(), nuovaMail, datiAnagrafici, null);
    			msg = message.getRitorno();
    			if(!isMessageValid(msg)){
    				WSReturnManager.evaluate(message, req.getSession());
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("[Confirm-Registration]- Username: "+user.getUsername() + " TipoUtente: "+tipoUtente +" Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
    				errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
    				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    			}
    			// save the BP information
        		user.setProperty("j:bp",msg.getBp());
    		}
    		if(tipoUtente.equals(Constants.TIPO_AZIENDA)){

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
						user.getProperty("j:firstName"), 
						user.getProperty("j:lastName"), 
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
    				logger.info("[Confirm-Registration]-Username: "+user.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
    				WSReturnManager.evaluate(message, req.getSession());
    				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    			}
    			// save the BP information
    			user.setProperty("j:bp",msg.getBp());
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
    					user.getUsername(),
    					nuovaMail, 
    					tempInfo.getPropertyAsString("j:codice_fiscale"),
    					null,
    					datiAnagrafici,
    					null,
    					false);
    			msg = message.getRitorno();
    			if(!isMessageValid(msg)){
    				logger.info("[Confirm-Registration]-Username: "+user.getUsername()+"TipoUtente: "+tipoUtente+"Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
    				errorMessageMail(datiAnagrafici, parameters, msg.getBp(), msg.getFirstMsg());
    				WSReturnManager.evaluate(message, req.getSession());
    				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    			}
    			WSClient.getClient().addEmail(msg.getBp(), nuovaMail);
    			user.setProperty("j:firstName", msg.getName());
    			user.setProperty("j:lastName", msg.getSurname());
    			user.setProperty("j:bp",msg.getBp());
    		}
			if(tipoUtente.equals(Constants.TIPO_AZ_CLI)){
				DatiAnagrafici datiAnagraficiInterlocutore = new DatiAnagrafici(
						null,
						"I",
						null,
						null,
						null,
						null,
						user.getProperty("j:firstName"),
						user.getProperty("j:lastName"),
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
    					user.getUsername(),
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
    					user.setProperty("j:bp", msg.getBp());
    				errorMessageMail(datiAnagraficiInterlocutore, parameters, msg.getBp(), msg.getFirstMsg());
    				WSReturnManager.evaluate(message, req.getSession());
    				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    			}	
    			user.setProperty("j:bp", msg.getBp());
			}
			if(tipoUtente.equals(Constants.TIPO_AMM_COND)){
				DatiAnagrafici datiAnagrafici = new DatiAnagrafici(
						null,
						"AM",
						user.getProperty("j:firstName"),
						user.getProperty("j:lastName"),
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
						user.getUsername(),
						nuovaMail, 
						tempInfo.getPropertyAsString("j:codice_fiscale"),
						tempInfo.getPropertyAsString("j:partita_iva"),
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
    				logger.info("TipoUtente: "+tipoUtente);
    				logger.info("Messaggio non valido: "+msg.geteReturn()+" - "+msg.getFirstMsg());
    				WSReturnManager.evaluate(message, req.getSession());
    				return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
    			}
				user.setProperty("j:bp", msg.getBp());
			}
			user.setProperty("j:validato", "true");
			user.setProperty("j:accountLocked", "false");
			logger.info("j:accountLocked: "+user.getProperty("j:accountLocked"));
			JCRNodeWrapper userNode = session.getNode(user.getLocalPath());
			userNode.saveSession();
			session.save();
			logger.info("[Confirm-Registration]-Username: "+user.getUsername()+" new user created");
    	}else{
    		logger.info("[Confirm-Registration]-Username: "+user.getUsername()+"Messaggio non valido: Errore: UTENTE "+user.getUsername()+" NON VALIDATO");
    		genericErrorMessageMail(parameters, "Errore:  UTENTE "+user.getUsername()+" NON VALIDATO");
    		WSReturnManager.evaluate(new WSReturn<String>(Constants.ERRORE_GENERICO, this.getClass().getName(), "return" , new ArrayList<String>()), req.getSession());
    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("redirectPage").get(0))).getPath());
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
