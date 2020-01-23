<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@page import="javax.imageio.spi.ServiceRegistry"%>
<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@page import="org.jahia.services.render.RenderContext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.jahia.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>

<template:addResources resources="jquery.uitablefilter.js" type="javascript" />
<template:addResources resources="jquery.tablesorter.min.js" type="javascript" />
<template:addResources resources="filtro.js" type="javascript" />

<%
JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
String group = currentNode.getPropertyAsString("group");

RenderContext currentResource = (RenderContext) request.getAttribute("renderContext");
int siteID = currentResource.getSite().getID();
String currentUser = "";


List<String> miaLista = ServicesRegistry.getInstance().getJahiaUserManagerService().getUsernameList(); 
Iterator<String> iterUserList = miaLista.iterator();
%>

<h3><jcr:nodeProperty name="jcr:title" node="${currentNode}" /></h3>

<div id="tabellaUtenti">

<script>
    var msg = "<fmt:message key="sbloccaUtente.filtri.messaggio"/>";
</script>
<div class="search">
<h3><fmt:message key="sbloccaUtente.filtri.filtro"/></h3>
<div id="filtri">
    <div class="grid_10">
        <label id="labelFiltro" for="filtro"><fmt:message key="sbloccaUtente.filtri.filtro"/></label>
        <input name="filtro" id="filtro" type="text" />
        <input type="button" class="btn medium" id="bottoneConferma" name="bottoneConferma" value="<fmt:message key="sbloccaUtente.filtri.bottoneConferma"/>" />
        <input type="button" class="btn medium left" id="bottoneAnnulla" name="bottoneAnnulla" value="<fmt:message key="sbloccaUtente.filtri.bottoneAnnulla"/>" />
    </div>

</div>
</div>
<div class="clear"></div>
<div class="txt">
<div class="messaggio" id="messaggio"></div>
</div>

<div class="box pag">
<div class="open"></div>
<table border="0" cellpadding="0" cellspacing="0" class="tbl tabella tablesorter" id="tabellaUtenti">
	<thead id="tabellaUtentiHeader" class="header">
	<tr>
		<th class="colonna"><fmt:message key="sbloccoUtente.username"/></th>
		<th class="colonna"><fmt:message key="sbloccoUtente.nomeEcognome" /></th>
		<th class="colonna"><fmt:message key="sbloccoUtente.bp" /></th>
	    <th class="colonna"><fmt:message key="sbloccoUtente.stato"/></th>
	    <th class="colonna"><fmt:message key="sbloccoUtente.impersona" /></th>
	    <th class="last colonna"><fmt:message key="sbloccoUtente.sblocca" /></th>
	</tr>
	</thead>
	<tbody id="tabellaUtentiRighe">
	<%while(iterUserList.hasNext()){
		currentUser = iterUserList.next(); 
		if(SbloccoUtente.checkMemberTEA(currentUser, group, siteID)){
			JahiaUser user = ServicesRegistry.getInstance().getJahiaUserManagerService().lookupUser(currentUser);
		%>	 		
			<tr class="riga">
				<td class="focus bg colonna"><%=currentUser%></td>
				<td class="bg colonna"><%=user.getProperty("j:firstname") %> &nbsp; <%=user.getProperty("j:lastname") %></td>
				<td class="bg colonna"><%=user.getProperty("j:bp") %></td>
				<td class="bg colonna"><%if(user.getProperty("j:accountLocked").equals("true")){ %>
		   			<fmt:message key="sbloccoUtente.locked" />
		   		<%}else{ %>
		   			<fmt:message key="sbloccoUtente.unlocked" />
		   		<%} %>
		   		</td>
				<td>
				<form id="impersona" method="post" action="<c:url value='${url.base}${currentNode.path}'/>.impersonaUtente.do">
				<input name="username" type="hidden" id="username" value="<%=user.getProperty("j:nodename")%>" />
				<input type="hidden" id="userredirectpage" name="userredirectpage" value="${currentNode.properties['userRedirectPage'].string}" />
				<input type="hidden" name="group" value="${currentNode.properties['group'].string}" />
				<input type="hidden" name="groupAmmCond" value="${currentNode.properties['groupAmmCond'].string}" />
				<input type="hidden" name="currentUser" value="${currentUser.username}" />
				<input type="submit" id="bottoneimpersonautente" name="bottoneimpersonautente" class="btn large" value="<fmt:message key="bottoneimpersonautente" />" />
				</form>
				</td>
				<td>
				<form class="frm" method="post" id="sbloccaAction" action="<c:url value='${url.base}${currentNode.path}.sbloccaAction.do'/>" name="sbloccaAction">
					<input type="hidden" id="getUser" name="getUser" value="<%=currentUser%>">
					<input type="hidden" name="userredirectpage" value="${currentNode.properties['userRedirectPage'].string}" />  
					<%if(user.getProperty("j:accountLocked").equals("true")){ %>
						&nbsp;
					<%}else{ %>
						<input type="hidden" name="action" value="lock" />
						<input type="submit" class="btn small" value='<fmt:message key="sbloccoUtente.blocca"/>'/>
					<%} %>
				</form> 
				</td>
			</tr>		
		<%
		}
		}
		%>
		</tbody>
</table>
</div>
</div>



<div class="clear"></div>
<div class="clear"></div>
