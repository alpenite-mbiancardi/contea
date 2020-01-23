package com.alpenite.tea.wdslfascicolo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-04-28T12:49:17.881+02:00
 * Generated source version: 3.2.4
 *
 */
@WebService(targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", name = "ZPT_GET_FASCICOLO")
@XmlSeeAlso({ObjectFactory.class})
public interface ZPTGETFASCICOLO {

    @WebMethod(operationName = "ZptGetFascicolo")
    @RequestWrapper(localName = "ZptGetFascicolo", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.alpenite.tea.wdslfascicolo.ZptGetFascicolo_Type")
    @ResponseWrapper(localName = "ZptGetFascicoloResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.alpenite.tea.wdslfascicolo.ZptGetFascicoloResponse")
    @WebResult(name = "TFascicolo", targetNamespace = "")
    public com.alpenite.tea.wdslfascicolo.ZptTyFascicolo zptGetFascicolo(
        @WebParam(name = "ICc", targetNamespace = "")
        java.lang.String iCc
    ) throws ZptGetFascicoloException;
}