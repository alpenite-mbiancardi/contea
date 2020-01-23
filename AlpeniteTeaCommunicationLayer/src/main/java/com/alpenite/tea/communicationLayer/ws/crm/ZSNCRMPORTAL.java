package com.alpenite.tea.communicationLayer.ws.crm;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-07-25T00:35:47.396+02:00
 * Generated source version: 2.7.8
 * 
 */
@WebServiceClient(name = "ZSN_CRM_PORTAL", 
                  wsdlLocation = "tea-crm.wsdl",
                  targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style") 
public class ZSNCRMPORTAL extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZSN_CRM_PORTAL");
    public final static QName ZbnCrmPortal = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "zbn_crm_portal");
    static {
        URL url = ZSNCRMPORTAL.class.getResource("tea-crm.wsdl");
        if (url == null) {
            url = ZSNCRMPORTAL.class.getClassLoader().getResource("tea-crm.wsdl");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(ZSNCRMPORTAL.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "tea-crm.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public ZSNCRMPORTAL(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZSNCRMPORTAL(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZSNCRMPORTAL() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ZSNCRMPORTAL(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ZSNCRMPORTAL(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ZSNCRMPORTAL(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ZWSCRMPORTAL
     */
    @WebEndpoint(name = "zbn_crm_portal")
    public ZWSCRMPORTAL getZbnCrmPortal() {
        return super.getPort(ZbnCrmPortal, ZWSCRMPORTAL.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSCRMPORTAL
     */
    @WebEndpoint(name = "zbn_crm_portal")
    public ZWSCRMPORTAL getZbnCrmPortal(WebServiceFeature... features) {
        return super.getPort(ZbnCrmPortal, ZWSCRMPORTAL.class, features);
    }

}
