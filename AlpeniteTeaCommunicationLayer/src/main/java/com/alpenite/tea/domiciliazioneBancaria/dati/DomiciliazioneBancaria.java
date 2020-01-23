package com.alpenite.tea.domiciliazioneBancaria.dati;

/**
 * Rappresenta la domiciliazione bancaria
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DomiciliazioneBancaria {

    public DomiciliazioneBancaria(String nomeBanca, String iban, String mandato,String lastDocumenType,String lastDocumentId) {
        this.nomeBanca = nomeBanca;
        this.iban = iban;
        this.mandato = mandato;
        this.lastDocumenType = lastDocumenType;
        this.lastDocumentId = lastDocumentId;
        
    }
    
    private String nomeBanca;
    private String iban;
    private String mandato;
    private String lastDocumenType;
    private String lastDocumentId;

    
    public String getMandato() {
		return mandato;
	}

	public void setMandato(String mandato) {
		this.mandato = mandato;
	}

	public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getNomeBanca() {
        return nomeBanca;
    }

    public void setNomeBanca(String nomeBanca) {
        this.nomeBanca = nomeBanca;
    }

	public String getLastDocumenType() {
		return lastDocumenType;
	}

	public void setLastDocumenType(String lastDocumenType) {
		this.lastDocumenType = lastDocumenType;
	}

	public String getLastDocumentId() {
		return lastDocumentId;
	}

	public void setLastDocumentId(String lastDocumentId) {
		this.lastDocumentId = lastDocumentId;
	}

    
    
    
    
}
