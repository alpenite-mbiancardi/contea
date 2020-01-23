
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
 *         &lt;element name="ICc" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/&gt;
 *         &lt;element name="IDd" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IDr" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
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
    "iCc",
    "iDd",
    "iDr",
    "iPartner"
})
@XmlRootElement(name = "ZptCcDetail")
public class ZptCcDetail {

    @XmlElement(name = "ICc")
    protected String iCc;
    @XmlElement(name = "IDd")
    protected String iDd;
    @XmlElement(name = "IDr")
    protected String iDr;
    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;

    /**
     * Recupera il valore della proprietà iCc.
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
     * Imposta il valore della proprietà iCc.
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
     * Recupera il valore della proprietà iDd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDd() {
        return iDd;
    }

    /**
     * Imposta il valore della proprietà iDd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDd(String value) {
        this.iDd = value;
    }

    /**
     * Recupera il valore della proprietà iDr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDr() {
        return iDr;
    }

    /**
     * Imposta il valore della proprietà iDr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDr(String value) {
        this.iDr = value;
    }

    /**
     * Recupera il valore della proprietà iPartner.
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
     * Imposta il valore della proprietà iPartner.
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
