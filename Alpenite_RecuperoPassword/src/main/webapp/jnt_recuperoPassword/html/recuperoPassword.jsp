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



<template:addResources type="javascript" resources="jquery.js,jquery.validate.js"/>


<script type="text/javascript">
	$(document).ready(function() {
            $("#recuperoPassword").validate({
	                rules: 
	                {
	                    email: 
	                    {
	                        required: true,
	                        email: true
	                    },
	                    username:{
	                    	required: true,
	                    }
	                },
	                messages: 
	                {                         	
	                    email: "<fmt:message key='recuperopassword.richiediMail'/>",
	                    username: "<fmt:message key='recuperopassword.richiediUsername'/>"
	                }
	                
            	});
        	});
 </script>

<div class="recuperoPassword">  
<div class="search">
<h3><fmt:message key="recuperopassword.titolo.modulo"/></h3>     
<form class="frm" method="post" id="recuperoPassword" name="recuperoPassword" action="<c:url value='${url.base}${currentNode.path}.recuperoPasswordAction.do'/>" >    
	<input type="hidden" name="userredirectpage" value="${currentNode.properties['userredirectpage'].string}" />
	<input type="hidden" name="verificationPage" value="${currentNode.properties['verificationPage'].string}" />
	<input type="hidden" name="group" value="${currentNode.properties['group'].string}" />
	<%--<input type="hidden" name="contenuto_mail" value="${currentNode.properties['mail'].string}" /> --%>	
	<c:choose>
		<c:when test="${not empty currentResource.locale}">
    		<input type="hidden" name="lang" value="${currentResource.locale}" />
		</c:when>
		<c:otherwise>
			<input type="hidden" name="lang" value="${param.locale}" />
		</c:otherwise>
	</c:choose>
	<input type="hidden" name="currentNode" value="${currentNode.identifier}" />
	<input type="hidden" name="from" value="${currentNode.properties['from'].string}"/>    
	<input type="hidden" name="subject" value="${currentNode.properties['subject'].string}"/>  
	<div>
		<span class="label">
			<label for="username"><fmt:message key='recuperopassword.label.username'/></label>
		</span>
		<span class="field">
			<input type="text" name="username" id="username" onkeyup="this.value = this.value.toUpperCase();"/>
		</span>
		<span class="label">		
			<label for="email"><fmt:message key='recuperopassword.label.email'/></label>
		</span>
		<span class="field">
			<input type="text" name="email" id="email"/>
		</span>				           		     
	</div>			
    <div>
    	<input type="submit" class="btn medium" value="<fmt:message key='recuperopassword.submit'/>"/>
    </div>                 		 
</form>
</div>
</div>

<script>
	$( window ).load(function() {
		$("#email").removeAttr("onkeydown");
	});
</script>