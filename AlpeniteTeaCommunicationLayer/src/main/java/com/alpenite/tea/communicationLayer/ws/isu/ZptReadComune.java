
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IRegion" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "iRegion"
})
@XmlRootElement(name = "ZptReadComune")
public class ZptReadComune {

    @XmlElement(name = "IRegion", required = true)
    protected String iRegion;

    /**
     * Recupera il valore della proprietà iRegion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIRegion() {
        return iRegion;
    }

    /**
     * Imposta il valore della proprietà iRegion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIRegion(String value) {
        this.iRegion = value;
    }

}
