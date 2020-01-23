package com.alpenite.gui.components.abstractComponents;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Rappresenta un generico componente select
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public abstract class SelectComponent extends Component {
    
    /**
     * Crea e torna il componente select
     * 
     * @param nome il nome del campo
     * @param id l'id del campo
     * @param attributi la mappa attributo=valore da associare al campo
     * @exception IOException se non e'possibile leggere la lista delle province
     */
    public SelectComponent(String nome, String id, Map<String, String> attributi) throws IOException {
        super(nome, id, attributi);
    }
    
    /** il valore che deve essere selezionato */
    private String valoreSelezionato;
    
    /** la mappa contenente l'associazione valore - descrizione */
    protected Map<String, String> mappaValori;
    
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
    
    /**
     * Torna la mappa contenente l'associazione valore - descrizione
     * @return la mappa contenente l'associazione valore - descrizione
     */
    public abstract Map<String, String> getMappaValori();
    
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
        html.append("\n\t<option></option>");
        
        // svolgo le opzioni ordinandole per nome
        for(String valore : getMappaValori().keySet()) {
            String nomeValore = ""+getMappaValori().get(valore);
            String selezionato = valore.equals(valoreSelezionato)?" selected=\"selected\"":"";
            html.append("\n\t<option value=\"").append(valore).append("\"").append(selezionato).append(">").append(nomeValore).append("</option>");
        }
        
        html.append("\n</select >");
        this.html = html.toString();
        return this.html;
    }    
    
    /**
     * Stampa il menu a tendina contenente solo i valori che hanno la chiave che contiene il valore key
     * @param key valore che deve contenere la chiave
     * @return stampa a video il menu a tendina contenente i valori
     */
    public String toString(String key){
    	
        // se l'html è già stato generato lo torno
        if (this.html != null) return this.html;
        
        // svolgo gli attributi
        StringBuilder args = new StringBuilder();
        for (String attributo : attributi.keySet()) {
            args.append(" ").append(attributo).append(" = \"").append(attributi.get(attributo)).append("\"");
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<select name=\"").append(nome).append("\" id=\"").append(id).append("\"").append(args.toString()).append(">");
        html.append("\n\t<option></option>");
        
        // svolgo le opzioni ordinandole per nome
        for(String valore : getMappaValori().keySet()) {
        	if(valore.contains(key)){
	            String nomeValore = ""+getMappaValori().get(valore);
	            String selezionato = valore.equals(valoreSelezionato)?" selected=\"selected\"":"";
	            html.append("\n\t<option value=\"").append(valore).append("\"").append(selezionato).append(">").append(nomeValore).append("</option>");
        	}
        }
        
        html.append("\n</select >");
        this.html = html.toString();
        return this.html; 
    }

    /**
     * Stampa il menu a tendina con i valori ordinati per ordine inverso
     * @return tampa a video il menu a tendina contenente i valori ordinati in ordine decrescente
     */
    public String toStringInverse() {
        // se l'html è già stato generato lo torno
        if (this.html != null) return this.html;
        
        // svolgo gli attributi
        StringBuilder args = new StringBuilder();
        for (String attributo : attributi.keySet()) {
            args.append(" ").append(attributo).append(" = \"").append(attributi.get(attributo)).append("\"");
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<select name=\"").append(nome).append("\" id=\"").append(id).append("\"").append(args.toString()).append(">");
        html.append("\n\t<option></option>");
        
        TreeMap<String, String> mappaValoriReverse = new TreeMap<String,String>(Collections.reverseOrder());
        for(String key: getMappaValori().keySet()){
        	mappaValoriReverse.put(key, mappaValori.get(key));
        }
        
        // svolgo le opzioni ordinandole per nome
        for(String valore : mappaValoriReverse.keySet()) {
            String nomeValore = ""+getMappaValori().get(valore);
            String selezionato = valore.equals(valoreSelezionato)?" selected=\"selected\"":"";
            html.append("\n\t<option value=\"").append(valore).append("\"").append(selezionato).append(">").append(nomeValore).append("</option>");
        }
        
        html.append("\n</select >");
        this.html = html.toString();
        return this.html;
    }
    
}
