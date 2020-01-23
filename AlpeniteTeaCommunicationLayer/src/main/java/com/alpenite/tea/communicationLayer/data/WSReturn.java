package com.alpenite.tea.communicationLayer.data;

import java.util.List;

/**
 * Rappresenta la struttura dati tornata dai metodi del WSClient
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class WSReturn<E> {

    /**
     * Crea e torna la struttura dati tornata dai metodi del WSClient
     *
     * @param codice il codice tornato dal web service
     * @param nomeMetodo il nome del metodo
     * @param ritorno l'oggetto tornato dal metodo
     * @param messaggi i messaggi tornati dal web service
     */
    public WSReturn(String codice, String nomeMetodo, E ritorno, List<? extends Object> messaggi) {
        this.codice = codice;
        this.nomeMetodo = nomeMetodo;
        this.ritorno = ritorno;
        this.messaggi = messaggi;
    }

    /**
     * Crea e torna la struttura dati tornata dai metodi del WSClient
     */
    public WSReturn() {
    }
    
    /**
     * il codice tornato dal web service
     */
    private String codice;
    
    /**
     * il nome del metodo
     */
    private String nomeMetodo;
    
    /**
     * l'oggetto tornato dal metodo
     */
    private E ritorno;
    
    /**
     * i messaggi tornati dal web service
     */
    private List<? extends Object> messaggi;

    /**
     * Torna il codice tornato dal web service
     *
     * @return il codice tornato dal web service
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il codice tornato dal web service
     *
     * @param codice il codice tornato dal web service
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Torna i messaggi tornati dal web service
     *
     * @return i messaggi tornati dal web service
     */
    public List<? extends Object> getMessaggi() {
        return messaggi;
    }

    /**
     * Imposta i messaggi tornati dal web service
     *
     * @param messaggi i messaggi tornati dal web service
     */
    public void setMessaggi(List<? extends Object> messaggi) {
        this.messaggi = messaggi;
    }

    /**
     * Torna il nome del metodo
     *
     * @return il nome del metodo
     */
    public String getNomeMetodo() {
        return nomeMetodo;
    }

    /**
     * Imposta il nome del metodo
     *
     * @param nomeMetodo il nome del metodo
     */
    public void setNomeMetodo(String nomeMetodo) {
        this.nomeMetodo = nomeMetodo;
    }

    /**
     * Torna l'oggetto tornato dal metodo
     *
     * @return l'oggetto tornato dal metodo
     */
    public E getRitorno() {
        return ritorno;
    }

    /**
     * Imposta l'oggetto tornato dal metodo
     *
     * @param ritorno l'oggetto tornato dal metodo
     */
    public void setRitorno(E ritorno) {
        this.ritorno = ritorno;
    }
}
