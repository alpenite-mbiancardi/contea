
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZptStBpData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZptStBpData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartnerNumber" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="BpType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Name" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Surname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Role" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Telephone" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Cellphone" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Region" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="City1" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="City2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Cap" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="Housenum" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Housenum2" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10"/>
 *         &lt;element name="Prvcy" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PrvcyMail" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PrvcyEmail" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PrvcyTel" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NameOrg1" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="NameOrg2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="NameOrg3" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="NameOrg4" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Cf" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Pi" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
    "role",
    "telephone",
    "cellphone",
    "country",
    "region",
    "city1",
    "city2",
    "cap",
    "street",
    "housenum",
    "housenum2",
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
    @XmlElement(name = "Role", required = true)
    protected String role;
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
    @XmlElement(name = "Housenum2", required = true)
    protected String housenum2;
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
     * Gets the value of the partnerNumber property.
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
     * Sets the value of the partnerNumber property.
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
     * Gets the value of the bpType property.
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
     * Sets the value of the bpType property.
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
     * Gets the value of the name property.
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
     * Sets the value of the name property.
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
     * Gets the value of the surname property.
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
     * Sets the value of the surname property.
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
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the telephone property.
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
     * Sets the value of the telephone property.
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
     * Gets the value of the cellphone property.
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
     * Sets the value of the cellphone property.
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

    /**
     * Gets the value of the prvcy property.
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
     * Sets the value of the prvcy property.
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
     * Gets the value of the prvcyMail property.
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
     * Sets the value of the prvcyMail property.
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
     * Gets the value of the prvcyEmail property.
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
     * Sets the value of the prvcyEmail property.
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
     * Gets the value of the prvcyTel property.
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
     * Sets the value of the prvcyTel property.
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
     * Gets the value of the nameOrg1 property.
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
     * Sets the value of the nameOrg1 property.
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
     * Gets the value of the nameOrg2 property.
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
     * Sets the value of the nameOrg2 property.
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
     * Gets the value of the nameOrg3 property.
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
     * Sets the value of the nameOrg3 property.
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
     * Gets the value of the nameOrg4 property.
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
     * Sets the value of the nameOrg4 property.
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
     * Gets the value of the cf property.
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
     * Sets the value of the cf property.
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
     * Gets the value of the pi property.
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
     * Sets the value of the pi property.
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
