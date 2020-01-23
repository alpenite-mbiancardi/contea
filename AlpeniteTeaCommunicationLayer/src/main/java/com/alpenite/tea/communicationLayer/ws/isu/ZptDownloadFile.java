
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
 *         &lt;element name="IArcDocId" type="{urn:sap-com:document:sap:rfc:functions}char40" minOccurs="0"/&gt;
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="ISapObject" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
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
    "iArcDocId",
    "iPartner",
    "iSapObject"
})
@XmlRootElement(name = "ZptDownloadFile")
public class ZptDownloadFile {

    @XmlElement(name = "IArcDocId")
    protected String iArcDocId;
    @XmlElement(name = "IPartner")
    protected String iPartner;
    @XmlElement(name = "ISapObject")
    protected String iSapObject;

    /**
     * Recupera il valore della proprietà iArcDocId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIArcDocId() {
        return iArcDocId;
    }

    /**
     * Imposta il valore della proprietà iArcDocId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIArcDocId(String value) {
        this.iArcDocId = value;
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
     * Recupera il valore della proprietà iSapObject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISapObject() {
        return iSapObject;
    }

    /**
     * Imposta il valore della proprietà iSapObject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISapObject(String value) {
        this.iSapObject = value;
    }

}
