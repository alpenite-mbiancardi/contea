package com.alpenite.tea.svuotamenti.dati;

/**
 * Rappresenta un bidone
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Bidone {

    public Bidone(String numeroDiSerie, String descrizione) {
        this.numeroDiSerie = numeroDiSerie;
        this.descrizione = descrizione;
    }
    
    private String numeroDiSerie;
    private String descrizione;
    /*
     * inserimiento delle informazioni per lo svuotamento di bidoni
     * 
     l*/
    private String date;
    private String hours;
    private String volume;
    
    

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNumeroDiSerie() {
        return numeroDiSerie;
    }

    public void setNumeroDiSerie(String numeroDiSerie) {
        this.numeroDiSerie = numeroDiSerie;
    }
    
}
