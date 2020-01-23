<%-- 
    Document   : elencoCondomini
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.elencoCondomini.actions.AggiungiCondominioLista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
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

<template:addResources resources="jquery-ui.css" type="css" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtro.js" type="javascript" />
<%
    // azzero la lista dei condomini da aggiungere
    session.removeAttribute(AggiungiCondominioLista.LISTA_AGGIUNGI_CONDOMINI);

    // richiedo al server la lista dei Condomini, eventualmente filtrandoli
    request.setAttribute("elenco", WSClient.getClient(session).getElencoCondomini().getRitorno());
    
    if (session.getAttribute("BPCSESSION") != null) {
        request.setAttribute("bpcattuale", session.getAttribute("BPCSESSION").toString());
    }
    if(session.getAttribute("UINFSESSION") != null) {
    	request.setAttribute("condominioSelezionato", session.getAttribute("UINFSESSION").toString() );
    }else{
    	request.setAttribute("condominioSelezionato", "Condominio:"); 
    }
%>
<% 
// visualizzo l'eventuale messaggio relativo all'aggiunta dei condomini
if (session.getAttribute("messaggioAggiungiCondominio") != null) { 
    out.print("<div id=\"messaggioAggiungiCondominio\">"+session.getAttribute("messaggioAggiungiCondominio")+"</div>");
    session.removeAttribute("messaggioAggiungiCondominio");
} 
// visualizzo l'eventuale messaggio relativo alla rimozione dei condomini
if (session.getAttribute("messaggioRimuoviCondominio") != null) { 
    out.print("<div id=\"messaggioRimuoviCondominio\">"+session.getAttribute("messaggioRimuoviCondominio")+"</div>");
    session.removeAttribute("messaggioRimuoviCondominio");
} 
%>
<script>
    var urlAggiungiLista = "${url.base}${currentNode.path}.aggiungiCondominioLista.do";
</script>
<div class="main">

<div class="box">
	<div class="open"></div>
	
	<form class="frm" id="formSelezionaCondominio" name="formSelezionaCondominio" method="post" action="<c:url value='${url.base}${currentNode.path}'/>.selezionaCondominio.do">
	  
		<table id="tabellaCondomini" class="tbl tabella tablesorter" border="0" cellpadding="0" cellspacing="0" class="data">
			<thead id="tabellaCondominiHeader" class="header">
				<tr>
					<th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.seleziona"/></th>
					<th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.nome"/></th>
					<th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.indirizzo"/></th>
					<th class="colonna last"><fmt:message key="elencoCondomini.tabellaCondomini.header.comune"/></th>
					
				</tr>
			</thead>
			<tbody id="tabellaCondominiRighe">
				<c:forEach items="${elenco}" var="condominio" varStatus="numero" >
					<tr id="tabellaCondominiRiga${numero}}" class="riga">
						<td class="colonna selezione">
							<c:set var="selezionato" value="" />
							<c:if test="${condominio.codiceBP == bpcattuale}" >
								<c:set var="selezionato" value="checked='checked'" />
							</c:if>
							<input type="radio" class="radio" ${selezionato} name="selezione" id="selezione" value="${condominio.codiceBP}" />
							<input type="hidden" name="nomeCond" id="nomeCond" value="${condominio.ragioneSociale1}" />
						</td>
						<td class="colonna nome focus bg">${condominio.ragioneSociale1} ${condominio.ragioneSociale2}</td>
						<td class="colonna indirizzo bg">${condominio.via}, ${condominio.numeroCivico}</td>
						<td class="colonna comune bg">${condominio.comune}</td>
					</tr>
				</c:forEach>
			</tbody> 
		</table>
	   
		<input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
	</form>
	<div id="aggiungiRimuovi">
		<div class="ctn-data pag pagination">
			<div class="paginationNavigation">
				<input type="button" class="btn large" id="bottoneAggiungiCondomini" value="<fmt:message key='elencoCondomini.bottoneAggiungiCondomini' />" />
				<input type="button" class="btn large left" id="bottoneRimuoviCondomini" value="<fmt:message key='elencoCondomini.bottoneRimuoviCondomini' />" />
			</div>
		</div>
	</div>
	</div>
</div>
       
