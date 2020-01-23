
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
 *         &lt;element name="EDetbol" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="EEmail" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="EMail" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="EMailpdf" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="EReturn" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/&gt;
 *         &lt;element name="EtMess" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtMess"/&gt;
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
    "eDetbol",
    "eEmail",
    "eMail",
    "eMailpdf",
    "eReturn",
    "etMess"
})
@XmlRootElement(name = "ZptFlagCartaceoResponse")
public class ZptFlagCartaceoResponse {

    @XmlElement(name = "EDetbol", required = true)
    protected String eDetbol;
    @XmlElement(name = "EEmail", required = true)
    protected String eEmail;
    @XmlElement(name = "EMail", required = true)
    protected String eMail;
    @XmlElement(name = "EMailpdf", required = true)
    protected String eMailpdf;
    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

    /**
     * Recupera il valore della proprietà eDetbol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEDetbol() {
        return eDetbol;
    }

    /**
     * Imposta il valore della proprietà eDetbol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEDetbol(String value) {
        this.eDetbol = value;
    }

    /**
     * Recupera il valore della proprietà eEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEEmail() {
        return eEmail;
    }

    /**
     * Imposta il valore della proprietà eEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEEmail(String value) {
        this.eEmail = value;
    }

    /**
     * Recupera il valore della proprietà eMail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Imposta il valore della proprietà eMail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

    /**
     * Recupera il valore della proprietà eMailpdf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMailpdf() {
        return eMailpdf;
    }

    /**
     * Imposta il valore della proprietà eMailpdf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMailpdf(String value) {
        this.eMailpdf = value;
    }

    /**
     * Recupera il valore della proprietà eReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEReturn() {
        return eReturn;
    }

    /**
     * Imposta il valore della proprietà eReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEReturn(String value) {
        this.eReturn = value;
    }

    /**
     * Recupera il valore della proprietà etMess.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtMess }
     *     
     */
    public ZptTtMess getEtMess() {
        return etMess;
    }

    /**
     * Imposta il valore della proprietà etMess.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtMess }
     *     
     */
    public void setEtMess(ZptTtMess value) {
        this.etMess = value;
    }

}
