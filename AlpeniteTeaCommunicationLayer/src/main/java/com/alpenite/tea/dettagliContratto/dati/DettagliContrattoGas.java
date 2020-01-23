package com.alpenite.tea.dettagliContratto.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Rappresenta i dati presenti nel contratto del gas
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DettagliContrattoGas {

    public DettagliContrattoGas(String dataStipula, String dataCessazione, String tipologiaUso, String opzioneTariffaria, String qualificaTitolare, String sconto, String iva, String accise) {
    	 try {
 			this.dataStipula = df.parse(dataStipula);
 			if(dataCessazione != null)
				this.dataCessazione = df.parse(dataCessazione);
			else
				this.dataCessazione = null;
 		} catch (ParseException e) {
 			e.printStackTrace();
 		}
        this.tipologiaUso = tipologiaUso;
        this.opzioneTariffaria = opzioneTariffaria;
        this.qualificaTitolare = qualificaTitolare;
        this.sconto = sconto;
        this.iva = iva;
        this.accise = accise;
    }
    
    public Date dataStipula;
    public Date dataCessazione;
    public String tipologiaUso;
    public String opzioneTariffaria;
    public String qualificaTitolare;
    public String sconto;
    public String iva;
    public String accise;
    DateFormat df = new SimpleDateFormat("y-m-d");

    public String getAccise() {
        return accise;
    }

    public void setAccise(String accise) {
        this.accise = accise;
    }

    public Date getDataCessazione() {
        return dataCessazione;
    }

    public void setDataCessazione(String dataCessazione) {
        try{
        	this.dataCessazione = df.parse(dataCessazione);
    	}catch (ParseException e) {
    		this.dataCessazione = new Date();
    	}
    }

    public Date getDataStipula() {
        return dataStipula;
    }

    public void setDataStipula(String dataStipula) {
    	try{
    		this.dataStipula = df.parse(dataStipula);
    	}catch (ParseException e) {
    		this.dataStipula = new Date();
    	}  
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getOpzioneTariffaria() {
        return opzioneTariffaria;
    }

    public void setOpzioneTariffaria(String opzioneTariffaria) {
        this.opzioneTariffaria = opzioneTariffaria;
    }

    public String getQualificaTitolare() {
        return qualificaTitolare;
    }

    public void setQualificaTitolare(String qualificaTitolare) {
        this.qualificaTitolare = qualificaTitolare;
    }

    public String getSconto() {
        return sconto;
    }

    public void setSconto(String sconto) {
        this.sconto = sconto;
    }

    public String getTipologiaUso() {
        return tipologiaUso;
    }

    public void setTipologiaUso(String tipologiaUso) {
        this.tipologiaUso = tipologiaUso;
    }

//    public String getDataCessazioneFormattata() {
//        return StringFormat.getStringInstance(StringFormat.LONG).format(getDataCessazione());
//    }
}
