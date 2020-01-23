
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStComuni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStComuni"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="City1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Cap" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Codcat" type="{urn:sap-com:document:sap:rfc:functions}char5"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStComuni", propOrder = {
    "city1",
    "cap",
    "codcat"
})
public class ZptStComuni {

    @XmlElement(name = "City1", required = true)
    protected String city1;
    @XmlElement(name = "Cap", required = true)
    protected String cap;
    @XmlElement(name = "Codcat", required = true)
    protected String codcat;

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
     * Recupera il valore della proprietà codcat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodcat() {
        return codcat;
    }

    /**
     * Imposta il valore della proprietà codcat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodcat(String value) {
        this.codcat = value;
    }

}
