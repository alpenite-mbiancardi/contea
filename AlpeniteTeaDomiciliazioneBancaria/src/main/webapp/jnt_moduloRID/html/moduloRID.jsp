<%-- 
    Document   : moduloRID
    Author     : Lorenzo Bortolotto <lorenzo@mxlab.net>
--%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="function" uri="http://www.jahia.org/tags/functions" %>
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
<c:url var='img_acque' value="${url.currentModule}/img/teaacque.jpg" />
<c:url var='img_ambiente' value="${url.currentModule}/img/mantovaambiente.png" />
<c:url var='img_energia' value="${url.currentModule}/img/teaenergia.png" />
<template:addResources resources="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js" type="javascript" />
<template:addResources resources="rid.css" type="css" media="screen, print"/>
<div id="moduloRid" class="container_12">
    <div class="grid_4">
        <div class="logo"><img src="${img_acque}" /></div>
        <div class="boxSuperiore">
        	<c:choose>
        		<c:when test="${param['servizio'] eq 'H1' }">
        		<div class="checkboxSelected" >&nbsp;</div>
        		</c:when>
        		<c:otherwise>
        		<div class="checkbox" >&nbsp;</div>
        		</c:otherwise>
        	</c:choose>
            <div class="scritte">
                <div class="left">
                    <span class="boldAcqua">ACQUA</span><br/>
                    C. Contr.<br/>
                    &nbsp;
                </div>
                <div class="right">
                    codice SIA AC9RC<br/>
                    <c:choose>
                    <c:when test="${param['servizio'] eq 'H1' }">
                    ${param['cc']}<br/>
                    </c:when>
                    <c:otherwise>
                    ________________<br/>
                    </c:otherwise>
                    </c:choose>
                    &nbsp;
                </div>
            </div>
        </div>
        <div class="boxSuperiore">
            <c:choose>
        		<c:when test="${param['servizio'] eq 'H1' }">
        		<div class="checkboxSelected" >&nbsp;</div>
        		</c:when>
        		<c:otherwise>
        		<div class="checkbox" >&nbsp;</div>
        		</c:otherwise>
        	</c:choose>
            <div class="scritte">
                <div class="left">
                    <span class="boldAcqua">POZZI</span><br/>
                    C. Contr.
                </div>
                <div class="right">
                    codice SIA AC9RC<br/>
                   <c:choose>
                    <c:when test="${param['servizio'] eq 'H1' }">
                    ${param['cc']}<br/>
                    </c:when>
                    <c:otherwise>
                    ________________<br/>
                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <div class="grid_4">
        <div class="logo"><img src="${img_energia}" /></div>
        <div class="boxSuperiore">
            <c:choose>
        		<c:when test="${param['servizio'] eq 'C3' }">
        		<div class="checkboxSelected" >&nbsp;</div>
        		</c:when>
        		<c:otherwise>
        		<div class="checkbox" >&nbsp;</div>
        		</c:otherwise>
        	</c:choose>
            <div class="scritte">
                <div class="left">
                    <span class="boldEnergia">TELE/ACS</span><br/>
                    C. Contr.<br/>
                    &nbsp;
                </div>
                <div class="right">
                    codice SIA A4KEH<br/>
                    <c:choose>
                    <c:when test="${param['servizio'] eq 'C3' }">
                    ${param['cc']}<br/>
                    </c:when>
                    <c:otherwise>
                    ________________<br/>
                    </c:otherwise>
                    </c:choose>
                    &nbsp;
                </div>
            </div>
        </div>
        <div class="boxSuperiore">
            <c:choose>
        		<c:when test="${param['servizio'] eq 'G1' }">
        		<div class="checkboxSelected" >&nbsp;</div>
        		</c:when>
        		<c:otherwise>
        		<div class="checkbox" >&nbsp;</div>
        		</c:otherwise>
        	</c:choose>
            <div class="scritte">
                <div class="left">
                    <span class="boldEnergia">GAS</span><br/>
                    C. Contr.<br/>
                    &nbsp;
                </div>
                <div class="right">
                    codice SIA A4KEH<br/>
                    <c:choose>
                    <c:when test="${param['servizio'] eq 'G1' }">
                    ${param['cc']}<br/>
                    </c:when>
                    <c:otherwise>
                    ________________<br/>
                    </c:otherwise>
                    </c:choose>
                    &nbsp;
                </div>
            </div>
        </div>
        <div class="boxSuperiore">
            <c:choose>
        		<c:when test="${param['servizio'] eq 'EE' }">
        		<div class="checkboxSelected" >&nbsp;</div>
        		</c:when>
        		<c:otherwise>
        		<div class="checkbox" >&nbsp;</div>
        		</c:otherwise>
        	</c:choose>
            <div class="scritte">
                <div class="left">
                    <span class="boldEnergia">EN.EL</span><br/>
                    C. Contr.<br/>
                    &nbsp;
                </div>
                <div class="right">
                    codice SIA AEAEN<br/>
                    <c:choose>
                    <c:when test="${param['servizio'] eq 'EE' }">
                    ${param['cc']}<br/>
                    </c:when>
                    <c:otherwise>
                    ________________<br/>
                    </c:otherwise>
                    </c:choose>
                    &nbsp;
                </div>
            </div>
        </div>
    </div>
    <div class="grid_4">
        <div class="logo"><img src="${img_ambiente}" /></div>
        <div class="boxSuperiore">
            <c:choose>
        		<c:when test="${param['servizio'] eq 'A1' }">
        		<div class="checkboxSelected" >&nbsp;</div>
        		</c:when>
        		<c:otherwise>
        		<div class="checkbox" >&nbsp;</div>
        		</c:otherwise>
        	</c:choose>
            <div class="scritte">
                <div class="left">
                    <span class="boldTerritorio">RIFIUTI</span><br/>
                    C. Contr.<br/>
                    &nbsp;
                </div>
                <div class="right">
                    codice SIA ALN4Y<br/>
                    <c:choose>
                    <c:when test="${param['servizio'] eq 'A1' }">
                    ${param['cc']}<br/>
                    </c:when>
                    <c:otherwise>
                    ________________<br/>
                    </c:otherwise>
                    </c:choose>
                    &nbsp;
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div class="container">    
        <div class="grid_12 header">R.I.D. - AUTORIZZAZIONE PERMANENTE DI ADDEBITO IN C/C</div>
        <div class="clear"></div>
        <div class="grid_6">
            <div class="subheader">Coordinate bancarie del conto corrente da addebitare</div>
            <div class="sezione">
                <div class="boxLabel">Banca </div><div class="box" style="width: auto;">${param['nomeBanca']}</div><br/>
                <div class="ibox" style="width: 30px;">PAESE</div><div class="ibox" style="width: 30px;">CONTR</div><div class="ibox" style="width: 13px;">CIN</div><div class="ibox" style="width: 81px;">Codice ABI</div><div class="ibox" style="width: 81px;">Codice CAB</div><div class="ibox" style="width: 200px;">Numero di conto</div><br/>
