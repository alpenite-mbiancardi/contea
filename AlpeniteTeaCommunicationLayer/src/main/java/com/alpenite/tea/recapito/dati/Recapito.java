package com.alpenite.tea.recapito.dati;

/**
 * Rappresenta un recapito
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Recapito {

//    public Recapito(String paese, String provincia, String comune, String presso, String cap, String via, String numeroCivico) {
//        this.paese = paese;
//        this.provincia = provincia;
//        this.comune = comune;
//        this.presso = presso;
//        this.cap = cap;
//        this.via = via;
//        this.numeroCivico = numeroCivico;
//    }
//    
    
    public Recapito(String paese, String provincia, String comune, String presso, String cap, String via, String numeroCivico,String extnumeroCivico ) {
        this.paese = paese;
        this.provincia = provincia;
        this.comune = comune;
        this.presso = presso;
        this.cap = cap;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.extnumeroCivico=extnumeroCivico;
    }
    
    public Recapito(String paese, String provincia, String comune, String presso, String cap, String via, String numeroCivico, String extnumeroCivico,String podCode) {
        this(paese,provincia,comune,presso,cap,via,numeroCivico, extnumeroCivico);
        this.podCode = podCode;
    }
    
    private String paese;
    private String provincia;
    private String comune;
    private String presso;
    private String cap;
    private String via;
    private String numeroCivico;
    private String podCode;
    private String extnumeroCivico;
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getFrazione() {
        return presso;
    }

    public void setFrazione(String presso) {
        this.presso = presso;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

	public String getPodCode() {
		return podCode;
	}

	public void setPodCode(String podCode) {
		this.podCode = podCode;
	}

	public String getPresso() {
		return presso;
	}

	public void setPresso(String presso) {
		this.presso = presso;
	}


	public String getExtnumeroCivico() {
		return extnumeroCivico;
	}


	public void setExtnumeroCivico(String extnumeroCivico) {
		this.extnumeroCivico = extnumeroCivico;
	}
    
}
