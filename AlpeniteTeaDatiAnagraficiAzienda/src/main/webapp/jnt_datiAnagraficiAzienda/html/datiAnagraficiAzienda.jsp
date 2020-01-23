<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@page import="com.alpenite.gui.components.SelectCountries"%>
<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="com.alpenite.tea.datiAnagrafici.dati.DatiAnagrafici"%>
<%@page import="java.util.List"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
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
<template:addResources resources="script.js" type="javascript" />
<template:addResources resources="style.css" type="css" />

<%
    JCRNodeWrapper nodo = (JCRNodeWrapper) request.getAttribute("currentNode");
	WSClient client;

	try {
		client = WSClient.getClient(session);
	} catch (Exception e)
	{
		String defaultBP = "0018000147"; // DEBUG - decide a value to use on production
		String bpU = session.getAttribute("BPUSESSION") == null ? defaultBP : session.getAttribute("BPUSESSION").toString();
		String bpC = session.getAttribute("BPCSESSION") == null ? defaultBP : session.getAttribute("BPCSESSION").toString();
		client = WSClient.getClient(bpU, bpC);
	}
	String tipoUtente = nodo.getUser().getProperty("j:tipoBP");
	    DatiAnagrafici datiAnagrafici = null;
	    datiAnagrafici = client.getDatiAnagrafici(!(Constants.TIPO_AMM_COND.equals(tipoUtente)),Constants.getJahiaType(tipoUtente, false, true)).getRitorno();
	    request.setAttribute("datiAnagrafici", datiAnagrafici);
	    
		// get bp type from sap
	    // check if exists as user attribute
	    String bpType = nodo.getUser().getProperty("j:bpType");
	    if(bpType == null){
		    // getting from SAP
		    bpType = client.getSapType(); 
		    // set in user attribute
	    	nodo.getUser().setProperty("j:bpType", bpType);
	    	nodo.saveSession();
	    }
	    
		String privacyEmail = (datiAnagrafici.getPrivacyEmail() != null && !datiAnagrafici.getPrivacyEmail().isEmpty()) ? "checked" : "";
		String privacyMail = (datiAnagrafici.getPrivacyMail() != null && !datiAnagrafici.getPrivacyMail().isEmpty()) ? "checked" : "";
		String privacyTel = (datiAnagrafici.getPrivacyTel() != null && !datiAnagrafici.getPrivacyTel().isEmpty()) ? "checked" : "";
		
		String prifixTEL = (datiAnagrafici.getPrefissoTel() != null && !datiAnagrafici.getPrefissoTel().isEmpty()) ? datiAnagrafici.getPrefissoTel() : "+39";
		String prifixCEL = (datiAnagrafici.getPrefissoCel() != null && !datiAnagrafici.getPrefissoCel().isEmpty()) ? datiAnagrafici.getPrefissoCel() : "+39";
	    
		SelectCountries selectCountries= new SelectCountries("paese", "paeseAzienda", null);
		selectCountries.setValoreSelezionato(datiAnagrafici.getPaese());
		SelectProvince selectProvince = new SelectProvince("provincia", "provinciaAzienda", null);
		selectProvince.setValoreSelezionato(datiAnagrafici.getProvincia());
    
%>
<template:addResources type="inlinejavascript">
	<script type="text/javascript">
