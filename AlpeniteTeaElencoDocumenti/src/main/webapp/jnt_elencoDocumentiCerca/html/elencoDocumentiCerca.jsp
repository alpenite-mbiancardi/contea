<%-- 
    Document   : elencoDocumentiCerca
    Author     : Andrei Bogdan
--%>
<%@page import="com.alpenite.tea.elencoDocumenti.dati.Documento"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="java.util.List"%>
<%@page import ="java.lang.Double"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import="java.util.Collections"%>
<%@page import= "java.lang.ExceptionInInitializerError"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.alpenite.gui.components.SelectAnni"%>
<%@page import="com.alpenite.tea.elencoDocumenti.Utils"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="com.roytuts.soapclient.GetOkPagaOnline"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:addResources resources="style.css" type="css" />
<%--
<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
 --%>
<%@page import="java.util.*" %>


<%@page import="it.netsw.apps.igfs.cg.coms.api.init.IgfsCgVerify" %> 
<%@page import="com.roytuts.soapclient.SetOkPagaOnline"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<%@page import="java.net.URL" %>


<template:addResources resources="filtro.js" type="javascript" />
<template:addResources resources="utilDocumenti.js" type="javascript" />

<template:addResources resources="scriptMessaggi.js" type="javascript" />
<template:addResources resources="jquery-ui.css" type="css" />
 
<%
    String dataInizioVisualizzata = request.getParameter("dataInizio");
    String dataFineVisualizzata = request.getParameter("dataFine");
    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
    String dataInizio = Utils.getDataFiltro(dataInizioVisualizzata);
    String dataFine = Utils.getDataFiltro(dataFineVisualizzata);
    String numeroFatturaCercata = request.getParameter("numeroFatturaInput");


 // creo il select degli anni
 
    String annoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
    if (!dataFine.isEmpty()) {
        annoCorrente = dataFine.split("-")[0];
    }
   
    SelectAnni anni = new SelectAnni("anno", "anno", null, 0, 0);
    anni.setValoreSelezionato(annoCorrente);
    request.setAttribute("anni", anni.toStringInverse());
    
    if (dataInizio.length()==0 && dataFine.length()==0) {
        String annoPrecedente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR)-1);
        String mesePrecedente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH));
        String meseCorrente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1);
        String meseProssimo = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+2);
        String giornoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
        if(GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH) <10){
            giornoCorrente = "0"+giornoCorrente;
        }
        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) <10){
            mesePrecedente = "0"+mesePrecedente;
        }
        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1 <10){
            meseCorrente = "0"+meseCorrente;
        }
        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+2 <10){
            meseProssimo = "0"+meseProssimo;
        }
        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) == 0){
            mesePrecedente = "12";
        }
      //dataInizio = annoPrecedente+"-"+meseCorrente+"-"+giornoCorrente;
        dataInizio = annoPrecedente+"-"+mesePrecedente+"-"+giornoCorrente;
        dataFine = annoCorrente+"-"+meseCorrente+"-"+giornoCorrente;
        //dataFine = annoCorrente+"-"+meseProssimo+"-"+giornoCorrente;
        //dataInizioVisualizzata = giornoCorrente+"/"+meseCorrente+"/"+annoPrecedente;
        dataInizioVisualizzata = giornoCorrente+"/"+mesePrecedente+"/"+annoPrecedente;
        dataFineVisualizzata = giornoCorrente+"/"+meseCorrente+"/"+annoCorrente;
        //dataFineVisualizzata = giornoCorrente+"/"+meseProssimo+"/"+annoCorrente;
    }
    
  %>    
    <c:set var="fatture" value="${currentNode.properties['fatture'].string}"/>
    <jsp:useBean id="fatture" type="java.lang.String" />
    <c:set var="partiteAperte" value="${currentNode.properties['partiteAperte'].string}"/>
    <jsp:useBean id="partiteAperte" type="java.lang.String" />
    
    
    <%
    partiteAperte= partiteAperte.equalsIgnoreCase("true")?"X":"";
    fatture= fatture.equalsIgnoreCase("true")?"X":"";
    
    // prelevo i documenti
    if(numeroFatturaCercata!=null){
    List<Documento> docs = WSClient.getClient(session).getDettaglioCercaFattura(
            "", 
            "",
            partiteAperte,
            fatture,numeroFatturaCercata);
    Collections.sort(docs);
    Collections.reverse(docs);
    request.setAttribute("elenco", docs);
    
    // imposto la data da visualizzare nel filtro
    request.setAttribute("dataInizio", dataInizioVisualizzata);
    request.setAttribute("dataFine", dataFineVisualizzata);
}
    //se sono nella pagina fatture ed è valorizzata la ricerca faccio vedere solo la fattura che mi interessa come codice
    boolean messaggioErrore = false;
    boolean messaggioErroreVisual= true;
    int elemvisual=0;
