
package com.alpenite.tea.communicationLayer.ws.isu;

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
 *         &lt;element name="IVertrag" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="IZzcontatto" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1" minOccurs="0"/>
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
    "iVertrag",
    "iZzcontatto"
})
@XmlRootElement(name = "ZptDettagliContatto")
public class ZptDettagliContatto {

    @XmlElement(name = "IVertrag", required = true)
    protected String iVertrag;
    @XmlElement(name = "IZzcontatto")
    protected String iZzcontatto;

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
     * Gets the value of the iZzcontatto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIZzcontatto() {
        return iZzcontatto;
    }

    /**
     * Sets the value of the iZzcontatto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIZzcontatto(String value) {
        this.iZzcontatto = value;
    }

}
