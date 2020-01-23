package com.alpenite.tea.communicationLayer;

import com.alpenite.tea.communicationLayer.data.WSReturn;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Permette di valutare e gestire i WSResult tornati dai metodi di WSClient
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class WSReturnManager {

    /**
     * nome dell'attributo di sessione contenente il codice del messaggio
     */
    public static final String WSMESSAGE = "WSMESSAGE";
    /**
     * nome dell'attributo di sessione contenente la lista dei messaggi tornati
     * dal web service
     */
    public static final String WSMESSAGE_LIST = "WSMESSAGE_LIST";

    /**
     * Valuta il ritorno del metodo e, se presenti, imposta in sessione i
     * messaggi
     *
     * @param <E> il tipo dell'oggetto ritornato dal metodo
     * @param r il ritorno del metodo
     * @param session la sessione corrente
     * @return l'oggetto ritornato del metodo
     */
    public static <E> E evaluate(WSReturn<E> r, HttpSession session) {
        if (r == null) {
            throw new NullPointerException("Il risultato del metodo non puo'essere nullo");
        }
        if (session == null) {
            throw new NullPointerException("La sessione non puo'essere nulla");
        }

        // creo le liste
        ArrayList<String> messaggi = new ArrayList<String>();
        ArrayList<Object> messaggi_ws = new ArrayList<Object>();

        // controllo se le variabili di sessione esistono gia'
        if (session.getAttribute(WSMESSAGE) != null
                && session.getAttribute(WSMESSAGE) instanceof ArrayList) {
            messaggi = (ArrayList<String>) session.getAttribute(WSMESSAGE);
        }
        if (session.getAttribute(WSMESSAGE_LIST) != null
                && session.getAttribute(WSMESSAGE_LIST) instanceof ArrayList) {
            messaggi_ws = (ArrayList<Object>) session.getAttribute(WSMESSAGE_LIST);
        }

        // aggiungo i messaggi
        messaggi.add(r.getNomeMetodo() + "_" + r.getCodice());
        messaggi_ws.addAll(r.getMessaggi());

        // imposto le variabili di sessione
        session.setAttribute(WSMESSAGE, messaggi);
        session.setAttribute(WSMESSAGE_LIST, messaggi_ws);

        // torno il risultato del metodo
        return r.getRitorno();
    }

    /**
     * Elimina i messaggi presenti nella sessione
     *
     * @param session la sessione attuale
     */
    public static void resetMessages(HttpSession session) {
        if (session == null) {
            throw new NullPointerException("La sessione non puo'essere nulla");
        }
        session.removeAttribute(WSMESSAGE);
        session.removeAttribute(WSMESSAGE_LIST);
    }

    /**
     * Torna tutte le chiavi dei messaggi attualmente presenti in sessione
     *
     * @param session la sessione attuale
     * @return la lista delle chiavi dei messaggi
     */
    public static List<String> getMessageKeys(HttpSession session) {
        if (session == null) {
            throw new NullPointerException("La sessione non puo'essere nulla");
        }
        if (session.getAttribute(WSMESSAGE) != null
                && session.getAttribute(WSMESSAGE) instanceof ArrayList) {
            return (ArrayList<String>) session.getAttribute(WSMESSAGE);
        }
        return null;
    }

    /**
     * Torna tutti i messaggi tornati direttamente dal web service attualmente
     * presenti in sessione
     *
     * @param session la sessione attuale
     * @return la lista dei messaggi del web service
     */
    public static List<String> getWebServiceMessage(HttpSession session) {
        if (session == null) {
            throw new NullPointerException("La sessione non puo'essere nulla");
        }
        if (session.getAttribute(WSMESSAGE_LIST) != null
                && session.getAttribute(WSMESSAGE_LIST) instanceof ArrayList) {
            ArrayList<String> lista = new ArrayList<String>();
            List l = (List)session.getAttribute(WSMESSAGE_LIST);
            for(Object o : l) {
                try {
                    // tramite reflection preleva il messaggio dall'oggetto
                    Method m = o.getClass().getMethod("getMessage", new Class[0]);
                    Object result = m.invoke(o, new Object[0]);
                    if (result != null)
                        lista.add(result.toString());
                } catch (Exception ex) {
                    Logger.getLogger(WSReturnManager.class).warn(
                            "Impossibile aggiungere il messaggio del web service alla lista", ex);
                }
            }
            return lista;
        }
        return null;
    }
}
