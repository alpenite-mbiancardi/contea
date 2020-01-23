<%-- 
    Document   : recapito
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="com.alpenite.gui.components.SelectCountries"%>
<%@page import="com.alpenite.tea.recapito.dati.Recapito"%>
<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
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
<template:addResources resources="style.css" type="css" />
<template:addResources type="javascript" resources="jquery.validate.js"/>
 <script type="text/javascript">

$(document).ready(function () {
    $("#formRecapito").validate({
        rules: {
            numeroCivico: {
                required: true
                
            }
        },
        messages: {
            numeroCivico: {
                required: "<fmt:message key='alpeniteuserregistration.errMsg.extncivico'/>"

            }
        }
    })
});
     
    </script>

.
<c:if test="${param['compagnia'] != 'Z022' }">

<%
Recapito r = WSClient.getClient(session).getRecapito(
            request.getParameter("cc")).getRitorno();
request.setAttribute("recapito", r);

SelectProvince province = new SelectProvince();
province.setValoreSelezionato(r.getProvincia());
request.setAttribute("province", province.toString());

SelectCountries countries = new SelectCountries();
countries.setValoreSelezionato(r.getPaese());
request.setAttribute("countries", countries.toString());
%>
<script>
    var annullaModifica = "<fmt:message key='recapito.bottoneAnnullaModifica'/>";
    var modifica = "<fmt:message key='recapito.bottoneModifica'/>";
</script>
<script>
    var urlComuni='${url.base}${currentNode.path}';
    var prov = '${recapito.provincia}';
    var com = "${recapito.comune}";
    var cap = '${recapito.cap}';
</script>



<h2 class="data"><button id="recapitoModificaButton" type="button" class="btn medium"><fmt:message key='recapito.bottoneModifica'/></button><fmt:message key='recapito.titolo'/></h2>
<table border="0" cellpadding="0" cellspacing="0" class="data">
	<tr>
		<th><fmt:message key='recapito.presso' /></th>
		<td colspan="3">${recapito.presso}</td>
	</tr>
	<tr>
		<th><fmt:message key='recapito.paese' /></th>
		<td>${recapito.paese}</td>
		<th><fmt:message key='recapito.comune' /></th>
		<td>${recapito.comune}</td>
	</tr>
	<tr>
		<th><fmt:message key='recapito.provincia'/></th>
		<td>${recapito.provincia}</td>
		<th><fmt:message key='recapito.cap' /></th>
		<td>${recapito.cap}</td>
	</tr>
	<tr>
		<th><fmt:message key='recapito.via' /></th>
		<td>${recapito.via}</td>
		<th><fmt:message key='recapito.civico' /></th>
		<td>${recapito.numeroCivico}</td>
		<th><fmt:message key='recapito.extecivico' /></th>
		<td>${recapito.extnumeroCivico}</td>
	</tr>
</table>
 
<div id="recapitoModificaForm"> 

	<div id="overlay">	
		<div id="content">
			<div class="main">
				<form class="frm" id="formRecapito" action="${url.base}${currentNode.path}.recapito.do" method="post" >
					<div class="ctn-data pag" style="width: 680px;">
						<h3><fmt:message key="recapito.titolo"/></h3>
						<table border="0" cellpadding="0" cellspacing="0" class="data" >
					    	<tr>
					    		<td><fmt:message key="recapito.paese"/></td>
					    		<td colspan="5">${countries}</td>
					    	</tr>
					    	<tr>
					    		<td><fmt:message key="recapito.provincia"/></td>
					    		<td>${province}</td>
					    		<td><fmt:message key="recapito.comune"/></td>
					    		<td>
					    		<select id="comune" name="comune" >
					                <option value="${recapito.comune}">${recapito.comune}</option>
					            </select>
					            </td>
					            <td><fmt:message key="recapito.cap"/></td>
					    		<td>
					    		<select name="cap" id="cap">
													<option value="${recapito.cap}">${recapito.cap}</option>
								</select>
					    		</td>
					    	</tr>
					    	<tr>
								<td><fmt:message key='recapito.presso' /></td>
								<td colspan="5"><input class="large" id="presso" name="presso" type="text" value="${recapito.presso}" /></td>
							</tr>
					    	<tr>
					    		<td><fmt:message key="recapito.via"/></td>
					    		<td colspan="3"><input class="large" id="via" name="via" type="text" value="${recapito.via}" /></td>
					    	</tr>
					    	<tr>
					    		<td><fmt:message key="recapito.civico"/></td>
					    		<td><input class="small" id="numeroCivico" name="numeroCivico" type="text" value="${recapito.numeroCivico}"/></td>
					    		<td><fmt:message key="recapito.extecivico"/></td>
					    		<td><input class="small" id="extnumeroCivico" name="extnumeroCivico" type="text"  maxlength="10" value="${recapito.extnumeroCivico}"/></td>
					    	</tr>
		   				</table>
		   			</div>
		   			<div class="pagination" style="width: 680px;">
					    <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
					    <input type="hidden" id="cc" name="cc" value="${param['cc']}" />
					    <input class="btn extralarge" id="bottoneConferma" type="button" value="<fmt:message key='recapito.bottoneConferma'/>"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</c:if>
<div class="clear"></div>

