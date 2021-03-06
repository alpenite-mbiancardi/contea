
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
 *         &lt;element name="EName" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="EPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="EReturn" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="ESurname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
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
    "eName",
    "ePartner",
    "eReturn",
    "eSurname",
    "etMess"
})
@XmlRootElement(name = "ZptCreateUsernameResponse")
public class ZptCreateUsernameResponse {

    @XmlElement(name = "EName", required = true)
    protected String eName;
    @XmlElement(name = "EPartner", required = true)
    protected String ePartner;
    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "ESurname", required = true)
    protected String eSurname;
    @XmlElement(name = "EtMess", required = true)
    protected ZptTtMess etMess;

    /**
     * Gets the value of the eName property.
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
     * Sets the value of the eName property.
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
     * Gets the value of the ePartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEPartner() {
        return ePartner;
    }

    /**
     * Sets the value of the ePartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEPartner(String value) {
        this.ePartner = value;
    }

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
     * Gets the value of the eSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESurname() {
        return eSurname;
    }

    /**
     * Sets the value of the eSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESurname(String value) {
        this.eSurname = value;
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
