
package com.roytuts.soap.client;

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
 *         &lt;element name="EtPaymentData" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZconteaPaymentOutGetTab"/>
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
    "etPaymentData"
})
@XmlRootElement(name = "ZzconteaGetOnlinepaymentResponse")
public class ZzconteaGetOnlinepaymentResponse {

    @XmlElement(name = "EtPaymentData", required = true)
    protected ZconteaPaymentOutGetTab etPaymentData;

    /**
     * Gets the value of the etPaymentData property.
     * 
     * @return
     *     possible object is
     *     {@link ZconteaPaymentOutGetTab }
     *     
     */
    public ZconteaPaymentOutGetTab getEtPaymentData() {
        return etPaymentData;
    }

    /**
     * Sets the value of the etPaymentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZconteaPaymentOutGetTab }
     *     
     */
    public void setEtPaymentData(ZconteaPaymentOutGetTab value) {
        this.etPaymentData = value;
    }

}
