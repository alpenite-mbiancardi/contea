<%-- 
    Document   : domiciliazioneBancaria
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
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

<template:addResources resources="script.js" type="javascript" />

<c:if test="${param['servizio'] != 'EE'}">


<!-- codice kora con inserimento dati -->


<div><fmt:message key='domiciliazioneBancaria.messageInformation' /></div>





<h2 class="data"><input id="modificaDomiciliazione" type="button" class="btn medium" value="<fmt:message key='domiciliazioneBancaria.bottoneModifica' />" /><fmt:message key='domiciliazioneBancaria.titolo' /></h2>
<table border="0" cellpadding="0" cellspacing="0" class="data">
	 <c:if test="${not empty domiciliazioneBancaria.nomeBanca}">
	 <tr>
		<th colspan="2"><fmt:message key='domiciliazioneBancaria.modalita' /></th>
		<td colspan="2"><fmt:message key='domiciliazioneBancaria.titolo' /></td>
		<td colspan="2"><fmt:message key='domiciliazioneBancaria.mandato' /> </td>
		<td colspan="2"><c:if test="${not empty domiciliazioneBancaria.mandato}">${domiciliazioneBancaria.mandato}
		</c:if>
		</td>
		<td colspan="2">icona pdf </td>
	</tr>
	<tr>
		<th><fmt:message key='domiciliazioneBancaria.banca' /></th>
		<td>${domiciliazioneBancaria.nomeBanca}</td>
		<th>IBAN:</th>
		<td>${domiciliazioneBancaria.iban}</td>
	</tr>
	</c:if>
	<c:if test="${empty domiciliazioneBancaria.nomeBanca}">
	 <tr>
		<th colspan="4">${currentNode.properties.msgNoRID.string}</th>
	</tr>
	</c:if>
</table>

