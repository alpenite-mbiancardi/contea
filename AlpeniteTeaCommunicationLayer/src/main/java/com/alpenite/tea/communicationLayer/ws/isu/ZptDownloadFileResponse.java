
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
 *         &lt;element name="EBin" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="EExt" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="EMimetyp" type="{urn:sap-com:document:sap:rfc:functions}char128"/&gt;
 *         &lt;element name="EName" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
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
    "eBin",
    "eExt",
    "eMimetyp",
    "eName",
    "eReturn",
    "etMess"
})
@XmlRootElement(name = "ZptDownloadFileResponse")
public class ZptDownloadFileResponse {

    @XmlElement(name = "EBin", required = true)
    protected byte[] eBin;
    @XmlElement(name = "EExt", required = true)
    protected String eExt;
    @XmlElement(name = "EMimetyp", required = true)
    protected String eMimetyp;
    @XmlElement(name = "EName", required = true)
    protected String eName;
    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

    /**
     * Recupera il valore della proprietà eBin.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEBin() {
        return eBin;
    }

    /**
     * Imposta il valore della proprietà eBin.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEBin(byte[] value) {
        this.eBin = value;
    }

    /**
     * Recupera il valore della proprietà eExt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEExt() {
        return eExt;
    }

    /**
     * Imposta il valore della proprietà eExt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEExt(String value) {
        this.eExt = value;
    }

    /**
     * Recupera il valore della proprietà eMimetyp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMimetyp() {
        return eMimetyp;
    }

    /**
     * Imposta il valore della proprietà eMimetyp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMimetyp(String value) {
        this.eMimetyp = value;
    }

    /**
     * Recupera il valore della proprietà eName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEName() {
        return eName;
    }

    /**
     * Imposta il valore della proprietà eName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEName(String value) {
        this.eName = value;
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
