<%-- 
    Document   : elencoContratti
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
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
<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtro.js" type="javascript" />
<template:addResources resources="autoletture.js" type="javascript" />
<style type="text/css">
.fixed-dialog
{
	position: fixed;
	top: 50px;
	left: 50px;
}		
</style>
<%
    // prelevo il valore del filtro predefinito
    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
    String filtro = currentNode.getProperty("valoreFiltro").getString();
    
    // ********************************************
    if (currentNode.hasProperty("bpuTest")) {
        session.setAttribute("BPUSESSION", currentNode.getProperty("bpuTest").getString());
        WSClient.getClient(session).setBpUtenza(currentNode.getProperty("bpuTest").getString());
    }
    if (currentNode.hasProperty("bpcTest")) {
        session.setAttribute("BPCSESSION", currentNode.getProperty("bpcTest").getString()); //0010000381
        WSClient.getClient(session).setBpCliente(currentNode.getProperty("bpcTest").getString());
    }
    // ********************************************    
    
    String tipoBp = currentNode.getUser().getProperty("j:tipoBP");
    if(tipoBp != null && tipoBp.equals(Constants.TIPO_AMM_COND)){
    	if(WSClient.getClient(session).getBpCliente() == null){
    		// redirect to "I miei condomini"
    		//response.sendRedirect(currentNode.getProperty("condominiPage").getPath());
    	}
    }
    
    // se il filtro contiene il valore tutto annullo la variabile
    List<String> filtri = filtro.equals("tutto")?null:Constants.ZPT_CONTRACT_LIST_ITCOMPANY_MAP.get(filtro);
    
    // richiedo al server la lista dei contratti, eventualmente filtrandoli
    request.setAttribute("elenco", WSClient.getClient(session).getElencoContratti(
            filtri).getRitorno());
%>
<c:if test="${currentNode.properties['visualizzaFiltro'].string == 'true'}">
    <script>
        var msg = "<fmt:message key="elencoContratti.filtri.messaggio"/>";
    </script>
    <div class="search">
		<h3><fmt:message key="elencoContratti.filtri.titolo"/></h3>
		<div id="filtri">
					<label id="labelFiltro" for="filtro"><fmt:message key="elencoContratti.filtri.filtro"/></label>
					<input name="filtro" id="filtro" type="text" />
					<input class="btn medium" type="button" id="bottoneConferma" name="bottoneConferma" value="<fmt:message key="elencoContratti.filtri.bottoneConferma"/>" />
					<input class="btn medium left" type="button" id="bottoneAnnulla" name="bottoneAnnulla" value="<fmt:message key="elencoContratti.filtri.bottoneAnnulla"/>" />      
		</div>
    </div>
    <div class="clear"></div>
    <div class="messaggio" id="messaggio"></div>
