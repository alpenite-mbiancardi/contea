<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="impersonaBP">
<form method="post" id="impersonaBP" action="<c:url value='${url.base}${currentNode.path}.impersonaBP.do'/>" name="impersonaBP">
	Insert BP: <input type="text" value="BP" name="bp" />
	is Amm. Cond.?  Si: <input type='radio' name='ammCond' value='true' id="ammCond"/>
	No: <input type='radio' name='ammCond' value='false' id="ammCond" checked="checked"/>
	<input type="hidden" name="redirectPage" value="${currentNode.properties['userRedirectPage'].string}" />
	<br />
	Lang: <input type="text" name="lang" value="it" />
	<input type="submit" value="impersona" />
</form>

</div>