<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s"        uri="http://www.jahia.org/tags/search" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="search">
<h3><fmt:message key="search.form.title" /></h3>
    
    <c:url var="searchUrl" value='${url.base}${currentNode.properties.result.node.path}.html'/>
    <c:if test="${jcr:isNodeType(renderContext.mainResource.node,'jnt:page')}">
        <c:set var="searchPath" value="${renderContext.mainResource.node.path}"/>
    </c:if>
    <c:if test="${!jcr:isNodeType(renderContext.mainResource.node,'jnt:page')}">
        <c:set var="searchPath" value="${jcr:getParentOfType(renderContext.mainResource.node,'jnt:page').path}"/>
    </c:if>
    
    <c:set var="searchType" value="${currentNode.properties.nodeType.string}"/>

    <%-- Form di ricerca testuale--%>
    <s:form method="get" action="${searchUrl}" class="frm">
        <label for="searchTerm"><fmt:message key="search.form.cerca"/></label>
        <s:term id="searchTerm" match="all_words" value="" searchIn="siteContent" class="text"/>
        
        <s:pagePath value="${searchPath}" display="false" includeChildren="true"/>
       	<s:nodeType value="${searchType}" display="false" />       	
       	
        <label for="searchTerm"><fmt:message key="search.form.filter" /> </label>
        <s:nodeProperty name="type" id="searchTerm" display="true" nodeType="${searchType}"/>
        
        <label for="searchCategory"><fmt:message key="search.form.category" /></label>
        <s:nodeProperty name="comune" id="searchTermComune" display="true" nodeType="${searchType}"/>
       
        <%--
        <jcr:nodeProperty node="${currentNode}" name="j:category" var="startCategory"/>
        <jcr:sql var="categories" sql="SELECT * FROM [jnt:category] WHERE ISDESCENDANTNODE('${startCategory.node.path}')"/>
        <label for="searchCategory"><fmt:message key="search.form.category" /></label>
        <select id="categorySearch" name="search-category">
        	<option value="all"><fmt:message key="search.form.all_category"/></option>
        	<c:forEach items="${categories.nodes}" var="category" varStatus="status">
        		<option value="${category.identifier}">${category.properties['jcr:title'].string}</option>
        	</c:forEach>
        </select>
         --%>
        
        <s:language value="${renderContext.mainResource.locale}" display="false" />
        <input class="btn small" type="submit"  value="<fmt:message key='search.submit'/>"/>
    </s:form>

    </div>
    <div class="clear"></div>
    <div class="clear"></div>


