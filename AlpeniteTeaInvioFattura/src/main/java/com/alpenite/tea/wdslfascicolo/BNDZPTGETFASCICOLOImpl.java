
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.alpenite.tea.wdslfascicolo;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-04-28T12:49:17.871+02:00
 * Generated source version: 3.2.4
 *
 */

@javax.jws.WebService(
                      serviceName = "SVCZPTGETFASCICOLO",
                      portName = "BNDZPTGETFASCICOLO",
                      targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style",
                      wsdlLocation = "file:/var/folders/jr/wg4px6q567z9pnd37nln7vbc0000gn/T/tempdir1574629698737589467.tmp/QTA_ZPTGETFASCICOLO_1.wsdl",
                      endpointInterface = "com.alpenite.tea.wdslfascicolo.ZPTGETFASCICOLO")

public class BNDZPTGETFASCICOLOImpl implements ZPTGETFASCICOLO {

    private static final Logger LOG = Logger.getLogger(BNDZPTGETFASCICOLOImpl.class.getName());

    /* (non-Javadoc)
     * @see com.alpenite.tea.wdslfascicolo.ZPTGETFASCICOLO#zptGetFascicolo(java.lang.String iCc)*
     */
    public com.alpenite.tea.wdslfascicolo.ZptTyFascicolo zptGetFascicolo(java.lang.String iCc) throws ZptGetFascicoloException   {
        LOG.info("Executing operation zptGetFascicolo");
        System.out.println(iCc);
        try {
            com.alpenite.tea.wdslfascicolo.ZptTyFascicolo _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ZptGetFascicoloException("ZptGetFascicolo.Exception...");
    }

}
