	<%-- 
	    Document   : invioFattura
	    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
	--%>
	<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
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
	<%@page import="com.alpenite.tea.wdslfascicolo.GetDocCliente"%>

	<template:addResources resources="script.js" type="javascript" />
	<%
	String[] flag = WSClient.getClient(session).getFlagCartaceoEmail(
	            request.getParameter("cc")).getRitorno();
	String c = "checked=\"checked\"";
	request.setAttribute("copiaCartacea", "".equals(flag[0])?"":c);
	request.setAttribute("copiaEmail",    "".equals(flag[1])?"":c);
	request.setAttribute("pdfInEmail",    "".equals(flag[2])?"":c);
	/* 07/03/2016 [CB] Aggiunta flag dettaglio bolletta per fatture che lo permettono*/
	if(flag.length == 4){
		request.setAttribute("showDetbol", true);
		request.setAttribute("detbol",    "".equals(flag[3])?"":c);
	} else {
		request.setAttribute("showDetbol", false);
	}
	String e = request.getParameter("e");
	if (e != null && e.equals("true")) {
	    session.setAttribute("e", true);
	} else if (e != null && e.equals("false")) {
	    session.setAttribute("e", false);
	}
	request.setAttribute("visualizzaCheck", session.getAttribute("e"));




	%>
	<%
	request.setAttribute("elencoDocumentiFascicolo", GetDocCliente.getItemDoc(request.getParameter("cc")));
	%>

	<h2 class="line data">
	<% 
		if (! request.getParameter("servizio").equals("EE")){
		%>
			<input type="button" id="bottoneModificaFlag" name="bottoneModificaFlag" class="btn medium" value="<fmt:message key='invioFatture.bottoneModifica' />" />
		<%}%>
	<fmt:message key='invioFatture.title' /></h2>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<tr>
	    	<th><fmt:message key="infoCc.cc"/></th>
	        <td>${param['cc']}</td>
	        <th><fmt:message key="invioFatture.copiaCartacea" /></th>
	        <td>
	        <% if(!"".equals(flag[0])){ %>
	        <fmt:message key='flag.attivo' />
	        <%}else{ %>
	        <fmt:message key='flag.nonattivo' />
	        <%} %>
	        </td>
	    </tr>
	    <tr>
	    	<td colspan="2">&nbsp;</td>
	    	<th><fmt:message key="invioFatture.email" /></th>
	    	 <td>
	    	<% if(!"".equals(flag[1])){ %>
	        <fmt:message key='flag.attivo' />
	        <%}else{ %>
	        <fmt:message key='flag.nonattivo' />
	        <%} %>
	        </td>
	    </tr>
	    <tr>
	    	<td colspan="2">&nbsp;</td>
	    	<th><fmt:message key="invioFatture.pdfInEmail" /></th>
	    	 <td>
	    	<% if(!"".equals(flag[2])){ %>
	        <fmt:message key='flag.attivo' />
	        <%}else{ %>
	        <fmt:message key='flag.nonattivo' />
	        <%} %>
	        </td>
	    </tr>
	    <c:if test="${showDetbol}">
	    	<tr>
	    		<td colspan="2">&nbsp;</td>
	    		<th><fmt:message key="invioFatture.detbol" /></th>
	    		<td>
	    			<%if(!"".equals(flag[3])){%>
	    				<fmt:message key="invioFatture.detbolAttivo"/>
	    			<%} else {%>
	    				<fmt:message key="invioFatture.detbolNonattivo"/>
	    			<%}%>
	    		</td>
	    	</tr>
	    </c:if>
	</table>

	<div id="dialogCheckSospensione" title="<fmt:message key="invioFatture.titolo"/>">


	<div id="overlay">	
	<div id="content">
		<div class="main" >
	  
	<h1 style=""><fmt:message key="invioFatture.messaggioAlert1"/> <br><fmt:message key="invioFatture.messaggioAlert2"/></h1><br><br>
	<a id="okSospensione" data-role="button" class=" btn btn-extralarge" style="width: 50px;    color: white;">SI</a> 
	<a id="noSospensione" data-role="button" class="btn btn-extralarge " style="width:49px   ; color: white;">NO</a>

	</div>

	<style>
	#dialogCheckSospensione {
	    display: none;
	}

	.ui-dialog-title, .ui-dialog-content, .ui-widget-content {
	    font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
	    font-size: 12pt;
	}
	td {
		text-align:left !important;
	}
	</style>
	</div>
	</div>
	</div>
	<div id="moduloInvioFatture">

		<div id="overlay">	
			<div id="content">
				<div class="main">
				    <form id="formInvioFatture" action="<c:url value='${url.base}${currentNode.path}'/>.invioFattura.do" method="post">
						<div class="ctn-data pag">
							<h3><fmt:message key="invioFatture.header" /></h3>
							<table border="0" cellpadding="0" cellspacing="0" class="data">
								<tr>
									<td colspan="2">${currentNode.properties['informativa'].string}</td>
								</tr>
								<c:if test="${visualizzaCheck}" >
									<tr>
										<th><label for="copiaCartacea"><fmt:message key="invioFatture.copiaCartacea" /></label></th>
										<td><input ${copiaCartacea} type="checkbox" id="copiaCartacea" name="copiaCartacea" value="1" /></td>
									</tr>
								</c:if>
								<tr>
									<th><label for="copiaEmail"><fmt:message key="invioFatture.email" /></label></th>
									<td><input ${copiaEmail} type="checkbox" id="copiaEmail" name="copiaEmail" value="1"/></td>
								</tr>
								<tr>
									<th><label for="pdfInEmail"><fmt:message key="invioFatture.pdfInEmail" /></label></th>
									<td><input ${pdfInEmail} type="checkbox" id="pdfInEmail" name="pdfInEmail" value="1"/></td>
								</tr>
								<c:if test="${showDetbol}">
									<tr>
										<th><label for="detbol"    ><fmt:message key="invioFatture.detbol.change"/></label></th>
										<td><input ${detbol}     type="checkbox" id="detbol"     name="detbol"     value="1"/></td>
									</tr>
								</c:if>
							</table>
							</div>
							<div class="pagination">
								<input type="hidden" id="conferma" name="conferma" value="${url.base}${renderContext.mainResource.node.path}" />
								<input type="hidden" id="cc" name="cc" value="${param['cc']}" />
								<input type="submit" class="btn large" id="bottoneConferma" value="<fmt:message key='invioFatture.bottoneConferma' />" />
							</div>
				    </form>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div>
	<c:if test="${not empty elencoDocumentiFascicolo}">
	<h2 class="line data"> Fascicolo cliente
	<input type="button" id="bottonefascicolocliente" name="bottonefascicolocliente" class="btn medium" value="Visualizza" >
	</h2>
	</div>
	
	<div id="modulofascicolocliente">

		<div id="overlay">	
			<div id="content" style="width:100%">
				<div class="main" style="width:905px">
				    <form id="formInvioFatture" action="<c:url value='${url.base}${currentNode.path}'/>.invioFattura.do" method="post">
						<div class="ctn-data pag" style="width:98%;margin-left: 2%;">
							
							<table id="tabellaDocumenti" class="tbl" style="width:98%">
								
							<tbody id="tabellaDocumentiRighe">
							



							
							</tbody>
							</table>
							

							<c:forEach items="${elencoDocumentiFascicolo}" var="rigaD" varStatus="count">
								 <c:if test = "${fn:contains(rigaD.sezioneIt, 'Adesione')}">
								 	
							         <table id="tabellaDocumenti-1" class="tbl" style="width:98%">
									<h3 class="contratto-fol2">Adesione Firma Elettronica Avanzata</h3>
										<tbody id="tabellaDocumentiRighe">
