
package com.roytuts.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.roytuts.soap.client package. 
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

    private final static QName _ZzconteaGetOnlinepaymentException_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZzconteaGetOnlinepayment.Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.roytuts.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZzconteaGetOnlinepaymentResponse }
     * 
     */
    public ZzconteaGetOnlinepaymentResponse createZzconteaGetOnlinepaymentResponse() {
        return new ZzconteaGetOnlinepaymentResponse();
    }

    /**
     * Create an instance of {@link ZconteaPaymentOutGetTab }
     * 
     */
    public ZconteaPaymentOutGetTab createZconteaPaymentOutGetTab() {
        return new ZconteaPaymentOutGetTab();
    }

    /**
     * Create an instance of {@link ZzconteaGetOnlinepayment }
     * 
     */
    public ZzconteaGetOnlinepayment createZzconteaGetOnlinepayment() {
        return new ZzconteaGetOnlinepayment();
    }

    /**
     * Create an instance of {@link ZzconteaGetOnlinepaymentRfcException }
     * 
     */
    public ZzconteaGetOnlinepaymentRfcException createZzconteaGetOnlinepaymentRfcException() {
        return new ZzconteaGetOnlinepaymentRfcException();
    }

    /**
     * Create an instance of {@link ZconteaPaymentOutGet }
     * 
     */
    public ZconteaPaymentOutGet createZconteaPaymentOutGet() {
        return new ZconteaPaymentOutGet();
    }

    /**
     * Create an instance of {@link RfcExceptionMessage }
     * 
     */
    public RfcExceptionMessage createRfcExceptionMessage() {
        return new RfcExceptionMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZzconteaGetOnlinepaymentRfcException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sap-com:document:sap:soap:functions:mc-style", name = "ZzconteaGetOnlinepayment.Exception")
    public JAXBElement<ZzconteaGetOnlinepaymentRfcException> createZzconteaGetOnlinepaymentException(ZzconteaGetOnlinepaymentRfcException value) {
        return new JAXBElement<ZzconteaGetOnlinepaymentRfcException>(_ZzconteaGetOnlinepaymentException_QNAME, ZzconteaGetOnlinepaymentRfcException.class, null, value);
    }

}
