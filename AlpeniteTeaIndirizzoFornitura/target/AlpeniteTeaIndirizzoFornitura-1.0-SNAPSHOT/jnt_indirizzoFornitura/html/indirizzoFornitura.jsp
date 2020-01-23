<%-- 
    Document   : indirizzoFornitura
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="com.alpenite.tea.recapito.dati.Recapito"%>
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
<template:addResources resources="script.js" type="javascript" />

<%
try{
Recapito r = WSClient.getClient(session).getIndirizzoFornitura(
        request.getParameter("c"), 
        request.getParameter("cc")).getRitorno();
request.setAttribute("recapito", r);
}catch (Exception e) {
	
	Recapito r = new Recapito("","","","","","","","");
}

String re ="";
try{
 re = WSClient.getClient(session).getDettaglioContatto(
        request.getParameter("c"));
request.setAttribute("tipocontatto", re);
}catch (Exception e) {
	
	 re = "N";
	System.out.println("nessun contatto null eccezione");
}
%>

<style>

#modificaContatto {
	overflow:hidden;
}

</style>

<div id="infoPagina">
	<div class="ctn-data pag">
		<h3></h3>
		<table border="0" cellpadding="0" cellspacing="0" class="data">
			<tr>
				<th><span class="label"><fmt:message key="infoPagina.cc"/></span></th>
				<td><span class="value">${param['cc']}</span></td>
				<th><span class="label"><fmt:message key="infoPagina.c"/></span></th>
				<td><span class="value">${param['c']}</span></td>
			</tr>
			<tr>
				<th colspan="2"><span class="label"><fmt:message key="infoPagina.servizio"/></span></th>
				<td colspan="2"><span class="value">
				<c:choose>
					<c:when test="${param['servizio']=='C3'}">
						<fmt:message key="Tlr"/>
					</c:when>
					<c:when test="${param['servizio']=='H1'}">
						<fmt:message key="Acqua"/>
					</c:when>
					<c:when test="${param['servizio']=='A1'}">
						<fmt:message key="Rifiuti"/>
					</c:when>
					<c:when test="${param['servizio']=='G1'}">
						<fmt:message key="Gas"/>
					</c:when>
					<c:when test="${param['servizio']=='EE'}">
						<fmt:message key="Luce"/>
					</c:when>
					<c:when test="${param['servizio']=='E8'}">
						<fmt:message key="Energia"/>
					</c:when>
				</c:choose>
				</span></td>
				<c:if test="${param['servizio'] eq 'G1'}">
					<th><span class="label"><fmt:message key="infoPagina.pdr"/></span></th>
					<td><span class="value">${recapito.podCode}</span></td>
				</c:if>
				<c:if test="${param['servizio'] eq 'E8'}">
				<th><span class="label">POD</span></th>
				<td><span class="value">${recapito.podCode}</span></td>
			</c:if>
			</tr>
		</table>
	</div>
</div>
<div id="indirizzoFornitura">
	<div class="ctn-data pag">
		<h3></h3>
		<table border="0" cellpadding="0" cellspacing="0" class="data">
			<tr>
				<th><fmt:message key="indirizzoFornitura.label" />:</th>
				<td>${recapito.via}, ${recapito.numeroCivico},${recapito.extnumeroCivico}, ${recapito.cap}, ${recapito.comune}</td>
			</tr>
		</table>
	</div>
	<div class="pagination">
		<div class="paginationNavigation">
			<input type="button" class="btn extralarge" id="bottoneModificaIndirizzo" value="<fmt:message key='indirizzoFornitura.bottoneModifica' />" />
		</div>
	</div>
</div>

