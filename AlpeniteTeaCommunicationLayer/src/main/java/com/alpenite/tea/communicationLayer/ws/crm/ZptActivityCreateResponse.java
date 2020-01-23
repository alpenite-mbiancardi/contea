
package com.alpenite.tea.communicationLayer.ws.crm;

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
 *         &lt;element name="EGuid" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="EObjectId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
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
    "eGuid",
    "eObjectId",
    "etMess"
})
@XmlRootElement(name = "ZptActivityCreateResponse")
public class ZptActivityCreateResponse {

    @XmlElement(name = "EGuid", required = true)
    protected String eGuid;
    @XmlElement(name = "EObjectId", required = true)
    protected String eObjectId;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

    /**
     * Gets the value of the eGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEGuid() {
        return eGuid;
    }

    /**
     * Sets the value of the eGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEGuid(String value) {
        this.eGuid = value;
    }

    /**
     * Gets the value of the eObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEObjectId() {
        return eObjectId;
    }

    /**
     * Sets the value of the eObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEObjectId(String value) {
        this.eObjectId = value;
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
