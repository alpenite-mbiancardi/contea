<%-- 
    Document   : elencoDocumentiGen
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
<template:addResources resources="filtro.js" type="javascript" />
<template:addResources resources="utilDocumenti.js" type="javascript" />


 
<%
    String dataInizioVisualizzata = request.getParameter("dataInizio");
    String dataFineVisualizzata = request.getParameter("dataFine");
    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
    String dataInizio = Utils.getDataFiltro(dataInizioVisualizzata);
    String dataFine = Utils.getDataFiltro(dataFineVisualizzata);

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
    List<Documento> docs = WSClient.getClient(session).getElencoDocumentiDett(
            dataInizio, 
            dataFine,
            partiteAperte,
            fatture).getRitorno();
    Collections.sort(docs);
    Collections.reverse(docs);
    request.setAttribute("elenco", docs);
    
    // imposto la data da visualizzare nel filtro
    request.setAttribute("dataInizio", dataInizioVisualizzata);
    request.setAttribute("dataFine", dataFineVisualizzata);

    
%>
<div>
<div class="search">
<h2 class="line"><fmt:message key="elencoDocumentiGen.titolo"/></h2>
<c:if test="${currentNode.properties['visualizzaRicerca'].string == 'true'}">
    <script>
        var msg = "<fmt:message key="elencoDocumenti.ricerca.messaggio"/>";
        var lang_picker = "${currentResource.locale}";
    </script>
    <form class="frm" id="ricerca" method="post" action="${url.base}${currentNode.path}.elencoDocumentiGen.do">
		<fieldset>			
			<label id="labelDataInizio" for="dataInizio"><fmt:message key="elencoDocumenti.ricerca.dataInizio"/></label>
			<input name="dataInizio" id="dataInizio" type="text" value="${dataInizio}"/>
			<label id="labelDataFine" for="dataFine"><fmt:message key="elencoDocumenti.ricerca.dataFine"/></label>
			<input name="dataFine" id="dataFine" type="text" value="${dataFine}" />
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
        
            <th class="colonna last"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.download"/></th>
            <%--
            /*<th class="colonna last"><fmt:message key="elencoDocumenti.pagaonline"/></th>
             * 
             */
           --%>
           
        </tr>
    </thead>
    <tbody id="tabellaDocumentiRighe">
        <c:forEach items="${elenco}" var="riga" varStatus="count">
            <tr id="tabellaDocumentiRiga${count}" class="riga">
                <c:if test="${currentNode.properties['scaricamentoMultiplo'].string == 'true'}">
                <td class="colonna selezione">
                        <c:if test="${riga.fileId != null && fn:length(riga.fileId)>0}">
                            <input class="selezioneCls" type="checkbox" name="selezione" id="selezione" value="${riga.fileId}|${riga.fileObj}|${riga.numeroFattura}" />
                        </c:if>
                    </td>
                </c:if>
                <td class="colonna numeroFattura bg">${riga.numeroFattura}</td>
                <fmt:formatDate value="${riga.dataEmissione}" pattern="dd-MM-yyyy" var="beginDate1"/>
                <td class="colonna dataEmissione bg">${beginDate1}</td>    
                <fmt:formatDate value="${riga.dataScadenza}" pattern="dd-MM-yyyy" var="endDate1"/>
                <td class="colonna dataScadenza bg">${endDate1}</td>
              
                <td class="colonna importo bg">${riga.importo} &euro;</td>
        
             
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
                /*
                Documento doc ; 
                doc =(Documento) pageContext.getAttribute("riga"); 
               String numeroFattura = doc.getNumeroFattura();
               boolean importoMaggioreZero = false;
               boolean isFattura = false;
               //String codCompagnia = request.getParameter("compagnia");
               String[] arrayCompagnieAttive={"Z023","Z025", "ZO31","YE22","YT22"};
               Double importoAperto1 = Double.parseDouble(doc.getImporto().replace(" ","").replace(",","."));
               if(importoAperto1>0) importoMaggioreZero =true;
               else importoMaggioreZero= false;
               if(numeroFattura.equals(null) || numeroFattura.equals("")) isFattura = false;
               else isFattura = true;
               
               Date date = doc.getDataEmissione();
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String dataEmiss = formatter.format(date);
               
               boolean bottonePagamento = true; 
               String codAzienda = "";
               int i=0;
               for(i=0;i<arrayCompagnieAttive.length;i++)
               {	
            	   try {
            		   //cerco la compagnia se non genera eccezzione la chiamata viene passata per verificare se posso pagare la fattura o meno
            		   codAzienda = GetOkPagaOnline.getCompany(dataEmiss, arrayCompagnieAttive[i], numeroFattura);
            		   if(codAzienda.equals("DocNotFound")==false){
            		   bottonePagamento = GetOkPagaOnline.getPaymentState(dataEmiss, codAzienda, numeroFattura);
		            	  //check importo aperto casting stringa
		                    if(importoMaggioreZero == true && isFattura==true && bottonePagamento==true) {
		                 	   //paga online
		                    	String price2 = importoAperto1.toString();
		                 	   // controllo 2 decimali
		                 	  
		                 	   String price= String.format("%.2f", importoAperto1);
		                 	  int casuale = (int)(Math.random()*1000);
		                 	  String numeroTrans = numeroFattura+casuale;
		                   %>
		                   <%--
		                   /*
		                   <a href="#" id="popup${riga.numeroFattura}"><img src="/modules/assets/css/images/bottonepaga.png"></a>
		                   <div id="dialog${riga.numeroFattura}" title="Paga Online : ${riga.numeroFattura} ">
		                   
		                   <h1>Metodo di Pagamento</h1><br>
		                 
		                     
		                  <a class="bottonePaga" href="/pagaOnlineCarta.jsp?f=<%=numeroTrans%>&i=<%=price%>&a=<%=codAzienda%>&d=<%=dataEmiss%>">Carta di credito</a>
		                  <a class="bottonePaga" href="/pagaOnlineMyBank.jsp?f=<%=numeroTrans%>&i=<%=price%>&a=<%=codAzienda%>&d=<%=dataEmiss%>">MyBank</a>
		                      
		                 </div>
		                   <script>
		                   $( "#popup${riga.numeroFattura}" ).click(function() {
		                   	  $( "#dialog${riga.numeroFattura}" ).dialog();
		                   	});
		                   </script>
		                   <style>
		                   #dialog${riga.numeroFattura} {
		                       display: none;
		                   }

		                   .ui-dialog-title, .ui-dialog-content, .ui-widget-content {
		                       font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
		                       font-size: 12pt;
		                   }
		                   </style>*/
		                  --%>
		                   <%
		                   /*
		                    }
		                }
            	   }
            	   catch (ExceptionInInitializerError  e1) {
            	    %><%=arrayCompagnieAttive[i]%><%
            	   }
               }*/
               %>
               </td>
              
               
            </tr>
        </c:forEach>
    </tbody> 
</table>
</div>
<c:if test="${currentNode.properties['scaricamentoMultiplo'].string == 'true'}">
    <input type="hidden" name="nomeFileArchivio" id="nomeFileArchivio" value="${currentNode.properties['nomeFileArchivio'].string}" />
    <div class="pagination">
		<div class="paginationNavigation">
			<input type="submit" class="btn extralarge" name="scaricaDocumentiSelezionati" id="scaricaDocumentiSelezionati" value="<fmt:message key="elencoDocumenti.scaricaDocumentiSelezionati"/>" />
		</div>
	</div>
	<script>
		$('#formDocumenti').submit(function(){
				for(var i = 0; i < $('td.colonna.selezione input').length; i++){
					if($('td.colonna.selezione input')[i].checked){
						return true;
					}
				}
				alert('<fmt:message key="elencoDocumentiGen.document.select"/>');
				return false;
				
		})
	</script>
</c:if>
</form>


