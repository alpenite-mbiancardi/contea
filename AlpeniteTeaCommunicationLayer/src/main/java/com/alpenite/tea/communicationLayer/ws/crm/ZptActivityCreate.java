
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Category" type="{urn:sap-com:document:sap:rfc:functions}char3" minOccurs="0"/>
 *         &lt;element name="ContFct" type="{urn:sap-com:document:sap:rfc:functions}char8" minOccurs="0"/>
 *         &lt;element name="ContNo" type="{urn:sap-com:document:sap:rfc:functions}char32" minOccurs="0"/>
 *         &lt;element name="DateFro" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="DateTo" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="Descript" type="{urn:sap-com:document:sap:rfc:functions}char40" minOccurs="0"/>
 *         &lt;element name="Direct" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="Logsys" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="Object" type="{urn:sap-com:document:sap:rfc:functions}char3" minOccurs="0"/>
 *         &lt;element name="Pdate" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="Priority" type="{urn:sap-com:document:sap:rfc:functions}numeric1" minOccurs="0"/>
 *         &lt;element name="Proctype" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/>
 *         &lt;element name="RespFct" type="{urn:sap-com:document:sap:rfc:functions}char8" minOccurs="0"/>
 *         &lt;element name="RespNo" type="{urn:sap-com:document:sap:rfc:functions}char32" minOccurs="0"/>
 *         &lt;element name="TimeFro" type="{urn:sap-com:document:sap:rfc:functions}time" minOccurs="0"/>
 *         &lt;element name="TimeTo" type="{urn:sap-com:document:sap:rfc:functions}time" minOccurs="0"/>
 *         &lt;element name="Tzone" type="{urn:sap-com:document:sap:rfc:functions}char6" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "category",
    "contFct",
    "contNo",
    "dateFro",
    "dateTo",
    "descript",
    "direct",
    "logsys",
    "object",
    "pdate",
    "priority",
    "proctype",
    "respFct",
    "respNo",
    "timeFro",
    "timeTo",
    "tzone"
})
@XmlRootElement(name = "ZptActivityCreate")
public class ZptActivityCreate {

    @XmlElement(name = "Category")
    protected String category;
    @XmlElement(name = "ContFct")
    protected String contFct;
    @XmlElement(name = "ContNo")
    protected String contNo;
    @XmlElement(name = "DateFro")
    protected String dateFro;
    @XmlElement(name = "DateTo")
    protected String dateTo;
    @XmlElement(name = "Descript")
    protected String descript;
    @XmlElement(name = "Direct")
    protected String direct;
    @XmlElement(name = "Logsys")
    protected String logsys;
    @XmlElement(name = "Object")
    protected String object;
    @XmlElement(name = "Pdate")
    protected String pdate;
    @XmlElement(name = "Priority")
    protected String priority;
    @XmlElement(name = "Proctype")
    protected String proctype;
    @XmlElement(name = "RespFct")
    protected String respFct;
    @XmlElement(name = "RespNo")
    protected String respNo;
    @XmlElement(name = "TimeFro")
    protected String timeFro;
    @XmlElement(name = "TimeTo")
    protected String timeTo;
    @XmlElement(name = "Tzone")
    protected String tzone;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the contFct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContFct() {
        return contFct;
    }

    /**
     * Sets the value of the contFct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContFct(String value) {
        this.contFct = value;
    }

    /**
     * Gets the value of the contNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContNo() {
        return contNo;
    }

    /**
     * Sets the value of the contNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContNo(String value) {
        this.contNo = value;
    }

    /**
     * Gets the value of the dateFro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFro() {
        return dateFro;
    }

    /**
     * Sets the value of the dateFro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFro(String value) {
        this.dateFro = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTo(String value) {
        this.dateTo = value;
    }

    /**
     * Gets the value of the descript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescript() {
        return descript;
    }

    /**
     * Sets the value of the descript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescript(String value) {
        this.descript = value;
    }

    /**
     * Gets the value of the direct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirect() {
        return direct;
    }

    /**
     * Sets the value of the direct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirect(String value) {
        this.direct = value;
    }

    /**
     * Gets the value of the logsys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogsys() {
        return logsys;
    }

    /**
     * Sets the value of the logsys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogsys(String value) {
        this.logsys = value;
    }

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObject(String value) {
        this.object = value;
    }

    /**
     * Gets the value of the pdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdate() {
        return pdate;
    }

    /**
     * Sets the value of the pdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdate(String value) {
        this.pdate = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the proctype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProctype() {
        return proctype;
    }

    /**
     * Sets the value of the proctype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProctype(String value) {
        this.proctype = value;
    }

    /**
     * Gets the value of the respFct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespFct() {
        return respFct;
    }

    /**
     * Sets the value of the respFct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespFct(String value) {
        this.respFct = value;
    }

    /**
     * Gets the value of the respNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespNo() {
        return respNo;
    }

    /**
     * Sets the value of the respNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespNo(String value) {
        this.respNo = value;
    }

    /**
     * Gets the value of the timeFro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeFro() {
        return timeFro;
    }

    /**
     * Sets the value of the timeFro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeFro(String value) {
        this.timeFro = value;
    }

    /**
     * Gets the value of the timeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeTo() {
        return timeTo;
    }

    /**
     * Sets the value of the timeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeTo(String value) {
        this.timeTo = value;
    }

    /**
     * Gets the value of the tzone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTzone() {
        return tzone;
    }

    /**
     * Sets the value of the tzone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTzone(String value) {
        this.tzone = value;
    }

}
