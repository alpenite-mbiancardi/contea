
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZptStSr complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZptStSr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Status" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Type" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Opendate" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Closedate" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="QuestionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnswerTest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZptStSr", propOrder = {
    "objectId",
    "status",
    "type",
    "opendate",
    "closedate",
    "questionText",
    "answerTest"
})
public class ZptStSr {

    @XmlElement(name = "ObjectId", required = true)
    protected String objectId;
    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Opendate", required = true)
    protected String opendate;
    @XmlElement(name = "Closedate", required = true)
    protected String closedate;
    @XmlElement(name = "QuestionText", required = true)
    protected String questionText;
    @XmlElement(name = "AnswerTest", required = true)
    protected String answerTest;

    /**
     * Gets the value of the objectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets the value of the objectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectId(String value) {
        this.objectId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the opendate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpendate() {
        return opendate;
    }

    /**
     * Sets the value of the opendate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpendate(String value) {
        this.opendate = value;
    }

    /**
     * Gets the value of the closedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClosedate() {
        return closedate;
    }

    /**
     * Sets the value of the closedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClosedate(String value) {
        this.closedate = value;
    }

    /**
     * Gets the value of the questionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the value of the questionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionText(String value) {
        this.questionText = value;
    }

    /**
     * Gets the value of the answerTest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswerTest() {
        return answerTest;
    }

    /**
     * Sets the value of the answerTest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswerTest(String value) {
        this.answerTest = value;
    }

}
