
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZptStRecapito complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZptStRecapito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Region" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="City1" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="City2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Cap" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="Housenum" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Housenum2" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStRecapito", propOrder = {
    "country",
    "region",
    "city1",
    "city2",
    "cap",
    "street",
    "housenum",
    "housenum2"
})
public class ZptStRecapito {

    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "Region", required = true)
    protected String region;
    @XmlElement(name = "City1", required = true)
    protected String city1;
    @XmlElement(name = "City2", required = true)
    protected String city2;
    @XmlElement(name = "Cap", required = true)
    protected String cap;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "Housenum", required = true)
    protected String housenum;
    @XmlElement(name = "Housenum2", required = true)
    protected String housenum2;

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the city1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity1() {
        return city1;
    }

    /**
     * Sets the value of the city1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity1(String value) {
        this.city1 = value;
    }

    /**
     * Gets the value of the city2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity2() {
        return city2;
    }

    /**
     * Sets the value of the city2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity2(String value) {
        this.city2 = value;
    }

    /**
     * Gets the value of the cap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Sets the value of the cap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the housenum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousenum() {
        return housenum;
    }

    /**
     * Sets the value of the housenum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousenum(String value) {
        this.housenum = value;
    }

    /**
     * Gets the value of the housenum2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousenum2() {
        return housenum2;
    }

    /**
     * Sets the value of the housenum2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousenum2(String value) {
        this.housenum2 = value;
    }

}
