
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
 *         &lt;element name="IDatafrom" type="{urn:sap-com:document:sap:rfc:functions}date10" minOccurs="0"/&gt;
 *         &lt;element name="IDatato" type="{urn:sap-com:document:sap:rfc:functions}date10" minOccurs="0"/&gt;
 *         &lt;element name="IFlagEc" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IFlagPa" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IFlagSfatt" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
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
    "iDatafrom",
    "iDatato",
    "iFlagEc",
    "iFlagPa",
    "iFlagSfatt",
    "iPartner"
})
@XmlRootElement(name = "ZptCcListDett")
public class ZptCcListDett {

    @XmlElement(name = "ICc")
    protected String iCc;
    @XmlElement(name = "IDatafrom")
    protected String iDatafrom;
    @XmlElement(name = "IDatato")
    protected String iDatato;
    @XmlElement(name = "IFlagEc")
    protected String iFlagEc;
    @XmlElement(name = "IFlagPa")
    protected String iFlagPa;
    @XmlElement(name = "IFlagSfatt")
    protected String iFlagSfatt;
    @XmlElement(name = "IPartner")
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
     * Recupera il valore della proprietà iDatafrom.
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
     * Imposta il valore della proprietà iDatafrom.
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
     * Recupera il valore della proprietà iDatato.
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
     * Imposta il valore della proprietà iDatato.
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
     * Recupera il valore della proprietà iFlagEc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagEc() {
        return iFlagEc;
    }

    /**
     * Imposta il valore della proprietà iFlagEc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagEc(String value) {
        this.iFlagEc = value;
    }

    /**
     * Recupera il valore della proprietà iFlagPa.
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
     * Imposta il valore della proprietà iFlagPa.
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
     * Recupera il valore della proprietà iFlagSfatt.
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
     * Imposta il valore della proprietà iFlagSfatt.
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
