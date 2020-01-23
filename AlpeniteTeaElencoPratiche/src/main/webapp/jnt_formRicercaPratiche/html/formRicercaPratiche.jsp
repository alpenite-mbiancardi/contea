<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.alpenite.tea.elencoPratiche.Utils"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr"%>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
<template:addResources resources="style.css" type="css" />
<template:addResources resources="jquery-ui.css" type="css" />
<template:addResources resources="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js" type="javascript" />
<template:addResources resources="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js" type="javascript" />
<template:addResources resources="jquery.uitablefilter.js"
	type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js"
	type="javascript" />
<template:addResources resources="filtro.js" type="javascript" />
 --%>

<%	
	String dataInizioVisualizzata = request.getParameter("dataInizio");
    String dataFineVisualizzata = request.getParameter("dataFine");
    String dataInizio = Utils.getDataFiltro(dataInizioVisualizzata);
    String dataFine = Utils.getDataFiltro(dataFineVisualizzata);
    String annoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
    if (!dataFine.isEmpty()) {
        annoCorrente = dataFine.split("-")[0];
    }
    String yearBefore = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)==0?""+(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR)-1):""+GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
    String mesePrecedente = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)==0?"12":""+GregorianCalendar.getInstance().get(GregorianCalendar.MONTH);
    String meseCorrente = ""+(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1);
    String giornoCorrente = ""+GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);

    if(GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH) <10){
    	giornoCorrente = "0"+giornoCorrente;
    }
    if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1 <10){
    	meseCorrente = "0"+meseCorrente;
    }
    
    if(GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) != 0 && GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) <10){
    	mesePrecedente = "0"+mesePrecedente;
    }
   
    
    dataInizio = yearBefore+"-"+mesePrecedente+"-"+giornoCorrente;
    dataFine = annoCorrente+"-"+meseCorrente+"-"+giornoCorrente;
    if(dataInizioVisualizzata == null)
    	dataInizioVisualizzata = giornoCorrente+"/"+mesePrecedente+"/"+yearBefore;
    if(dataFineVisualizzata == null)
    	dataFineVisualizzata = giornoCorrente+"/"+meseCorrente+"/"+annoCorrente;
    
 	// imposto la data da visualizzare nel filtro
    //request.setAttribute("dataInizio", dataInizioVisualizzata);
    //request.setAttribute("dataFine", dataFineVisualizzata);
%>
<script>
        var lang_picker = "${currentResource.locale}";
</script>

<div class="search" id="formRicercaPratiche">
	<h3><fmt:message key="elencoPratiche.ricerca.titolo"/></h3>
	<form action="${currentNode.properties['paginaElencoPratiche'].node.url}">
		<label id="labelDataInizio" for="dataInizio"><fmt:message key="elencoPratiche.ricerca.dataInizio"/></label>
		<input name="dataInizio" id="dataInizio" type="text" value="<%=dataInizioVisualizzata %>"/>
		<label id="labelDataFine" for="dataFine"><fmt:message key="elencoPratiche.ricerca.dataFine"/></label>
		<input name="dataFine" id="dataFine" type="text" value="<%=dataFineVisualizzata %>" />
		<input type="submit" class="btn large" id="bottoneConferma" value="<fmt:message key="elencoPratiche.ricerca.bottoneConferma"/>" />
	</form>
</div>