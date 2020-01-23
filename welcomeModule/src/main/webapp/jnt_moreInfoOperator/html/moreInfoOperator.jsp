<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>

<%-- <jcr:sql var="isAdmincond"
	sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/groups/${currentNode.properties['groupAmmCond'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}'  "/> --%>

<jcr:sql var="isHelpdesk"
		sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('${currentNode.properties['groupHelpDeskPath'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}' "/> 
      																																																					
<div class="container_16">
	<div class="bottombar">
    	<div class="grid_16">
    		<div class="log">    			 
				<c:choose>
					<c:when test="${isHelpdesk.nodes.size!=0}">
						<span>
							<strong><fmt:message key="moreInfoOperator.label.impersonando.title"/></strong>&nbsp;${sessionScope['UTINFSESSION']}			
						</span>
					</c:when>
				</c:choose>									
				<span>					
					<strong><fmt:message key="moreInfoOperator.label.guardando.title"/></strong>&nbsp;${sessionScope['UINFSESSION']}
				</span>  				          
        	</div>
    	</div>
	</div>   
</div>