</c:if>
<div class="box pag">
    <div class="open"></div>
		<table  class="tbl tabella tablesorter" id="tabellaContratti">
			<thead id="tabellaContrattiHeader" class="header">
			<tr>
				<th class="colonna"><fmt:message key="elencoContratti.tabellaContratti.header.servizio"/></th>
				<th class="colonna"><fmt:message key="elencoContratti.tabellaContratti.header.codiceCliente"/></th>
				<th class="colonna"><fmt:message key="elencoContratti.tabellaContratti.header.contratto"/></th>
				<th class="colonna"><fmt:message key="elencoContratti.tabellaContratti.header.indirizzo"/></th>
				<th class="colonna"><fmt:message key="elencoContratti.tabellaContratti.header.stato"/></th>
				<th class="last colonna"><fmt:message key="elencoContratti.tabellaContratti.header.autolettura"/></th>
			</tr>
			</thead>
			<tbody id="tabellaContrattiRighe">
			<c:forEach items="${elenco}" var="contratto" varStatus="numero" >
				<tr id="tabellaContrattiRiga1" class="riga">
					
					<td class="icon<c:if test="${currentNode.properties['valoreFiltro'].string == 'tutto'}">-tea</c:if> colonna">
					<c:choose>
						<c:when test="${contratto.tipoServizio=='C3' && contratto.codiceCompagnia=='Z022'}">
							<span class="teleriscaldamento"><p><c:out value="${contratto.codiceCompagnia}"/></p><fmt:message key="Tlr"/></span>
						</c:when>
						<c:when test="${contratto.tipoServizio=='H1' && contratto.codiceCompagnia=='Z025' }">
							<span class="acqua"><p><c:out value="${contratto.codiceCompagnia}"/></p><fmt:message key="Acqua"/></span>
						</c:when>
						<c:when test="${contratto.tipoServizio=='A1' && contratto.codiceCompagnia=='Z023'}">
							<span class="servizio_rifiuti"><fmt:message key="Rifiuti"/></span>
						</c:when>
						<c:when test="${contratto.tipoServizio=='G1' && contratto.codiceCompagnia=='Z022'}">
							<span class="gas"><p><c:out value="${contratto.codiceCompagnia}"/></p><fmt:message key="Gas"/></span>
						</c:when>
						<c:when test="${contratto.tipoServizio=='EE'&& contratto.codiceCompagnia=='Z022' }">
							<span class="luce"><p><c:out value="${contratto.codiceCompagnia}"/></p><fmt:message key="Luce"/></span>
						</c:when>
						<c:when test="${contratto.tipoServizio=='E8'}">
							<span class="luce"><p><c:out value="${contratto.codiceCompagnia}"/></p><fmt:message key="Luce"/></span>
						</c:when>
						
						<c:when test="${contratto.tipoServizio=='H1' && contratto.codiceCompagnia=='Z031'}">
						<span class="aqa"><p><c:out value="${contratto.codiceCompagnia}"/></p><fmt:message key="Luce"/></span>
							
						</c:when>
					</c:choose>
					</td>
					<td class="focus bg colonna codiceCliente">
						<c:set var="energia" value="&e=true" />
						<%-- 07/03/2016 [CB] commentato per richiesta Simona Borgonovi in e-mail del 29/02/2016
						<c:if test="${contratto.tipoServizio=='EE' || contratto.tipoServizio=='E8' }">
							<c:set var="energia" value="&e=false" />
						</c:if>--%>
						
						<c:if test="${contratto.tipoServizio=='G1'}">
						<c:set var="company" value="YE22" />
						</c:if>
						<c:if test="${contratto.tipoServizio=='C3'}">
						<c:set var="company" value="YT22" />
						</c:if>
						<c:if test="${contratto.tipoServizio!='G1' && contratto.tipoServizio!='C3' }">
						<c:set var="company" value="${contratto.codiceCompagnia}" />
						</c:if>
						<c:choose>
							<c:when test="${currentNode.properties['paginaContoContrattuale'] != null}">
								<a href="${currentNode.properties['paginaContoContrattuale'].node.url}?cc=${contratto.contoContrattuale}&servizio=${contratto.tipoServizio}${energia}&compagnia=${company}">${contratto.contoContrattuale}</a>
							</c:when>
							<c:otherwise>
								${contratto.contoContrattuale}
							</c:otherwise>
						</c:choose>
					</td>
					<td class="focus bg colonna contratto">
						<c:choose>
							<c:when test="${contratto.tipoServizio=='C3' && contratto.codiceCompagnia=='Z022'}">
								<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoTeleriscaldamento'].node.url}"/>
							</c:when>
							<c:when test="${contratto.tipoServizio=='H1' && contratto.codiceCompagnia=='Z025'}">
								<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoAcqua'].node.url}"/>
							</c:when>
							<c:when test="${contratto.tipoServizio=='A1' && contratto.codiceCompagnia=='Z023'}">
								<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoAmbiente'].node.url}"/>
							</c:when>
							<c:when test="${contratto.tipoServizio=='G1' && contratto.codiceCompagnia=='Z022'}">
								<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoGas'].node.url}"/>
							</c:when>
							<c:when test="${contratto.tipoServizio=='H1' && contratto.codiceCompagnia=='Z031'}">
							<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoAqa'].node.url}"/>
							</c:when>
							<c:when test="${contratto.tipoServizio=='E8' }">
							<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoLuce'].node.url}"/>
							</c:when>
							<c:when test="${contratto.tipoServizio=='EE' }">
							<c:set var="urlPagina" value="${currentNode.properties['paginaContrattoLuce'].node.url}"/>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${contratto.tipoServizio=='G1'}">
								<c:set var="h2o" value="false" />
							</c:when>
							<c:when test="${contratto.tipoServizio=='H1'}">
								<c:set var="h2o" value="true" />
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${urlPagina != null}">
								<c:if test="${contratto.stato == 'ATTIVO'}">
									<c:set var="status" value="A" />
								</c:if>
								<c:if test="${contratto.stato == 'NON ATTIVO'}">
									<c:set var="status" value="N" />
								</c:if>
								<a href="${urlPagina}?cc=${contratto.contoContrattuale}&c=${contratto.contratto}&servizio=${contratto.tipoServizio}&h2o=${h2o}&status=${status}">${contratto.contratto}</a>
							</c:when>
							<c:otherwise>
								${contratto.contratto}
							</c:otherwise>
						</c:choose>
					</td>    
					<td class="bg colonna indirizzo">
					<c:choose>


						<c:when test="${contratto.tipoServizio=='EE' && contratto.codiceCompagnia=='Z022'}">
							
						<p>Per visualizzare le fatture precedenti al 2017 </p><a target="_blank" href="https://www.teaenergia.it/irj/portal/te/display_post?site=epwp&mode=news&id=2970"> Per info </a>
						</c:when>
						<c:otherwise>
							${contratto.indirizzo}
							
						</c:otherwise>
					</c:choose>
					
					
					</td>
					<td class="bg colonna stato">
					<c:choose>
						<c:when test="${contratto.stato == 'ATTIVO'}">
							<span class="status performed">${contratto.stato}</span>
						</c:when>
						<c:when test="${contratto.stato == 'NON ATTIVO'}">
							<span class="status reset">${contratto.stato}</span>
						</c:when>
					</c:choose>
					</td>
					<td class="colonna autolettura">
					<c:choose>
						<c:when test="${(contratto.tipoServizio=='G1' or contratto.tipoServizio=='H1'   ) and contratto.stato == 'ATTIVO'}">
							<a href="#" class="link right" onclick="autolettura(${contratto.contratto})"><fmt:message key="elencoContratti.comunica"/></a>
							<div id="autolettura${contratto.contratto}">
							<div id="overlay">
							<div id="content">
							<div class="main">
							<form class="frm" id="moduloAutolettura${contratto.contratto}" name="moduloAutolettura" method="post" action="<c:url value='${url.base}${currentNode.path}.autoletture.do' />">
									<div class="ctn-data pag">
										
										<input name="c" type="hidden" value="${contratto.contratto}" />
								        <input name="cc" type="hidden" value="${contratto.contoContrattuale}" />
								        <c:choose>
											<c:when test="${contratto.tipoServizio=='G1'}">
												<input type="hidden"  name="isAcqua" value="false" />
											</c:when>
											<c:when test="${contratto.tipoServizio=='H1'}">
												<input type="hidden"  name="isAcqua" value="true" />
											</c:when>
										</c:choose>
										<h3><fmt:message key="autolettura.valore" /></h3>
										<table border="0" cellpadding="0" cellspacing="0" class="data">
											<tr>
											<th><label for="valoreAutolettura"><fmt:message key="autolettura.valore2" /></label></th>
											<td><input class="obbligatorio large" name="valoreAutolettura" type="text" /></td>
											</tr>
										</table>
									</div>
									<div class="pagination">
										<input type="hidden"  name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
										<input type="submit" class="btn medium" name="bottoneConferma" value="<fmt:message key='autolettura.bottoneConferma' />" />
									</div>
							</form>
							</div>
							</div>
							</div>
							</div>
							<template:addResources type="inlinejavascript">
							<script type="text/javascript"> 
							//makeDialog('pratica${pratica.numero}div', 'tabellaPraticheRiga${pratica.numero}') 
							
							$(window).resize(function() {
    						$("#autolettura${contratto.contratto}").dialog("option", "position", "center");
							});
							
							$(document).ready(function() {
								$("#autolettura${contratto.contratto}").hide();
							    
							    // creo il dialogo a partire dal modulo
							    var dialogWidth = 680;
							    var $dialogAutoletture = $("#autolettura${contratto.contratto}").dialog({
							    	dialogClass: 'fixed-dialog',
							        autoOpen: false,
							        modal: true,
							        draggable: false,
							        width: dialogWidth,
							        resizable: false,
							        position: ['center','center'],
							        closeText: "X"
							    });			
							});
							
							function autolettura(numero){
								var string = "#autolettura"+numero;
								$(string).dialog('open');
								return false;
							}
							 
							// valido il form quando viene inviato
						    $("#moduloAutolettura${contratto.contratto}").submit(function() {
						        var invia = true;
						        
						        // verifico i campi obbligatori
						        $("#moduloAutolettura${contratto.contratto} .obbligatorio").each(function() {
						            if ($.trim($(this).val()) == "") {
						                $(this).addClass("nonCompleto");
						                invia = false;
						            } else {
						                $(this).addClass("completo");
						            }
						        });
						        
						        if (invia) {
						            $dialogAutoletture.dialog('close');
						        }
						        $("#loading").hide();
						        $("#loading").dialog('close');
						        return invia;
						    });
							
							</script>
				</template:addResources>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
