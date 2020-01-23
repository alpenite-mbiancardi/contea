<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
<c:when test="${renderContext.editMode}">
	Confirm Registration Module
</c:when>
<c:otherwise>
<jsp:forward page="${url.base}${currentNode.path}.confirmRegistration.do">
	<jsp:param value="${param['username']}" name="username"/>
	<jsp:param value="${param['codice']}" name="codice"/>
	<jsp:param value="${param['mail']}" name="mail"/>
	
	<jsp:param value="${currentNode.properties['from'].string}" name="from"/>
	<jsp:param value="${currentNode.properties['cc'].string}" name="cc"/>
	<jsp:param value="${currentNode.properties['bcc'].string}" name="bcc"/>
	<jsp:param value="${currentNode.properties['subject'].string}" name="subject"/>
	<jsp:param value="${currentNode.properties['mailContent'].string}" name="mailContent"/>
	<jsp:param value="${currentNode.properties['backOffice'].string}" name="backOffice"/>
	
	<jsp:param value="${currentNode.properties['userRedirectPage'].string}" name="redirectPage"/>

</jsp:forward>
</c:otherwise>
</c:choose>