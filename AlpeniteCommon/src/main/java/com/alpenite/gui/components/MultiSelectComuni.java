package com.alpenite.gui.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

import com.alpenite.gui.components.abstractComponents.MultiSelectComponent;

/**
 * Rappresenta un componente multiselect che permette di selezionare un comune
 * tra quelli presenti.
 * La lista dei comuni viene prelevata da un file di configurazione chiamato
 * comuni.ini.
 * E'possibile impostare i valori da selezionare chiamando il metodo
 * setValoriSelezionati.
 * 
 * @author Corrado Battagliese
 */

public class MultiSelectComuni extends MultiSelectComponent {

	private static final String PATH_FILE_COMUNI = "comuni.ini";
	private Properties          comuni;
	private static final String NAME_PREDEFINITO = "comuni";
	private static final String ID_PREDEFINITO   = "comuni";
	/**
     * Crea e torna il componente che permette di selezionare dei comuni
     * dall'elenco dei comuni
     * 
     * @param nome il nome del campo
     * @param id l'id del campo
     * @param attributi la mappa attributo=valore da associare al campo
     * @exception IOException se non e'possibile leggere la lista delle province
     */
    
	public MultiSelectComuni(String nome, String id, Map<String, String> attributi)throws IOException {
		super(nome, id, attributi);
		// leggo dal file i comuni
		InputStream is = getClass().getClassLoader().getResourceAsStream(PATH_FILE_COMUNI);
		comuni = new Properties();
		comuni.load(is);	}

    /**
     * Crea e torna il componente che permette di selezionare dei comuni dall'
     * elenco dei comuni
     * 
     * @param attributi la mappa attributo=valore da associare al campo
     */
    public MultiSelectComuni(Map<String, String> attributi) throws IOException {
    	this(NAME_PREDEFINITO, ID_PREDEFINITO, attributi);
    }

    /**
     * Crea e torna il componente che permette di selezionare i comuni dall'
     * elenco dei comuni
     */    
    public MultiSelectComuni() throws IOException {
    	this(null);
    }

	@Override
	public Map<String, String> getMappaValori() {
		if (mappaValori == null) {
			mappaValori = new TreeMap<String, String>();
			
			// uso un TreeSet per ordinare i valori disordinati del Properties
			TreeSet<String> val = new TreeSet<String>(comuni.stringPropertyNames());
			for(String v : val) {
			    mappaValori.put(v, comuni.getProperty(v));
			}
		}
		return mappaValori;
	}
}