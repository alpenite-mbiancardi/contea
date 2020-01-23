<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@page import="java.util.List"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
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

<div id="moduloImpersonaUtente">

<div class="ctn-data pag">
<h3><fmt:message key="impersonaUtente.title" /></h3>
    <form class="frm" id="formModificadatiAnagrafici" method="post" action="<c:url value='${url.base}${currentNode.path}'/>.impersonaUtente.do">
    <input class="medium left" name="username" type="text" id="username" value="" />
	<input type="hidden" id="userredirectpage" name="userredirectpage" value="${currentNode.properties['userRedirectPage'].string}" />
	<input type="hidden" name="group" value="${currentNode.properties['group'].string}" />
	<input type="hidden" name="groupAmmCond" value="${currentNode.properties['groupAmmCond'].string}" />
	<input type="hidden" name="currentUser" value="${currentUser.username}" />
	<input type="submit"  class="btn medium" id="bottoneimpersonautente" name="bottoneimpersonautente" value="<fmt:message key="bottoneimpersonautente" />" />
    </form>

</div>
</div>