<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="org.jahia.services.usermanager.JahiaUser"%>
<%@ page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ page import="com.alpenite.gui.components.MultiSelectComuni"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib"%>
<template:addResources type="css"	     resources="tea_my.css"/>
<template:addResources type="javascript" resources="formInserimentoDocumento.js"/>

<%-- <template:addResources type="inlinejavascript"/> --%>

<script type="text/javascript">
	window.messaggi={};
	window.messaggi.inserireDocumento= "<fmt:message key='alpeniteteadocumentale.form.inserireDocumento'/>";
	window.messaggi.inviareEmail     = "<fmt:message key='alpeniteteadocumentale.form.inviareEmail'     />";
</script>
<div class="formInserimentoDocumento">
	<h3>
		<fmt:message key="alpeniteteadocumentale.titolo.caricaDocumento" />
	</h3>
	<form action="${url.base}${currentNode.path}.documentaleSalvataggioDocumento.do" method="post" id="uploadDocument" enctype="multipart/form-data">
		<table border="0" cellpadding="0" class="tb1 tabella">
			<tr class="riga">
				<td class="bg colonna"><label for="tipoDocumento"><fmt:message key="alpeniteteadocumentale.form.tipoDocumento" /></label></td>
				<td class="bg colonna">
					<select name="tipoDocumento">
						<jcr:sql var="tipiDocumento" sql="SELECT * FROM [jnt:tipoDocumento] ORDER BY [id]" />
						<c:forEach var="tD" items="${tipiDocumento.nodes}">
							<option value="${tD.properties['id'].string}">${tD.properties['name'].string}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr class="riga">
				<td class="bg colonna"><label for="societa"><fmt:message key="alpeniteteadocumentale.form.azienda" /></label></td>
				<td class="bg colonna">
					<select name="societa">
						<option value="">Qualsiasi</option>
						<option value="luce">luce</option>
						<option value="acqua">acqua</option>
						<option value="teleriscaldamento">teleriscaldamento</option>
						<option value="servizio_rifiuti">servizio_rifiuti</option>
						<option value="gas">gas</option>
						<option value="pozzi">pozzi</option>
						<option value="info_generali">info_generali</option>
						<option value="scadenzario">scadenzario</option>
						<option value="igiene_urbana">igiene_urbana</option>
					</select>
				</td>
			</tr>
			<tr class="riga">
				<td><label for="comune"><fmt:message key="alpeniteteadocumentale.form.comune" /></label></td>
				<td class="bg colonna">
					<%
						MultiSelectComuni comuni = new MultiSelectComuni("comuni", "modImpComuni", null);
						/* Map<String, String> mappaComuni = comuni.getMappaValori();
						request.setAttribute("mappaComuni", mappaComuni); */
					%> 
					<%=comuni.toString()%>
					<%-- <select name="comune">
						<c:forEach var="comune" items="${mappaComuni}">
							<option value="${comune.key}">${comune.value}</option>
						</c:forEach>
					</select> --%>
				</td>
			</tr>
			<tr class="riga">
				<td><label for="documentoDaSalvare"><fmt:message key="alpeniteteadocumentale.form.caricaDocumento" /></label></td>
				<td class="bg colonna">
					<input class="selectFile" type="file" name="documentoDaSalvare" >
				</td>
			</tr>
		</table>
			<div class="grid_10">
				<input type="hidden" name="sendEmail" class="sendEmail" value="false" /> 
				<input class="btn large upload" type="submit" value="<fmt:message key="alpeniteteadocumentale.form.caricaDocumento"></fmt:message>" onclick="verifyInsertion();return false" />
			</div>
		
	</form>
</div>