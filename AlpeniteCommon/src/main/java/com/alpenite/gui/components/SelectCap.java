package com.alpenite.gui.components;

import com.alpenite.gui.components.abstractComponents.SelectComponent;
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
public class SelectCap extends SelectComponent {
    
    /**
     * Crea e torna il componente che permette di selezionare la provincia
     * dall'elenco delle province
     * 
     * @param nome il nome del campo
     * @param id l'id del campo
     * @param attributi la mappa attributo=valore da associare al campo
     * @exception IOException se non e'possibile leggere la lista delle province
     */
    public SelectCap(String nome, String id, Map<String, String> attributi) throws IOException {
        super(nome, id, attributi);
        
        // leggo dal file le province
        InputStream is = getClass().getClassLoader().getResourceAsStream(PATH_FILE_PROVINCE);
        cap = new Properties();
        cap.load(is);
    }
    
    /**
     * Crea e torna il componente che permette di selezionare le province dall'
     * elenco delle province
     * 
     * @param attributi la mappa attributo=valore da associare al campo
     */
    public SelectCap(Map<String, String> attributi) throws IOException {
        this(NAME_PREDEFINITO, ID_PREDEFINITO, attributi);
    }
    
    /**
     * Crea e torna il componente che permette di selezionare le province dall'
     * elenco delle province
     */    
    public SelectCap() throws IOException {
        this(null);
    }
    
    /** costante con il nome del file che contiene la lista delle province */
    public static String PATH_FILE_PROVINCE = "cap.ini";
    
    /** il nome predefinito del campo */
    public static String NAME_PREDEFINITO = "cap";
    
    /** l'id predefinito del campo */
    public static String ID_PREDEFINITO = "cap";
    
    /** le province caricate dal file di configurazione */
    private Properties cap;
    
    @Override
    public Map<String, String> getMappaValori() {
        if (mappaValori == null) {
            mappaValori = new TreeMap<String, String>();
            
            // uso un TreeSet per ordinare i valori disordinati del Properties
            TreeSet<String> val = new TreeSet<String>(cap.stringPropertyNames());
            for(String v : val) {
                mappaValori.put(v, cap.getProperty(v));
            }
        }
        return mappaValori;
    }
    
    /**
     * Ritorna la lista di valori che hanno la chiave che contiene la stringa "key"
     * @param key 
     * @return lista di valori
     */
    public List<String> getValori(String key){
    	List<String> values = new ArrayList<String>();
    	for(String keyMap : this.getMappaValori().keySet()){
    		if(keyMap.contains(key)){
    			values.add(this.getMappaValori().get(keyMap));
    		}
    	}
    	return values;
    }

}
