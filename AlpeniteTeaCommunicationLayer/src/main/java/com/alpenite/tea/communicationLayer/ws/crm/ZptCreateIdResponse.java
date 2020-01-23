
package com.alpenite.tea.communicationLayer.ws.crm;

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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EtReturn" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapiret2T"/>
 *         &lt;element name="EvEletSuccess" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/>
 *         &lt;element name="EvPecSuccess" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "etReturn",
    "evEletSuccess",
    "evPecSuccess"
})
@XmlRootElement(name = "ZptCreateIdResponse")
public class ZptCreateIdResponse {

    @XmlElement(name = "EtReturn", required = true)
    protected Bapiret2T etReturn;
    @XmlElement(name = "EvEletSuccess", required = true)
    protected String evEletSuccess;
    @XmlElement(name = "EvPecSuccess", required = true)
    protected String evPecSuccess;

    /**
     * Recupera il valore della proprietà etReturn.
     * 
     * @return
     *     possible object is
     *     {@link Bapiret2T }
     *     
     */
    public Bapiret2T getEtReturn() {
        return etReturn;
    }

    /**
     * Imposta il valore della proprietà etReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapiret2T }
     *     
     */
    public void setEtReturn(Bapiret2T value) {
        this.etReturn = value;
    }

    /**
     * Recupera il valore della proprietà evEletSuccess.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvEletSuccess() {
        return evEletSuccess;
    }

    /**
     * Imposta il valore della proprietà evEletSuccess.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvEletSuccess(String value) {
        this.evEletSuccess = value;
    }

    /**
     * Recupera il valore della proprietà evPecSuccess.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvPecSuccess() {
        return evPecSuccess;
    }

    /**
     * Imposta il valore della proprietà evPecSuccess.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvPecSuccess(String value) {
        this.evPecSuccess = value;
    }

}
