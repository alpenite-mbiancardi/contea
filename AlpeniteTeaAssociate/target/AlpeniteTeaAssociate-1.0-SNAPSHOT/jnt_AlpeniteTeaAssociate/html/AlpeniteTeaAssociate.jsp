<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jcr:nodeProperty node="${currentNode}" name="immagine" var="image"/>

<div class="int">
	<a href="#"><img src="${image.node.url}" alt="${image.node.url}" height="90" width="125"/></a>
	<h3>${currentNode.properties['titolo'].string}</h3>
	<p>${currentNode.properties['testo'].string }</p>
	<c:choose>
	<c:when test="${not empty currentNode.properties['link']}">
		<a class="link left" href="${currentNode.properties['link'].node.url}">${currentNode.properties['testoLink'].string}</a>
	</c:when>
	<c:otherwise>
		<a class="link left" href="${currentNode.properties['externalLink'].string}">${currentNode.properties['testoLink'].string}</a>
	</c:otherwise>
	</c:choose>
</div>
