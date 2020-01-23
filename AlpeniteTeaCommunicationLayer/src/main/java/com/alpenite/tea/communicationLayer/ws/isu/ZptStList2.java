
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZptStList2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZptStList2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Societa" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Datafatt" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="Datascad" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="TotalAmnt" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="OpenAmnt" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="TotalWaer" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStList2", propOrder = {
    "societa",
    "datafatt",
    "datascad",
    "totalAmnt",
    "openAmnt",
    "totalWaer"
})
public class ZptStList2 {

    @XmlElement(name = "Societa", required = true)
    protected String societa;
    @XmlElement(name = "Datafatt", required = true)
    protected String datafatt;
    @XmlElement(name = "Datascad", required = true)
    protected String datascad;
    @XmlElement(name = "TotalAmnt", required = true)
    protected String totalAmnt;
    @XmlElement(name = "OpenAmnt", required = true)
    protected String openAmnt;
    @XmlElement(name = "TotalWaer", required = true)
    protected String totalWaer;

    /**
     * Gets the value of the societa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocieta() {
        return societa;
    }

    /**
     * Sets the value of the societa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocieta(String value) {
        this.societa = value;
    }

    /**
     * Gets the value of the datafatt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatafatt() {
        return datafatt;
    }

    /**
     * Sets the value of the datafatt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatafatt(String value) {
        this.datafatt = value;
    }

    /**
     * Gets the value of the datascad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatascad() {
        return datascad;
    }

    /**
     * Sets the value of the datascad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatascad(String value) {
        this.datascad = value;
    }

    /**
     * Gets the value of the totalAmnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAmnt() {
        return totalAmnt;
    }

    /**
     * Sets the value of the totalAmnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAmnt(String value) {
        this.totalAmnt = value;
    }

    /**
     * Gets the value of the openAmnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenAmnt() {
        return openAmnt;
    }

    /**
     * Sets the value of the openAmnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenAmnt(String value) {
        this.openAmnt = value;
    }

    /**
     * Gets the value of the totalWaer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalWaer() {
        return totalWaer;
    }

    /**
     * Sets the value of the totalWaer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalWaer(String value) {
        this.totalWaer = value;
    }

}
