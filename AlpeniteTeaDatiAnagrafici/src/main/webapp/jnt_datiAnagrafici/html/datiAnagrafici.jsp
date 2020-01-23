<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@page import="com.alpenite.gui.components.SelectCountries"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="java.util.List"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.userregistration.utils.UserJahiaDB"%>
<%@page import="com.alpenite.tea.datiAnagrafici.dati.*"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:addResources resources="script.js" type="javascript" />
<template:addResources resources="style.css" type="css" />

<%
    JCRNodeWrapper currentNode = (JCRNodeWrapper) request.getAttribute("currentNode");

	WSClient client;

	try {
		client = WSClient.getClient(session);
	} catch (Exception e)
	{
		String defaultBP = "0040000042"; // DEBUG - decide a value to use on production
		String bpU = session.getAttribute("BPUSESSION") == null ? defaultBP : session.getAttribute("BPUSESSION").toString();
		String bpC = session.getAttribute("BPCSESSION") == null ? defaultBP : session.getAttribute("BPCSESSION").toString();
		client = WSClient.getClient(bpU, bpC);
	}
	String tipoUtente = currentNode.getUser().getProperty("j:tipoBP");
	System.out.println("ALPENITE datiAnagrafici.jsp - tipoUtente: " + tipoUtente);
	
	DatiAnagrafici datiAnagrafici = client.getDatiAnagrafici(false, Constants.TIPO_AMM_COND.equals(tipoUtente), Constants.getJahiaType(tipoUtente, true, false)).getRitorno();
    request.setAttribute("datiAnagrafici", datiAnagrafici);
    
    // get bp type from sap
    // check if exists as user attribute
    String bpType = currentNode.getUser().getProperty("j:bpType");
    if(bpType == null){
	    // getting from SAP
	    bpType = client.getSapType(); 
	    // set in user attribute
    	currentNode.getUser().setProperty("j:bpType", bpType);
	    currentNode.saveSession();
    }
    /*
	String privacyEmailCheck = (datiAnagrafici.getPrivacyEmail() != null && !datiAnagrafici.getPrivacyEmail().isEmpty()) ? "checked" : "1";
	String privacyMailCheck = (datiAnagrafici.getPrivacyMail() != null && !datiAnagrafici.getPrivacyMail().isEmpty()) ? "checked" : "2";
	String privacyTelCheck = (datiAnagrafici.getPrivacyTel() != null && !datiAnagrafici.getPrivacyTel().isEmpty()) ? "checked" : "3";
    */	
	String privacyTel = (datiAnagrafici.getPrivacyTel() != null && !datiAnagrafici.getPrivacyTel().isEmpty()) ? "checked" : "";
	String privacyEmail = (datiAnagrafici.getPrivacyEmail() != null && !datiAnagrafici.getPrivacyEmail().isEmpty()) ? "checked" : "";
	String privacyMail = (datiAnagrafici.getPrivacyMail() != null && !datiAnagrafici.getPrivacyMail().isEmpty()) ? "checked" : "";
	
	
	
	SelectCountries selectCountries= new SelectCountries();
	selectCountries.setValoreSelezionato(datiAnagrafici.getPaese());
	SelectProvince selectProvince = new SelectProvince();
	selectProvince.setValoreSelezionato(datiAnagrafici.getProvincia());
	
%>
<template:addResources type="inlinejavascript">
	<script type="text/javascript">