var urlComuniAzienda='${url.base}${currentNode.path}';
var provAzienda = '${datiAnagrafici.provincia}';
var comAzienda = "${datiAnagrafici.comune}";
var capAzienda = '${datiAnagrafici.cap}';
var errMsg = "<fmt:message key='datiAnagrafici.errorMsg'/>";
</script>
</template:addResources>
<div id="datiAnagrafici">
	<h2 class="line data"><input type="submit" id="bottoneModificadatiAnagraficiAzienda" name="bottoneModificadatiAnagraficiAzienda" class="btn medium" value="<fmt:message key='datiAnagrafici.bottoneModifica' />" /><fmt:message key="datiAnagrafici.header" /></h2>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<% if(datiAnagrafici.getRagioneSociale1() != null && !datiAnagrafici.getRagioneSociale1().isEmpty()) { %><tr><th><fmt:message key="ragioneSociale1" /></th><td><%= datiAnagrafici.getRagioneSociale1()%></td></tr><% } %>
		<% if(datiAnagrafici.getRagioneSociale2() != null && !datiAnagrafici.getRagioneSociale2().isEmpty()) { %><tr><th><td><%= datiAnagrafici.getRagioneSociale2()%></td></tr><% } %>
		<% if(bpType.equals(Constants.BP_ORG)){ %>
			<% if(datiAnagrafici.getRagioneSociale3() != null && !datiAnagrafici.getRagioneSociale3().isEmpty()) { %><tr><th></th><td><%= datiAnagrafici.getRagioneSociale3()%></td></tr><% } %>
		<%} %>

		<% if(datiAnagrafici.getCodiceFiscale() != null && !datiAnagrafici.getCodiceFiscale().isEmpty()) { %><tr><th><fmt:message key="codiceFiscale" /></th><td><%= datiAnagrafici.getCodiceFiscale()%></td></tr><% } %>
		<% if(datiAnagrafici.getPartitaIVA() != null && !datiAnagrafici.getPartitaIVA().isEmpty()) { %><tr><th><fmt:message key="partitaIVA" /></th><td><%= datiAnagrafici.getPartitaIVA()%></td></tr><% } %>
		
		<tr>
			<% if(datiAnagrafici.getTelefono() != null && !datiAnagrafici.getTelefono().isEmpty()) { %><th><fmt:message key="telefono" /></th><td><%=datiAnagrafici.getPrefissoTel() %>-<%= datiAnagrafici.getTelefono()%></td><% } %>
			<% if(datiAnagrafici.getCellulare() != null && !datiAnagrafici.getCellulare().isEmpty()) { %><th><fmt:message key="cellulare" /></th><td><%= datiAnagrafici.getPrefissoCel() %>-<%= datiAnagrafici.getCellulare()%></td><% } %>
		</tr>
	</table>
	
	<h2 class="data"><fmt:message key="datiAnagrafici.residenza" /></h2>
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
	<h2 class="data"><fmt:message key="datiAnagrafici.consensi" /></h2>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<% if(datiAnagrafici.getPrivacy() != null && !datiAnagrafici.getPrivacy().isEmpty()) { %><tr><th><fmt:message key="privacy" /></th><td><%= datiAnagrafici.getPrivacy()%></td></tr><% } %>
		<%--
		<tr><th><fmt:message key="privacyEmail" /></th><td><%= datiAnagrafici.getPrivacyEmail()%></td></tr>
		<tr><th><fmt:message key="privacyMail" /></th><td><%= datiAnagrafici.getPrivacyMail()%></td></tr>
		<tr><th><fmt:message key="privacyTel" /></th><td><%= datiAnagrafici.getPrivacyTel()%></td></tr>
		 --%>
    </table>
    <!--div id="footer"><input type="submit" id="bottoneModificadatiAnagraficiAzienda" name="bottoneModificadatiAnagraficiAzienda" value="<fmt:message key='datiAnagrafici.bottoneModifica' />" /></div-->
</div>

