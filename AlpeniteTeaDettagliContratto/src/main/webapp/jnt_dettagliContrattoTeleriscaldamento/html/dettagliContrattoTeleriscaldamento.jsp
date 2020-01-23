<%-- 
    Document   : dettagliContrattoGas
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
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
<template:addResources resources="style.css" type="css" />
<%
request.setAttribute("dettagliContratto", 
        WSClient.getClient(session).getDettagliContrattoTeleriscaldamento(
            request.getParameter("c"), 
            request.getParameter("cc")).getRitorno());
%>
<div id="dettagliContrattoTeleriscaldamento" class="dettagliContratto">
	<div class="ctn-data pag">
		<h3><fmt:message key="dettagliContratto.teleriscaldamento.header" /></h3>
		<table border="0" cellpadding="0" cellspacing="0" class="data">
			<tr>
				<th><fmt:message key="dettagliContratto.dataStipula" /></th><td><fmt:formatDate value="${dettagliContratto.dataStipula}" pattern="dd-mm-yyyy"/></td>
				<c:if test="${not empty dettagliContratto.dataCessazione}">
					<th><fmt:message key="dettagliContratto.dataCessazione" /></th><td><fmt:formatDate value="${dettagliContratto.dataCessazione}" pattern="dd-mm-yyyy"/></td>
				</c:if>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.tipologiaUso" /></th><td>${dettagliContratto.tipologiaUso}</td>
				<th><fmt:message key="dettagliContratto.categoriaUso" /></th><td>${dettagliContratto.categoriaUso}</td>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.opzioneTariffaria" /></th><td>${dettagliContratto.opzioneTariffaria}</td>
				<th><fmt:message key="dettagliContratto.qualificaTitolare" /></th><td>${dettagliContratto.qualificaTitolare}</td>
			</tr>
			<tr>	
				<th><fmt:message key="dettagliContratto.volumetria" /></th><td><fmt:formatNumber pattern="#,##0" var="sup" value="${dettagliContratto.volumetria}" />${fn:replace(sup, ",", ".")}</td>
				<th><fmt:message key="dettagliContratto.coefficienteUso" /></th><td>${dettagliContratto.coefficienteUso}<br/>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.portataContrattuale" /></th><td>${dettagliContratto.portataContrattuale}</td>
				<th><fmt:message key="dettagliContratto.iva" /></th><td>${dettagliContratto.iva}</td>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.riscaldamento" /></th><td>${dettagliContratto.riscaldamento}</td>
				<th><fmt:message key="dettagliContratto.raffrescamento" /></th><td>${dettagliContratto.raffrescamento}</td>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.acquaSanitaria" /></th><td colspan="3">${dettagliContratto.acquaSanitaria}</td>
			</tr>
		</table>
	</div>
</div>