<div id="moduloDomiciliazioneBancaria">
	<div id="overlay">	
		<div id="content">
			<div class="main">
					<c:url value="${url.base}${currentNode.properties['paginaModulo'].node.path}.html" var="urlAction"/>
					<form class="frm" id="formModificaDomiciliazioneBancaria" method="get" action="${urlAction}" target="_blank">
						<div class="ctn-data pag">
						    
    						<h3><fmt:message key='domiciliazioneBancaria.titolo' /></h3>
    						<fieldset id="coordinateBancarie">
	        					<legend><h2><fmt:message key='domiciliazioneBancaria.modulo.titoloCoordinate' /></h2></legend>
					        	<table border="0" cellspacing="0" cellpadding="0" class="data">
					            	<tr>
					            		<td><label for="nomeBanca" id="labelNomeBanca"><fmt:message key='domiciliazioneBancaria.modulo.nomeBanca' /></label></td>
					            		<td><input class="large" id="nomeBanca" name="nomeBanca" value="" class="obbligatorio" class="large" /></td>
					            	</tr>
					            	<tr>
					            		<td><label for="iban" id="labelIban" ><fmt:message key='domiciliazioneBancaria.modulo.iban' /></label></td>
					            		<td><input class="large" id="iban" name="iban" value="" class="obbligatorio" class="large" /></td>
					            	</tr>            	
					            </table>
							</fieldset>
        					<fieldset id="datiSottoscrittore">
					            <legend><h2><fmt:message key='domiciliazioneBancaria.modulo.titoloSottoscrittore' /></h2></legend>
					            <table border="0" cellspacing="0" cellpadding="0" class="data">
					            	<tr>
						            	<td><label for="cognome" id="labelCognome" class=""><fmt:message key='domiciliazioneBancaria.modulo.cognomeSottoscrittore' /></label></td>
					    	        	<td><input readonly="readonly" id="cognome" name="cognome" value="${datiAnagrafici.cognome}" /></td>
					    	        	<td><label for="nome" id="labelNome" ><fmt:message key='domiciliazioneBancaria.modulo.nomeSottoscrittore' /></label></td>
					    	        	<td><input readonly="readonly" id="nome" name="nome" value="${datiAnagrafici.nome}" /></td>
					            	</tr>
					            	<tr>
						            	<td><label for="codiceFiscale" id="labelCodiceFiscale" ><fmt:message key='domiciliazioneBancaria.modulo.codiceFiscale' /></label></td>
					    	        	<td colspan="3"><input readonly="readonly" id="codiceFiscale" name="codiceFiscale" value="${datiAnagrafici.codiceFiscale}" /></td>
					            	</tr>
					            	<tr>
						            	<td><label for="indirizzo" id="labelIndirizzo" ><fmt:message key='domiciliazioneBancaria.modulo.indirizzo' /></label></td>
					    	        	<td><input readonly="readonly" id="indirizzo" name="indirizzo" value="${datiAnagrafici.via}, ${datiAnagrafici.numeroCivico}" /></td>
					    	        	<td><label for="localita" id="labelLocalita" ><fmt:message key='domiciliazioneBancaria.modulo.localita' /></label></td>
					    	        	<td><input readonly="readonly" id="localita" name="localita" value="${datiAnagrafici.comune}" /></td>
					            	</tr>
					            	<tr>
						            	<td><label for="cap" id="labelCap" ><fmt:message key='domiciliazioneBancaria.modulo.cap' /></label></td>
					    	        	<td><input readonly="readonly" id="cap" name="cap" value="${datiAnagrafici.cap}" /></td>
					    	        	<td><label for="telefono" id="labelTelefono" ><fmt:message key='domiciliazioneBancaria.modulo.telefono' /></label></td>
					    	        	<td><input readonly="readonly" id="telefono" name="telefono" value="${datiAnagrafici.telefono}" /></td>
					            	</tr>
					            </table>
       					 	</fieldset>
        					<fieldset id="datiIntestatario">
					            <legend><h2><fmt:message key='domiciliazioneBancaria.modulo.titoloIntestatario' /></h2></legend>
					            <table border="0" cellspacing="0" cellpadding="0" class="data">
					            	<tr>
					            		<td><label for="cognomeIntestatario" id="labelCognomeIntestatario" class=""><fmt:message key='domiciliazioneBancaria.modulo.cognomeIntestatario' /></label></td>
					            		<td><input id="cognomeIntestatario" name="cognomeIntestatario" value="" /></td>
					            		<td><label for="nomeIntestatario" id="labelNomeIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.nomeIntestatario' /></label></td>
					            		<td><input id="nomeIntestatario" name="nomeIntestatario" value="" /></td>
					            	</tr>
					            	<tr>
					            		<td><label for="ragioneSocialeIntestatario" id="labelRagioneSocialeIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.ragioneSocialeIntestatario' /></label></td>
					            		<td colspan="2"><input id="ragioneSocialeIntestatario" name="ragioneSocialeIntestatario" value="" /></td>
					            	</tr>
					            	<tr>
										<td><label for="codiceFiscaleIntestatario" id="labelCodiceFiscaleIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.codiceFiscaleIntestatario' /></label></td>
										<td><input id="codiceFiscaleIntestatario" name="codiceFiscaleIntestatario" value="" /></td>
										<td><label for="partitaIvaIntestatario" id="labelPartitaIvaIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.partitaIvaIntestatario' /></label></td>
										<td><input id="partitaIvaIntestatario" name="partitaIvaIntestatario" value="" /></td>
									</tr> 
					            	
					            	<tr>
					            		<td><label for="indirizzoIntestatario" id="labelIndirizzoIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.indirizzoIntestatario' /></label></td>
					            		<td><input id="indirizzoIntestatario" name="indirizzoIntestatario" value="" /></td>
					            		<td><label for="localitaIntestatario" id="labelLocalitaIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.localitaIntestatario' /></label></td>
					            		<td><input id="localitaIntestatario" name="localitaIntestatario" value="" /></td>
					            	</tr>
					            	<tr>
					            						            					            		
					            	</tr>
									<tr>
										<td><label for="capIntestatario" id="labelCapIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.capIntestatario' /></label></td>
										<td><input id="capIntestatario" name="capIntestatario" value="" /></td>
										<td><label for="telefonoIntestatario" id="labelTelefonoIntestatario" ><fmt:message key='domiciliazioneBancaria.modulo.telefonoIntestatario' /></label></td>
										<td><input id="telefonoIntestatario" name="telefonoIntestatario" value="" /></td>
									</tr>                             	
					            </table>
        					</fieldset>  
        					<input type="hidden" name="cc" value="${param['cc']}" />
        					<input type="hidden" name="servizio" value="${param['servizio']}" />      					
        				</div>
        				<div class="pagination">  
        					      
				            <input id="path_rid" name="path_rid" type="hidden" value="${currentNode.properties['paginaModulo'].node.path}" />
				            <input id="bottoneStampa" class="btn large" type="submit" value="<fmt:message key='domiciliazioneBancaria.modulo.stampaModulo' />" />
				         </div>
        			</form>
	    		</div>
			</div>
		</div>
	</div>
</c:if>
<div class="clear"></div>