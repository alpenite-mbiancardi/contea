<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"         %>

<div class="tipoDocumento">
	<p><fmt:message key="alpeniteteadocumentale.tipoDocumento.nodeName"/>${currentNode.properties['j:nodename'].string}</p>
	<p><fmt:message key="alpeniteteadocumentale.tipoDocumento.id"      />${currentNode.properties['id'].string        }</p>
	<p><fmt:message key="alpeniteteadocumentale.tipoDocumento.name"    />${currentNode.properties['name'].string      }</p>
</div>