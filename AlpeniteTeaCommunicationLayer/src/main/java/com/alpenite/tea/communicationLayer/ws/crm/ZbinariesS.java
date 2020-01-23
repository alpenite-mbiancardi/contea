
package com.alpenite.tea.communicationLayer.ws.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZbinariesS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZbinariesS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Zfile" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="BinaryFlg" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/>
 *         &lt;element name="FileName" type="{urn:sap-com:document:sap:soap:functions:mc-style}char255"/>
 *         &lt;element name="Mimetype" type="{urn:sap-com:document:sap:soap:functions:mc-style}char128"/>
 *         &lt;element name="FileSize" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric12"/>
 *         &lt;element name="IArchiveid" type="{urn:sap-com:document:sap:soap:functions:mc-style}char2"/>
 *         &lt;element name="IDoctype" type="{urn:sap-com:document:sap:soap:functions:mc-style}char20"/>
 *         &lt;element name="IArobj" type="{urn:sap-com:document:sap:soap:functions:mc-style}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZbinariesS", propOrder = {
    "zfile",
    "binaryFlg",
    "fileName",
    "mimetype",
    "fileSize",
    "iArchiveid",
    "iDoctype",
    "iArobj"
})
public class ZbinariesS {

    @XmlElement(name = "Zfile", required = true)
    protected byte[] zfile;
    @XmlElement(name = "BinaryFlg", required = true)
    protected String binaryFlg;
    @XmlElement(name = "FileName", required = true)
    protected String fileName;
    @XmlElement(name = "Mimetype", required = true)
    protected String mimetype;
    @XmlElement(name = "FileSize", required = true)
    protected String fileSize;
    @XmlElement(name = "IArchiveid", required = true)
    protected String iArchiveid;
    @XmlElement(name = "IDoctype", required = true)
    protected String iDoctype;
    @XmlElement(name = "IArobj", required = true)
    protected String iArobj;

    /**
     * Gets the value of the zfile property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getZfile() {
        return zfile;
    }

    /**
     * Sets the value of the zfile property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setZfile(byte[] value) {
        this.zfile = value;
    }

    /**
     * Gets the value of the binaryFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinaryFlg() {
        return binaryFlg;
    }

    /**
     * Sets the value of the binaryFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinaryFlg(String value) {
        this.binaryFlg = value;
    }

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the mimetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * Sets the value of the mimetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimetype(String value) {
        this.mimetype = value;
    }

    /**
     * Gets the value of the fileSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Sets the value of the fileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileSize(String value) {
        this.fileSize = value;
    }

    /**
     * Gets the value of the iArchiveid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIArchiveid() {
        return iArchiveid;
    }

    /**
     * Sets the value of the iArchiveid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIArchiveid(String value) {
        this.iArchiveid = value;
    }

    /**
     * Gets the value of the iDoctype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDoctype() {
        return iDoctype;
    }

    /**
     * Sets the value of the iDoctype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDoctype(String value) {
        this.iDoctype = value;
    }

    /**
     * Gets the value of the iArobj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIArobj() {
        return iArobj;
    }

    /**
     * Sets the value of the iArobj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIArobj(String value) {
        this.iArobj = value;
    }

}
