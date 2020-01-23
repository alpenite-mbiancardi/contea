
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStRiduzioni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStRiduzioni"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Riduzione" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStRiduzioni", propOrder = {
    "riduzione"
})
public class ZptStRiduzioni {

    @XmlElement(name = "Riduzione", required = true)
    protected String riduzione;

    /**
     * Recupera il valore della proprietà riduzione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiduzione() {
        return riduzione;
    }

    /**
     * Imposta il valore della proprietà riduzione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiduzione(String value) {
        this.riduzione = value;
    }

}
