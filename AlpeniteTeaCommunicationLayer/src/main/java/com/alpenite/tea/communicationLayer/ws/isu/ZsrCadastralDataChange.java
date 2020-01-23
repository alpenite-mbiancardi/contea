
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
 *         &lt;element name="ICodCat" type="{urn:sap-com:document:sap:rfc:functions}char5" minOccurs="0"/&gt;
 *         &lt;element name="IComAmm" type="{urn:sap-com:document:sap:rfc:functions}char40" minOccurs="0"/&gt;
 *         &lt;element name="IComCat" type="{urn:sap-com:document:sap:rfc:functions}char40" minOccurs="0"/&gt;
 *         &lt;element name="IPart" type="{urn:sap-com:document:sap:rfc:functions}char5" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="IPartst" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="ISezUrb" type="{urn:sap-com:document:sap:rfc:functions}char3" minOccurs="0"/&gt;
 *         &lt;element name="ISubal" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/&gt;
 *         &lt;element name="ITipopar" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
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
    "iCodCat",
    "iComAmm",
    "iComCat",
    "iPart",
    "iPartner",
    "iPartst",
    "iSezUrb",
    "iSubal",
    "iTipopar",
    "iVertrag"
})
@XmlRootElement(name = "ZsrCadastralDataChange")
public class ZsrCadastralDataChange {

    @XmlElement(name = "ICodCat")
    protected String iCodCat;
    @XmlElement(name = "IComAmm")
    protected String iComAmm;
    @XmlElement(name = "IComCat")
    protected String iComCat;
    @XmlElement(name = "IPart")
    protected String iPart;
    @XmlElement(name = "IPartner")
    protected String iPartner;
    @XmlElement(name = "IPartst")
    protected String iPartst;
    @XmlElement(name = "ISezUrb")
    protected String iSezUrb;
    @XmlElement(name = "ISubal")
    protected String iSubal;
    @XmlElement(name = "ITipopar")
    protected String iTipopar;
    @XmlElement(name = "IVertrag")
    protected String iVertrag;

    /**
     * Recupera il valore della proprietà iCodCat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICodCat() {
        return iCodCat;
    }

    /**
     * Imposta il valore della proprietà iCodCat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICodCat(String value) {
        this.iCodCat = value;
    }

    /**
     * Recupera il valore della proprietà iComAmm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIComAmm() {
        return iComAmm;
    }

    /**
     * Imposta il valore della proprietà iComAmm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIComAmm(String value) {
        this.iComAmm = value;
    }

    /**
     * Recupera il valore della proprietà iComCat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIComCat() {
        return iComCat;
    }

    /**
     * Imposta il valore della proprietà iComCat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIComCat(String value) {
        this.iComCat = value;
    }

    /**
     * Recupera il valore della proprietà iPart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPart() {
        return iPart;
    }

    /**
     * Imposta il valore della proprietà iPart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPart(String value) {
        this.iPart = value;
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
     * Recupera il valore della proprietà iPartst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPartst() {
        return iPartst;
    }

    /**
     * Imposta il valore della proprietà iPartst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPartst(String value) {
        this.iPartst = value;
    }

    /**
     * Recupera il valore della proprietà iSezUrb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISezUrb() {
        return iSezUrb;
    }

    /**
     * Imposta il valore della proprietà iSezUrb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISezUrb(String value) {
        this.iSezUrb = value;
    }

    /**
     * Recupera il valore della proprietà iSubal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISubal() {
        return iSubal;
    }

    /**
     * Imposta il valore della proprietà iSubal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISubal(String value) {
        this.iSubal = value;
    }

    /**
     * Recupera il valore della proprietà iTipopar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITipopar() {
        return iTipopar;
    }

    /**
     * Imposta il valore della proprietà iTipopar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITipopar(String value) {
        this.iTipopar = value;
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
