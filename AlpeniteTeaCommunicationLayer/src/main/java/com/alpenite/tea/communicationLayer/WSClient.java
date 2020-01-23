package com.alpenite.tea.communicationLayer;



import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.log4j.Logger;

import com.alpenite.tea.communicationLayer.data.Comune;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.File;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.communicationLayer.ws.crm.ZSNCRMPORTAL;
import com.alpenite.tea.communicationLayer.ws.crm.ZWSCRMPORTAL;
import com.alpenite.tea.communicationLayer.ws.crm.ZbinariesS;
import com.alpenite.tea.communicationLayer.ws.crm.ZbinariesT;
import com.alpenite.tea.communicationLayer.ws.crm.ZptStBpData;
import com.alpenite.tea.communicationLayer.ws.crm.ZptStCond;
import com.alpenite.tea.communicationLayer.ws.crm.ZptStDc;
import com.alpenite.tea.communicationLayer.ws.crm.ZptStRecapito;
import com.alpenite.tea.communicationLayer.ws.crm.ZptStSr;
import com.alpenite.tea.communicationLayer.ws.crm.ZptTtBpData;
import com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess;
import com.alpenite.tea.communicationLayer.ws.isu.ZSNISUPORTAL;
import com.alpenite.tea.communicationLayer.ws.isu.ZWSISUPORTAL;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStBidoni;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStCompanies;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStComuni;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStContract;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStLett;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStList;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStList2;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStRiduzioni;
import com.alpenite.tea.communicationLayer.ws.isu.ZptStVktyp;
import com.alpenite.tea.communicationLayer.ws.isu.ZptTtCompanies;
import com.alpenite.tea.communicationLayer.ws.isu.ZptTtLett;
import com.alpenite.tea.communicationLayer.ws.isu.ZptTtList2;
import com.alpenite.tea.communicationLayer.ws.isu.ZptTtVktyp;
import com.alpenite.tea.condomini.dati.Condominio;
import com.alpenite.tea.datiAnagrafici.dati.DatiAnagrafici;
import com.alpenite.tea.datiCatastali.dati.DatiCatastali;
import com.alpenite.tea.dettagliContratto.dati.DettagliContrattoAcqua;
import com.alpenite.tea.dettagliContratto.dati.DettagliContrattoAmbiente;
import com.alpenite.tea.dettagliContratto.dati.DettagliContrattoGas;
import com.alpenite.tea.dettagliContratto.dati.DettagliContrattoTeleriscaldamento;
import com.alpenite.tea.dettagliContratto.dati.DettagliContrattoElettricita;
import com.alpenite.tea.document.dati.DocumentRequest;
import com.alpenite.tea.document.dati.DocumentResponse;
import com.alpenite.tea.domiciliazioneBancaria.dati.DomiciliazioneBancaria;
import com.alpenite.tea.elencoContratti.dati.Contratto;
import com.alpenite.tea.elencoDocumenti.dati.Documento;
import com.alpenite.tea.letture.dati.Lettura;
import com.alpenite.tea.recapito.dati.Recapito;
import com.alpenite.tea.sapMessages.dati.Message;
import com.alpenite.tea.serviceRequest.dati.ServiceRequest;
import com.alpenite.tea.svuotamenti.dati.Bidone;
import com.alpenite.tea.svuotamenti.dati.Sacchetti;
import com.alpenite.tea.svuotamenti.dati.Svuotamento;

