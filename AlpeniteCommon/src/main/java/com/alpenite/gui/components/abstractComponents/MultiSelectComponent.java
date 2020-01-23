package com.alpenite.gui.components.abstractComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Rappresenta un generico componente multiselect
 * @author corrado
 *
 */
public abstract class MultiSelectComponent extends SelectComponent{

	/**
	 * Crea e torna il componente multiselect
	 * @param nome
	 * @param id
	 * @param attributi
	 * @throws IOException
	 */
	public MultiSelectComponent(String nome, String id,	Map<String, String> attributi) throws IOException {
		super(nome, id, attributi);
	}
	
	/** I valori che devono essere selezionati*/
	private List<String> valoriSelezionati = new ArrayList<String>();
	
	/**Torna la lista dei valori selezionati*/
	public List<String> getValoriSelezionati(){
		return this.valoriSelezionati;
	}
	public void setValoriSelezionati(List<String> valoriSelezionati){
		this.valoriSelezionati=valoriSelezionati;
	}
	/**
	 * @deprecated In una multiselect si possono selezionare piu' valori. Utilizzare il metodo getValoriSelezionati()
	 */
	@Deprecated
	@Override
	public String getValoreSelezionato() {
		throw new UnsupportedOperationException("Please use the \"getValoriSelezionati\" method.");
	}
	
	/**
	 * @deprecated In una multiselect si possono selezionare piu' valori. Utilizzare il metodo setValoriSelezionati(String)
	 */
	@Deprecated
	@Override
	public void setValoreSelezionato(String valoreSelezionato) {
		throw new UnsupportedOperationException("Please use the \"setValoriSelezionati\" method.");
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
        html.append("<select multiple name=\"").append(nome).append("\" id=\"").append(id).append("\"").append(args.toString()).append(">");
        //html.append("\n\t<option></option>");
        
        // svolgo le opzioni ordinandole per nome
        for(String valore : getMappaValori().keySet()) {
            String nomeValore = ""+getMappaValori().get(valore);
            String selezionato = valoriSelezionati.contains(valore)?" selected=\"selected\"":"";
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
	@Override
    public String toString(String key){
    	
        // se l'html è già stato generato lo torno
        if (this.html != null) return this.html;
        
        // svolgo gli attributi
        StringBuilder args = new StringBuilder();
        for (String attributo : attributi.keySet()) {
            args.append(" ").append(attributo).append(" = \"").append(attributi.get(attributo)).append("\"");
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<select multiple name=\"").append(nome).append("\" id=\"").append(id).append("\"").append(args.toString()).append(">");
        //html.append("\n\t<option></option>");
        
        // svolgo le opzioni ordinandole per nome
        for(String valore : getMappaValori().keySet()) {
        	if(valore.contains(key)){
	            String nomeValore = ""+getMappaValori().get(valore);
	            String selezionato = valoriSelezionati.contains(valore)?" selected=\"selected\"":"";
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
	@Override
    public String toStringInverse() {
        // se l'html è già stato generato lo torno
        if (this.html != null) return this.html;
        
        // svolgo gli attributi
        StringBuilder args = new StringBuilder();
        for (String attributo : attributi.keySet()) {
            args.append(" ").append(attributo).append(" = \"").append(attributi.get(attributo)).append("\"");
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<select multiple name=\"").append(nome).append("\" id=\"").append(id).append("\"").append(args.toString()).append(">");
        html.append("\n\t<option></option>");
        
        TreeMap<String, String> mappaValoriReverse = new TreeMap<String,String>(Collections.reverseOrder());
        for(String key: getMappaValori().keySet()){
        	mappaValoriReverse.put(key, mappaValori.get(key));
        }
        
        // svolgo le opzioni ordinandole per nome
        for(String valore : mappaValoriReverse.keySet()) {
            String nomeValore = ""+getMappaValori().get(valore);
            String selezionato = valoriSelezionati.contains(valore)?" selected=\"selected\"":"";
            html.append("\n\t<option value=\"").append(valore).append("\"").append(selezionato).append(">").append(nomeValore).append("</option>");
        }
        
        html.append("\n</select >");
        this.html = html.toString();
        return this.html;
    }
}
