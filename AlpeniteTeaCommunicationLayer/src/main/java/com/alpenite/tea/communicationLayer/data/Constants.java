package com.alpenite.tea.communicationLayer.data;

import java.util.*;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Classe di utilita'che contiene le costanti utilizzate nel client.
 * 
 * La struttura dei nomi delle costanti e'la seguente: dato il nome del web
 * service scritto utilizzando lo standard CamelCase (es. zptServiceRequestCreate),
 * dividere le parole che compongono il nome e separale con il carattere underscore
 * (_), aggiungere il nome del campo del quale vogliamo descrivere un possibile valore
 * (es. iTypSr) e infine aggiungere un identificativo del valore (es. aggiuntaCC).
 * 
 * Nel caso in esempio il nome risultante sarà:
 * 
 * ZPT_SERVICE_REQUEST_CREATE_ITYPSR_AGGIUNTA_CC
 * -------------------------- ------ -----------
 *    nome del web service    campo   id valore
 *  
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Constants {
    
    /*
     * Costanti contenenti i codici utilizzati per definire i tipi di richiesta
     * nel web service zptServiceRequestCreate
     */
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_AGGIUNTA_CC = "ZS07";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_RECAPITO = "ZS06";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_INDIRIZZO_FORNITURA = "ZS03";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_DATI_CATASTALI = "ZS02";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_MODIFICA_DATI_ANAGRAFICI = "ZS01";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_AGGIUNGI_RIMUOVI_CONDOMINI = "ZS08";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_RICHIESTA_CLIENTE = "ZS05";
    public static final String ZPT_SERVICE_REQUEST_CREATE_ITYPSR_RICHIESTA_PROSPECT = "ZS04";
    
    /**
     * Costanti contenenti i codici utilizzati per definire i tipi di contratti
     * permessi nel web service zptContractList
     */
    public static final String TIPO_CONTRATTO_TEA = "tea";
    public static final String TIPO_CONTRATTO_ACQUA = "acqua";
    //public static final String TIPO_CONTRATTO_AMBIENTE = "ambiente";
    public static final String TIPO_CONTRATTO_AMBIENTE = "rifiuti"; // modifica effettuata a seguito di cambiamento del definition.cnd da parte di Tea
    public static final String TIPO_CONTRATTO_GAS = "gas";
    
    public static final String ZPT_CONTRACT_LIST_ITCOMPANY_TEA = "Z022";
    public static final String ZPT_CONTRACT_LIST_ITCOMPANY_TEA_ENERGIA = "YL22";
    // modifica adattamento aqa 25ott2016 Marzio Biancardi
    //public static final String ZPT_CONTRACT_LIST_ITCOMPANY_ACQUA = "Z025";
    public static final String ZPT_CONTRACT_LIST_ITCOMPANY_AMBIENTE = "Z023";
    public static final List<String> ZPT_CONTRACT_LIST_ITCOMPANY_GAS = new ArrayList<String>();
    
    static {
        ZPT_CONTRACT_LIST_ITCOMPANY_GAS.add("YE22");
        ZPT_CONTRACT_LIST_ITCOMPANY_GAS.add("YT22");
        ZPT_CONTRACT_LIST_ITCOMPANY_GAS.add("Z022");
        //modifica nuovi servizi energetici
        ZPT_CONTRACT_LIST_ITCOMPANY_GAS.add(ZPT_CONTRACT_LIST_ITCOMPANY_TEA_ENERGIA);
    }
    
    public static final List<String> ZPT_CONTRACT_LIST_ITCOMPANY_ACQUA = new ArrayList<String>();
    static {
    	ZPT_CONTRACT_LIST_ITCOMPANY_ACQUA.add("Z025");
    	ZPT_CONTRACT_LIST_ITCOMPANY_ACQUA.add("Z031");
        
    }
    
    /** la mappa contenente le chiavi tea, acqua, ambiente e gas con i relativi codici */
    public static final Map<String, List<String>> ZPT_CONTRACT_LIST_ITCOMPANY_MAP = new HashMap<String, List<String>>();
    static {
        ZPT_CONTRACT_LIST_ITCOMPANY_MAP.put(
                TIPO_CONTRATTO_TEA, Arrays.asList(ZPT_CONTRACT_LIST_ITCOMPANY_TEA));
        // modifica contratto energia      
        ZPT_CONTRACT_LIST_ITCOMPANY_MAP.put(
                TIPO_CONTRATTO_TEA, Arrays.asList(ZPT_CONTRACT_LIST_ITCOMPANY_TEA_ENERGIA));
        ZPT_CONTRACT_LIST_ITCOMPANY_MAP.put(
                TIPO_CONTRATTO_ACQUA, ZPT_CONTRACT_LIST_ITCOMPANY_ACQUA);
        ZPT_CONTRACT_LIST_ITCOMPANY_MAP.put(
                TIPO_CONTRATTO_AMBIENTE, Arrays.asList(ZPT_CONTRACT_LIST_ITCOMPANY_AMBIENTE));
        ZPT_CONTRACT_LIST_ITCOMPANY_MAP.put(
                TIPO_CONTRATTO_GAS, ZPT_CONTRACT_LIST_ITCOMPANY_GAS);
        
    }
    /**
     * Costanti relative al flag cartaceo
     */
    public static final List<String> ZPT_FLAG_CARTACEO_DETBOL_CONTI = new ArrayList<String>();
    static {
    	ZPT_FLAG_CARTACEO_DETBOL_CONTI.add("02");//"Tipo conto = conto TeaEnergia
    	ZPT_FLAG_CARTACEO_DETBOL_CONTI.add("06");//"Tipo conto = conto contrattuale
    }
    /**
     * Costanti relative ai tipi di documento
     */
    public static final String ZPT_CC_LIST_DETT_PARTITE_APERTE = "partiteAperte";
    public static final String ZPT_CC_LIST_DETT_STORICO_FATTURE = "fatture";
    public static final String ZPT_CC_LIST_DETT_ESTRATTO_CONTO = "estrattoConto";
    
    /**
     * Costanti relative ai tipi di utente
     */
    public final static String TIPO_PRIVATO = "privato";
    public final static String TIPO_AZIENDA = "azienda";
    public final static String TIPO_PRIV_CLI = "privato_cliente";
    public final static String TIPO_AZ_CLI = "azienda_cliente";
    public final static String TIPO_AMM_COND = "amm_condominio";
    
    /**
     * Costanti degli stati ritornati da SAP
     */
    public final static String SAP_SUCCESS = "00";
    public final static String SAP_ERR_02 = "02";
    
    /**
     * Costanti errori portale
     */
    public final static String SUCCESS = "P0";
    public final static String SUCCESS_REGISTRATION = "P1";
    public final static String SUCCESS_VALIDATION = "P2";
	public final static String ERRORE_CAMPI_OBBLIGATORI = "P3";
	public final static String ERRORE_MAIL = "P4";
	public final static String ERRORE_USERNAME = "P5";
	public final static String ERRORE_PASSWORD = "P6";
	public final static String ERRORE_CONSENSO = "P7";
	public final static String ERRORE_DATA_PRECEDENTE_1110 = "P8";
	public final static String ERRORE_PIVA = "P9";
	public final static String ERRORE_GENERICO = "-1";
	public final static String ERRORE_CAMPI_NON_CORRETTI = "P10";
	public final static String ERRORE_PARAMETRI_NON_CORRETTI = "P11";
	public final static String SUCCESS_MODIFICATION_MAIL = "P12";
	public final static String SUCCESS_MODIFICATION_PASSWORD = "P13";
	public final static String ERROR_AUTH_LOCKED_USER = "P14";
	public final static String ERROR_AUTH_GENERAL = "P15";
	public final static String ERROR_USERNAME_UPPERCASE = "P16";
	public final static String ERROR_LIST_FILE_NULL = "P17";
	public final static String ERROR_EXENTION_TYPE = "P18";
	public final static String ERROR_MAX_DIM="P19";
	public final static String SUCCESS_UPLOAD_FILE="P20";
	public final static String ERROR_GENERIC = "P21";
	
	/**
	 * Dimensioni dei vari parametri
	 */
	public final static int DIM_CC = 12;
	public final static int DIM_C = 10;
	public final static int STRING_START = 0;
	public final static int DIM_USERNAME = 20;
	public final static int DIM_CF = 16;
	public final static int DIM_PI = 20;
	public final static int DIM_MAIL = 241;
	
	/**
	 * Elenco tipi utente SAP 
	 */
	public final static String PERSONA = "P";
	public final static String AZIENDA = "A";
	public final static String AMM_COND = "AM";
	
	/**
	 * BP type
	 */
	public final static String BP_PERSONA = "1";
	public final static String BP_ORG = "2";
	public final static String BP_GRP = "3";
	
	/**
	 * Document information for upload
	 */
	public final static String DOCTYPE = "PDF";
	public final static String ARCHIVE_OBJ = "ZSEPA_DOCS"; 
	public final static String ARCHIVE_ID = "Z1";
	
	/**
	 * List of permitted extension
	 */
	public final static List<String> EXTENSIONS = Arrays.asList("PDF","DOC","JPG","JPEG","TIFF","TIF","BMP");
	
	/**
	 * Codice SAP per indicare il tipo utente
	 * @param type tipo utente
	 * @return tipo utente SAP
	 */
	public final static String getJahiaType(String type){
		return getJahiaType(type, false, false);
	}
	
	/**
	 * Codice SAP per indicare il tipo utente
	 * @param type tipo utente
	 * @return tipo utente SAP
	 */
	public final static String getJahiaType(String type, boolean datiAnagrafici, boolean datiAnagraficiAzienda){
		if(type.equals(TIPO_PRIVATO) || type.equals(TIPO_PRIV_CLI))
			return PERSONA;
		if(type.equals(TIPO_AZIENDA) || type.equals(TIPO_AZ_CLI)){
			if(datiAnagrafici)
				return PERSONA;
			if(datiAnagraficiAzienda)
				return AZIENDA;
			return AZIENDA;
		}
		if(type.equals(TIPO_AMM_COND)){
			if(datiAnagrafici)
				return AZIENDA;
			if(datiAnagraficiAzienda)
				return AZIENDA;
			return AMM_COND;
		}
		return null;
	}
	
}
