<%-- 
Document   : elencoDocumenti
Author     : Lorenzo Bortolotto <lorenzo@mxlab.net> , Marzio Biancardi 
--%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.alpenite.gui.components.SelectAnni"%>
<%@page import="com.alpenite.tea.elencoDocumenti.Utils"%>
<%@page import="com.alpenite.tea.elencoDocumenti.dati.Documento"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="com.roytuts.soapclient.GetOkPagaOnline"%>
<%@page import ="java.lang.Double"%>
<%@page import ="java.math.BigDecimal"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>


<%@page import="it.netsw.apps.igfs.cg.coms.api.init.IgfsCgVerify" %> 
<%@page import="com.roytuts.soapclient.SetOkPagaOnline"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<%@page import="java.net.URL" %>

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
//System.out.println("DENTRO JSP");
String dataInizioVisualizzata = request.getParameter("dataInizio");
String dataFineVisualizzata = request.getParameter("dataFine");

JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
String dataInizio = Utils.getDataFiltro(dataInizioVisualizzata);
String dataFine = Utils.getDataFiltro(dataFineVisualizzata);
String tipo = currentNode.getPropertyAsString("tipoDocumento");
request.setAttribute("tipo", tipo);

// creo il select degli anni
String anniPrima = currentNode.getPropertyAsString("anniPrima");
String annoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
if (!dataFine.isEmpty()) {
	annoCorrente = dataFine.split("-")[0];
}
anniPrima = anniPrima==null||anniPrima.length()==0?"10":anniPrima;
SelectAnni anni = new SelectAnni("anno", "anno", null, Integer.parseInt(anniPrima), 0);
anni.setValoreSelezionato(annoCorrente);
request.setAttribute("anni", anni.toStringInverse());

// imposto un filtro di default
if (tipo.equals("estrattoConto") && dataInizio.length()==0 && dataFine.length()==0) {
	dataInizio = annoCorrente+"-01-01";
	dataFine = annoCorrente+"-12-31";
}

