
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStVktyp complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStVktyp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipoConto" type="{urn:sap-com:document:sap:soap:functions:mc-style}char2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStVktyp", propOrder = {
    "tipoConto"
})
public class ZptStVktyp {

    @XmlElement(name = "TipoConto", required = true)
    protected String tipoConto;

    /**
     * Recupera il valore della proprietà tipoConto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoConto() {
        return tipoConto;
    }

    /**
     * Imposta il valore della proprietà tipoConto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoConto(String value) {
        this.tipoConto = value;
    }

}
