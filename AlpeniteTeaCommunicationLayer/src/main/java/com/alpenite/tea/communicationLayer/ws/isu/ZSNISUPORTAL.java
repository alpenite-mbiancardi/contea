package com.alpenite.tea.communicationLayer.ws.isu;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.5
 * 2016-03-03T13:51:16.514+01:00
 * Generated source version: 3.1.5
 * 
 */
@WebServiceClient(name = "ZSN_ISU_PORTAL", 
                  wsdlLocation = "file:src/main/resources/isu-dev.teaspa.lan.8010.xml",
                  targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style") 
public class ZSNISUPORTAL extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZSN_ISU_PORTAL");
    public final static QName ZbnIsuPortal = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "zbn_isu_portal");
    static {
        URL url = null;
        try {
            url = new URL("file:src/main/resources/isu-dev.teaspa.lan.8010.xml");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ZSNISUPORTAL.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:src/main/resources/isu-dev.teaspa.lan.8010.xml");
        }
        WSDL_LOCATION = url;
    }

    public ZSNISUPORTAL(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZSNISUPORTAL(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZSNISUPORTAL() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ZSNISUPORTAL(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ZSNISUPORTAL(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ZSNISUPORTAL(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ZWSISUPORTAL
     */
    @WebEndpoint(name = "zbn_isu_portal")
    public ZWSISUPORTAL getZbnIsuPortal() {
        return super.getPort(ZbnIsuPortal, ZWSISUPORTAL.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSISUPORTAL
     */
    @WebEndpoint(name = "zbn_isu_portal")
    public ZWSISUPORTAL getZbnIsuPortal(WebServiceFeature... features) {
        return super.getPort(ZbnIsuPortal, ZWSISUPORTAL.class, features);
    }

}