package com.alpenite.tea.elencoContratti.dati;

/**
 * Rappresenta un contratto
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Contratto {

    public Contratto(String contoContrattuale, String contratto, String indirizzo, String servizio, String stato, String tipoServizio ,String codiceCompagnia) {
        this.contoContrattuale = contoContrattuale;
        this.contratto = contratto;
        this.indirizzo = indirizzo;
        this.servizio = servizio;
        this.stato = stato;
        this.tipoServizio = tipoServizio;
        this.codiceCompagnia = codiceCompagnia;
    }

    private String contoContrattuale;
    private String contratto;
    private String indirizzo;
    private String servizio;
    private String stato;
    private String tipoServizio;
    //modifica AQA
    private String codiceCompagnia;

    public String getContoContrattuale() {
        return contoContrattuale;
    }

    public void setContoContrattuale(String contoContrattuale) {
        this.contoContrattuale = contoContrattuale;
    }

    public String getContratto() {
        return contratto;
    }

    public void setContratto(String contratto) {
        this.contratto = contratto;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getServizio() {
        return servizio;
    }

    public void setServizio(String servizio) {
        this.servizio = servizio;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipoServizio() {
        return tipoServizio;
    }

    public void setTipoServizio(String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }
    
    
    public String getCodiceCompagnia() {
        return codiceCompagnia;
    }

    public void setCodiceCompagnia(String codiceCompagnia) {
        this.codiceCompagnia = codiceCompagnia;
    }
	
}