<%
for (int i=0;i<27;i++) {
    String iban = request.getParameter("iban").trim();
    try {
        out.print("<div class=\"smallbox\">"+iban.charAt(i)+"</div>");
    } catch (Exception e) {
        out.print("<div class=\"smallbox\">&nbsp;</div>");
    }
}
%>
            </div>
            <div class="subheader">Sottoscrittore del modulo</div>
                <div class="boxLabel">Cognome </div><div class="box" style="width: auto;">${param['cognome']}</div> <div class="boxLabel">Nome </div><div class="box" style="width: auto;">${param['nome']}</div><br/>
                <div class="boxLabel">Indirizzo </div><div class="box" style="width: auto;">${param['indirizzo']}</div><br/>
                <div class="boxLabel">Località </div><div class="box" style="width: auto;">${param['localita']}</div> <div class="boxLabel">CAP </div>
<%
for (int i=0;i<5;i++) {
    String cap = request.getParameter("cap");
    try {
        out.print("<div class=\"smallbox\">"+cap.charAt(i)+"</div>");
    } catch (Exception e) {
        out.print("<div class=\"smallbox\">&nbsp;</div>");
    }
}
%>
                <br/>
                <div class="boxLabel">Telefono </div><div class="box" style="width: auto;">${param['telefono']}</div><br/>
                <div class="boxLabel">Cod. Fiscale </div>
<%
for (int i=0;i<16;i++) {
    String codiceFiscale = request.getParameter("codiceFiscale");
    try {
        out.print("<div class=\"smallbox\">"+codiceFiscale.charAt(i)+"</div>");
    } catch (Exception e) {
        out.print("<div class=\"smallbox\">&nbsp;</div>");
    }
}
%>                
            <div class="subheader">Intestatario del conto (compilare solo se diverso dal sottoscrittore)</div>
                <div class="boxLabel">Cognome </div><div class="box" style="width: auto;">${param['cognomeIntestatario']}</div> <div class="boxLabel">Nome </div><div class="box" style="width: auto;">${param['nomeIntestatario']}</div><br/>
                <div class="boxLabel">Ragione sociale </div><div class="box" style="width: auto;">${param['ragioneSocialeIntestatario']}</div><br/>
                <div class="boxLabel">Indirizzo </div><div class="box" style="width: auto;">${param['indirizzoIntestatario']}</div><br/>
                <div class="boxLabel">Località </div><div class="box" style="width: auto;">${param['localitaIntestatario']}</div> <div class="boxLabel">CAP </div>
