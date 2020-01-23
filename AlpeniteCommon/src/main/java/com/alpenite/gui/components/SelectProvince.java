package com.alpenite.gui.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

import com.alpenite.gui.components.abstractComponents.SelectComponent;

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
public class SelectProvince extends SelectComponent {
    
    /**
     * Crea e torna il componente che permette di selezionare la provincia
     * dall'elenco delle province
     * 
     * @param nome il nome del campo
     * @param id l'id del campo
     * @param attributi la mappa attributo=valore da associare al campo
     * @exception IOException se non e'possibile leggere la lista delle province
     */
    public SelectProvince(String nome, String id, Map<String, String> attributi) throws IOException {
        super(nome, id, attributi);
        
        // leggo dal file le province
        InputStream is = getClass().getClassLoader().getResourceAsStream(PATH_FILE_PROVINCE);
        province = new Properties();
        province.load(is);
    }
    
    /**
     * Crea e torna il componente che permette di selezionare le province dall'
     * elenco delle province
     * 
     * @param attributi la mappa attributo=valore da associare al campo
     */
    public SelectProvince(Map<String, String> attributi) throws IOException {
        this(NAME_PREDEFINITO, ID_PREDEFINITO, attributi);
    }
    
    /**
     * Crea e torna il componente che permette di selezionare le province dall'
     * elenco delle province
     */    
    public SelectProvince() throws IOException {
        this(null);
    }
    
    /** costante con il nome del file che contiene la lista delle province */
    public static String PATH_FILE_PROVINCE = "province.ini";
    
    /** il nome predefinito del campo */
    public static String NAME_PREDEFINITO = "provincia";
    
    /** l'id predefinito del campo */
    public static String ID_PREDEFINITO = "provincia";
    
    /** le province caricate dal file di configurazione */
    private Properties province;
    
    @Override
    public Map<String, String> getMappaValori() {
        if (mappaValori == null) {
            mappaValori = new TreeMap<String, String>();
            
            // uso un TreeSet per ordinare i valori disordinati del Properties
            TreeSet<String> val = new TreeSet<String>(province.stringPropertyNames());
            for(String v : val) {
                mappaValori.put(v, province.getProperty(v));
            }
        }
        return mappaValori;
    }
}