var urlComuni='${url.base}${currentNode.path}';
var prov = '${datiAnagrafici.provincia}';
var com = "${datiAnagrafici.comune}";
var cap = '${datiAnagrafici.cap}';
var errMsg = "<fmt:message key='datiAnagrafici.errorMsg'/>";
</script>
</template:addResources>
<div id="datiAnagrafici">
	<h2 class="line data"><input type="submit" id="bottoneModificadatiAnagrafici" name="bottoneModificadatiAnagrafici" class="btn medium" value="<fmt:message key='datiAnagrafici.bottoneModifica' />" />
	<fmt:message key="datiAnagrafici.header" />
	<% if(tipoUtente.equals(Constants.TIPO_AZIENDA) || tipoUtente.equals(Constants.TIPO_AZ_CLI)) { %>
		&nbsp;<fmt:message key="datiAnagrafici.header2" />
	<%} %>
	</h2>
	<!--div id="header"><!--fmt:message key="datiAnagrafici.header" /></div-->
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<% if(datiAnagrafici.getNome() != null && !datiAnagrafici.getNome().isEmpty()) { %><th><fmt:message key="nome" /></th><td><%= datiAnagrafici.getNome()%></td><% } %>
		<% if(datiAnagrafici.getCognome() != null && !datiAnagrafici.getCognome().isEmpty()) { %><th><fmt:message key="cognome" /></th><td><%= datiAnagrafici.getCognome()%></td><% } %>
		<% if(datiAnagrafici.getRagioneSociale1() != null && !datiAnagrafici.getRagioneSociale1().isEmpty()) { %><tr><th><fmt:message key="ragioneSociale1" /></th><td><%= datiAnagrafici.getRagioneSociale1()%></td></tr><% } %>
		<% if(datiAnagrafici.getRagioneSociale2() != null && !datiAnagrafici.getRagioneSociale2().isEmpty()) { %><tr><th></th><td><%= datiAnagrafici.getRagioneSociale2()%></td></tr><% } %>
		<% if(bpType.equals(Constants.BP_ORG)){ %>
			<% if(datiAnagrafici.getRagioneSociale3() != null && !datiAnagrafici.getRagioneSociale3().isEmpty()) { %><tr><th></th><td><%= datiAnagrafici.getRagioneSociale3()%></td></tr><% } %>
		<%} %>
		<% if((datiAnagrafici.getCodiceFiscale() != null && !datiAnagrafici.getCodiceFiscale().isEmpty()) || (datiAnagrafici.getPartitaIVA() != null && !datiAnagrafici.getPartitaIVA().isEmpty())) { %>
			<tr>
				<% if(datiAnagrafici.getCodiceFiscale() != null && !datiAnagrafici.getCodiceFiscale().isEmpty() ) { %><th><fmt:message key="codiceFiscale" /></th><td colspan="3"><%= datiAnagrafici.getCodiceFiscale()%></td><% } %>
				<% if(datiAnagrafici.getPartitaIVA() != null && !datiAnagrafici.getPartitaIVA().isEmpty()) { %><th><fmt:message key="partitaIVA" /></th><td colspan="3"><%= datiAnagrafici.getPartitaIVA()%></td><% } %>
			</tr>
		<% } %>
		<% if((datiAnagrafici.getCellulare() != null && !datiAnagrafici.getCellulare().isEmpty()) || (datiAnagrafici.getTelefono() != null && !datiAnagrafici.getTelefono().isEmpty())) { %>
			<tr>
				<% if(datiAnagrafici.getTelefono() != null && !datiAnagrafici.getTelefono().isEmpty()) { %><th><fmt:message key="telefono" /></th><td><%=datiAnagrafici.getPrefissoTel() %>-<%= datiAnagrafici.getTelefono()%></td><% } %>
				<% if(datiAnagrafici.getCellulare() != null && !datiAnagrafici.getCellulare().isEmpty()) { %><th><fmt:message key="cellulare" /></th><td><%= datiAnagrafici.getPrefissoCel() %>-<%= datiAnagrafici.getCellulare()%></td><% } %>
			</tr>
		<% } %>
		<%--
		<!--  ruolo -->
		<tr>
			<th><fmt:message key="ruolo" /></th>
			<td><% if((datiAnagrafici.getRuolo() != null && !datiAnagrafici.getRuolo().isEmpty())){%><%=datiAnagrafici.getRuolo()%> <%}%></td>
			<th>&nbsp;</th>
			<td>&nbsp;</td>
		</tr>
		 --%>
	</table>
		<h2 class="data"><fmt:message key="datiAnagrafici.residenza" /></h2>
		<%-- cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --% >
	<% if(!tipoUtente.equals(Constants.TIPO_AZIENDA) && !tipoUtente.equals(Constants.TIPO_AZ_CLI)) { %>
	< %-- end cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --%>
			<!-- MarVal 23022015 dati referente aziendale in visualizzazione-->
			<table border="0" cellpadding="0" cellspacing="0" class="data">
				<tr>
					<% if(datiAnagrafici.getPaese() != null && !datiAnagrafici.getPaese().isEmpty()) { %><th><fmt:message key="paese" /></th><td><%= datiAnagrafici.getPaese()%></td><% } %>
					<% if(datiAnagrafici.getComune() != null && !datiAnagrafici.getComune().isEmpty()) { %><th><fmt:message key="comune" /></th><td><%= datiAnagrafici.getComune()%></td><% } %>
				</tr>
				<tr>
					<% if(datiAnagrafici.getProvincia() != null && !datiAnagrafici.getProvincia().isEmpty()) { %><th><fmt:message key="provincia" /></th><td><%= datiAnagrafici.getProvincia()%></td><% } %>
					<% if(datiAnagrafici.getCap() != null && !datiAnagrafici.getCap().isEmpty()) { %><th><fmt:message key="cap" /></th><td><%= datiAnagrafici.getCap()%></td><% } %>
				</tr>
				<tr>		
					<% if(datiAnagrafici.getVia() != null && !datiAnagrafici.getVia().isEmpty()) { %><th><fmt:message key="via" /></th><td><%= datiAnagrafici.getVia()%></td><% } %>
					<% if(datiAnagrafici.getNumeroCivico() != null && !datiAnagrafici.getNumeroCivico().isEmpty()) { %><th><fmt:message key="numeroCivico" /></th><td><%= datiAnagrafici.getNumeroCivico()%></td><% } %>
					<% if(datiAnagrafici.getEstenzioneNumeroCivico() != null && !datiAnagrafici.getEstenzioneNumeroCivico().isEmpty()) { %><th><fmt:message key="estenzioneNumeroCivico" /></th><td><%= datiAnagrafici.getEstenzioneNumeroCivico()%></td><% } %>
				</tr>
			</table>
			<!--END MarVal 23022015 dati referente aziendale modifica in visualizzazione-->
	<%-- cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --% >
	<%} %>
	< %-- end cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --%>
	<h2 class="data"><fmt:message key="datiAnagrafici.consensi" /></h2>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<% if(datiAnagrafici.getPrivacy() != null && !datiAnagrafici.getPrivacy().isEmpty()) { %>
			<tr>
				<th class="large"><fmt:message key="privacy" /></th>
				<td><%= datiAnagrafici.getPrivacy()%></td>
			</tr>
		<% } %>
		<tr>
			<th class="large"><fmt:message key="privacyEmail" /></th>
			<td><%= datiAnagrafici.getPrivacyEmail()%></td>
		</tr>
		
	<%--	<% if(!tipoUtente.equals(Constants.TIPO_AZIENDA) && !tipoUtente.equals(Constants.TIPO_AZ_CLI)) { %> --%>
			<tr>
				<th class="large"><fmt:message key="privacyMail" /></th>
				<td><%= datiAnagrafici.getPrivacyMail()%></td>
			</tr>
<%--		<%} %> --%>
		<tr>
			<th class="large"><fmt:message key="privacyTel" /></th>
			<td><%= datiAnagrafici.getPrivacyTel()%></td>
		</tr>
    </table>
    <!--div id="footer"><input type="submit" id="bottoneModificadatiAnagrafici" name="bottoneModificadatiAnagrafici" value="<fmt:message key='datiAnagrafici.bottoneModifica' />" /></div-->
