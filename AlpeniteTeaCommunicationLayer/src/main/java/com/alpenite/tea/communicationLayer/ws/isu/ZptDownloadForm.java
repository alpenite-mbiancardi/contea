
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
 *         &lt;element name="IBp" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="ICc" type="{urn:sap-com:document:sap:rfc:functions}char12"/&gt;
 *         &lt;element name="IsRevoke" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
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
    "iBp",
    "iCc",
    "isRevoke"
})
@XmlRootElement(name = "ZptDownloadForm")
public class ZptDownloadForm {

    @XmlElement(name = "IBp", required = true)
    protected String iBp;
    @XmlElement(name = "ICc", required = true)
    protected String iCc;
    @XmlElement(name = "IsRevoke", required = true)
    protected String isRevoke;

    /**
     * Recupera il valore della proprietà iBp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIBp() {
        return iBp;
    }

    /**
     * Imposta il valore della proprietà iBp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIBp(String value) {
        this.iBp = value;
    }

    /**
     * Recupera il valore della proprietà iCc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICc() {
        return iCc;
    }

    /**
     * Imposta il valore della proprietà iCc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICc(String value) {
        this.iCc = value;
    }

    /**
     * Recupera il valore della proprietà isRevoke.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRevoke() {
        return isRevoke;
    }

    /**
     * Imposta il valore della proprietà isRevoke.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRevoke(String value) {
        this.isRevoke = value;
    }

}