%>
<div>
<div class="search">
<h2 class="line"><fmt:message key="elencoDocumentiCerca.titolo"/></h2>
<c:if test="${currentNode.properties['visualizzaRicerca'].string == 'true'}">
    <script>
        var msg = "<fmt:message key="elencoDocumenti.ricerca.messaggio"/>";
        var lang_picker = "${currentResource.locale}";
    </script>
    <form class="frm" id="ricerca" method="post" action="${url.base}${currentNode.path}.elencoDocumentiCerca.do">
        <fieldset>          
            <label id="labelDataInizio" for="dataInizio"><fmt:message key="elencoDocumentiCerca.inserisci"/></label>
            <input name="numeroFatturaInput" id="numeroFatturaInput" type="text" value=""/>
            
            <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
            <%-- <input class="btn medium left" type="button" id="bottoneAnnulla" value="<fmt:message key="elencoDocumenti.ricerca.bottoneAnnulla"/>" />--%>
            
            <input class="btn medium" type="submit" id="bottoneConferma" value="<fmt:message key="elencoDocumenti.ricerca.bottoneConferma"/>" />
        </fieldset>
        
    </form>
    </div>
    <div class="clear"></div>
    <!--div class="messaggio" id="messaggio"></div-->
</c:if>
</div> <!-- chiusura div search aperto in custom tabular -->
<form class="noLoader frm" id="formDocumenti" name="formDocumenti" method="post" action="/scaricaDocumento.jsp" >
<div class="box pag">
<div class="open"></div>

