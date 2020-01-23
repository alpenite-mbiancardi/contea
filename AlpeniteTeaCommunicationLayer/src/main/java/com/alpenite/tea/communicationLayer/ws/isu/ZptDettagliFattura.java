
package com.alpenite.tea.communicationLayer.ws.isu;

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
 *         &lt;element name="IDatafrom" type="{urn:sap-com:document:sap:rfc:functions}date10" minOccurs="0"/>
 *         &lt;element name="IDatato" type="{urn:sap-com:document:sap:rfc:functions}date10" minOccurs="0"/>
 *         &lt;element name="IFlagPa" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="IFlagSfatt" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="IXblnr" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
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
    "iDatafrom",
    "iDatato",
    "iFlagPa",
    "iFlagSfatt",
    "iPartner",
    "iXblnr"
})
@XmlRootElement(name = "ZptDettagliFattura")
public class ZptDettagliFattura {

    @XmlElement(name = "IDatafrom")
    protected String iDatafrom;
    @XmlElement(name = "IDatato")
    protected String iDatato;
    @XmlElement(name = "IFlagPa")
    protected String iFlagPa;
    @XmlElement(name = "IFlagSfatt")
    protected String iFlagSfatt;
    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;
    @XmlElement(name = "IXblnr", required = true)
    protected String iXblnr;

    /**
     * Gets the value of the iDatafrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDatafrom() {
        return iDatafrom;
    }

    /**
     * Sets the value of the iDatafrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDatafrom(String value) {
        this.iDatafrom = value;
    }

    /**
     * Gets the value of the iDatato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDatato() {
        return iDatato;
    }

    /**
     * Sets the value of the iDatato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDatato(String value) {
        this.iDatato = value;
    }

    /**
     * Gets the value of the iFlagPa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagPa() {
        return iFlagPa;
    }

    /**
     * Sets the value of the iFlagPa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagPa(String value) {
        this.iFlagPa = value;
    }

    /**
     * Gets the value of the iFlagSfatt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagSfatt() {
        return iFlagSfatt;
    }

    /**
     * Sets the value of the iFlagSfatt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagSfatt(String value) {
        this.iFlagSfatt = value;
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
     * Gets the value of the iXblnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIXblnr() {
        return iXblnr;
    }

    /**
     * Sets the value of the iXblnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIXblnr(String value) {
        this.iXblnr = value;
    }

}
