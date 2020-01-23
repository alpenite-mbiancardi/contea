<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@page import="com.alpenite.gui.components.SelectCountries"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--
<div class="bloccoDati2">
	<div class="ragioneSociale">
		<span class="label"> <label for="ragioneSociale"><fmt:message
					key="ragioneSociale1" /></label>
		</span> <span class="value"> <input type="text" name="ragioneSociale1"
			id="ragioneSociale1"
			value="${datiAnagrafici.ragioneSociale1}" />
		</span>
	</div>
	<div class="ragioneSociale">
		<span class="label"> <label for="ragioneSociale"><fmt:message
					key="ragioneSociale2" /></label> </span> 
		<span class="value"> <input type="text" name="ragioneSociale2"
			id="ragioneSociale2"
			value="${datiAnagrafici.ragioneSociale2}" />
		</span>
	</div>
	<div class="ragioneSociale">
		<span class="label"> <label for="ragioneSociale"><fmt:message
					key="ragioneSociale3" /></label> </span> 
		<span class="value"> <input type="text" name="ragioneSociale3"
			id="ragioneSociale3"
			value="${datiAnagrafici.ragioneSociale3}" />
		</span>
	</div>
</div>
--%>
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
    <td><input type="text" name="prefixTel" class="small" id="prefixTel" value="${datiAnagrafici.prefissoTel}" /> <input type="text" name="telefono" id="telefono" value="${datiAnagrafici.telefono}" /></td>
    <th><label for="phone"><fmt:message key="cellulare" /></label></th>
    <td><input type="text" name="prefixCel" class="small" id="prefixCel" value="${datiAnagrafici.prefissoCel}" /> <input type="text" name="cellulare" id="cellulare" value="${datiAnagrafici.cellulare}" /></td>
</tr>
<%--
<div class="bloccoDati3">
	<div class="telefono">
		<span class="label"> <label for="telefono"><fmt:message
					key="telefono" /></label>
		</span> <span class="value"> 
		<input type="text" name="prefixTel" id="prefixTel" value="${datiAnagrafici.prefissoTel}" />
		<input type="text" name="telefono" id="telefono" value="${datiAnagrafici.telefono}" />
		</span>
	</div>
	<div class="cellulare">
		<span class="label"> <label for="cellulare"><fmt:message
					key="cellulare" /></label>
		</span> <span class="value"> 
		<input type="text" name="prefixCel" id="prefixCel" value="${datiAnagrafici.prefissoCel}" />
		<input type="text" name="cellulare"
			id="cellulare" value="${datiAnagrafici.cellulare}" />
		</span>
	</div>
</div>
 --%>
