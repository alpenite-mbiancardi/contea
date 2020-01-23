<%@page import="org.jahia.userregistration.dto.ModifyImpersonatedUserDTO"%>
<%@ page   import="java.util.List"                                       %>
<%@ page   import="java.util.ArrayList"                                  %>
<%@ page   import="org.apache.commons.lang.StringUtils"                  %>
<%@ page   import="org.jahia.services.usermanager.JahiaUser"             %>
<%@ page   import="org.jahia.services.content.JCRNodeWrapper"            %>
<%@ page   import="com.alpenite.gui.components.MultiSelectComuni"        %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr"              %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"           %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"          %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"     %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"  %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>

<template:addResources resources="script.js" type="javascript" />
<template:addResources resources="jquery-ui.css" type="css" />
<template:addResources resources="style.css" type="css" />
<c:choose>
	<c:when test="${renderContext.editMode}">jnt:modifyImpersonatedUser</c:when>
	<c:otherwise>
		<%
			ModifyImpersonatedUserDTO dto = new ModifyImpersonatedUserDTO(session);
			request.setAttribute("dto", dto);
		%>
		<c:if test="${dto.showForm}">
			<div id="modifyImpersonatedUsers">
				<form action="<c:url value='${url.base}${currentNode.path}.userModify.do'/>">
					<div class="ctn-data pag">
						<h3><fmt:message key="modifyImpersonatedUsers.title"/></h3>
							<table border="0" cellpadding="0" cellspacing="0" class="data">
								<tr>
									<th><label><fmt:message key='modifyImpersonatedUsers.label.username' /></label></th>
									<td>${dto.name}</td>
								</tr>
								<tr>
									<th><label for="email"><fmt:message key='modifyImpersonatedUsers.label.mail' /></label></th>
									<td><input type="text" name="mail" value="${dto.email}"/></td>
								</tr>
								<tr>
									<th><label for="comuni"><fmt:message key='modifyImpersonatedUsers.label.comuni' /></label></th>
									<td>
										<%=dto.getListaComuni().toString()%>
									</td>
								</tr>
								<tr>
									<th><label for="tipiDocumento"><fmt:message key='modifyImpersonatedUsers.label.tipidocumento'/></label></th>
									<td>
										<select name="tipiDocumento" multiple>
											<jcr:sql var="tipiDocumento" sql="SELECT * FROM [jnt:tipoDocumento]"/>
											<c:forEach var="tD" items="${tipiDocumento.nodes}">
												<option value="${tD.properties['id'].string}" <c:if test="${fn:contains(dto.listaTipiDocumento,tD.properties['id'].string)}">selected</c:if>>${tD.properties['name'].string}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
					</div>
					<div class="pagination">
						<div class="paginationNavigation">
							<input id="modificaUtenteImpersonato" class="btn extralarge" type="submit" value="<fmt:message key='modifyImpersonatedUsers.label.userModificationConfirm'/>" />
						</div>
					</div>
				</form>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>