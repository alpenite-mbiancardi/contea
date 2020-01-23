
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
 *         &lt;element name="IFlagDc" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IFlagInfo" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IFlagPod" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="IVertrag" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
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
    "iFlagDc",
    "iFlagInfo",
    "iFlagPod",
    "iPartner",
    "iVertrag"
})
@XmlRootElement(name = "ZptContractDetail")
public class ZptContractDetail {

    @XmlElement(name = "ICc")
    protected String iCc;
    @XmlElement(name = "IFlagDc")
    protected String iFlagDc;
    @XmlElement(name = "IFlagInfo")
    protected String iFlagInfo;
    @XmlElement(name = "IFlagPod")
    protected String iFlagPod;
    @XmlElement(name = "IPartner")
    protected String iPartner;
    @XmlElement(name = "IVertrag")
    protected String iVertrag;

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
     * Recupera il valore della proprietà iFlagDc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagDc() {
        return iFlagDc;
    }

    /**
     * Imposta il valore della proprietà iFlagDc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagDc(String value) {
        this.iFlagDc = value;
    }

    /**
     * Recupera il valore della proprietà iFlagInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagInfo() {
        return iFlagInfo;
    }

    /**
     * Imposta il valore della proprietà iFlagInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagInfo(String value) {
        this.iFlagInfo = value;
    }

    /**
     * Recupera il valore della proprietà iFlagPod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagPod() {
        return iFlagPod;
    }

    /**
     * Imposta il valore della proprietà iFlagPod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagPod(String value) {
        this.iFlagPod = value;
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

    /**
     * Recupera il valore della proprietà iVertrag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIVertrag() {
        return iVertrag;
    }

    /**
     * Imposta il valore della proprietà iVertrag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIVertrag(String value) {
        this.iVertrag = value;
    }

}
