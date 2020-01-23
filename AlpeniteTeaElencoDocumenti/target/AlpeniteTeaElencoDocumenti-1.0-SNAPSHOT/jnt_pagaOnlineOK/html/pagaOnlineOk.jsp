<%@page import="java.util.zip.ZipException"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.zip.ZipEntry"%>
<%@page import="java.util.zip.ZipOutputStream"%>
<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@page import="com.alpenite.tea.communicationLayer.data.File"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="it.netsw.apps.igfs.cg.coms.api.init.IgfsCgInit" %>
<%@page import="it.netsw.apps.igfs.cg.coms.api.init.IgfsCgInit.*" %>
<%@page import="it.netsw.apps.igfs.cg.coms.api.BaseIgfsCg.CountryCode" %>
<%@page import="it.netsw.apps.igfs.cg.coms.api.BaseIgfsCg" %>
<%@page import="java.net.URL" %>
<%@page import="java.security.SecureRandom" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Properties" %>
<%@page import="java.io.InputStream" %>
<%@page import="com.roytuts.soapclient.SetOkPagaOnline"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%--
<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
 --%>
<template:addResources resources="filtro.js" type="javascript" />
<template:addResources resources="utilDocumenti.js" type="javascript" />

<%
//prelevo dati in get dalla risposta
if (request.getMethod().toLowerCase().equals("get")) {
	String numeroFattura=  request.getParameter("f");
	String codCompagnia = request.getParameter("a");
	String dataEmiss = request.getParameter("d");
	String tipo = request.getParameter("t"); // il tipo è C per carta di credito e M per mybank
	
	
		//aggiorno su sap il tipo di pagamento e lo imposto come effettuato
		
		SetOkPagaOnline.setPaymentState(dataEmiss, codCompagnia, numeroFattura,tipo);
	
	
}

%>



