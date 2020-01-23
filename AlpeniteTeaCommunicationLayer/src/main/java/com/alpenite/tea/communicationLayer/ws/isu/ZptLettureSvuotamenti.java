
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
 *         &lt;element name="IA" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10" minOccurs="0"/&gt;
 *         &lt;element name="IDa" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10" minOccurs="0"/&gt;
 *         &lt;element name="IFlagLetture" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IFlagSac" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IFlagSvuota" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
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
    "ia",
    "iDa",
    "iFlagLetture",
    "iFlagSac",
    "iFlagSvuota",
    "iPartner",
    "iVertrag"
})
@XmlRootElement(name = "ZptLettureSvuotamenti")
public class ZptLettureSvuotamenti {

    @XmlElement(name = "IA")
    protected String ia;
    @XmlElement(name = "IDa")
    protected String iDa;
    @XmlElement(name = "IFlagLetture")
    protected String iFlagLetture;
    @XmlElement(name = "IFlagSac")
    protected String iFlagSac;
    @XmlElement(name = "IFlagSvuota")
    protected String iFlagSvuota;
    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;
    @XmlElement(name = "IVertrag")
    protected String iVertrag;

    /**
     * Recupera il valore della proprietà ia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIA() {
        return ia;
    }

    /**
     * Imposta il valore della proprietà ia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIA(String value) {
        this.ia = value;
    }

    /**
     * Recupera il valore della proprietà iDa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDa() {
        return iDa;
    }

    /**
     * Imposta il valore della proprietà iDa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDa(String value) {
        this.iDa = value;
    }

    /**
     * Recupera il valore della proprietà iFlagLetture.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagLetture() {
        return iFlagLetture;
    }

    /**
     * Imposta il valore della proprietà iFlagLetture.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagLetture(String value) {
        this.iFlagLetture = value;
    }

    /**
     * Recupera il valore della proprietà iFlagSac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagSac() {
        return iFlagSac;
    }

    /**
     * Imposta il valore della proprietà iFlagSac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagSac(String value) {
        this.iFlagSac = value;
    }

    /**
     * Recupera il valore della proprietà iFlagSvuota.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFlagSvuota() {
        return iFlagSvuota;
    }

    /**
     * Imposta il valore della proprietà iFlagSvuota.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFlagSvuota(String value) {
        this.iFlagSvuota = value;
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
