<%-- 
    Document   : sacchetti
    Author     : Leone Luca 
--%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.gui.components.SelectAnni"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="com.alpenite.tea.svuotamentiLetture.Utils"%>
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
<template:addResources resources="filtroSvuotamenti.js" type="javascript" />
<template:addResources resources="jquery-ui.css" type="css" />
<template:addResources resources="filtro.js" type="javascript" />
<%

	try{
	    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
	    
	    String dataInizioVisualizzata = request.getParameter("dataInizio");
	    String dataFineVisualizzata = request.getParameter("dataFine");

	    String dataInizio = Utils.getDataFiltro(dataInizioVisualizzata);
	    String dataFine = Utils.getDataFiltro(dataFineVisualizzata);
	    
	    String annoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
	    
	 	// imposto un filtro di default
	    if (dataInizio.length()==0 && dataFine.length()==0) {
	        dataInizio = annoCorrente+"-01-01";
	        dataFine = annoCorrente+"-12-31";
		dataInizioVisualizzata = "01/01/"+annoCorrente;
		dataFineVisualizzata = "31/12/"+annoCorrente;
	    }
	   

    request.setAttribute("elenco", 
        WSClient.getClient(session). getSacchetti(
            request.getParameter("c"), 
            dataInizio,
            dataFine).getRitorno());
    
    request.setAttribute("elencoSacchetti", 
        WSClient.getClient(session).getElencoSacchetti(
            request.getParameter("c")).getRitorno());
    
    // imposto la data da visualizzare nel filtro
    request.setAttribute("dataInizio", dataInizioVisualizzata);
    request.setAttribute("dataFine", dataFineVisualizzata);

}
catch (Exception e){
	
	//TODO
}
%>
<script>
    var msg = "<fmt:message key="ricerca.messaggio"/>";
</script>

<template:addResources type="inlinejavascript">
    <script type="text/javascript">
    function submitFormSacchetti(){
    	$('#moduloRicercaAnno').submit();
    }
    var msg = "<fmt:message key="svuotamenti.ricerca.messaggio"/>";
    var lang_picker = "${currentResource.locale}";
        
    </script>
</template:addResources>
<c:set var="totVolume" value="0" />
<div id="sacchetti">
	<div class="ctn-data pag"style="margin-left:-10px">
		<h3><fmt:message key="sacchetti.title" /></h3>
		<form id="moduloRicercaAnno" method="get" action="${url.base}${renderContext.mainResource.node.path}.html">
			<label id="labelDataInizio" for="dataInizio"><fmt:message key="svuotamenti.ricerca.dataInizio"/></label>
			<input name="dataInizio" id="dataInizio" type="text" value="${dataInizio}"/>
			<label id="labelDataFine" for="dataFine"><fmt:message key="svuotamenti.ricerca.dataFine"/></label>
			<input name="dataFine" id="dataFine" type="text" value="${dataFine}" />
			
			<input type="hidden" id="c" name="c" value="${param['c']}" />
			<input type="hidden" id="cc" name="cc" value="${param['cc']}" />
			<input type="hidden" id="displayTab" name="displayTab" value="${param['displayTab']}" />
			<input type="hidden" id="servizio" name="servizio" value="${param['servizio']}" />   
		</form>
		<div class="pagination" style="float:left; width:300px; clear:none">
			<div class="paginationNavigation">
				<input type="button" onclick="submitFormSacchetti();" class="btn large left" id="bottoneConferma" value="<fmt:message key="ricerca.bottoneConferma"/>" />
			</div>
		</div>
		<div class="pagination" style="float:left; width:300px; clear:none">
			<div class="paginationNavigation">
				<input type="button" class="btn large" id="bottoneVisualizzaSacchetti" value="<fmt:message key="sacchetti.bottoneVisualizzaSacchetti"/>" />
			</div>
		</div>
		<div class="box pag">
			<div class="open"></div>
			<table id="tabellaSacchetti" class="tbl"  cellspacing="0" cellpadding="0" border="0">
				<thead id="tabellaSacchettiHeader" class="header">
					<tr>
						<th><fmt:message key="sacchetti.tabellaSacchetti.header.numeroDiSerie"/></th>
						<th><fmt:message key="sacchetti.tabellaSacchetti.header.data"/></th>
						<!--<th><fmt:message key="sacchetti.tabellaSacchetti.header.ora"/></th>-->
						<th class="last"><fmt:message key="sacchetti.tabellaSacchetti.header.volume"/></th>
					</tr>
				</thead>
				<tbody id="tabellaSacchettiRighe">
					<c:forEach items="${elenco}" var="riga" varStatus="count">
						<tr id="tabellaSacchettiRiga${count}" class="riga">
							<td class="numeroDiSerie">${riga.numeroDiSerie}</td>
							<fmt:formatDate value="${riga.data}" pattern="dd-mm-yyyy" var="data"/>
							<td class="bg data">${data}</td>    
							<!--<td class="bg ora">${riga.ora}</td>-->
							<td class="bg volume"><fmt:formatNumber value="${riga.volume}" /></td>
							<c:set var="vol" value="${riga.volume}" />
						</tr>
						<c:set var="totVolume" value="${totVolume+vol}"/>
					</c:forEach>
				</tbody> 
			</table>
		</div>
	</div>
</div>


<div class="totalContent">
	<div  class="totalValue">
			<label>TOT. Volume</label>
			<span>${totVolume}</span>	
	</div>
</div>

<div id="listaSacchetti">
<div class="ctn-data pag"  >
		<table id="tabellaBidoni"   class="tbl"  cellspacing="0" cellpadding="0" border="0">
				<tr>
					<th><fmt:message key="sacchetti.tabellaBidoni.header.numeroDiSerie"/></th>
					<th><fmt:message key="sacchetti.tabellaBidoni.header.descrizione"/></th>
				</tr>
				<c:forEach items="${elencoSacchetti}" var="riga" varStatus="count">
					<tr id="tabellaSacchettiRiga${count}" class="riga">
						<td class="numeroDiSerie">${riga.numeroDiSerie}</td>
						<td class="bg descrizione">${riga.descrizione}</td>    
					</tr>
				</c:forEach>
		</table>
</div>
</div>
</div><!-- chiusura div search aperto  in customTabular -->