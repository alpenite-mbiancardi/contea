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

<!-- recuperer une liste d'items de type teasers et boucler dessus -->

<div class="carousel">
	<div id="slides-container">
		<c:forEach items="${moduleMap.currentList}" var="child" varStatus="status">
			<div class="slide">
				<template:module path="${child.path}"/>
			</div>
		</c:forEach>
		<template:module path="*"/>
	</div>
</div>
<a href="#" id="prev">Indietro</a>
<a href="#" id="next">Avanti</a> 