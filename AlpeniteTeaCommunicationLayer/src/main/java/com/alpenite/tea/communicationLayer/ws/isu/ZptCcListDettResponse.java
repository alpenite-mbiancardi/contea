
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
 *         &lt;element name="EtList" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtList"/&gt;
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
    "eReturn",
    "etList",
    "etMess"
})
@XmlRootElement(name = "ZptCcListDettResponse")
public class ZptCcListDettResponse {

    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "EtList", required = true)
    protected ZptTtList etList;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

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
     * Recupera il valore della proprietà etList.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtList }
     *     
     */
    public ZptTtList getEtList() {
        return etList;
    }

    /**
     * Imposta il valore della proprietà etList.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtList }
     *     
     */
    public void setEtList(ZptTtList value) {
        this.etList = value;
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
