<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"        %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"         %>

<c:if test="${not empty param['src_properties(jnt:AlpeniteTeaModulistica).type.value']}">
	<c:set var="type" value="${param['src_properties(jnt:AlpeniteTeaModulistica).type.value']}" />  
</c:if>
<c:if test="${not empty param['src_properties(jnt:AlpeniteTeaModulistica).comune.value']}">
	<c:set var="comune" value="${param['src_properties(jnt:AlpeniteTeaModulistica).comune.value']}" />
</c:if>
<c:choose>
	<c:when test="${not empty param['src_languages.values']}">
		<c:set var="language" value="${param['src_languages.values']}" />
	</c:when>
	<c:otherwise>
		<c:set var="language" value="${lang}"/>
	</c:otherwise>
</c:choose>

<c:url var="url" value='${url.base}${currentNode.path}.xml?servizio=${type}&comune=${comune}&lang=${language}&num=10'/>
<a class="customRss" href="${url}">
	<img src="/modules/assets/css/res/img/rss-icon.gif" alt="<fmt:message key='com.alpenite.tea.modulistica.rss-alt-msg'/>">
</a>