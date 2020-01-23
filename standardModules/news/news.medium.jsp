<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>

 <jcr:nodeProperty node="${currentNode}" name="jcr:title" var="newsTitle"/>
 <c:set var="newsTitleEscaped" value="${not empty newsTitle ? fn:escapeXml(newsTitle.string) : ''}"/> 
 <jcr:nodeProperty node="${currentNode}" name="date" var="newsDate"/>
 <jcr:nodeProperty node="${currentNode}" name="desc" var="newsDesc"/>
 <jcr:nodeProperty node="${currentNode}" name="image" var="newsImage"/>
 <jcr:nodeProperty node="${currentNode}" name="type" var="newsType"/>
 
<div class="newsListItem">
	<c:if test="${not empty newsImage}">
		<c:url value="${url.files}${newsImage.node.path}" var="imageUrl"/>
		<div class="newsSummaryImg"><a href="<c:url value='${url.base}${currentNode.path}.html'/>"><img src="${imageUrl}" alt="${newsTitleEscaped}"/></a></div>
    </c:if>
	<p class="newsInfo">
		<span class="newsDate">
			<fmt:formatDate value="${newsDate.time}" pattern="dd/MM/yyyy"/>
		</span>
	</p>
	<h4><fmt:message key="${newsType.string}" /></h4>
	<p class="newsResume"><a href="<c:url value='${url.base}${currentNode.path}.html'/>">${fn:substring(functions:removeHtmlTags(newsDesc.string),0,75)}</a></p>
</div>