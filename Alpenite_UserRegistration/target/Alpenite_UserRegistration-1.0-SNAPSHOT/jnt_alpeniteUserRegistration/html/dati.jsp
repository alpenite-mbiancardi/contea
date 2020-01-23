<%@page import="java.util.List"%>
<%@page import="com.alpenite.gui.components.SelectRoles"%>
<%@page import="com.alpenite.gui.components.SelectCountries"%>
<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="aziendaEnte">
<h3><fmt:message key="aziendaEnte"/></h3>
	<table border="0" cellpadding="0" cellspacing="0" class="data">
		<tr>
			<th><fmt:message key="alpeniteuserregistration.azienda" /></th>
			<td><input type='radio' name='aziendaEnte' value='azienda' id="aziendaEnte" checked="checked" /></td>
		</tr>
		<tr>
			<th><fmt:message key="alpeniteuserregistration.ente" /></th>
			<td><input type='radio' name='aziendaEnte' value='ente' id="aziendaEnte"/></td>
		</tr>
	</table>
</div>

<h3><fmt:message key="alpeniteuserregistration.datiAnagraficiTitle"/></h3>
<div class="nomeEcognome">
<table border="0" cellpadding="0" cellspacing="0" class="data">
       <tr>
           <th><label for="name"><fmt:message key="nome"/></label></th>
           <td><input type="text" name="nome" id="nome" value="${sessionScope.formDatas['nome'][0]}"/></td>
           <th><label for="lastname"><fmt:message key="cognome"/></label></th>
           <td><input type="text" name="cognome" id="cognome" value="${sessionScope.formDatas['cognome'][0]}"/></td>
       </tr>
       <tr>
           <th><label for="phone"><fmt:message key="telefono" /></label></th>
           <td>
           <!--  MODIFICA UNISYS 27-1-14 -->
				<input style="width: 30px!important;" type="text" class="small" name="prefissoTel" id="prefissoTel" value="+39"/>
				<input type="text" name="telefono" id="telefono" value="${sessionScope.formDatas['telefono'][0]}"/>
			</td>
           <th><label for="cell"><fmt:message key="cellulare"/></label></th>
           <td>
				<input style="width: 30px!important;" type="text" class="small" name="prefissoCel" id="prefissoCel" value="+39"/>
				<input type="text" name="cellulare" id="cellulare" value="${sessionScope.formDatas['cellulare'][0]}"/>
		   </td>
		   <!--  MODIFICA UNISYS 27-1-14 FINE -->
	   </tr>
	   <tr class="ruolo">
           <th><label for="ruolo"><fmt:message key="ruolo"/></label></th>
           <td>
           <select name="ruolo">
           <%
				List<String> roles = (new SelectRoles()).getRoles();
           		for(String roleCode : roles){
           			request.setAttribute("role", roleCode);
           %>
           			<option value="<%=roleCode%>"><fmt:message key="role.${role}" /></option>
           <%} %>
           </select>
           </td>
       </tr>
</table>       
</div>

<div class="codiceCliente">
<table border="0" cellpadding="0" cellspacing="0" class="data">
       <tr class="codiceClienteField">
           <th><label for="codice_cliente"><fmt:message key="codiceCliente"/></label></th>
           <td colspan="3"><div class="ctn-info"><a href="#1" class="info"><fmt:message key="altBaloon"/></a><div class="balloon"><br /><fmt:message key="baloonText" /><a href="#1" class="close"><fmt:message key="ballonClose"/></a></div></div><input type="text" name="codice_cliente" id="codice_cliente" value="${sessionScope.formDatas['codiceCliente'][0]}"/></td>
       </tr>
       <tr class="ragioneSociale">
           <th><label for="ragione_sociale"><fmt:message key="ragione_sociale"/></label></th>
           <td><input type="text" name="ragione_sociale" id="ragione_sociale" value="${sessionScope.formDatas['ragione_sociale'][0]}"/></td>
       </tr>
       <tr class="codFiscale">
       		<th><label for="codice_fiscale"><fmt:message key="codfiscale"/></label></th>
       		<td><input type="text" name="codice_fiscale" id="codice_fiscale" value="${sessionScope.formDatas['codice_fiscale'][0]}"/></td>
       </tr>
       <tr class="piva">
       		<th><label for="partita_iva"><fmt:message key="partita_iva"/></label></th>
       		<td><input type="text" name="partita_iva" id="partita_iva" value="${sessionScope.formDatas['partita_iva'][0]}"/></td>
       		<th><label for="pviaIT"><fmt:message key="pivaIT"/></label></th>
       		<td><input type="checkbox" name="pivaIT" id="pivaIT" value="true" /></td>
       </tr>
</table>

</div>

<div class="bloccoDati4">
<table border="0" cellpadding="0" cellspacing="0" class="data">
	<tr>
		<th><label for="paese"><fmt:message key="paese"/></label></th>
		<td colspan="5"><% SelectCountries countries = new SelectCountries(); countries.setValoreSelezionato("IT");%><%= countries.toString() %></td>
	</tr>
	<tr>
		<th><label for="provincia"><fmt:message key="provincia"/></label></th>
		<td class="valueProvincia"><%= (new SelectProvince()).toString() %></td>
		<td class="valueProvinciaEstera"><input type="text" name="provincia_estera" value="estero" /></td>
		<th><label for="comune"><fmt:message key="comune"/></label></th>
		<td class="valueComune">
			<select name="comune" disabled="disabled">
				<option value=""></option>
			</select>
		</td>
		<td class="valueComuneEstero"><input type="text" name="comune_estero" value="estero" /></td>
	</tr>
	<tr>
		<th><label for="cap"><fmt:message key="cap"/></label></th>
		<%--<td><input type="text" name="cap" class="small" value="${sessionScope.formDatas['cap'][0]}" /></td> --%>
		<td class="valueCap">
			<select name="cap" disabled="disabled">
				<option value=""></option>
			</select>
		</td>
		<th><label for="via"><fmt:message key="indirizzo" /></label></th>
		<td><input type="text" name="via" id="via" value="${sessionScope.formDatas['via'][0]}"/></td>
		<th><label for="ncivico"><fmt:message key="n_civico"/></label></th>
		<td><input type="text" name="ncivico" maxlength="10"  class="small" style="width: 30px" id="ncivico" value="${sessionScope.formDatas['ncivico'][0]}"/></td>
		<th style="margin-left: -236px;"><label for="extncivico" style="width: 30px" ><fmt:message key="extn_civico"/></label></th>
		<td style="margin-left: -230px;"><input type="text" style="margin-left: 0px;" name="extncivico" maxlength="10" class="small" id="extncivico" value="${sessionScope.formDatas['extncivico'][0]}"/></td>
	</tr>
</table>
</div>
