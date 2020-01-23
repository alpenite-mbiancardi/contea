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

<script>
urlLocationDownloadMandato = '/scaricaModulo.jsp?cc=${param["cc"]}&isRevoke=false';
urlLocationUploadMandato = '${url.base}${currentNode.properties.paginaAggiungiMandato.node.path}.html?cc=${param["cc"]}&isRevoke=false';
urlLocationDownloadRevoca = "/scaricaModulo.jsp?cc=${param['cc']}&isRevoke=true";
urlLocationUploadRevoca =  "${url.base}${currentNode.properties.paginaRichiediRevoca.node.path}.html?cc=${param['cc']}&isRevoke=true";
</script>

<%
JCRNodeWrapper nodo = (JCRNodeWrapper)request.getAttribute("currentNode");
if(request.getParameter("cc") != null){
	request.setAttribute("zs09", WSClient.getClient(session).checkMandatoActive(request.getParameter("cc"), "", "X", "").getCodice().equals("00")?"true":"false");
	request.setAttribute("zs10", WSClient.getClient(session).checkMandatoActive(request.getParameter("cc"), "X", "", "").getCodice().equals("00")?"true":"false");
	request.setAttribute("zs09AndMnd", WSClient.getClient(session).checkMandatoActive(request.getParameter("cc"), "X", "", "X").getCodice().equals("00")?"true":"false");
}
%>

<div class="ctn-data pag">
	<div class="open"><h3><fmt:message key="aggiungiERevocaMandato.titleMandato" /></h3></div>
		
	<div class="txt">
		<p>${currentNode.properties.testoMandato.string}<p>
	</div>

</div>

<div class="pagination">
	<div class="paginationNavigation">
		<input id="downloadMandato" type="button" class="btn extralarge left" value="<fmt:message key='aggiungiERevocaMandato.downloadMandato' />" />
		<c:choose>
		<c:when test ="${zs09 eq true}">
			<input id="uploadMandato" type="button" class="btn extralarge right" value="<fmt:message key='aggiungiERevocaMandato.inviaMandato' />" />
		</c:when>
		<c:otherwise>
			<input id="uploadMandato" type="button" class="btn extralarge right disable" value="<fmt:message key='aggiungiERevocaMandato.inviaMandato'/>" disabled />
		</c:otherwise>
		</c:choose>
		
	</div>
</div>



<div style="clear: both;height: 10px "></div>

<div class="ctn-data pag">
	<div class="open"><h3><fmt:message key="aggiungiERevocaMandato.titleRevoca" /></h3></div>
		
	<div class="txt">
		${currentNode.properties.testoRevoca.string}
	</div>
</div>

<div class="pagination">
	<div class="paginationNavigation">
		<c:choose>
		<c:when test ="${zs10 eq true}">
			<input id="downloadRevoca" type="button" class="btn extralarge left" value="<fmt:message key='aggiungiERevocaMandato.downloadRevoca' />" />
		</c:when>
		<c:otherwise>
			<input id="downloadRevoca" type="button" class="btn extralarge left disable" value="<fmt:message key='aggiungiERevocaMandato.downloadRevoca' />" disabled />
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test ="${zs09AndMnd eq true}">
			<input id="uploadRevoca" type="button" class="btn extralarge right" value="<fmt:message key='aggiungiERevocaMandato.inviaRevoca' />" />
		</c:when>
		<c:otherwise>
			<input id="uploadRevoca" type="button" class="btn extralarge right disable" value="<fmt:message key='aggiungiERevocaMandato.inviaRevoca'  />" disabled />
		</c:otherwise>
		</c:choose>
		
	</div>
</div>