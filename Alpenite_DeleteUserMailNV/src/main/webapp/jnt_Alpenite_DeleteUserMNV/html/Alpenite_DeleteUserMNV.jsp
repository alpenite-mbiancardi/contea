<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@page import="org.jahia.services.usermanager.JahiaUser"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="org.jahia.services.render.RenderContext"%>
<%@page import="org.jahia.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr"%>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>

<%
JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");
String group = currentNode.getPropertyAsString("group"); 

RenderContext currentResource = (RenderContext) request.getAttribute("renderContext");
int siteID = currentResource.getSite().getID();

List<String> miaLista = DeleteUserMNV.getUsernameList(group, siteID);

String currentUser = "";
String stato = "";
Iterator<String> iterUserList = miaLista.iterator();
%>
<div class="ctn-data pag">
<h3><fmt:message key='deleteusermnv.titolo.modulo'/></h3>	
<form class="frm" method="post" id="sbloccaAction" action="<c:url value='${url.base}${currentNode.path}.deleteUserMNVAction.do'/>" name="sbloccaAction">
	<input type="hidden" name="userredirectpage" value="${currentNode.properties['userRedirectPage'].node.path}" />	
	<input type="hidden" name="group" value="${currentNode.properties['group'].string}" />
	<input type="submit" class="btn large" value="<fmt:message key='deleteusermnv.button.cancella'/>"/>
</form>
		<%
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dfOutput = new SimpleDateFormat("dd-MM-yyy");
		int top = 0; 
		//ab - se esistono utenti non validati
		if(miaLista.size()!=0)
		{
			%>
			<div class="box pag">
			<div class="open"></div>
			<table border="0" cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<th><fmt:message key="deleteUser.username"/></th>
					<th><fmt:message key="deleteUser.nomeEcognome" /></th>
					<th><fmt:message key="deleteUser.data" /></th>
					<th><fmt:message key="deleteUser.mail" /></th>
					<th>&nbsp;</th>
				</tr>	  	 		
		  	<%  	
			while(iterUserList.hasNext())
			{	
				currentUser = iterUserList.next(); 
				JahiaUser user = ServicesRegistry.getInstance().getJahiaUserManagerService().lookupUser(currentUser);	
			%>	
			<tr> 
				<td class="focus bg"><%=currentUser%></td>
				<td class="bg"><%=user.getProperty("j:firstname") %> &nbsp; <%=user.getProperty("j:lastname") %></td>
				<td class="bg"><%= dfOutput.format(df.parse(user.getProperty("jcr:created"))) %></td>
				<td class="bg"><%=user.getProperty("j:email") %></td>
				<td>
					<form method="post" id="deleteSingleUser" action="<c:url value='${url.base}${currentNode.path}.deleteSingleUser.do'/>" name="deleteSingleUser">
						<input type="hidden" name="username" value="<%=currentUser%>" />
						<input type="hidden" name="userredirectpage" value="${currentNode.properties['userRedirectPage'].node.path}" />
						<input type="submit" class="button btn large" name="delete" value="<fmt:message key='deleteUser.rimuoviUtente'/>" />
					</form>
				</td>
	   		</tr>		
			<%		
			}
			%>			
		</table>
		</div>		
	<%
}
	//ab - se non esistono utenti non validati
	else
	{
	%>
		<p><fmt:message key='deleteusermnv.listavuota'/></p>
	<%
	} 
	%>	
</div>