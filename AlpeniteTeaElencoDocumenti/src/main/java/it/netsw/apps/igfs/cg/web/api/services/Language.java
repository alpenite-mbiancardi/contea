
package it.netsw.apps.igfs.cg.web.api.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Language.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Language">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IT"/>
 *     &lt;enumeration value="EN"/>
 *     &lt;enumeration value="DE"/>
 *     &lt;enumeration value="FR"/>
 *     &lt;enumeration value="ES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Language")
@XmlEnum
public enum Language {

    IT,
    EN,
    DE,
    FR,
    ES;

    public String value() {
        return name();
    }

    public static Language fromValue(String v) {
        return valueOf(v);
    }

}
