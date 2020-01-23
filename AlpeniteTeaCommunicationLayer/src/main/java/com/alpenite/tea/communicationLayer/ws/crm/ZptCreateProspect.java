
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
 *         &lt;element name="IMail" type="{urn:sap-com:document:sap:rfc:functions}char241" minOccurs="0"/>
 *         &lt;element name="IUsername" type="{urn:sap-com:document:sap:rfc:functions}char20" minOccurs="0"/>
 *         &lt;element name="ItBpData" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtBpData" minOccurs="0"/>
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
    "iMail",
    "iUsername",
    "itBpData"
})
@XmlRootElement(name = "ZptCreateProspect")
public class ZptCreateProspect {

    @XmlElement(name = "IMail")
    protected String iMail;
    @XmlElement(name = "IUsername")
    protected String iUsername;
    @XmlElement(name = "ItBpData")
    protected ZptTtBpData itBpData;

    /**
     * Gets the value of the iMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMail() {
        return iMail;
    }

    /**
     * Sets the value of the iMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMail(String value) {
        this.iMail = value;
    }

    /**
     * Gets the value of the iUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIUsername() {
        return iUsername;
    }

    /**
     * Sets the value of the iUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIUsername(String value) {
        this.iUsername = value;
    }

    /**
     * Gets the value of the itBpData property.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtBpData }
     *     
     */
    public ZptTtBpData getItBpData() {
        return itBpData;
    }

    /**
     * Sets the value of the itBpData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtBpData }
     *     
     */
    public void setItBpData(ZptTtBpData value) {
        this.itBpData = value;
    }

}
