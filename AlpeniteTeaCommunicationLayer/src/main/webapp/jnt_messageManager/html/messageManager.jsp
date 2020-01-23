<%-- 
    Document   : messageManager
    Created on : 29-ott-2012, 12.20.01
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.communicationLayer.WSReturnManager"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:addResources resources="scriptMessaggi.js" type="javascript" />
<template:addResources resources="jquery-ui.css" type="css" />
<%
// prelevo tutti i messaggi presenti
    request.setAttribute("chiaviMessaggi",
            WSReturnManager.getMessageKeys(session));
    request.setAttribute("messaggiWebServices",
            WSReturnManager.getWebServiceMessage(session));
%>

<template:addResources type="inlinejavascript">
<script type="text/javascript">
	function redirect(){
		window.location = "/";
	}
</script>
</template:addResources>

<c:if test="${renderContext.editMode}">
	Message Module
</c:if>

<c:if test="${chiaviMessaggi != null || (currentNode.properties['visualizzaMessaggiWs'].string=='true' && messaggiWebServices != null)}" >
    <c:if test="${currentNode.properties['popup'].string=='true'}">
        <c:set var="popup" value="popup"/>
    </c:if>

			    <div id="messaggi" class="${popup}">
			    
			        <c:forEach items="${chiaviMessaggi}" var="messaggio" varStatus="count">
			            <div id="messaggio${count}" class="messaggio">
			            
			     
			            <%
			            // differenza messaggio alert per richiesta Simona Borgonovi su messaggi di errore autoletture nel caso di Contratto Acqua (Marzio Biancardi)
			            %>
			            
			            <c:choose>
			            	<c:when test="${param.isAcqua eq true }">
			            		<fmt:message key="${messaggio}1" />
			            	</c:when>
			            	<c:otherwise>
			            		<fmt:message key="${messaggio}" />
			            	</c:otherwise>
			            </c:choose>
			                <c:if test="${messaggio eq 'org.jahia.userregistration.NewUser_P1' }">
			                	<c:set var="successReg" value="true" />
			                </c:if>
			            </div>
			        </c:forEach>
			        <c:if test="${currentNode.properties['visualizzaMessaggiWs'].string=='true'}">
			            <c:forEach items="${messaggiWebServices}" var="messaggio" varStatus="count">
			                <div id="messaggioWS${count}" class="messaggio">${messaggio}</div>
			            </c:forEach>        
			        </c:if>
			        <c:if test="${currentNode.properties['popup'].string=='true'}">
			            <c:set var="redirect" value="" />
			            <c:if test="${successReg eq 'true' }">
			            	<c:set var="redirect" value="onclick='redirect()'"/>
			            </c:if>
			            <input type="button" class="btn large" ${redirect} id="bottoneConferma" value="<fmt:message key="messaggi.bottoneConferma" />" />
			        </c:if>	
			        </div>

</c:if>
<%--
<c:if test="${empty successReg || currentNode.properties['successRegistrationInSpacificPage'].string = 'false' || currentNode.url eq currentNode.properties['specificPage'].url }">
 --%>
<%
    WSReturnManager.resetMessages(session);
%>
<%--
</c:if>
 --%>