
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s"        uri="http://www.jahia.org/tags/search" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="jcr"      uri="http://www.jahia.org/tags/jcr" %>

<jcr:sql var="nodiResult" sql="select * from [jnt:user] where [j:nodename]='${currentUser.name}'"/>


<h1>${currentNode.properties.title}</h1>
<div class="mySearchForm">
    <c:url var="searchUrl"      value='${url.base}${currentNode.properties.result.node.path}.html'/>
    <c:set var="startSearching" value="Start searching ..."                                       />
    <c:set var="searchPath"     value="${renderContext.mainResource.node.path}"                   />

    <%-- Form di ricerca testuale--%>
    <s:form method="get" action="${searchUrl}">
        <label for="searchTerm">Start searching:</label>
        <s:term id="searchTerm" match="all_words" value="${startSearching}" searchIn="siteContent" onfocus="if(this.value=='${startSearching}')this.value='';" onblur="if(this.value=='')this.value='${startSearching}';" class="text-input"/>
        <!-- input type="submit" value="Search" title="Search"/-->
        <s:pagePath value="${searchPath}" display="false" includeChildren="true"/>
        <!-- s:site value="${renderContext.site.name}" display="false"/ -->
    </s:form>
</div>
<div class="clear"></div>
<div class="clear"></div>