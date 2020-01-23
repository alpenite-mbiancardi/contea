<%-- 
    Document   : domiciliazioneBancaria
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
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

<c:if test="${param['servizio'] != 'EE'}">

<%
JCRNodeWrapper nodo = (JCRNodeWrapper)request.getAttribute("currentNode");

String tipoBP = "";
if(nodo != null && nodo.getUser() != null) tipoBP = nodo.getUser().getProperty("j:tipoBP");
request.setAttribute(
        "domiciliazioneBancaria", 
        WSClient.getClient(session).getDomiciliazioneBancaria(
            request.getParameter("cc")).getRitorno());

request.setAttribute(
        "datiAnagrafici",
        WSClient.getClient(session).getDatiAnagrafici(
            (tipoBP != null && tipoBP.contains("azienda")), Constants.getJahiaType(tipoBP)
            ).getRitorno());
%>

<!-- codice kora con inserimento dati -->

<c:url value="${url.base}${currentNode.properties['paginaModulo'].node.path}.html?cc=${param['cc']}" var="urlAction" />
<script>
    urlLocation = '${urlAction}';
</script>

<h2 class="data"><input id="modificaDomiciliazioneSEPA" type="button" class="btn medium" value="<fmt:message key='domiciliazioneBancaria.bottoneModifica' />" /><fmt:message key='domiciliazioneBancaria.titolo' /></h2>
<table border="0" cellpadding="0" cellspacing="0" class="data">
	 <c:if test="${not empty domiciliazioneBancaria.nomeBanca}">
	 <tr>
		<th colspan="2"><fmt:message key='domiciliazioneBancaria.modalita' /></th>
		<td colspan="2"><fmt:message key='domiciliazioneBancaria.titolo' /></td>
		<td colspan="2"><fmt:message key='domiciliazioneBancaria.mandato' /> </td>
		<td colspan="2"><c:if test="${not empty domiciliazioneBancaria.mandato}">${domiciliazioneBancaria.mandato}
		</c:if>
		</td>
		<td colspan="2">
		<c:if test="${not empty domiciliazioneBancaria.mandato && not empty domiciliazioneBancaria.lastDocumenType && not empty domiciliazioneBancaria.lastDocumentId &&  domiciliazioneBancaria.lastDocumentId != '00000000000000000000000000000000' &&  domiciliazioneBancaria.lastDocumentId != 'AAAAAAAAAAAAAAAAAAAAAA=='}">
		<a  href="/scaricaDocumento.jsp?n=${domiciliazioneBancaria.lastDocumentId}&t=${domiciliazioneBancaria.lastDocumenType}&name=${domiciliazioneBancaria.mandato}">
		<img src="/css/images/back_ico_pdf.gif"/> 
		</a></c:if> </td>
	</tr>
	<tr>
		<th colspan="2"><fmt:message key='domiciliazioneBancaria.banca' /></th>
		<td colspan="2">${domiciliazioneBancaria.nomeBanca}</td>
		<td colspan="2">IBAN:</td>
		<td colspan="2">${domiciliazioneBancaria.iban}</td>
	</tr>
	</c:if>
	<c:if test="${empty domiciliazioneBancaria.nomeBanca}">
	 <tr>
		<th colspan="4">${currentNode.properties.msgNoRID.string}</th>
	</tr>
	</c:if>
</table>


	
					
</c:if>					
