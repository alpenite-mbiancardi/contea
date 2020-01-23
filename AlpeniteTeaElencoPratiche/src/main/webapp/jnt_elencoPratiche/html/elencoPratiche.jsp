<%@page import="java.util.Date"%>
<%@page import="com.alpenite.tea.serviceRequest.dati.ServiceRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.elencoPratiche.Utils"%>
<%@page import="java.util.GregorianCalendar"%>
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
<template:addResources resources="jquery-ui.css" type="css" />
<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtro.js" type="javascript" />
<%

	String year = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
	String yearBefore = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)==0?""+(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR)-1):""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
	String month = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1);
	String day = ""+GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
	String monthBefore = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)==0?"12":""+GregorianCalendar.getInstance().get(GregorianCalendar.MONTH);
	
	if(GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH) < 10){
		day = "0"+day;
	}
	if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1 <10){
		month = "0"+month;
    }
	
	if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)!=0 && GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)<10){
		monthBefore = "0"+monthBefore;
	}

	String defaultStartDate = yearBefore+"-"+monthBefore+"-"+day;
    String defaultEndDate = year+"-"+month+"-"+day;
	
	String dataInizioVisualizzata = request.getParameter("dataInizio");
    String dataFineVisualizzata = request.getParameter("dataFine");
    String dataInizio = Utils.getDataFiltro(dataInizioVisualizzata);
    String dataFine = Utils.getDataFiltro(dataFineVisualizzata);
	
    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
	//WSClient client;
	/*try {
		client = WSClient.getClient(session);
	} catch (Exception e)
	{
		//String defaultBP = "0040000042"; // DEBUG - decide a value to use on production
		//String bpU = session.getAttribute("BPUSESSION") == null ? defaultBP : session.getAttribute("BPUSESSION").toString();
		//String bpC = session.getAttribute("BPCSESSION") == null ? defaultBP : session.getAttribute("BPCSESSION").toString();
		//client = WSClient.getClient(bpU, bpC);
	}*/
	
	String startDate = dataInizio.equals("") ? defaultStartDate : dataInizio;
	String endDate = dataFine.equals("") ? defaultEndDate : dataFine;
	
	// prelevo l'elenco delle pratiche
    request.setAttribute("elenco", WSClient.getClient(session).getElencoServiceRequest(
    		startDate, 
    		endDate).getRitorno());
	
	//List<ServiceRequest> elenco = client.getElencoServiceRequest(startDate, endDate).getRitorno();
	
	//request.setAttribute("elenco", elenco);
	
%>
<div class="box">
	<div class="open"></div>
	<table id="tabellaPratiche" class="tbl" border="0" cellpadding="0" cellspacing="0">
		<thead id="tabellaPraticheHeader" class="header">
			<tr>
				<th class="colonna"><fmt:message key="elencoPratiche.tabella.header.numero"/></th>
				<th class="colonna"><fmt:message key="elencoPratiche.tabella.header.stato"/></th>
				<th class="colonna"><fmt:message key="elencoPratiche.tabella.header.tipologia"/></th>
				<th class="colonna"><fmt:message key="elencoPratiche.tabella.header.dataApertura"/></th>
				<th class="colonna last"><fmt:message key="elencoPratiche.tabella.header.dataChiusura"/></th>
			</tr>
		</thead>
		<tbody id="tabellaContrattiRighe">
			<c:forEach items="${elenco}" var="pratica" varStatus="numero" >
				<tr id="tabellaPraticheRiga${pratica.numero}" class="riga">
					<td class="colonna numero focus">
						<a href="#" onclick="viewDetails(${pratica.numero})">${pratica.numero}</a>
					</td>
					<td class="colonna stato bg">
						<c:choose>
							<c:when test="${pratica.stato=='Completed'}">
								<span class="status performed">${pratica.stato}</span>
							</c:when>
							<c:when test="${pratica.stato=='Canceled'}">
								<span class="status reset">${pratica.stato}</span>
							</c:when>
							<c:otherwise>
								<span class="status performed">${pratica.stato}</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="colonna tipologia bg">${pratica.tipologia}</td>
					<fmt:formatDate value="${pratica.dataApertura}" pattern="dd-mm-yyyy" var="dataApertura"/>
					<td class="colonna dataApertura bg">${dataApertura}</td>
					<fmt:formatDate value="${pratica.dataChiusura}" pattern="dd-mm-yyyy" var="dataChiusura"/>
					<td class="colonna dataChiusura bg">
					<c:if test="${dataChiusura != '31-00-0002' }">
						${dataChiusura}
					</c:if>
					<c:if test="${dataChiusura eq '31-00-0002' }">
						--
					</c:if>
					</td>
				</tr>
				<div id="pratica${pratica.numero}div">
					<div class="details">
					<c:if test="${not empty pratica.domanda}">
					<div class="label">
						<fmt:message key="elencoPratiche.testoDomanda"/>
					</div>
					<div class="value">
					<p>
						${pratica.domanda}
					</p>
					</div>
					</c:if>
					<div class="label">
						<fmt:message key="elencoPratiche.testoRisposta"/>
					</div>
					<div class="value">
					<p>
						${pratica.risposta}
					</p>
					</div>
					</div>
				</div>
				<template:addResources type="inlinejavascript">
					<script type="text/javascript"> 
					//makeDialog('pratica${pratica.numero}div', 'tabellaPraticheRiga${pratica.numero}') 
					$(document).ready(function() {
						$("#pratica${pratica.numero}div").hide();
				
						$("#pratica${pratica.numero}div").dialog({
							autoOpen: false,
							modal: true,
							draggable: false,
							width: 680,
							resizable: false
						});
						
						//$("#tabellaPraticheRiga${pratica.numero}").click(function() {
						//	$("#pratica${pratica.numero}div").dialog('open');
						//    return false;
						//});
					});
					function viewDetails(numero){
						var string = "#pratica"+numero+"div";
						$(string).dialog('open');
						return false;
					}
					</script>
				</template:addResources>
			</c:forEach>
		</tbody> 
	</table>
	<div class="close"></div>
</div>