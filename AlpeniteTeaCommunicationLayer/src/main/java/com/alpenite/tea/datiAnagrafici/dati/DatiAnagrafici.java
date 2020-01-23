/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpenite.tea.datiAnagrafici.dati;

import org.apache.log4j.Logger;

/**
 * Rappresenta dei dati anagrafici
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DatiAnagrafici {

	// constants
	private final int DIM_NOME = 40;
	private final int DIM_COGNOME = 40;
	private final int DIM_RAGSOC = 40;
	private final int DIM_TEL = 30;
	private final int DIM_PIVA = 20;
	private final int DIM_CF = 16;
	private final int DIM_CEL = 30;
	private final int DIM_VIA = 60;
	private final int DIM_COMUNE = 40;
	private final int DIM_CAP = 10;
	private final int DIM_PAESE = 3;
	private final int DIM_PROVINCIA = 3;
	private final int DIM_BP = 10;
	private final int DIM_FRAZIONE = 30;
	private final int DIM_NUMCIV = 5;
	private final int DIM_ATT_MARK = 6;
	private final int DIM_RUOLO = 20;
	private final int DIM_NUMESTCIV = 10;
    
	public DatiAnagrafici(String codiceBP, String tipoBP, String ragioneSociale1, String ragioneSociale2, String ragioneSociale3, String ragioneSociale4, String nome, String cognome, String telefono, String cellulare, String paese, String provincia, String comune, String frazione, String via, String cap,String numeroCivico, String codiceFiscale, String partitaIVA, String privacy, String privacyMail, String privacyEmail, String privacyTel, String ruolo) {
        this.codiceBP = codiceBP;
        this.tipoBP = tipoBP;
        this.ragioneSociale1 = ragioneSociale1;
        this.ragioneSociale2 = ragioneSociale2;
        this.ragioneSociale3 = ragioneSociale3;
        this.ragioneSociale4 = ragioneSociale4;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.cellulare = cellulare;
        this.paese = paese;
        this.provincia = provincia;
        this.comune = comune;
        this.frazione = frazione;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.cap = cap;
        this.codiceFiscale = codiceFiscale;
        this.partitaIVA = partitaIVA;
        this.privacy = privacy;
        this.privacyMail = privacyMail;
        this.privacyEmail = privacyEmail;
        this.privacyTel = privacyTel;
        this.ruolo = ruolo;
    }
    
    public DatiAnagrafici(String codiceBP, String tipoBP, String ragioneSociale1, String ragioneSociale2, String ragioneSociale3, String ragioneSociale4, String nome, String cognome, String telefono, String cellulare, String paese, String provincia, String comune, String frazione, String via, String numeroCivico,String estensioneNumeroCivico, String cap, String codiceFiscale, String partitaIVA, String privacy, String privacyMail, String privacyEmail, String privacyTel, String ruolo, String prefissoTel, String prefissoCel) {
        this.codiceBP = codiceBP;
        this.tipoBP = tipoBP;
        this.ragioneSociale1 = ragioneSociale1;
        this.ragioneSociale2 = ragioneSociale2;
        this.ragioneSociale3 = ragioneSociale3;
        this.ragioneSociale4 = ragioneSociale4;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.cellulare = cellulare;
        this.paese = paese;
        this.provincia = provincia;
        this.comune = comune;
        this.frazione = frazione;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.estenzioneNumeroCivico =estensioneNumeroCivico;
        this.cap = cap;
        this.codiceFiscale = codiceFiscale;
        this.partitaIVA = partitaIVA;
        this.privacy = privacy;
        this.privacyMail = privacyMail;
        this.privacyEmail = privacyEmail;
        this.privacyTel = privacyTel;
        this.ruolo = ruolo;
        this.prefissoCel = prefissoCel;
        this.prefissoTel = prefissoTel;
    }
    
  

	public DatiAnagrafici(){};
    
    private String codiceBP;
    private String tipoBP;
    private String ragioneSociale1;
    private String ragioneSociale2;
    private String ragioneSociale3;
    private String ragioneSociale4;
    private String nome;
    private String cognome;
    private String telefono;
    private String cellulare;
    private String paese;
    private String provincia;
    private String comune;
    private String frazione;
    private String via;
    private String numeroCivico;
    private String cap;
    private String codiceFiscale;
    private String partitaIVA;
    private String privacy;
    private String privacyMail;
    private String privacyEmail;
    private String privacyTel;
    private String ruolo;
    private String prefissoTel;
    private String prefissoCel;
    private String estenzioneNumeroCivico;
    /**
     * Matteo Zanioli - 12/11/2012
     * Verifica la lunghezza della stringa inserita ed eventualmente lancia
     * un'eccezione
     * @param s la stringa da verificare
     * @param length la lunghezza
     * @param msg il messaggio contenuto nell'eccezione
     */
    private void validate(String s, int length, String msg) {
      Logger.getLogger(getClass()).info("valore della stringa inserita"+s);
    	if (s != null && s.length()>length)
            throw new IllegalArgumentException(msg);
    }
    
    public String getCap() {    
        return cap;
    }

    public void setCap(String cap) {
    	validate(cap, DIM_CAP, "Errore lunghezza CAP");
        this.cap = cap;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
    	validate(cellulare, DIM_CEL, "Errore lunghezza cel");
    	this.cellulare = cellulare;    
    }

    public String getCodiceBP() {
        return codiceBP;
    }

    public void setCodiceBP(String codiceBP) {
    	validate(codiceBP, DIM_BP, "Errore lunghezza bp");
        this.codiceBP = codiceBP;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
    	validate(codiceFiscale, DIM_CF, "Errore lunghezza CF");
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
    	validate(cognome, DIM_COGNOME, "Errore lunghezza cognome");
        this.cognome = cognome;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
    	validate(comune, DIM_COMUNE, "Errore lunghezza comune");
        this.comune = comune;
    }

    public String getFrazione() {
        return frazione;
    }

    public void setFrazione(String frazione) {
    	validate(frazione, DIM_FRAZIONE, "Errore lunghezza frazione");
        this.frazione = frazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
    	validate(nome, DIM_NOME, "Errore lunghezza frazione");
        this.nome = nome;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
    	validate(numeroCivico, DIM_NUMCIV, "Errore lunghezza numero civico");
    	
    	this.numeroCivico = numeroCivico;        
    }
    public String getEstenzioneNumeroCivico() {
		return estenzioneNumeroCivico;
	}

	public void setEstenzioneNumeroCivico(String estenzioneNumeroCivico) {
		validate(numeroCivico, DIM_NUMESTCIV, "Errore lunghezza estensione lnumero civico");
		Logger.getLogger(getClass()).info("setEstenzioneNumeroCivico"+estenzioneNumeroCivico);
		this.estenzioneNumeroCivico = estenzioneNumeroCivico;
	}

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
    	validate(paese, DIM_PAESE, "Errore lunghezza paese");
        this.paese = paese;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
    	validate(partitaIVA, DIM_PIVA, "Errore lunghezza PIVA");
        this.partitaIVA = partitaIVA;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
    	validate(privacy, DIM_ATT_MARK, "Errore lunghezza privacy");
        this.privacy = privacy;
    }

    public String getPrivacyEmail() {
        return privacyEmail;
    }

    public void setPrivacyEmail(String privacyEmail) {
    	validate(privacyEmail, DIM_ATT_MARK, "Errore lunghezza privacy email");
        this.privacyEmail = privacyEmail;
    }

    public String getPrivacyMail() {
        return privacyMail;
    }

    public void setPrivacyMail(String privacyMail) {
    	validate(privacyMail, DIM_ATT_MARK, "Errore lunghezza privacy mail");
        this.privacyMail = privacyMail;
    }

    public String getPrivacyTel() {
        return privacyTel;
    }

    public void setPrivacyTel(String privacyTel) {
    	validate(privacyTel, DIM_ATT_MARK, "Errore lunghezzaa privacy tel");
        this.privacyTel = privacyTel;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
    	validate(provincia, DIM_PROVINCIA, "Errore lunghezza provincia");
        this.provincia = provincia;
    }

    public String getRagioneSociale1() {
        return ragioneSociale1;
    }

    public void setRagioneSociale1(String ragioneSociale1) {
    	validate(ragioneSociale1, DIM_RAGSOC, "Errore lunghezza ragione sociale1");
        this.ragioneSociale1 = ragioneSociale1;
    }

    public String getRagioneSociale2() {
        return ragioneSociale2;
    }

    public void setRagioneSociale2(String ragioneSociale2) {
    	validate(ragioneSociale2, DIM_RAGSOC, "Errore lunghezza ragione sociale2");
    	this.ragioneSociale2 = ragioneSociale2;
    }

    public String getRagioneSociale3() {
        return ragioneSociale3;
    }

    public void setRagioneSociale3(String ragioneSociale3) {
    	validate(ragioneSociale3, DIM_RAGSOC, "Errore lunghezza ragione sociale3");
    	this.ragioneSociale3 = ragioneSociale3;
    }

    public String getRagioneSociale4() {
        return ragioneSociale4;
    }

    public void setRagioneSociale4(String ragioneSociale4) {
    	validate(ragioneSociale4, DIM_RAGSOC, "Errore lunghezza ragione sociale4");
    	this.ragioneSociale4 = ragioneSociale4;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
    	validate(ruolo, DIM_RUOLO, "Errore lunghezza ruolo");
        this.ruolo = ruolo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
    	validate(telefono, DIM_TEL, "Errore lunghezza telefono");
    	this.telefono = telefono;
    }

    public String getTipoBP() {
        return tipoBP;
    }

    public void setTipoBP(String tipoBP) {
        this.tipoBP = tipoBP;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
    	validate(via, DIM_VIA, "Errore lunghezza via");
        this.via = via;
    }

    public String toString()
    {
    	String dati = "";		
		dati=dati.concat(this.cap!=null?dati.concat("Cap: "+this.cap+"<br />"):"").
				  concat(this.cellulare!=null?dati.concat("Cellulare: "+this.cellulare+"<br />"):"").
				  concat(this.codiceBP!=null?dati.concat("Codice BP: "+this.codiceBP+"<br />"):"").
				  concat(this.codiceFiscale!=null?dati.concat("Codice Fiscale: "+this.codiceFiscale+"<br />"):"").
				  concat(this.cognome!=null?dati.concat("Cognome: "+this.cognome+"<br />"):"").
				  concat(this.nome!=null?dati.concat("Nome: "+this.nome+"<br />"):"").
				  concat(this.comune!=null?dati.concat("Comune: "+this.comune+"<br />"):"").
				  concat(this.frazione!=null?dati.concat("Frazione: "+this.frazione+"<br />"):"").
				  concat(this.via!=null?dati.concat("Via: "+this.via+"<br />"):"").				
				  concat(this.numeroCivico!=null?dati.concat("NumeroCivico: "+this.numeroCivico+"<br />"):"").
				  concat(this.estenzioneNumeroCivico!=null?dati.concat("EstensioneNumeroCivico: "+this.estenzioneNumeroCivico+"<br />"):"").
				  concat(this.paese!=null?dati.concat("Paese: "+this.paese+"<br />"):"").
				  concat(this.partitaIVA!=null?dati.concat("Partita IVA: "+this.partitaIVA+"<br />"):"").
				  concat(this.prefissoCel!=null?dati.concat("Prefisso Cel: "+this.prefissoCel+"<br />"):"").
				  concat(this.privacy!=null?dati.concat("Privacy: "+this.privacy+"<br />"):"").
				  concat(this.privacyEmail!=null?dati.concat("Privacy Email: "+this.privacyEmail+"<br />"):"").
				  concat(this.privacyMail!=null?dati.concat("Privacy Mail: "+this.privacyMail+"<br />"):"").
				  concat(this.privacyTel!=null?dati.concat("Privacy Tel: "+this.privacyTel+"<br />"):"").
				  concat(this.provincia!=null?dati.concat("Provincia: "+this.provincia+"<br />"):"").
				  concat(this.ragioneSociale1!=null?dati.concat("Ragione Sociale: "+this.ragioneSociale1+"<br />"):"").
				  concat(this.ragioneSociale2!=null?dati.concat("Ragione Sociale: "+this.ragioneSociale2+"<br />"):"").
				  concat(this.ragioneSociale3!=null?dati.concat("Ragione Sociale: "+this.ragioneSociale3+"<br />"):"").
				  concat(this.ragioneSociale4!=null?dati.concat("Ragione Sociale: "+this.ragioneSociale4+"<br />"):"").
				  concat(this.ruolo!=null?dati.concat("Ruolo: "+this.ruolo+"<br />"):"").
				  concat(this.telefono!=null?dati.concat("Telefono: "+this.telefono+"<br />"):"").
				  concat(this.tipoBP!=null?dati.concat("Tipo BP: "+this.tipoBP+"<br />"):"");
		
		return dati;
    }

	public String getPrefissoTel() {
		return prefissoTel;
	}

	public void setPrefissoTel(String prefissoTel) {
		this.prefissoTel = prefissoTel;
	}

	public String getPrefissoCel() {
		return prefissoCel;
	}

	public void setPrefissoCel(String prefissoCel) {
		this.prefissoCel = prefissoCel;
	}

    
}
