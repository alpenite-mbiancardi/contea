<%@page import="com.alpenite.tea.documentale.dto.VisualizzazioneDocumentoFiltri"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.List"                                          %>
<%@ page import="java.util.Map"                                           %>
<%@ page import="java.util.ArrayList"								      %>
<%@ page import="org.apache.commons.lang.StringUtils"				      %>
<%@ page import="org.jahia.services.usermanager.jcr.JCRUser"              %>
<%@ page import="com.alpenite.gui.components.MultiSelectComuni"	          %>
<%@ taglib prefix="jcr"       uri="http://www.jahia.org/tags/jcr"         %>
<%@ taglib prefix="fmt"       uri="http://java.sun.com/jsp/jstl/fmt"      %>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core"     %>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"   %>
<%@ taglib prefix="template"  uri="http://www.jahia.org/tags/templateLib" %>

<template:addResources resources="filtro.js" type="javascript" />
<template:addResources resources="jquery-ui.css" type="css" />
<c:choose>
	<c:when test="${renderContext.editMode}">	
		jnt_visualizzazioneDocumenti.filtri
	</c:when>
	<c:otherwise>
		<%
			VisualizzazioneDocumentoFiltri dto = new VisualizzazioneDocumentoFiltri();
			String annoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
		
			if((request.getParameter("dataInizio")==null || request.getParameter("dataInizio").isEmpty()) && (request.getParameter("dataFine")==null || request.getParameter("dataFine").isEmpty())){
				String annoPrecedente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR)-1);
				String mesePrecedente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH));
		        String meseCorrente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1);
		        String meseProssimo = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+2);
		        String giornoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
		        if(GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH) <10){
		        	giornoCorrente = "0"+giornoCorrente;
		        }
		        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) <10){
		        	mesePrecedente = "0"+mesePrecedente;
		        }
		        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1 <10){
		        	meseCorrente = "0"+meseCorrente;
		        }
		        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+2 <10){
		        	meseProssimo = "0"+meseProssimo;
		        }
		        if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) == 0){
		        	mesePrecedente = "12";
		        }
		        
		        /*if (!mesePrecedente.equals("12")){
		        	annoPrecedente = annoCorrente;
		        }*/
		        
		        dto.setDataInizio(giornoCorrente+"/"+meseCorrente+"/"+annoPrecedente);
		        dto.setDataFine(giornoCorrente+"/"+meseCorrente+"/"+annoCorrente);
		        
		        
			}else{
				dto.setDataInizio(request.getParameter("dataInizio"));
		        dto.setDataFine(request.getParameter("dataFine"));
			}
			request.setAttribute("dto", dto);
			session.setAttribute("dtoSession", dto);
		%>
		<script>
        	var lang_picker = "${currentResource.locale}";
    	</script>
		<c:choose>
			<c:when test="${not empty param['comune']}">
				<c:set var="comuneSelected" value="${param['comune']}" />
			</c:when>
			<c:otherwise>
				<c:set var="comuneSelected" value="" />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty param['tipoDocumento']}">
				<c:set var="tipoDocumentoSelected" value="${param['tipoDocumento']}" />
			</c:when>
			<c:otherwise>
				<c:set var="tipoDocumentoSelected" value="" />
			</c:otherwise>
		</c:choose>
		<div class="search">
			<h3><fmt:message key="alpeniteteadocumentale.visualizza.titoloFiltri"/></h3>
		 	<form class="frm" name="filtri">
		 		<fieldset>
			 		<label for="comune"><fmt:message key="alpeniteteadocumentale.visualizza.comune"/></label>
					<select name="comune">
						<option value=""><fmt:message key="alpeniteteadocumentale.visualizza.tutti"/></option>
						<c:forEach var="comune" items="${dto.mappaComuni}">
							<option value="${comune.key}" <c:if test="${comuneSelected == comune.key}">selected</c:if>>${comune.value}</option>
						</c:forEach>
					</select>
					<label for="tipoDocumento"><fmt:message key="alpeniteteadocumentale.visualizza.tipoDocumento"/></label>
					<select name="tipoDocumento">
						<option value=""><fmt:message key="alpeniteteadocumentale.visualizza.tutti"/></option>
						<jcr:sql var="tipiDocumento" sql="SELECT * FROM [jnt:tipoDocumento]"/>
						<c:forEach var="tD" items="${tipiDocumento.nodes}">
							 <c:if test="${fn:contains(dto.listaTipiDocumento,tD.properties['id'].string)}"> 
							 	<option value="${tD.properties['id'].string}" <c:if test="${tipoDocumentoSelected==tD.properties['id'].string}">selected</c:if>> ${tD.properties['name'].string}</option>
							 </c:if>
						</c:forEach>
					</select>
					<label id="labelDataInizio" for="dataInizio"><fmt:message key="alpeniteteadocumentale.ricerca.dataInizio"/></label>
					<input name="dataInizio" id="dataInizio" type="text" value="${dto.dataInizio}"/>
					<label id="labelDataFine" for="dataFine"><fmt:message key="alpeniteteadocumentale.ricerca.dataFine"/></label>
					<input name="dataFine" id="dataFine" type="text" value="${dto.dataFine}" />
					
					<input class="btn small" type="submit" value="<fmt:message key="alpeniteteadocumentale.visualizza.filtra" />" name="action" />
				</fieldset>
		 	</form>
	 	</div>
	</c:otherwise>
</c:choose>