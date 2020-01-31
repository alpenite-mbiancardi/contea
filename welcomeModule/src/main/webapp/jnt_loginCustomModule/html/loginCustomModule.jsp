<%@page language="java" session="true" %>
<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@page import="org.jahia.services.content.JCRSessionWrapper"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<utility:setBundle basename="JahiaInternalResources"/>

<!--c:choose-->
<!--c:when test="${!renderContext.loggedIn || currentAliasUser.username eq 'guest'}"-->
<template:addResources type="inlinejavascript">
	<script type="text/javascript">
		function submitForm(){
			$("[name='loginForm']").submit()
		}
	</script>
</template:addResources>

<div id="area">
	<div class="container_16">
		<div class="grid_10">
			<div id="login">
				<h3><fmt:message key="login"/></h3>
				<p><fmt:message key="accediCredenziali"/></p>
				<ui:loginArea class="frm">
					<ui:isLoginError var="loginResult">
						<span class="error"><fmt:message bundle="JahiaInternalResources" key="${loginResult == 'account_locked' ? 'message.accountLocked' : 'message.invalidUsernamePassword'}"/></span>
					</ui:isLoginError>
					<fieldset>
						<label class="left" for="username"><fmt:message key="label.username"/></label>
						<input class="full" type="text" value="" tabindex="1" maxlength="250" name="username" id="username" onkeyup="this.value = this.value.toUpperCase();"/>
					</fieldset>
					<fieldset>
						<label class="left" for="password"><fmt:message key="label.password"/></label>
						<input class="full" type="password" tabindex="2" maxlength="250" name="password" id="password" />
					</fieldset>
					<input type="submit" value="<fmt:message key='loginButton'/>" class="btn xsmall" style="width:54px" />
				</ui:loginArea>
				<jcr:sql var="pages2" sql="select * from [jnt:page] where [jnt:page].[jcr:uuid] = '${currentNode.properties['paginaRecuperoPassword'].string}'"/>
				<c:forEach items="${pages2.nodes}" var="p2" >
					<c:set var="page2" value="${p2}" />
				</c:forEach>
				<jcr:nodeProperty name="j:fullpath" node="${page2}" var="path2"/>
				<c:url value="${url.base}${path2.string}.html" var="url2" />
				<a href="${url2}" class="link left"><fmt:message key="recuperaPassword" /></a>
				<jcr:sql var="pages1" sql="select * from [jnt:page] where [jnt:page].[jcr:uuid] = '${currentNode.properties['paginaRecuperoUtente'].string}'"/>
				<c:forEach items="${pages1.nodes}" var="p1" >
					<c:set var="page1" value="${p1}" />
				</c:forEach>
				<jcr:nodeProperty name="j:fullpath" node="${page1}" var="path1"/>
				<c:url value="${url.base}${path1.string}.html" var="url1" />
				<a href="${url1}" class="link left"><fmt:message key="recuperaUsername" /></a>
			</div>
		</div>
		<div class="grid_6">
			<div id="registration">
				<h3><fmt:message key="nonSeiRegistrato"/><br /><fmt:message key="cosaAspetti"/></h3>
				<p> ${currentNode.properties['testoRegistrazione'].string }</p>
				<jcr:sql var="pages" sql="select * from [jnt:page] where [jnt:page].[jcr:uuid] = '${currentNode.properties['paginaRegistrazione'].string}'"/>
				<c:forEach items="${pages.nodes}" var="p" >
					<c:set var="page" value="${p}" />
				</c:forEach>
				<jcr:nodeProperty name="j:fullpath" node="${page}" var="path"/>
				<c:url value="${url.base}${path.string}.html" var="url0" />
				<a href="${url0}" class="btn large"><fmt:message key="registrati"/></a>
			</div>
		</div>
	</div>
</div>
<!--/c:when-->
<!--c:otherwise-->
<!--c:if test="${renderContext.editMode}"-->
<!--fieldset>
<!--legend>${fn:escapeXml(currentNode.displayableName)}</legend-->
<!--/fieldset-->
<!--/c:if-->
<!--/c:otherwise-->
<!--/c:choose-->

<script>
	$( document ).ready(function() {
		$( "#username" ).keyup(function() {
			if ($(this).val() === 'ROOT' || $(this).val() === 'root'){
				$(this).val('root');
			}
		});
	});
</script>
