package com.alpenite.gui.components;

import com.alpenite.gui.components.abstractComponents.SelectComponent;
import java.io.IOException;
import java.lang.String;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Rappresenta un componente select che permette di selezionare un anno tra
 * l'attuale, x anni prima e y anni dopo
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class SelectAnni extends SelectComponent {
    
    /**
     * Crea e torna il componente che permette di selezionare gli anni indicati
     * 
     * @param anniPrima il numero di anni precedenti all'attuale da visualizzare
     * @param anniDopo il numero di anni successivi all'attuale da visualizzare
     */
    public SelectAnni(String nome, String id, Map<String, String> attributi, int anniPrima, int anniDopo) throws IOException {
        super(nome, id, attributi);
        
        if(anniPrima<0 || anniDopo<0)
            throw new IllegalArgumentException("Gli anni indicati devono essere positivi o nulli");
        
        this.anniDopo = anniDopo;
        this.anniPrima = anniPrima;
    }

    /**
     * Crea e torna il componente che permette di selezionare gli anni che
     * vanno dall'attuale ai dieci precedenti
     */
    public SelectAnni(String nome, String id, Map<String, String> attributi) throws IOException {
        this(nome, id, attributi, 10, 0);
    }

    /** il numero di anni precedenti all'attuale da visualizzare */
    private int anniPrima;
    
    /** il numero di anni successivi all'attuale da visualizzare */
    private int anniDopo;
    
    /**
     * Torna il numero di anni successivi all'attuale da visualizzare
     * @return il numero di anni successivi all'attuale da visualizzare
     */
    public int getAnniDopo() {
        return anniDopo;
    }

    /**
     * Imposta il numero di anni successivi all'attuale da visualizzare
     * @param anniDopo il numero di anni successivi all'attuale da visualizzare
     */
    public void setAnniDopo(int anniDopo) {
        this.anniDopo = anniDopo;
    }

    /**
     * Torna il numero di anni precedenti all'attuale da visualizzare
     * @return il numero di anni precedenti all'attuale da visualizzare
     */
    public int getAnniPrima() {
        return anniPrima;
    }

    /**
     * Imposta il numero di anni precedenti all'attuale da visualizzare
     * @param anniPrima il numero di anni precedenti all'attuale da visualizzare
     */
    public void setAnniPrima(int anniPrima) {
        this.anniPrima = anniPrima;
    }

    @Override
    public Map<String, String> getMappaValori() {
        if (mappaValori == null) {
            // prendo l'anno attuale
            int annoAttuale = GregorianCalendar.getInstance().get(Calendar.YEAR);
            mappaValori = new TreeMap<String, String>();
            
            // aggiungo gli anni precedenti
            for(int i=getAnniPrima(); i>=0; i--) {
                String anno = new Integer(annoAttuale-i).toString();
                mappaValori.put(anno, anno);
            }
            
            // aggiungo gli anni successivi
            for(int i=0; i<getAnniDopo(); i++) {
                String anno = new Integer(annoAttuale+(i+1)).toString();
                mappaValori.put(anno, anno);
            }
            
        }
        return mappaValori;
    }    

}
