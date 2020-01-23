
package com.alpenite.tea.wdslfascicolo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStFascicolo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStFascicolo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SezioneIt" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="SezioneEn" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="DefinizIt" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="DefinizEn" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Estensione" type="{urn:sap-com:document:sap:rfc:functions}char27"/&gt;
 *         &lt;element name="Data" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="SapObject" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="ArcDocId" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Tech1" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Tech2" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Tech3" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStFascicolo", propOrder = {
    "sezioneIt",
    "sezioneEn",
    "definizIt",
    "definizEn",
    "estensione",
    "data",
    "sapObject",
    "arcDocId",
    "tech1",
    "tech2",
    "tech3"
})
public class ZptStFascicolo {

    @XmlElement(name = "SezioneIt", required = true)
	public String sezioneIt;
    @XmlElement(name = "SezioneEn", required = true)
    public String sezioneEn;
    @XmlElement(name = "DefinizIt", required = true)
    public String definizIt;
    @XmlElement(name = "DefinizEn", required = true)
    public String definizEn;
    @XmlElement(name = "Estensione", required = true)
    public String estensione;
    @XmlElement(name = "Data", required = true)
    public String data;
    @XmlElement(name = "SapObject", required = true)
    public String sapObject;
    @XmlElement(name = "ArcDocId", required = true)
    public String arcDocId;
    @XmlElement(name = "Tech1", required = true)
    public String tech1;
    @XmlElement(name = "Tech2", required = true)
    public String tech2;
    @XmlElement(name = "Tech3", required = true)
    public String tech3;

    /**
     * Recupera il valore della proprietà sezioneIt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSezioneIt() {
        return sezioneIt;
    }

    /**
     * Imposta il valore della proprietà sezioneIt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSezioneIt(String value) {
        this.sezioneIt = value;
    }

    /**
     * Recupera il valore della proprietà sezioneEn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSezioneEn() {
        return sezioneEn;
    }

    /**
     * Imposta il valore della proprietà sezioneEn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSezioneEn(String value) {
        this.sezioneEn = value;
    }

    /**
     * Recupera il valore della proprietà definizIt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefinizIt() {
        return definizIt;
    }

    /**
     * Imposta il valore della proprietà definizIt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefinizIt(String value) {
        this.definizIt = value;
    }

    /**
     * Recupera il valore della proprietà definizEn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefinizEn() {
        return definizEn;
    }

    /**
     * Imposta il valore della proprietà definizEn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefinizEn(String value) {
        this.definizEn = value;
    }

    /**
     * Recupera il valore della proprietà estensione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstensione() {
        return estensione;
    }

    /**
     * Imposta il valore della proprietà estensione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstensione(String value) {
        this.estensione = value;
    }

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietà sapObject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapObject() {
        return sapObject;
    }

    /**
     * Imposta il valore della proprietà sapObject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapObject(String value) {
        this.sapObject = value;
    }

    /**
     * Recupera il valore della proprietà arcDocId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArcDocId() {
        return arcDocId;
    }

    /**
     * Imposta il valore della proprietà arcDocId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArcDocId(String value) {
        this.arcDocId = value;
    }

    /**
     * Recupera il valore della proprietà tech1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTech1() {
        return tech1;
    }

    /**
     * Imposta il valore della proprietà tech1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTech1(String value) {
        this.tech1 = value;
    }

    /**
     * Recupera il valore della proprietà tech2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTech2() {
        return tech2;
    }

    /**
     * Imposta il valore della proprietà tech2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTech2(String value) {
        this.tech2 = value;
    }

    /**
     * Recupera il valore della proprietà tech3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTech3() {
        return tech3;
    }

    /**
     * Imposta il valore della proprietà tech3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTech3(String value) {
        this.tech3 = value;
    }

}
