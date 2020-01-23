package com.alpenite.gui.components.abstractComponents;

import java.util.HashMap;
import java.util.Map;

/**
 * Rappresenta un generico componente html 
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public abstract class Component {

    /**
     * Crea e torna un generico componente
     * 
     * @param nome il nome del campo
     * @param id l'id del campo
     * @param attributi la mappa attributo=valore da associare al campo
     */
    public Component(String nome, String id, Map<String, String> attributi) {
        this.nome = nome;
        this.id = id;
        this.attributi = attributi==null?new HashMap<String, String>():attributi;
    }
   
    /** il nome del campo */
    protected String nome;
    
    /** l'id del campo */
    protected String id;
    
    /** la mappa degli attributi e dei valori */
    protected Map<String, String> attributi;
    
    /** l'html generato dal metodo toString */
    protected String html;
    
}
