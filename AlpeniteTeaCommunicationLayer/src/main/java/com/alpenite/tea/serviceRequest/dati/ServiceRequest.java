package com.alpenite.tea.serviceRequest.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rappresenta un service request
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class ServiceRequest {

    public ServiceRequest(String numero, String stato, String tipologia, String dataApertura, String dataChiusura, String domanda, String risposta) {
        this.numero = numero;
        this.stato = stato;
        this.tipologia = tipologia;
        try {
			this.dataApertura = df.parse(dataApertura);
			this.dataChiusura = df.parse(dataChiusura);
		} catch (ParseException e) {
			this.dataApertura = new Date();
			this.dataChiusura = new Date();
		}
        this.domanda = domanda.replace("*", "<br />");
        this.risposta = risposta.replace("*", "<br />");
    }
    
    private String numero;
    private String stato;
    private String tipologia;
    private Date dataApertura;
    private Date dataChiusura;
    private String domanda;
    private String risposta;
    DateFormat df = new SimpleDateFormat("y-m-d");

    public Date getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(String dataApertura) {
    	try {
			this.dataApertura = df.parse(dataApertura);
		} catch (ParseException e) {
			this.dataApertura = new Date();
		}
    }

    public Date getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(String dataChiusura) {
    	try {
			this.dataChiusura = df.parse(dataChiusura);
		} catch (ParseException e) {
			this.dataChiusura = new Date();
		}
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
    
}
