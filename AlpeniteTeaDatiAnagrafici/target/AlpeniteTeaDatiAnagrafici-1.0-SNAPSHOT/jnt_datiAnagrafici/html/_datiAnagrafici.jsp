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
String tipoUtente = currentNode.getUser().getProperty("j:tipoUtente");
boolean isProspect = (tipoUtente == Constants.TIPO_PRIVATO) || (tipoUtente == Constants.TIPO_AZIENDA);
%>
<%--template:addResources resources="style.css" type="css" />
<template:addResources resources="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js" type="javascript" />
<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtro.js" type="javascript" --%>
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
        
    </script>
</template:addResources>

<div id="richiestaBackoffice">
<form method="post" id="registrationForm" action="<c:url value='${url.base}${currentNode.path}.sendBackOfficeRequest.do'/>" name="richiestaBackofficeForm">
<fmt:message key="richiestaBackOffice.header"/><br>
<%
    String bpc = "nd";
    if(session.getAttribute("BPCSESSION") != null)
    	bpc = (String) session.getAttribute("BPCSESSION").toString();
    
%>
		<fmt:message key="richiestaBackOffice.level1Label"/>
		<select name="level1">
			<option value=""><fmt:message key="richiestaBackOffice.level1Default"/></option>
			<%
				//TODO: insert check about cliente or prospect
					String prefix = (isProspect)? "CA_P_" : "CA_C_";
					for (String key : DropDownDatas.fistLevel.keySet()) {
						out.print("<option value=\"" + prefix + key + "\">" + DropDownDatas.fistLevel.get(key)  + "</option>");
					}
			%>
		</select>
		<br>
		<fmt:message key="richiestaBackOffice.level2Label"/>
		<select name="level2" disabled="true">
			<option value=""><fmt:message key="richiestaBackOffice.level2Default"/></option>
		</select>
		<br>
		<fmt:message key="richiestaBackOffice.noteLabel"/>
		<textarea name="richestaBackofficeNote">
		</textarea>
		<br>
		<div class="buttons">
			<input type="submit" class="button"
				><fmt:message key="richiestaBackOffice.level1Label"/></input>
		</div>
		</form>
</div>
