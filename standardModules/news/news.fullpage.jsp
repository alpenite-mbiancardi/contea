<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>

<jcr:nodeProperty node="${currentNode}" name="jcr:title" var="newsTitle"/>
<jcr:nodeProperty node="${currentNode}" name="desc" var="newsDesc"/>
<c:set var="newsTitleEscaped" value="${not empty newsTitle ? fn:escapeXml(newsTitle.string) : ''}"/> 
<jcr:nodeProperty node="${currentNode}" name="date" var="newsDate"/>
<jcr:nodeProperty node="${currentNode}" name="image" var="newsImage"/>
<jcr:nodeProperty node="${currentNode}" name="jcr:title" var="newsTitle"/>
<jcr:nodeProperty node="${currentNode}" name="type" var="newsType"/>

<div class="title">
	<h1><fmt:message key="news_page_title" /></h1>
</div>   
<div class="txt">
	<p><fmt:message key="news_page_subtitle" /></p>
</div>
<div class="pagination">
	<div class="paginationNavigation">
	<c:if test="${!empty jcr:findDisplayableNode(renderContext.mainResource.node.parent, renderContext)}">
		<c:url value='${url.base}${jcr:findDisplayableNode(renderContext.mainResource.node.parent, renderContext).path}.html' var="action"/>
    </c:if>
    <c:if test="${empty jcr:findDisplayableNode(renderContext.mainResource.node.parent, renderContext)}">
        <c:set var="action">javascript:history.back()</c:set>
    </c:if>
		<a class="prevLink" id="news-page-back" href="${action}"><fmt:message key="news-back" /></a>
	</div>
</div>     
<div class="title-news" id="${newsType.string}">
	<div class="newsListItem">
		<p class="newsInfo"><span class="newsDate">
		<fmt:formatDate value="${newsDate.date.time}" pattern="dd/MM/yyyy"/>&nbsp;<fmt:formatDate
                    value="${newsDate.date.time}" pattern="HH:mm" var="dateTimeNews"/>
                <c:if test="${dateTimeNews != '00:00'}">${dateTimeNews}</c:if>
		</p>
		<h3><fmt:message key="${newsType.string}" /></h3>
		<h2>${newsTitleEscaped}</h2>
	</div>
</div>
<div class="txt line">                   		
	<p>
		${newsDesc.string}
	</p>
</div>
</div> 
<div class="clear"></div>
