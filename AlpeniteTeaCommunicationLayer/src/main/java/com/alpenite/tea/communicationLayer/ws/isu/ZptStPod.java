
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStPod complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStPod"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PodCode" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/&gt;
 *         &lt;element name="Housenum" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Cap" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="City1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Exthousenum" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStPod", propOrder = {
    "podCode",
    "street",
    "housenum",
    "cap",
    "city1",
    "exthousenum"
})
public class ZptStPod {

    @XmlElement(name = "PodCode", required = true)
    protected String podCode;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "Housenum", required = true)
    protected String housenum;
    @XmlElement(name = "Cap", required = true)
    protected String cap;
    @XmlElement(name = "City1", required = true)
    protected String city1;
    @XmlElement(name = "Exthousenum", required = true)
    protected String exthousenum;

    /**
     * Recupera il valore della proprietà podCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * Imposta il valore della proprietà podCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodCode(String value) {
        this.podCode = value;
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
     * Recupera il valore della proprietà exthousenum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExthousenum() {
        return exthousenum;
    }

    /**
     * Imposta il valore della proprietà exthousenum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExthousenum(String value) {
        this.exthousenum = value;
    }

}
