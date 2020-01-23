package com.alpenite.gui.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Rappresenta un componente select che permette di selezionare una provincia
 * tra quelle presenti.
 * La lista delle province viene prelevata da un file di configurazione chiamato
 * province.ini.
 * E'possibile impostare il valore da selezionare chiamando il metodo
 * setValoreSelezionato.
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class SelectCountries {
    
    /**
     * Crea e torna il componente che permette di selezionare la provincia
     * dall'elenco delle province
     * 
     * @param nome il nome del campo
     * @param id l'id del campo
     * @param attributi la mappa attributo=valore da associare al campo
     * @exception IOException se non e'possibile leggere la lista delle province
     */
    public SelectCountries(String nome, String id, Map<String, String> attributi) throws IOException {
        this.nome = nome;
        this.id = id;
        this.attributi = attributi==null?new HashMap<String, String>():attributi;
        
        // leggo dal file le province
        InputStream is = getClass().getClassLoader().getResourceAsStream(PATH_FILE_COUNTRIES);
        countries = new Properties();
        countries.load(is);
    }
    
    /**
     * Crea e torna il componente che permette di selezionare le province dall'
     * elenco delle province
     * 
     * @param attributi la mappa attributo=valore da associare al campo
     */
    public SelectCountries(Map<String, String> attributi) throws IOException {
        this(NAME_PREDEFINITO, ID_PREDEFINITO, attributi);
    }
    
    /**
     * Crea e torna il componente che permette di selezionare le province dall'
     * elenco delle province
     */    
    public SelectCountries() throws IOException {
        this(null);
    }
    
    
    
    /** costante con il nome del file che contiene la lista delle province */
    public static String PATH_FILE_COUNTRIES = "countries.ini";
    
    /** il nome predefinito del campo */
    public static String NAME_PREDEFINITO = "paese";
    
    /** l'id predefinito del campo */
    public static String ID_PREDEFINITO = "paese";
    
    /** il nome del campo */
    private String nome;
    
    /** l'id del campo */
    private String id;
    
    /** la mappa degli attributi e dei valori */
    private Map<String, String> attributi;
    
    /** le province caricate dal file di configurazione */
    private Properties countries;
    
    /** l'html generato dal metodo toString */
    private String html;
    
    /** il valore che deve essere selezionato */
    private String valoreSelezionato;
    
    /**
     * Torna il valore che dovrà essere selezionato
     * @return il valore che dovrà essere selezionato
     */
    public String getValoreSelezionato() {
        return valoreSelezionato;
    }

    /**
     * Imposta il valore che dovrà essere selezionato
     * @param valoreSelezionato il valore che dovrà essere selezionato
     */
    public void setValoreSelezionato(String valoreSelezionato) {
        this.valoreSelezionato = valoreSelezionato;
        this.html = null;
    }

    @Override
    public String toString() {
        // se l'html è già stato generato lo torno
        if (this.html != null) return this.html;
        
        // svolgo gli attributi
        StringBuilder args = new StringBuilder();
        for (String attributo : attributi.keySet()) {
            args.append(" ").append(attributo).append(" = \"").append(attributi.get(attributo)).append("\"");
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<select name=\"").append(nome).append("\" id=\"").append(id).append("\"").append(args.toString()).append(">");
        if(valoreSelezionato == null)
        	html.append("\n\t<option></option>");
        
        // svolgo le opzioni ordinandole per nome
        TreeSet<String> prov = new TreeSet<String>(countries.stringPropertyNames());
        for(String provincia: prov) {
            String valore = provincia;
            String nomeValore = ""+countries.getProperty(valore);
            String selezionato = valore.equals(valoreSelezionato)?" selected=\"selected\"":"";
            html.append("\n\t<option value=\"").append(valore).append("\"").append(selezionato).append(">").append(nomeValore).append("</option>");
        }
        
        html.append("\n</select >");
        this.html = html.toString();
        return this.html;
    }
    
}
