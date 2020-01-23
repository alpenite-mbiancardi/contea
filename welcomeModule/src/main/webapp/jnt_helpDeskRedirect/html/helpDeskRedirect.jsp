<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
 <%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>

<jcr:sql var="isHelpdesk" sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/sites/contea/groups/${currentNode.properties['helpDeskGroup'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}' "/>

<!-- help desk forward -->
<!-- sql = SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/sites/contea/groups/${currentNode.properties['helpDeskGroup'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}' " -->
<c:if test="${isHelpdesk.nodes.size != 0 }">
	<!--  help desk -->
	<c:if test="${empty sessionScope['UTINFSESSION']}">
		<%-- call the javascript to forward the right page --%>
		<!--  redirect - window.location -->
		<!--  ${url.base} - ${currentNode.properties['helpDeskPageForward'].node.path} -->
			<script type="text/javascript">
				window.location = "${url.base}${currentNode.properties['helpDeskPageForward'].node.path}.html";
			</script>
	</c:if>
</c:if>