<div id="modificaIndirizzoFornitura">
	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form class="frm" action="${url.base}${currentNode.path}.modificaIndirizzo.do" id="formModificaIndirizzo">
					<div class="ctn-data pag">
						<h3><fmt:message key="modificaIndirizzoFornitura.label" /></h3>
						<table border="0" cellpadding="0" cellspacing="0" class="data">
							<tr>
								<th><label for="numeroCivicoFornitura" id="labelNumeroCivicoFornitura" ><fmt:message key="modificaIndirizzoFornitura.label" /></label></th>
								<td><input class="obbligatorio" type="text" id="numeroCivicoFornitura" name="numeroCivicoFornitura" value="${numeroCivico}" /></td>
								<th><label for="estensioneNumeroCivicoFornitura" id="labelestensioneNumeroCivicoFornitura" ><fmt:message key="modificaEstesioneIndirizzoFornitura.label" /></label></th>
								<td><input  type="text" id="estensioneNumeroCivicoFornitura" name="estensioneNumeroCivicoFornitura"  maxlength="10" value="${extNumeroCivico}" /></td>
							
							</tr>
						</table>
						<input type="hidden" id="cc" name="cc" value="${param['cc']}" />
						<input type="hidden" id="c" name="c" value="${param['c']}" />
						<input type="hidden" name="servizio" value="${param['servizio']}" />
						<input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
					</div>			
					<div class="pagination">
						<input type="submit"class="btn large" id="bottoneConfermaNumeroCivico" name="bottoneConfermaNumeroCivico" value="<fmt:message key="modificaIndirizzoFornitura.bottoneConferma" />" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<c:choose>
<c:when test="${param['servizio']=='H1'}">
<!-- modifica indirizzo fornitura -->

<div id="indirizzoFornitura">
<div class="ctn-data pag">
	<h3></h3>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<tr>
			<th><fmt:message key='indirizzoFornitura.titoloModificaContatto' />:</th>
			<td>
			<c:if test="${tipocontatto eq 'M'}">
			Mail
			</c:if>
			<c:if test="${tipocontatto eq 'S'}">
			SMS
			</c:if>
			<c:if test="${tipocontatto eq 'T'}">
			<fmt:message key='indirizzoFornitura.telefono' />
			</c:if>
			<c:if test="${tipocontatto eq 'N'}">
			<fmt:message key='indirizzoFornitura.nessuno' />
			</c:if>
			<c:if test="${empty tipocontatto}">
			<fmt:message key='indirizzoFornitura.nessuno' />
			</c:if>
			
		</tr>
	</table>
</div>

<div class="pagination">
	<div class="paginationNavigation">
		<input type="button" class="btn extralarge" id="bottoneModificaTipoContatto" value="<fmt:message key='indirizzoFornitura.bottoneModificaContatto' />" />
	</div>
</div>
</div>


<div id="modificaContatto">
<div id="overlay">	
	<div id="content">
		<div class="main">
			<form class="frm" action="${url.base}${currentNode.path}.tipoContatto.do" id="formModificaTipoContatto">
				<div class="ctn-data pag">
					<h3><fmt:message key='indirizzoFornitura.titoloModificaContatto' /></h3>
					<table border="0" cellpadding="0" cellspacing="0" class="data">
						<tr>
							<td>
							<%
							String checkedN = "";
							String checkedM = "";
							String checkedS = "";
							String checkedT = "";
							if(re.equals("N")) checkedN ="checked";
							
							else if(re.equals("M")) checkedM ="checked";
							else if(re.equals("S")) checkedS ="checked";
							else if(re.equals("T")) checkedT ="checked";
							else checkedN ="checked";
							%>
							
							
							
							
							<input type="radio" name="tipoContattoPref" value="N" <%=checkedN%>> <fmt:message key='indirizzoFornitura.nessuno' /><br>
							  <input type="radio" name="tipoContattoPref" value="M"  <%=checkedM%>> Mail<br>
							  <input type="radio" name="tipoContattoPref" value="S"  <%=checkedS%>> SMS<br>
							  <input type="radio" name="tipoContattoPref" value="T"  <%=checkedT%>> <fmt:message key='indirizzoFornitura.telefono' /><br><br>
							
							</td>
						
						
						</tr>
					</table>
					<input type="hidden" id="cc" name="cc" value="${param['cc']}" />
					<input type="hidden" id="c" name="c" value="${param['c']}" />
					<input type="hidden" name="servizio" value="${param['servizio']}" />
					<input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
				</div>			
				<div class="pagination">
					<input type="submit"class="btn large" id="bottoneConfermaTipoContatto" name="bottoneConfermaTipoContatto" value="<fmt:message key="modificaIndirizzoFornitura.bottoneConferma" />" />
				</div>
			</form>
		</div>
	</div>
</div>
</div>
<!-- fine modifica indirizzo fornitura -->

</c:when>
</c:choose>