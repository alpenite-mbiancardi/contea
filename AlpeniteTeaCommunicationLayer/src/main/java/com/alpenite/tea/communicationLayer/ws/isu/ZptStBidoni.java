
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStBidoni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStBidoni"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Numserie" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="DescBid" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStBidoni", propOrder = {
    "numserie",
    "descBid"
})
public class ZptStBidoni {

    @XmlElement(name = "Numserie", required = true)
    protected String numserie;
    @XmlElement(name = "DescBid", required = true)
    protected String descBid;

    /**
     * Recupera il valore della proprietà numserie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumserie() {
        return numserie;
    }

    /**
     * Imposta il valore della proprietà numserie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumserie(String value) {
        this.numserie = value;
    }

    /**
     * Recupera il valore della proprietà descBid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescBid() {
        return descBid;
    }

    /**
     * Imposta il valore della proprietà descBid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescBid(String value) {
        this.descBid = value;
    }

}
