package com.roytuts.soapclient;

import com.roytuts.soap.client.*;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.ws.handler.MessageContext;

import  java.lang.ExceptionInInitializerError;



public class GetOkPagaOnline {
 
    private static GetOkPagaOnline client;
    /*QTY
    private static String usernameSap ="UNISYS_MZANI";
    public static String passwordSap ="t2tudo";
    */
    private static String usernameSap ="CONTEA.PRD";
    public static String passwordSap ="MQzGpWcFgH";
    
	private static final String WS_URL = "http://d-q-isu-as.teaspa.lan:8020/sap/bc/srt/rfc/sap/zzcontea_get_onlinepayment/900/zzcontea_get_onlinepayment/getpayment";
	// restituisce il valore di zzcartcr//
	
	public static boolean getPaymentState(String dataP, String servizioP, String codiceP) throws ExceptionInInitializerError, ZzconteaGetOnlinepaymentException, MalformedURLException {
		boolean risultato = false;
		ZZCONTEAGETONLINEPAYMENT_Service service = null;
	        try {
	        	//System.out.println(dataP +" "+servizioP+" "+codiceP);
	           
	        	//URL wsdlLocation = new URL("file:/home/marziobiancardi/wstest/soapclient/src/wsdl/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	            //qta
	        	URL wsdlLocation = new URL("file:/root/namespaceSap/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	            //qta
	        	String targetNamespace="urn:sap-com:document:sap:soap:functions:mc-style";
	            String name="ZZCONTEA_GET_ONLINEPAYMENT";
	            service = new ZZCONTEAGETONLINEPAYMENT_Service( wsdlLocation, new QName(targetNamespace, name));
	            ZZCONTEAGETONLINEPAYMENT port = service.getPort(ZZCONTEAGETONLINEPAYMENT.class);
	            BindingProvider provider = (BindingProvider) port;
	            /*
	            QTA
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "UNISYS_MZANI");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "t2tudo");
	            PROD
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "CONTEA.PRD");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "MQzGpWcFgH");
	            */
	            
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, usernameSap);   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, passwordSap);
	            List<ZconteaPaymentOutGet> dati = new ArrayList<ZconteaPaymentOutGet>();
	            dati = port.zzconteaGetOnlinepayment(dataP, servizioP, codiceP).getItem();
	            int len = dati.size();
	           
	            Iterator<ZconteaPaymentOutGet> itr = dati.iterator();
	             while (0 < len-- && itr.hasNext()) {
	                 
	            	 ZconteaPaymentOutGet dato = itr.next();
	                 
	                 if(dato.getZzcartacr().equals("")) risultato= true;
	                 //else  risultato= true;
	                 
	                // System.out.println("zzcartcr verify"+ dato.getZzcartacr());
	             }
	           
	} catch (ZzconteaGetOnlinepaymentException e ) {
       e.printStackTrace();
		return true;
    }
		
