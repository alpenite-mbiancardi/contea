<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tr>
    <th><label for="name"><fmt:message key="nome" /></label></th>
    <td><input type="text" name="nome" id="nome" value="${datiAnagrafici.nome}" /></td>
    <th><label for="lastname"><fmt:message key="cognome" /></label></th>
    <td><input type="text" name="cognome" id="cognome" value="${datiAnagrafici.cognome}" /></td>
</tr>


<%--
<div class="bloccoDati1">
	<div class="nomeEcognome">
		<div class="nome">
			<span class="label"> <label for="nome"><fmt:message
						key="nome" /></label>
			</span> <span class="value"> <input type="text" name="nome" id="nome"
				value="${datiAnagrafici.nome}" />
			</span>
		</div>
		<div class="cognome">
			<span class="label"> <label for="cognome"><fmt:message
						key="cognome" /></label>
			</span> <span class="value"> <input type="text" name="cognome"
				id="cognome" value="${datiAnagrafici.cognome}" />
			</span>
		</div>
	</div>
</div>
 --%>