if (tipo.equals("fatture") && dataInizio.length()==0 && dataFine.length()==0) {
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

	if (!mesePrecedente.equals("12")){
		annoPrecedente = annoCorrente;
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

// prelevo i documenti
request.setAttribute("elenco", WSClient.getClient(session).getElencoDocumenti(
		request.getParameter("cc"), 
		tipo, 
		dataInizio, 
		dataFine).getRitorno());

// imposto la data da visualizzare nel filtro
request.setAttribute("dataInizio", dataInizioVisualizzata);
request.setAttribute("dataFine", dataFineVisualizzata);

// recupero email utente 

String emailUser = currentNode.getUser().getProperty("j:email");
%>
<c:if test="${currentNode.properties['visualizzaRicerca'].string == 'true'}">
<script>
var msg = "<fmt:message key="elencoDocumenti.ricerca.messaggio"/>";
var lang_picker = "${currentResource.locale}";
</script>
<form class="frm" id="ricerca" method="post" action="${url.base}${currentNode.path}.elencoDocumenti1.do">
<fieldset>
<c:if test="${tipo!='estrattoConto'}">
<label id="labelDataInizio" for="dataInizio"><fmt:message key="elencoDocumenti.ricerca.dataInizio"/></label>
<input name="dataInizio" id="dataInizio" type="text" value="${dataInizio}"/>
<label id="labelDataFine" for="dataFine"><fmt:message key="elencoDocumenti.ricerca.dataFine"/></label>
<input name="dataFine" id="dataFine" type="text" value="${dataFine}" />
<input type="hidden" id="compagnia" name="compagnia" value="${param['compagnia']}" />
</c:if>
<c:if test="${tipo=='estrattoConto'}">
<label id="labelAnno" for="anno"><fmt:message key="elencoDocumenti.ricerca.anno"/></label>
${anni}
</c:if>    
<input type="hidden" id="cc" name="cc" value="${param['cc']}" />
<input type="hidden" id="displayTab" name="displayTab" value="${param['displayTab']}" />
<input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
<input type="hidden" name="servizio" value="${param['servizio']}" />
<input type="hidden" name="e" value="${param['e']}" />
<input type="hidden" id="compagnia" name="compagnia" value="${param['compagnia']}" />
<%-- <input class="btn medium left" type="button" id="bottoneAnnulla" value="<fmt:message key="elencoDocumenti.ricerca.bottoneAnnulla"/>" />--%>
<input class="btn medium" type="submit" id="bottoneConferma" value="<fmt:message key="elencoDocumenti.ricerca.bottoneConferma"/>" />
</fieldset>

</form>

<div class="clear"></div>
<!--div class="messaggio" id="messaggio"></div-->
</c:if>
</div> 

<!--MOD UNISYS 28-01-2014 --> 
<!-- chiusura div search aperto in custom tabular -->



<form class="noLoader frm" id="formDocumenti" name="formDocumenti" method="post" action="/scaricaDocumento.jsp" >
<div class="box pag">
<div class="open"></div>
<table id="tabellaDocumenti" class="tbl">
<thead id="tabellaDocumentiHeader" class="header">
<tr>
<c:if test="${currentNode.properties['scaricamentoMultiplo'].string == 'true'}">
<th class="colonna first"><input type="checkbox" onclick="checkedAct(this)"></th>
</c:if>
<c:if test="${tipo!='estrattoConto'}">
<th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.numeroFattura"/></th>
</c:if>
<c:if test="${tipo=='estrattoConto'}">
<th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.numeroDocumento"/></th>
</c:if>
<th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.dataEmissione"/></th>
<th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.dataScadenza"/></th>
<th class="colonna"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.importo"/></th>

<c:if test="${tipo!='estrattoConto'}">
<th class="colonna last"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.download"/></th>
</c:if>
<c:if test="${tipo=='estrattoConto'}">
<th class="colonna "><fmt:message key="elencoDocumenti.tabellaDocumenti.header.importoAperto"/></th>
</c:if>
<c:if test="${tipo=='estrattoConto' || tipo=='partiteAperte' }">
<th class="colonna last"><fmt:message key="elencoDocumenti.pagaonline"/></th>
</c:if>
</tr>
</thead>
<tbody id="tabellaDocumentiRighe">
<c:forEach items="${elenco}" var="riga" varStatus="count">
<tr id="tabellaDocumentiRiga${count}" class="riga">
<c:if test="${currentNode.properties['scaricamentoMultiplo'].string == 'true'}">
<td class="colonna selezione">
<c:if test="${riga.fileId != null && fn:length(riga.fileId)>0}">
<input type="checkbox" name="selezione" class="selezioneCls" value="${riga.fileId}|${riga.fileObj}|${riga.numeroFattura}" />
</c:if>
</td>
</c:if>
<td class="colonna numeroFattura bg">${riga.numeroFattura}

</td>
<fmt:formatDate value="${riga.dataEmissione}" pattern="dd-MM-yyyy" var="beginDate1"/>
<td class="colonna dataEmissione bg">${beginDate1}</td>    
<fmt:formatDate value="${riga.dataScadenza}" pattern="dd-MM-yyyy" var="endDate1"/>
<td class="colonna dataScadenza bg">${endDate1}</td>
<c:if test="${tipo=='partiteAperte'}">
<td class="colonna importo bg">${riga.importoAperto} &euro;</td>
</c:if>

<c:if test="${tipo!='partiteAperte'}">
<td class="colonna importo bg">${riga.importoAperto} &euro;</td>
</c:if>
<c:if test="${tipo!='estrattoConto'}">
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
</c:if>
<c:if test="${tipo=='estrattoConto'}">
<td class="colonna importoAperto">${riga.importoAperto} &euro;</td>    
</c:if> 
<c:if test="${tipo=='estrattoConto' || tipo=='partiteAperte' }">
<td class="colonna pagaonline">
<%
Documento doc ; 
Double importoAperto1;
doc =(Documento) pageContext.getAttribute("riga"); 
String numeroFattura = doc.getNumeroFattura();
boolean importoMaggioreZero = false;
boolean isFattura = false;
String codCompagnia = request.getParameter("compagnia");
boolean visualBottonePagamento = false;
if(currentNode.getPropertyAsString("tipoDocumento").equals("partiteAperte"))
{
	String decimale =doc.getImportoAperto().substring(doc.getImportoAperto().length()-2);
	String intero = doc.getImportoAperto().substring(0, doc.getImportoAperto().length()-3);
	intero = intero.replace(" ","").replace(",","").replace(".","");
	//System.out.println(intero+"."+decimale);


	importoAperto1 = Double.parseDouble(intero+"."+decimale);
	//importoAperto1 = Double.parseDouble(doc.getImportoAperto().replace(" ","").replace(",","."));
}

else  if(currentNode.getPropertyAsString("tipoDocumento").equals("estrattoConto")){
	String decimale =doc.getImportoAperto().substring(doc.getImportoAperto().length()-2);
	String intero = doc.getImportoAperto().substring(0, doc.getImportoAperto().length()-3);
	intero = intero.replace(" ","").replace(",","").replace(".","");
	//System.out.println(intero+"."+decimale);


	importoAperto1 = Double.parseDouble(intero+"."+decimale);
	//importoAperto1 = Double.parseDouble(doc.getImportoAperto().replace(" ","").replace(",","."));
}

else {
	String decimale =doc.getImporto().substring(doc.getImporto().length()-2);
	String intero = doc.getImporto().substring(0, doc.getImporto().length()-3);
	intero = intero.replace(" ","").replace(",","").replace(".","");
	//System.out.println(intero+"."+decimale);


	importoAperto1 = Double.parseDouble(intero+"."+decimale);
}
//System.out.println(importoAperto1);
if(importoAperto1<=0) importoMaggioreZero =false;
else importoMaggioreZero= true;
if(numeroFattura.equals(null) || numeroFattura.equals("")) isFattura = false;
else isFattura = true;

Date date = doc.getDataEmissione();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String dataEmiss = formatter.format(date);
if(importoAperto1 >0) {
	if(request.getParameter("servizio").equals("E8")){
		if(codCompagnia.equals("Z022")){
			codCompagnia="YL22";
		}
	}
	boolean bottonePagamento = GetOkPagaOnline.getPaymentState(dataEmiss, codCompagnia, numeroFattura);
	//chech importo aperto casting stringa
	if(importoMaggioreZero == true && isFattura==true && bottonePagamento == true ) {
		//paga online
		String price2 = importoAperto1.toString();
		// controllo 2 decimali

		String price= String.format("%.2f", importoAperto1);
		int casuale = (int)(Math.random()*1000);
		String numeroTrans = numeroFattura+casuale;
		%>



		<%
		//cambio il codice compagnia per il problema delle due compagnia differenti
		if(request.getParameter("servizio").equals("E8")){
			if(codCompagnia.equals("Z022")){
				codCompagnia="YL22";
			}
		}%>
		<% 
		// nuova modifica pagamenti
		// cerco id transazione
		String idTransazione = GetOkPagaOnline.getIdTransazione(dataEmiss, codCompagnia, numeroFattura);
		// AMBIENTE DI TEST
		/*
		String tid="UNI_ECOM";
		String kSig="UNI_TESTKEY";
		*/
		if(!idTransazione.equals("")){
			// esiste un id transazione associato, chiamo il metodo Verify Unicredit
			// predispongo ji valori per il metodo Verify()
			
			String azienda = codCompagnia;
			String tid ="";
			String kSig ="";
			String[] parts = idTransazione.split("-");
			String idtran = parts[0]; // 004
			String numeroOrdine = parts[1]; // 034556
			String tipoPag = parts[2]; // 034556
			
			if(tipoPag.equals('M')) {
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
			verify.setTid(tid); 
			verify.setKSig(kSig);
			System.out.println("idtransazione"+idTransazione);

			
			

			//shop id passo il numero di fattura
			verify.setShopID(numeroOrdine);
			// payment id è il payment id generato da unicredit e salvato su sap
			verify.setPaymentID(idtran);
			//Preparo la data di oggi da salvare
			String dataPagamento ="";
			Date dNow = new Date( );
			SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");
			dataPagamento = ft.format(dNow).toString();
			if (!verify.execute()) {
				//la fattura è pagabile perchè il metodo verify restituisce false
				visualBottonePagamento = true;
				System.out.println("verify false");
				System.out.println(verify.getTranID());
			}
			else {
				//il metodo verify resituisce true quindi la fatture è già stata pagata
				//chiamo sap e salvo tramite 
				SetOkPagaOnline.setPaymentState(dataEmiss, codCompagnia, numeroFattura,tipoPag,dataPagamento,idTransazione);
				visualBottonePagamento = false;
				System.out.println("verify true");
				System.out.println(verify.getTranID());
			}
		}
		else {
			// non esiste l'di transazione visualizzo il bottone
			visualBottonePagamento = true;
		}
		%>

		<%
		if(visualBottonePagamento) {
			%>
			<a href="#" id="popup${riga.numeroFattura}"><img src="/modules/assets/css/images/bottonepaga.png"></a>
			<div id="dialog${riga.numeroFattura}" title="Paga Online : ${riga.numeroFattura} ">

			<h1>Metodo di Pagamento</h1><br>
			<a class="bottonePaga" href="/pagaOnlineCarta.jsp?f=<%=numeroTrans%>&i=<%=price%>&a=<%=codCompagnia%>&d=<%=dataEmiss%>&usermail=<%=emailUser%>">Carta di credito</a>
			<a class="bottonePaga" href="/pagaOnlineMyBank.jsp?f=<%=numeroTrans%>&i=<%=price%>&a=<%=codCompagnia%>&d=<%=dataEmiss%>&usermail=<%=emailUser%>">MyBank</a>

			</div>
			<%
		} else {
			%>Pagamento online in corso
			<%
		}
		%>

		<script>
		$( "#popup${riga.numeroFattura}" ).click(function() {

			var $div = $('#tabellaDocumenti');

			$( "#dialog${riga.numeroFattura}" ).dialog({
				position:{
				my: "left top",
				at: "left top",
				of: $div
			},
			modal: true,
			open : function() {
				$('#dialog${riga.numeroFattura}').scrollTop(0);
			}


			} );

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
		</style>
		<%              //System.out.println("bottonepagamento" + bottonePagamento +" visual bottone pagamento "+ visualBottonePagamento);
	} else if(bottonePagamento==false || visualBottonePagamento==false) {%>
	Pagamento online in corso
	<%}
}%>

</td>
</c:if>
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

