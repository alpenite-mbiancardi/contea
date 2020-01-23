
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
 *         &lt;element name="EReturn" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="ESrId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/>
 *         &lt;element name="ESrNum" type="{urn:sap-com:document:sap:soap:functions:mc-style}byte16"/>
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
    "eSrId",
    "eSrNum",
    "etMess"
})
@XmlRootElement(name = "ZptServiceRequestCreateResponse")
public class ZptServiceRequestCreateResponse {

    @XmlElement(name = "EReturn", required = true)
    protected String eReturn;
    @XmlElement(name = "ESrId", required = true)
    protected String eSrId;
    @XmlElement(name = "ESrNum", required = true)
    protected byte[] eSrNum;
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
     * Gets the value of the eSrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESrId() {
        return eSrId;
    }

    /**
     * Sets the value of the eSrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESrId(String value) {
        this.eSrId = value;
    }

    /**
     * Gets the value of the eSrNum property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getESrNum() {
        return eSrNum;
    }

    /**
     * Sets the value of the eSrNum property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setESrNum(byte[] value) {
        this.eSrNum = value;
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
