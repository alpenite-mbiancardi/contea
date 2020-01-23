
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
 *         &lt;element name="IDetbol" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IEmail" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IMail" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IMailpdf" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="ITipiConto" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtVktyp" minOccurs="0"/&gt;
 *         &lt;element name="IUpdate" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
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
    "iDetbol",
    "iEmail",
    "iMail",
    "iMailpdf",
    "iPartner",
    "iTipiConto",
    "iUpdate"
})
@XmlRootElement(name = "ZptFlagCartaceo")
public class ZptFlagCartaceo {

    @XmlElement(name = "ICc")
    protected String iCc;
    @XmlElement(name = "IDetbol")
    protected String iDetbol;
    @XmlElement(name = "IEmail")
    protected String iEmail;
    @XmlElement(name = "IMail")
    protected String iMail;
    @XmlElement(name = "IMailpdf")
    protected String iMailpdf;
    @XmlElement(name = "IPartner")
    protected String iPartner;
    @XmlElement(name = "ITipiConto")
    protected ZptTtVktyp iTipiConto;
    @XmlElement(name = "IUpdate")
    protected String iUpdate;

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
     * Recupera il valore della proprietà iDetbol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDetbol() {
        return iDetbol;
    }

    /**
     * Imposta il valore della proprietà iDetbol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDetbol(String value) {
        this.iDetbol = value;
    }

    /**
     * Recupera il valore della proprietà iEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIEmail() {
        return iEmail;
    }

    /**
     * Imposta il valore della proprietà iEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIEmail(String value) {
        this.iEmail = value;
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
     * Recupera il valore della proprietà iMailpdf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMailpdf() {
        return iMailpdf;
    }

    /**
     * Imposta il valore della proprietà iMailpdf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMailpdf(String value) {
        this.iMailpdf = value;
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
     * Recupera il valore della proprietà iTipiConto.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtVktyp }
     *     
     */
    public ZptTtVktyp getITipiConto() {
        return iTipiConto;
    }

    /**
     * Imposta il valore della proprietà iTipiConto.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtVktyp }
     *     
     */
    public void setITipiConto(ZptTtVktyp value) {
        this.iTipiConto = value;
    }

    /**
     * Recupera il valore della proprietà iUpdate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIUpdate() {
        return iUpdate;
    }

    /**
     * Imposta il valore della proprietà iUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIUpdate(String value) {
        this.iUpdate = value;
    }

}
