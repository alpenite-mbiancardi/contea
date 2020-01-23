
package com.alpenite.tea.wdslfascicolo;

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
 *         &lt;element name="TFascicolo" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTyFascicolo"/&gt;
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
    "tFascicolo"
})
@XmlRootElement(name = "ZptGetFascicoloResponse")
public class ZptGetFascicoloResponse {

    @XmlElement(name = "TFascicolo", required = true)
    protected ZptTyFascicolo tFascicolo;

    /**
     * Recupera il valore della proprietà tFascicolo.
     * 
     * @return
     *     possible object is
     *     {@link ZptTyFascicolo }
     *     
     */
    public ZptTyFascicolo getTFascicolo() {
        return tFascicolo;
    }

    /**
     * Imposta il valore della proprietà tFascicolo.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTyFascicolo }
     *     
     */
    public void setTFascicolo(ZptTyFascicolo value) {
        this.tFascicolo = value;
    }

}
