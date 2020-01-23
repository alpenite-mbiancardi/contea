package com.alpenite.tea.dettagliContratto.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rappresenta i dati presenti nel contratto dell'acqua
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DettagliContrattoAcqua {

    public DettagliContrattoAcqua(String dataStipula, String dataCessazione, String tipologiaUso, String categoriaUso, String opzioneTariffaria, String qualificaTitolare, String unitaAbitative, String iva) {
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
        this.categoriaUso = categoriaUso;
        this.opzioneTariffaria = opzioneTariffaria;
        this.qualificaTitolare = qualificaTitolare;
        this.unitaAbitative = stringToInt(unitaAbitative);
        this.iva = iva;
    }

    private int stringToInt(String value){
    	if(value == null || value.equals(""))
    		return -1;
    	value = value.replaceAll(",", "#");
    	value = value.replaceAll("/.", ",");
    	value = value.replaceAll("#", ".");
    	return (int) Double.parseDouble(value);
    }
    
    public Date dataStipula;
    public Date dataCessazione;
    public String tipologiaUso;
    public String categoriaUso;
    public String opzioneTariffaria;
    public String qualificaTitolare;
    public int unitaAbitative;
    public String iva;
    DateFormat df = new SimpleDateFormat("y-m-d");

    public String getCategoriaUso() {
        return categoriaUso;
    }

    public void setCategoriaUso(String categoriaUso) {
        this.categoriaUso = categoriaUso;
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

    public String getTipologiaUso() {
        return tipologiaUso;
    }

    public void setTipologiaUso(String tipologiaUso) {
        this.tipologiaUso = tipologiaUso;
    }

    public int getUnitaAbitative() {
        return unitaAbitative;
    }

    public void setUnitaAbitative(String unitaAbitative) {
        this.unitaAbitative = stringToInt(unitaAbitative);
    }
    
    public void setUnitaAbitative(int unitaAbitative) {
        this.unitaAbitative = unitaAbitative;
    }

//    public String getDataCessazioneFormattata() {
//        return StringFormat.getStringInstance(StringFormat.LONG).format(getDataCessazione());
//    }
}
