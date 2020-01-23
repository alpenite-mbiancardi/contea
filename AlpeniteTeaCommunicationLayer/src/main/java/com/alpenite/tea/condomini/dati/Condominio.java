package com.alpenite.tea.condomini.dati;

/**
 * Rappresenta un condominio
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Condominio{
	
	public Condominio(String codiceBP, String ragioneSociale1, String ragioneSociale2, String paese, String provincia, String comune, String frazione, String cap, String via, String numeroCivico, String estensioneNumeroCivico) {
		this.codiceBP = codiceBP;
		this.ragioneSociale1 = ragioneSociale1;
		this.ragioneSociale2 = ragioneSociale2;
		this.paese = paese;
		this.provincia = provincia;
		this.comune = comune;
		this.frazione = frazione;
		this.cap = cap;
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.estensioneNumeroCivico = estensioneNumeroCivico;
	}
	
	public Condominio(String codiceBP, String ragioneSociale1, String ragioneSociale2, String paese, String provincia, String comune, String frazione, String cap, String via, String numeroCivico, String estensioneNumeroCivico, String contoContrattuale) {
		this(codiceBP, ragioneSociale1, ragioneSociale2, paese, provincia, comune, frazione, cap, via, numeroCivico, estensioneNumeroCivico);
		this.contoContrattuale = contoContrattuale;
	}
	
	private String	codiceBP;
	private String	ragioneSociale1;
	private String	ragioneSociale2;
	private String	paese;
	private String	provincia;
	private String	comune;
	private String	frazione;
	private String	cap;
	private String	via;
	private String	numeroCivico;
	private String	estensioneNumeroCivico;
	public String	contoContrattuale;
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getComune() {
		return comune;
	}
	
	public void setComune(String comune) {
		this.comune = comune;
	}
	
	public String getFrazione() {
		return frazione;
	}
	
	public void setFrazione(String frazione) {
		this.frazione = frazione;
	}
	
	public String getNumeroCivico() {
		return numeroCivico;
	}
	
	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	
	public String getEstensioneNumeroCivico() {
		return estensioneNumeroCivico;
	}
	
	public void setEstensioneNumeroCivico(String estensioneNumeroCivico) {
		this.estensioneNumeroCivico = estensioneNumeroCivico;
	}
	
	public String getPaese() {
		return paese;
	}
	
	public void setPaese(String paese) {
		this.paese = paese;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getRagioneSociale1() {
		return ragioneSociale1;
	}
	
	public void setRagioneSociale1(String ragioneSociale1) {
		this.ragioneSociale1 = ragioneSociale1;
	}
	
	public String getRagioneSociale2() {
		return ragioneSociale2;
	}
	
	public void setRagioneSociale2(String ragioneSociale2) {
		this.ragioneSociale2 = ragioneSociale2;
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	@Override
	public String toString() {
		/*
		 * StringBuilder sb = new StringBuilder();
		 * //sb.append("-----------\n");
		 * sb.append(" ------ ");
		 * sb.append("CodiceBP: ").append(getCodiceBP()).append("$");
		 * sb.append("Ragione sociale: ").append(getRagioneSociale1()).append(" "
		 * ).append(getRagioneSociale2()).append("$");
		 * sb.append("Via: ").append(getVia()).append("$");
		 * sb.append("Numero civico: ").append(getNumeroCivico()).append("$");
		 * sb.append("CAP: ").append(getCap()).append("$");
		 * sb.append("Paese: ").append(getPaese()).append("$");
		 * sb.append("Frazione: ").append(getFrazione()).append("$");
		 * sb.append("Comune: ").append(getComune()).append("$");
		 * sb.append("Provincia: ").append(getProvincia()).append("$");
		 * sb.append(" ------ ");
		 * return sb.toString().replace('\n', '$');
		 */
		String str = "-----$";
		str += "Conto Contrattuale: " + getContoContrattuale() + "$";
		str += "Ragione Sociale: " + getRagioneSociale1() + " " + getRagioneSociale2() + "$";
		str += "Via: " + getVia() + "$";
		str += "Num. civico: " + getNumeroCivico() + "$";
		str += "CAP: " + getCap() + "$";
		str += "Paese: " + getPaese() + "$";
		str += "Comune: " + getComune() + "$";
		str += "Provincia: " + getProvincia() + "$";
		str += "-----$$";
		return str;
		
	}
	
	public String getContoContrattuale() {
		return contoContrattuale;
	}
	
	public void setContoContrattuale(String contoContrattuale) {
		this.contoContrattuale = contoContrattuale;
	}
	
	public String getCodiceBP() {
		return codiceBP;
	}
	
	public void setCodiceBP(String codiceBP) {
		this.codiceBP = codiceBP;
	}
	
}