			return risultato;
}
	
	public static String getIdTransazione(String dataP, String servizioP, String codiceP) throws ExceptionInInitializerError, ZzconteaGetOnlinepaymentException, MalformedURLException {
		String transIdCode  = "";
		ZZCONTEAGETONLINEPAYMENT_Service service = null;
	        try {
	        	//System.out.println(dataP +" "+servizioP+" "+codiceP);
	           
	        	//URL wsdlLocation = new URL("file:/home/marziobiancardi/wstest/soapclient/src/wsdl/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	            //qta
	        	URL wsdlLocation = new URL("file:/root/namespaceSap/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	            //qta
	        	String targetNamespace="urn:sap-com:document:sap:soap:functions:mc-style";
	            String name="ZZCONTEA_GET_ONLINEPAYMENT";
	            service = new ZZCONTEAGETONLINEPAYMENT_Service( wsdlLocation, new QName(targetNamespace, name));
	            ZZCONTEAGETONLINEPAYMENT port = service.getPort(ZZCONTEAGETONLINEPAYMENT.class);
	            BindingProvider provider = (BindingProvider) port;
	            /*
	            QTA
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "UNISYS_MZANI");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "t2tudo");
	            PROD
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "CONTEA.PRD");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "MQzGpWcFgH");
	            */
	            
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, usernameSap);   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, passwordSap);
	            List<ZconteaPaymentOutGet> dati = new ArrayList<ZconteaPaymentOutGet>();
	            dati = port.zzconteaGetOnlinepayment(dataP, servizioP, codiceP).getItem();
	            int len = dati.size();
	           
	            Iterator<ZconteaPaymentOutGet> itr = dati.iterator();
	             while (0 < len-- && itr.hasNext()) {
	                 
	            	 ZconteaPaymentOutGet dato = itr.next();
	                 
	                 if(dato.getZzcartaid() != null)  transIdCode= dato.getZzcartaid();
	                 else transIdCode= ""; 
	                 
	                
	             }
	           
	} catch (ZzconteaGetOnlinepaymentException e ) {
       e.printStackTrace();
		
    }
		
			return transIdCode;
}

	
	public static String getCompany(String dataP, String servizioP, String codiceP) throws ExceptionInInitializerError, ZzconteaGetOnlinepaymentException, MalformedURLException {
		String risultato = "00";
		ZZCONTEAGETONLINEPAYMENT_Service service = null;
	        try {
	        	//System.out.println(dataP +" "+servizioP+" "+codiceP);
	           // qta
	        	URL wsdlLocation = new URL("file:/root/namespaceSap/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	        	//URL wsdlLocation = new URL("file:/home/marziobiancardi/wstest/soapclient/src/wsdl/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	            String targetNamespace="urn:sap-com:document:sap:soap:functions:mc-style";
	            String name="ZZCONTEA_GET_ONLINEPAYMENT";
	            service = new ZZCONTEAGETONLINEPAYMENT_Service( wsdlLocation, new QName(targetNamespace, name));
	            ZZCONTEAGETONLINEPAYMENT port = service.getPort(ZZCONTEAGETONLINEPAYMENT.class);
	            BindingProvider provider = (BindingProvider) port;
	            /*
	            QTA
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "UNISYS_MZANI");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "t2tudo");
	            PROD
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "CONTEA.PRD");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "MQzGpWcFgH");
	            */
	            
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "CONTEA.PRD");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "MQzGpWcFgH");
	            List<ZconteaPaymentOutGet> dati = new ArrayList<ZconteaPaymentOutGet>();
	            
	            dati = port.zzconteaGetOnlinepayment(dataP, servizioP, codiceP).getItem();
	            int len = dati.size();
	            Iterator<ZconteaPaymentOutGet> itr = dati.iterator();
	             while (0 < len-- && itr.hasNext()) {
	                 
	            	 ZconteaPaymentOutGet dato = itr.next();
	                 
	                 risultato= dato.getBukrs();
	                 //else  risultato= true;
	                 
	                 //System.out.println("zzcartcr verify"+ dato.getZzcartacr().equals(""));
	             }
	           
	} catch (ZzconteaGetOnlinepaymentException e ) {
       // e.printStackTrace();
		return "DocNotFound";
    }
		
			return risultato;
}
	
	
    public static void main(String[] args) throws ZzconteaGetOnlinepaymentException, ExceptionInInitializerError, MalformedURLException {
        
           

             boolean zzcart = true ;
             String idtrans = "";
            
             
             zzcart=getPaymentState("2017-03-24", "Z025", "251600076980");
            //compagnia = getCompany("2016-09-15", "YE22", "221600096737");
            	 
            	 //e1.printStackTrace();
             
             
             //System.out.println(zzcart);
             idtrans = getIdTransazione("2017-03-24", "Z025", "251600076980");
             System.out.println("idtrans: "+idtrans);
             System.out.println(zzcart);
             //System.out.println(compagnia);
             
        
     
    }
}