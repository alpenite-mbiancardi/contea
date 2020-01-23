
package com.roytuts.soap.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZzconteaGetOnlinepayment.RfcExceptions.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ZzconteaGetOnlinepayment.RfcExceptions">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NoDocFound"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ZzconteaGetOnlinepayment.RfcExceptions")
@XmlEnum
public enum ZzconteaGetOnlinepaymentRfcExceptions {

    @XmlEnumValue("NoDocFound")
    NO_DOC_FOUND("NoDocFound");
    private final String value;

    ZzconteaGetOnlinepaymentRfcExceptions(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZzconteaGetOnlinepaymentRfcExceptions fromValue(String v) {
        for (ZzconteaGetOnlinepaymentRfcExceptions c: ZzconteaGetOnlinepaymentRfcExceptions.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
