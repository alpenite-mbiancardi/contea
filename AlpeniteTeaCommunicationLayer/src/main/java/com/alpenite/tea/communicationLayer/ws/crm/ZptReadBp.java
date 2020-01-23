
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
 *         &lt;element name="IAzienda" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="IJahiaType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
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
    "iAzienda",
    "iJahiaType",
    "iPartner"
})
@XmlRootElement(name = "ZptReadBp")
public class ZptReadBp {

    @XmlElement(name = "IAzienda")
    protected String iAzienda;
    @XmlElement(name = "IJahiaType", required = true)
    protected String iJahiaType;
    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;

    /**
     * Gets the value of the iAzienda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIAzienda() {
        return iAzienda;
    }

    /**
     * Sets the value of the iAzienda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIAzienda(String value) {
        this.iAzienda = value;
    }

    /**
     * Gets the value of the iJahiaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIJahiaType() {
        return iJahiaType;
    }

    /**
     * Sets the value of the iJahiaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIJahiaType(String value) {
        this.iJahiaType = value;
    }

    /**
     * Gets the value of the iPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPartner() {
        return iPartner;
    }

    /**
     * Sets the value of the iPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPartner(String value) {
        this.iPartner = value;
    }

}
