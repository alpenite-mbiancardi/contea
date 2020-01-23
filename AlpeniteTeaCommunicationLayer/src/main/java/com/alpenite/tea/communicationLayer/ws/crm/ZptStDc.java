
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZptStDc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZptStDc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CityAmm" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="CityCat" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Codcat" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="Sezurb" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Part" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="Parttav" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Tppart" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Subalt" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Zfoglio" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStDc", propOrder = {
    "cityAmm",
    "cityCat",
    "codcat",
    "sezurb",
    "part",
    "parttav",
    "tppart",
    "subalt",
    "zfoglio"
})
public class ZptStDc {

    @XmlElement(name = "CityAmm", required = true)
    protected String cityAmm;
    @XmlElement(name = "CityCat", required = true)
    protected String cityCat;
    @XmlElement(name = "Codcat", required = true)
    protected String codcat;
    @XmlElement(name = "Sezurb", required = true)
    protected String sezurb;
    @XmlElement(name = "Part", required = true)
    protected String part;
    @XmlElement(name = "Parttav", required = true)
    protected String parttav;
    @XmlElement(name = "Tppart", required = true)
    protected String tppart;
    @XmlElement(name = "Subalt", required = true)
    protected String subalt;
    @XmlElement(name = "Zfoglio", required = true)
    protected String zfoglio;

    /**
     * Gets the value of the cityAmm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityAmm() {
        return cityAmm;
    }

    /**
     * Sets the value of the cityAmm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityAmm(String value) {
        this.cityAmm = value;
    }

    /**
     * Gets the value of the cityCat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCat() {
        return cityCat;
    }

    /**
     * Sets the value of the cityCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCat(String value) {
        this.cityCat = value;
    }

    /**
     * Gets the value of the codcat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodcat() {
        return codcat;
    }

    /**
     * Sets the value of the codcat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodcat(String value) {
        this.codcat = value;
    }

    /**
     * Gets the value of the sezurb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSezurb() {
        return sezurb;
    }

    /**
     * Sets the value of the sezurb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSezurb(String value) {
        this.sezurb = value;
    }

    /**
     * Gets the value of the part property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPart() {
        return part;
    }

    /**
     * Sets the value of the part property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPart(String value) {
        this.part = value;
    }

    /**
     * Gets the value of the parttav property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParttav() {
        return parttav;
    }

    /**
     * Sets the value of the parttav property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParttav(String value) {
        this.parttav = value;
    }

    /**
     * Gets the value of the tppart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTppart() {
        return tppart;
    }

    /**
     * Sets the value of the tppart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTppart(String value) {
        this.tppart = value;
    }

    /**
     * Gets the value of the subalt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubalt() {
        return subalt;
    }

    /**
     * Sets the value of the subalt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubalt(String value) {
        this.subalt = value;
    }

    /**
     * Gets the value of the zfoglio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZfoglio() {
        return zfoglio;
    }

    /**
     * Sets the value of the zfoglio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZfoglio(String value) {
        this.zfoglio = value;
    }

}
