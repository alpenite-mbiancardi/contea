package com.alpenite.tea.dettagliContratto.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rappresenta i dati presenti nel contratto del teleriscaldamento
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DettagliContrattoTeleriscaldamento {

    public DettagliContrattoTeleriscaldamento(String dataStipula, String dataCessazione, String tipologiaUso, String categoriaUso, String opzioneTariffaria, String qualificaTitolare, String volumetria, String coefficienteUso, String portataContrattuale, String iva, String riscaldamento, String raffrescamento, String acquaSanitaria) {
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
        this.volumetria = stringToInt(volumetria);
        this.coefficienteUso = coefficienteUso;
        this.portataContrattuale = portataContrattuale;
        this.iva = iva;
        this.riscaldamento = riscaldamento;
        this.raffrescamento = raffrescamento;
        this.acquaSanitaria = acquaSanitaria;
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
    public String categoriaUso;
    public String opzioneTariffaria;
    public String qualificaTitolare;
    public int volumetria;
    public String coefficienteUso;
    public String portataContrattuale;
    public String iva;
    public String riscaldamento;
    public String raffrescamento;
    public String acquaSanitaria;
    DateFormat df = new SimpleDateFormat("y-m-d");

    public String getAcquaSanitaria() {
        return acquaSanitaria;
    }

    public void setAcquaSanitaria(String acquaSanitaria) {
        this.acquaSanitaria = acquaSanitaria;
    }

    public String getCategoriaUso() {
        return categoriaUso;
    }

    public void setCategoriaUso(String categoriaUso) {
        this.categoriaUso = categoriaUso;
    }

    public String getCoefficienteUso() {
        return coefficienteUso;
    }

    public void setCoefficienteUso(String coefficienteUso) {
        this.coefficienteUso = coefficienteUso;
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

    public String getPortataContrattuale() {
        return portataContrattuale;
    }

    public void setPortataContrattuale(String portataContrattuale) {
        this.portataContrattuale = portataContrattuale;
    }

    public String getQualificaTitolare() {
        return qualificaTitolare;
    }

    public void setQualificaTitolare(String qualificaTitolare) {
        this.qualificaTitolare = qualificaTitolare;
    }

    public String getRaffrescamento() {
        return raffrescamento;
    }

    public void setRaffrescamento(String raffrescamento) {
        this.raffrescamento = raffrescamento;
    }

    public String getRiscaldamento() {
        return riscaldamento;
    }

    public void setRiscaldamento(String riscaldamento) {
        this.riscaldamento = riscaldamento;
    }

    public String getTipologiaUso() {
        return tipologiaUso;
    }

    public void setTipologiaUso(String tipologiaUso) {
        this.tipologiaUso = tipologiaUso;
    }

    public int getVolumetria() {
        return volumetria;
    }

    public void setVolumetria(int volumetria) {
        this.volumetria = volumetria;
    }
    
    public void setVolumetria(String volumetria) {
        this.volumetria = stringToInt(volumetria);;
    }

//    public String getDataCessazioneFormattata() {
//        return StringFormat.getStringInstance(StringFormat.LONG).format(getDataCessazione());
//    }
}
