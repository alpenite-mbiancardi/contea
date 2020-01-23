<%@page import="org.jahia.*"%>
<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@page import="org.jahia.services.mail.MailService"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<template:addResources type="javascript" resources="jquery.js,jquery.validate.js"/>

<script type="text/javascript">
 $(document).ready(function() {
            $("#confermaRecuperoPassword").validate({
                rules: 
                {
                	new_password: 
                	{
                        required: true,
                        minlength: 6
                    },
                    confirm_password: 
                    {
                        required: true,
                        minlength: 6,
                        equalTo: "#new_password"
                    }
                },
                messages: 
                {
                	confirm_password: 
                	{
                    	required: "<fmt:message key='recuperopassword.chiediPassword'/>",
                        minlength: "<fmt:message key='recuperopassword.minlength'/>",
                        equalTo: "<fmt:message key='recuperopassword.confermaPasswordEqualTo'/>"
                    },
                    new_password: 
                    {
                        required: "<fmt:message key='recuperopassword.chiediPassword'/>",
                        minlength: "<fmt:message key='recuperopassword.minlength'/>",                             
                    }
                }
            });
        });
 </script>
 
<div class="cambioPwd">
<div class="search">
	<h3>
		<fmt:message key="recuperopassword.titolo.modulo"/>
	</h3>
	<%-- get the user --%>
	<jcr:sql var="users" sql="SELECT * FROM [jnt:user] WHERE [j:nodename] = '${param['username']}'"/>
	<c:choose>
		<c:when test="${users.nodes.size != 1 }">
			Invalid request - Found: ${users.nodes.size} users
		</c:when>
		<c:otherwise>
			<div class="form">
				<form class="frm" method="post" id="confermaRecuperoPassword" name="confermaRecuperoPassword" action="<c:url value='${url.base}${currentNode.path}.confermaRecuperoPasswordAction.do'/>" >
   						<input type="hidden" name="userredirectpage" value="${currentNode.properties['userredirectpage'].string}" />
   						<input type="hidden" name="username" value="${param['username']}" />
   						<input type="hidden" name="codice" value="${param['codice']}" />
   						<input type="hidden" name="group" value="${currentNode.properties['group'].string}" />
   						<div class="passwords">
   						<div class="password1">
   							<span class="label"><label for="password"><fmt:message key='recuperopassword.label.nuovaPassword'/></label></span>
   							<span class="field"><input type="password" name="new_password" id="new_password"/></span>
   						</div>
   						<div class="password2">
   							<span class="label"><label for="password"><fmt:message key='recuperopassword.label.confermaPassword'/></label></span>
   							<span class="field"><input type="password" name="confirm_password" id="confirm_password"/></span>
   						</div>
   						</div>
   						<div class="button">
   							<input type="submit" class="btn medium" value="<fmt:message key='recuperopassword.submit'/>"/>
   						</div>
   					</form>
			</div>
		</c:otherwise>
	</c:choose>
</div>
</div>
