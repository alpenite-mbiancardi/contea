
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
 *         &lt;element name="ICc" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="IsMndActive" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1" minOccurs="0"/>
 *         &lt;element name="IsSrZs09" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1" minOccurs="0"/>
 *         &lt;element name="IsSrZs10" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1" minOccurs="0"/>
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
    "iCc",
    "iPartner",
    "isMndActive",
    "isSrZs09",
    "isSrZs10"
})
@XmlRootElement(name = "ZptCheckMandAndRevocation")
public class ZptCheckMandAndRevocation {

    @XmlElement(name = "ICc", required = true)
    protected String iCc;
    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;
    @XmlElement(name = "IsMndActive")
    protected String isMndActive;
    @XmlElement(name = "IsSrZs09")
    protected String isSrZs09;
    @XmlElement(name = "IsSrZs10")
    protected String isSrZs10;

    /**
     * Gets the value of the iCc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICc() {
        return iCc;
    }

    /**
     * Sets the value of the iCc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICc(String value) {
        this.iCc = value;
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

    /**
     * Gets the value of the isMndActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMndActive() {
        return isMndActive;
    }

    /**
     * Sets the value of the isMndActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMndActive(String value) {
        this.isMndActive = value;
    }

    /**
     * Gets the value of the isSrZs09 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSrZs09() {
        return isSrZs09;
    }

    /**
     * Sets the value of the isSrZs09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSrZs09(String value) {
        this.isSrZs09 = value;
    }

    /**
     * Gets the value of the isSrZs10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSrZs10() {
        return isSrZs10;
    }

    /**
     * Sets the value of the isSrZs10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSrZs10(String value) {
        this.isSrZs10 = value;
    }

}
