
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
 *         &lt;element name="EtBin" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZbinariesT"/>
 *         &lt;element name="IBp" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ICc" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="IsRevoke" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/>
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
    "etBin",
    "iBp",
    "iCc",
    "isRevoke"
})
@XmlRootElement(name = "ZptUploadDocs")
public class ZptUploadDocs {

    @XmlElement(name = "EtBin", required = true)
    protected ZbinariesT etBin;
    @XmlElement(name = "IBp", required = true)
    protected String iBp;
    @XmlElement(name = "ICc", required = true)
    protected String iCc;
    @XmlElement(name = "IsRevoke", required = true)
    protected String isRevoke;

    /**
     * Gets the value of the etBin property.
     * 
     * @return
     *     possible object is
     *     {@link ZbinariesT }
     *     
     */
    public ZbinariesT getEtBin() {
        return etBin;
    }

    /**
     * Sets the value of the etBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZbinariesT }
     *     
     */
    public void setEtBin(ZbinariesT value) {
        this.etBin = value;
    }

    /**
     * Gets the value of the iBp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIBp() {
        return iBp;
    }

    /**
     * Sets the value of the iBp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIBp(String value) {
        this.iBp = value;
    }

    /**
     * Gets the value of the iCc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICc() {
        return iCc;
    }

    /**
     * Sets the value of the iCc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICc(String value) {
        this.iCc = value;
    }

    /**
     * Gets the value of the isRevoke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRevoke() {
        return isRevoke;
    }

    /**
     * Sets the value of the isRevoke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRevoke(String value) {
        this.isRevoke = value;
    }

}
