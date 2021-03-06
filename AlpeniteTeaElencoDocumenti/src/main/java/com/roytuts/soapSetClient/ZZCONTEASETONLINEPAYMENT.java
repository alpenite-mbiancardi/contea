
package com.roytuts.soapSetClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ZZCONTEA_SET_ONLINEPAYMENT", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZZCONTEASETONLINEPAYMENT {


    /**
     * 
     * @param xblnr
     * @param bukrs
     * @param idTransazione
     * @param bldat
     * @param zzcartacr
     * @param dataCreazione
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ZzconteaSetOnlinepayment")
    @WebResult(name = "Status", targetNamespace = "")
    @RequestWrapper(localName = "ZzconteaSetOnlinepayment", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.roytuts.soapSetClient.ZzconteaSetOnlinepayment")
    @ResponseWrapper(localName = "ZzconteaSetOnlinepaymentResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.roytuts.soapSetClient.ZzconteaSetOnlinepaymentResponse")
    public String zzconteaSetOnlinepayment(
        @WebParam(name = "Bldat", targetNamespace = "")
        String bldat,
        @WebParam(name = "Bukrs", targetNamespace = "")
        String bukrs,
        @WebParam(name = "DataCreazione", targetNamespace = "")
        String dataCreazione,
        @WebParam(name = "IdTransazione", targetNamespace = "")
        String idTransazione,
        @WebParam(name = "Xblnr", targetNamespace = "")
        String xblnr,
        @WebParam(name = "Zzcartacr", targetNamespace = "")
        String zzcartacr);

}
