
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
 *         &lt;element name="IPartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="ItCompany" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZptTtCompanies" minOccurs="0"/&gt;
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
    "iPartner",
    "itCompany"
})
@XmlRootElement(name = "ZptContractList")
public class ZptContractList {

    @XmlElement(name = "IPartner", required = true)
    protected String iPartner;
    @XmlElement(name = "ItCompany")
    protected ZptTtCompanies itCompany;

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
     * Recupera il valore della proprietà itCompany.
     * 
     * @return
     *     possible object is
     *     {@link ZptTtCompanies }
     *     
     */
    public ZptTtCompanies getItCompany() {
        return itCompany;
    }

    /**
     * Imposta il valore della proprietà itCompany.
     * 
     * @param value
     *     allowed object is
     *     {@link ZptTtCompanies }
     *     
     */
    public void setItCompany(ZptTtCompanies value) {
        this.itCompany = value;
    }

}
