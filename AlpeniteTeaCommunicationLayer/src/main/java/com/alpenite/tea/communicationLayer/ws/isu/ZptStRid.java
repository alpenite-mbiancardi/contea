
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStRid complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStRid"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Bankname" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Iban" type="{urn:sap-com:document:sap:rfc:functions}char34"/&gt;
 *         &lt;element name="MandateId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char35"/&gt;
 *         &lt;element name="Doctype" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10"/&gt;
 *         &lt;element name="Docid" type="{urn:sap-com:document:sap:soap:functions:mc-style}byte16"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStRid", propOrder = {
    "bankname",
    "iban",
    "mandateId",
    "doctype",
    "docid"
})
public class ZptStRid {

    @XmlElement(name = "Bankname", required = true)
    protected String bankname;
    @XmlElement(name = "Iban", required = true)
    protected String iban;
    @XmlElement(name = "MandateId", required = true)
    protected String mandateId;
    @XmlElement(name = "Doctype", required = true)
    protected String doctype;
    @XmlElement(name = "Docid", required = true)
    protected byte[] docid;

    /**
     * Recupera il valore della proprietà bankname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * Imposta il valore della proprietà bankname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankname(String value) {
        this.bankname = value;
    }

    /**
     * Recupera il valore della proprietà iban.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Imposta il valore della proprietà iban.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Recupera il valore della proprietà mandateId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandateId() {
        return mandateId;
    }

    /**
     * Imposta il valore della proprietà mandateId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandateId(String value) {
        this.mandateId = value;
    }

    /**
     * Recupera il valore della proprietà doctype.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctype() {
        return doctype;
    }

    /**
     * Imposta il valore della proprietà doctype.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctype(String value) {
        this.doctype = value;
    }

    /**
     * Recupera il valore della proprietà docid.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocid() {
        return docid;
    }

    /**
     * Imposta il valore della proprietà docid.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocid(byte[] value) {
        this.docid = value;
    }

}
