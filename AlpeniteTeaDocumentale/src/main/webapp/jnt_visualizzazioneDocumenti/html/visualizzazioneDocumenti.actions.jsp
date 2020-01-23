<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"     %>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr"         %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"      %>
<%@ taglib prefix="query"    uri="http://www.jahia.org/tags/queryLib"    %>
<%@ taglib prefix="utility"  uri="http://www.jahia.org/tags/utilityLib"  %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>

<template:addResources type="css"        resources="tea.css"               />
<template:addResources type="javascript" resources="visualizzaDocumenti.js"/>

<c:if test="${renderContext.editMode}">	
	jnt_visualizzazioneDocumenti.action		
</c:if> 
<script type="text/javascript">
    var baseUrl = '<c:url value="${url.base}${currentNode.path}"/>';
</script>

<div class="actions">
	<c:forEach var="child" items="${jcr:getNodes(currentNode,'jnt:bottoniVisualizzazioneDocumenti')}">		
		<template:module node="${child}"  view="default"/>
 	</c:forEach>
	<input class="btn medium" id="scaricaSelezionati" type="submit" value="<fmt:message key="alpeniteteadocumentale.visualizza.scaricaSelezionati"/>" onclick="esegui('documentaleDownloadDocumenti')"/>
</div>	
