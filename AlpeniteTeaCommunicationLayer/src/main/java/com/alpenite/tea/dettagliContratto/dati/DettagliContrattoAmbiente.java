package com.alpenite.tea.dettagliContratto.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Rappresenta i dati presenti nel contratto delle immondizie
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DettagliContrattoAmbiente {

    public DettagliContrattoAmbiente(String dataStipula, String dataCessazione, String tipologiaUso, String opzioneTariffaria, String qualificaTitolare, String superficie, String numeroPersone, String attivita, List<String> riduzioni) {
        if (riduzioni == null)
            riduzioni = new ArrayList<String>();
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
        this.superficie = stringToInt(superficie);
        this.numeroPersone = stringToIntPersone(numeroPersone);
        this.attivita = attivita;
        this.riduzioni = riduzioni;
    }
    
    private int stringToIntPersone(String value){
    	if(value == null || value.equals(""))
    		return -1;
    	return (int) Double.parseDouble(value);
    }
    
    private int stringToInt(String value){
    	if(value == null || value.equals(""))
    		return -1;
    	value = value.replaceAll("\\.", "");
    	value = value.replaceAll(",", ".");
    	return (int) Double.parseDouble(value);
    }
    
    public Date dataStipula;
    public Date dataCessazione;
    public String tipologiaUso;
    public String opzioneTariffaria;
    public String qualificaTitolare;
    public int superficie;
    public int numeroPersone;
    public String attivita;
    public List<String> riduzioni;
    DateFormat df = new SimpleDateFormat("y-m-d");

    public String getAttivita() {
        return attivita;
    }

    public void setAttivita(String attivita) {
        this.attivita = attivita;
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

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(String numeroPersone) {
        this.numeroPersone = stringToInt(numeroPersone);
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
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

    public List<String> getRiduzioni() {
        return riduzioni;
    }

    public void setRiduzioni(List<String> riduzioni) {
        this.riduzioni = riduzioni;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }
    
    public void setSuperficie(String superficie) {
        this.superficie = stringToInt(superficie);
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
