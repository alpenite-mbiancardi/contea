<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"        %>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions"   %>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr"            %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"         %>
<%@ taglib prefix="functions"uri="http://www.jahia.org/tags/functions"      %>
<%@ page   import="org.jahia.services.content.JCRNodeWrapper"               %>
<c:choose>
	<c:when test="${not empty param['servizio']}">
		<c:set var="type"       value="${param['servizio']}" />
		<c:set var="parameters" value="[type] = '${type}' AND "/>
	</c:when>
	<c:otherwise>
		<fmt:message var="type" key="jnt_alpeniteTeaModulisticaRss.tutti"/>
	</c:otherwise>
</c:choose>
<c:if test="${not empty param['lang']}">
	<c:set var="language" value="${param['lang']}" />
	<c:set var="parameters" value="${parameters} [jcr:language] = '${language}' AND "/>
	<%-- <fmt:setLocale value="${language}" scope="session"/> --%>
</c:if>
<c:choose>
	<c:when test="${not empty param['num']}">
		<c:set var="end" value="${param['num']}"/>
	</c:when>
</c:choose>
<c:if test="${not (end gt 0)}">
	<c:set var="end" value="1000000"/>
</c:if>
<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
<c:set var="path" value="${fn:substringAfter(fn:substringBefore(currentNode.properties['contenitoreModuli'].node.url,'.html'),'sites')}"/>
	<channel>
		<title><fmt:message key="jnt_alpeniteTeaNewsRss.title"/></title>
		<link>http://contea.teaspa.it</link>
		<description><fmt:message key="jnt_alpeniteTeaNewsRss.description">
			<fmt:param value="${type}"/>
		</fmt:message></description>
		<category><fmt:message key="jnt_alpeniteTeaNewsRss.category"/></category>
		<language>${language}</language>
		<c:set var="query" value="SELECT * FROM [jnt:news] as documento WHERE ${parameters} ISDESCENDANTNODE(documento,'/sites${path}')	ORDER BY [jcr:lastModified] DESC"/>
		<jcr:sql var="listQuery" sql="${query}" />
		<c:forEach items="${listQuery.nodes}" var="news" begin="0" end="${end-1}" varStatus="counter">
			<item>
				<c:set var="host"             value="<%=request.getHeader(\"host\")%>" />
				<c:set var="documentUrl"      value="http://${host}${news.url}"        />
				<title>${news.properties['jcr:title'].string}</title>
				<link>${documentUrl}</link>
				<description>${functions:abbreviate(functions:removeHtmlTags(news.properties['desc'].string),150,200,'...')}</description>
				<c:set var="image"            value="${news.properties['image'].node}" />
				<c:if test="${not empty image}">
					<c:set var="imageUrl"         value="http://${host}${image.url}"       />
					<c:set var="documentResource" value="${jcr:getNodes(image,'jnt:resource')[0]}"/>
					<enclosure url="${imageUrl}" length="${documentResource.properties['jcr:data'].binary.size}" type="${documentResource.properties['jcr:mimeType'].string}"/>
				</c:if>
				<%-- <fmt:setLocale value="en" scope="request"/> --%>
				<pubDate><fmt:formatDate pattern="EEE, dd MMM yyyy HH:mm:ss zzz" value="${news.properties['date'].date.time}" /></pubDate>
				<%-- <c:if test="${not empty language}">
					<fmt:setLocale value="${language}" scope="session"/>
				</c:if> --%>
			</item>
		</c:forEach>
	</channel>
</rss>
