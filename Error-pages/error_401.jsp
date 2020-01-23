<%@page language="java" contentType="text/html; charset=UTF-8"
%><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.jahia.org/tags/internalLib" prefix="internal"%>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<utility:setBundle basename="JahiaInternalResources"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="robots" content="noindex, nofollow"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/res/grid.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/res/tea.css" type="text/css"/>
    <title><fmt:message key="label.login"/></title>
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
    document.onkeydown = function (e) { if ((e || window.event).keyCode == 13) document.loginForm.submit(); };
	var parametri = '?${pageContext.request.queryString}';
	$(document).ready(function() {
		$('input[name="redirect"]').val($('input[name="redirect"]').val()+parametri)
	});
    </script>
</head>
<body onload="document.loginForm.username.focus()" class="login">
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
(servizio con operatore attivo dal lunedì al venerdì dalle 8.30 alle 18.00)
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
	<div class="ctn-data pag">
    <h2 class="loginlogo"></h2>
        <ui:loginArea>
            <h3 class="loginIcon"><fmt:message key="label.login"/></h3>
            <br class="clearFloat" />
        <ui:isLoginError var="loginResult">
          <span class="error"><fmt:message key="${loginResult == 'account_locked' ? 'message.accountLocked' : 'message.invalidUsernamePassword'}"/></span>
        </ui:isLoginError>
	
        <table cellspacing="1" cellpadding="0" border="0" class="formTable data">
            <tbody>
            <tr>
                <th><fmt:message key="label.username"/></th>
                <td><input type="text" value="" style="width: 150px;" tabindex="1" maxlength="250" size="13" name="username"/></td>
            </tr>
            <tr>
                <th><fmt:message key="label.password"/></th>
                <td><input type="password" style="width: 150px;" tabindex="2" maxlength="250" size="13" name="password"/></td>
            </tr>
            </tbody>
        </table>
	
        <c:if test="${not fn:contains(param.redirect, '/administration')}">
        <br/>
        <table align="center" width="100%" cellspacing="5">
          <tr>
              <td class="alignCenter" colspan="2">
                <label for="rememberme"><fmt:message key="label.rememberme"/></label><ui:loginRememberMe id="rememberme"/>
              </td>
            </tr>
        </table>
        </c:if>
		<div class="pagination">
        <div class="paginationNavigation">
        <div id="actionBar" class="alignCenter">
          <span class="dex-PushButton">
            <span class="first-child">
              <a class="btn small ico-ok" href="#login" onClick="document.forms.loginForm.submit(); return false;" tabindex="5" title="<fmt:message key='label.login'/>"><fmt:message key="label.login"/></a>
             </span>
          </span>
        </div>
		</div>
		</div>
<%--
	<input type="hidden" name="redirect" value="${pageContext.request.requestURI}?${pageContext.request.queryString}" />
--%>
        </ui:loginArea>
		</div>
    </div>
	</div>
	 <div class="clear"></div>
	</div>
	</div>
	</div>
</body>
</html>
