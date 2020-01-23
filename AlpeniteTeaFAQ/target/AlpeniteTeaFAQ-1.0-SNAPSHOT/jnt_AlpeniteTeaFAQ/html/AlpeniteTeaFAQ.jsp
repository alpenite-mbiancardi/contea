
<%-- 
    Document   : AlpeniteTeaFAQ
    Created on : Feb 25, 2013, 7:46:09 PM
    Author     : giop
--%>

<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jcr:nodeProperty var="question" node="${currentNode}" name="question"/>
<jcr:nodeProperty var="answer"   node="${currentNode}" name="answer"  />
<jcr:nodeProperty var="type"     node="${currentNode}" name="type"    />

<%--
<c:if test="${not renderContext.editMode}">
    <template:addResources type="javascript" resources="res/jquery.cycle.all.js"/>
    <template:addResources type="javascript" resources="res/common.js"/>
</c:if>
 --%>

<!-- alpenite faq -->
<span class="<jcr:nodeProperty node="${currentNode}" name="type" />">${type.string}</span>
<a href="#1" class="tab">${question.string}</a>
<div class="ctn">${answer.string}</div>
