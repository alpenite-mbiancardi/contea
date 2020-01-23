<%-- 
    Document   : letture
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.gui.components.SelectAnni"%>
<%@page import="java.util.GregorianCalendar"%>
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
<template:addResources resources="style.css" type="css" />

<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtroLetture.js" type="javascript" />
<template:addResources resources="autoletture.js" type="javascript" />

<%
try{
    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
        
    // creo il select degli anni
    String anniPrima = currentNode.getPropertyAsString("anniPrima");
    String annoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
    anniPrima = anniPrima==null||anniPrima.length()==0?"10":anniPrima;
    SelectAnni anni = new SelectAnni("anno", "anno", null, Integer.parseInt(anniPrima), 0);
    anni.setValoreSelezionato(annoCorrente);
    if (request.getParameter("anno") != null )
        anni.setValoreSelezionato(request.getParameter("anno"));
    request.setAttribute("anni", anni.toStringInverse());
        
    request.setAttribute("elenco", 
        WSClient.getClient(session).getLetture(
            request.getParameter("c"), 
            request.getParameter("anno")).getRitorno());
}
catch (Exception e){
	
	//;
}

String servizio = request.getParameter("servizio");
%>
<script>
    var msg = "<fmt:message key="ricerca.messaggio"/>";
</script>
<template:addResources type="inlinejavascript">
    <script type="text/javascript">
    function submitFormLetture(){
    	$('#moduloRicercaAnno').submit();
    }
    </script>
</template:addResources>
	
	<div id="letture">
	<div class="ctn-data pag">
		<h3><fmt:message key="letture.title" /></h3>
		<input type="button" onclick="submitFormLetture();" class="btn large marginleft10" id="bottoneConferma" value="<fmt:message key="ricerca.bottoneConferma"/>" />
    	
		<div class="paginationNavigation" id="floatRightPN">
		<%
		String contrattoGet = request.getParameter("c");
		String contoContrattualeGet = request.getParameter("cc");
		String servizioGet = request.getParameter("servizio");
		%>
				<c:if test="${currentNode.properties['tipoLettura'].string != 'teleriscaldamento' and param['status'] eq 'A'}" >
				<%
					//  controllo servizio ENERGETCO - L'autolettura deve essere disattivata
					if (!servizio.equals("E8")){
				%>
				
					<a href="#" class="btn large" onclick="autolettura(<%=contrattoGet%>)"><fmt:message key='letture.bottoneAutolettura' /></a>
					<div id="autolettura<%=contrattoGet%>">
					<div id="overlay">
					<div id="content">
					<div class="main">
					<form class="frm" id="moduloAutolettura<%=contrattoGet%>" name="moduloAutolettura" method="post" action="<c:url value='${url.base}${currentNode.path}.autoletture.do' />">
							<div class="ctn-data pag">
								
								<input name="c" type="hidden" value="<%=contrattoGet%>" />
						        <input name="cc" type="hidden" value="<%=contoContrattualeGet%>" />
						        <input name="servizio" type="hidden" value="<%=servizioGet%>" />
						        <% if(servizioGet.equals("G1")){%>
										<input type="hidden"  name="isAcqua" value="false" />
										 <% } if(servizioGet.equals("H1")){%>
									
										<input type="hidden"  name="isAcqua" value="true" />
										 <% } %>
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
					<script type="text/javascript"> 
					//makeDialog('pratica${pratica.numero}div', 'tabellaPraticheRiga${pratica.numero}') 
					
					$(window).resize(function() {
					$("#autolettura<%=contrattoGet%>").dialog("option", "position", "center");
					});
					
					$(document).ready(function() {
						$("#autolettura<%=contrattoGet%>").hide();
					    
					    // creo il dialogo a partire dal modulo
					    var dialogWidth = 680;
					    var $dialogAutoletture = $("#autolettura<%=contrattoGet%>").dialog({
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
				    $("#moduloAutolettura<%=contrattoGet%>").submit(function() {
				        var invia = true;
				        
				        // verifico i campi obbligatori
				        $("#moduloAutolettura<%=contrattoGet%> .obbligatorio").each(function() {
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
					
					<%
					// fine controllo servizio ENERGETCO - L'autolettura deve essere disattivata
					}
					%>
					</c:if>
			</div>
		<div class="clear" class="marginbottom10" style="margin-bottom:10px"> </div>
		<form class="frm" id="moduloRicercaAnno">
			<label id="labelAnno" for="anno" class="marginleft10"><fmt:message key="ricerca.anno"/></label>
			${anni}
			<input type="hidden" id="c" name="c" value="${param['c']}" />
			<input type="hidden" id="cc" name="cc" value="${param['cc']}" /> 
			<input type="hidden" id="servizio" name="servizio" value="${param['servizio']}" />      
			<input type="hidden" id="status" name="status" value="${param['status']}" />

		</form>
		<div class="messaggio" id="messaggio" style="background-color:white;"></div>
		<div class="pagination">
			
		</div>
	</div>
	
	<div class="box pag">
		<div class="open"></div>
		<table id="tabellaLetture" class="tbl" cellspacing="0" cellpadding="0" border="0">
			<thead id="tabellaLettureHeader" class="header">
				<tr>
					<th><fmt:message key="letture.tabellaLetture.header.contatore"/></th>
					<th><fmt:message key="letture.tabellaLetture.header.registro"/></th>
					<th><fmt:message key="letture.tabellaLetture.header.dataLettura"/></th>
					<th><fmt:message key="letture.tabellaLetture.header.valore"/></th>
					<th class="last"><fmt:message key="letture.tabellaLetture.header.tipologia"/></th>
				</tr>
			</thead>
			<tbody id="tabellaLettureRighe">
				<c:forEach items="${elenco}" var="riga" varStatus="count">
					<tr id="tabellaLettureRiga${count}" class="riga">
						<td>${riga.contatore}</td>
						<td class="bg">${riga.registro}</td>  
						<fmt:formatDate value="${riga.dataLettura}" pattern="dd-mm-yyyy" var="dataLettura"/>  
						<td class="bg">${dataLettura}</td>
						<td class="bg">${riga.valore}</td>
						<td class="bg">${riga.tipologia}</td>
					</tr>
				</c:forEach>
			</tbody> 
		</table>
	</div>
</div>