<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>


<template:include view="hidden.load"/>

<c:if test="${empty editable}">
    <c:set var="editable" value="false"/>
</c:if>

<h2 class="line">Bollette</h2>
<div class="txt">
	<p>Questa lista contiene tutte le letture del contatore effettuate negli ultimi 5 anni. puoi modificare l'arco temporale selezionando l'anno di interesse. </p>
</div>
<div class="tab">
	<ul>
		<li><a href="#1" class="item1 active">Bollette</a></li>
		<li><a href="#1" class="item2">Partite aperte</a></li>
		<li><a href="#1" class="item3">Estratto conto</a></li>
	</ul>
	<c:forEach items="${moduleMap.currentList}" var="child" varStatus="status">
		<template:module path="${child.path}"/>
	</c:forEach>
	<template:module path="*"/>
</div>