<div id="moduloAggiungiCondomini">
	<div id="overlay">	
		<div id="content">
			<div class="main">
			    <form id="formAggiungiCondominiLista">
			    	<div class="ctn-data pag">
			    		<h3><fmt:message key='elencoCondomini.label.aggiungi.titolo' /></h3>
				    	<table border="0" cellpadding="0" cellspacing="0">
				    	<tr>
				    		<td><label for="ragioneSociale1" id="labelRagioneSociale1"><fmt:message key="elencoCondomini.aggiungiCondominio.ragioneSociale1" /></label></td>
				    		<td colspan="3"><input size="53" type="text" id="ragioneSociale1" name="ragioneSociale1" />
				    		<input type="hidden"  id="ragioneSociale2" name="ragioneSociale2" value=" " />
				    		</td>
				    		<%--<td><label for="ragioneSociale2" id="labelRagioneSociale2"><fmt:message key="elencoCondomini.aggiungiCondominio.ragioneSociale2" /></label></td>
				    		<td><input type="text"  id="ragioneSociale2" name="ragioneSociale2" /></td>--%>
				    		<td><label for="contoContrattuale" id="labelContoContrattuale"><fmt:message key="elencoCondomini.aggiungiCondominio.contoContrattuale"/></label></td>
				    		<td><input type="text"  id="contoContrattuale" name="contoContrattuale" /></td>
				    	</tr>
				    	<tr>
				    		<td><label for="paese" id="labelPaese"><fmt:message key="elencoCondomini.aggiungiCondominio.paese" /></label></td>
				    		<td><input type="text"  id="paese" name="paese" /></td>
				    		<td><label for="provincia" id="labelProvincia"><fmt:message key="elencoCondomini.aggiungiCondominio.provincia" /></label></td>
				    		<td><input type="text"  id="provincia" name="provincia" /></td>
				    		<td><label for="comune" id="labelComune"><fmt:message key="elencoCondomini.aggiungiCondominio.comune" /></label></td>
				    		<td><input type="text"  id="comune" name="comune" /></td>
				    	</tr>
				    	<tr>
				    		<td><label for="cap" id="labelCap"><fmt:message key="elencoCondomini.aggiungiCondominio.cap" /></label></td>
				    		<td><input type="text"  id="cap" name="cap" /></td>
				    		<td><label for="via" id="labelVia"><fmt:message key="elencoCondomini.aggiungiCondominio.via" /></label></td>
				    		<td><input type="text"  id="via" name="via" /></td>
				    		<td><label for="numeroCivico" id="labelNumeroCivico"><fmt:message key="elencoCondomini.aggiungiCondominio.numeroCivico" /></label></td>
				    		<td><input type="text"  id="numeroCivico" name="numeroCivico" /></td>
				    	</tr>
				    	</table>
				    	</div>
				    	<div class="pagination">
					        <input class="btn extralarge" type="button" id="bottoneAggiungiCondominioLista" value="<fmt:message key='elencoCondomini.aggiungiCondominio.bottoneAggiungiLista' />" />
					    	<input class="btn extralarge" type="reset" id="bottoneReset" value="<fmt:message key='elencoCondomini.aggiungiCondominio.bottoneReset' />" />
					    </div>
			    </form>
			    <br/> <br/>
<!--<form class="frm" id="formAggiungiCondomini" method="post" action="${url.base}${currentNode.path}.aggiungiCondomini.do">-->

<form class="frm" id="formAggiungiCondomini" method="post">
				    <div class="ctn-data pag">
					       	 <table id="tabellaAggiungiCondomini" class="tabella tablesorter">
					            <thead id="tabellaCondominiHeader" class="header">
					                <tr>
					                    <th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.seleziona"/></th>
					                    <th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.nome"/></th>
					                    <th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.indirizzo"/></th>
					                    <th class="colonna"><fmt:message key="elencoCondomini.tabellaCondomini.header.comune"/></th>
					                </tr>
					            </thead>
					            <tbody id="tabellaCondominiRighe">
					            </tbody>
					        </table>
					</div>
					<div class="pagination">
				        <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
				        <input class="btn extralarge btnprova" type="submit" id="bottoneAggiungiCondomini" value="<fmt:message key='elencoCondomini.aggiungiCondominio.bottoneConferma' />" />
				    </div>
    			</form>
			</div>
		</div>
	</div>
</div>

<div id="moduloRimuoviCondomini">
	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form class="frm" id="formRimuoviCondomini" method="post" action="${url.base}${currentNode.path}.rimuoviCondomini.do">
					<div class="ctn-data pag">
						<h3><fmt:message key='elencoCondomini.label.rimuovi.titolo' /></h3>
		    			<table id="tabellaRimuoviCondomini" border="0" cellpadding="0" cellspacing="0" >
		                <tr>
		                    <th class="check"><fmt:message key="elencoCondomini.tabellaCondomini.header.seleziona"/></th>
		                    <th><fmt:message key="elencoCondomini.tabellaCondomini.header.nome"/></th>
		                    <th><fmt:message key="elencoCondomini.tabellaCondomini.header.indirizzo"/></th>
		                    <th><fmt:message key="elencoCondomini.tabellaCondomini.header.comune"/></th>
		                </tr>
		                <c:forEach items="${elenco}" var="condominio" varStatus="numero" >
		                    <tr id="tabellaCondominiRiga${numero}}" class="riga">
		                        <td class="colonna selezione">
		                            <input type="checkbox" name="selezione" id ="selezione" value="${condominio.codiceBP}" />
		                        </td>
		                        <td class="colonna nome">${condominio.ragioneSociale1} ${condominio.ragioneSociale2}</td>
		                        <td class="colonna indirizzo">${condominio.via}, ${condominio.numeroCivico}</td>
		                        <td class="colonna comune">${condominio.comune}</td>
		                    </tr>
		                </c:forEach>
		       			</table>
		       		</div>
						<div class="pagination">
					        <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
					        <input class="btn extralarge" type="submit" id="bottoneRimuoviCondominio" value="<fmt:message key='elencoCondomini.tabellaRimuoviCondomini.bottoneConferma' />" />
					    </div>
    			</form>
    		</div>
    	</div>
    </div>
</div>



<script>
/*
$('.btnprova').click(function () {
	$( ".selezione" ).each(function( index ) {
		alert(JSON.stringify($(this)));
	});
});

	
*/

</script>
