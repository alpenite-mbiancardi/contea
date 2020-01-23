package com.roytuts.soapclient;
import com.roytuts.soapSetClient.*;
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



public class SetOkPagaOnline {
 
    private static SetOkPagaOnline client;

	private static final String WS_URL = "http://d-q-isu-as.teaspa.lan:8020/sap/bc/srt/rfc/sap/zzcontea_set_onlinepayment/900/zzcontea_set_onlinepayment/setpayment";
	// restituisce il valore di zzcartcr//
	
	public static String setPaymentState(String dataP, String servizioP, String codiceP, String zzcart, String dataPagamento, String idTransazione) throws ExceptionInInitializerError, ZzconteaGetOnlinepaymentException, MalformedURLException {
		boolean risultato = true;
		ZZCONTEASETONLINEPAYMENT_Service service = null;
	        
	        	System.out.println(dataP +" "+servizioP+" "+codiceP+" "+dataPagamento+ " "+idTransazione);
	           
	        	//URL wsdlLocation = new URL("file:/home/marziobiancardi/Downloads/ZZCONTEA_SET_ONLINEPAYMENT.wsdl");
	        	
	            //qta
	        	URL wsdlLocation = new URL("file:/root/namespaceSap/ZZCONTEA_SET_ONLINEPAYMENT.wsdl");
	            //qta
	            String targetNamespace="urn:sap-com:document:sap:soap:functions:mc-style";
	            String name="ZZCONTEA_SET_ONLINEPAYMENT";
	            service = new ZZCONTEASETONLINEPAYMENT_Service( wsdlLocation, new QName(targetNamespace, name));
	            ZZCONTEASETONLINEPAYMENT port = service.getPort(ZZCONTEASETONLINEPAYMENT.class);
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
	            String dati = "";
	            dati = port.zzconteaSetOnlinepayment(dataP, servizioP, dataPagamento, idTransazione, codiceP, zzcart);

	           //port.zzconteaSetOnlinepayment(bldat, bukrs, xblnr, zzcartacr)
	            return dati;
	           
		
			
}
	
	
    public static void main(String[] args) throws ZzconteaGetOnlinepaymentException, ExceptionInInitializerError, MalformedURLException {
        
           

             String risp  = "" ;
             
             try {
             
            	 risp=setPaymentState("2017-03-24", "Z025", "251600076980","", "2017-06-28", "TRAN1");
             } catch (ExceptionInInitializerError  e1) {
            	 
            	 e1.printStackTrace();
             }
             
             System.out.println(risp);
             
        
     
    }
}