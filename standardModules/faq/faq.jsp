<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not renderContext.editMode}">
<template:addResources type="javascript" resources="res/jquery.cycle.all.js"/>
<template:addResources type="javascript" resources="res/common.js"/>
</c:if>

<!-- default jahia faq -->
<!--h4 class="faqQuestion">${currentNode.properties.question.string}</h4-->
<!--div class="faqAnswer">${currentNode.properties.answer.string}</div-->

<!-- alpenite faq -->
<a href="#1" class="tab">${currentNode.properties.question.string}</a>
<div class="ctn">${currentNode.properties.answer.string}</div>