<%
for (int i=0;i<5;i++) {
    String cap = request.getParameter("capIntestatario");
    try {
        out.print("<div class=\"smallbox\">"+cap.charAt(i)+"</div>");
    } catch (Exception e) {
        out.print("<div class=\"smallbox\">&nbsp;</div>");
    }
}
%>
                <br/>
                <div class="boxLabel">Telefono </div><div class="box" style="width: auto;">${param['telefonoIntestatario']}</div><br/>
                <div class="boxLabel">Cod. Fiscale </div>
<%
for (int i=0;i<16;i++) {
    String codiceFiscale = request.getParameter("codiceFiscaleIntestatario");
    try {
        out.print("<div class=\"smallbox\">"+codiceFiscale.charAt(i)+"</div>");
    } catch (Exception e) {
        out.print("<div class=\"smallbox\">&nbsp;</div>");
    }
}
%> 
                <br/><div class="boxLabel">P. IVA </div>
<%
for (int i=0;i<16;i++) {
    String piva = request.getParameter("partitaIvaIntestatario");
    try {
        out.print("<div class=\"smallbox\">"+piva.charAt(i)+"</div>");
    } catch (Exception e) {
        out.print("<div class=\"smallbox\">&nbsp;</div>");
    }
}
%>                 

        </div>
        <div class="grid_6">
            <div class="subheader">Adesione</div>
            <div class="testo">
                Il sottoscrittore autorizza la Banca a margine ad addebitare sul c/c indicato, nella data di scadenza dell'obbligazione, tutti gli addebiti diretti RID inviati dall'Azienda e contrassegnati con le coordinate dell'Azienda creditrice riportate (o aggiornate d'iniziativa dell'Azienda) a condizione che vi siano sul c/c da addebitare disponibilità sufficienti al momento dell'esecuzione dell'operazione di addebito.<br/>
                Il debitore ha diritto di revocare il singolo addebito diretto RID entro il giorno lavorativo precedente la data di scadenza addebitata dall'Azienda creditrice e di chiedere il rimborso di un addebito diretto RID autorizzato entro 8 settimane dalla data di addebito, secondo gli accordi ed alle condizioni previsti nel contratto del conto corrente (indicato a lato) che regolano il rapporto con la Banca del debitore ed in base alle modalità previste dal D.Lgs n.11 del 27/01/2010.<br/>
                <strong>Solo per debitore "non consumatore" o "microimpresa"</strong><br/>
                Il debitore, fermo restando il diritto di revocare il singolo addebito diretto RID entro il giorno lavorativo precedente la data di scadenza, può chiedere il rimborso di un addebito diretto RID autorizzato: entro la data di scadenza ovvero entro 5 gg. lav. dopo la data di scadenza secondo le condizioni previste nel contratto di conto corrente.<br/>
                Le parti hanno facoltà di recedere in ogni momento dal presente accordo, con preavviso pari a quello previsto nel contratto di conto corrente, in precedenza sottoscritto tra le parti, o comunque secondo le condizioni rese pubbliche presso gli sportelli della banca e tempo per tempo vigenti.<br/>
                Per quanto non espressamente previsto dalle presenti disposizioni, sono applicabili le Norme che regolano i conti correnti di corrispondenza e i servizi connessi a suo tempo sottoscritte dalle parti, che formano parte integrante del presente contratto.<br/>
                <br/>
                <div style="display: inline-block; text-align: center;">
                    ________________________<br/>
                    <span style="font-size: 8px; font-style: italic;">luogo</span>
                </div>
                <div style="display: inline-block; text-align: center;">
                    ____/____/_______<br/>
                    <span style="font-size: 8px; font-style: italic;">data</span>
                </div>
                <div style="display: inline-block; text-align: center; margin-left: 70px;">
                    ________________________<br/>
                    <span style="font-size: 8px; font-style: italic;">firma</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!--        <script>
            $(function() {
               $("div.box").html("TESTO DI PROVA");
               $("div.smallbox").html("X");
            });
        </script>-->