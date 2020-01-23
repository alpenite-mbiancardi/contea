<%@page import="com.alpenite.tea.documentale.utils.DateCommon"%>
<%@page import="com.alpenite.tea.documentale.dto.VisualizzazioneDocumentoFiltri"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core"		%>
<%@ taglib prefix="fn"	   uri="http://java.sun.com/jsp/jstl/functions"   %>
<%@ taglib prefix="jcr"	  uri="http://www.jahia.org/tags/jcr"			%>
<%@ taglib prefix="fmt"	  uri="http://java.sun.com/jsp/jstl/fmt"		 %>
<%@ taglib prefix="query"	uri="http://www.jahia.org/tags/queryLib"	   %> 
<%@ taglib prefix="pager"	uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="utility"  uri="http://www.jahia.org/tags/utilityLib"	 %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib"	%>

<template:addResources type="javascript" resources="visualizzaDocumenti.js"	 />
<c:choose>
	<c:when test="${renderContext.editMode}">	
		jnt_visualizzazioneDocumenti		
	</c:when>
	<c:otherwise>
		<c:set var="stringaComuni" value="${currentUser.properties['j:comuniDocumentale']}"/>
		<c:choose>
			<c:when test="${not empty param['comune']}">
				<c:set var="comune" value="[comune] LIKE '%${param['comune']}%' AND " />  
			</c:when>
			<c:when test="${not empty stringaComuni}">
				<c:set var="listaComuni" value="${fn:split(stringaComuni,',')}" />
				<c:set var="comune" value="("/>
				<c:forEach var="item" items="${listaComuni}">
					<c:set var="comune" value="${comune}[comune] LIKE '%${item}%' OR "/>
					<c:set var="last" value="${item}"/>
				</c:forEach>
				<c:set var="comune" value="${comune}[comune] LIKE '%${last}%') AND "/>
			</c:when>
			<c:otherwise>
				<c:set var="comune" value="" />
			</c:otherwise>
		</c:choose>
		<c:set var="stringaTipiDocumento" value="${currentUser.properties['j:listaTipiDocumentoDocumentale']}"/>
				<c:choose>
						<c:when test="${not empty param['tipoDocumento']}">
								<c:set var="tipoDocumento" value=""/>
								<c:set var="query" value="SELECT * FROM [jnt:tipoDocumento] WHERE [id] = '${param['tipoDocumento']}'"/>
								<jcr:sql var="listQuery" sql="${query}" />
								<c:forEach items="${listQuery.nodes}" var="tipoDocumento1" begin="0" end="0" varStatus="counter">
										<c:set var="tipoDocumento" value="[tipoDocumento] = '${tipoDocumento1.properties['jcr:uuid'].string}' AND " />
								</c:forEach>
						</c:when>
						<c:when test="${not empty stringaTipiDocumento}">
								<c:set var="listaTipiDocumento" value="${fn:split(stringaTipiDocumento,',')}"/>
								<c:set var="tipoDocumento" value="("/>
								<c:forEach var="itemId" items="${listaTipiDocumento}">
										<c:set var="query" value="SELECT * FROM [jnt:tipoDocumento] WHERE [id] = '${itemId}'"/>
										<jcr:sql var="listQuery" sql="${query}" />
										<c:forEach items="${listQuery.nodes}" var="item" begin="0" end="0" varStatus="counter">
												<c:set var="tipoDocumento" value="${tipoDocumento}[tipoDocumento] = '${item.properties['jcr:uuid'].string}' OR "/>
												<c:set var="last1" value="${item.properties['jcr:uuid'].string}"/>
										</c:forEach>
								</c:forEach>
								<c:set var="tipoDocumento" value="${tipoDocumento}[tipoDocumento] = '${last1}') AND "/>
						</c:when>
						<c:otherwise>
								<c:set var="tipoDocumento" value="" />
						</c:otherwise>
				</c:choose>
				
		<div class="resultsList">
			<jsp:include page="visualizzazioneDocumenti.filtri.jsp" />
			<%
				VisualizzazioneDocumentoFiltri dto = (VisualizzazioneDocumentoFiltri)request.getAttribute("dto");
				System.out.println( " VisualizzazioneDocumentoFiltri -------------------------------: " + dto);
				
				String dataDocumento = "";
				String dateInizio = "cast('" + DateCommon.convertStringUserToStringDb(dto.getDataInizio(), true) + "' as date)";
				String dataFine = "cast('" + DateCommon.convertStringUserToStringDb(dto.getDataFine(), false) + "' as date)";
				
				System.out.println( " dateInizio -------------------------------: " + dateInizio);
				System.out.println( " dataFine -------------------------------: " + dataFine);
				
				if(request.getParameter("dataInizio")!=null && request.getParameter("dataFine")!=null){
					dataDocumento = " ([jcr:lastModified] >= " +  dateInizio
							+ " AND [jcr:lastModified] <= " + dataFine
							+ " ) AND";
				}
				
				request.setAttribute("dataDocumento", dataDocumento);
				
				System.out.println( " dataDoc -------------------------------: " + dataDocumento);
			%>
			<c:set var="query" value="SELECT * FROM [jnt:contenitoreDocumento] as documento WHERE ${comune} ${tipoDocumento} ${dataDocumento}  ISDESCENDANTNODE(documento,'/sites/contea/contents/documentale')	ORDER BY [jcr:lastModified] DESC" />
			<jcr:sql var="listQuery" sql="${query}" />
			<c:set var="end" value="0"/>
			<c:if test="${not empty listQuery.nodes}">
				<c:set var="end" value="${fn:length(listQuery.nodes)}" />
			</c:if>
			<c:set var="displayPager" value="${currentNode.properties['displayPager'].boolean}"/>
			<c:choose>
	 			<c:when test="${not displayPager}">
	 				<%@include file="formDocumenti.jspf" %>
	 			</c:when>
	 			<c:otherwise>
					<c:set var="maxPageItems" value="${currentNode.properties['pageSize'].long}" />
					<c:set var="nbOfPages"	value="${currentNode.properties['nbOfPages'].long}"/>
		 			<pager:pager id="p" items="${fn:length(listQuery.nodes)}" index="half-full" maxPageItems="${maxPageItems}" maxIndexPages="${nbOfPages}" isOffset="true" export="offset,currentPageNumber=pageNumber" scope="request">
						<pager:param name="style"		  />
						<pager:param name="position"	  />
						<pager:param name="index"		  />
						<pager:param name="maxPageItems"  />
						<pager:param name="maxIndexPages" />
						
						<%@include file="formDocumenti.jspf" %>
						
						<pager:index export="pageCount">
							<div class="pagination">
								<div class="paginationNavigation">
									<pager:prev export="pageUrl" ifnull="true">
										<c:if test="${not empty pageUrl}">
											<a class="pagerLink previousLink" href="${pageUrl}"><fmt:message key="pagination.previous" /></a>
										</c:if>
									</pager:prev>
									<pager:pages>
										<c:choose>
											<c:when test="${pageNumber eq currentPageNumber}">
												<span class="currentPage">${pageNumber}</span>
											</c:when>
											<c:otherwise>
												<span><a class="pagerLink paginationPageUrl" href="${pageUrl}">${pageNumber}</a></span>
											</c:otherwise>
										</c:choose>
									</pager:pages>
									<pager:next export="pageUrl" ifnull="true">
										<c:if test="${not empty pageUrl}">
											<a class="pagerLink nextLink" href="${pageUrl}"><fmt:message key="pagination.next" /></a>
										</c:if>
									</pager:next>
								</div>
							</div>
						</pager:index>
					</pager:pager>
	 			</c:otherwise>
 			</c:choose>
			<jsp:include page="visualizzazioneDocumenti.actions.jsp"/>
		</div>
	</c:otherwise>
</c:choose>