
package com.alpenite.tea.wdslfascicolo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptGetFascicolo.RfcExceptions.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ZptGetFascicolo.RfcExceptions"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ErroreContratti"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ZptGetFascicolo.RfcExceptions")
@XmlEnum
public enum ZptGetFascicoloRfcExceptions {

    @XmlEnumValue("ErroreContratti")
    ERRORE_CONTRATTI("ErroreContratti");
    private final String value;

    ZptGetFascicoloRfcExceptions(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZptGetFascicoloRfcExceptions fromValue(String v) {
        for (ZptGetFascicoloRfcExceptions c: ZptGetFascicoloRfcExceptions.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
