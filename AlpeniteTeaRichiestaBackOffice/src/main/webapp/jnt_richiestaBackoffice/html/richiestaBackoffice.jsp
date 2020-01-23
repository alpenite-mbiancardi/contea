<%@page import="com.alpenite.gui.components.SelectFirstLevelReqClient"%>
<%@page import="com.alpenite.gui.components.SelectFirstLevelReqProspect"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Hashtable"%>
<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.richiestabackoffice.ws.*"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
if (currentNode.hasProperty("bpuTest")) {
    session.setAttribute("BPUSESSION", currentNode.getProperty("bpuTest").getString());
}
if (currentNode.hasProperty("bpcTest")) {
    session.setAttribute("BPCSESSION", currentNode.getProperty("bpcTest").getString()); //0010000381
}
String tipoUtente = currentNode.getUser().getProperty("j:tipoBP");
boolean isProspect = (tipoUtente.equals(Constants.TIPO_PRIVATO)) || (tipoUtente.equals(Constants.TIPO_AZIENDA));
String prefix = (isProspect)? "CA_P_" : "CA_C_"; 
pageContext.setAttribute("prefix", prefix);

Set<String> fistLevel = com.alpenite.tea.richiestabackoffice.ws.DropDownDatas.fistLevel.keySet();
pageContext.setAttribute("firstLevelList", new ArrayList<String>(fistLevel));

%>
<%--template:addResources resources="style.css" type="css" />
<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtro.js" type="javascript" 
<template:addResources type="javascript" resources="jquery.js,jquery.validate.js"/>--%>
<template:addResources type="inlinejavascript">
    <script type="text/javascript">
        $(document).ready(function() {
            $('select[name=level1]').change(function(){
            	var value = $('select[name=level1] option:selected').val();
            	if(value==""){
            		$('select[name=level2]').attr('disabled', true);
            	}else{
            		$('select[name=level2]').attr('disabled', false);
            		$.ajax({
                    	url: '${url.base}${currentNode.path}.secondDropDown.do',
                    	type: "POST",
                    	data: {"level1": $('select[name=level1] option:selected').val()},
                    	dataType: "json",
                    	success: function(json){
                    		$('select[name=level2]').empty().append(function(){
                    			//comuni = jQuery.parseJSON(json);
                    			var output = '';
                    			$.each(json, function(index, value){
                    				output += '<option value="' + index + '">'+ value + '</option>';
                    			});
                    			return output;
                    		});
                    	}
                    });
            	}
            });
            
        });

        //  MODIFICA UNISYS  

        function submitForm(){
            if ($('#textNote').val().length > 0) {
            	$('#requestForm').submit();
            } else {
                $('#errorDiv').show();
            }
        	
        }

        //  MODIFICA UNISYS fine 
        
        
    </script>
</template:addResources>

	
<div id="richiestaBackoffice">
<div class="ctn-data pag">
	<h3><fmt:message key="richiestaBackOffice.header"/></h3>
	<div id="richiestaBackoffice">
		<form method="post" id="requestForm" action="<c:url value='${url.base}${currentNode.path}.sendBackOfficeRequest.do'/>" name="richiestaBackofficeForm">
			<input type="hidden" name="redirectPage" value="${currentNode.properties['redirectPage'].string}" />
<%
			String bpc = "nd";
			if(session.getAttribute("BPCSESSION") != null)
				bpc = (String) session.getAttribute("BPCSESSION").toString();
%>

<%
			// tipo utente
			if(isProspect){
%>
				<input type="hidden" name="isClient" value="false" />
<%			}else{ %>
				<input type="hidden" name="isClient" value="true" />
<%			}%>
			<input type="hidden" name="BP" value="<%=bpc %>" />
			<table border="0" cellpadding="0" cellspacing="0" class="data">
				<tr>
					<th><label for="segnalazioni"><fmt:message key="richiestaBackOffice.level1Label"/></label></th>
					<td>
						<c:choose>
							<c:when test="${isClient eq 'false'}">
								<%
									SelectFirstLevelReqProspect sflrp = new SelectFirstLevelReqProspect("level1", "level1", null);
								%>
								<select name="level1">
									<option value="" selected="selected"><fmt:message key="richiestaBackOffice.level1Default"/></option>
									<% for (String key : sflrp.getValues()) {
										pageContext.setAttribute("key", sflrp.getMappaValori().get(key));%>
										<option value="${key}">
											<fmt:message key="richiestaBackOffice.${key}" />
										</option>
									<%} %>
								</select>
							</c:when>
							<c:otherwise>
								<%
									SelectFirstLevelReqClient sflrc = new SelectFirstLevelReqClient("level1", "level1", null);
								%>
								<select name="level1">
									<option value="" selected="selected"><fmt:message key="richiestaBackOffice.level1Default"/></option>
									<% for (String key : sflrc.getValues()) {
										pageContext.setAttribute("key", sflrc.getMappaValori().get(key));%>
										<option value="${key}">
											<fmt:message key="richiestaBackOffice.${key}" />
										</option>
									<%} %>
								</select>
							</c:otherwise>
						</c:choose>
						<%--
						<select name="level1">
							<option value="" selected=""><fmt:message key="richiestaBackOffice.level1Default"/></option>
							<% for (String key : DropDownDatas.fistLevel.keySet()) {
								pageContext.setAttribute("key", key);%>
								<option value="${prefix}${key}">
									<fmt:message key="richiestaBackOffice.${prefix}${key}" />
								</option>
							<%} %>
						</select>
						 --%>
					</td>
				</tr>
				<tr>
					<th><fmt:message key="richiestaBackOffice.level2Label"/></th>
					<td>
						<select name="level2" disabled="true">
							<option value="" selected=""><fmt:message key="richiestaBackOffice.level2Default"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<th><label for="richiesta"><fmt:message key="richiestaBackOffice.noteLabel"/> * </label></th>
					<td><textarea cols="50" rows="4" name="richestaBackofficeNote" id="textNote"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div class="pagination">
	<div id="errorDiv" style="display:none;margin-top:10px;color:red;">Attenzione il campo note è obbligatorio</div>
	<div class="paginationNavigation">
		<input type="button" onclick="submitForm();" class="btn small" value="<fmt:message key='richiestaBackOffice.buttonLabel'/>" />
	</div>
</div>
</div>
