<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<template:addResources type="css" resources="publication.css"/>
<jcr:nodeProperty var="file" node="${currentNode}" name="file"/>
<jcr:nodeProperty var="preview" node="${currentNode}" name="preview"/>

<a href="${file.node.url}" class="download">Scarica</a>
<h3><c:if test="${!empty currentNode.properties.title.string}">${currentNode.properties.title.string}</c:if></h3>
<c:if test="${!empty currentNode.properties.text.string}">${currentNode.properties.text.string}</c:if>
<fmt:formatNumber var="num" pattern="### ### ###.##" type="number" value="${(file.node.fileContent.contentLength/1024)}"/>
<span>(${num} KB)</span>

