
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EReturn" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="EtList" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtList2"/>
 *         &lt;element name="EtMess" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtMess"/>
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
    "eReturn",
    "etList",
    "etMess"
})
@XmlRootElement(name = "ZptDettagliFatturaResponse")
public class ZptDettagliFatturaResponse {

    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "EtList", required = true)
    protected ZptTtList2 etList;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

    /**
     * Gets the value of the eReturn property.
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
     * Sets the value of the eReturn property.
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
     * Gets the value of the etList property.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtList2 }
     *     
     */
    public ZptTtList2 getEtList() {
        return etList;
    }

    /**
     * Sets the value of the etList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtList2 }
     *     
     */
    public void setEtList(ZptTtList2 value) {
        this.etList = value;
    }

    /**
     * Gets the value of the etMess property.
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
     * Sets the value of the etMess property.
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
