<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ page import="org.jahia.services.cache.CacheHelper"%>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <title>${fn:escapeXml(renderContext.mainResource.node.displayableName)}</title>

	<template:addResources type="css" resources="res/jquery-ui.css"/>
	<template:addResources type="css" resources="960.css,01web.css"/>
	<template:addResources type="css" resources="res/grid.css"/>
	<template:addResources type="css" resources="res/tea.css"/>  
	<%--<template:addResources type="javascript" resources="res/jquery-1.8.3.min.js"/>--%>
	<%--<template:addResources type="javascript" resources="res/jquery.cycle.all.js"/>--%>
	<%--<template:addResources type="javascript" resources="res/common.js"/>--%>
	<template:addResources type="javascript" resources="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min.js" />
	<template:addResources type="javascript" resources="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js" />
	<template:addResources type="javascript" resources="res/common.js"/>
	<template:addResources type="javascript" resources="res/jquery2.cycle.all.js"/>
</head>

<body>






<%
// empty HTML cache
        CacheHelper.flushEhcacheByName("HTMLCache");
%>
<div class="bodywrapper logged"><!--start bodywrapper-->

<jcr:sql var="isAdmincond"
		sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/groups/${currentNode.properties['groupAmmCond'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}'  "/>
		
		<jcr:sql var="isHelpdesk"
		sql = "SELECT * FROM [jnt:member] where ISDESCENDANTNODE('/groups/${currentNode.properties['groupHelpDesk'].string}') and [jnt:member].[j:member] = '${currentUser.identifier}' "/> 
        
<c:choose>
<c:when test="${isAdmincond.nodes.size!=0 || isHelpdesk.nodes.size!=0 }">	
		<div id="header" class="log"><template:area path="header"/></div>    
</c:when>
<c:otherwise>
<div id="header"><template:area path="header"/></div>
</c:otherwise>
</c:choose>     
	<div id="content"><template:area path="pagecontent" /></div>
    <div id="footer"><template:area path="footer" /></div>
</div>
<!--stop bodywrapper-->

<c:if test="${renderContext.editMode}">
    <template:addResources type="css" resources="edit.css" />
</c:if>

<template:theme/>

<%--<div id="loader">Caricamento in corso, attendere...</div>--%>
<div id="loading"></div>
<script>


   
(function($) {
		
function showLoader() {
 
  $("#loading").show();
  $("#loading").dialog({modal: true});
   $(".ui-dialog-titlebar").hide();
  return true;
}

$("#loading").hide();

 
$("form").not(".noLoader").submit(function(e) {	
  return showLoader(); 
});

$(".navbar a").not(".noLoader").click(function(e) {
  return showLoader();
});
if (window.location.href.match('registrazione')) {
    //$('#loading').html('Attendere prego. La procedura di registrazione impiegherà dai 5 ai 10 minuti') 
  }

  if (window.location.href.match('10.100.100.152')) {
    //$('#loading').html('Attendere prego. La procedura di registrazione impiegherà dai 5 ai 10 minuti') 
    console.log("PRODDDD")

   // var $div = $('<div style="position: absolute;top: 0; color: red;font-size: 30px; padding-left: 25%;">PROD</div>').appendTo('body');
  }

})(jQuery);
</script>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-40341548-1']);
  _gaq.push (['_gat._anonymizeIp']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

  if (window.location.href.match('domande-frequenti')) {
    $('.tab').click(function() {$(this).parent().find('.ctn').attr("style","display:block")})
   

} 

</script>



</body>
</html>
