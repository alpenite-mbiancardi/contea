<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:addResources resources="script.js" type="javascript" />
<template:addResources resources="jquery-ui.css" type="css" />
<template:addResources resources="style.css" type="css" />

<div id="datiUtentePortale">
<div class="ctn-data pag">
	<h3><fmt:message key="datiUtentePortale.title"/></h3>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<tr>
			<th><label for="username"><fmt:message key='datiUtentePortale.label.username' /></label></th>
			<td>${currentUser.name}</td>
		</tr>
		<jcr:sql var="users" sql="SELECT * FROM [jnt:user] WHERE [jcr:uuid] = '${currentUser.identifier}'"/>
		<c:forEach items="${users.nodes}" var="u">
			<c:set var="user" value="${u}" />
		</c:forEach>
		<jcr:nodeProperty name="j:email" node="${user}" var="mail"/>
		<tr>
			<th><label for="password"><fmt:message key='datiUtentePortale.label.mail' /></label></th>
			<td>${mail.string}</td>
		</tr>
	</table>                        
</div>
<div class="pagination">
	<div class="paginationNavigation">
		<input id="modificaDatiUtentePassword" class="btn large left" type="button" value="Modifica password" />
		<input id="modificaDatiUtenteEmail" class="btn large" type="button" value="Modifica email" />
	</div>
</div>
</div>




<div id="datiUtentePortaleEmail">
	
	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form method="post" id="datiUtenteFormEmail" action="<c:url value='${url.base}${currentNode.path}.modificaDatiUtentePortale.do'/>" name="datiUtenteFormEmail">
					<div class="ctn-data pag">
						<input type="hidden" name="username" value="${currentUser.name}" />
						<input type="hidden" name="oldMail" value="${mail.string}" />
						<input type="hidden" name="redirectPage" value="${currentNode.properties['redirectPage'].string}" />
						<input type="hidden" name="mailVerificationPage" value="${currentNode.properties['mailVerificationPage'].string}" />
						<c:if test="${not empty currentNode.properties['from']}">
						   <input type="hidden" name="from" value="${currentNode.properties['from'].string}"/>
						</c:if>
						<c:if test="${not empty currentNode.properties['cc']}">
						   <input type="hidden" name="cc" value="${currentNode.properties['cc'].string}"/>
						</c:if>
						<c:if test="${not empty currentNode.properties['bcc']}">
						   <input type="hidden" name="bcc" value="${currentNode.properties['bcc'].string}"/>
						</c:if>
						<c:if test="${not empty currentNode.properties['subject']}">
						   <input type="hidden" name="subject" value="${currentNode.properties['subject'].string}"/>
						</c:if>
						<c:choose>
						<c:when test="${not empty currentResource.locale}">
							<input type="hidden" name="lang" value="${currentResource.locale}" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="lang" value="${param.locale}" />
						</c:otherwise>
						</c:choose>
						<input type="hidden" name="currentNode" value="${currentNode.identifier}" />
						<h3><fmt:message key='datiUtentePortale.label.mail.titolo' /></h3>
						<table border="0" cellpadding="0" cellspacing="0" class="data">
							<tr>	
								<th><label for="email" id="labelmail"><fmt:message key='datiUtentePortale.label.mail' /></label></th>
								<td><input class="large" id="email" name="email" value="${mail.string}" type="text" /></td>
							</tr>
						</table>
					</div>
					<div class="pagination">						
							<input id="bottoneSaveEmail" class="btn large" type="submit" value="<fmt:message key='datiUtentePortale.label.save' />" />						
					</div>
				</form>
			</div>
		</div>	
	</div>	
	
</div>

<div class="clear"></div>

<div id="datiUtentePortalePassword">
	
	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form method="post" id="datiUtenteFormPassword" action="<c:url value='${url.base}${currentNode.path}.modificaDatiUtentePortale.do'/>" name="datiUtenteFormPassword">
					<div class="ctn-data pag">
						<input type="hidden" name="username" value="${currentUser.name}" />
						<input type="hidden" name="oldMail" value="${mail.string}" />
						<input type="hidden" name="redirectPage" value="${currentNode.properties['redirectPage'].string}" />
						<input type="hidden" name="mailVerificationPage" value="${currentNode.properties['mailVerificationPage'].string}" />
						<h3><fmt:message key='datiUtentePortale.label.passwordArea.titolo' /></h3>
						<table border="0" cellpadding="0" cellspacing="0" class="data">
							<tr>
								<th><label for="password" id="labelPassoword"><fmt:message key='datiUtentePortale.label.password' /></label></th>
								<td>
								<div class="ctn-info">
									<a href="#1" class="info">
										<fmt:message key="altBaloon"/>
									</a>
									<div class="balloon">
										<br />
										<fmt:message key="baloonText" />
										<a href="#1" class="close">
											<fmt:message key="ballonClose"/>
										</a>
									</div>
								</div>
								<input id="password" name="password" value="" type="password" /></td>
							</tr>
							<tr>
								<th><label for="password1" id="labelPassoword1"><fmt:message key='datiUtentePortale.label.password1' /></label></th>
								<td><input id="password1" name="password1" value="" type="password" /></td>
							</tr>
						</table>
					</div>
					<div class="pagination">
						<input id="bottoneSavePassword" class="btn large" type="submit" value="<fmt:message key='datiUtentePortale.label.save' />" />
					</div>
				</form>
			</div>
		</div>
	</div>
		
</div>