
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStDc complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStDc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CityAmm" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="CityCat" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Codcat" type="{urn:sap-com:document:sap:rfc:functions}char5"/&gt;
 *         &lt;element name="Sezurb" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Part" type="{urn:sap-com:document:sap:rfc:functions}char5"/&gt;
 *         &lt;element name="Parttav" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Tppart" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Subalt" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Foglio" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    "foglio"
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
    @XmlElement(name = "Foglio", required = true)
    protected String foglio;

    /**
     * Recupera il valore della proprietà cityAmm.
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
     * Imposta il valore della proprietà cityAmm.
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
     * Recupera il valore della proprietà cityCat.
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
     * Imposta il valore della proprietà cityCat.
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
     * Recupera il valore della proprietà codcat.
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
     * Imposta il valore della proprietà codcat.
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
     * Recupera il valore della proprietà sezurb.
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
     * Imposta il valore della proprietà sezurb.
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
     * Recupera il valore della proprietà part.
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
     * Imposta il valore della proprietà part.
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
     * Recupera il valore della proprietà parttav.
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
     * Imposta il valore della proprietà parttav.
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
     * Recupera il valore della proprietà tppart.
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
     * Imposta il valore della proprietà tppart.
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
     * Recupera il valore della proprietà subalt.
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
     * Imposta il valore della proprietà subalt.
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
     * Recupera il valore della proprietà foglio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoglio() {
        return foglio;
    }

    /**
     * Imposta il valore della proprietà foglio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoglio(String value) {
        this.foglio = value;
    }

}
