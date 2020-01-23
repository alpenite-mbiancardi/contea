
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptGetBpFromCc.RfcException complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptGetBpFromCc.RfcException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptGetBpFromCc.RfcExceptions"/&gt;
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Message" type="{urn:sap-com:document:sap:soap:functions:mc-style}RfcException.Message" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptGetBpFromCc.RfcException", propOrder = {
    "name",
    "text",
    "message"
})
public class ZptGetBpFromCcRfcException {

    @XmlElement(name = "Name", required = true)
    @XmlSchemaType(name = "string")
    protected ZptGetBpFromCcRfcExceptions name;
    @XmlElement(name = "Text")
    protected String text;
    @XmlElement(name = "Message")
    protected RfcExceptionMessage message;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link ZptGetBpFromCcRfcExceptions }
     *     
     */
    public ZptGetBpFromCcRfcExceptions getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptGetBpFromCcRfcExceptions }
     *     
     */
    public void setName(ZptGetBpFromCcRfcExceptions value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà text.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Imposta il valore della proprietà text.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Recupera il valore della proprietà message.
     * 
     * @return
     *     possible object is
     *     {@link RfcExceptionMessage }
     *     
     */
    public RfcExceptionMessage getMessage() {
        return message;
    }

    /**
     * Imposta il valore della proprietà message.
     * 
     * @param value
     *     allowed object is
     *     {@link RfcExceptionMessage }
     *     
     */
    public void setMessage(RfcExceptionMessage value) {
        this.message = value;
    }

}
