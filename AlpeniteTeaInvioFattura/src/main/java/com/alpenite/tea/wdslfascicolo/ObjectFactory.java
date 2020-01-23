
package com.alpenite.tea.wdslfascicolo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.alpenite.tea.wdslfascicolo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ZptGetFascicoloException_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZptGetFascicolo.Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.alpenite.tea.wdslfascicolo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZptGetFascicoloRfcException }
     * 
     */
    public ZptGetFascicoloRfcException createZptGetFascicoloRfcException() {
        return new ZptGetFascicoloRfcException();
    }

    /**
     * Create an instance of {@link ZptGetFascicolo_Type }
     * 
     */
    public ZptGetFascicolo_Type createZptGetFascicolo_Type() {
        return new ZptGetFascicolo_Type();
    }

    /**
     * Create an instance of {@link ZptGetFascicoloResponse }
     * 
     */
    public ZptGetFascicoloResponse createZptGetFascicoloResponse() {
        return new ZptGetFascicoloResponse();
    }

    /**
     * Create an instance of {@link ZptTyFascicolo }
     * 
     */
    public ZptTyFascicolo createZptTyFascicolo() {
        return new ZptTyFascicolo();
    }

    /**
     * Create an instance of {@link ZptStFascicolo }
     * 
     */
    public ZptStFascicolo createZptStFascicolo() {
        return new ZptStFascicolo();
    }

    /**
     * Create an instance of {@link RfcExceptionMessage }
     * 
     */
    public RfcExceptionMessage createRfcExceptionMessage() {
        return new RfcExceptionMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZptGetFascicoloRfcException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sap-com:document:sap:soap:functions:mc-style", name = "ZptGetFascicolo.Exception")
    public JAXBElement<ZptGetFascicoloRfcException> createZptGetFascicoloException(ZptGetFascicoloRfcException value) {
        return new JAXBElement<ZptGetFascicoloRfcException>(_ZptGetFascicoloException_QNAME, ZptGetFascicoloRfcException.class, null, value);
    }

}
