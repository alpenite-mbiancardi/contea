package com.alpenite.tea.letture.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rappresenta una lettura di acqua, gas o teleriscaldamento
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Lettura {

    public Lettura(String contatore, String registro, String dataLettura, String valore, String tipologia) {
        this.contatore = contatore;
        this.registro = registro;
        try {
			this.dataLettura = df.parse(dataLettura);
		} catch (ParseException e) {
			this.dataLettura = new Date();
		}
        this.valore = valore;
        this.tipologia = tipologia;
    }

    private String contatore;
    private String registro;
    private Date dataLettura;
    private String valore;
    private String tipologia;
    DateFormat df = new SimpleDateFormat("y-m-d");

    public String getContatore() {
        return contatore;
    }

    public void setContatore(String contatore) {
        this.contatore = contatore;
    }

    public Date getDataLettura() {
        return dataLettura;
    }

    public void setDataLettura(String dataLettura) {
    	try {
			this.dataLettura = df.parse(dataLettura);
		} catch (ParseException e) {
			this.dataLettura = new Date();
		}
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

	public void setDataLettura(Date dataLettura) {
		this.dataLettura = dataLettura;
	}

//    public String getDataLetturaFormattata() {
//        return StringFormat.getStringInstance(StringFormat.LONG).format(getDataLettura());
//    }
    
}
