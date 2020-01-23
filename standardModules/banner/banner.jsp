<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>

<%--
<c:if test="${not renderContext.editMode}">
<template:addResources type="javascript" resources="res/jquery.cycle.all.js"/>
<template:addResources type="javascript" resources="res/common.js"/>
</c:if>
--%>

<jcr:nodeProperty node="${currentNode}" name="background" var="background"/>
<template:addCacheDependency node="${background.node}"/>
<div id="banner" style="background:transparent url(${background.node.url}) no-repeat top left;">
    <div class="banner-text"
         style='margin-top:${currentNode.properties.positionTop.string}px; margin-left:${currentNode.properties.positionLeft.string}px'>
        ${currentNode.properties.cast.string}
        <div class="clear"></div>
    </div>
</div>
