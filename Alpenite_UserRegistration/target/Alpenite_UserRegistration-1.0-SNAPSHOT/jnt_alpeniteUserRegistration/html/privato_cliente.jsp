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
		<label for="codice_fiscale"><fmt:message key="codfiscale" /></label>
		</td>
		<td>
		<input type="text" name="codice_fiscale" id="codice_fiscale" value="${sessionScope.formDatas['codice_fiscale'][0]}"/>
		</td>
		<td>
		<label for="email"><fmt:message key="mail"/></label>
		</td>
		<td>
		<input type="text" name="email" id="email" value="${sessionScope.formDatas['email'][0]}"/>
		</td>
		</table>		
</div>  