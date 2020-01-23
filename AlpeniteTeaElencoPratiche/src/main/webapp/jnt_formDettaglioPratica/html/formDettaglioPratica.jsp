<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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
<template:addResources resources="style.css" type="css" />
<template:addResources
	resources="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"
	type="javascript" />
<template:addResources resources="jquery.uitablefilter.js"
	type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js"
	type="javascript" />
<template:addResources resources="filtro.js" type="javascript" />
<%
	JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
	WSClient client;

	try {
		client = WSClient.getClient(session);
	} catch (Exception e) {
		String defaultBP = "0018000147"; // DEBUG - decide a value to use on production
		String bpU = session.getAttribute("BPUSESSION") == null ? defaultBP : session.getAttribute("BPUSESSION").toString();
		String bpC = session.getAttribute("BPCSESSION") == null ? defaultBP : session.getAttribute("BPCSESSION").toString();
		client = WSClient.getClient(bpU, bpC);
	}
	
	client.getFileDocumento(fileId, fileObj);


%>
