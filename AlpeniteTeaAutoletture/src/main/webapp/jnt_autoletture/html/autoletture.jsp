<%-- 
    Document   : autoletture
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
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

<div id="autolettura">
	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form class="frm" id="moduloAutolettura" name="moduloAutolettura" method="post" action="<c:url value='${url.base}${currentNode.path}' />.autoletture.do">
					<div class="ctn-data pag">
						<h3> <fmt:message key="autolettura.valore" /></h3>
    					<table id="tabellaSvuotamenti" class="data"  cellspacing="0" cellpadding="0" border="0">
				        	<th>
				        		<td><label id="labelValoreAutolettura" for="valoreAutolettura"><fmt:message key="autolettura.valore2" /></label></td>
				        		<td><input class="obbligatorio" id="valoreAutolettura" name="valoreAutolettura" type="text" /></td>
				        	</th>
				        </table>
				    </div>
				    <div class="pagination">
				        <input id="c" name="c" type="hidden" value="${param['c']}" />
				        <input id="cc" name="cc" type="hidden" value="${param['cc']}" />
				        <input type="hidden" name="servizio" value="${param['servizio']}" />
				        <input type="hidden" id="isAcqua" name="isAcqua" value="${param['h2o']}" />
				        <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
				        <input type="submit" class="btn large" id="bottoneConferma" value="<fmt:message key='autolettura.bottoneConferma' />" />
				    </div>
    			</form>
			</div>
		</div>
	</div>
</div>