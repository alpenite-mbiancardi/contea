<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>

<%--
<template:include view="hidden.load"/>

<c:if test="${empty editable}">
    <c:set var="editable" value="false"/>
</c:if>

<ul class="download">
        <c:forEach items="${moduleMap.currentList}" var="child" varStatus="status">

                        <template:module path="${child.path}"/>

        </c:forEach>
        <template:module path="*"/>
</ul>
--%>
<template:include view="hidden.header"/>
<c:if test="${not empty moduleMap.currentList}">
    <ul class="download">
        <c:forEach items="${moduleMap.currentList}" var="subchild" begin="${moduleMap.begin}" end="${moduleMap.end}">
                <jcr:nodeProperty node="${subchild}" name="type" var="type"/>
            <li class="${type.string}">
                <template:module path="${subchild.path}"/>
            </li>
        </c:forEach>
        <c:if test="${functions:length(moduleMap.currentList) == 0 and not empty moduleMap.emptyListMessage}">
            ${moduleMap.emptyListMessage}
        </c:if>
    </ul>
</c:if>
<c:if test="${moduleMap.editable and renderContext.editMode && !resourceReadOnly}">
    <template:module path="*"/>
 </c:if>
<div class="clear"></div>
<template:include view="hidden.footer"/>
