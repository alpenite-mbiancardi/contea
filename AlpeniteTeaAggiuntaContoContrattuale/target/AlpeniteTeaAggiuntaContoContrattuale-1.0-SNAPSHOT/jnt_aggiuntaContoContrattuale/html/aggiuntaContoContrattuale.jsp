<%--
    Document   : aggiuntaCodiceContrattuale
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

<div class="search">
        <h3><fmt:message key="aggiuntaContoContrattuale.labelContoContrattuale" /></h3>
        <form class="frm" id="formContoContrattuale" action="${url.base}${currentNode.path}.contoContrattuale.do" method="post">
                        <label for="contoContrattuale" id="labelContoContrattuale"><fmt:message key="aggiuntaContoContrattuale.labelContoContrattuale2" /></label>
                    <input type="text" id="contoContrattuale" name="contoContrattuale" />
                    <input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
					<input type="submit" class="btn extralarge" id="bottoneInviaContoContrattuale" value="<fmt:message key="aggiuntaContoContrattuale.bottoneInvia" />" />
        </form>
</div>
