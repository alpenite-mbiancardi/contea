
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStContract complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStContract"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Cc" type="{urn:sap-com:document:sap:rfc:functions}char12"/&gt;
 *         &lt;element name="Contractnum" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/&gt;
 *         &lt;element name="Service" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *         &lt;element name="Status" type="{urn:sap-com:document:sap:rfc:functions}string"/&gt;
 *         &lt;element name="ServiceCode" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStContract", propOrder = {
    "cc",
    "contractnum",
    "street",
    "service",
    "status",
    "serviceCode",
    "companyCode"
})
public class ZptStContract {

    @XmlElement(name = "Cc", required = true)
    protected String cc;
    @XmlElement(name = "Contractnum", required = true)
    protected String contractnum;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "Service", required = true)
    protected String service;
    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "ServiceCode", required = true)
    protected String serviceCode;
    // modifica 8-11-2016 AQA
    
    @XmlElement(name = "CompanyCode", required = true)
    protected String companyCode;
    
    

    /**
     * Recupera il valore della proprietà cc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCc() {
        return cc;
    }

    /**
     * Imposta il valore della proprietà cc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCc(String value) {
        this.cc = value;
    }

    /**
     * Recupera il valore della proprietà contractnum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractnum() {
        return contractnum;
    }

    /**
     * Imposta il valore della proprietà contractnum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractnum(String value) {
        this.contractnum = value;
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
     * Recupera il valore della proprietà service.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Imposta il valore della proprietà service.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * Recupera il valore della proprietà status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Imposta il valore della proprietà status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Recupera il valore della proprietà serviceCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Imposta il valore della proprietà serviceCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }
    
    //mod aqa 
    
    /**
     * Recupera il valore della proprietà companyCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     /*
      * 
      * @return
      */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * Imposta il valore della proprietà companyCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    
    public void setCompanyCode(String value) {
        this.companyCode = value;
    }
    
    
    

}