<div id="moduloDatiAnagraficiAzienda">

	<div id="overlay">	
		<div id="content">
			<div class="main">	
			    <form id="formModificadatiAnagraficiAzienda" method="post" action="<c:url value='${url.base}${currentNode.path}'/>.sendDatiAnagraficiAzienda.do">
			        <div class="ctn-data pag">
				        <c:if test="${not empty datiAnagrafici}">
				        <h3><fmt:message key='datiAnagraficiAzienda.label.modifica.titolo' /></h3>
						<table border="0" cellpadding="0" cellspacing="0" class="data">
				        
				    	<!--jsp:include page="dati.jsp" /-->
				    	<tr>
							<th><label for="ragioneSociale"><fmt:message key="ragioneSociale1" /></label></th>
						<td colspan="3">
							<input class="large" type="text" name="ragioneSociale1" id="ragioneSociale1" value="${datiAnagrafici.ragioneSociale1}" /><br />
							<input class="large" type="text" name="ragioneSociale2" id="ragioneSociale2" value="${datiAnagrafici.ragioneSociale2}" /><br />
							<% if(bpType.equals(Constants.BP_ORG)){ %>
							<input class="large" type="text" name="ragioneSociale3" id="ragioneSociale3" value="${datiAnagrafici.ragioneSociale3}" />
							<%} %>
						</td>
						</tr>
						
						
						<tr>
						    <th><label for="phone"><fmt:message key="telefono" /></label></th>
						    <td>
						    	<!--  MODIFICA UNISYS 16-1-14-->
						    	<input style="width: 30px!important;" type="text" name="prefixTel" class="small" id="prefixTel" value="<%= prifixTEL %>"/>
						    	
						    	<input type="text" name="telefono" id="telefono" value="${datiAnagrafici.telefono}" /></td>
						    <th><label for="phone"><fmt:message key="cellulare" /></label></th>
						    <td>
						    	<input style="width: 30px!important;" type="text" name="prefixCel" class="small" id="prefixCel" value="<%= prifixCEL %>"/>
						    	<input type="text" name="cellulare" id="cellulare" value="${datiAnagrafici.cellulare}" />
						    	
						    	<!--  MODIFICA UNISYS fine-->
						    </td>
						</tr>
				    	
				    	
				    	
				    	<%--
				    	<tr>
				    		<th colspan="2"><label for="privacyMail"><fmt:message key="privacyMail" /></label></th>
				    		<td><input class="radio" type="checkbox" name="privacyMail" id="privacyMail" value="X" <%= privacyMail %> /></td>
				    	</tr>
				    	<tr>
				    		<th colspan="2"><label for="privacyEmail"><fmt:message key="privacyEmail" /></label></th>
				    		<td><input class="radio" type="checkbox" name="privacyEmail" id="privacyEmail" value="X" <%= privacyEmail %> /></td>
				    	</tr>
				    	<tr>
				    		<th colspan="2"><label for="privacyTel"><fmt:message key="privacyTel" /></label></th>
				    		<td><input class="radio" type="checkbox" name="privacyTel" id="privacyTel" value="X" <%= privacyTel %> /></td>
				    	</tr>
				    	 --%>
				    	<%--
				   		<div class="bloccoPrivacy">
							<div class="privacyMail">
								<span class="label"> <label for="privacyMail"><fmt:message
											key="privacyMail" /></label>
								</span> <span class="value"> <input type="checkbox"
									name="privacyMail" id="privacyMail" value="X" 
									<%= privacyMail %> />
								</span>
							</div>
							<div class="privacyEmail">
								<span class="label"> <label for="privacyEmail"><fmt:message
											key="privacyEmail" /></label>
								</span> <span class="value"> <input type="checkbox"
									name="privacyEmail" id="privacyEmail" value="X" 
									<%= privacyEmail %> />
								</span>
							</div>
							<div class="privacyTel">
								<span class="label"> <label for="privacy"><fmt:message
											key="privacyTel" /></label>
								</span> <span class="value"> <input type="checkbox" name="privacyTel" 
									id="privacyTel" value="X" <%=privacyTel %> />
								</span>
							</div>
						</div>
						 --%>
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
					        	<th><label for="comuneAzienda"><fmt:message key="comune" /></label></th>
					        	<td>
					        		<select id="comuneAzienda" name="comune">
										<option value="${datiAnagrafici.comune}">${datiAnagrafici.comune}</option>
									</select>
								</td>
				        	</tr>
				        	<tr>
				        		<th><label for="cap"><fmt:message key="cap" /></label></th>
				        		<td>
				        		<select name="cap" id="capAzienda">
									<option value="${datiAnagrafici.cap}">${datiAnagrafici.cap}</option>
								</select>
				        		</td>
				        	</tr>
				        	<tr>
				        		<th><label for="via"><fmt:message key="indirizzo" /></label></th>
				        		<td><input type="text" name="via" id="via" value="${datiAnagrafici.via}" /></td>
				           	</tr>
				        	<tr>
				        		<th><p><label for="ncivico"><fmt:message key="n_civico" /></label></p></th>
				        		<td><input type="text" name="ncivico" id="ncivico" maxlength="10"  value="${datiAnagrafici.numeroCivico}" /></td>
				        		<th><p><label for="extncivico"><fmt:message key="estenzioneNumeroCivico" /></label></p></th>
				        		<td><input type="text" name="extncivico" id="extncivico" maxlength="10" value="${datiAnagrafici.estenzioneNumeroCivico}" /></td>
				        	</tr>
						<%--
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
												name="provincia_estera" value="estero" style="display: none;"/>
										</span>
										</span>
									</div>
									<div class="comune">
											<span class="label"> <label for="comune"><fmt:message
														key="comune" /></label>
											</span> <span class="value"> <span class="valueComune">
												<select id="comuneAzienda" name="comune">
													<option value="${datiAnagrafici.comune}">${datiAnagrafici.comune}</option>
												</select>
											</span>
											</span>
									</div>
									<div class="cap">
											<span class="label"> <label for="cap"><fmt:message
														key="cap" /></label>
											</span> <span class="value"> <span class="valuecap">
												<select name="cap" id="capAzienda">
												<option value="${datiAnagrafici.cap}">${datiAnagrafici.cap}</option>
											</select>
											</span>
											</span>
									</div>
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
						 --%>
						</table>	
						</c:if>
				
						<input type="hidden" id="bp" name="bp" value="<%= datiAnagrafici.getCodiceBP()%>" />
						<input type="hidden" id="tipoBp" name="tipoBp" value="<%= tipoUtente%>" />
						<input type="hidden" id="conferma1" name="conferma1" value="${currentNode.properties['redirectPage'].string}" />
						<input type="hidden" id="privacy" name="privacy" value="X" />
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
			            </div>
			            <!-- end old data -->
			            <div class="pagination">
							<input type="button" class="btn medium" id="bottoneConfermaModificadatiAnagraficiAzienda" name="bottoneConfermaModificadatiAnagraficiAzienda" value="<fmt:message key="datiAnagrafici.modulo.bottoneConferma" />" onclick="validateAnagraficaAzienda('formModificadatiAnagraficiAzienda')"/>
				    	</div>
			    </form>
			</div>
		</div>
	</div>
	
</div>