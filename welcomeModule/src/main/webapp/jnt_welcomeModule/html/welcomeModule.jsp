<%@page import="org.jahia.services.content.JCRValueWrapperImpl"%>
<%@page language="java" session="true" %>
<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@page import="org.jahia.services.content.JCRSessionWrapper"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>


<c:choose>
<c:when test="${renderContext.loggedIn and currentAliasUser.username != 'guest'}">
	
	<c:if test="${empty sessionScope['userName'] || empty sessionScope['userSurname']}">
		<jcr:sql var="isAdmincond"
		sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/groups/${currentNode.properties['groupAmmCond'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}'  "/>
		
		<jcr:sql var="isHelpdesk"
		sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/groups/${currentNode.properties['groupHelpDesk'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}' "/> 
		
		<jcr:sql var="users" sql="SELECT * FROM [jnt:user] WHERE [jcr:uuid]='${currentUser.identifier}'" />
		<c:forEach items="${users.nodes}" var="u">
			<c:set var="user" value="${u}" />
		</c:forEach>
		
		<jcr:nodeProperty name="j:firstName" node="${user}" var="userName" scope="session"/>
		<jcr:nodeProperty name="j:lastName" node ="${user}" var="userSurname" scope="session" />
	</c:if>
	
	
<div class="log">
		<span><fmt:message key="text"/>&nbsp;${sessionScope['userName'].string}&nbsp;${sessionScope['userSurname'].string}</span>
		 <form action="/cms/logout" method="post" id="logoutForm">
                <input type="hidden" name="redirectActive" value="true" />
                <input type="hidden" name="redirect" value="${url.base}" />
        </form>
		<a href="#" onclick="$('#logoutForm').submit()" class="arrow"><fmt:message key="logout" /></a>
	
</div>

	<%--
	<c:if test="${not empty sessionScope['UINFSESSION']}">
		<div id="infoBP">
			${sessionScope['UINFSESSION']}
		</div>
	</c:if>
	 --%> 
</c:when>
<c:otherwise>
<div class="nolog"></div>
</c:otherwise>
</c:choose>     
