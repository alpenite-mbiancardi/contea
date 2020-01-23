
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
 *         &lt;element name="EtMess" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtMess"/&gt;
 *         &lt;element name="ItContract" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtContract"/&gt;
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
    "etMess",
    "itContract"
})
@XmlRootElement(name = "ZptContractListResponse")
public class ZptContractListResponse {

    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;
    @XmlElement(name = "ItContract", required = true)
    protected ZptTtContract itContract;

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

    /**
     * Recupera il valore della proprietà itContract.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtContract }
     *     
     */
    public ZptTtContract getItContract() {
        return itContract;
    }

    /**
     * Imposta il valore della proprietà itContract.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtContract }
     *     
     */
    public void setItContract(ZptTtContract value) {
        this.itContract = value;
    }

}
