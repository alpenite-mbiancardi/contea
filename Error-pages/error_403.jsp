<%@page language="java" contentType="text/html; charset=UTF-8"
%><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.jahia.org/tags/internalLib" prefix="internal"%>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<utility:setBundle basename="JahiaInternalResources"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="robots" content="noindex, nofollow"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/res/grid.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/res/tea.css" type="text/css"/>
    <title><fmt:message key="label.error.403.title"/></title>
</head>
<body class="login" onLoad="if (history.length > 1) { document.getElementById('backLink').style.display=''; }">
        <div class="bodywrapper">
    <div id="header">
        <div class="container_16">
            <div class="topbar">
                <div class="grid_5"><div class="site"><em>Il sito del gruppo Tea</em> <a href="/" class="arrow">Visita</a></div></div>
                <div class="grid_8"><div class="nolog"></div></div>
                <div class="grid_3"><div class="lang"><em>Italiano</em></div></div>
            </div>   
        </div>   
        <div class="container_16">
           <div class="grid_5"><div id="logo"><a href="#1" title="ConTea - Sportello online">ConTea - Sportello online</a></div></div>
           <div class="grid_11">
           		<div class="navbar">
                    <ul class="navmenu level_1">

                    </ul>
            	</div>
            </div>
        </div>
    </div>
    
    <div id="content">
    	<div class="container_16">
        	<div class="bg6">
                <div class="grid_5 colsx">
                    <div class="navbar">
                        <ul class="navmenu level_1">
							
						</ul>
                    </div>     
                    <div class="box" id="contact">
                    	<h2>Contattaci</h2>
                        <div class="ctn">
                            <h3>SEDE</h3>
                            <p>Gruppo Tea S.p.A.<br />
via Taliercio, 3 - Mantova 46100<br />
Centralino: <strong>376 4121</strong></p>
<h3>PER INFORMAZIONI, 
preventivi e CONTRATTI</h3>
<p>Numero verde: <strong>800 473165 </strong><br />
Per chi chiama da cellulare: <strong>199 4121 </strong><br />
(servizio con operatore attivo dal luned� al venerd� dalle 8.30 alle 18.00)
E-mail: <a href="mailto:clienti@teaspa.it">clienti@teaspa.it</a></p>
                        </div>
                		<div class="close"></div>
                	</div>             
               </div> 
               <div class="grid_11 main">
                    <div class="title">
                        <h1><fmt:message key="label.error"/></h1>
                    </div>                          
                    <div class="txt"><p>&nbsp;</p>
                    </div>
				</div>
	<div id="adminLogin">
    <h2 class="loginlogo"></h2>
            <br class="clearFloat" />
            <h3 class="loginIcon"><fmt:message key="label.error.403.title"/></h3>
        <p><fmt:message key="label.error.403.description"/></p>
        <p id="backLink" style="display:none"><fmt:message key="label.error.backLink.1"/>&nbsp;<a href="javascript:history.back()"><fmt:message key="label.error.backLink.2"/></a>&nbsp;<fmt:message key="label.error.backLink.3"/></p>
        <p><fmt:message key="label.error.homeLink"/>:&nbsp;<a href="<c:url value='/'/>"><fmt:message key="label.homepage"/></a></p>
        <br class="clearFloat" />
    </div>
	</div>
	<div class="clear"></div>
	</div>
	</div>
	</div>
</body>
</html>