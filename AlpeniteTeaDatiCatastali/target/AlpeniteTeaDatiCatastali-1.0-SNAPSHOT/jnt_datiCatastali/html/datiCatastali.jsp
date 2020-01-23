<%-- 
    Document   : datiCatastali
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.communicationLayer.WSReturnManager"%>
<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@page import="java.util.List"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="com.alpenite.tea.datiCatastali.dati.DatiCatastali"%>
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

<template:addResources resources="script.js" type="javascript"/>
<template:addResources type="javascript" resources="jquery.validate.js"/>
<%try{
    JCRNodeWrapper nodo = (JCRNodeWrapper) request.getAttribute("currentNode");
    DatiCatastali dati = WSClient.getClient(session).getDatiCatastali(
            request.getParameter("c"),
            request.getParameter("cc")).getRitorno();
    request.setAttribute("datiCatastali", dati);
    
    SelectProvince province = new SelectProvince();
    request.setAttribute("province", province.toString());
}catch (Exception e){

	//;
}
%>
<script>var urlComuni='${url.base}${currentNode.path}';</script>
<%--
<div id="datiCatastali">
    <div id="header"><fmt:message key="datiCatastali.header" /></div>
    <div class="label"><fmt:message key="datiCatastali.comuneAmministrativo" /></div><div class="dati">${datiCatastali.comuneAmministrativo}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.comuneCatastale" /></div><div class="dati">${datiCatastali.comuneCatastale}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.codiceCatastale" /></div><div class="dati">${datiCatastali.codiceCatastale}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.sezioneUrbana" /></div><div class="dati">${datiCatastali.sezioneUrbana}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.particella" /></div><div class="dati">${datiCatastali.particella}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.particellaSistemaTavolare" /></div><div class="dati">${datiCatastali.particellaSistemaTavolare}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.tipoParticella" /></div><div class="dati">${datiCatastali.tipoParticella}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.subalterno" /></div><div class="dati">${datiCatastali.subalterno}</div><br/>
    <div class="label"><fmt:message key="datiCatastali.foglio" /></div><div class="dati">${datiCatastali.foglio}</div><br/>
    <div id="footer"><input type="submit" id="aaa" name="bottoneModificaDatiCatastali" value="<fmt:message key='datiCatastali.bottoneModifica' />" /></div>
</div>
 --%>

<div class="ctn-data pag">
	<h3><fmt:message key="datiCatastali.header" /></h3>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<tr>
			<th><fmt:message key="datiCatastali.comuneAmministrativo" /></th>
			<td>${datiCatastali.comuneAmministrativo}</td>
			<th><fmt:message key="datiCatastali.codiceCatastale" /></label></th>
			<td>${datiCatastali.codiceCatastale}</td>
			
		</tr>
		<tr>
			<th><fmt:message key="datiCatastali.comuneCatastale" /></th>
			<td>${datiCatastali.comuneCatastale}</td>
			<th><fmt:message key="datiCatastali.sezioneUrbana" /></th>
			<td>${datiCatastali.sezioneUrbana}</td>
		</tr>
		<tr>
			<th><fmt:message key="datiCatastali.foglio" /></th>
			<td>${datiCatastali.foglio}</td>
			<th><label for="part"><fmt:message key="datiCatastali.particella" /></label></th>
			<td>${datiCatastali.particella}</td>
		</tr>
		<tr>
			<th><fmt:message key="datiCatastali.subalterno" /></th>
			<td>${datiCatastali.subalterno}</td>
			<th><fmt:message key="datiCatastali.tipoParticella" /></th>
			<td>${datiCatastali.tipoParticella}</td>
			
		</tr>
		<tr>
			<th><fmt:message key="datiCatastali.particellaSistemaTavolare" /></th>
			<td>${datiCatastali.particellaSistemaTavolare}</td>
			<th>&nbsp;</th>
			<td>&nbsp;</td>
	</table>                        
</div>
<div class="pagination">
	<div class="paginationNavigation">
		<input type="submit" class="btn large" id="bottoneModificaDatiCatastali" name="bottoneModificaDatiCatastali" value="<fmt:message key='datiCatastali.bottoneModifica' />" />
	</div>
</div>




