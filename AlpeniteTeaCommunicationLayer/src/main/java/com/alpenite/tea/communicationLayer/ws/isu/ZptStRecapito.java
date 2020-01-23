
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStRecapito complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStRecapito"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="Region" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="City1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="City2" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Cap" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/&gt;
 *         &lt;element name="Housenum" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="ExtHousenum" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    "extHousenum"
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
    @XmlElement(name = "ExtHousenum", required = true)
    protected String extHousenum;

    /**
     * Recupera il valore della proprietà country.
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
     * Imposta il valore della proprietà country.
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
     * Recupera il valore della proprietà region.
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
     * Imposta il valore della proprietà region.
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
     * Recupera il valore della proprietà city1.
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
     * Imposta il valore della proprietà city1.
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
     * Recupera il valore della proprietà city2.
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
     * Imposta il valore della proprietà city2.
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
     * Recupera il valore della proprietà cap.
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
     * Imposta il valore della proprietà cap.
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
     * Recupera il valore della proprietà street.
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
     * Imposta il valore della proprietà street.
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
     * Recupera il valore della proprietà housenum.
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
     * Imposta il valore della proprietà housenum.
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
     * Recupera il valore della proprietà extHousenum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtHousenum() {
        return extHousenum;
    }

    /**
     * Imposta il valore della proprietà extHousenum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtHousenum(String value) {
        this.extHousenum = value;
    }

}
