
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStList complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Numfatt" type="{urn:sap-com:document:sap:rfc:functions}char16"/&gt;
 *         &lt;element name="Datafatt" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Datascad" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="TotalAmnt" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="OpenAmnt" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="TotalWaer" type="{urn:sap-com:document:sap:rfc:functions}char5"/&gt;
 *         &lt;element name="Pdf" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="PdfObj" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStList", propOrder = {
    "numfatt",
    "datafatt",
    "datascad",
    "totalAmnt",
    "openAmnt",
    "totalWaer",
    "pdf",
    "pdfObj"
})
public class ZptStList {

    @XmlElement(name = "Numfatt", required = true)
    protected String numfatt;
    @XmlElement(name = "Datafatt", required = true)
    protected String datafatt;
    @XmlElement(name = "Datascad", required = true)
    protected String datascad;
    @XmlElement(name = "TotalAmnt", required = true)
    protected String totalAmnt;
    @XmlElement(name = "OpenAmnt", required = true)
    protected String openAmnt;
    @XmlElement(name = "TotalWaer", required = true)
    protected String totalWaer;
    @XmlElement(name = "Pdf", required = true)
    protected String pdf;
    @XmlElement(name = "PdfObj", required = true)
    protected String pdfObj;

    /**
     * Recupera il valore della proprietà numfatt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumfatt() {
        return numfatt;
    }

    /**
     * Imposta il valore della proprietà numfatt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumfatt(String value) {
        this.numfatt = value;
    }

    /**
     * Recupera il valore della proprietà datafatt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatafatt() {
        return datafatt;
    }

    /**
     * Imposta il valore della proprietà datafatt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatafatt(String value) {
        this.datafatt = value;
    }

    /**
     * Recupera il valore della proprietà datascad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatascad() {
        return datascad;
    }

    /**
     * Imposta il valore della proprietà datascad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatascad(String value) {
        this.datascad = value;
    }

    /**
     * Recupera il valore della proprietà totalAmnt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAmnt() {
        return totalAmnt;
    }

    /**
     * Imposta il valore della proprietà totalAmnt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAmnt(String value) {
        this.totalAmnt = value;
    }

    /**
     * Recupera il valore della proprietà openAmnt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenAmnt() {
        return openAmnt;
    }

    /**
     * Imposta il valore della proprietà openAmnt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenAmnt(String value) {
        this.openAmnt = value;
    }

    /**
     * Recupera il valore della proprietà totalWaer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalWaer() {
        return totalWaer;
    }

    /**
     * Imposta il valore della proprietà totalWaer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalWaer(String value) {
        this.totalWaer = value;
    }

    /**
     * Recupera il valore della proprietà pdf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdf() {
        return pdf;
    }

    /**
     * Imposta il valore della proprietà pdf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdf(String value) {
        this.pdf = value;
    }

    /**
     * Recupera il valore della proprietà pdfObj.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfObj() {
        return pdfObj;
    }

    /**
     * Imposta il valore della proprietà pdfObj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfObj(String value) {
        this.pdfObj = value;
    }

}
