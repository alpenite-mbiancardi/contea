
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptGetBpFromCc.RfcExceptions.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ZptGetBpFromCc.RfcExceptions"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NotFound"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ZptGetBpFromCc.RfcExceptions")
@XmlEnum
public enum ZptGetBpFromCcRfcExceptions {

    @XmlEnumValue("NotFound")
    NOT_FOUND("NotFound");
    private final String value;

    ZptGetBpFromCcRfcExceptions(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZptGetBpFromCcRfcExceptions fromValue(String v) {
        for (ZptGetBpFromCcRfcExceptions c: ZptGetBpFromCcRfcExceptions.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
