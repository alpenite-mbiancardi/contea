
<%-- 
    Document   : AlpeniteTeaModulisticaPersonal
    Created on : Feb 25, 2013, 11:08:02 AM
    Author     : giop
--%>


<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"     %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"      %>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr"         %>
<%@ taglib prefix="utility"  uri="http://www.jahia.org/tags/utilityLib"  %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>


<jcr:nodeProperty var="file"    node="${currentNode}" name="file"   />
<jcr:nodeProperty var="preview" node="${currentNode}" name="preview"/>
<jcr:nodeProperty var="image"   node="${currentNode}" name="image"  />
<jcr:nodeProperty var="type"     node="${currentNode}" name="type"    />


    <span class="img">
        <c:if test="${!empty image}">
            <c:url  var="imageUrl" value="${url.files}${image.node.path}"/>
            <img src="${imageUrl}" alt="${newsTitleEscaped}" width="69" height="77"/>
        </c:if>
    </span>
    <a href="${file.node.url}" class="download"><fmt:message key="scarica" /></a>
    <h3><fmt:message key="${type.string}" /></h3>
    <c:if test="${!empty currentNode.properties.title.string}">
        <a href="${file.node.url}">${currentNode.properties.title.string}</a>
    </c:if>
    <fmt:formatNumber var="num" pattern="### ### ###.##" type="number" value="${(file.node.fileContent.contentLength/1024)}"/>
    <span> (${num} KB)</span>


