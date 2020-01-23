
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
 *         &lt;element name="IBpType" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/>
 *         &lt;element name="ICc" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/>
 *         &lt;element name="ICf" type="{urn:sap-com:document:sap:rfc:functions}char20" minOccurs="0"/>
 *         &lt;element name="IMail" type="{urn:sap-com:document:sap:rfc:functions}char241" minOccurs="0"/>
 *         &lt;element name="IPi" type="{urn:sap-com:document:sap:rfc:functions}char20" minOccurs="0"/>
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
    "iBpType",
    "iCc",
    "iCf",
    "iMail",
    "iPi"
})
@XmlRootElement(name = "ZptCheckUsername")
public class ZptCheckUsername {

    @XmlElement(name = "IBpType")
    protected String iBpType;
    @XmlElement(name = "ICc")
    protected String iCc;
    @XmlElement(name = "ICf")
    protected String iCf;
    @XmlElement(name = "IMail")
    protected String iMail;
    @XmlElement(name = "IPi")
    protected String iPi;

    /**
     * Gets the value of the iBpType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIBpType() {
        return iBpType;
    }

    /**
     * Sets the value of the iBpType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIBpType(String value) {
        this.iBpType = value;
    }

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
     * Gets the value of the iCf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICf() {
        return iCf;
    }

    /**
     * Sets the value of the iCf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICf(String value) {
        this.iCf = value;
    }

    /**
     * Gets the value of the iMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMail() {
        return iMail;
    }

    /**
     * Sets the value of the iMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMail(String value) {
        this.iMail = value;
    }

    /**
     * Gets the value of the iPi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPi() {
        return iPi;
    }

    /**
     * Sets the value of the iPi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPi(String value) {
        this.iPi = value;
    }

}