/**
 * Questa classe rappresenta il client che permette di interagire con i web
 * services dai quali reperire ed inviare informazioni.
 * 
 * All'interno del file config.ini, è possibile trovare i paramentri di configurazione
 * necessari per il corretto funzionamento della classe.
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class WSClient {

    /**
     * l'unica istanza possibile del client
     */
    private static WSClient client;

    /**
     * Crea e torna una nuova istanza del client che permette l'interazione con i
     * web services, legata a ad un bp utente e ad uno cliente
     * 
     * @param bpCliente il bp del cliente tea
     * @param bpUtenza il bp dell'utenza del portale
     * @throws IOException se non e'possibile leggere il file di configurazione
     * @throws URISyntaxException se l'url del wsdl non e'corretto
     */    
    public static WSClient getClient(String bpUtenza, String bpCliente) throws IOException, URISyntaxException {
        // se entrambi i bp sono nulli torna l'istanza del client per l'utente anonimo
        if (bpUtenza == null && bpCliente == null) {
            return WSClient.getClient();
        }
        return new WSClient(bpCliente, bpUtenza);
    }

    /**
     * Crea e torna un'istanza del client che permette l'interazione con i
     * web services, legata ad un bp
     * 
     * @param bp il bp dell'utente privato o azienda
     * @throws IOException se non e'possibile leggere il file di configurazione
     * @throws URISyntaxException se l'url del wsdl non e'corretto
     */    
    public static WSClient getClient(String bp) throws Exception {
        // se il bp è nullo torna l'istanza del client per l'utente anonimo
        if (bp == null) {
            return WSClient.getClient();
        }
        return new WSClient(bp);
    }
    
    /**
     * Crea e torna l'unica istanza del client che permette l'interazione con i
     * web services per l'utente anonimo, che non puo'quindi utilizzare i
     * metodi che richiedono l'uso di un bp
     * 
     * @throws IOException se non e'possibile leggere il file di configurazione
     * @throws URISyntaxException se l'url del wsdl non e'corretto
     */
    public static WSClient getClient() throws IOException, URISyntaxException {
        if (client == null) {
            client = new WSClient();
        }
        return client;
    }
    
    /**
     * Torna l'istanza del client relativo alla sessione in corso
     * 
     * @param session la sessione in corso
     * @return il client
     * @throws Exception se la sessione non è inizializzata, il client non esiste o è del tipo sbagliato
     */
    public static WSClient getClient(HttpSession session) {
    																																																																																																																																																																																																																																																																																																																																																																																																																																																																												
    	if (session == null)
            throw new IllegalArgumentException("La sessione non e' inizializzata");
        if (session.getAttribute("WSCLIENT") == null)
            throw new NullPointerException("Il client non e' stato correttamente inizializzato per questa sessione. Eseguire di nuovo il login al sistema");
        if (session.getAttribute("WSCLIENT") instanceof WSClient) {
            return (WSClient)session.getAttribute("WSCLIENT");
        }
        throw new IllegalArgumentException("L'attributo della sessione non contiene un client valido");
    }

    /**
     * Crea e torna un'istanza del client che permette l'interazione con i
     * web services, legata a ad un bp utente e ad uno cliente
     * 
     * @param bpCliente il bp del cliente tea
     * @param bpUtenza il bp dell'utenza del portale
     * @throws IOException se non e'possibile leggere il file di configurazione
     * @throws URISyntaxException se l'url del wsdl non e'corretto
     */  
    private WSClient(String bpCliente, String bpUtenza) throws IOException, URISyntaxException {

        this.bpCliente = bpCliente;
        this.bpUtenza = bpUtenza;
        
        // leggo dal file di configurazione i parametri di connessione
        // ai due web services e la lingua predefinita
        InputStream is = getClass().getClassLoader().getResourceAsStream(nomeFileConfigurazione);
        Properties p = new Properties();
        p.load(is);
        usernameISU = p.getProperty("usernameISU");
        passwordISU = p.getProperty("passwordISU");
        usernameCRM = p.getProperty("usernameCRM");
        passwordCRM = p.getProperty("passwordCRM");
        linguaPredefinita= p.getProperty("linguaPredefinita");

        // configuro le classi che permettono l'accesso al ws isu
        serviceISU = new ZSNISUPORTAL(
                getClass().getClassLoader().getResource(
                    p.getProperty("pathWSDLISU")), 
                SERVICE_NAME_ISU);
        portISU = serviceISU.getZbnIsuPortal();
        
        // configuro le classi che permettono l'accesso al ws crm
        serviceCRM = new ZSNCRMPORTAL(
                getClass().getClassLoader().getResource(
                    p.getProperty("pathWSDLCRM")), 
                SERVICE_NAME_CRM);
        portCRM = serviceCRM.getZbnCrmPortal();
        
        // imposto la lingua predefinita
        setLingua(linguaPredefinita);
        
        // eseguo l'accesso al ws isu
        BindingProvider bpISU = (BindingProvider) portISU;
        bpISU.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, usernameISU);
        bpISU.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, passwordISU);
        
        // eseguo l'accesso al ws crm
        BindingProvider bpCRM = (BindingProvider) portCRM;
        bpCRM.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, usernameCRM);
        bpCRM.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, passwordCRM);
    }
    
    /**
     * Crea e torna un'istanza del client che permette l'interazione con i
     * web services, legata ad un bp
     * 
     * @param bp il bp dell'utente privato o azienda
     * @throws IOException se non e'possibile leggere il file di configurazione
     * @throws URISyntaxException se l'url del wsdl non e'corretto
     */
    private WSClient(String bp) throws IOException, URISyntaxException {
        this(bp, bp);
    }
    
    /**
     * Crea e torna un'istanza del client che permette l'interazione con i
     * web services per l'utente anonimo, che non puo'quindi utilizzare i
     * metodi che richiedono l'uso di un bp
     * 
     * @param bp il bp dell'utente privato o azienda
     * @throws IOException se non e'possibile leggere il file di configurazione
     * @throws URISyntaxException se l'url del wsdl non e'corretto
     */
    private WSClient() throws IOException, URISyntaxException {
        this(null);
    }
    
    private static final QName SERVICE_NAME_ISU = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZSN_ISU_PORTAL");
    private static final QName SERVICE_NAME_CRM = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZSN_CRM_PORTAL");
    
    /**
     * il nome del file di configurazione
     */
    private String nomeFileConfigurazione = "config.ini";
    
    /**
     * il codice della lingua predefinita
     */
    private String linguaPredefinita;
    
    /**
     * l'username con il quale accedere al ws ISU
     */
    private String usernameISU;
    
    /**
     * la password con la quale accedere al ws ISU
     */
    private String passwordISU;
    
    /**
     * l'username con il quale accedere al ws CRM
     */
    private String usernameCRM;
    
    /**
     * la password con la quale accedere al ws CRM
     */
    private String passwordCRM;
    
    private ZSNISUPORTAL serviceISU;
    private ZWSISUPORTAL portISU;
    
    private ZSNCRMPORTAL serviceCRM;
    private ZWSCRMPORTAL portCRM;
    
    /**
     * il BP del cliente tea
     */
    private String bpCliente;
    
    /**
     * il BP dell'utenza del portale
     */
    private String bpUtenza;
    
    /**
     * la lingua attualmente selezionata dall'utente
     */
    private String lingua;

    /** il percorso dell'endpoint prelevato dalla configurazione */
    private String isuEndpointAddress;
    
    /** il percorso dell'endpoint prelevato dalla configurazione */
    private String crmEndpointAddress;
    
    /**
     * Torna il BP del cliente tea
     * @param bpCliente il BP del cliente tea
     */    
    public String getBpCliente() {
        return bpCliente;
    }

    /**
     * Imposta il BP del cliente tea
     * @return il BP del cliente tea
     */
    public void setBpCliente(String bpCliente) {
        this.bpCliente = bpCliente;
    }

    /**
     * Torna la lingua attualmente selezionata dall'utente
     * @return la lingua attualmente selezionata dall'utente
     */
    public String getLingua() {
        return lingua;
    }

    /**
     * Torna il BP dell'utenza del portale
     * @return il BP dell'utenza del portale
     */
    public String getBpUtenza() {
        return bpUtenza;
    }

    /**
     * Imposta il BP dell'utenza del portale
     * @param bpUtenza il BP dell'utenza del portale
     */
    public void setBpUtenza(String bpUtenza) {
        this.bpUtenza = bpUtenza;
    }
    

    /**
     * Scrive nel log un messaggio di debug
     * @param nomeFunzione il nome della funzione
     * @param args gli argomenti
     */
    private void debug(String nomeFunzione, Object... args) {
        StringBuilder sb = new StringBuilder(nomeFunzione.concat(": "));
        if (args != null) {
            for (Object string : args) {
                if (string != null) {
                    sb.append(string.toString().concat(", "));
                }
            }
        }
        Logger.getLogger(getClass()).info(sb.toString());
    }

    /**
     * Scrive nel log i messaggi tornati dai web service
     * @param nomeFunzione il nome della funzione
     * @param messaggi la lista dei messaggi
     */
    private void debugMsg(String nomeFunzione, List messaggi) {
        debug(nomeFunzione, getMessaggiWS(messaggi));
    }
    
    /**
     * Data una lista di messaggi, torna una stringa composta da piu'righe
     * contenenti i messaggi
     * @param msg la lista dei messaggi
     * @return la stringa contenente i messaggi
     */
    private String getMessaggiWS(List msg) {
        if (msg == null) 
            return "";
        StringBuilder sb = new StringBuilder();
        for(Object o : msg) {
            // tramite reflection chiamo il metodo getMessage
            if (o != null) {
                try {
                    Method m = o.getClass().getMethod("getMessage", new Class[0]);
                    sb.append(m.invoke(o, new Object[0])).append("<br/>");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                
        }
        return sb.toString();
    }
    
    /*
     * DEFINIZIONE DEI SERVIZI DEL CLIENT
     */
    
    /**
     * Richiede al web service i dettagli di un contratto
     * 
     * @param bp il bp dell'utente
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @param riduzioni una lista vuota che verra'riempita da questo metodo
     * @return l'oggetto tornato dal web service
     */
    private WSReturn<ZptStInfo> getDettagliContratto(String bp, String contratto, String cc, List<ZptStRiduzioni> riduzioni) {
        debug("getDettagliContratto", bp, contratto, checkLenght(cc, Constants.DIM_CC, true));
        
        java.lang.String _zptContractDetail_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptContractDetail_iFlagDc = "";
        java.lang.String _zptContractDetail_iFlagInfo = "X";
        java.lang.String _zptContractDetail_iFlagPod = "";
        java.lang.String _zptContractDetail_iPartner = bp;
        java.lang.String _zptContractDetail_iVertrag = checkLenght(contratto, Constants.DIM_C, true);
        javax.xml.ws.Holder<java.lang.String> _zptContractDetail_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStDc> _zptContractDetail_esDc = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStDc>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo> _zptContractDetail_esInfo = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStPod> _zptContractDetail_esPod = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStPod>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptContractDetail_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtRiduzioni> _zptContractDetail_etRiduzioni = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtRiduzioni>();
        portISU.zptContractDetail(_zptContractDetail_iCc, _zptContractDetail_iFlagDc, _zptContractDetail_iFlagInfo, _zptContractDetail_iFlagPod, _zptContractDetail_iPartner, _zptContractDetail_iVertrag, _zptContractDetail_eReturn, _zptContractDetail_esDc, _zptContractDetail_esInfo, _zptContractDetail_esPod, _zptContractDetail_etMess, _zptContractDetail_etRiduzioni);

        debug("zptContractDetail._zptContractDetail_eReturn=", _zptContractDetail_eReturn.value);
        debug("zptContractDetail._zptContractDetail_esDc=", _zptContractDetail_esDc.value);
        debug("zptContractDetail._zptContractDetail_esInfo=", _zptContractDetail_esInfo.value);
        debug("zptContractDetail._zptContractDetail_esPod=", _zptContractDetail_esPod.value);
        debugMsg("zptContractDetail._zptContractDetail_etMess=", _zptContractDetail_etMess.value.getItem());
        debug("zptContractDetail._zptContractDetail_etRiduzioni=", _zptContractDetail_etRiduzioni.value);
        
        if (riduzioni != null && _zptContractDetail_etRiduzioni.value != null){
        	for(ZptStRiduzioni red : _zptContractDetail_etRiduzioni.value.getItem())
        		riduzioni.add(red) ;
        }
        
//        return _zptContractDetail_esInfo.value;
        return new WSReturn<ZptStInfo>(_zptContractDetail_eReturn.value, "getDettagliContratto", _zptContractDetail_esInfo.value, _zptContractDetail_etMess.value.getItem());
    }
    
    /**
     * Richiede al web service i dettagli di un contratto
     * 
     * @param bp il bp dell'utente
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @return l'oggetto tornato dal web service
     */    
    private WSReturn<ZptStInfo> getDettagliContratto(String bp, String contratto, String cc) {    
        return getDettagliContratto(bp, contratto, cc, null);
    }
    
    /**
     * Richiede al web service le letture o gli svuotamenti
     * 
     * @param bp il bp dell'utente
     * @param contratto il contratto
     * @param lettura se true torna le letture, se false gli svuotamenti
     * @param anno l'anno
     * @return l'oggetto tornato dal web service
     */
    /**
     * Richiede al web service le letture o gli svuotamenti
     * 
     * @param bp il bp dell'utente
     * @param contratto il contratto
     * @param lettura se true torna le letture, se false gli svuotamenti
     * @param anno l'anno
     * @return l'oggetto tornato dal web service
     */
    private WSReturn<ZptTtLett> getLetturaSvuotamento(String bp, String contratto, boolean lettura, String anno) {
        // se l'anno non è indicato imposto l'anno corrente
        
    	if(lettura) {
    		String startDate=anno+"-01-01";
    		String endtDate= anno +"-12-31";
    		return getLetturaSvuotamento(bp, contratto,"1", startDate,endtDate);
        }
    	else {
    	return null;
    	}
    }
    
    
    private WSReturn<ZptTtLett> getLetturaSvuotamento(String bp, String contratto, String lettura, String startData,String endData) {
    
    	
        
        debug("getLetturaSvuotamento", bp, contratto, startData,endData);        
        
        java.lang.String _zptLettureSvuotamenti_iFlagLetture = lettura=="1"?"X":"";
        java.lang.String _zptLettureSvuotamenti_iFlagSvuota = lettura=="2"?"X":"";
        java.lang.String _zptLettureSvuotamenti_iFlagSacchetti = lettura=="3"?"X":"";
        java.lang.String _zptLettureSvuotamenti_iPartner = bp;
        java.lang.String _zptLettureSvuotamenti_iVertrag = checkLenght(contratto, Constants.DIM_C, true);;
        java.lang.String _zptLettureSvuotamenti_ia=endData;
        java.lang.String _zptLettureSvuotamenti_iDa=startData;
        javax.xml.ws.Holder<java.lang.String> _zptLettureSvuotamenti_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtLett> _zptLettureSvuotamenti_etLett = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtLett>();
        Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptLettureSvuotamenti_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        portISU.zptLettureSvuotamenti(
        		_zptLettureSvuotamenti_ia, 
        		_zptLettureSvuotamenti_iDa,
        		_zptLettureSvuotamenti_iFlagLetture,
        		_zptLettureSvuotamenti_iFlagSacchetti,
        		_zptLettureSvuotamenti_iFlagSvuota, 
        		_zptLettureSvuotamenti_iPartner,
        		_zptLettureSvuotamenti_iVertrag,
        		_zptLettureSvuotamenti_eReturn,
        		_zptLettureSvuotamenti_etLett,
        		_zptLettureSvuotamenti_etMess);
        
        debug("zptLettureSvuotamenti._zptLettureSvuotamenti_eReturn=", _zptLettureSvuotamenti_eReturn.value);
        debug("zptLettureSvuotamenti._zptLettureSvuotamenti_etLett=", _zptLettureSvuotamenti_etLett.value);
        debugMsg("zptLettureSvuotamenti._zptLettureSvuotamenti_etMess=", _zptLettureSvuotamenti_etMess.value.getItem());
           	
    	
    	return new WSReturn<ZptTtLett>(_zptLettureSvuotamenti_eReturn.value, "getLetturaSvuotamento", _zptLettureSvuotamenti_etLett.value, _zptLettureSvuotamenti_etMess.value.getItem());
        }
//    
//    private WSReturn<ZptTtBidoni> getBidoni(String bp, String contratto, boolean lettura, String anno) {
//        // se l'anno non è indicato imposto l'anno corrente
//        if (anno==null || anno.isEmpty()) {
//            anno = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
//        }
//        
//        debug("getLetturaSvuotamento", bp, contratto, anno);        
//        
//        java.lang.String _zptLettureSvuotamenti_iFlagLetture = lettura?"X":"";
//        java.lang.String _zptLettureSvuotamenti_iFlagSvuota = lettura?"":"X";
//        java.lang.String _zptLettureSvuotamenti_iFlagSacchetti = lettura?"":"X";
//        java.lang.String _zptBidoni_iPartner = bp;
//        java.lang.String __zptBidoni_iPartneri_iVertrag = checkLenght(contratto, Constants.DIM_C, true);;
//        javax.xml.ws.Holder<java.lang.String> _zptBidoni_eReturn = new javax.xml.ws.Holder<java.lang.String>();
//        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtLett> _zptBidoni_etLett = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtLett>();
//        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptBidoni_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
//        Holder<ZptTtBidoni> _zptBidoni_etBidoni = new Holder<ZptTtBidoni>();
//		portISU.zpt   (_zptBidoni_iPartner, __zptBidoni_iPartneri_iVertrag, _zptBidoni_eReturn, _zptBidoni_etBidoni, _zptBidoni_etMess);
//       
//        
//        
//        debug("zptLettureSvuotamenti._zptLettureSvuotamenti_eReturn=", _zptBidoni_eReturn.value);
//        debug("zptLettureSvuotamenti.__zptBidoni_etBidoni=", _zptBidoni_etBidoni.value);
//        debugMsg("zptLettureSvuotamenti.__zptBidoni_etMess=", _zptBidoni_etMess.value.getItem());
//        
////        return _zptLettureSvuotamenti_etLett.value;
//        return new WSReturn<ZptTtBidoni>(_zptBidoni_eReturn.value ,"getBidoni", _zptBidoni_etBidoni.value, _zptBidoni_etMess.value.getItem());
//    }
//    
//    
    
    
    
    
    /**
     * Imposta i dati anagrafici relativi ad un interlocutore o ad un
     * amministratore di condominio
     * 
     * @param bp il bp dell'utente
     * @param datiAnagrafici i dati anagrafici
     * @return un messaggio tornato dal web service
     */
    private WSReturn setDatiAnagraficiIntAmm(String bp, DatiAnagrafici datiAnagrafici ) {
        debug("setDatiAnagraficiIntAmm", bp, datiAnagrafici, datiAnagrafici.getPrefissoTel());
        
        /* integrity check */
        	if((datiAnagrafici.getTipoBP().equals(Constants.AMM_COND) || datiAnagrafici.getTipoBP().equals(Constants.AZIENDA)) && datiAnagrafici.getRagioneSociale1().equals("")){
        		return new WSReturn<String>(Constants.ERRORE_CAMPI_OBBLIGATORI, "setDatiAnagraficiIntAmm", "return" , new ArrayList<String>());
        	}
        	if(datiAnagrafici.getTipoBP().equals(Constants.PERSONA) && (datiAnagrafici.getNome().equals("") || datiAnagrafici.getCognome().equals(""))){
        		return new WSReturn<String>(Constants.ERRORE_CAMPI_OBBLIGATORI, "setDatiAnagraficiIntAmm", "return" , new ArrayList<String>());
        	}
        /* end check */
        
		java.lang.String _zptBpChange_iJahiaType = datiAnagrafici.getTipoBP();
        java.lang.String _zptBpChange_iPartner = bp;
        com.alpenite.tea.communicationLayer.ws.crm.ZptTtBpData _zptBpChange_itBpData = new ZptTtBpData();
        ZptStBpData dati = new ZptStBpData();
        dati.setBpType(toUpperCase(datiAnagrafici.getTipoBP()));
        String cel = datiAnagrafici.getPrefissoCel()!=null?datiAnagrafici.getPrefissoCel():"+39";
        cel = cel.concat("-");
        if(datiAnagrafici.getCellulare()==null || datiAnagrafici.getCellulare().equals(""))
        	cel="";
        else
        	cel = cel.concat(datiAnagrafici.getCellulare());
        dati.setCellphone(cel);
        dati.setCf(toUpperCase(datiAnagrafici.getCodiceFiscale().toUpperCase()));
        dati.setCity1(toUpperCase(datiAnagrafici.getComune()));
        dati.setCity2(toUpperCase(datiAnagrafici.getFrazione()));
        dati.setCountry(toUpperCase(datiAnagrafici.getPaese()));
        dati.setCap(toUpperCase(datiAnagrafici.getCap()));
        dati.setHousenum(toUpperCase(datiAnagrafici.getNumeroCivico()));
        dati.setHousenum2(toUpperCase(datiAnagrafici.getEstenzioneNumeroCivico()));
        dati.setName(toUpperCase(datiAnagrafici.getNome()));
        dati.setNameOrg1(toUpperCase(datiAnagrafici.getRagioneSociale1()));
        dati.setNameOrg2(toUpperCase(datiAnagrafici.getRagioneSociale2()));
        dati.setNameOrg3(toUpperCase(datiAnagrafici.getRagioneSociale3()));
        dati.setNameOrg4(toUpperCase(datiAnagrafici.getRagioneSociale4()));
        dati.setPartnerNumber(toUpperCase(datiAnagrafici.getCodiceBP()));
        dati.setPi(toUpperCase(datiAnagrafici.getPartitaIVA()));
        //dati.setPrvcy(datiAnagrafici.getPrivacy());
        dati.setPrvcy("X");
        dati.setPrvcyEmail(toUpperCase(datiAnagrafici.getPrivacyEmail()));
        dati.setPrvcyMail(toUpperCase(datiAnagrafici.getPrivacyMail()));
        dati.setPrvcyTel(toUpperCase(datiAnagrafici.getPrivacyTel()));
        dati.setRegion(toUpperCase(datiAnagrafici.getProvincia()));
        dati.setStreet(toUpperCase(datiAnagrafici.getVia()));
        dati.setSurname(toUpperCase(datiAnagrafici.getCognome()));
        String tel = datiAnagrafici.getPrefissoTel()!=null?datiAnagrafici.getPrefissoTel():"+39";
        tel = tel.concat("-");
        if(datiAnagrafici.getTelefono()==null || datiAnagrafici.getTelefono().equals("")){
        	tel = "";
        }else{
        	tel = tel.concat(datiAnagrafici.getTelefono());
        }
        dati.setTelephone(tel);
        dati.setRole(toUpperCase(datiAnagrafici.getRuolo()));
        _zptBpChange_itBpData.getItem().add(dati);
        javax.xml.ws.Holder<java.lang.String> _zptBpChange_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptBpChange_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptBpChange(_zptBpChange_iJahiaType,_zptBpChange_iPartner, _zptBpChange_itBpData, _zptBpChange_eReturn, _zptBpChange_etMess);

        debug("zptBpChange._zptBpChange_eReturn=", _zptBpChange_eReturn.value);
        debugMsg("zptBpChange._zptBpChange_etMess=", _zptBpChange_etMess.value.getItem());

//        return getMessaggiWS(_zptBpChange_etMess.value.getItem());   
        return new WSReturn<Object>(_zptBpChange_eReturn.value, "setDatiAnagraficiIntAmm", null, _zptBpChange_etMess.value.getItem());
    }
    
    /**
     * Apre una service request che permette di cambiare i dati anagrafici
     * per un privato, azienda o ente
     * 
     * @param bp il bp dell'utente
     * @param datiAnagrafici i dati anagrafici
     * @return un messaggio tornato dal web service
     */
    private WSReturn setDatiAnagraficiPAE(String bp, DatiAnagrafici datiAnagrafici, String jahiaType) {
        debug("setDatiAnagraficiPAE", bp, datiAnagrafici, this.bpUtenza, datiAnagrafici.getNumeroCivico(), datiAnagrafici.getEstenzioneNumeroCivico());
		
        /* integrity check */
        if(datiAnagrafici.getTipoBP().equals(Constants.PERSONA) && (datiAnagrafici.getNome().equals("") || datiAnagrafici.getCognome().equals(""))){
    		return new WSReturn<String>(Constants.ERRORE_CAMPI_OBBLIGATORI, "setDatiAnagraficiIntAmm", "return" , new ArrayList<String>());
    	}
    	if(datiAnagrafici.getTipoBP().equals(Constants.AZIENDA) && datiAnagrafici.getRagioneSociale1().equals("")){
    		return new WSReturn<String>(Constants.ERRORE_CAMPI_OBBLIGATORI, "setDatiAnagraficiIntAmm", "return" , new ArrayList<String>());
    	}
    	/* end check */
        
        String _zptServiceRequestCreate_iCateg1 = "";
        String _zptServiceRequestCreate_iCateg2 = "";
        String _zptServiceRequestCreate_iInterloc = this.bpUtenza;
        String _zptServiceRequestCreate_iNumciv = "";
        String _zptServiceRequestCreate_iNumcivExtension=datiAnagrafici.getEstenzioneNumeroCivico() ; //Modifica Numero Civico
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = "";
        String _zptServiceRequestCreate_iTypSr = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_DATI_ANAGRAFICI;
        String _zptServiceRequestCreate_iVkont = "";
        String _zptServiceRequestCreate_iVertrag = "";
		String _zptServiceRequestCreate_iJahiaType = jahiaType;
        ZptStBpData dati = new ZptStBpData();
        dati.setBpType(toUpperCase(datiAnagrafici.getTipoBP()));
        String cel = datiAnagrafici.getPrefissoCel()!=null?datiAnagrafici.getPrefissoCel():"+39";
        cel = cel.concat("-");
        if(datiAnagrafici.getCellulare()==null || datiAnagrafici.getCellulare().equals(""))
        	cel="";
        else
        	cel = cel.concat(datiAnagrafici.getCellulare());
        dati.setCellphone(cel);
        //dati.setCellphone(datiAnagrafici.getCellulare());
        dati.setCf(toUpperCase(datiAnagrafici.getCodiceFiscale().toUpperCase()));
        dati.setCity1(toUpperCase(datiAnagrafici.getComune()));
        dati.setCity2(toUpperCase(datiAnagrafici.getFrazione()));
        dati.setCountry(toUpperCase(datiAnagrafici.getPaese()));
        dati.setCap(toUpperCase(datiAnagrafici.getCap()));
        dati.setHousenum(toUpperCase(datiAnagrafici.getNumeroCivico()));
        dati.setHousenum2(toUpperCase(datiAnagrafici.getEstenzioneNumeroCivico()));
        dati.setName(toUpperCase(datiAnagrafici.getNome()));
        dati.setNameOrg1(toUpperCase(datiAnagrafici.getRagioneSociale1()));
        dati.setNameOrg2(toUpperCase(datiAnagrafici.getRagioneSociale2()));
        dati.setNameOrg3(toUpperCase(datiAnagrafici.getRagioneSociale3()));
        dati.setNameOrg4(toUpperCase(datiAnagrafici.getRagioneSociale4()));
        dati.setPartnerNumber(toUpperCase(datiAnagrafici.getCodiceBP()));
        dati.setPi(toUpperCase(datiAnagrafici.getPartitaIVA()));
        dati.setPrvcy(toUpperCase(datiAnagrafici.getPrivacy()));
        dati.setPrvcy("X");
        dati.setPrvcyEmail(toUpperCase(datiAnagrafici.getPrivacyEmail()));
        dati.setPrvcyMail(toUpperCase(datiAnagrafici.getPrivacyMail()));
        dati.setPrvcyTel(toUpperCase(datiAnagrafici.getPrivacyTel()));
        dati.setRegion(toUpperCase(datiAnagrafici.getProvincia()));
        dati.setStreet(toUpperCase(datiAnagrafici.getVia()));
        dati.setSurname(toUpperCase(datiAnagrafici.getCognome()));
        String tel = datiAnagrafici.getPrefissoTel()!=null?datiAnagrafici.getPrefissoTel():"+39";
        tel = tel.concat("-");
        if(datiAnagrafici.getTelefono()==null || datiAnagrafici.getTelefono().equals("")){
        	tel = "";
        }else{
        	tel = tel.concat(datiAnagrafici.getTelefono());
        }
        dati.setTelephone(tel);
        //dati.setTelephone(datiAnagrafici.getTelefono());
        ZptStCond _zptServiceRequestCreate_isCond = null;
        ZptStDc _zptServiceRequestCreate_isDc = null;
        ZptStRecapito _zptServiceRequestCreate_isRec = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
        _zptServiceRequestCreate_iNumcivExtension =dati.getHousenum2();
        Logger.getLogger(getClass()).info("setDatiAnagraficiPAE : Estensione Passata"+datiAnagrafici.getEstenzioneNumeroCivico()+"Estensione Arrivata"+ dati.getHousenum2() +"Passiamo "+_zptServiceRequestCreate_iNumcivExtension);
		portCRM.zptServiceRequestCreate(_zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, _zptServiceRequestCreate_iInterloc,
        		_zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv,_zptServiceRequestCreate_iNumcivExtension,_zptServiceRequestCreate_iPartner,
        		_zptServiceRequestCreate_iText, _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag,
        		_zptServiceRequestCreate_iVkont, dati, _zptServiceRequestCreate_isCond, _zptServiceRequestCreate_isDc,
        		_zptServiceRequestCreate_isRec, _zptServiceRequestCreate_eReturn, _zptServiceRequestCreate_eSrId, _zptServiceRequestCreate_esrNum, _zptServiceRequestCreate_etMess);
        
        
        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
        return new WSReturn<Object>(_zptServiceRequestCreate_eReturn.value, "setDatiAnagraficiPAE", null, _zptServiceRequestCreate_etMess.value.getItem());
    }
    
    /**
     * Permette di modificare la lingua con la quale sap torna i contenuti
     * 
     * @param langCode il codice della lingua (it, en, ...)
     */
    public final void setLingua(String langCode) {
        debug("setLingua", langCode);
        if (langCode == null || langCode.isEmpty())
            throw new IllegalArgumentException("Il codice della lingua è obbligatorio");
        
        langCode = langCode.toUpperCase();
        
        // imposto la lingua per isu
        if (isuEndpointAddress == null)
            isuEndpointAddress = ((BindingProvider)portISU).getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY).toString();
        String eaIsu = isuEndpointAddress+"?sap-language="+langCode;
        ((BindingProvider)portISU).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, eaIsu);        

        // imposto la lingua per crm
        if (crmEndpointAddress == null)
            crmEndpointAddress = ((BindingProvider)portCRM).getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY).toString();
        String eaCrm = crmEndpointAddress+"?sap-language="+langCode;
        ((BindingProvider)portCRM).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, eaCrm);       
        
        this.lingua = langCode;
    }
    
    /**
     * Crea la service request per l'associazione di un nuovo conto contrattuale
     * 
     * @param ccNuovo il nuovo conto contrattuale
     * @return il messaggio tornato dal web service
     */
    public WSReturn aggiungiContoContrattuale(String ccNuovo) {
        String bp = bpCliente;
        debug("aggiungiContoContrattuale", bp, ccNuovo);
        
        String _zptServiceRequestCreate_iCateg1 = "";
        String _zptServiceRequestCreate_iCateg2 = "";
        String _zptServiceRequestCreate_iInterloc = bpUtenza;
        String _zptServiceRequestCreate_iNumciv = "";
        String _zptServiceRequestCreate_iNumcivExtension = "";// lleone - modifica estenzione numero civ
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = "";
        String _zptServiceRequestCreate_iTypSr = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_AGGIUNTA_CC;
        String _zptServiceRequestCreate_iVkont = checkLenght(ccNuovo, Constants.DIM_CC, true);
        String _zptServiceRequestCreate_iVertrag = "";
        ZptStBpData _zptServiceRequestCreate_isBp = null;
        ZptStCond _zptServiceRequestCreate_isCond = null;
        ZptStDc _zptServiceRequestCreate_isDc = null;
        ZptStRecapito _zptServiceRequestCreate_isRec = null;
		String _zptServiceRequestCreate_iJahiaType = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
        portCRM.zptServiceRequestCreate(
                _zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, 
                _zptServiceRequestCreate_iInterloc, _zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv,
                _zptServiceRequestCreate_iNumcivExtension,
                _zptServiceRequestCreate_iPartner, _zptServiceRequestCreate_iText, 
                _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag, _zptServiceRequestCreate_iVkont, 
                _zptServiceRequestCreate_isBp, _zptServiceRequestCreate_isCond, 
                _zptServiceRequestCreate_isDc, _zptServiceRequestCreate_isRec, 
                _zptServiceRequestCreate_eReturn, _zptServiceRequestCreate_eSrId, _zptServiceRequestCreate_esrNum, _zptServiceRequestCreate_etMess);
        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
//        return getMessaggiWS(_zptServiceRequestCreate_etMess.value.getItem());
        return new WSReturn<Object>(_zptServiceRequestCreate_eReturn.value, "aggiungiContoContrattuale", null, _zptServiceRequestCreate_etMess.value.getItem());        
    }
    
    /**
     * Torna i dati catastali
     * 
     * @param contratto il codice del contratto
     * @param cc il conto contrattuale dell'utente
     * @return i dati catastali
     */
    public WSReturn<DatiCatastali> getDatiCatastali(String contratto, String cc) {
        String bp = bpCliente;
        debug("getDatiCatastali", bp, contratto, checkLenght(cc, Constants.DIM_CC, true));
        
        java.lang.String _zptContractDetail_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptContractDetail_iFlagDc = "X";
        java.lang.String _zptContractDetail_iFlagInfo = "";
        java.lang.String _zptContractDetail_iFlagPod = "";
        java.lang.String _zptContractDetail_iPartner = bp;
        java.lang.String _zptContractDetail_iVertrag = checkLenght(contratto, Constants.DIM_C, true);
        javax.xml.ws.Holder<java.lang.String> _zptContractDetail_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStDc> _zptContractDetail_esDc = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStDc>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo> _zptContractDetail_esInfo = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStPod> _zptContractDetail_esPod = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStPod>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptContractDetail_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtRiduzioni> _zptContractDetail_etRiduzioni = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtRiduzioni>();
        portISU.zptContractDetail(_zptContractDetail_iCc, _zptContractDetail_iFlagDc, _zptContractDetail_iFlagInfo, _zptContractDetail_iFlagPod, _zptContractDetail_iPartner, _zptContractDetail_iVertrag, _zptContractDetail_eReturn, _zptContractDetail_esDc, _zptContractDetail_esInfo, _zptContractDetail_esPod, _zptContractDetail_etMess, _zptContractDetail_etRiduzioni);

        DatiCatastali datiCatastali = new DatiCatastali(
                _zptContractDetail_esDc.value.getCityAmm(),
                _zptContractDetail_esDc.value.getCityCat(),
                _zptContractDetail_esDc.value.getCodcat(),
                _zptContractDetail_esDc.value.getSezurb(),
                _zptContractDetail_esDc.value.getPart(),
                _zptContractDetail_esDc.value.getParttav(),
                _zptContractDetail_esDc.value.getTppart(),
                _zptContractDetail_esDc.value.getSubalt(),
                _zptContractDetail_esDc.value.getFoglio());
        
        debug("getDatiCatastali return", datiCatastali.toString());
        
//        return datiCatastali;
        return new WSReturn<DatiCatastali>(_zptContractDetail_eReturn.value, "getDatiCatastali", datiCatastali, _zptContractDetail_etMess.value.getItem());         
    }
    
    /**
	     * Comunica un'autolettura per un certo conto contrattuale
	     * 
	     * @param contratto il contratto dell'utente
	     * @param autolettura il valore dell'autolettura
	     * @return il messaggio tornato dal web service
	     */
	    public WSReturn comunicaAutolettura(String contratto, String autolettura, boolean isAcqua) {
	        String bp = bpCliente;
	        debug("comunicaAutolettura", bp, contratto, autolettura);
	
	        java.lang.String _zptAutolettura_iId = "PCLIE";
	        java.lang.String _zptAutolettura_iPartner = bp;
	        java.lang.String _zptAutolettura_iVertrag = checkLenght(contratto, Constants.DIM_C, true);;
	        java.lang.String _zptAutolettura_iZwstan = autolettura;
	        javax.xml.ws.Holder<java.lang.String> _zptAutolettura_eReturn = new javax.xml.ws.Holder<java.lang.String>();
	        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptAutolettura_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
	        String _zptAutolettura_iAcqua = isAcqua?"X":"";
			portISU.zptAutolettura(_zptAutolettura_iAcqua , _zptAutolettura_iId, _zptAutolettura_iPartner, _zptAutolettura_iVertrag, _zptAutolettura_iZwstan, _zptAutolettura_eReturn, _zptAutolettura_etMess);
	
	
	        debug("zptAutolettura._zptAutolettura_eReturn=", _zptAutolettura_eReturn.value);
	        debugMsg("zptAutolettura._zptAutolettura_etMess=", _zptAutolettura_etMess.value.getItem());
	
	//        return getMessaggiWS(_zptAutolettura_etMess.value.getItem());
	        return new WSReturn<Object>(_zptAutolettura_eReturn.value, "comunicaAutolettura", null, _zptAutolettura_etMess.value.getItem());                
	    }
	    
	    

	/**
     * Crea una service request per la richiesta di modifica dei dati catastali
     * 
     * @param cc il conto contrattuale dell'utente
     * @param datiCatastali i nuovi dati catastali
     * @return il messaggio tornato dal web service
     */
    public WSReturn setDatiCatastali(String cc, String contratto, DatiCatastali datiCatastali) {
        String bp = bpCliente;
        debug("setDatiCatastali", bp, checkLenght(cc, Constants.DIM_CC, true), checkLenght(contratto, Constants.DIM_C, true), datiCatastali);
        
        String _zptServiceRequestCreate_iCateg1 = "";
        String _zptServiceRequestCreate_iCateg2 = "";
        String _zptServiceRequestCreate_iInterloc = bpUtenza;
        String _zptServiceRequestCreate_iNumciv = "";
        String _zptServiceRequestCreate_iNumcivExtension = "";// lleone - modifica estenzione numero civ
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = "";
        String _zptServiceRequestCreate_iTypSr = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_DATI_CATASTALI;
        
        debug("setDatiCatastali", _zptServiceRequestCreate_iTypSr);
        
        String _zptServiceRequestCreate_iVkont = toUpperCase(checkLenght(cc, Constants.DIM_CC, true));
        
        String _zptServiceRequestCreate_iVertrag = toUpperCase(checkLenght(contratto, Constants.DIM_C, true));
        ZptStBpData _zptServiceRequestCreate_isBp = null;
        ZptStCond _zptServiceRequestCreate_isCond = null;
		String _zptServiceRequestCreate_iJahiaType = null;
        ZptStDc _zptServiceRequestCreate_isDc = new ZptStDc();
        _zptServiceRequestCreate_isDc.setCityAmm(toUpperCase(datiCatastali.getComuneAmministrativo()));
        _zptServiceRequestCreate_isDc.setCityCat(toUpperCase(datiCatastali.getComuneCatastale()));
        _zptServiceRequestCreate_isDc.setCodcat(toUpperCase(datiCatastali.getCodiceCatastale()));
        _zptServiceRequestCreate_isDc.setPart(toUpperCase(datiCatastali.getParticella()));
        _zptServiceRequestCreate_isDc.setParttav(toUpperCase(datiCatastali.getParticellaSistemaTavolare()));
        _zptServiceRequestCreate_isDc.setSezurb(toUpperCase(datiCatastali.getSezioneUrbana()));
        _zptServiceRequestCreate_isDc.setSubalt(toUpperCase(datiCatastali.getSubalterno()));
        _zptServiceRequestCreate_isDc.setTppart(toUpperCase(datiCatastali.getTipoParticella()));
        _zptServiceRequestCreate_isDc.setZfoglio(toUpperCase(datiCatastali.getFoglio()));
        ZptStRecapito _zptServiceRequestCreate_isRec = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
		portCRM.zptServiceRequestCreate(
                _zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, 
                _zptServiceRequestCreate_iInterloc, _zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv, 
                 _zptServiceRequestCreate_iNumcivExtension,
                _zptServiceRequestCreate_iPartner, _zptServiceRequestCreate_iText, 
                _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag, _zptServiceRequestCreate_iVkont, 
                _zptServiceRequestCreate_isBp, _zptServiceRequestCreate_isCond, 
                _zptServiceRequestCreate_isDc, _zptServiceRequestCreate_isRec, 
                _zptServiceRequestCreate_eReturn,_zptServiceRequestCreate_eSrId, _zptServiceRequestCreate_esrNum, 
                _zptServiceRequestCreate_etMess);

        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
//        return getMessaggiWS(_zptServiceRequestCreate_etMess.value.getItem());
        return new WSReturn<Object>(_zptServiceRequestCreate_eReturn.value, "setDatiCatastali", null, _zptServiceRequestCreate_etMess.value.getItem());        
    }
    
    /**
     * Torna la lista dei comuni
     * @param provincia la provincia della quale tornare i comuni
     * @return la lista dei comuni della provincia
     */
    public WSReturn<List<Comune>> getComuni(String provincia) {
        debug("comuni", provincia);
        
        java.lang.String _zptReadComune_iRegion = provincia;
        javax.xml.ws.Holder<java.lang.String> _zptReadComune_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtComuni> _zptReadComune_etCity = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtComuni>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptReadComune_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        portISU.zptReadComune(_zptReadComune_iRegion, _zptReadComune_eReturn, _zptReadComune_etCity, _zptReadComune_etMess);

        debug("zptReadComune._zptReadComune_eReturn=", _zptReadComune_eReturn.value);
        debug("zptReadComune._zptReadComune_etCity=", _zptReadComune_etCity.value.toString());
        debug("zptReadComune._zptReadComune_etMess=", _zptReadComune_etMess.value.toString());
        debugMsg("comuni", _zptReadComune_etMess.value.getItem());
        
        ArrayList<Comune> comuni = new ArrayList<Comune>();
        for (ZptStComuni zptStComuni : _zptReadComune_etCity.value.getItem()) {
            comuni.add(
                    new Comune(
                        zptStComuni.getCity1(), 
                        zptStComuni.getCodcat(),
                        zptStComuni.getCap()));
        }
//        return comuni;
        return new WSReturn<List<Comune>>(_zptReadComune_eReturn.value, "getComuni", comuni, _zptReadComune_etMess.value.getItem());        
    }
    
    /**
     * Torna la lista dei contratti di un certo tipo associati al bp
     * 
     * @param tipoContratto una lista di servizi dei quali tornare i contratti o null per averli tutti
     * @return la lista dei contratti
     */
    public WSReturn<List<Contratto>> getElencoContratti(List<String> tipoContratto) {
        String bp = bpCliente;
        debug("getElencoContratti", bp, tipoContratto);

        // se non sono definiti tipi di contratto li aggiungo tutti di default
        if (tipoContratto == null || tipoContratto.isEmpty()) {
            tipoContratto = new ArrayList<String>();
            for (List<String> tipi : Constants.ZPT_CONTRACT_LIST_ITCOMPANY_MAP.values()) {
                tipoContratto.addAll(tipi);
            }
        }
        
        java.lang.String _zptContractList_iPartner = bp;
        
        // aggiungo le societa'
        com.alpenite.tea.communicationLayer.ws.isu.ZptTtCompanies _zptContractList_itCompany = new ZptTtCompanies();
        for (String s : tipoContratto) {
            ZptStCompanies c = new ZptStCompanies();
            c.setCompany(s);
            _zptContractList_itCompany.getItem().add(c);
        }
        javax.xml.ws.Holder<java.lang.String> _zptContractList_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptContractList_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtContract> _zptContractList_itContract = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtContract>();
        portISU.zptContractList(_zptContractList_iPartner, _zptContractList_itCompany, _zptContractList_eReturn, _zptContractList_etMess, _zptContractList_itContract);
        debug("----- BEGIN zptContractList ------");
        
        debug (_zptContractList_iPartner);
        debug (_zptContractList_itCompany);
        debug("----- END zptContractList ------");
        
        
        
        debug("zptContractList._zptContractList_eReturn=", _zptContractList_eReturn.value);
        debug("zptContractList._zptContractList_etMess=", _zptContractList_etMess.value.toString());
        debug("zptContractList._zptContractList_itContract=", _zptContractList_itContract.value.toString());
        debugMsg("getElencoContratti", _zptContractList_etMess.value.getItem());
        
        for(com.alpenite.tea.communicationLayer.ws.isu.ZptStMess mess:_zptContractList_etMess.value.getItem()){
            System.out.println(mess.getId()+" "+mess.getMsgNumber()+" "+mess.getType()+" "+mess.getMessage());
           }
        
        // compongo la lista dei contratti
        ArrayList<Contratto> contratti = new ArrayList<Contratto>();
        for (ZptStContract c : _zptContractList_itContract.value.getItem()) {
            contratti.add( 
                    new Contratto(
                        removePadding(c.getCc()), 
                        removePadding(c.getContractnum()), 
                        c.getStreet(), 
                        c.getService(), 
                        c.getStatus(),
                        c.getServiceCode(), 
                        c.getCompanyCode()));
        }
        
//        return contratti;
        return new WSReturn<List<Contratto>>(_zptContractList_eReturn.value, "getElencoContratti", contratti, _zptContractList_etMess.value.getItem());
    }
    
    
   
    
    
    
    private void debug(ZptTtCompanies _zptContractList_itCompany) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Ritorna i moduli di richiesta o revoca Mandato
     * @param documentRequest
     * @return
     */
    public WSReturn<DocumentResponse> getModuleDocument(DocumentRequest documentRequest){
    	 
    	Holder<String> ext = new javax.xml.ws.Holder<java.lang.String>(); 
		Holder<String> eReturn =new javax.xml.ws.Holder<java.lang.String>();
		Holder<String> eMimetyp=new javax.xml.ws.Holder<java.lang.String>();
		Holder<byte[]> eBin=new javax.xml.ws.Holder<byte[]>();
		Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> etMess = new Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
		Holder<String> eExt=new javax.xml.ws.Holder<String>();
		
		portISU.zptDownloadForm(this.bpCliente, documentRequest.getAccountContractual(), Boolean.valueOf(documentRequest.getIsRevoke())? "X":"", eBin, eExt, eMimetyp, eReturn, etMess);
		DocumentResponse documentResponse= new DocumentResponse();
		
		documentResponse.setDocument(eBin.value);
		documentResponse.setExtention(ext.value); 
		documentResponse.setMimetyp(eMimetyp.value);  
		
		return new WSReturn<DocumentResponse>(eReturn.value, "getModuleDocument", documentResponse, etMess.value.getItem());
    }
    
    
    
    
    /**
     * Torna il recapito relativo al conto contrattuale
     * 
     * @param cc il conto contrattuale
     * @return il recapito relativo al conto contrattuale
     */
    public WSReturn<Recapito> getRecapito(String cc) {
        String bp = bpCliente;
        debug("getRecapito", bp, checkLenght(cc, Constants.DIM_CC, true));
        
        java.lang.String _zptCcDetail_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptCcDetail_iDd = "";
        java.lang.String _zptCcDetail_iDr = "X";
        java.lang.String _zptCcDetail_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptCcDetail_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptCcDetail_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRecapito> _zptCcDetail_isRecapito = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRecapito>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRid> _zptCcDetail_isRid = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRid>();
        
        portISU.zptCcDetail(_zptCcDetail_iCc, _zptCcDetail_iDd, _zptCcDetail_iDr, _zptCcDetail_iPartner, _zptCcDetail_eReturn, _zptCcDetail_etMess, _zptCcDetail_isRecapito, _zptCcDetail_isRid);

        debug("zptCcDetail._zptCcDetail_eReturn=", _zptCcDetail_eReturn.value);
        debugMsg("zptCcDetail._zptCcDetail_etMess=" , _zptCcDetail_etMess.value.getItem());
        debug("zptCcDetail._zptCcDetail_isRecapito=", _zptCcDetail_isRecapito.value);
        debug("zptCcDetail._zptCcDetail_isRid=", _zptCcDetail_isRid.value);     
        
        Recapito r = new Recapito(
                _zptCcDetail_isRecapito.value.getCountry(),
                _zptCcDetail_isRecapito.value.getRegion(),
                _zptCcDetail_isRecapito.value.getCity1(),
                _zptCcDetail_isRecapito.value.getCity2(),
                _zptCcDetail_isRecapito.value.getCap(),
                _zptCcDetail_isRecapito.value.getStreet(),
                _zptCcDetail_isRecapito.value.getHousenum(),
                _zptCcDetail_isRecapito.value.getExtHousenum()
                );//Todo _zptCcDetail_isRecapito.value.getHousenum());
        
        return new WSReturn<Recapito>(_zptCcDetail_eReturn.value, "getRecapito", r, _zptCcDetail_etMess.value.getItem());
    }
    
    /**
     * Torna i dati relativi alla domiciliazione bancaria del conto contrattuale
     * 
     * @param cc il conto contrattuale
     * @return i dati sulla domiciliazione bancaria
     */
    public WSReturn<DomiciliazioneBancaria> getDomiciliazioneBancaria(String cc) {
        String bp = bpCliente;
        debug("getDomiciliazioneBancaria", bp, checkLenght(cc, Constants.DIM_CC, true));
        
        java.lang.String _zptCcDetail_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptCcDetail_iDd = "X";
        java.lang.String _zptCcDetail_iDr = "";
        java.lang.String _zptCcDetail_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptCcDetail_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptCcDetail_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRecapito> _zptCcDetail_isRecapito = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRecapito>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRid> _zptCcDetail_isRid = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStRid>();
        portISU.zptCcDetail(_zptCcDetail_iCc, _zptCcDetail_iDd, _zptCcDetail_iDr, _zptCcDetail_iPartner, _zptCcDetail_eReturn, _zptCcDetail_etMess, _zptCcDetail_isRecapito, _zptCcDetail_isRid);

        debug("zptCcDetail._zptCcDetail_eReturn=", _zptCcDetail_eReturn.value);
        debugMsg("zptCcDetail._zptCcDetail_etMess=" , _zptCcDetail_etMess.value.getItem());
        debug("zptCcDetail._zptCcDetail_isRecapito=", _zptCcDetail_isRecapito.value);
        debug("zptCcDetail._zptCcDetail_isRid=", _zptCcDetail_isRid.value);     
        
        DomiciliazioneBancaria d = new DomiciliazioneBancaria(
                _zptCcDetail_isRid.value.getBankname(),
                _zptCcDetail_isRid.value.getIban(),
                _zptCcDetail_isRid.value.getMandateId(),
                _zptCcDetail_isRid.value.getDoctype(),
                bytesToHex(_zptCcDetail_isRid.value.getDocid())                
        		);
        
        
        return new WSReturn<DomiciliazioneBancaria>(_zptCcDetail_eReturn.value, "getDomiciliazioneBancaria", d, _zptCcDetail_etMess.value.getItem());
    }
    
    public String bytesToHex(byte[] bytes) {
    	char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    /**
     * Torna i dati anagrafici relativi al bp cliente (non per amministratori di condominio)
     * 
     * @param azienda un flag che indica se i dati anagrafici sono riferiti ad un'azienda
     * @return i dati anagrafici richiesti
     */
    public WSReturn<DatiAnagrafici> getDatiAnagrafici(boolean azienda, String jahiaType) {
        return getDatiAnagrafici(azienda, false, jahiaType);
    }
      
    private String getPhone(String number){
    	String[] values = number.split("-");
    	if(values.length > 1){
    		String result = "";
    		for(int i = 1; i< values.length; i++){
    			result += values[i];
    		}
    		return result;
    	}
    	if(values.length == 1 && number.contains("+39")){
    		return number.replaceAll("\\+39", "");
    	}
    	return number; 
    }
    
    private String getInternationalPrefix(String number){
    	String[] values = number.split("-");
    	if(values.length > 1)
    		return values[0];
    	if(values.length == 1 && number.contains("+39")){
    		return "+39";
    	}
    	return "";
    }
    
    /**
     * Torna i dati anagrafici relativi al bp indicato
     * 
     * @param azienda un flag che indica se i dati anagrafici sono riferiti ad un'azienda
     * @param amministratore un flag che indica se i dati anagrafici sono riferiti ad un'amministratore
     * @return i dati anagrafici richiesti
     */
    public WSReturn<DatiAnagrafici> getDatiAnagrafici(boolean azienda, boolean amministratore, String jahiaType) {
    	debug("getDatiAnagrafici(new)", bpUtenza, azienda, bpCliente, amministratore, jahiaType);
    	
    	String bp;
    	if (amministratore) {
    		bp = bpUtenza;
		} else {
			bp = bpCliente;
		}
        debug("getDatiAnagrafici", bp, azienda);
        
        java.lang.String _zptReadBp_iAzienda = (azienda?"X":"");
        java.lang.String _zptReadBp_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptReadBp_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptStBpData> dati = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptStBpData>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptReadBp_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        javax.xml.ws.Holder<String> _zptReadBp_eMail = new javax.xml.ws.Holder<String>();
		java.lang.String _zptReadBp_iJahiaType = jahiaType;
		portCRM.zptReadBp(_zptReadBp_iAzienda,_zptReadBp_iJahiaType, _zptReadBp_iPartner, _zptReadBp_eMail , _zptReadBp_eReturn, dati, _zptReadBp_etMess);
        
        debug("zptReadBp._zptReadBp_eReturn=", _zptReadBp_eReturn.value);
        debug("zptReadBp._zptReadBp_esBp=", dati.value);
        debugMsg("zptReadBp._zptReadBp_etMess=", _zptReadBp_etMess.value.getItem());
        DatiAnagrafici d = new DatiAnagrafici(
                dati.value.getPartnerNumber(),
                dati.value.getBpType(),
                dati.value.getNameOrg1(),
                dati.value.getNameOrg2(),
                dati.value.getNameOrg3(),
                dati.value.getNameOrg4(),
                dati.value.getName(),
                dati.value.getSurname(),
                getPhone(dati.value.getTelephone()),
                getPhone(dati.value.getCellphone()),
                dati.value.getCountry(),
                dati.value.getRegion(),
                dati.value.getCity1(),
                dati.value.getCity2(),
                dati.value.getStreet(),
                dati.value.getHousenum(),
                dati.value.getHousenum2(),
                dati.value.getCap(),
                dati.value.getCf(),
                dati.value.getPi(),
                dati.value.getPrvcy(),
                dati.value.getPrvcyMail(),
                dati.value.getPrvcyEmail(),
                dati.value.getPrvcyTel(),
                dati.value.getRole(),
                getInternationalPrefix(dati.value.getTelephone()),
                getInternationalPrefix(dati.value.getCellphone()));
        
        // se è richiesta l'azienda eseguo una seconda chiamata al ws per reperire
        // il nome e cognome dell'interlocutore, da sostituire nei dati anagrafici
        if (azienda) {
            _zptReadBp_iAzienda = "";
            javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptStBpData> datiInterlocutore = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptStBpData>();
            javax.xml.ws.Holder<String> _zptReadBp_eMailAzienda = new javax.xml.ws.Holder<String>();
            portCRM.zptReadBp(_zptReadBp_iAzienda,_zptReadBp_iJahiaType ,_zptReadBp_iPartner, _zptReadBp_eMailAzienda, _zptReadBp_eReturn, datiInterlocutore, _zptReadBp_etMess);
            d.setNome(datiInterlocutore.value.getName());
            d.setCognome(datiInterlocutore.value.getSurname());
        }        
                
        
        return new WSReturn<DatiAnagrafici>(_zptReadBp_eReturn.value, "getDatiAnagrafici", d, _zptReadBp_etMess.value.getItem());        
    }
    
    /**
     * Aggiorna i dati anagrafici legati al bp
     * 
     * @param bp il bp dell'utente
     * @param tipoBp il tipo di bp dell'utente
     * @param datiAnagrafici i dati anagrafici
     * @return un messaggio tornato dal web service
     */
    public WSReturn setDatiAnagrafici(String bp, String tipoBp, DatiAnagrafici datiAnagrafici, String tipoUserJahia) {
        
        // come da BBP, se il tipo bp è privato/azienda/ente uso la service request
        if (tipoBp.equals("SR")) {
        	Logger.getLogger(getClass()).info(" Vado in PAE");//da abbassare a debug
            return setDatiAnagraficiPAE(bp, datiAnagrafici,tipoUserJahia);
        } 
        // se è amministratore di condominio o intelocutore utilizzo il web servicev
        else {
        	Logger.getLogger(getClass()).info(" Vado in IntAmm");//da abbassare a debug
            return setDatiAnagraficiIntAmm(bp, datiAnagrafici);
        }
    }
    
    /**
     * Torna un array contenente all'indice 0 il flag relativo alla modalità
     * d'invio delle fatture cartacea mentre all'indice 1 quello relativo all'email
     * 
     * @param cc il conto contrattuale dell'utente
     * @return un array con 0=cartaceo, 1=email
     */
    public WSReturn<String[]> getFlagCartaceoEmail(String cc) {
        String bp = bpCliente;
        debug("getFlagCartaceoEmail", bp, checkLenght(cc, Constants.DIM_CC, true));
        
        String _zptFlagCartaceo_iCc      = toUpperCase(checkLenght(cc, Constants.DIM_CC, true));
        String _zptFlagCartaceo_iDetbol  = "X";
        String _zptFlagCartaceo_iEmail   = "X";
        String _zptFlagCartaceo_iMail    = "X";
        String _zptFlagCartaceo_iMailpdf = "X";
        String _zptFlagCartaceo_iPartner = bp;
        String _zptFlagCartaceo_iUpdate  = "";
        
        //Inizializzo i tipi di conto per cui è possibile scegliere il dettaglio della bolletta
        ZptTtVktyp _zptFlagCartaceo_iTipiConto = new ZptTtVktyp();
        for(String tipoConto:Constants.ZPT_FLAG_CARTACEO_DETBOL_CONTI){
        	ZptStVktyp zptStVktyp = new ZptStVktyp();
        	zptStVktyp.setTipoConto(tipoConto);
        	_zptFlagCartaceo_iTipiConto.getItem().add(zptStVktyp);
        }
        
        Holder<String> _zptFlagCartaceo_eDetbol = new Holder<String>();
        Holder<String> _zptFlagCartaceo_eEmail   = new Holder<String>();
        Holder<String> _zptFlagCartaceo_eMail    = new Holder<String>();
        Holder<String> _zptFlagCartaceo_eMailpdf = new Holder<String>();
        Holder<String> _zptFlagCartaceo_eReturn  = new Holder<String>();
        Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptFlagCartaceo_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();

		portISU.zptFlagCartaceo(_zptFlagCartaceo_iCc,
								_zptFlagCartaceo_iDetbol,
        						_zptFlagCartaceo_iEmail,
								_zptFlagCartaceo_iMail,
								_zptFlagCartaceo_iMailpdf,
								_zptFlagCartaceo_iPartner,
								_zptFlagCartaceo_iTipiConto,
								_zptFlagCartaceo_iUpdate,
								_zptFlagCartaceo_eDetbol,
								_zptFlagCartaceo_eEmail,
								_zptFlagCartaceo_eMail,
								_zptFlagCartaceo_eMailpdf,
								_zptFlagCartaceo_eReturn,
								_zptFlagCartaceo_etMess);

        debug("zptFlagCartaceo._zptFlagCartaceo_eEmail=",    _zptFlagCartaceo_eEmail.value);
        debug("zptFlagCartaceo._zptFlagCartaceo_eMail=",     _zptFlagCartaceo_eMail.value);
        debug("zptFlagCartaceo._zptFlagCartaceo_eReturn=",   _zptFlagCartaceo_eReturn.value);
        debug("zptFlagCartaceo._zptFlagCartaceo_eMailpdf=",  _zptFlagCartaceo_eMailpdf.value);
        debug("zptFlagCartaceo._zptFlagCartaceo_eDetbol=",   _zptFlagCartaceo_eDetbol.value);
        debugMsg("zptFlagCartaceo._zptFlagCartaceo_etMess=", _zptFlagCartaceo_etMess.value.getItem());
        String[] datiTornati;
        
        if(_zptFlagCartaceo_eDetbol.value.equals("N")){//eDetbol==n non va riportato.
        	datiTornati =  new String[] { _zptFlagCartaceo_eMail.value,
						      			  _zptFlagCartaceo_eEmail.value,
						      			  _zptFlagCartaceo_eMailpdf.value,
						      			};
        } else {
        	datiTornati =  new String[] { _zptFlagCartaceo_eMail.value,
						     			  _zptFlagCartaceo_eEmail.value,
						     			  _zptFlagCartaceo_eMailpdf.value,
						     			  _zptFlagCartaceo_eDetbol.value
						     			};
        }
//        return new String[] { _zptFlagCartaceo_eMail.value, _zptFlagCartaceo_eEmail.value };
        return new WSReturn<String[]>(
                _zptFlagCartaceo_eReturn.value, 
                "getFlagCartaceoEmail", 
                datiTornati,
                _zptFlagCartaceo_etMess.value.getItem());        
    }
    
    /**
     * Permette di impostare il flag per la selezione della modalità d'invio
     * delle fatture
     * 
     * @param cc il conto contrattuale dell'utente
     * @param cartaceo il flag relativo alla modalità cartacea
     * @param email il flag relativo alla modalità via email
     * @param pdfInEmail il flag relativo al pdf fornito nell'e-mail
     * @param detBol il flag relativo al dettaglio della bolletta
     * @return il messaggio tornato dal ws
     */
    public WSReturn setFlagCartaceoEmail(String cc, boolean cartaceo, boolean email, boolean pdfInEmail, boolean detBol) {
        String bp = bpCliente;
        debug("setFlagCartaceoEmail", bp, checkLenght(cc, Constants.DIM_CC, true), cartaceo, email,pdfInEmail);
        
        String _zptFlagCartaceo_iCc      = toUpperCase(checkLenght(cc, Constants.DIM_CC, true));
        String _zptFlagCartaceo_iDetbol  = detBol?"X":"";
        String _zptFlagCartaceo_iEmail   = email?"X":"";
        String _zptFlagCartaceo_iMail    = cartaceo?"X":"";
        String _zptFlagCartaceo_iMailpdf = pdfInEmail?"X":"";
        String _zptFlagCartaceo_iPartner = bp;
        String _zptFlagCartaceo_iUpdate  = "X";
        
        //Inizializzo i tipi di conto per cui è possibile scegliere il dettaglio della bolletta
        ZptTtVktyp _zptFlagCartaceo_iTipiConto = new ZptTtVktyp();
        for(String tipoConto:Constants.ZPT_FLAG_CARTACEO_DETBOL_CONTI){
        	ZptStVktyp zptStVktyp = new ZptStVktyp();
        	zptStVktyp.setTipoConto(tipoConto);
        	_zptFlagCartaceo_iTipiConto.getItem().add(zptStVktyp);
        }
        
        Holder<String>    _zptFlagCartaceo_eDetbol  = new Holder<String>();
        Holder<String>    _zptFlagCartaceo_eEmail   = new Holder<String>();
        Holder<String>    _zptFlagCartaceo_eMail    = new Holder<String>();
        Holder<String>    _zptFlagCartaceo_eMailpdf = new Holder<String>();
        Holder<String>    _zptFlagCartaceo_eReturn  = new Holder<String>();
        Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptFlagCartaceo_etMess   = new Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();

        portISU.zptFlagCartaceo(_zptFlagCartaceo_iCc,
        						_zptFlagCartaceo_iDetbol,
        						_zptFlagCartaceo_iEmail,
								_zptFlagCartaceo_iMail,
								_zptFlagCartaceo_iMailpdf,
								_zptFlagCartaceo_iPartner,
								_zptFlagCartaceo_iTipiConto,
								_zptFlagCartaceo_iUpdate,
								_zptFlagCartaceo_eDetbol,
								_zptFlagCartaceo_eEmail,
								_zptFlagCartaceo_eMail,
								_zptFlagCartaceo_eMailpdf,
								_zptFlagCartaceo_eReturn,
								_zptFlagCartaceo_etMess);

        debug("zptFlagCartaceo._zptFlagCartaceo_eEmail=",    _zptFlagCartaceo_eEmail.value  );
        debug("zptFlagCartaceo._zptFlagCartaceo_eMail=",     _zptFlagCartaceo_eMail.value   );
        debug("zptFlagCartaceo._zptFlagCartaceo_eMailpdf=",  _zptFlagCartaceo_eMailpdf.value);
        debug("zptFlagCartaceo._zptFlagCartaceo_eDetbol=",   _zptFlagCartaceo_eDetbol.value);
        debug("zptFlagCartaceo._zptFlagCartaceo_eReturn=",   _zptFlagCartaceo_eReturn.value );
        debugMsg("zptFlagCartaceo._zptFlagCartaceo_etMess=", _zptFlagCartaceo_etMess.value.getItem());

//        return getMessaggiWS(_zptFlagCartaceo_etMess.value.getItem());    
        return new WSReturn(_zptFlagCartaceo_eReturn.value, "setFlagCartaceoEmail", null, _zptFlagCartaceo_etMess.value.getItem());        
    }
    
    /**
     * Crea una service request per la modifica del recapito
     * 
     * @param cc il conto contrattuale dell'utente
     * @param r il recapito da modificare
     * @return il messaggio tornato dal web service
     */
    public WSReturn modificaRecapito(String cc, Recapito r) {
        String bp = bpCliente;
        debug("modificaRecapito", bp, checkLenght(cc, Constants.DIM_CC, true), r);
        
        String _zptServiceRequestCreate_iCateg1 = "";
        String _zptServiceRequestCreate_iCateg2 = "";
        String _zptServiceRequestCreate_iInterloc = bpUtenza;
        String _zptServiceRequestCreate_iNumciv = "";
        String _zptServiceRequestCreate_iNumcivExtension =r.getExtnumeroCivico();
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = "";
        String _zptServiceRequestCreate_iTypSr = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_RECAPITO;
        String _zptServiceRequestCreate_iVkont = checkLenght(cc, Constants.DIM_CC, true);
        String _zptServiceRequestCreate_iVertrag = "";
        ZptStBpData _zptServiceRequestCreate_isBp = null;
        ZptStCond _zptServiceRequestCreate_isCond = null;
        ZptStDc _zptServiceRequestCreate_isDc = null;
        ZptStRecapito _zptServiceRequestCreate_isRec = new ZptStRecapito();
        _zptServiceRequestCreate_isRec.setCountry(r.getPaese());
        _zptServiceRequestCreate_isRec.setRegion(r.getProvincia());
        _zptServiceRequestCreate_isRec.setCity1(r.getComune());
        _zptServiceRequestCreate_isRec.setCity2(r.getFrazione());
        _zptServiceRequestCreate_isRec.setCap(r.getCap());
        _zptServiceRequestCreate_isRec.setStreet(r.getVia());
        _zptServiceRequestCreate_isRec.setHousenum(r.getNumeroCivico());
        _zptServiceRequestCreate_isRec.setHousenum2(r.getExtnumeroCivico());
		String _zptServiceRequestCreate_iJahiaType = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
		portCRM.zptServiceRequestCreate(
                _zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, 
                _zptServiceRequestCreate_iInterloc,_zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv, 
                _zptServiceRequestCreate_iNumcivExtension,
                _zptServiceRequestCreate_iPartner, _zptServiceRequestCreate_iText, 
                _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag, _zptServiceRequestCreate_iVkont, 
                _zptServiceRequestCreate_isBp, _zptServiceRequestCreate_isCond, 
                _zptServiceRequestCreate_isDc, _zptServiceRequestCreate_isRec, 
                _zptServiceRequestCreate_eReturn, _zptServiceRequestCreate_eSrId, _zptServiceRequestCreate_esrNum,_zptServiceRequestCreate_etMess);

        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
//        return getMessaggiWS(_zptServiceRequestCreate_etMess.value.getItem());    
        return new WSReturn(_zptServiceRequestCreate_eReturn.value, "modificaRecapito", null, _zptServiceRequestCreate_etMess.value.getItem());        
    }
    
    /**
     * Torna l'elenco dei documenti di un certo tipo, eventualmente filtrati
     * per le date indicate
     * 
     * @param cc il conto contrattuale dell'utente
     * @param tipo il tipo del documento (Constants.ZPT_CC_LIST_DETT_*)
     * @param dataDa la data iniziale
     * @param dataA la data finale
     * @return la lista dei documenti richiesti
     */
    public WSReturn<List<Documento>> getElencoDocumenti(String cc, String tipo, String dataDa, String dataA ) {
        String bp = bpCliente;
        debug("geElencoDocumenti", bp, checkLenght(cc, Constants.DIM_CC, true), tipo, dataDa, dataA);
        
        DateFormat df = new SimpleDateFormat("y-m-d");
        try {
            if ((dataDa != null && !dataDa.isEmpty() && df.parse(dataDa).before(df.parse("2010-01-01"))) ||
                    (dataA != null && !dataA.isEmpty() && df.parse(dataA).before(df.parse("2010-01-01")))) {
                return new WSReturn<List<Documento>>(Constants.ERRORE_DATA_PRECEDENTE_1110, "getElencoDocumenti", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new WSReturn<List<Documento>>(Constants.ERRORE_DATA_PRECEDENTE_1110, "getElencoDocumenti", null, null);
        }
        
        java.lang.String _zptCcListDett_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptCcListDett_iDatafrom = dataDa;
        java.lang.String _zptCcListDett_iDatato = dataA;
        java.lang.String _zptCcListDett_iFlagEc = Constants.ZPT_CC_LIST_DETT_ESTRATTO_CONTO.equals(tipo)?"X":"";
        java.lang.String _zptCcListDett_iFlagPa = Constants.ZPT_CC_LIST_DETT_PARTITE_APERTE.equals(tipo)?"X":"";
        java.lang.String _zptCcListDett_iFlagSfatt = Constants.ZPT_CC_LIST_DETT_STORICO_FATTURE.equals(tipo)?"X":"";
        java.lang.String _zptCcListDett_iPartner = bp;
        
        if(_zptCcListDett_iFlagPa.equals("X") && _zptCcListDett_iFlagEc.equals(_zptCcListDett_iFlagSfatt) && _zptCcListDett_iFlagEc.equals("")){
        	_zptCcListDett_iDatafrom = "";
        	_zptCcListDett_iDatato = "";
        }
        
        javax.xml.ws.Holder<java.lang.String> _zptCcListDett_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList> _zptCcListDett_etList = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptCcListDett_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        portISU.zptCcListDett(_zptCcListDett_iCc, _zptCcListDett_iDatafrom, _zptCcListDett_iDatato, _zptCcListDett_iFlagEc, _zptCcListDett_iFlagPa, _zptCcListDett_iFlagSfatt, _zptCcListDett_iPartner, _zptCcListDett_eReturn, _zptCcListDett_etList, _zptCcListDett_etMess);

        debug("zptCcListDett._zptCcListDett_eReturn=", _zptCcListDett_eReturn.value);
        debug("zptCcListDett._zptCcListDett_etList=", _zptCcListDett_etList.value);
        debugMsg("zptCcListDett._zptCcListDett_etMess=",_zptCcListDett_etMess.value.getItem());
        
        List<Documento> lista = new ArrayList<Documento>();
        for (ZptStList doc : _zptCcListDett_etList.value.getItem()) {
            lista.add( new Documento(
                    tipo, 
                    doc.getNumfatt(), 
                    doc.getDatafatt(),
                    doc.getDatascad(), 
                    doc.getTotalAmnt(),
                    doc.getOpenAmnt(),
                    doc.getPdf(),
                    doc.getPdfObj()));
        }
        
//        return lista;
        return new WSReturn<List<Documento>>(_zptCcListDett_eReturn.value, "getElencoDocumenti", lista, _zptCcListDett_etMess.value.getItem());        
    }
    
    /**
     * Richiede al web service un file da scaricare
     * 
     * @param fileId l'id del file richiesto
     * @param fileObj l'oggetto sap
     * @return il documento
     */
    public WSReturn<File> getFileDocumento(String fileId, String fileObj) {
        String bp = bpCliente;
        debug("getFileDocumento", bp, fileId);
        
        java.lang.String _zptDownloadFile_iArcDocId = fileId;
        java.lang.String _zptDownloadFile_iPartner = bp;
        java.lang.String _zptDownloadFile_iSapObject = fileObj;
        javax.xml.ws.Holder<byte[]> _zptDownloadFile_eBin = new javax.xml.ws.Holder<byte[]>();
        javax.xml.ws.Holder<java.lang.String> _zptDownloadFile_eExt = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptDownloadFile_eMimetyp = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptDownloadFile_eName = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptDownloadFile_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptDownloadFile_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        portISU.zptDownloadFile(_zptDownloadFile_iArcDocId, _zptDownloadFile_iPartner, _zptDownloadFile_iSapObject, _zptDownloadFile_eBin, _zptDownloadFile_eExt, _zptDownloadFile_eMimetyp, _zptDownloadFile_eName, _zptDownloadFile_eReturn, _zptDownloadFile_etMess);

        debug("zptDownloadFile._zptDownloadFile_eBin.size=", _zptDownloadFile_eBin.value.length);
        debug("zptDownloadFile._zptDownloadFile_eExt=", _zptDownloadFile_eExt.value);
        debug("zptDownloadFile._zptDownloadFile_eMimetyp=", _zptDownloadFile_eMimetyp.value);
        debug("zptDownloadFile._zptDownloadFile_eName=", _zptDownloadFile_eName.value);
        debug("zptDownloadFile._zptDownloadFile_eReturn=", _zptDownloadFile_eReturn.value);
        debugMsg("zptDownloadFile._zptDownloadFile_etMess=", _zptDownloadFile_etMess.value.getItem());
        File f = new File(_zptDownloadFile_eName.value, _zptDownloadFile_eExt.value, _zptDownloadFile_eMimetyp.value, _zptDownloadFile_eBin.value);
        return new WSReturn<File>(_zptDownloadFile_eReturn.value, "getFileDocumento", f, _zptDownloadFile_etMess.value.getItem());        
    }
    
    /**
     * Torna l'indirizzo di fornitura relativo al bp e al conto contrattuale
     * 
     * @param contratto il contratto relativo all'indirizzo
     * @param cc il conto contrattuale dell'utente
     * @return un Recapito contenente l'indirizzo richiesto
     */
    public WSReturn<Recapito> getIndirizzoFornitura(String contratto, String cc) {
        String bp = bpCliente;
        debug("getIndirizzoFornitura", bp, contratto, checkLenght(cc, Constants.DIM_CC, true));
        
        java.lang.String _zptContractDetail_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptContractDetail_iFlagDc = "";
        java.lang.String _zptContractDetail_iFlagInfo = "";
        java.lang.String _zptContractDetail_iFlagPod = "X";
        java.lang.String _zptContractDetail_iPartner = bp;
        java.lang.String _zptContractDetail_iVertrag = checkLenght(contratto, Constants.DIM_C, true);;
        javax.xml.ws.Holder<java.lang.String> _zptContractDetail_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStDc> _zptContractDetail_esDc = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStDc>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo> _zptContractDetail_esInfo = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStInfo>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStPod> _zptContractDetail_esPod = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptStPod>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptContractDetail_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtRiduzioni> _zptContractDetail_etRiduzioni = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtRiduzioni>();
        portISU.zptContractDetail(_zptContractDetail_iCc, _zptContractDetail_iFlagDc, _zptContractDetail_iFlagInfo, _zptContractDetail_iFlagPod, _zptContractDetail_iPartner, _zptContractDetail_iVertrag, _zptContractDetail_eReturn, _zptContractDetail_esDc, _zptContractDetail_esInfo, _zptContractDetail_esPod, _zptContractDetail_etMess, _zptContractDetail_etRiduzioni);

        Recapito recapito = new Recapito(
                null, 
                null, 
                _zptContractDetail_esPod.value.getCity1(), 
                null, 
                _zptContractDetail_esPod.value.getCap(), 
                _zptContractDetail_esPod.value.getStreet(), 
                _zptContractDetail_esPod.value.getHousenum(),
                _zptContractDetail_esPod.value.getExthousenum(),
                _zptContractDetail_esPod.value.getPodCode());
        
        debug("getIndirizzoFornitura return", recapito.toString());
        
//        return recapito;
        return new WSReturn<Recapito>(_zptContractDetail_eReturn.value, "getIndirizzoFornitura", recapito, _zptContractDetail_etMess.value.getItem());
    }
    
    /**
     * Apre una service request che permette di impostare il numero civico 
     * dell'indirizzo di fornitura
     * 
     * @param cc il conto contrattuale del cliente
     * @param numeroCivico il numero civico corretto
     * @return il messaggio tornato dal web service
     */
    public WSReturn setIndirizzoFornitura(String cc, String c, String numeroCivico,String exteNumeroCivico) {
        String bp = bpCliente;
        debug("setIndirizzoFornitura", bp, checkLenght(cc, Constants.DIM_CC, true), checkLenght(c, Constants.DIM_C, true), numeroCivico,exteNumeroCivico);
        
        String _zptServiceRequestCreate_iCateg1 = "";
        String _zptServiceRequestCreate_iCateg2 = "";
        String _zptServiceRequestCreate_iInterloc = bpUtenza;
        String _zptServiceRequestCreate_iNumciv = numeroCivico;
        String _zptServiceRequestCreate_iNumcivExtension = exteNumeroCivico;// lleone - modifica estenzione numero civ ;
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = "";
        String _zptServiceRequestCreate_iTypSr = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_INDIRIZZO_FORNITURA;
        String _zptServiceRequestCreate_iVkont = toUpperCase(checkLenght(cc, Constants.DIM_CC, true));
        String _zptServiceRequestCreate_iVertrag = toUpperCase(checkLenght(c, Constants.DIM_C, true));
        ZptStBpData _zptServiceRequestCreate_isBp = null;
        ZptStCond _zptServiceRequestCreate_isCond = null;
        ZptStDc _zptServiceRequestCreate_isDc = null;
        ZptStRecapito _zptServiceRequestCreate_isRec = null;
		String _zptServiceRequestCreate_iJahiaType = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
        portCRM.zptServiceRequestCreate(
                _zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, 
                _zptServiceRequestCreate_iInterloc, _zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv,
                _zptServiceRequestCreate_iNumcivExtension, 
                _zptServiceRequestCreate_iPartner, _zptServiceRequestCreate_iText, 
                _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag, _zptServiceRequestCreate_iVkont, 
                _zptServiceRequestCreate_isBp, _zptServiceRequestCreate_isCond, 
                _zptServiceRequestCreate_isDc, _zptServiceRequestCreate_isRec, 
                _zptServiceRequestCreate_eReturn, _zptServiceRequestCreate_eSrId, _zptServiceRequestCreate_esrNum,_zptServiceRequestCreate_etMess);

        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
//        return getMessaggiWS(_zptServiceRequestCreate_etMess.value.getItem());
        return new WSReturn<Object>(_zptServiceRequestCreate_eReturn.value, "setIndirizzoFornitura", null, _zptServiceRequestCreate_etMess.value.getItem());
    }
    
    /**
     * Richiede al web service i dettagli di un contratto dell'acqua
     * 
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @return i dettagli del contratto
     */    
    public WSReturn<DettagliContrattoAcqua> getDettagliContrattoAcqua(String contratto, String cc) {
        String bp = bpCliente;
        WSReturn<ZptStInfo> r = getDettagliContratto(bp, contratto, cc);
        ZptStInfo info = r.getRitorno();
        String dataCessasione = info.getDatacess().equals("9999-12-31")?null:info.getDatacess();
        DettagliContrattoAcqua d = new DettagliContrattoAcqua(
                info.getDatainizio(), 
                dataCessasione,
                info.getTpuso(), 
                info.getCatUso(), 
                info.getOpzTarif(), 
                info.getQualTit(), 
                info.getUntabitat(), 
                info.getIva());
        
        return new WSReturn<DettagliContrattoAcqua>(r.getCodice(), "getDettagliContrattoAcqua", d, r.getMessaggi());
    }
    
    /**
     * Richiede al web service i dettagli di un contratto ambientale
     * 
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @return i dettagli del contratto
     */      
    public WSReturn<DettagliContrattoAmbiente> getDettagliContrattoAmbiente(String contratto, String cc) {
        String bp = bpCliente;
        List<ZptStRiduzioni> riduzioni = new ArrayList<ZptStRiduzioni>();
        
        // faccio impostare le riduzioni direttamente al metodo
        WSReturn<ZptStInfo> r = getDettagliContratto(bp, contratto, cc, riduzioni);
        ZptStInfo info = r.getRitorno();
        
        ArrayList<String> rid = new ArrayList<String>();
        
        for (ZptStRiduzioni riduzione : riduzioni) {
            rid.add(riduzione.getRiduzione());
        }
        
        String dataCessasione = info.getDatacess().equals("9999-12-31")?null:info.getDatacess();
        
        DettagliContrattoAmbiente d = new DettagliContrattoAmbiente(
                info.getDatainizio(), 
                dataCessasione, 
                info.getTpuso(), 
                info.getOpzTarif(), 
                info.getQualTit(), 
                info.getSuper(), 
                info.getNumpers(), 
                info.getAttivita(), 
                rid);
        
        return new WSReturn<DettagliContrattoAmbiente>(r.getCodice(), "getDettagliContrattoAmbiente", d, r.getMessaggi());
    }
    
    /**
     * Richiede al web service i dettagli di un contratto del gas
     * 
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @return i dettagli del contratto
     */     
    public WSReturn<DettagliContrattoGas> getDettagliContrattoGas(String contratto, String cc) {
        String bp = bpCliente;
        WSReturn<ZptStInfo> r = getDettagliContratto(bp, contratto, cc);
        ZptStInfo info = r.getRitorno();
        String dataCessasione = info.getDatacess().equals("9999-12-31")?null:info.getDatacess();
        DettagliContrattoGas d = new DettagliContrattoGas(
                info.getDatainizio(), 
                dataCessasione,
                info.getTpuso(), 
                info.getOpzTarif(), 
                info.getQualTit(), 
                info.getSconto(), 
                info.getIva(),
                info.getAccise());
        
        return new WSReturn<DettagliContrattoGas>(r.getCodice(), "getDettagliContrattoGas", d, r.getMessaggi());
    }
    /**
     * Richiede al web service i dettagli di un contratto del gas
     * 
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @return i dettagli del contratto
     */     
    public WSReturn<DettagliContrattoElettricita> getDettagliContrattoElettricita(String contratto, String cc) {
        String bp = bpCliente;
        WSReturn<ZptStInfo> r = getDettagliContratto(bp, contratto, cc);
        ZptStInfo info = r.getRitorno();
        String dataCessasione = info.getDatacess().equals("9999-12-31")?null:info.getDatacess();
        DettagliContrattoElettricita d = new DettagliContrattoElettricita(
                info.getDatainizio(), 
                dataCessasione,
                info.getPotenzacontr(), 
                info.getOpzTarif(), 
                info.getQualTit(), 
                info.getSconto(), 
                info.getIva(),
                info.getAccise(), info.getLivtensione());
        
        return new WSReturn<DettagliContrattoElettricita>(r.getCodice(), "getDettagliContratto", d, r.getMessaggi());
    }
    /**
     * Richiede al web service i dettagli di un contratto del teleriscaldamento
     * 
     * @param contratto il contratto
     * @param cc il conto contrattuale dell'utente
     * @return i dettagli del contratto
     */         
    public WSReturn<DettagliContrattoTeleriscaldamento> getDettagliContrattoTeleriscaldamento(String contratto, String cc) {
        String bp = bpCliente;
        WSReturn<ZptStInfo> r = getDettagliContratto(bp, contratto, cc);
        ZptStInfo info = r.getRitorno();
        String dataCessasione = info.getDatacess().equals("9999-12-31")?null:info.getDatacess();
        DettagliContrattoTeleriscaldamento d = new DettagliContrattoTeleriscaldamento(
                info.getDatainizio(), 
                dataCessasione,
                info.getTpuso(), 
                info.getCatUso(),
                info.getOpzTarif(), 
                info.getQualTit(), 
                info.getVolum(),
                info.getCoeffuso(),
                info.getPortata(),
                info.getIva(),
                info.getRiscaldam(),
                info.getRaffred(),
                info.getAcqsanitaria());
        
        return new WSReturn<DettagliContrattoTeleriscaldamento>(r.getCodice(), "getDettagliContrattoTeleriscaldamento", d, r.getMessaggi());
    }
    
    
    
    /**
     * Torna le letture per il contratto e l'anno indicato
     * 
     * @param contratto il numero del contratto
     * @param anno l'anno nel quale cercare le letture
     * @return la lista delle letture
     */
    public WSReturn<List<Lettura>> getLetture(String contratto, String anno) {
        String bp = bpCliente;
        WSReturn<ZptTtLett> ls = getLetturaSvuotamento(bp, contratto, true, anno);
        ZptTtLett info = ls.getRitorno();
        ArrayList<Lettura> letture = new ArrayList<Lettura>();
        for (ZptStLett lettura : info.getItem()) {
            letture.add(new Lettura(
                    removePadding(lettura.getContatore()), 
                    lettura.getRegistro(), 
                    lettura.getData(), 
                    lettura.getValore().toPlainString(), 
                    lettura.getTipologia()));
        }
        return new WSReturn<List<Lettura>>(ls.getCodice(), "getLetture", letture, ls.getMessaggi());
    }
    
    /**
     * Torna gli svuotamenti per il contratto e l'anno indicato
     * 
     * @param contratto il numero del contratto
     * @param anno l'anno nel quale cercare le letture
     * @return la lista degli svuotamenti
     */
    public WSReturn<List<Svuotamento>> getSvuotamenti(String contratto, String startDate,String endDate) {
        String bp = bpCliente;
        WSReturn<ZptTtLett> ls = getLetturaSvuotamento(bp, contratto, "2", startDate, endDate);
        ZptTtLett info = ls.getRitorno();
        ArrayList<Svuotamento> svuotamenti = new ArrayList<Svuotamento>();
        for (ZptStLett svuotamento : info.getItem()) {
            svuotamenti.add(new Svuotamento(
                    removePadding(svuotamento.getNumserie()), 
                    svuotamento.getData(),
                    svuotamento.getOra().toString(),
                    svuotamento.getValore().toPlainString()));
        }
        return new WSReturn<List<Svuotamento>>(ls.getCodice(), "getSvuotamenti", svuotamenti, ls.getMessaggi());
    }
    
    public WSReturn<List<Sacchetti>> getSacchetti(String contratto, String startDate, String endDate) {
        String bp = bpCliente;
        WSReturn<ZptTtLett> ls = getLetturaSvuotamento(bp, contratto, "3",   startDate, endDate);
        ZptTtLett info = ls.getRitorno();
        ArrayList<Sacchetti> sacchetti = new ArrayList<Sacchetti>();
        for (ZptStLett svuotamento : info.getItem()) {
        	sacchetti.add(new Sacchetti(
                    removePadding(svuotamento.getNumserie()), 
                    svuotamento.getData(),
                    svuotamento.getOra().toString(),
                    svuotamento.getValore().toPlainString()));
        }
        return new WSReturn<List<Sacchetti>>(ls.getCodice(), "getSvuotamenti", sacchetti, ls.getMessaggi());
    }
    
    
    
    
    
    
    /**
     * Torna l'elenco dei bidoni legati al contratto
     * N.B.: sarebbe bene aggiungere anche il BP ai parametri in modo da
     * rendere più sicuro il metodo
     * 
     * @param contratto il numero del contratto
     * @return la lista dei bidoni
     */
    public WSReturn<List<Bidone>> getElencoBidoni(String contratto) {
        String bp = bpCliente;
        debug("getElencoBidoni", contratto);
        
        java.lang.String _zptBidoni_iPartner = bp;
        java.lang.String _zptBidoni_iVertrag = checkLenght(contratto, Constants.DIM_C, true);
        javax.xml.ws.Holder<java.lang.String> _zptBidoni_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtBidoni> _zptBidoni_etBidoni = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtBidoni>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptBidoni_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        portISU.zptBidoni(_zptBidoni_iPartner, _zptBidoni_iVertrag, _zptBidoni_eReturn, _zptBidoni_etBidoni, _zptBidoni_etMess);

        debug("zptBidoni._zptBidoni_eReturn=", _zptBidoni_eReturn.value);
        debug("zptBidoni._zptBidoni_etBidoni=", _zptBidoni_etBidoni.value);
        debugMsg("zptBidoni._zptBidoni_etMess=", _zptBidoni_etMess.value.getItem());
        
        ArrayList<Bidone> bidoni = new ArrayList<Bidone>();
        for (ZptStBidoni bidone : _zptBidoni_etBidoni.value.getItem()) {
            bidoni.add(new Bidone(removePadding(bidone.getNumserie()), bidone.getDescBid()));
        }
//        return bidoni;
        return new WSReturn<List<Bidone>>(_zptBidoni_eReturn.value, "getElencoBidoni", bidoni, _zptBidoni_etMess.value.getItem());
    }
    
    
	 public WSReturn<List<Sacchetti>> getElencoSacchetti(String contratto)
	 {
		 String bp = bpCliente;
	        debug("getElencoBidoni", contratto);
	        
	        java.lang.String _zptBidoni_iPartner = bp;
	        java.lang.String _zptBidoni_iVertrag = checkLenght(contratto, Constants.DIM_C, true);
	        javax.xml.ws.Holder<java.lang.String> _zptBidoni_eReturn = new javax.xml.ws.Holder<java.lang.String>();
	        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtBidoni> _zptBidoni_etBidoni = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtBidoni>();
	        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptBidoni_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
	        portISU.zptSacchetti(_zptBidoni_iPartner, _zptBidoni_iVertrag, _zptBidoni_eReturn, _zptBidoni_etBidoni, _zptBidoni_etMess);

	        debug("zptBidoni._zptBidoni_eReturn=", _zptBidoni_eReturn.value);
	        debug("zptBidoni._zptBidoni_etBidoni=", _zptBidoni_etBidoni.value);
	        debugMsg("zptBidoni._zptBidoni_etMess=", _zptBidoni_etMess.value.getItem());
	        
	        ArrayList<Sacchetti> bidoni = new ArrayList<Sacchetti>();
	        for (ZptStBidoni bidone : _zptBidoni_etBidoni.value.getItem()) {
	            bidoni.add(new Sacchetti(removePadding(bidone.getNumserie()), bidone.getDescBid()));
	        }
		 
		 
	        return new WSReturn<List<Sacchetti>>(_zptBidoni_eReturn.value, "getElencoBidoni", bidoni, _zptBidoni_etMess.value.getItem());
	 }
    
    
    /**
     * Aggiunge un indirizzo email
     * 
     * @param bp il bp dell'utente
     * @param email l'indirizzo email da aggiungere
     * @return un messaggio tornato dal web service
     */
    public WSReturn addEmail(String bp, String email) {
        debug("addEmail", bp, email);
        
        java.lang.String _zptAddMail_iEmail = email.toLowerCase(Locale.ENGLISH);
        java.lang.String _zptAddMail_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptAddMail_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptAddMail_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptAddMail(_zptAddMail_iEmail, _zptAddMail_iPartner, _zptAddMail_eReturn, _zptAddMail_etMess);

        debug("zptAddMail._zptAddMail_eReturn=", _zptAddMail_eReturn.value);
        debugMsg("zptAddMail._zptAddMail_etMess=", _zptAddMail_etMess.value.getItem());
        
//        return getMessaggiWS(_zptAddMail_etMess.value.getItem());
        return new WSReturn<Object>(_zptAddMail_eReturn.value, "addEmail", null, _zptAddMail_etMess.value.getItem());
    }
    
    /**
     * Torna l'elenco delle service request dell'utente
     * 
     * @param dataDa l'eventuale data di inizio periodo
     * @param dataA l'eventuale data di fine periodo
     * @return la lista delle richieste di servizio
     */
    public WSReturn<List<ServiceRequest>> getElencoServiceRequest(String dataDa, String dataA) {
        //String bp = bpCliente;
    	String bp = bpUtenza;
        debug("getElencoServiceRequest", bp, dataDa, dataA);
        
        java.lang.String _zptSrList_iDateFrom = dataDa;
        java.lang.String _zptSrList_iDateTo = dataA;
        java.lang.String _zptSrList_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptSrList_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptSrList_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtSr> _zptSrList_etSr = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtSr>();
        portCRM.zptSrList(_zptSrList_iDateFrom, _zptSrList_iDateTo, _zptSrList_iPartner, _zptSrList_eReturn, _zptSrList_etMess, _zptSrList_etSr);

        debug("zptSrList._zptSrList_eReturn=", _zptSrList_eReturn.value);
        debugMsg("zptSrList._zptSrList_etMess=", _zptSrList_etMess.value.getItem());
        debug("zptSrList._zptSrList_etSr=", _zptSrList_etSr.value);
        
        ArrayList<ServiceRequest> elenco = new ArrayList<ServiceRequest>();
        
        for (ZptStSr sr : _zptSrList_etSr.value.getItem()) {
            elenco.add(
                    new ServiceRequest(
                        removePadding(sr.getObjectId()), 
                        sr.getStatus(), 
                        sr.getType(), 
                        sr.getOpendate(), 
                        sr.getClosedate(), 
                        sr.getQuestionText(),
                        sr.getAnswerTest()));
        }
        
        return new WSReturn<List<ServiceRequest>>(_zptSrList_eReturn.value, "getElencoServiceRequest", elenco, _zptSrList_etMess.value.getItem());
    }
    
    /**
     * Torna l'elenco dei condomini associati al bp dell'amministratore
     * 
     * @return  la lista dei condomini
     */
    public WSReturn<List<Condominio>> getElencoCondomini() {
        String bp = bpUtenza;
        debug("getElencoCondomini", bp);
        
        java.lang.String _zptBuildingList_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptBuildingList_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtCond> _zptBuildingList_etCondominii = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtCond>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptBuildingList_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptBuildingList(_zptBuildingList_iPartner, _zptBuildingList_eReturn, _zptBuildingList_etCondominii, _zptBuildingList_etMess);

        debug("zptBuildingList._zptBuildingList_eReturn=", _zptBuildingList_eReturn.value);
        debug("zptBuildingList._zptBuildingList_etCondominii=", _zptBuildingList_etCondominii.value);
        debugMsg("zptBuildingList._zptBuildingList_etMess=", _zptBuildingList_etMess.value.getItem());
        
        ArrayList<Condominio> elenco = new ArrayList<Condominio>();
        
        for (ZptStCond c : _zptBuildingList_etCondominii.value.getItem()) {
            elenco.add(new Condominio(
                    c.getPartnerNumber(), 
                    c.getNameGrp1(), 
                    c.getNameGrp2(), 
                    c.getCountry(),
                    c.getRegion(),
                    c.getCity1(),
                    c.getCity2(),
                    c.getPostCode1(),
                    c.getStreet(), 
                    c.getHousenum(),
                    c.getHousenum2()));
        }
        
        return new WSReturn<List<Condominio>>(_zptBuildingList_eReturn.value, "getElencoCondomini", elenco, _zptBuildingList_etMess.value.getItem());
    }

    /**
     * Crea la service request per la richiesta di aggiunta o rimozione dei
     * condomini
     * 
     * @param condomini la lista dei condomini da aggiungere o rimuovere
     * @param aggiungi true se i condomini vanno aggiunti, false se vanno rimossi
     * @return un messaggio tornato dal web service
     */
    public WSReturn aggiungiRimuoviCondomini(List<Condominio> condomini, boolean aggiunta) {
        String bp = bpUtenza;
        debug("aggiungiRimuoviCondomini", bp, condomini);
        
        /*StringBuilder sb = new StringBuilder();
        sb.append((aggiunta?"Aggiunta":"Rimozione")).append(" condomini: ");
        for (Condominio condominio : condomini) {
        	sb.append(condominio);
        }*/
        
        String s = (aggiunta?"Aggiunta":"Rimozione");
        s += " condomini:$";
        for (Condominio condominio : condomini) {
        	s += condominio.toString();
        }
        
        debug("aggiungiRimuoviCondomini testo", s);
        
        String _zptServiceRequestCreate_iCateg1 = "";
        String _zptServiceRequestCreate_iCateg2 = "";
        String _zptServiceRequestCreate_iInterloc = bpUtenza;
        String _zptServiceRequestCreate_iNumciv = "";
        String _zptServiceRequestCreate_iNumcivExtension = "";// lleone - modifica estenzione numero civ
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = s;
        String _zptServiceRequestCreate_iTypSr = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_AGGIUNGI_RIMUOVI_CONDOMINI;
        String _zptServiceRequestCreate_iVkont = "";
        String _zptServiceRequestCreate_iVertrag = "";
        ZptStBpData _zptServiceRequestCreate_isBp = null;
        ZptStCond _zptServiceRequestCreate_isCond = null;
        ZptStDc _zptServiceRequestCreate_isDc = null;
        ZptStRecapito _zptServiceRequestCreate_isRec = null;
		String _zptServiceRequestCreate_iJahiaType = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
        portCRM.zptServiceRequestCreate(
                _zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, 
                _zptServiceRequestCreate_iInterloc, _zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv, 
                _zptServiceRequestCreate_iNumcivExtension,
                _zptServiceRequestCreate_iPartner, _zptServiceRequestCreate_iText, 
                _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag, _zptServiceRequestCreate_iVkont, 
                _zptServiceRequestCreate_isBp, _zptServiceRequestCreate_isCond, 
                _zptServiceRequestCreate_isDc, _zptServiceRequestCreate_isRec, 
                _zptServiceRequestCreate_eReturn, _zptServiceRequestCreate_eSrId,_zptServiceRequestCreate_esrNum, _zptServiceRequestCreate_etMess);

        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
//        return getMessaggiWS(_zptServiceRequestCreate_etMess.value.getItem());
        return new WSReturn<Object>(_zptServiceRequestCreate_eReturn.value, "aggiungiRimuoviCondomini", null, _zptServiceRequestCreate_etMess.value.getItem());
    }
    
    /**
     * Blocca il nome utente relativo al bp indicato
     * 
     * @return il bp dell'utente bloccato
     */
    public WSReturn<String> cancellaUsername(String bp) {
        debug("cancellaUsername", bp);
        java.lang.String _zptDeleteUsername_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptDeleteUsername_ePartner = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptDeleteUsername_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptDeleteUsername_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptDeleteUsername(_zptDeleteUsername_iPartner, _zptDeleteUsername_ePartner, _zptDeleteUsername_eReturn, _zptDeleteUsername_etMess);

        debug("zptDeleteUsername._zptDeleteUsername_ePartner=", _zptDeleteUsername_ePartner.value);
        debug("zptDeleteUsername._zptDeleteUsername_eReturn=", _zptDeleteUsername_eReturn.value);
        debugMsg("zptDeleteUsername._zptDeleteUsername_etMess=", _zptDeleteUsername_etMess.value.getItem());
        
//        return _zptDeleteUsername_ePartner.value;
        return new WSReturn<String>(_zptDeleteUsername_eReturn.value, "cancellaUsername", _zptDeleteUsername_ePartner.value, _zptDeleteUsername_etMess.value.getItem());
    }
    
    /**
     * Crea un nuovo utente e memorizza nei dati anagrafici il bp assegnato
     *
     * @param cc il conto contrattuale
     * @param username il nome utente
     * @param email l'indirizzo di posta elettrinica
     * @param codiceFiscale il codice fiscale
     * @param partitaIva la partita iva
     * @param datiAnagrafici i dati anagrafici
     * @param sblocca un flag che permette di richiedere lo sblocco dell'utente
     * @return un messaggio tornato dal web service
     */
    public WSReturn<Message> creaUsername(String cc, String username, String email, String codiceFiscale, String partitaIva, DatiAnagrafici datiAnagrafici, DatiAnagrafici datiAnagraficiAziendaEnte, boolean sblocca) {
        debug("creaUsername", datiAnagrafici);
 
        java.lang.String _zptCreateUsername_iBpType = toUpperCase(datiAnagrafici.getTipoBP());
        java.lang.String _zptCreateUsername_iCc = toUpperCase(checkLenght(cc, Constants.DIM_CC, true));
        java.lang.String _zptCreateUsername_iCf = codiceFiscale!=null?checkLenght(codiceFiscale, Constants.DIM_CF, false).toUpperCase():null;
        java.lang.String _zptCreateUsername_iMail = checkLenght(email.toLowerCase(Locale.ENGLISH), Constants.DIM_MAIL, false);
        java.lang.String _zptCreateUsername_iPi = toUpperCase(checkLenght(partitaIva, Constants.DIM_PI, false));
        java.lang.String _zptCreateUsername_iUnlock = sblocca ? "X" : "";
        java.lang.String _zptCreateUsername_iUsername = checkLenght(username, Constants.DIM_USERNAME, false);
        com.alpenite.tea.communicationLayer.ws.crm.ZptTtBpData _zptCreateUsername_itBpData = new ZptTtBpData();
  
        // creo i dati anagrafici dell'utente
        ZptStBpData dati = new ZptStBpData();
        dati.setBpType(toUpperCase(datiAnagrafici.getTipoBP()));
        String cel = datiAnagrafici.getPrefissoCel()!=null?datiAnagrafici.getPrefissoCel():"+39";
        cel = cel.concat("-");
        if(datiAnagrafici.getCellulare()==null || datiAnagrafici.getCellulare().equals(""))
        	cel="";
        else
        	cel = cel.concat(datiAnagrafici.getCellulare());
        dati.setCellphone(cel);
        //dati.setCellphone(datiAnagrafici.getCellulare());
   
        if(datiAnagrafici.getCodiceFiscale()!=null)
        dati.setCf(toUpperCase(datiAnagrafici.getCodiceFiscale()));
     
        dati.setCity1(toUpperCase(datiAnagrafici.getComune()));
        dati.setCity2(toUpperCase(datiAnagrafici.getFrazione()));
        dati.setCountry(toUpperCase(datiAnagrafici.getPaese()));
        dati.setCap(toUpperCase(datiAnagrafici.getCap()));
        dati.setHousenum(toUpperCase(datiAnagrafici.getNumeroCivico()));
        dati.setHousenum2(toUpperCase(datiAnagrafici.getEstenzioneNumeroCivico()));
        dati.setName(toUpperCase(datiAnagrafici.getNome()));
        dati.setNameOrg1(toUpperCase(datiAnagrafici.getRagioneSociale1()));
        dati.setNameOrg2(toUpperCase(datiAnagrafici.getRagioneSociale2()));
        dati.setNameOrg3(toUpperCase(datiAnagrafici.getRagioneSociale3()));
        dati.setNameOrg4(toUpperCase(datiAnagrafici.getRagioneSociale4()));
        dati.setPartnerNumber(toUpperCase(datiAnagrafici.getCodiceBP()));
        dati.setPi(toUpperCase(datiAnagrafici.getPartitaIVA()));
        dati.setPrvcy(toUpperCase(datiAnagrafici.getPrivacy()));
        dati.setPrvcyEmail(toUpperCase(datiAnagrafici.getPrivacyEmail()));
        dati.setPrvcyMail(toUpperCase(datiAnagrafici.getPrivacyMail()));
        dati.setPrvcyTel(toUpperCase(datiAnagrafici.getPrivacyTel()));
        dati.setRegion(toUpperCase(datiAnagrafici.getProvincia()));
        dati.setStreet(toUpperCase(datiAnagrafici.getVia()));
        dati.setSurname(toUpperCase(datiAnagrafici.getCognome()));
        String tel = datiAnagrafici.getPrefissoTel()!=null?datiAnagrafici.getPrefissoTel():"+39";
        tel = tel.concat("-");
        if(datiAnagrafici.getTelefono()==null || datiAnagrafici.getTelefono().equals("")){
        	tel = "";
        }else{
        	tel = tel.concat(datiAnagrafici.getTelefono());
        }
        dati.setTelephone(tel);
        //dati.setTelephone(datiAnagrafici.getTelefono());
        dati.setRole(toUpperCase(datiAnagrafici.getRuolo()));
        _zptCreateUsername_itBpData.getItem().add(dati);

        // creo i dati anagrafici dell'azienda
        if (datiAnagraficiAziendaEnte != null) {
            ZptStBpData datiAziendaEnte = new ZptStBpData();
            datiAziendaEnte.setBpType(toUpperCase(datiAnagraficiAziendaEnte.getTipoBP()));
            String cel2 = datiAnagraficiAziendaEnte.getPrefissoCel()!=null?datiAnagraficiAziendaEnte.getPrefissoCel():"+39";
            cel2 = cel2.concat("-");
            cel2 = cel2.concat(datiAnagraficiAziendaEnte.getCellulare());
            if(datiAnagraficiAziendaEnte.getCellulare().equals(""))
            	cel2="";
            datiAziendaEnte.setCellphone(cel2);
            //datiAziendaEnte.setCellphone(datiAnagraficiAziendaEnte.getCellulare());
            datiAziendaEnte.setCf(toUpperCase(datiAnagraficiAziendaEnte.getCodiceFiscale().toUpperCase()));
            datiAziendaEnte.setCity1(toUpperCase(datiAnagraficiAziendaEnte.getComune()));
            datiAziendaEnte.setCity2(toUpperCase(datiAnagraficiAziendaEnte.getFrazione()));
            datiAziendaEnte.setCountry(toUpperCase(datiAnagraficiAziendaEnte.getPaese()));
            datiAziendaEnte.setCap(toUpperCase(datiAnagraficiAziendaEnte.getCap()));
            datiAziendaEnte.setHousenum(toUpperCase(datiAnagraficiAziendaEnte.getNumeroCivico()));
            datiAziendaEnte.setHousenum2(toUpperCase(datiAnagraficiAziendaEnte.getEstenzioneNumeroCivico()));
            datiAziendaEnte.setName(toUpperCase(datiAnagraficiAziendaEnte.getNome()));
            datiAziendaEnte.setNameOrg1(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale1()));
            datiAziendaEnte.setNameOrg2(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale2()));
            datiAziendaEnte.setNameOrg3(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale3()));
            datiAziendaEnte.setNameOrg4(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale4()));
            datiAziendaEnte.setPartnerNumber(toUpperCase(datiAnagraficiAziendaEnte.getCodiceBP()));
            datiAziendaEnte.setPi(toUpperCase(datiAnagraficiAziendaEnte.getPartitaIVA()));
            datiAziendaEnte.setPrvcy(toUpperCase(datiAnagraficiAziendaEnte.getPrivacy()));
            datiAziendaEnte.setPrvcyEmail(toUpperCase(datiAnagraficiAziendaEnte.getPrivacyEmail()));
            datiAziendaEnte.setPrvcyMail(toUpperCase(datiAnagraficiAziendaEnte.getPrivacyMail()));
            datiAziendaEnte.setPrvcyTel(toUpperCase(datiAnagraficiAziendaEnte.getPrivacyTel()));
            datiAziendaEnte.setRegion(toUpperCase(datiAnagraficiAziendaEnte.getProvincia()));
            datiAziendaEnte.setStreet(toUpperCase(datiAnagraficiAziendaEnte.getVia()));
            datiAziendaEnte.setSurname(toUpperCase(datiAnagraficiAziendaEnte.getCognome()));
            String tel2 = datiAnagraficiAziendaEnte.getPrefissoTel()!=null?datiAnagraficiAziendaEnte.getPrefissoTel():"+39";
            tel2 = tel2.concat("-");
            tel2 = tel2.concat(datiAnagraficiAziendaEnte.getTelefono());
            if(datiAnagraficiAziendaEnte.getTelefono().equals("")){
            	tel2 = "";
            }
            datiAziendaEnte.setTelephone(tel2);
            //datiAziendaEnte.setTelephone(datiAnagraficiAziendaEnte.getTelefono());
            _zptCreateUsername_itBpData.getItem().add(datiAziendaEnte);
        }
        
        javax.xml.ws.Holder<java.lang.String> _zptCreateUsername_eName = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCreateUsername_ePartner = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCreateUsername_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCreateUsername_eSurname = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptCreateUsername_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptCreateUsername(_zptCreateUsername_iBpType, _zptCreateUsername_iCc, _zptCreateUsername_iCf, _zptCreateUsername_iMail, _zptCreateUsername_iPi, _zptCreateUsername_iUnlock, _zptCreateUsername_iUsername, _zptCreateUsername_itBpData, _zptCreateUsername_eName, _zptCreateUsername_ePartner, _zptCreateUsername_eReturn, _zptCreateUsername_eSurname, _zptCreateUsername_etMess);
      
        debug("zptCreateUsername._zptCreateUsername_eName=", _zptCreateUsername_eName.value);
        debug("zptCreateUsername._zptCreateUsername_ePartner=", _zptCreateUsername_ePartner.value);
        debug("zptCreateUsername._zptCreateUsername_eReturn=", _zptCreateUsername_eReturn.value);
        debug("zptCreateUsername._zptCreateUsername_eSurname=", _zptCreateUsername_eSurname.value);
        debugMsg("zptCreateUsername._zptCreateUsername_etMess=", _zptCreateUsername_etMess.value.getItem());

        // inserisco il codice bp assegnato nei dati anagrafici
        datiAnagrafici.setCodiceBP(_zptCreateUsername_ePartner.value);

        // se andato a buon fine, aggiungo la mail scritta in fase di registrazione
        //if(_zptCreateUsername_eReturn.value.equals(Constants.SAP_SUCCESS)){
        //	this.addEmail(_zptCreateUsername_ePartner.value, _zptCreateUsername_iMail);
        //}
        
        Message m = new Message(_zptCreateUsername_eReturn.value, _zptCreateUsername_etMess.value.getItem(), _zptCreateUsername_ePartner.value, _zptCreateUsername_eName.value, _zptCreateUsername_eSurname.value);
        return new WSReturn<Message>(_zptCreateUsername_eReturn.value, "creaUsername", m, _zptCreateUsername_etMess.value.getItem());
    }
    
    /**
     * Utilizzato per sbloccare un utente bloccato in precedenza
     * dall'amministratore 17/10/2012 - Matteo Zanioli
     *
     * @param username il nome utente
     * @return il messaggio tornato dal web service
     */
    public WSReturn<Message> creaUsername(String bp, String username, String mail) {
    	DatiAnagrafici datiAnagrafici = new DatiAnagrafici();
    	datiAnagrafici.setCodiceBP(bp);
        return creaUsername("", username, mail, "", "", datiAnagrafici, null, true);
    }
    
    /**
     * Controlla l'univocità del prospect
     * 
     * @param tipoBP il tipo del bp
     * @param email l'indirizzo di posta elettrinica
     * @param partitaIva la partita iva
     * @return il messaggio tornato dal , eMimetyp, eReturn, etMess);oadForm(doweb service
     */
    public WSReturn<Message> controllaProspect(String tipoBP, String email, String partitaIva) {
        debug("controllaProspect", tipoBP, email, partitaIva);
        java.lang.String _zptCheckProspect_iBpType = tipoBP;
        java.lang.String _zptCheckProspect_iMail = checkLenght(email.toLowerCase(Locale.ENGLISH), Constants.DIM_MAIL, false);
        java.lang.String _zptCheckProspect_iPi = checkLenght(partitaIva, Constants.DIM_PI, false);
        javax.xml.ws.Holder<java.lang.String> _zptCheckProspect_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptCheckProspect_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptCheckProspect(_zptCheckProspect_iBpType, _zptCheckProspect_iMail, _zptCheckProspect_iPi, _zptCheckProspect_eReturn, _zptCheckProspect_etMess);
        
        debug("zptCheckProspect._zptCheckProspect_eReturn=", _zptCheckProspect_eReturn.value);
        debugMsg("zptCheckProspect._zptCheckProspect_etMess=", _zptCheckProspect_etMess.value.getItem());        
        for(com.alpenite.tea.communicationLayer.ws.crm.ZptStMess mess:_zptCheckProspect_etMess.value.getItem()){
            System.out.println(mess.getId()+" "+mess.getMsgNumber()+" "+mess.getType()+" "+mess.getMessage());
           }
        Message m = new Message(_zptCheckProspect_eReturn.value, _zptCheckProspect_etMess.value.getItem());
        return new WSReturn<Message>(_zptCheckProspect_eReturn.value, "controllaProspect", m, _zptCheckProspect_etMess.value.getItem());
    }
    
    /**
     * Controlla l'univocità del bp del prospect ed eventualmente lo crea
     *
     * @param username il nome utente
     * @param email l'indirizzo email
     * @param datiAnagrafici i dati anagrafici
     * @return il messaggio tornato dal web service
     */
    public WSReturn<Message> creaProspect(String username, String email, DatiAnagrafici datiAnagrafici, DatiAnagrafici datiAnagraficiAziendaEnte) {
       
    	java.lang.String _zptCreateProspect_iMail = checkLenght(email.toLowerCase(Locale.ENGLISH),Constants.DIM_MAIL, false);
        java.lang.String _zptCreateProspect_iUsername = toUpperCase(checkLenght(username,Constants.DIM_USERNAME, false));
        com.alpenite.tea.communicationLayer.ws.crm.ZptTtBpData _zptCreateProspect_itBpData = new ZptTtBpData();
       
        // creo i dati anagrafici dell'utente
        ZptStBpData dati = new ZptStBpData();
        dati.setBpType(toUpperCase(datiAnagrafici.getTipoBP()));
        String cel = datiAnagrafici.getPrefissoCel()!=null?datiAnagrafici.getPrefissoCel():"+39";
        cel = cel.concat("-");
        if(datiAnagrafici.getCellulare()==null || datiAnagrafici.getCellulare().equals(""))
        	cel="";
        else
        	cel = cel.concat(datiAnagrafici.getCellulare());
       
        dati.setCellphone(cel);
        //dati.setCellphone(datiAnagrafici.getCellulare());
        
        if(datiAnagrafici.getCodiceFiscale() != null)
        dati.setCf(toUpperCase(datiAnagrafici.getCodiceFiscale())); 
     
        dati.setCity1(toUpperCase(datiAnagrafici.getComune()));
        dati.setCity2(toUpperCase(datiAnagrafici.getFrazione()));
        dati.setCountry(toUpperCase(datiAnagrafici.getPaese()));
        dati.setCap(toUpperCase(datiAnagrafici.getCap()));
        dati.setHousenum(toUpperCase(datiAnagrafici.getNumeroCivico()));
        dati.setHousenum2(toUpperCase(datiAnagrafici.getEstenzioneNumeroCivico()));
        dati.setName(toUpperCase(datiAnagrafici.getNome()));
        dati.setNameOrg1(toUpperCase(datiAnagrafici.getRagioneSociale1()));
        dati.setNameOrg2(toUpperCase(datiAnagrafici.getRagioneSociale2()));
        dati.setNameOrg3(toUpperCase(datiAnagrafici.getRagioneSociale3()));
        dati.setNameOrg4(toUpperCase(datiAnagrafici.getRagioneSociale4()));
        dati.setPartnerNumber(toUpperCase(datiAnagrafici.getCodiceBP()));
        dati.setPi(toUpperCase(datiAnagrafici.getPartitaIVA()));
        dati.setPrvcy(toUpperCase(datiAnagrafici.getPrivacy()));
        dati.setPrvcyEmail(toUpperCase(datiAnagrafici.getPrivacyEmail()));
        dati.setPrvcyMail(toUpperCase(datiAnagrafici.getPrivacyMail()));
        dati.setPrvcyTel(toUpperCase(datiAnagrafici.getPrivacyTel()));
        dati.setRegion(toUpperCase(datiAnagrafici.getProvincia()));
        dati.setStreet(toUpperCase(datiAnagrafici.getVia()));
        dati.setSurname(toUpperCase(datiAnagrafici.getCognome()));
        String tel = datiAnagrafici.getPrefissoTel()!=null?datiAnagrafici.getPrefissoTel():"+39";
        tel = tel.concat("-");
        if(datiAnagrafici.getTelefono()==null || datiAnagrafici.getTelefono().equals("")){
        	tel = "";
        }else{
        	tel = tel.concat(datiAnagrafici.getTelefono());
        }
        dati.setTelephone(tel);
        //dati.setTelephone(datiAnagrafici.getTelefono());
        dati.setRole(toUpperCase(datiAnagrafici.getRuolo()));
        _zptCreateProspect_itBpData.getItem().add(dati);
        // creo i dati anagrafici dell'azienda
        if (datiAnagraficiAziendaEnte != null) {
            ZptStBpData datiAziendaEnte = new ZptStBpData();
            datiAziendaEnte.setBpType(toUpperCase(datiAnagraficiAziendaEnte.getTipoBP()));
            String cel2 = datiAnagraficiAziendaEnte.getPrefissoCel()!=null?datiAnagraficiAziendaEnte.getPrefissoCel():"+39";
            cel2 = cel2.concat("-");
            cel2 = cel2.concat(datiAnagraficiAziendaEnte.getCellulare());
            if(datiAnagraficiAziendaEnte.getCellulare().equals(""))
            	cel2="";
            datiAziendaEnte.setCellphone(cel2);
            datiAziendaEnte.setCf(toUpperCase(datiAnagraficiAziendaEnte.getCodiceFiscale().toUpperCase()));
            datiAziendaEnte.setCity1(toUpperCase(datiAnagraficiAziendaEnte.getComune()));
            datiAziendaEnte.setCity2(toUpperCase(datiAnagraficiAziendaEnte.getFrazione()));
            datiAziendaEnte.setCountry(toUpperCase(datiAnagraficiAziendaEnte.getPaese()));
            datiAziendaEnte.setCap(toUpperCase(datiAnagraficiAziendaEnte.getCap()));
            datiAziendaEnte.setHousenum(toUpperCase(datiAnagraficiAziendaEnte.getNumeroCivico()));
            datiAziendaEnte.setHousenum2(toUpperCase(datiAnagraficiAziendaEnte.getNumeroCivico()));
            datiAziendaEnte.setName(toUpperCase(datiAnagraficiAziendaEnte.getNome()));
            datiAziendaEnte.setNameOrg1(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale1()));
            datiAziendaEnte.setNameOrg2(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale2()));
            datiAziendaEnte.setNameOrg3(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale3()));
            datiAziendaEnte.setNameOrg4(toUpperCase(datiAnagraficiAziendaEnte.getRagioneSociale4()));
            datiAziendaEnte.setPartnerNumber(toUpperCase(datiAnagraficiAziendaEnte.getCodiceBP()));
            datiAziendaEnte.setPi(toUpperCase(datiAnagraficiAziendaEnte.getPartitaIVA()));
            datiAziendaEnte.setPrvcy(toUpperCase(datiAnagraficiAziendaEnte.getPrivacy()));
            datiAziendaEnte.setPrvcyEmail(toUpperCase(datiAnagraficiAziendaEnte.getPrivacyEmail()));
            datiAziendaEnte.setPrvcyMail(toUpperCase(datiAnagraficiAziendaEnte.getPrivacyMail()));
            datiAziendaEnte.setPrvcyTel(toUpperCase(datiAnagraficiAziendaEnte.getPrivacyTel()));
            datiAziendaEnte.setRegion(toUpperCase(datiAnagraficiAziendaEnte.getProvincia()));
            datiAziendaEnte.setStreet(toUpperCase(datiAnagraficiAziendaEnte.getVia()));
            datiAziendaEnte.setSurname(toUpperCase(datiAnagraficiAziendaEnte.getCognome()));
            String tel2 = datiAnagraficiAziendaEnte.getPrefissoTel()!=null?datiAnagraficiAziendaEnte.getPrefissoTel():"+39";
            tel2 = tel2.concat("-");
            tel2 = tel2.concat(datiAnagraficiAziendaEnte.getTelefono());
            if(datiAnagraficiAziendaEnte.getTelefono().equals("")){
            	tel2 = "";
            }
            datiAziendaEnte.setTelephone(tel2);
            _zptCreateProspect_itBpData.getItem().add(datiAziendaEnte);
        }
        
        javax.xml.ws.Holder<java.lang.String> _zptCreateProspect_eName = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCreateProspect_ePartner = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCreateProspect_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCreateProspect_eSurname = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptCreateProspect_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        
        portCRM.zptCreateProspect(_zptCreateProspect_iMail, _zptCreateProspect_iUsername, _zptCreateProspect_itBpData, _zptCreateProspect_eName, _zptCreateProspect_ePartner, _zptCreateProspect_eReturn, _zptCreateProspect_eSurname, _zptCreateProspect_etMess);

        debug("zptCreateProspect._zptCreateProspect_eName=", _zptCreateProspect_eName.value);
        debug("zptCreateProspect._zptCreateProspect_ePartner=", _zptCreateProspect_ePartner.value);
        debug("zptCreateProspect._zptCreateProspect_eReturn=", _zptCreateProspect_eReturn.value);
        debug("zptCreateProspect._zptCreateProspect_eSurname=", _zptCreateProspect_eSurname.value);
        debugMsg("zptCreateProspect._zptCreateProspect_etMess=", _zptCreateProspect_etMess.value.getItem());

        // se andato a buon fine, aggiungo la mail scritta in fase di registrazione
        if(_zptCreateProspect_eReturn.value.equals(Constants.SAP_SUCCESS)){
        	this.addEmail(_zptCreateProspect_ePartner.value, _zptCreateProspect_iMail);
        }
        
        Message m = new Message(_zptCreateProspect_eReturn.value, _zptCreateProspect_etMess.value.getItem(), _zptCreateProspect_ePartner.value, _zptCreateProspect_eName.value, _zptCreateProspect_eSurname.value);
        return new WSReturn<Message>(_zptCreateProspect_eReturn.value, "creaProspect", m, _zptCreateProspect_etMess.value.getItem());
    }
    
    /**
     * Controlla che l'utenza non sia già associata ad un bp
     * 
     * @param tipoBI il tipo del bp
     * @param cc il conto contrattuale
     * @param email l'indirizzo di posta elettronica
     * @param codiceFiscale il codice fiscale
     * @param partitaIva la partita iva
     * @return il messaggio tornato dal web service
     */
    public WSReturn<Message> controllaUsername(String tipoBI, String cc, String email, String codiceFiscale, String partitaIva) {
        debug("controllaUsername", tipoBI, checkLenght(cc, Constants.DIM_CC, true), email, codiceFiscale, partitaIva);
        
        java.lang.String _zptCheckUsername_iBpType = tipoBI;
        java.lang.String _zptCheckUsername_iCc = checkLenght(cc, Constants.DIM_CC, true);
        java.lang.String _zptCheckUsername_iCf = codiceFiscale!=null?checkLenght(codiceFiscale, Constants.DIM_CF, false).toUpperCase():null;
        java.lang.String _zptCheckUsername_iMail = checkLenght(email.toLowerCase(Locale.ENGLISH), Constants.DIM_MAIL, false);
        java.lang.String _zptCheckUsername_iPi = checkLenght(partitaIva, Constants.DIM_PI, false);
        javax.xml.ws.Holder<java.lang.String> _zptCheckUsername_ePartner = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _zptCheckUsername_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess> _zptCheckUsername_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.crm.ZptTtMess>();
        portCRM.zptCheckUsername(_zptCheckUsername_iBpType, _zptCheckUsername_iCc, _zptCheckUsername_iCf, _zptCheckUsername_iMail, _zptCheckUsername_iPi, _zptCheckUsername_ePartner, _zptCheckUsername_eReturn, _zptCheckUsername_etMess);

        debug("zptCheckUsername._zptCheckUsername_ePartner=", _zptCheckUsername_ePartner.value);
        debug("zptCheckUsername._zptCheckUsername_eReturn=", _zptCheckUsername_eReturn.value);
        debug("zptCheckUsername._zptCheckUsername_etMess=", _zptCheckUsername_etMess.value.getItem()); 
        for(com.alpenite.tea.communicationLayer.ws.crm.ZptStMess mess:_zptCheckUsername_etMess.value.getItem()){
            System.out.println(mess.getId()+" "+mess.getMsgNumber()+" "+mess.getType()+" "+mess.getMessage());
           }
        
        Message m = new Message(_zptCheckUsername_eReturn.value, _zptCheckUsername_etMess.value.getItem(), _zptCheckUsername_ePartner.value);
        return new WSReturn<Message>(_zptCheckUsername_eReturn.value, "controllaUsername", m, _zptCheckUsername_etMess.value.getItem());
    }
    
    /**
     * Effettua la richiesta al backoffice 16/10/2012 - Marco Gaion
     *
     * @param cliente true se la richiesta e'fatta da un cliente, false da un prospect
     * @param testoRichiesta il testo scritto dall'utente che descrive la richiesta
     * @param categoria1 categoria di primo livello della SR
     * @param categoria2 categoria di secondo livello per la SR
     * @return il messaggio tornato dal web service
     */
    public WSReturn<Message> effetuaRichiestaBackoffice(boolean cliente, String testoRichiesta, String categoria1, String categoria2) {
        String bp = bpCliente;
        String strTestoRichiesta = testoRichiesta.replaceAll("\\$", "\\$\\/");
        strTestoRichiesta = testoRichiesta.replace('\n', '$');
        strTestoRichiesta = strTestoRichiesta.replace('\t', ' ');
        strTestoRichiesta = strTestoRichiesta.concat("$");
        debug("effetuaRichiestaBackoffice", bp, cliente, strTestoRichiesta, categoria1, categoria2);
        
        String tipo = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_RICHIESTA_CLIENTE;
        if (!cliente) tipo = Constants.ZPT_SERVICE_REQUEST_CREATE_ITYPSR_RICHIESTA_PROSPECT;
        
        String _zptServiceRequestCreate_iCateg1 = categoria1;
        String _zptServiceRequestCreate_iCateg2 = categoria2;
        String _zptServiceRequestCreate_iInterloc = bpUtenza;
        String _zptServiceRequestCreate_iNumciv = "";
        String _zptServiceRequestCreate_iPartner = bp;
        String _zptServiceRequestCreate_iText = strTestoRichiesta;
        String _zptServiceRequestCreate_iTypSr = tipo;
        String _zptServiceRequestCreate_iVkont = "";
        String _zptServiceRequestCreate_iVertrag = "";
        String _zptServiceRequestCreate_iNumcivExtension = "";// lleone - modifica estenzione numero civ
        ZptStBpData _zptServiceRequestCreate_isBp = null;
        ZptStCond _zptServiceRequestCreate_isCond = null;
        ZptStDc _zptServiceRequestCreate_isDc = null;
        ZptStRecapito _zptServiceRequestCreate_isRec = null;
		String _zptServiceRequestCreate_iJahiaType = null;
        Holder<java.lang.String> _zptServiceRequestCreate_eReturn = new Holder<java.lang.String>();
        Holder<java.lang.String> _zptServiceRequestCreate_eSrId = new Holder<java.lang.String>();
        Holder<byte[]> _zptServiceRequestCreate_esrNum =  new Holder<byte[]>();
        Holder<ZptTtMess> _zptServiceRequestCreate_etMess = new Holder<ZptTtMess>();
        portCRM.zptServiceRequestCreate(
                _zptServiceRequestCreate_iCateg1, _zptServiceRequestCreate_iCateg2, 
                _zptServiceRequestCreate_iInterloc, _zptServiceRequestCreate_iJahiaType, _zptServiceRequestCreate_iNumciv, _zptServiceRequestCreate_iNumcivExtension,
                _zptServiceRequestCreate_iPartner, _zptServiceRequestCreate_iText, 
                _zptServiceRequestCreate_iTypSr, _zptServiceRequestCreate_iVertrag, _zptServiceRequestCreate_iVkont, 
                _zptServiceRequestCreate_isBp, _zptServiceRequestCreate_isCond, 
                _zptServiceRequestCreate_isDc, _zptServiceRequestCreate_isRec, 
                _zptServiceRequestCreate_eReturn, _zptServiceRequestCreate_eSrId, _zptServiceRequestCreate_esrNum,_zptServiceRequestCreate_etMess);

        debug("zptServiceRequestCreate._zptServiceRequestCreate_eReturn=", _zptServiceRequestCreate_eReturn.value);
        debugMsg("zptServiceRequestCreate._zptServiceRequestCreate_etMess=", _zptServiceRequestCreate_etMess.value.getItem());
        
        Message m = new Message(_zptServiceRequestCreate_eReturn.value, _zptServiceRequestCreate_etMess.value.getItem());
        return new WSReturn<Message>(_zptServiceRequestCreate_eReturn.value, "effetuaRichiestaBackoffice", m, _zptServiceRequestCreate_etMess.value.getItem());
    }

    /**
     * 11/11/2012 - Matteo Zanioli
     * Passata una stringa che è un numero toglie gli zeri iniziali
     * 
     * @param String che contiene un numero (generalmente conto contrattuale e contratto)
     * @return String contenente la stringa di prima senza gli zeri iniziali, se la stringa passata
     * non è un numero valido la ritorna così com'è
     */
    private String removePadding(String s){
    	String temp = "";
    	try{
	    	temp = (new Long(s)).toString();
    	}catch(NumberFormatException e){
    		temp = s;
    	}
    	return temp;
    }
    
    /**
     * 11/11/2012 - Matteo Zanioli
     * Controlla la lunghezza della stringa e aggiunge gli zeri iniziali (se mancano) fino a coprire la dimensione passata come parametro
     * @param String valore a cui va aggiunto il padding
     * @param int dimensione che deve avere
     * @param boolean true se vanno aggiunti gli zeri all'inizio della stringa
     * @return la stringa verificata ed eventualmente modificata (se la lunghezza della stringa è superiore alla dimensione prevista viene ridotto il valore inserito)
     */
    private String checkLenght(String s, int dim, boolean padding){
    	if(s == null){
    		return null;
    	}
    	String newString = s;
    	if(s.length() == dim)
    		return s;
    	if(s.length() > dim)
    		return s.substring(Constants.STRING_START, dim);
    	if(padding){
	    	int dimPadding = dim - s.length();
	    	for(int i = 0; i < dimPadding; i++)
	    		newString = "0"+ newString;
    	}
    	return newString;
    }
    
    /**
     * 24/04/2012 - Andrei Bogdan
     * toUpperCase della stringa (se non nulla o vuota)
     * @param String valore a cui va aggiunto il toUpperCase
     * @return la stringa verificata 
     */
    private String toUpperCase(String s)
    {
    	if(s!=null && !s.equals(""))
    	{
    		s = s.toUpperCase();
    	}
    	return s;
    }
    /**
     * Torna l'elenco dei documenti di un certo tipo, eventualmente filtrati
     * per le date indicate
     * 
     * @param dataDa la data iniziale
     * @param dataA la data finale
     * @return la lista dei documenti richiesti
     */
    public WSReturn<List<Documento>> getElencoDocumentiDett(String dataDa, String dataA, String partiteAperte, String fatture ) {
        String bp = bpCliente;
        debug("getElencoDocumentiDett", bp, dataDa, dataA, "partiteAperte="+partiteAperte, "fatture= "+fatture);
        
        DateFormat df = new SimpleDateFormat("y-m-d");
        try {
            if ((dataDa != null && !dataDa.isEmpty() && df.parse(dataDa).before(df.parse("2010-01-01"))) ||
                    (dataA != null && !dataA.isEmpty() && df.parse(dataA).before(df.parse("2010-01-01")))) {
                return new WSReturn<List<Documento>>(Constants.ERRORE_DATA_PRECEDENTE_1110, "getElencoDocumentiDett", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new WSReturn<List<Documento>>(Constants.ERRORE_DATA_PRECEDENTE_1110, "getElencoDocumentiDett", null, null);
        }
        java.lang.String _zptContractListDett_iDatafrom = dataDa;
        java.lang.String _zptContractListDett_iDatato = dataA;
        java.lang.String _zptContractListDett_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptContractListDett_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList> _zptContractListDett_etList = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptContractListDett_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        portISU.zptContractListDett(_zptContractListDett_iDatafrom, _zptContractListDett_iDatato, partiteAperte, fatture, _zptContractListDett_iPartner, _zptContractListDett_eReturn, _zptContractListDett_etList, _zptContractListDett_etMess);
        debug("zptContractListDett._zptContractListDett_eReturn=", _zptContractListDett_eReturn.value);
        debug("zptContractListDett._zptContractListDett_etList=", _zptContractListDett_etList.value);
        debugMsg("zptContractListDett._zptContractListDett_etMess=",_zptContractListDett_etMess.value.getItem());
        
        List<Documento> lista = new ArrayList<Documento>();
        for (ZptStList doc : _zptContractListDett_etList.value.getItem()) {
            lista.add( new Documento("",
                    doc.getNumfatt(), 
                    doc.getDatafatt(),
                    doc.getDatascad(), 
                    doc.getTotalAmnt(),
                    doc.getOpenAmnt(),
                    doc.getPdf(),
                    doc.getPdfObj()));
        }
        
        return new WSReturn<List<Documento>>(_zptContractListDett_eReturn.value, "getElencoDocumentiDett", lista, _zptContractListDett_etMess.value.getItem());        
    }
    
    /**
     * Torna l'elenco dei documenti di un certo tipo, eventualmente filtrati
     * per le date indicate
     * 
     * @param dataDa la data iniziale
     * @param dataA la data finale
     * @return la lista dei documenti richiesti
     */
    public List<Documento> getDettaglioCercaFattura(String dataDa, String dataA, String partiteAperte, String fatture, String numeroFattura ) {
        String bp = bpCliente;
        String ritorno = "";
        debug("getDettaglioCercaFattura", bp, dataDa, dataA, "partiteAperte="+partiteAperte, "fatture= "+fatture, "numerofattura= "+numeroFattura);
        
        DateFormat df = new SimpleDateFormat("y-m-d");
        
        java.lang.String _zptContractListDett_iDatafrom = dataDa;
        java.lang.String _zptContractListDett_iDatato = dataA;
        java.lang.String _zptContractListDett_iPartner = bp;
        javax.xml.ws.Holder<java.lang.String> _zptContractListDett_eReturn = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList> _zptContractListDett_etList = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList>();
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> _zptContractListDett_etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
        Holder<String> eReturn =  new javax.xml.ws.Holder<java.lang.String>();

		String iFlagSfatt = "";
		Holder<ZptTtList2> etList = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtList2>();;
        javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
       
		portISU.zptDettagliFattura(_zptContractListDett_iDatafrom, _zptContractListDett_iDatato, partiteAperte, iFlagSfatt, _zptContractListDett_iPartner, numeroFattura, eReturn, etList, etMess);
        //portISU.zptContractListDett(_zptContractListDett_iDatafrom, _zptContractListDett_iDatato, partiteAperte, fatture, _zptContractListDett_iPartner, _zptContractListDett_eReturn, _zptContractListDett_etList, _zptContractListDett_etMess);
		debug("zptContractListDett._zptContractListDett_eReturn=", _zptContractListDett_eReturn.value);
        debug("zptContractListDett._zptContractListDett_etList=", _zptContractListDett_etList.value);
        //debugMsg("zptContractListDett._zptContractListDett_etMess=",_zptContractListDett_etMess.value.getItem());
        
        List<Documento> lista = new ArrayList<Documento>();
        for (ZptStList2 doc : etList.value.getItem()) {
            lista.add( new Documento(doc.getSocieta(),
            		numeroFattura, 
                    doc.getDatafatt(),
                    doc.getDatascad(), 
                    doc.getTotalAmnt(),
                    doc.getOpenAmnt(),
                   "",
                    ""));
            ritorno = doc.getSocieta();
        }
        
       
        
        
        return lista;       
    }
    
    
    public String getDettaglioContatto(String tipoContatto ) {
        String bp = bpCliente;
        String ritorno = "";
        debug("getDettaglioContatto", tipoContatto);
        
        
        String iVertrag = "";
        iVertrag = tipoContatto;
		Holder<String> eVertrag = new javax.xml.ws.Holder<java.lang.String>();;
		Holder<String> eZzcontatto=  new javax.xml.ws.Holder<java.lang.String>();;
		Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
		String iZzcontatto ="";
		portISU.zptDettagliContatto(iVertrag, iZzcontatto, eVertrag, eZzcontatto, etMess);
		System.out.println("ezcontatto"+eZzcontatto.value);
		return eZzcontatto.value;
        
       
        
        }
    
    public String setDettaglioContatto(String contratto, String  tipoContatto) {
        String bp = bpCliente;
        String ritorno = "";
        debug("getDettaglioContatto", tipoContatto);
        
        
        String iVertrag = "";
        iVertrag = contratto;
		Holder<String> eVertrag = new javax.xml.ws.Holder<java.lang.String>();;
		Holder<String> eZzcontatto=  new javax.xml.ws.Holder<java.lang.String>();;
		Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess> etMess = new javax.xml.ws.Holder<com.alpenite.tea.communicationLayer.ws.isu.ZptTtMess>();
		String iZzcontatto ="";
		iZzcontatto= tipoContatto;
		portISU.zptDettagliContatto(iVertrag, iZzcontatto, eVertrag, eZzcontatto, etMess);
		System.out.println("contatto"+iZzcontatto+" numerocontratto"+iVertrag+" ris:"+eZzcontatto.value);
		return eZzcontatto.value;
        
       
        
        }
    
    
    
       
        
       
    
	
    /**
	 * Torna il tipo SAP del bp "this"
	 * @return ritorna 1 per Persona, 2 per Organizzazione o 3 per Ente.
	 */
    public String getSapType(){
    	if(bpCliente != null)
    		return getSapType(bpCliente);
    	else
    		return getSapType(bpUtenza);
    }
    
    
	/**
	 * Torna il tipo SAP del bp
	 * @param bp
	 * @return ritorna 1 per Persona, 2 per Organizzazione o 3 per Ente.
	 */
	public String getSapType(String bp){
		String _zptBpTypeGet_iPartner = bp;
		debug("getSapType",bp);
		Holder<String> _zptBpTypeGet_eReturn   = new Holder<String>();
		Holder<String> _zptBpTypeGet_eSapType  = new Holder<String>();
		Holder<ZptTtMess> _zptBpTypeGet_etMess = new Holder<ZptTtMess>();
		portCRM.zptBpTypeGet(_zptBpTypeGet_iPartner, _zptBpTypeGet_eReturn,_zptBpTypeGet_eSapType,_zptBpTypeGet_etMess);
		debug("getSapType._zptBpTypeGet_eReturn=",_zptBpTypeGet_eReturn.value);
		debug("getSapType._zptBpTypeGet_etMess=",_zptBpTypeGet_etMess.value);
		debug("getSapType._zptBpTypeGet_eSapType",_zptBpTypeGet_eSapType.value);
		return _zptBpTypeGet_eSapType.value;
	}
   
	
	public String sendFilesToSap(List<File> files,  String cc,String isRevoke){
		ZbinariesT etBin = new ZbinariesT();
		Holder<ZptTtMess> etMess =  new Holder<ZptTtMess>();
		Holder<String> eReturn = new Holder<String>();
		for( File file: files){
			ZbinariesS  elem = new ZbinariesS();
			elem.setIArchiveid(Constants.ARCHIVE_ID);
			elem.setIArobj(Constants.ARCHIVE_OBJ);
			elem.setIDoctype(file.getNome().substring(file.getNome().lastIndexOf(".")+1, file.getNome().length()).toUpperCase());
			elem.setFileName(file.getNome());
			elem.setBinaryFlg(isRevoke);
			elem.setMimetype(file.getMime());
			elem.setZfile(file.getBinario());
			etBin.getItem().add(elem);
		}
		debug("sendFilesToSap", cc, isRevoke, etBin.getItem().get(0).getFileName(), etBin.getItem().get(0).getMimetype());
		portCRM.zptUploadDocs(etBin, this.bpCliente, cc, isRevoke, eReturn, etMess);
		
		return eReturn.value;
	}
	
	public WSReturn<String> checkMandatoActive(String iCc,String isMndActive, String isSrZs09 ,String isSrZs10 ){
		Holder<String> eReturn = new Holder<String>();
		Holder<ZptTtMess> etMess =  new Holder<ZptTtMess>();
		portCRM.zptCheckMandAndRevocation(iCc, this.bpCliente, isMndActive, isSrZs09, isSrZs10, eReturn, etMess);
	 return new WSReturn<String>(eReturn.value, "checkMandatoActive()", eReturn.value, new ArrayList<String>());
	}
	

	
	
	
}
