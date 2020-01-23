<%@ page   import="com.alpenite.gui.components.MultiSelectComuni"       %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr"             %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"          %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"           %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib"  %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"     %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib"      %>
<!-- Messaggi: modulo in middleware, che visualizza messaggi in sessione, no ajax -->
<%
	MultiSelectComuni msc = new MultiSelectComuni("","",null);
	request.setAttribute("mappaComuni", msc.getMappaValori());
%>
<tr class="${currentNode.properties['societa'].string}" id="${currentNode.properties['jcr:uuid'].string}">
	<td class="society ${currentNode.properties['societa'].string}">
	</td>
	<td class="document">
		<c:forEach items="${currentNode.nodes}" var="documento" begin="0" end="1">  
			<div class="scarica">
				<template:module node="${documento}" view="linkExternal" /> 
			</div>
		</c:forEach>  
	</td>
	<%--20150413 cbattagliese: il tipo documento non è più un linkato per id ma per weakreference --%>
	<%-- <td class="documentType">
		<jcr:sql var="tipiDocumento" sql="SELECT * FROM [jnt:tipoDocumento] WHERE [id] = '${currentNode.properties['tipoDocumento'].string}'"/>
		<c:forEach var="tD" items="${tipiDocumento.nodes}">
			<c:set var="tipoDocumento" value="${tD}"/>
		</c:forEach>
		${tipoDocumento.properties['name'].string}
	</td> --%>
	<td class="documentType">
		${currentNode.properties['tipoDocumento'].node.properties['name'].string}
	</td>
	<%--20150413 cbattagliese: fine --%>
	<c:forEach var="idComune" items="${fn:split(currentNode.properties['comune'].string,',')}" begin="0" end="0">
		<c:set var="primoComune" value="${mappaComuni[idComune]}"/>
		<c:set var="listaComuni" value="${mappaComuni[idComune]}"/>
	</c:forEach>
	<c:forEach var="idComune" items="${fn:split(currentNode.properties['comune'].string,',')}" begin="1">
		<c:set var="listaComuni" value="${listaComuni}, ${mappaComuni[idComune]}"/>
	</c:forEach>
	<c:choose>
		<c:when test="${primoComune eq listaComuni}">
			<td class="comune">
				${primoComune}
			</td>
		</c:when>
		<c:otherwise>
			<td class="comune" title="${listaComuni}">
				${primoComune}...
			</td>
		</c:otherwise>
	</c:choose>
	<td class="date">
		<fmt:formatDate var="modDate" pattern="dd-MM-yyyy" type="date" value="${currentNode.properties['jcr:lastModified'].time}"/>
		<c:out value="${modDate}" />
	</td>
	<td class="checkBox">
		<input type="checkbox" name="id" class="selezioneDocumento" value="${currentNode.properties['id'].string}">
	</td>
</tr>