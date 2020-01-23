<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>

<template:addResources type="inlinejavascript">
    <script type="text/javascript">
    function setLangCustom(lang){
                $.ajax({
                        url: '${url.base}${currentNode.path}.changeLang.do',
                        type: "POST",
                        data: {"lang" : lang},
                        dataType: "json"
                });
    }
</script>
</template:addResources>

<div class="lang">
<ui:initLangBarAttributes activeLanguagesOnly="${renderContext.liveMode}"/>
<c:set var="linkKind" value="${currentNode.properties.typeOfDisplay.string}"/>
<ui:displayLanguageSwitchLink languageCode="${currentResource.locale}" display="false" urlVar="switchUrl" var="renderedLanguage" linkKind="${currentNode.properties.typeOfDisplay.string}"/>
<em>${renderedLanguage}</em>
<c:choose>
	<c:when test="${currentResource.locale eq 'en'}">
		<ui:displayLanguageSwitchLink languageCode="it" display="false" urlVar="switchUrl" var="renderedLanguage" linkKind="${currentNode.properties.typeOfDisplay.string}"/>
		<a onclick="setLangCustom('it')" href="<c:url context='/' value='${switchUrl}?${pageContext.request.queryString}'/>" class="arrow">Ita</a>
	</c:when>
	
	<c:otherwise>
		<ui:displayLanguageSwitchLink languageCode="en" display="false" urlVar="switchUrl" var="renderedLanguage" linkKind="${currentNode.properties.typeOfDisplay.string}"/>
		<a onclick="setLangCustom('en')" href="<c:url context='/' value='${switchUrl}?${pageContext.request.queryString}'/>" class="arrow">Eng</a>
	</c:otherwise>
</c:choose>

<%--
<c:forEach items="${requestScope.languageCodes}" var="language">
	<ui:displayLanguageSwitchLink languageCode="${language}" display="false" urlVar="switchUrl" var="renderedLanguage" linkKind="${currentNode.properties.typeOfDisplay.string}"/>
	<c:choose>
	    <c:when test="${language eq currentResource.locale}">
	        <em>${renderedLanguage}</em>
	    </c:when>
	    <c:otherwise>
	        <a onclick="setLangCustom(${language})" href="<c:url context='/' value='${switchUrl}'/>" class="arrow">${renderedLanguage}</a>
	    </c:otherwise>
	</c:choose>
</c:forEach>
 --%>
 
</div>

