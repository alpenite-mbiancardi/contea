<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="informationContainer">
	<table>
		<tr>
			<td>
				<label for="nome"><fmt:message key="nome"/></label>
			</td>
			<td>
				<input type="text" name="nome" id="nome" value="${sessionScope.formDatas['nome'][0]}"/>
			</td>
			<td>
				<label for="cognome"><fmt:message key="cognome"/></label>
			</td>
			<td>
				<input type="text" name="cognome" id="cognome" value="${sessionScope.formDatas['cognome'][0]}"/>
			</td>
			<td>
				<label for="codice_fiscale"><fmt:message key="codfiscale"/></label>
			</td>
			<td>
				<input type="text" name="codice_fiscale" id="codice_fiscale" value="${sessionScope.formDatas['codice_fiscale'][0]}"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="email"><fmt:message key="mail"/></label>
			</td>
			<td>
				<input type="text" name="email" id="email" value="${sessionScope.formDatas['email'][0]}"/>
			</td>
			<td>
				<label for="telefono"><fmt:message key="telefono" /></label>
			</td>
			<td>
				<input type="text" name="telefono" id="telefono" value="${sessionScope.formDatas['telefono'][0]}"/>
			</td>
			<td>
				<label for="cellulare"><fmt:message key="cellulare"/></label>
			</td>
			<td>
				<input type="text" name="cellulare" id="cellulare" value="${sessionScope.formDatas['cellulare'][0]}"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="paese"><fmt:message key="paese"/></label>
			</td>
			<td>
				<input type="text" name="paese" id="paese" value="${sessionScope.formDatas['paese'][0]}"/>
			</td>
			<td>
				<label for="provincia"><fmt:message key="provincia"/></label>
			</td>
			<td>
				<select name="provincia">
					<option value=""></option>
					<option value="VE">VE</option>
					<option value="MI">MI</option>
				</select>
			</td>
			<td>
				<label for="comune"><fmt:message key="comune"/></label>
			</td>
			<td>
				<select name="comune" disabled="disabled" >
					<option value="Venezia">Venezia</option>
					<option value="Milano">Milano</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for="via"><fmt:message key="indirizzo" /></label>
			</td>
			<td>
				<input type="text" name="via" id="via" value="${sessionScope.formDatas['via'][0]}"/>
			</td>
			<td>
				<label for="ncivico"><fmt:message key="n_civico"/></label>
			</td>
			<td>
				<input type="text" name="ncivico" id="ncivico" value="${sessionScope.formDatas['ncivico'][0]}"/>
			</td>
			<td>
				<label for="extncivico"><fmt:message key="extn_civico"/></label>
			</td>
			<td>
				<input type="text" name="extncivico" id="extncivico" value="${sessionScope.formDatas['extncivico'][0]}"/>
			</td>
		</tr>
	</table>
</div>