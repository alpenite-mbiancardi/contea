package com.alpenite.tea.svuotamenti.dati;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rappresenta uno svuotamento
 * @author Luca Leone
 */
public class Sacchetti {

	
	
	
	public Sacchetti() {
		super();
	}

	public Sacchetti(String numeroDiSerie, String descrizione) {
		super();
		this.numeroDiSerie = numeroDiSerie;
		this.descrizione = descrizione;
	}
	
    public Sacchetti(String numeroDiSerie, String data, String ora, String volume) {
        this.numeroDiSerie = numeroDiSerie;
        try {
			this.data = df.parse(data);
		} catch (ParseException e) {
			this.data = new Date();
		}
        this.ora = ora;
        this.volume = volume;
    }
    
    private String numeroDiSerie;
    private Date data;
    private String ora;
    private String volume;
    DateFormat df = new SimpleDateFormat("y-m-d");
    private String descrizione;
    
    
    

	public Date getData() {
        return data;
    }

    public void setData(String data) {
    	try {
			this.data = df.parse(data);
		} catch (ParseException e) {
			this.data = new Date();
		}
    }

    public String getNumeroDiSerie() {
        return numeroDiSerie;
    }

    public void setNumeroDiSerie(String numeroDiSerie) {
        this.numeroDiSerie = numeroDiSerie;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
    

	
    
}
