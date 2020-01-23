package com.alpenite.tea.svuotamentiLetture;

/**
 * Classe contenente metodi statici di utilità
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class Utils {
    
    /**
     * Converte la data che arriva dal parametro in una stringa compatibile
     * con il parametro del ws
     * @param data la data formattata gg/mm/aaaa
     * @return la data formattata aaaammgg
     */
    public static String getDataFiltro(String data) {
        if (data==null||data.isEmpty()||data.equals("null")) return "";
        String[] e = data.split("/");
        return e[2]+"-"+e[1]+"-"+e[0];
    }

}
