package com.alpenite.tea.elencoDocumenti.dati;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;


/**
 * Rappresenta un generico documento
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Documento implements Comparable<Documento>{

    public Documento(String tipoDocumento, String numeroFattura, String dataEmissione, String dataScadenza, String importo, String importoAperto, String fileId, String fileObj) {
    	this.tipoDocumento = tipoDocumento;
        this.numeroFattura = removePadding(numeroFattura);
        try {
			this.dataEmissione = df.parse(dataEmissione);
			this.dataScadenza = df.parse(dataScadenza);
		} catch (ParseException e) {
			System.out.println("Errore nel parser della data");
			this.dataEmissione = new Date();
			this.dataScadenza = new Date();
		}
        this.importo = formatValue(importo);
        this.importoAperto = formatValue(importoAperto);
        this.fileId = fileId;
        this.fileObj = fileObj;
    }

    private String tipoDocumento;
    private String numeroFattura;
    private Date dataEmissione;
    private Date dataScadenza;
    private String importo;
    private String importoAperto;
    private String fileId;
    private String fileObj;
    DateFormat df = new SimpleDateFormat("y-M-d");

//    public String getDataEmissioneFormattata() {
//        return DateFormat.getDateInstance(DateFormat.LONG).format(getDataEmissione());
//    }

    public Date getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(String dataEmissione) {
    	try{
    		this.dataEmissione = df.parse(dataEmissione);
    	}catch (ParseException e) {
    		this.dataEmissione = new Date();
    	}
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
    	try{
    		DateFormat df = new SimpleDateFormat("y-M-d");
    		this.dataScadenza = df.parse(dataScadenza);
    	}catch (ParseException e) {
    		this.dataScadenza = new Date();
    	}
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileObj() {
        return fileObj;
    }

    public void setFileObj(String fileObj) {
        this.fileObj = fileObj;
    }

    public String getImporto() {
        return importo;
    }

    public void setImporto(String importo) {
        this.importo = formatValue(importo);
    }

    public String getImportoAperto() {
        return importoAperto;
    }

    public void setImportoAperto(String importoAperto) {
        this.importoAperto = formatValue(importoAperto);
    }

    public String getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = removePadding(numeroFattura);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	private String removePadding(String value){
		if(value == null)
			return value;
		int index = 0;
		for(int i = 0; i< value.length(); i++){
			if(value.charAt(i)!='0'){
				index = i;
				break;
			}
		}
		return value.substring(index);
	}
	
	private String formatValue(String value){
		if(value == null)
			return value;
		
		if(value.length() == 0)
			return value;
		
		DecimalFormat currencyFormatter = (DecimalFormat) NumberFormat.getInstance(new Locale("it", "IT"));
		currencyFormatter.setMinimumFractionDigits(2);
		String val = value;
		boolean negative = false;
		if(value.trim().charAt(value.trim().length()-1) == '-'){
			val = value.trim().substring(0, value.trim().length()-1);
			negative = true;
		}
		
		String result = "";
		if(negative)
			result = "- " + currencyFormatter.format(Double.parseDouble(val));
		else
			result = "" + currencyFormatter.format(Double.parseDouble(val));
		
		return result;
	}
	
	public int compareTo(Documento doc){
		return this.dataEmissione.compareTo(doc.dataEmissione);
	}
	
}
