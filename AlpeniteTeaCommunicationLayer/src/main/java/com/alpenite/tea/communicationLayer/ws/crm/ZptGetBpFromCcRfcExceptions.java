
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZptGetBpFromCc.RfcExceptions.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ZptGetBpFromCc.RfcExceptions">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotFound"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