<div id="moduloDatiCatastali">

	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form class="frm" id="formModificaDatiCatastali" method="post" action="<c:url value='${url.base}${currentNode.path}'/>.datiCatastali.do" >
					<div class="ctn-data pag">    
					<%--
					        <fieldset id="campiDatiCatastali">
					            <legend><fmt:message key='datiCatastali.modulo.titolo' /></legend>
					            <label for="provincia"><fmt:message key="datiCatastali.provincia" /></label>
					            ${province}<br/>
					            <label for="comuneAmministrativo"><fmt:message key="datiCatastali.comuneAmministrativo" /></label>
					            <select id="comuneAmministrativo" name="comuneAmministrativo" >
					            <option value="${datiCatastali.comuneCatastale}">${datiCatastali.comuneCatastale}</option>
					            </select><br/>
					            <label for="comuneCatastale"><fmt:message key="datiCatastali.comuneCatastale" /></label><input readonly="readonly" type="text" id="comuneCatastale" name="comuneCatastale" value="${datiCatastali.comuneCatastale}" /><br/>
					            <label for="codiceCatastale"><fmt:message key="datiCatastali.codiceCatastale" /></label><input readonly="readonly" type="text" id="codiceCatastale" name="codiceCatastale" value="${datiCatastali.codiceCatastale}" /><br/>
					            <label for="sezioneUrbana"><fmt:message key="datiCatastali.sezioneUrbana" /></label><input type="text" id="sezioneUrbana" name="sezioneUrbana" value="${datiCatastali.sezioneUrbana}" maxlength="3" /><br/>
					            <label for="particella"><fmt:message key="datiCatastali.particella" /></label><input type="text" id="particella" name="particella" value="${datiCatastali.particella}" maxlength="5"/><br/>
					            <label for="particellaSistemaTavolare"><fmt:message key="datiCatastali.particellaSistemaTavolare" /></label><input type="text" id="particellaSistemaTavolare" name="particellaSistemaTavolare" value="${datiCatastali.particellaSistemaTavolare}" maxlength="10"/><br/>
					            <label for="tipoParticella"><fmt:message key="datiCatastali.tipoParticella" /></label><input type="text" id="tipoParticella" name="tipoParticella" value="${datiCatastali.tipoParticella}" maxlength="1"/><br/>
					            <label for="subalterno"><fmt:message key="datiCatastali.subalterno" /></label><input type="text" id="subalterno" name="subalterno" value="${datiCatastali.subalterno}" maxlength="4"/><br/>
					            <label for="foglio"><fmt:message key="datiCatastali.foglio" /></label><input type="text" id="foglio" name="foglio" value="${datiCatastali.foglio}" maxlength="4"/><br/>
					            <div id="footer">
					                <input type="hidden" id="cc" name="cc" value="${param['cc']}" />
					--%>
							
					<h3><fmt:message key='datiCatastali.modulo.titolo' /></h3>
					<table border="0" cellpadding="0" cellspacing="0" class="data" id="campiDatiCatastali">
						<tr>
							<th><label for="city-amm"><fmt:message key="datiCatastali.provincia" /></label></th>
							<td>${province}</td>
							<th><label for="city-cat"><fmt:message key="datiCatastali.comuneAmministrativo" /></label></th>
							<td><select id="comuneAmministrativo" name="comuneAmministrativo" >
		            			<option value="${datiCatastali.comuneAmministrativo}">${datiCatastali.comuneAmministrativo}</option>
		           				</select>
		           			</td>
						</tr>
						<tr>
							<th><label for="code"><fmt:message key="datiCatastali.codiceCatastale" /></label></th>
							<td><input class="small" size="5" readonly="readonly" type="text" id="codiceCatastale" name="codiceCatastale" value="${datiCatastali.codiceCatastale}" /></td>
							<th><label for="com-cat"><fmt:message key="datiCatastali.comuneCatastale" /></label></th>
							<td><input class="small" readonly="readonly" type="text" id="comuneCatastale" name="comuneCatastale" value="${datiCatastali.comuneCatastale}" /></td>
						</tr>
						<tr>
							<th><label for="sez-urb"><fmt:message key="datiCatastali.sezioneUrbana" /></label></th>
							<td><input class="small" size="5" type="text" id="sezioneUrbana" name="sezioneUrbana" value="${datiCatastali.sezioneUrbana}" /></td>
							<th><label for="foglio"><fmt:message key="datiCatastali.foglio" /></label></th>
							<td><input class="small" size="5" type="text" id="foglio" name="foglio" value="${datiCatastali.foglio}" maxlength="4"/></td>
						</tr>
						<tr>
							<th><label for="part"><fmt:message key="datiCatastali.particella" /></label></th>
							<td><input class="small" size="5" type="text" id="particella" name="particella" value="${datiCatastali.particella}" maxlength="5"/></td>
							<th><label for="sub"><fmt:message key="datiCatastali.subalterno" /></label></th>
							<td><input class="small" size="5" type="text" id="subalterno" name="subalterno" value="${datiCatastali.subalterno}" maxlength="4"/></td>
						</tr>
						<tr>
							<th><label for="type-part"><fmt:message key="datiCatastali.tipoParticella" /></label></th>
							<td><input class="small" size="5" type="text" id="tipoParticella" name="tipoParticella" value="${datiCatastali.tipoParticella}" maxlength="1"/></td>
							<th><label for="part-tav"><fmt:message key="datiCatastali.particellaSistemaTavolare" /></label></th>
							<td><input class="small" size="5" type="text" id="particellaSistemaTavolare" name="particellaSistemaTavolare" value="${datiCatastali.particellaSistemaTavolare}" maxlength="10"/></td>
						</tr>
					</table>
					</div>
					<div class="pagination">
						<!--button type="button"class="btn small">salva</button-->
						<!--a class="prevLink" href="#1">Annulla</a-->
						<input type="hidden" id="cc" name="cc" value="${param['cc']}" />
		                <input type="hidden" id="c" name="c" value="${param['c']}" />
		                <input type="hidden" name="servizio" value="${param['servizio']}" />
		                <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
		                <input type="submit" class="btn large" id="bottoneConfermaModificaDatiCatastali" name="bottoneConfermaModificaDatiCatastali" value="<fmt:message key="datiCatastali.modulo.bottoneConferma" />" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
