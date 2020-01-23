
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
 *         &lt;element name="ICateg1" type="{urn:sap-com:document:sap:rfc:functions}char40" minOccurs="0"/>
 *         &lt;element name="ICateg2" type="{urn:sap-com:document:sap:rfc:functions}char40" minOccurs="0"/>
 *         &lt;element name="IInterloc" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="IJahiaType" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/>
 *         &lt;element name="INumciv" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="INumcivExtension" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10" minOccurs="0"/>
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="IText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ITypSr" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/>
 *         &lt;element name="IVertrag" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="IVkont" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/>
 *         &lt;element name="IsBp" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStBpData" minOccurs="0"/>
 *         &lt;element name="IsCond" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStCond" minOccurs="0"/>
 *         &lt;element name="IsDc" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStDc" minOccurs="0"/>
 *         &lt;element name="IsRec" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptStRecapito" minOccurs="0"/>
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
    "iCateg1",
    "iCateg2",
    "iInterloc",
    "iJahiaType",
    "iNumciv",
    "iNumcivExtension",
    "iPartner",
    "iText",
    "iTypSr",
    "iVertrag",
    "iVkont",
    "isBp",
    "isCond",
    "isDc",
    "isRec"
})
@XmlRootElement(name = "ZptServiceRequestCreate")
public class ZptServiceRequestCreate {

    @XmlElement(name = "ICateg1")
    protected String iCateg1;
    @XmlElement(name = "ICateg2")
    protected String iCateg2;
    @XmlElement(name = "IInterloc")
    protected String iInterloc;
    @XmlElement(name = "IJahiaType")
    protected String iJahiaType;
    @XmlElement(name = "INumciv")
    protected String iNumciv;
    @XmlElement(name = "INumcivExtension")
    protected String iNumcivExtension;
    @XmlElement(name = "IPartner")
    protected String iPartner;
    @XmlElement(name = "IText")
    protected String iText;
    @XmlElement(name = "ITypSr")
    protected String iTypSr;
    @XmlElement(name = "IVertrag")
    protected String iVertrag;
    @XmlElement(name = "IVkont")
    protected String iVkont;
    @XmlElement(name = "IsBp")
    protected ZptStBpData isBp;
    @XmlElement(name = "IsCond")
    protected ZptStCond isCond;
    @XmlElement(name = "IsDc")
    protected ZptStDc isDc;
    @XmlElement(name = "IsRec")
    protected ZptStRecapito isRec;

    /**
     * Gets the value of the iCateg1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICateg1() {
        return iCateg1;
    }

    /**
     * Sets the value of the iCateg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICateg1(String value) {
        this.iCateg1 = value;
    }

    /**
     * Gets the value of the iCateg2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICateg2() {
        return iCateg2;
    }

    /**
     * Sets the value of the iCateg2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICateg2(String value) {
        this.iCateg2 = value;
    }

    /**
     * Gets the value of the iInterloc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIInterloc() {
        return iInterloc;
    }

    /**
     * Sets the value of the iInterloc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIInterloc(String value) {
        this.iInterloc = value;
    }

    /**
     * Gets the value of the iJahiaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIJahiaType() {
        return iJahiaType;
    }

    /**
     * Sets the value of the iJahiaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIJahiaType(String value) {
        this.iJahiaType = value;
    }

    /**
     * Gets the value of the iNumciv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINumciv() {
        return iNumciv;
    }

    /**
     * Sets the value of the iNumciv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINumciv(String value) {
        this.iNumciv = value;
    }

    /**
     * Gets the value of the iNumcivExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINumcivExtension() {
        return iNumcivExtension;
    }

    /**
     * Sets the value of the iNumcivExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINumcivExtension(String value) {
        this.iNumcivExtension = value;
    }

    /**
     * Gets the value of the iPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPartner() {
        return iPartner;
    }

    /**
     * Sets the value of the iPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPartner(String value) {
        this.iPartner = value;
    }

    /**
     * Gets the value of the iText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIText() {
        return iText;
    }

    /**
     * Sets the value of the iText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIText(String value) {
        this.iText = value;
    }

    /**
     * Gets the value of the iTypSr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITypSr() {
        return iTypSr;
    }

    /**
     * Sets the value of the iTypSr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITypSr(String value) {
        this.iTypSr = value;
    }

    /**
     * Gets the value of the iVertrag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIVertrag() {
        return iVertrag;
    }

    /**
     * Sets the value of the iVertrag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIVertrag(String value) {
        this.iVertrag = value;
    }

    /**
     * Gets the value of the iVkont property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIVkont() {
        return iVkont;
    }

    /**
     * Sets the value of the iVkont property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIVkont(String value) {
        this.iVkont = value;
    }

    /**
     * Gets the value of the isBp property.
     * 
     * @return
     *     possible object is
     *     {@link ZptStBpData }
     *     
     */
    public ZptStBpData getIsBp() {
        return isBp;
    }

    /**
     * Sets the value of the isBp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStBpData }
     *     
     */
    public void setIsBp(ZptStBpData value) {
        this.isBp = value;
    }

    /**
     * Gets the value of the isCond property.
     * 
     * @return
     *     possible object is
     *     {@link ZptStCond }
     *     
     */
    public ZptStCond getIsCond() {
        return isCond;
    }

    /**
     * Sets the value of the isCond property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStCond }
     *     
     */
    public void setIsCond(ZptStCond value) {
        this.isCond = value;
    }

    /**
     * Gets the value of the isDc property.
     * 
     * @return
     *     possible object is
     *     {@link ZptStDc }
     *     
     */
    public ZptStDc getIsDc() {
        return isDc;
    }

    /**
     * Sets the value of the isDc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStDc }
     *     
     */
    public void setIsDc(ZptStDc value) {
        this.isDc = value;
    }

    /**
     * Gets the value of the isRec property.
     * 
     * @return
     *     possible object is
     *     {@link ZptStRecapito }
     *     
     */
    public ZptStRecapito getIsRec() {
        return isRec;
    }

    /**
     * Sets the value of the isRec property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptStRecapito }
     *     
     */
    public void setIsRec(ZptStRecapito value) {
        this.isRec = value;
    }

}
