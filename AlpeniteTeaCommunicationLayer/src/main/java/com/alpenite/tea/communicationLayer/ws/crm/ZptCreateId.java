
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IElet" type="{urn:sap-com:document:sap:soap:functions:mc-style}char60" minOccurs="0"/>
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="IPec" type="{urn:sap-com:document:sap:rfc:functions}char241" minOccurs="0"/>
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
    "iElet",
    "iPartner",
    "iPec"
})
@XmlRootElement(name = "ZptCreateId")
public class ZptCreateId {

    @XmlElement(name = "IElet")
    protected String iElet;
    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;
    @XmlElement(name = "IPec")
    protected String iPec;

    /**
     * Recupera il valore della proprietà iElet.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIElet() {
        return iElet;
    }

    /**
     * Imposta il valore della proprietà iElet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIElet(String value) {
        this.iElet = value;
    }

    /**
     * Recupera il valore della proprietà iPartner.
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
     * Imposta il valore della proprietà iPartner.
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
     * Recupera il valore della proprietà iPec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPec() {
        return iPec;
    }

    /**
     * Imposta il valore della proprietà iPec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPec(String value) {
        this.iPec = value;
    }

}
