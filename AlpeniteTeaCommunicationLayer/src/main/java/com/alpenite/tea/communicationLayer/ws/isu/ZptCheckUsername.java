
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
 *         &lt;element name="IBpType" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/&gt;
 *         &lt;element name="ICc" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/&gt;
 *         &lt;element name="ICf" type="{urn:sap-com:document:sap:rfc:functions}char20" minOccurs="0"/&gt;
 *         &lt;element name="IMail" type="{urn:sap-com:document:sap:rfc:functions}char241" minOccurs="0"/&gt;
 *         &lt;element name="IPi" type="{urn:sap-com:document:sap:rfc:functions}char20" minOccurs="0"/&gt;
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
     * Recupera il valore della proprietà iBpType.
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
     * Imposta il valore della proprietà iBpType.
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
     * Recupera il valore della proprietà iCf.
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
     * Imposta il valore della proprietà iCf.
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
     * Recupera il valore della proprietà iMail.
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
     * Imposta il valore della proprietà iMail.
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
     * Recupera il valore della proprietà iPi.
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
     * Imposta il valore della proprietà iPi.
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
