package com.alpenite.tea.wdslfascicolo;

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
import com.alpenite.tea.wdslfascicolo.SvcZptGetFascicolo;
import javax.xml.ws.handler.MessageContext;

import  java.lang.ExceptionInInitializerError;



public class GetDocCliente {
 
    private static GetDocCliente client;
    
    private static String usernameSap ="UNISYS_MZANI";
    public static String passwordSap ="t2tudo";
    /*prd
    private static String usernameSap ="CONTEA.PRD";
    public static String passwordSap ="MQzGpWcFgH";
    */
    //qta
	//private static final String WS_URL = "http://d-q-isu-as.teaspa.lan:8020/sap/bc/srt/rfc/sap/zzcontea_get_onlinepayment/900/zzcontea_get_onlinepayment/getpayment";
	private static final String WS_URL = "http://p-isu-a3.teaspa.lan:8037/sap/bc/srt/rfc/sap/zpt_get_fascicolo/900/svczptgetfascicolo/bndzptgetfascicolo";

	// restituisce il valore di zzcartcr//
	
	public static    List<DocumentoFascicolo> getItemDoc(String condcontratto ) throws ExceptionInInitializerError, MalformedURLException {
		ZptTyFascicolo risultato = new ZptTyFascicolo();
	        
	        	//System.out.println(dataP +" "+servizioP+" "+codiceP);
	           
	        	//URL wsdlLocation = new URL("file:/home/marziobiancardi/wstest/soapclient/src/wsdl/ZZCONTEA_GET_ONLINEPAYMENT.wsdl");
	            //qta
	        	URL wsdlLocation = new URL("file:/root/namespaceSap/PRD_ZPTGETFASCICOLO.wsdl");
	            //qta
	        	String targetNamespace="urn:sap-com:document:sap:soap:functions:mc-style";
	            String name="SVCZPTGETFASCICOLO";
	            SvcZptGetFascicolo service = new SvcZptGetFascicolo( wsdlLocation, new QName(targetNamespace, name));
	            ZPTGETFASCICOLO port = service.getPort(ZPTGETFASCICOLO.class);
	            BindingProvider provider = (BindingProvider) port;
	            
	            //qta 
	            /*
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "UNISYS_MZANI");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "t2tudo");
	            */
	            
	            provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "CONTEA.PRD");   
	            provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "MQzGpWcFgH");
	            /*PROD*/
	            
	            List<DocumentoFascicolo> lista = new ArrayList<DocumentoFascicolo>();

	            {
	            System.out.println("Invoking zptGetFascicolo...");
	            java.lang.String _zptGetFascicolo_iCc = condcontratto;
	            try {
	                com.alpenite.tea.wdslfascicolo.ZptTyFascicolo _zptGetFascicolo__return = port.zptGetFascicolo(_zptGetFascicolo_iCc);
	                //System.out.println("zptGetFascicolo.result=" + _zptGetFascicolo__return);
	                //System.out.println(_zptGetFascicolo__return.item.get(0).definizIt);
	                
	                ZptTyFascicolo documenti = _zptGetFascicolo__return;
	               
	                List<ZptStFascicolo> dati = new ArrayList<ZptStFascicolo>();
		            dati = documenti.getItem();
		            int len = dati.size();
		            System.out.println("test2");
		            System.out.println(len);
		            Iterator<ZptStFascicolo> itr = dati.iterator();
		             while (0 < len-- && itr.hasNext()) {
		                 
		            	 ZptStFascicolo dato = itr.next();
		            	 
		            	 lista.add(new DocumentoFascicolo (dato.sezioneIt,dato.sezioneEn, dato.definizIt,
		            			 
		            			 dato.definizEn, dato.estensione, dato.data, dato.arcDocId, dato.tech1,
		            			 
		            			 dato.tech2, dato.tech3, dato.sapObject));
		            	 System.out.println("test");
		                 System.out.println(dato.arcDocId + " " + dato.definizIt + " " + dato.sezioneIt); 
		                 
		                
		             }
	                

	            } catch (ZptGetFascicoloException e) {
	                System.out.println("Expected exception: ZptGetFascicolo.Exception has occurred.");
	                System.out.println(e.toString());
	            }
	                }
	           
	
		
			return lista;
}
	
    public static void main(String[] args) throws  ExceptionInInitializerError, MalformedURLException {
        
           

            
            
             //qta
             //getItemDoc("30139084");
            
             getItemDoc("30146808");
             
            //compagnia = getCompany("2016-09-15", "YE22", "221600096737");
            	 
            	 //e1.printStackTrace();
             
             
         
             
        
     
    }
}