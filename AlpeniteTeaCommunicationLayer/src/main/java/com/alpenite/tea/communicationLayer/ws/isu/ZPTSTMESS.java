
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZPT_ST_MESS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZPT_ST_MESS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="MSG_NUMBER" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="TYPE" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="MESSAGE" type="{urn:sap-com:document:sap:rfc:functions}char220"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZPT_ST_MESS", namespace = "urn:sap-com:document:sap:rfc:functions", propOrder = {
    "id",
    "msgnumber",
    "type",
    "message"
})
public class ZPTSTMESS {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "MSG_NUMBER", required = true)
    protected String msgnumber;
    @XmlElement(name = "TYPE", required = true)
    protected String type;
    @XmlElement(name = "MESSAGE", required = true)
    protected String message;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the msgnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSGNUMBER() {
        return msgnumber;
    }

    /**
     * Sets the value of the msgnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSGNUMBER(String value) {
        this.msgnumber = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMESSAGE() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMESSAGE(String value) {
        this.message = value;
    }

}
