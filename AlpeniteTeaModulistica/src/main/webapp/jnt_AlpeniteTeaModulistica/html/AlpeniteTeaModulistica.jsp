<%-- 
    Document   : AlpeniteTeaModulistica
    Created on : Feb 22, 2013, 5:19:57 PM
    Author     : giop
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>

--%>

<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"     %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"      %>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr"         %>
<%@ taglib prefix="utility"  uri="http://www.jahia.org/tags/utilityLib"  %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>

<jcr:nodeProperty var="file"    node="${currentNode}" name="file"   />
<%--
<jcr:nodeProperty var="type"    node="${currentNode}" name="type"   />
--%>


        <a href="${file.node.url}" class="download"><fmt:message key="scarica" /></a>
        <h3>
            <c:if test="${!empty currentNode.properties.title.string}">
                ${currentNode.properties.title.string}
            </c:if>
        </h3>
        <a href="${file.node.url}">
                <c:if test="${!empty currentNode.properties.subtitle.string}">
                        ${currentNode.properties.subtitle.string}
                </c:if>
                </a>
        <fmt:formatNumber var="num" pattern="### ### ###.##" type="number" value="${(file.node.fileContent.contentLength/1024)}"/>
        <span> (${num} KB)</span>

