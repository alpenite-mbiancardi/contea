package com.alpenite.tea.communicationLayer.data;

/**
 * Rappresenta un comune
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Comune {

    public Comune(String nome, String codiceCatastale, String cap) {
        this.nome = nome;
        this.codiceCatastale = codiceCatastale;
        this.cap = cap;
    }

    private String nome;
    private String codiceCatastale;
    private String cap;

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCodiceCatastale() {
        return codiceCatastale;
    }

    public void setCodiceCatastale(String codiceCatastale) {
        this.codiceCatastale = codiceCatastale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
