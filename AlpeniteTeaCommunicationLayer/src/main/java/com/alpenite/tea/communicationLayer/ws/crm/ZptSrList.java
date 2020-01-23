
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
 *         &lt;element name="IDateFrom" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="IDateTo" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
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
    "iDateFrom",
    "iDateTo",
    "iPartner"
})
@XmlRootElement(name = "ZptSrList")
public class ZptSrList {

    @XmlElement(name = "IDateFrom")
    protected String iDateFrom;
    @XmlElement(name = "IDateTo")
    protected String iDateTo;
    @XmlElement(name = "IPartner")
    protected String iPartner;

    /**
     * Gets the value of the iDateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDateFrom() {
        return iDateFrom;
    }

    /**
     * Sets the value of the iDateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDateFrom(String value) {
        this.iDateFrom = value;
    }

    /**
     * Gets the value of the iDateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDateTo() {
        return iDateTo;
    }

    /**
     * Sets the value of the iDateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDateTo(String value) {
        this.iDateTo = value;
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
