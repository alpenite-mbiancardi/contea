
package com.roytuts.soap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZconteaPaymentOutGet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZconteaPaymentOutGet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Xblnr" type="{urn:sap-com:document:sap:soap:functions:mc-style}string"/>
 *         &lt;element name="Opupk" type="{urn:sap-com:document:sap:soap:functions:mc-style}string"/>
 *         &lt;element name="Bldat" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/>
 *         &lt;element name="Bukrs" type="{urn:sap-com:document:sap:soap:functions:mc-style}string"/>
 *         &lt;element name="Zzcartacr" type="{urn:sap-com:document:sap:soap:functions:mc-style}string"/>
 *         &lt;element name="Zzcartacrdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/>
 *         &lt;element name="Zzcartaid" type="{urn:sap-com:document:sap:soap:functions:mc-style}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZconteaPaymentOutGet", propOrder = {
    "xblnr",
    "opupk",
    "bldat",
    "bukrs",
    "zzcartacr",
    "zzcartacrdata",
    "zzcartaid"
})
public class ZconteaPaymentOutGet {

    @XmlElement(name = "Xblnr", required = true)
    protected String xblnr;
    @XmlElement(name = "Opupk", required = true)
    protected String opupk;
    @XmlElement(name = "Bldat", required = true)
    protected String bldat;
    @XmlElement(name = "Bukrs", required = true)
    protected String bukrs;
    @XmlElement(name = "Zzcartacr", required = true)
    protected String zzcartacr;
    @XmlElement(name = "Zzcartacrdata", required = true)
    protected String zzcartacrdata;
    @XmlElement(name = "Zzcartaid", required = true)
    protected String zzcartaid;

    /**
     * Gets the value of the xblnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXblnr() {
        return xblnr;
    }

    /**
     * Sets the value of the xblnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXblnr(String value) {
        this.xblnr = value;
    }

    /**
     * Gets the value of the opupk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpupk() {
        return opupk;
    }

    /**
     * Sets the value of the opupk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpupk(String value) {
        this.opupk = value;
    }

    /**
     * Gets the value of the bldat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBldat() {
        return bldat;
    }

    /**
     * Sets the value of the bldat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBldat(String value) {
        this.bldat = value;
    }

    /**
     * Gets the value of the bukrs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBukrs() {
        return bukrs;
    }

    /**
     * Sets the value of the bukrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBukrs(String value) {
        this.bukrs = value;
    }

    /**
     * Gets the value of the zzcartacr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzcartacr() {
        return zzcartacr;
    }

    /**
     * Sets the value of the zzcartacr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzcartacr(String value) {
        this.zzcartacr = value;
    }

    /**
     * Gets the value of the zzcartacrdata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzcartacrdata() {
        return zzcartacrdata;
    }

    /**
     * Sets the value of the zzcartacrdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzcartacrdata(String value) {
        this.zzcartacrdata = value;
    }

    /**
     * Gets the value of the zzcartaid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzcartaid() {
        return zzcartaid;
    }

    /**
     * Sets the value of the zzcartaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzcartaid(String value) {
        this.zzcartaid = value;
    }

}
