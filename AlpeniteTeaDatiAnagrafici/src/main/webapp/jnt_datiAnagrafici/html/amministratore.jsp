<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tr>
    <th><label for="name"><fmt:message key="ragioneSociale1Amm" /></label></th>
    <td><input type="text" name="ragioneSociale1" id="ragioneSociale1" value="${datiAnagrafici.ragioneSociale1}" /></td>
</tr>
<tr>
    <th></th>
    <td><input type="text" name="ragioneSociale2" id="ragioneSociale2" value="${datiAnagrafici.ragioneSociale2}" /></td>
</tr>
<tr>
    <th></th>
    <td><input type="text" name="ragioneSociale3" id="ragioneSociale3" value="${datiAnagrafici.ragioneSociale3}" /></td>
</tr>

<%--
<div class="bloccoDati2">
	<div class="ragioneSociale">
		<span class="label"> <label for="ragioneSociale"><fmt:message
					key="ragioneSociale1Amm" /></label>
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
<%--
<div class="bloccoDati1">
	<div class="codFiscale">
		<span class="label"> <label for="codice_fiscale"><fmt:message
					key="codfiscale" /></label>
		</span> <span class="value"> <input type="text" name="codiceFiscale"
			id="codiceFiscale"
			value="${datiAnagrafici.codiceFiscale}" />
		</span>
	</div>
	<div class="piva">
		<span class="label">
			<label for="partita_iva"><fmt:message key="partita_iva"/></label>
		</span>
		<span class="value">
			<input type="text" name="partita_iva" id="partita_iva" value="${datiAnagrafici.partitaIVA}"/>
		</span>
	</div>
</div>
 --%>