<tr>
											<td class="colonna" style="width:400px">${rigaD.definizioneIt}</td>
											<td class="colonna" style="width:140px">${rigaD.estensione}</td>
											<td class="colonna" style="width:110px">${rigaD.data}</td>
											<td class="colonna download">
<c:choose>
												    <c:when test="${rigaD.estensione=='MP3'}">
												        <a href="/scaricaDocumento.jsp?n=${rigaD.arcDocId}&t=${rigaD.sapObject}&name=${rigaD.definizioneIt}.mp3" class="download link1" target="_blank"><span style="color: #338087;font-size: 8.5pt" >Download<span></a> 
												        <br />
												    </c:when>    
												    <c:otherwise>
												        <a href="/scaricaDocumento.jsp?n=${rigaD.arcDocId}&t=${rigaD.sapObject}&name=${rigaD.definizioneIt}.pdf" class="download link1" target="_blank"><span style="color: #338087;font-size: 8.5pt" >Download<span></a>  
												        <br />
												    </c:otherwise>
												</c:choose>
</td></tr>
										</tbody>
									</table>
							      </c:if>
							</c:forEach>



							<c:forEach items="${elencoDocumentiFascicolo}" var="rigaD" varStatus="count">
								 <c:if test = "${fn:contains(rigaD.sezioneIt, 'Contratto')}">
								 	
							         <table id="tabellaDocumenti-2" class="tbl" style="width:98%">
										<h3 class="contratto-fol">${rigaD.sezioneIt}</h3>
										<tbody id="tabellaDocumentiRighe">
