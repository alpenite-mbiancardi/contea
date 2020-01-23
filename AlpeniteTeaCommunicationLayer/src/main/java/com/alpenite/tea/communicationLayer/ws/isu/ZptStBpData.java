
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStBpData complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStBpData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PartnerNumber" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="BpType" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Name" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Surname" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Telephone" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Cellphone" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="Region" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="City1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="City2" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Cap" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/&gt;
 *         &lt;element name="Housenum" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Prvcy" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="PrvcyMail" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="PrvcyEmail" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="PrvcyTel" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="NameOrg1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="NameOrg2" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="NameOrg3" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="NameOrg4" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Cf" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *         &lt;element name="Pi" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStBpData", propOrder = {
    "partnerNumber",
    "bpType",
    "name",
    "surname",
    "telephone",
    "cellphone",
    "country",
    "region",
    "city1",
    "city2",
    "cap",
    "street",
    "housenum",
    "prvcy",
    "prvcyMail",
    "prvcyEmail",
    "prvcyTel",
    "nameOrg1",
    "nameOrg2",
    "nameOrg3",
    "nameOrg4",
    "cf",
    "pi"
})
public class ZptStBpData {

    @XmlElement(name = "PartnerNumber", required = true)
    protected String partnerNumber;
    @XmlElement(name = "BpType", required = true)
    protected String bpType;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Surname", required = true)
    protected String surname;
    @XmlElement(name = "Telephone", required = true)
    protected String telephone;
    @XmlElement(name = "Cellphone", required = true)
    protected String cellphone;
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
    @XmlElement(name = "Prvcy", required = true)
    protected String prvcy;
    @XmlElement(name = "PrvcyMail", required = true)
    protected String prvcyMail;
    @XmlElement(name = "PrvcyEmail", required = true)
    protected String prvcyEmail;
    @XmlElement(name = "PrvcyTel", required = true)
    protected String prvcyTel;
    @XmlElement(name = "NameOrg1", required = true)
    protected String nameOrg1;
    @XmlElement(name = "NameOrg2", required = true)
    protected String nameOrg2;
    @XmlElement(name = "NameOrg3", required = true)
    protected String nameOrg3;
    @XmlElement(name = "NameOrg4", required = true)
    protected String nameOrg4;
    @XmlElement(name = "Cf", required = true)
    protected String cf;
    @XmlElement(name = "Pi", required = true)
    protected String pi;

    /**
     * Recupera il valore della proprietà partnerNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerNumber() {
        return partnerNumber;
    }

    /**
     * Imposta il valore della proprietà partnerNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerNumber(String value) {
        this.partnerNumber = value;
    }

    /**
     * Recupera il valore della proprietà bpType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBpType() {
        return bpType;
    }

    /**
     * Imposta il valore della proprietà bpType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBpType(String value) {
        this.bpType = value;
    }

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà surname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Imposta il valore della proprietà surname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Recupera il valore della proprietà telephone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Imposta il valore della proprietà telephone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * Recupera il valore della proprietà cellphone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * Imposta il valore della proprietà cellphone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellphone(String value) {
        this.cellphone = value;
    }

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
     * Recupera il valore della proprietà prvcy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrvcy() {
        return prvcy;
    }

    /**
     * Imposta il valore della proprietà prvcy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrvcy(String value) {
        this.prvcy = value;
    }

    /**
     * Recupera il valore della proprietà prvcyMail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrvcyMail() {
        return prvcyMail;
    }

    /**
     * Imposta il valore della proprietà prvcyMail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrvcyMail(String value) {
        this.prvcyMail = value;
    }

    /**
     * Recupera il valore della proprietà prvcyEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrvcyEmail() {
        return prvcyEmail;
    }

    /**
     * Imposta il valore della proprietà prvcyEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrvcyEmail(String value) {
        this.prvcyEmail = value;
    }

    /**
     * Recupera il valore della proprietà prvcyTel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrvcyTel() {
        return prvcyTel;
    }

    /**
     * Imposta il valore della proprietà prvcyTel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrvcyTel(String value) {
        this.prvcyTel = value;
    }

    /**
     * Recupera il valore della proprietà nameOrg1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOrg1() {
        return nameOrg1;
    }

    /**
     * Imposta il valore della proprietà nameOrg1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOrg1(String value) {
        this.nameOrg1 = value;
    }

    /**
     * Recupera il valore della proprietà nameOrg2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOrg2() {
        return nameOrg2;
    }

    /**
     * Imposta il valore della proprietà nameOrg2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOrg2(String value) {
        this.nameOrg2 = value;
    }

    /**
     * Recupera il valore della proprietà nameOrg3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOrg3() {
        return nameOrg3;
    }

    /**
     * Imposta il valore della proprietà nameOrg3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOrg3(String value) {
        this.nameOrg3 = value;
    }

    /**
     * Recupera il valore della proprietà nameOrg4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOrg4() {
        return nameOrg4;
    }

    /**
     * Imposta il valore della proprietà nameOrg4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOrg4(String value) {
        this.nameOrg4 = value;
    }

    /**
     * Recupera il valore della proprietà cf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCf() {
        return cf;
    }

    /**
     * Imposta il valore della proprietà cf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCf(String value) {
        this.cf = value;
    }

    /**
     * Recupera il valore della proprietà pi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPi() {
        return pi;
    }

    /**
     * Imposta il valore della proprietà pi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPi(String value) {
        this.pi = value;
    }

}
