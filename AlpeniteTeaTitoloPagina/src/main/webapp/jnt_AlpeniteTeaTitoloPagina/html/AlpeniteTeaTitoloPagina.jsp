<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
 
<jcr:nodeProperty node="${currentNode}" name="tag" var="tagSubtitle"/>

<div class="title">
	<h1>${currentNode.properties['titolo'].string}</h1>
	<c:choose>
		<c:when test="${tagSubtitle.string == 'h2'}">
		   <h2>${currentNode.properties['sottotitolo'].string}</h2>
		</c:when>
		<c:when test="${tagSubtitle.string == 'p'}">
			<p>${currentNode.properties['sottotitolo'].string}</p>
		</c:when>
	</c:choose>
</div>
		