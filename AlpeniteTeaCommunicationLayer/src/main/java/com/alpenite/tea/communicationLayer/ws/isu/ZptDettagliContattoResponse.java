
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
 *         &lt;element name="EVertrag" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="EZzcontatto" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/>
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
    "eVertrag",
    "eZzcontatto",
    "etMess"
})
@XmlRootElement(name = "ZptDettagliContattoResponse")
public class ZptDettagliContattoResponse {

    @XmlElement(name = "EVertrag", required = true)
    protected String eVertrag;
    @XmlElement(name = "EZzcontatto", required = true)
    protected String eZzcontatto;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

    /**
     * Gets the value of the eVertrag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEVertrag() {
        return eVertrag;
    }

    /**
     * Sets the value of the eVertrag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEVertrag(String value) {
        this.eVertrag = value;
    }

    /**
     * Gets the value of the eZzcontatto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEZzcontatto() {
        return eZzcontatto;
    }

    /**
     * Sets the value of the eZzcontatto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEZzcontatto(String value) {
        this.eZzcontatto = value;
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
