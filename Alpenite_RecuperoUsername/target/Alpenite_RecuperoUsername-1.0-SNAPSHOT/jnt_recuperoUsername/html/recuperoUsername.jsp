<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>

<template:addResources type="javascript" resources="jquery.js,jquery.validate.js"/>
<template:addResources type="inlinejavascript">
<script type="text/javascript">
 $(document).ready(function() {
            $("#recuperoUsername").validate({
                rules: {
                    email: {
                        required: true,
                        email: true
                    },
                    captcha: "required"
                },
                messages: {
                    email: '<fmt:message key="recuperoUsername.mailNonValida"/>'
                }
            });
        });
 </script>    
</template:addResources>
 
      <div class="recuperoUsernameDiv">
      <div class="search">
  <form class="frm" method="post" id="recuperoUsername" name="recuperoUsername" action="<c:url value='${url.base}${currentNode.path}.getUsername.do'/>" >
        
        <input type="hidden" name="userredirectpage" value="${currentNode.properties['userRedirectPage'].string}" />
        <input type="hidden" name="group" value="${currentNode.properties['group'].string}" />
        <%--<input type="hidden" name="contenuto_mail" value="${currentNode.properties['mail'].string}" /> --%>
        <input type="hidden" name="mailSubject" value="${currentNode.properties['mailSubject'].string }"/>
	    <input type="hidden" name="from" value="${currentNode.properties['from'].string }" />
	    <c:choose>
		<c:when test="${not empty currentResource.locale}">
    		<input type="hidden" name="lang" value="${currentResource.locale}" />
		</c:when>
		<c:otherwise>
			<input type="hidden" name="lang" value="${param.locale}" />
		</c:otherwise>
		</c:choose>
		<input type="hidden" name="currentNode" value="${currentNode.identifier}" />
	    
        <div id="mail">
    		<div class="label">	
				<label for="email"><fmt:message key="recuperoUsername.mail"/></label>
			</div>
			<div class="value">
				<input type="text" name="email" id="email"/>				           		     
			</div>			
        </div>

        <div class="divButtonMail">
        	<input type="submit" class="btn medium" value="<fmt:message key="recuperoUsername.button"/>"/>
        </div>                 	
    </form>
    </div>
</div>