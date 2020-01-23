
package com.alpenite.tea.communicationLayer.ws.isu;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IAcqua" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="IId" type="{urn:sap-com:document:sap:rfc:functions}char5" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="IVertrag" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="IZwstan" type="{urn:sap-com:document:sap:rfc:functions}char32" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "iAcqua",
    "iId",
    "iPartner",
    "iVertrag",
    "iZwstan"
})
@XmlRootElement(name = "ZptAutolettura")
public class ZptAutolettura {

    @XmlElement(name = "IAcqua")
    protected String iAcqua;
    @XmlElement(name = "IId")
    protected String iId;
    @XmlElement(name = "IPartner")
    protected String iPartner;
    @XmlElement(name = "IVertrag")
    protected String iVertrag;
    @XmlElement(name = "IZwstan")
    protected String iZwstan;

    /**
     * Recupera il valore della proprietà iAcqua.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIAcqua() {
        return iAcqua;
    }

    /**
     * Imposta il valore della proprietà iAcqua.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIAcqua(String value) {
        this.iAcqua = value;
    }

    /**
     * Recupera il valore della proprietà iId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIId() {
        return iId;
    }

    /**
     * Imposta il valore della proprietà iId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIId(String value) {
        this.iId = value;
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
     * Recupera il valore della proprietà iVertrag.
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
     * Imposta il valore della proprietà iVertrag.
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
     * Recupera il valore della proprietà iZwstan.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIZwstan() {
        return iZwstan;
    }

    /**
     * Imposta il valore della proprietà iZwstan.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIZwstan(String value) {
        this.iZwstan = value;
    }

}
