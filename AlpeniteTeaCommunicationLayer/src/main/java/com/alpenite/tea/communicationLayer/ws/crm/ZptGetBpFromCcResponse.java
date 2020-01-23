
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ePartner"
})
@XmlRootElement(name = "ZptGetBpFromCcResponse")
public class ZptGetBpFromCcResponse {

    @XmlElement(name = "EPartner", required = true)
    protected String ePartner;

    /**
     * Gets the value of the ePartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEPartner() {
        return ePartner;
    }

    /**
     * Sets the value of the ePartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEPartner(String value) {
        this.ePartner = value;
    }

}
