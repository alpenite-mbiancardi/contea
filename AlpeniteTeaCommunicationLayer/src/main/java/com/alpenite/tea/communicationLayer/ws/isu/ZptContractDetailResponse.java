
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
 *         &lt;element name="EReturn" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/&gt;
 *         &lt;element name="EsDc" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStDc"/&gt;
 *         &lt;element name="EsInfo" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStInfo"/&gt;
 *         &lt;element name="EsPod" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStPod"/&gt;
 *         &lt;element name="EtMess" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtMess"/&gt;
 *         &lt;element name="EtRiduzioni" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtRiduzioni"/&gt;
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
    "eReturn",
    "esDc",
    "esInfo",
    "esPod",
    "etMess",
    "etRiduzioni"
})
@XmlRootElement(name = "ZptContractDetailResponse")
public class ZptContractDetailResponse {

    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "EsDc", required = true)
    protected ZptStDc esDc;
    @XmlElement(name = "EsInfo", required = true)
    protected ZptStInfo esInfo;
    @XmlElement(name = "EsPod", required = true)
    protected ZptStPod esPod;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;
    @XmlElement(name = "EtRiduzioni", required = true)
    protected ZptTtRiduzioni etRiduzioni;

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
     * Recupera il valore della proprietà esDc.
     * 
     * @return
     *     possible object is
     *     {@link ZptStDc }
     *     
     */
    public ZptStDc getEsDc() {
        return esDc;
    }

    /**
     * Imposta il valore della proprietà esDc.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStDc }
     *     
     */
    public void setEsDc(ZptStDc value) {
        this.esDc = value;
    }

    /**
     * Recupera il valore della proprietà esInfo.
     * 
     * @return
     *     possible object is
     *     {@link ZptStInfo }
     *     
     */
    public ZptStInfo getEsInfo() {
        return esInfo;
    }

    /**
     * Imposta il valore della proprietà esInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStInfo }
     *     
     */
    public void setEsInfo(ZptStInfo value) {
        this.esInfo = value;
    }

    /**
     * Recupera il valore della proprietà esPod.
     * 
     * @return
     *     possible object is
     *     {@link ZptStPod }
     *     
     */
    public ZptStPod getEsPod() {
        return esPod;
    }

    /**
     * Imposta il valore della proprietà esPod.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStPod }
     *     
     */
    public void setEsPod(ZptStPod value) {
        this.esPod = value;
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

    /**
     * Recupera il valore della proprietà etRiduzioni.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtRiduzioni }
     *     
     */
    public ZptTtRiduzioni getEtRiduzioni() {
        return etRiduzioni;
    }

    /**
     * Imposta il valore della proprietà etRiduzioni.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtRiduzioni }
     *     
     */
    public void setEtRiduzioni(ZptTtRiduzioni value) {
        this.etRiduzioni = value;
    }

}
