
package com.alpenite.tea.communicationLayer.ws.isu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ZptStInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ZptStInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Datainizio" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Datacess" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Tpuso" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="CatUso" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="OpzTarif" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="QualTit" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Volum" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Super" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Coeffuso" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Numpers" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Portata" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Attivita" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Iva" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Riscaldam" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Raffred" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Acqsanitaria" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Untabitat" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Sconto" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Accise" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStInfo", propOrder = {
    "datainizio",
    "datacess",
    "tpuso",
    "catUso",
    "opzTarif",
    "qualTit",
    "volum",
    "_super",
    "coeffuso",
    "numpers",
    "portata",
    "attivita",
    "iva",
    "riscaldam",
    "raffred",
    "acqsanitaria",
    "untabitat",
    "sconto",
    "accise",
    "potenzacontr",
    "livtensione"
    
})
public class ZptStInfo {

    @XmlElement(name = "Datainizio", required = true)
    protected String datainizio;
    @XmlElement(name = "Datacess", required = true)
    protected String datacess;
    @XmlElement(name = "Tpuso", required = true)
    protected String tpuso;
    @XmlElement(name = "CatUso", required = true)
    protected String catUso;
    @XmlElement(name = "OpzTarif", required = true)
    protected String opzTarif;
    @XmlElement(name = "QualTit", required = true)
    protected String qualTit;
    @XmlElement(name = "Volum", required = true)
    protected String volum;
    @XmlElement(name = "Super", required = true)
    protected String _super;
    @XmlElement(name = "Coeffuso", required = true)
    protected String coeffuso;
    @XmlElement(name = "Numpers", required = true)
    protected String numpers;
    @XmlElement(name = "Portata", required = true)
    protected String portata;
    @XmlElement(name = "Attivita", required = true)
    protected String attivita;
    @XmlElement(name = "Iva", required = true)
    protected String iva;
    @XmlElement(name = "Riscaldam", required = true)
    protected String riscaldam;
    @XmlElement(name = "Raffred", required = true)
    protected String raffred;
    @XmlElement(name = "Acqsanitaria", required = true)
    protected String acqsanitaria;
    @XmlElement(name = "Untabitat", required = true)
    protected String untabitat;
    @XmlElement(name = "Sconto", required = true)
    protected String sconto;
    @XmlElement(name = "Accise", required = true)
    protected String accise;
    @XmlElement(name = "Potenzacontr", required = true)
    protected String potenzacontr;
    @XmlElement(name = "Livtensione", required = true)
    protected String livtensione;

    /**
     * Recupera il valore della proprietà datainizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatainizio() {
        return datainizio;
    }

    /**
     * Imposta il valore della proprietà datainizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatainizio(String value) {
        this.datainizio = value;
    }

    /**
     * Recupera il valore della proprietà datacess.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatacess() {
        return datacess;
    }

    /**
     * Imposta il valore della proprietà datacess.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatacess(String value) {
        this.datacess = value;
    }

    /**
     * Recupera il valore della proprietà tpuso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpuso() {
        return tpuso;
    }

    /**
     * Imposta il valore della proprietà tpuso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpuso(String value) {
        this.tpuso = value;
    }

    /**
     * Recupera il valore della proprietà catUso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatUso() {
        return catUso;
    }

    /**
     * Imposta il valore della proprietà catUso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatUso(String value) {
        this.catUso = value;
    }

    /**
     * Recupera il valore della proprietà opzTarif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpzTarif() {
        return opzTarif;
    }

    /**
     * Imposta il valore della proprietà opzTarif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpzTarif(String value) {
        this.opzTarif = value;
    }

    /**
     * Recupera il valore della proprietà qualTit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualTit() {
        return qualTit;
    }

    /**
     * Imposta il valore della proprietà qualTit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualTit(String value) {
        this.qualTit = value;
    }

    /**
     * Recupera il valore della proprietà volum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolum() {
        return volum;
    }

    /**
     * Imposta il valore della proprietà volum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolum(String value) {
        this.volum = value;
    }

    /**
     * Recupera il valore della proprietà super.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuper() {
        return _super;
    }

    /**
     * Imposta il valore della proprietà super.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuper(String value) {
        this._super = value;
    }

    /**
     * Recupera il valore della proprietà coeffuso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoeffuso() {
        return coeffuso;
    }

    /**
     * Imposta il valore della proprietà coeffuso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoeffuso(String value) {
        this.coeffuso = value;
    }

    /**
     * Recupera il valore della proprietà numpers.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumpers() {
        return numpers;
    }

    /**
     * Imposta il valore della proprietà numpers.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumpers(String value) {
        this.numpers = value;
    }

    /**
     * Recupera il valore della proprietà portata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortata() {
        return portata;
    }

    /**
     * Imposta il valore della proprietà portata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortata(String value) {
        this.portata = value;
    }

    /**
     * Recupera il valore della proprietà attivita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttivita() {
        return attivita;
    }

    /**
     * Imposta il valore della proprietà attivita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttivita(String value) {
        this.attivita = value;
    }

    /**
     * Recupera il valore della proprietà iva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIva() {
        return iva;
    }

    /**
     * Imposta il valore della proprietà iva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIva(String value) {
        this.iva = value;
    }

    /**
     * Recupera il valore della proprietà riscaldam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiscaldam() {
        return riscaldam;
    }

    /**
     * Imposta il valore della proprietà riscaldam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiscaldam(String value) {
        this.riscaldam = value;
    }

    /**
     * Recupera il valore della proprietà raffred.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaffred() {
        return raffred;
    }

    /**
     * Imposta il valore della proprietà raffred.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaffred(String value) {
        this.raffred = value;
    }

    /**
     * Recupera il valore della proprietà acqsanitaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcqsanitaria() {
        return acqsanitaria;
    }

    /**
     * Imposta il valore della proprietà acqsanitaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcqsanitaria(String value) {
        this.acqsanitaria = value;
    }

    /**
     * Recupera il valore della proprietà untabitat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUntabitat() {
        return untabitat;
    }

    /**
     * Imposta il valore della proprietà untabitat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUntabitat(String value) {
        this.untabitat = value;
    }

    /**
     * Recupera il valore della proprietà sconto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSconto() {
        return sconto;
    }

    /**
     * Imposta il valore della proprietà sconto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSconto(String value) {
        this.sconto = value;
    }

    /**
     * Recupera il valore della proprietà accise.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccise() {
        return accise;
    }

    /**
     * Imposta il valore della proprietà accise.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccise(String value) {
        this.accise = value;
    }
    
    /**
     * Recupera il valore della proprietà poteza contrattuale contratto energia elettrica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPotenzacontr() {
        return potenzacontr;
    }

    /**
     * Imposta il valore della proprietà potenza contrattuale contratto energia elettrica.
     * c'ho p
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPotenzacontr(String value) {
        this.potenzacontr = value;
    }
    
    /**
     * Recupera il valore della proprietà Livtensione contrattuale contratto energia elettrica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivtensione() {
        return livtensione;
    }

    /**
     * Imposta il valore della proprietà potenza contrattuale contratto energia elettrica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivtensione(String value) {
        this.livtensione = value;
    }



    
}