<tr>
											<td class="colonna" style="width:400px">${rigaD.definizioneIt}</td>
											<td class="colonna" style="width:140px">${rigaD.estensione}</td>
											<td class="colonna" style="width:110px">${rigaD.data}</td>
											<td class="colonna download">
												<c:choose>
												    <c:when test="${rigaD.estensione=='MP3'}">
												        <a href="/scaricaDocumento.jsp?n=${rigaD.arcDocId}&t=${rigaD.sapObject}&name=${rigaD.definizioneIt}.mp3" class="download link1" target="_blank"><span style="color: #338087;font-size: 8.5pt" >Download<span></a> 
												        <br />
												    </c:when>    
												    <c:otherwise>
												        <a href="/scaricaDocumento.jsp?n=${rigaD.arcDocId}&t=${rigaD.sapObject}&name=${rigaD.definizioneIt}.pdf" class="download link1" target="_blank"><span style="color: #338087;font-size: 8.5pt" >Download<span></a>  
												        <br />
												    </c:otherwise>
												</c:choose>

												
</td></tr>
										</tbody>
									</table>
							      </c:if>
							</c:forEach>
							
							
							<div class="pagination">
							<div class="paginationNavigation">
							
							</div>
							</div>
							</div>
				    </form>
				</div>
		</div>
	</div>
	</div>
</c:if>

	<div class="clear"></div>

	<script type="text/javascript">
	

	$(".link1").click(function () {
	         var url1 = $(this).attr("href");
	         window.open(url1 , '_blank');
	        
	});
	var cnt = $( ".contratto-fol" ).html();
	console.log(cnt);
	var status1 = 0;
	$( ".contratto-fol" ).each(function( index ) {
		console.log($(this).html() );
		if(status1 > 0){
  		if($(this).html() == cnt) {
  			
  			$(this).hide();
	  		}
	  			
	  	}
	  	status1++;
	});

	var cnt2 = $( ".contratto-fol2" ).html();
	console.log(cnt);
	var status2 = 0;
	$( ".contratto-fol2" ).each(function( index ) {
		console.log($(this).html() );
		if(status2 > 0){
  		if($(this).html() == cnt2) {
  			
  			$(this).hide();
	  		}
	  			
	  	}
	  	status2++;
	});

	</script>