</div>

<div id="moduloDatiAnagrafici">
	<div id="overlay">	
		<div id="content">
			<div class="main">
			    <form id="formModificadatiAnagrafici" method="post" action="<c:url value='${url.base}${currentNode.path}'/>.sendDatiAnagrafici.do">
			    	<div class="ctn-data pag">
				    	<c:if test="${not empty datiAnagrafici}">
				    	<h3><fmt:message key='datiAnagrafici.label.modifica.titolo' /></h3>
						<table border="0" cellpadding="0" cellspacing="0" class="data">
					    	 <% if(tipoUtente.equals(Constants.TIPO_PRIVATO) || tipoUtente.equals(Constants.TIPO_PRIV_CLI)) {%>
					             <!-- privato -->
					             <jsp:include page="privato.jsp" />
				             <% } %>
				
				             <% if(tipoUtente.equals(Constants.TIPO_AZIENDA) || tipoUtente.equals(Constants.TIPO_AZ_CLI)) { %>
				                     <!-- azienda -->
				                     <jsp:include page="interlocutore.jsp" />
				             <% } %>
				
				             <% if(tipoUtente.equals(Constants.TIPO_AMM_COND)) { %>
				                     <!-- amm_cond -->
				                     <jsp:include page="amministratore.jsp" />
				             <% } %>
				
					        <jsp:include page="dati.jsp" />
					        <%-- cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --% >
					        <!-- MarVal 23022015 dati referente aziendale modifica dati sap-->
					        <%if(tipoUtente.equals(Constants.TIPO_PRIVATO) || tipoUtente.equals(Constants.TIPO_PRIV_CLI) || tipoUtente.equals(Constants.TIPO_AMM_COND)|| tipoUtente.equals(Constants.TIPO_AZ_CLI)) {%>
					         <!--END MarVal 23022015 dati referente aziendale modifica dati sap-->
					         <% -- end cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --%>
	
							<tr>
					        	<th><label for="paese"><fmt:message key="paese" /></label></th>
					        	<td colspan="3"><%=selectCountries.toString()%></td>
					        </tr>
					        <tr>
					        	<th><label for="provincia"><fmt:message key="provincia" /></label></th>
					        	<td>
					        	<span class="valueProvincia"> <%=selectProvince.toString()%>
											</span> <span class="valueProvinciaEstera"> <input type="text"
													name="provincia_estera" value="estero" style="display: none;" />
											</span>
					        	</td>
					        	<th><label for="comune"><fmt:message key="comune" /></label></th>
					        	<td>
					        		<select id="comune" name="comune">
										<option value="${datiAnagrafici.comune}">${datiAnagrafici.comune}</option>
									</select>
								</td>
				        	</tr>
				        	<tr>
				        		<th><label for="cap"><fmt:message key="cap" /></label></th>
				        		<td>
				        		<select name="cap" id="cap">
									<option value="${datiAnagrafici.cap}">${datiAnagrafici.cap}</option>
								</select>
				        		</td>
				        	</tr>
				        	<tr>
				        		<th>
				        			<label for="via"><fmt:message key="indirizzo" /></label>
				        		</th>
			        			<td>
			        			 	<input type="text" name="via" id="via" value="${datiAnagrafici.via}" maxLeng/>
			        			</td>
				        	</tr>
				        	<tr>			        			
				        		<th>
					        		<p>
					        			<label for="ncivico"><fmt:message key="n_civico" /></label>
					        		</p>
				        		</th>
				        		<td> 
						        	<input type="text" name="ncivico" id="ncivico" value="${datiAnagrafici.numeroCivico}" maxlength="10" />
						        </td>		
						        <th>		
						        	 <fmt:message key="estenzioneNumeroCivico" />
						        </th>
						        <td>		
						        	<input type="text" name="extncivico" id="extncivicoDatiAnagrafici" value="${datiAnagrafici.estenzioneNumeroCivico}" maxlength="10" />
				           		</td>
				        	</tr>
							<!-- 
							<div class="bloccoDati4">
								<div class="indirizzo">
									<div class="blocco1">
										<div class="paese">
											<span class="label"> <label for="paese"><fmt:message
														key="paese" /></label>
											</span> <span class="value"> <%=selectCountries.toString()%>
											</span>
										</div>
										<div class="provincia">
											<span class="label"> <label for="provincia"><fmt:message
														key="provincia" /></label>
											</span> <span class="value"> <span class="valueProvincia"> <%=selectProvince.toString()%>
											</span> <span class="valueProvinciaEstera"> <input type="text"
													name="provincia_estera" value="estero" style="display: none;" />
											</span>
											</span>
										</div>
										<div class="comune">
											<span class="label"> <label for="comune"><fmt:message
														key="comune" /></label>
											</span> <span class="value"> <span class="valueComune">
												<select id="comune" name="comune">
													<option value="${datiAnagrafici.comune}">${datiAnagrafici.comune}</option>
												</select>
											</span>
											</span>
										</div>
									</div>
									<div class="cap">
											<span class="label"> <label for="cap"><fmt:message
														key="cap" /></label>
											</span> <span class="value"> <span class="valuecap">
											<select name="cap" id="cap">
												<option value="${datiAnagrafici.cap}">${datiAnagrafici.cap}</option>
											</select>
											</span>
											</span>
									</div>
									<div class="blocco2">
										<div class="via">
											<span class="label"> <label for="via"><fmt:message
														key="indirizzo" /></label>
											</span> <span class="value"> <input type="text" name="via" id="via"
												value="${datiAnagrafici.via}" />
											</span>
										</div>
										<div class="ncvico">
											<span class="label"> <label for="ncivico"><fmt:message
														key="n_civico" /></label>
											</span> <span class="value"> <input type="text" name="ncivico"
												id="ncivico" value="${datiAnagrafici.numeroCivico}" />
											</span>
										</div>
									</div>
								</div>
							</div>
							 -->
						<%-- cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --% >
							<%} %>
					    <%- - cbattagliese 20.03.2015 tutti gli utenti devono vedere il proprio indirizzo --%>
	    
					  <%--      <% if(!tipoUtente.equals(Constants.TIPO_AZIENDA) && !tipoUtente.equals(Constants.TIPO_AZ_CLI)) { %>  --%>
					        <tr>
					    		<th colspan="2"><label for="privacyMail"><fmt:message key="privacyMail" /></label></th>
					    		<td><input class="radio" type="checkbox" name="privacyMail" id="privacyMail" value="X" <%= privacyMail %> /></td>
					    	</tr>
					   <%-- 	<%} %>  --%>
					    	<tr>
					    		<th colspan="2"><label for="privacyEmail"><fmt:message key="privacyEmail" /></label></th>
					    		<td><input class="radio" type="checkbox" name="privacyEmail" id="privacyEmail" value="X" <%= privacyEmail %> /></td>
					    	</tr>
					    	<tr>
					    		<th colspan="2"><label for="privacyTel"><fmt:message key="privacyTel" /></label></th>
					    		<td><input class="radio" type="checkbox" name="privacyTel" id="privacyTel" value="X" <%= privacyTel %> /></td>
					    	</tr>
					    	
					    	<%--
							<div class="bloccoPrivacy">
								<% if(!tipoUtente.equals(Constants.TIPO_AZIENDA) && !tipoUtente.equals(Constants.TIPO_AZ_CLI)) { %>
								<!-- consensi -->
								<div class="privacyMail">
									<span class="label"> <label for="privacyMail"><fmt:message
												key="privacyMail" /></label>
									</span> <span class="value"> <input type="checkbox"
										name="privacyMail" id="privacyMail" value="X"
										<%= privacyMail%> />
									</span>
								</div>
								<%} %>
								<div class="privacyEmail">
									<span class="label"> <label for="privacyEmail"><fmt:message
												key="privacyEmail" /></label>
									</span> <span class="value"> <input type="checkbox"
										name="privacyEmail" id="privacyEmail" value="X"
										<%= privacyEmail%> />
									</span>
								</div>
								<div class="privacyTel">
									<span class="label"> <label for="privacy"><fmt:message
												key="privacyTel" /></label>
									</span> <span class="value"> <input type="checkbox" name="privacyTel" value="X"
										id="privacyTel" <%=privacyTel%> />
									</span>
								</div>
							</div>
							 --%>
					    </table>	
						</c:if>			
			        	<input type="hidden" id="bp" name="bp" value="<%= datiAnagrafici.getCodiceBP()%>" />
			        	<input type="hidden" id="tipoBp" name="tipoBp" value="<%= tipoUtente%>" />
			            <input type="hidden" id="conferma" name="conferma" value="${currentNode.properties['redirectPage'].string}" />
			            <input type="hidden" id="privacy" name="privacy" value="X" />
			            <input type="hidden" id="username" name="username" value="${renderContext.user.username}" />
			            <!-- old data -->
			           	<input type="hidden" name="codiceFiscaleOld" value="<%= datiAnagrafici.getCodiceFiscale() %>"/>
						<input type="hidden" name="nomeOld" value="<%= datiAnagrafici.getNome() %>"/>
						<input type="hidden" name="cognomeOld" value="<%= datiAnagrafici.getCognome()%>"/>
						<input type="hidden" name="paeseOld" value="<%= datiAnagrafici.getPaese()%>"/>
						<input type="hidden" name="provinciaOld" value="<%= datiAnagrafici.getProvincia()%>"/>
						<input type="hidden" name="comuneOld" value="<%= datiAnagrafici.getComune()%>"/>
						<input type="hidden" name="capOld" value="<%= datiAnagrafici.getCap()%>"/>
						<input type="hidden" name="viaOld" value="<%= datiAnagrafici.getVia()%>"/>
						<input type="hidden" name="ncivicoOld" value="<%= datiAnagrafici.getNumeroCivico()%>"/>
						<input type="hidden" name="ncivicoExtOld" value="<%= datiAnagrafici.getEstenzioneNumeroCivico()%>"/>
						<input type="hidden" name="ragioneSociale1Old" value="<%= datiAnagrafici.getRagioneSociale1()%>"/>
						<input type="hidden" name="ragioneSociale2Old" value="<%= datiAnagrafici.getRagioneSociale2()%>"/>
						<input type="hidden" name="ragioneSociale3Old" value="<%= datiAnagrafici.getRagioneSociale3()%>"/>
						<input type="hidden" name="ragioneSociale4Old" value="<%= datiAnagrafici.getRagioneSociale4()%>"/>
						<input type="hidden" name="cellulareOld" value="<%= datiAnagrafici.getCellulare()%>"/>
						<input type="hidden" name="telefonoOld" value="<%= datiAnagrafici.getTelefono()%>"/>
						<input type="hidden" name="prefissoTelOld" value="<%= datiAnagrafici.getPrefissoTel() %>" />
						<input type="hidden" name="prefissoCelOld" value="<%= datiAnagrafici.getPrefissoCel() %>" />
						<input type="hidden" name="partitaIVAOld" value="<%= datiAnagrafici.getPartitaIVA()%>"/>
						<input type="hidden" name="privacyOld" value="<%= datiAnagrafici.getPrivacy()%>"/>
						<input type="hidden" name="privacyEmailOld" value="<%= datiAnagrafici.getPrivacyEmail()%>"/>
						<input type="hidden" name="privacyMailOld" value="<%= datiAnagrafici.getPrivacyMail()%>"/>
						<input type="hidden" name="privacyTelOld" value="<%= datiAnagrafici.getPrivacyTel()%>"/>
						<input type="hidden" name="ruolo" value="<%= datiAnagrafici.getRuolo()%>" />
			            <!-- end old data -->
			            </div>
			            <div class="pagination">	
			            	<input type="button" class="btn medium" id="bottoneConfermaModificadatiAnagrafici" name="bottoneConfermaModificadatiAnagrafici" value="<fmt:message key="datiAnagrafici.modulo.bottoneConferma" />"  onclick="validateAnagrafica('formModificadatiAnagrafici')"/>
						</div>
			    </form>
			</div>
		</div>
	</div>
</div>
