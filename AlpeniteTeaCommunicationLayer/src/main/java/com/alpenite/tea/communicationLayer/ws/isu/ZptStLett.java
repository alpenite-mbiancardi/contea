
package com.alpenite.tea.communicationLayer.ws.isu;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ZptStLett complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStLett"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Contatore" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="Numserie" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="Registro" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Data" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Ora" type="{urn:sap-com:document:sap:rfc:functions}time"/&gt;
 *         &lt;element name="Valore" type="{urn:sap-com:document:sap:rfc:functions}decimal17.0"/&gt;
 *         &lt;element name="Tipologia" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStLett", propOrder = {
    "contatore",
    "numserie",
    "registro",
    "data",
    "ora",
    "valore",
    "tipologia"
})
public class ZptStLett {

    @XmlElement(name = "Contatore", required = true)
    protected String contatore;
    @XmlElement(name = "Numserie", required = true)
    protected String numserie;
    @XmlElement(name = "Registro", required = true)
    protected String registro;
    @XmlElement(name = "Data", required = true)
    protected String data;
    @XmlElement(name = "Ora", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar ora;
    @XmlElement(name = "Valore", required = true)
    protected BigDecimal valore;
    @XmlElement(name = "Tipologia", required = true)
    protected String tipologia;

    /**
     * Recupera il valore della proprietà contatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContatore() {
        return contatore;
    }

    /**
     * Imposta il valore della proprietà contatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContatore(String value) {
        this.contatore = value;
    }

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
     * Recupera il valore della proprietà registro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistro() {
        return registro;
    }

    /**
     * Imposta il valore della proprietà registro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistro(String value) {
        this.registro = value;
    }

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietà ora.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOra() {
        return ora;
    }

    /**
     * Imposta il valore della proprietà ora.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOra(XMLGregorianCalendar value) {
        this.ora = value;
    }

    /**
     * Recupera il valore della proprietà valore.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValore() {
        return valore;
    }

    /**
     * Imposta il valore della proprietà valore.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValore(BigDecimal value) {
        this.valore = value;
    }

    /**
     * Recupera il valore della proprietà tipologia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Imposta il valore della proprietà tipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologia(String value) {
        this.tipologia = value;
    }

}
