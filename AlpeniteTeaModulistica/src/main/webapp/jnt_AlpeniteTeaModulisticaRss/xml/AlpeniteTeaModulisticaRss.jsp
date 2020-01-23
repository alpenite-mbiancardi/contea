<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"        %>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions"   %>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr"            %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"         %>
<c:choose>
	<c:when test="${not empty param['servizio']}">
		<c:set var="type"       value="${param['servizio']}" />
		<c:set var="parameters" value="[type] = '${type}' AND "/>
	</c:when>
	<c:otherwise>
		<fmt:message var="type" key="jnt_alpeniteTeaModulisticaRss.tutti"/>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty param['comune']}">
		<c:set var="comune"     value="${param['comune']}" />
		<c:set var="parameters" value="${parameters} [comune] = '${comune}' AND "/>
	</c:when>
	<c:otherwise>
		<fmt:message var="comune" key="jnt_alpeniteTeaModulisticaRss.tutti"/>
	</c:otherwise>
</c:choose>
<c:if test="${not empty param['lang']}">
	<c:set var="language"   value="${param['lang']}" />
	<c:set var="parameters" value="${parameters} [jcr:language] = '${language}' AND "/>
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
<rss version="2.0">
<c:set var="path" value="${fn:substringAfter(fn:substringBefore(currentNode.properties['contenitoreModuli'].node.url,'.html'),'sites')}"/>
	<channel>
		<title><fmt:message key="jnt_alpeniteTeaModulisticaRss.title"/></title>
		<link>http://contea.teaspa.it</link>
		<description><fmt:message key="jnt_alpeniteTeaModulisticaRss.description">
			<fmt:param value="${type}"/>
			<fmt:param value="${comune}"/>
		</fmt:message></description>
		<category><fmt:message key="jnt_alpeniteTeaModulisticaRss.category"/></category>
		<language>${language}</language>
		<c:set var="query" value="SELECT * FROM [jnt:AlpeniteTeaModulistica] as documento WHERE ${parameters} ISDESCENDANTNODE(documento,'/sites${path}')	ORDER BY [jcr:lastModified] DESC"/>
		<jcr:sql var="listQuery" sql="${query}" />
		<c:forEach items="${listQuery.nodes}" var="documento" begin="0" end="${end-1}" varStatus="counter">
			<item>
				<c:set var="host" value="<%=request.getHeader(\"host\")%>" />
				<c:set var="documentUrl" value="http://${host}${documento.properties['file'].node.url}"/>
				<title>${documento.properties['title'].string}</title>
				<c:if test="${not empty documento.properties['file'].node}">
				<c:set var="documentResource" value="${jcr:getNodes(documento.properties['file'].node,'jnt:resource')[0]}"/>
					<enclosure url="${documentUrl}" length="${documentResource.properties['jcr:data'].binary.size}" type="${documentResource.properties['jcr:mimeType'].string}" />
				</c:if>	
				<link>${documentUrl}</link>
			</item>
		</c:forEach>
	</channel>
</rss>