<table id="tabellaDocumenti" class="tbl">
    <thead id="tabellaDocumentiHeader" class="header">
        <tr>
            <c:if test="${currentNode.properties['scaricamentoMultiplo'].string == 'true'}">
                <th class="colonna first"><input type="checkbox" name="selezioneAll" id="selezioneAll" onclick="checkedAct(this)"/></th>
            </c:if>
         
            <th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.numeroFattura"/></th>
           
            
            <th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.dataEmissione"/></th>
            <th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.dataScadenza"/></th>
            <th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.importo"/></th>
        
            <th class="colonna "><fmt:message key="elencoDocumenti.tabellaDocumenti.header.download"/></th>
            <th class="colonna last"><fmt:message key="elencoDocumenti.pagaonline"/></th>
           
           
        </tr>
    </thead>
    <tbody id="tabellaDocumentiRighe">
        <c:forEach items="${elenco}" var="riga" varStatus="count">
        
            <tr id="tabellaDocumentiRiga${count}" class="riga">
            <%
            Documento doc ; 
            doc =(Documento) pageContext.getAttribute("riga"); 
            System.out.println(doc);
           String numeroFattura = doc.getNumeroFattura();
           if(numeroFattura.equals(numeroFatturaCercata)==true){
               messaggioErrore= false;
            %>
                <td></td>
                <td class="colonna dataEmissione bg">${riga.numeroFattura}</td>
                <fmt:formatDate value="${riga.dataEmissione}" pattern="dd-MM-yyyy" var="beginDate1"/>
                <td class="colonna dataEmissione bg">${beginDate1}</td>    
                <fmt:formatDate value="${riga.dataScadenza}" pattern="dd-MM-yyyy" var="endDate1"/>
                <td class="colonna dataScadenza bg">${endDate1}</td>
              
                <td class="colonna importo bg">${riga.importoAperto} &euro;</td>
        
             
                <td class="colonna download">
                    <c:choose>
                        <c:when test="${riga.fileId != null && fn:length(riga.fileId)>0}">
<% 
// <a href="${currentNode.properties['paginaScaricamentoFile'].node.url}?n=${riga.fileId}&t=${riga.fileObj}" target="_blank"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.download"/></a> 
%>
                             <a href="/scaricaDocumento.jsp?n=${riga.fileId}&t=${riga.fileObj}&name=${riga.numeroFattura}.pdf" class="download" target="_blank"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.download"/></a> 
                        </c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>                    
                </td>
                <td class="colonna pagaonline">
                <%
                
               boolean importoMaggioreZero = false;
               boolean isFattura = false;
               //String  = request.getParameter("compagnia");
               String[] arrayCompagnieAttive={doc.getTipoDocumento()};
               boolean visualBottonePagamento = false;
               String decimale =doc.getImportoAperto().substring(doc.getImportoAperto().length()-2);
                String intero = doc.getImportoAperto().substring(0, doc.getImportoAperto().length()-3);
                intero = intero.replace(" ","").replace(",","").replace(".","");
                System.out.println(intero+"."+decimale);
                   System.out.println("numero");
                   System.out.println(doc.getNumeroFattura());
                   
                   System.out.println("importo");
                   System.out.println(doc.getImportoAperto());
                   
                   
                
               Double importoAperto1 = Double.parseDouble(intero+"."+decimale);
                //   Double importoAperto1 = Double.parseDouble(doc.getImportoAperto());
                   
               if(importoAperto1>0) importoMaggioreZero =true;
               else importoMaggioreZero= false;
               if(numeroFattura.equals(null) || numeroFattura.equals("")) isFattura = false;
               else isFattura = true;
               
               Date date = doc.getDataEmissione();
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String dataEmiss = formatter.format(date);
               
               boolean bottonePagamento = true;
               messaggioErrore = true;
               String codAzienda = "";
               int i=0;
               elemvisual++;
               for(i=0;i<arrayCompagnieAttive.length;i++)
               {    
                   try {
                       //cerco la compagnia se non genera eccezzione la chiamata viene passata per verificare se posso pagare la fattura o meno
                        codAzienda = doc.getTipoDocumento();
                       System.out.println("azienda:");
                       System.out.println(codAzienda);
                       if(codAzienda.equals("DocNotFound")==false){
                           messaggioErrore = false;
                       bottonePagamento = GetOkPagaOnline.getPaymentState(dataEmiss, codAzienda, numeroFattura);







                          //check importo aperto casting stringa
                       System.out.println("bottonepagamento:");
                       System.out.println(bottonePagamento);
                            if(importoMaggioreZero == true && isFattura==true && bottonePagamento==true) {
                               //paga online
                                 messaggioErrore = false;
                                 String price2 = importoAperto1.toString();
                                   // controllo 2 decimali
                                  
                                   String price= String.format("%.2f", importoAperto1);
                                  int casuale = (int)(Math.random()*1000);
                                  String numeroTrans = numeroFattura+casuale;
                                  

                                            // nuova modifica pagamenti
                                            // cerco id transazione
                                            String idTransazione = GetOkPagaOnline.getIdTransazione(dataEmiss, codAzienda, numeroFattura);
                                            /*
                                            String tid="UNI_ECOM";
                                            String kSig="UNI_TESTKEY";
                                            */
                                            
                                            
                                            if(!idTransazione.equals("")){
                                                // esiste un id transazione associato, chiamo il metodo Verify Unicredit
                                                // predispongo ji valori per il metodo Verify()
                                            	String azienda = codAzienda;
                                    			String tid ="";
                                    			String kSig ="";
                                    			
                                            	String[] parts = idTransazione.split("-");
                                                String idtran = parts[0]; // 004
                                                String numeroOrdine = parts[1]; // 034556
                                                String tipoPag = parts[2]; // 034556
                                                
                                                
                                                if(tipoPag.contains("M")) {
                                    				//mybank
                                    				if(azienda.equals("Z022")|| azienda.equals("YL22") || azienda.equals("YE22") || azienda.equals("YT22")) {
                                    					//TEA ENERGIA
                                    					 tid= "30363580";
                                    					 kSig= "4e7c4e9c0c6c5h0j0j0f4e7c4e9j0j0f";
                                    				};
                                    				if(azienda.equals("Z023")){
                                    					//MANTOVA AMBIENTE
                                    					 tid= "30363732";
                                    					 kSig= "4e7c4f0c0c6c7c2j0j0f4e7c4f0j0j0f";
                                    				};

                                    				if(azienda.equals("Z025")){
                                    					//TEA ACQUE
                                    					 tid= "30363749";
                                    					 kSig= "4e7c4f2c0c6c7d9j0j0f4e7c4f2j0j0f";
                                    				};

                                    				if(azienda.equals("Z031")){
                                    					//TEA AQA
                                    					 tid= "30363745";
                                    					 kSig= "4h8d3d0c0c6c7d5j0j0f4h8d3d0j0j0f";
                                    				};
                                    				
                                    			}else {
                                    				//carta di credito tipoPag C
                                    				
                                    				if(azienda.equals("Z022")|| azienda.equals("YL22") || azienda.equals("YE22") || azienda.equals("YT22")) {
                                    					//TEA ENERGIA
                                    					 tid= "30362576";
                                    					 kSig= "4e7c4e9c0c6b5g6j0j0e4e7c4e9j0j0e";
                                    				};
                                    				if(azienda.equals("Z023")){
                                    					//MANTOVA AMBIENTE
                                    					 tid= "30362599";
                                    					 kSig= "4e7c4f0c0c6b5i9j0j0e4e7c4f0j0j0e";
                                    				};

                                    				if(azienda.equals("Z025")){
                                    					//TEA ACQUE
                                    					 tid= "30362672";
                                    					 kSig= "4e7c4f2c0c6b6g2j0j0e4e7c4f2j0j0e";
                                    				};

                                    				if(azienda.equals("Z031")){
                                    					//TEA AQA
                                    					 tid= "30362665";
                                    					 kSig= "4h8d3d0c0c6b6f5j0j0e4h8d3d0j0j0e";
                                    				};
                                    			}
                                            	System.out.println(tid);
                                            	System.out.println(kSig);
                                                /* test
                                    			String serverURL="https://testeps.netswgroup.it/UNI_CG_SERVICES/services";
                                    			*/
                                    			String serverURL="https://pagamenti.unicredit.it/UNI_CG_SERVICES/service";
                                                int timeout  = 15000;
                                                //da dividere in base ai negozi in produzione
                                                String errorDescVerify ="";
                                                IgfsCgVerify verify = new IgfsCgVerify();
                                                verify.setServerURL(new URL(serverURL)); 
                                                verify.setTimeout(timeout); 
                                                verify.setTid(tid.replaceAll("\\s+","")); 
                                                verify.setKSig(kSig.replaceAll("\\s+",""));
                                                System.out.println("idtransazione"+idTransazione);

                                                

                                                //shop id passo il numero di fattura
                                                verify.setShopID(numeroOrdine.replaceAll("\\s+",""));
                                                
                                                //verify.setShopID("221770005734773");
                                                
                                                System.out.println("n:"+numeroOrdine);
                                                System.out.println("t:"+idtran);
                                                // payment id è il payment id generato da unicredit e salvato su sap
                                                verify.setPaymentID(idtran.replaceAll("\\s+",""));
                                                //verify.setPaymentID("00221290288137409994");
                                                //Preparo la data di oggi da salvare
                                                String dataPagamento ="";
                                                Date dNow = new Date( );
                                                SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");
                                                dataPagamento = ft.format(dNow).toString();
                                                //System.out.println("ver:"+verify.execute());
                                                if (!verify.execute()) {
                                                    //la fattura è pagabile perchè il metodo verify restituisce false
                                                    visualBottonePagamento = true;
                                                    System.out.println("verify false");
                                                    System.out.println(verify.getTranID());
                                                }
                                                else {
                                                    //il metodo verify resituisce true quindi la fatture è già stata pagata
                                                    //chiamo sap e salvo tramite 
                                                    SetOkPagaOnline.setPaymentState(dataEmiss, codAzienda, numeroFattura,tipoPag,dataPagamento,idTransazione);
                                                    visualBottonePagamento = false;
                                                    System.out.println("verify true");
                                                    System.out.println(verify.getTranID());
                                                }
                                                
                                              

                                            }
                                            else {
                                                // non esiste l'di transazione visualizzo il bottone
                                                visualBottonePagamento = true;
                                            }
                                            Random rand = new Random();
                                             int n = rand.nextInt(90000) + 10000;
                           %>


                            <%if(visualBottonePagamento) {%>
                           <a href="#" id="popup${riga.numeroFattura}<%=n%>"><img src="/modules/assets/css/images/bottonepaga.png"></a>
                           <div id="dialog${riga.numeroFattura}<%=n%>" title="Paga Online : ${riga.numeroFattura} ">
                           
                           <h1>Metodo di Pagamento</h1><br>
                           
                             
                          <a class="bottonePaga" href="/pagaOnlineCarta.jsp?f=<%=numeroTrans%>&i=<%=price%>&a=<%=codAzienda%>&d=<%=dataEmiss%>">Carta di credito</a>
                          <a class="bottonePaga" href="/pagaOnlineMyBank.jsp?f=<%=numeroTrans%>&i=<%=price%>&a=<%=codAzienda%>&d=<%=dataEmiss%>">MyBank</a>
                              
                         </div>
                                <%
                                        } else {
                                            %>Pagamento online in corso
                                            <%
                                        }
                                        %>


                           <script>
                           $( "#popup${riga.numeroFattura}<%=n%>" ).click(function() {
                              $( "#dialog${riga.numeroFattura}<%=n%>" ).dialog();
                            });
                           </script>
                           <style>
                           #dialog${riga.numeroFattura}<%=n%> {
                               display: none;
                           }

                           .ui-dialog-title, .ui-dialog-content, .ui-widget-content {
                               font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
                               font-size: 12pt;
                           }
                           </style>
                           
                           <%
                            } else {
                                if (importoMaggioreZero == true && isFattura==true && bottonePagamento==false){
                                %><fmt:message key="elencoDocumentiCerca.pagamentoincorso"/><%
                                }
                            } 
                        }
                   }
                   catch (ExceptionInInitializerError  e1) {
                   // %><%=arrayCompagnieAttive[i]%><%
                      
                   }
               }
               
               %>
               </td>
          <% } else messaggioErroreVisual = true;
           if(messaggioErroreVisual==false) messaggioErrore = true;
           System.out.println(elemvisual);
           
           %>
               
            </tr>
            
        </c:forEach>
    </tbody> 
</table>

<%
boolean isSetNumeroFattura = (request.getParameter("numeroFatturaInput") == null);

if(isSetNumeroFattura == false && elemvisual==0 ){%>
<p style="font-size:14pt; font-weight:bold;">
    <fmt:message key="elencoDocumentiCerca.errore"/>
</p>
<%}
%>
</div>
<c:if test="${currentNode.properties['scaricamentoMultiplo'].string == 'true'}">
    <input type="hidden" name="nomeFileArchivio" id="nomeFileArchivio" value="${currentNode.properties['nomeFileArchivio'].string}" />
    <div class="pagination">
        <div class="paginationNavigation">
        <!--
            <input type="submit" class="btn extralarge" name="scaricaDocumentiSelezionati" id="scaricaDocumentiSelezionati" value="<fmt:message key="elencoDocumenti.scaricaDocumentiSelezionati"/>" />

            -->
        </div>
    </div>
    <script>
        $('#formDocumenti').submit(function(){
                for(var i = 0; i < $('td.colonna.selezione input').length; i++){
                    if($('td.colonna.selezione input')[i].checked){
                        return true;
                    }
                }
                alert('<fmt:message key="elencoDocumentiCerca.document.select"/>');
                return false;
                
        })
    </script>
</c:if>
</form>

