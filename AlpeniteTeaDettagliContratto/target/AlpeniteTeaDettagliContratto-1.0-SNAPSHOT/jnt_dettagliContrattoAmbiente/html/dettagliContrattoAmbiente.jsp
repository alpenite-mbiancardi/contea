<%-- 
    Document   : dettagliContrattoGas
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="com.alpenite.tea.dettagliContratto.dati.DettagliContrattoAmbiente"%>
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
<%try{
request.setAttribute("dettagliContratto", 
        WSClient.getClient(session).getDettagliContrattoAmbiente(
            request.getParameter("c"), 
            request.getParameter("cc")).getRitorno());}
catch (Exception e){

	//;
}
%>
<div id="dettagliContrattoAmbiente" class="dettagliContratto">
	<div class="ctn-data pag">
		<h3><fmt:message key="dettagliContratto.ambiente.header" /></h3>
		<table border="0" cellpadding="0" cellspacing="0" class="data">
			<tr>
				<th><fmt:message key="dettagliContratto.dataStipula" /></th><td><fmt:formatDate value="${dettagliContratto.dataStipula}" pattern="dd-mm-yyyy"/></td>
				<c:if test="${not empty dettagliContratto.dataCessazione}">
					<th><fmt:message key="dettagliContratto.dataCessazione" /></th><td><fmt:formatDate value="${dettagliContratto.dataCessazione}" pattern="dd-mm-yyyy"/></td>
				</c:if>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.tipologiaUso" /></th><td>${dettagliContratto.tipologiaUso}</td>
				<th><fmt:message key="dettagliContratto.opzioneTariffaria" /></th><td>${dettagliContratto.opzioneTariffaria}</td>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.qualificaTitolare" /></th><td>${dettagliContratto.qualificaTitolare}</td>
				<th><fmt:message key="dettagliContratto.superficie" /></th><td><fmt:formatNumber pattern="#,##0" var="sup" value="${dettagliContratto.superficie}" />${fn:replace(sup, ",", ".")}</td>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.numeroPersone" /></th><td>
				 <c:choose>
                 <c:when test="${dettagliContratto.numeroPersone != -1}">
                         <fmt:formatNumber pattern="#,##0" var="per" value="${dettagliContratto.numeroPersone}" />${fn:replace(per, ",", ".")}
                 </c:when>
                 <c:otherwise>
                         --
                 </c:otherwise>
                 </c:choose>
				</td>
				<th><fmt:message key="dettagliContratto.attivita" /></th><td>${dettagliContratto.attivita}</td>
			</tr>
			<tr>
				<th><fmt:message key="dettagliContratto.riduzioni" /></th>
				<td>
					<ul>
						<c:forEach items="${dettagliContratto.riduzioni}" var="riduzione">
							<li>${riduzione}</li>
						</c:forEach>
					</ul>
				</td>
				<th>&nbsp;</th><td>&nbsp;</td>
			</tr>
		</table>
	</div>
</div>