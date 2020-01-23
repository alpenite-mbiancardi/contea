<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		  		 
<div class="informationContainer">
		<table>
		<tr>
		<td>
		<label for="codiceCliente"><fmt:message key="codiceCliente"/></label>
		</td>
		<td>
		<input type="text" name="codiceCliente" id="codiceCliente" value="${sessionScope.formDatas['codiceCliente'][0]}"/>
		</td>
		<td>
		<label for="codice_fiscale"><fmt:message key="codfiscale"/></label>
		</td>
		<td>
		<input type="text" name="codice_fiscale" id="codice_fiscale" value="${sessionScope.formDatas['codice_fiscale'][0]}"/>
		</td>
		<td>
		<label for="partita_iva"><fmt:message key="partita_iva"/></label>
		</td>
		<td>
		<input type="text" name="partita_iva" id="partita_iva" value="${sessionScope.formDatas['partita_iva'][0]}"/>
		</td>
		</table>
		<hr>
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
		<label for="email"><fmt:message key="mail"/></label>
		</td>
		<td>
		<input type="text" name="email" id="email" value="${sessionScope.formDatas['email'][0]}"/>
		</td>
		</tr>
		<tr>
		<td>
		<label for="telefono"><fmt:message key="telefono"/></label>
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
		<td>
		<label for="ruolo"><fmt:message key="ruolo"/></label>
		</td>
		<td>
		<input type="text" name="ruolo" id="ruolo" value="${sessionScope.formDatas['ruolo'][0]}" />
		</td>
		</tr>
		</table>		
</div>  