<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String n = request.getParameter("n");
	String t = request.getParameter("t");
	String url = "/scaricaDocumento.jsp";
	String finalUrl = url+"?n="+n+"&t="+t;
	response.sendRedirect(finalUrl);
%>

<%-- 
<jsp:forward page="/scaricaDocumento.jsp">
	<jsp:param value="${param['n']}" name="n"/>
	<jsp:param value="${param['t']}" name="t"/>
</jsp:forward>


<c:redirect url="/scaricaDocumento.jsp">
	<jsp:param value="${param['n']}" name="n"/>
	<jsp:param value="${param['t']}" name="t"/>
</c:redirect>
--%>
<%--
<a href="/scaricaDocumento.jsp?n=${riga.fileId}&t=${riga.fileObj}" target="_blank"><fmt:message key="elencoDocumenti.tabellaDocumenti.header.download"/></a>
--%>