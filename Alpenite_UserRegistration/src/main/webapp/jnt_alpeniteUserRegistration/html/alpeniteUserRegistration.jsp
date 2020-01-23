<%@page import="com.alpenite.tea.communicationLayer.data.WSReturn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.alpenite.tea.communicationLayer.WSReturnManager"%>
<%@page import="org.jahia.userregistration.*"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not renderContext.editMode}">
    <template:addResources type="javascript" resources="res/jquery.cycle.all.js"/>
    <template:addResources type="javascript" resources="res/common.js"/>
</c:if>
<template:addResources type="javascript" resources="jquery.validate.js"/>
<template:addResources type="inlinejavascript">
    <script type="text/javascript">
        jQuery.validator.addMethod("equalToValue", function(value,element,param){
            return this.optional(element) || value == param;
        }, "This has to be equal to yes");
        /*RB Alpenite*/
        jQuery.validator.addMethod("uppercase", function(value,element,param){
            return value.toUpperCase() === value;
        }, "<fmt:message key='alpeniteuserregistration.errMsg.usernameNotUpperCase'/>");
        /*END RB*/
        $(document).ready(function() {
            $("#registrationForm").validate({
                rules: {
                    username: {
                        uppercase: true,
                        required: true,
                        minlength: 3,
                        remote: {
                            url: "${url.base}${currentNode.path}.checkUsername.do",
                            type: "post",
                            data:{
                                username : function(){
                                    return $("#username").val();
                                }
                            },
                            dataType: "json",
                            dataFilter: function(data){
                                var json = JSON.parse(data);
                                return json["data"];
                            }
                        }
                    },
                    password: {
                        required: true,
                        minlength: 6
                    },
                    password2: {
                        required: true,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    nome: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() != 'privato_cliente');
                        }
                    },
                    cognome: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() != 'privato_cliente');
                        }
                    },
                    paese: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda');
                        }
                    },
                    provincia: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda');
                        }
                    },
                    comune: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda');
                        }
                    },
                    via: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda');
                        }
                    },
                    cap: {
                        required: function(){
                            return (($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda')&& $('select[name=paese] option:selected').val() == 'IT');
                        }
                    },
                    ncivico: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda');
                        }
                    },
                    extncivico:{
                        required: function(){

                            return (($('input:radio[name=tipoUtente]:checked').val() == 'privato' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda') &&($('#ncivico').val() != null || $('#ncivico').val() != '' ));

                        }
                    },
                    ragione_sociale: {
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'azienda');
                        }
                    },
                    partita_iva: {
                        required: function(){
                            if($('input:radio[name=tipoUtente]:checked').val() == 'azienda' || $('input:radio[name=tipoUtente]:checked').val() == 'amm_condominio'){
                                return true;
                            }
                            return false;
                        }
                    },
                    codice_fiscale:{
                        required: function(){
                            if(($('input:radio[name=tipoUtente]:checked').val() == 'privato_cliente') || ($('input:radio[name=tipoUtente]:checked').val() == 'amm_condominio')){
                                return true;
                            }
                            return false;
                        }
                    },
                    codice_cliente:{
                        required: function(){
                            return ($('input:radio[name=tipoUtente]:checked').val() == 'privato_cliente' || $('input:radio[name=tipoUtente]:checked').val() == 'azienda_cliente');
                        }
                    },
                    conDatiRadio:{
                        required: true,
                        equalToValue: 'si'
                    },
                    newsletterRadio:{
                        required: true
                    },
                    telefonoRadio:{
                        required: true
                    },
                    postaRadio:{
                        required: true
                    },
                    jcrCaptcha: "required"
                },
                messages:{
                    confirm_password:{
                        required: "<fmt:message key='alpeniteuserregistration.chiediPassword'/>",
                        minlength: "<fmt:message key='alpeniteuserregistration.minlength'/>",
                        equalTo: "<fmt:message key='alpeniteuserregistration.confermaPasswordEqualTo'/>"
                    },
                    new_password: {
                        required: "<fmt:message key='alpeniteuserregistration.chiediPassword'/>",
                        minlength: "<fmt:message key='alpeniteuserregistration.minlength'/>"
                    },
                    username: {
                        required: "<fmt:message key='alpeniteuserregistration.chiediUsername'/>",
                        minlength: "<fmt:message key='alpeniteuserregistration.userminlenght'/>",
                        remote: "<fmt:message key='alpeniteuserregistration.userAlreadyCreated'/>"
                    },
                    email: {
                        required: "<fmt:message key='alpeniteuserregistration.mailReq'/>",
                        email: "<fmt:message key='alpeniteuserregistration.validMail'/>"
                    },
                    nome: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.nome'/>"
                    },
                    cognome: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.cognome'/>"
                    },
                    paese: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.paese'/>"
                    },
                    provincia: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.provincia'/>"
                    },
                    comune: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.comune'/>"
                    },
                    cap: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.cap' />"
                    },
                    via: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.via'/>"
                    },
                    ncivico: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.ncivico'/>"
                    },
                    extncivico :{
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.extncivico'/>"
                    },
                    ragione_sociale: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.ragsoc'/>"
                    },
                    partita_iva: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.piva'/>"
                    },
                    codice_fiscale:{
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.cf'/>"
                    },
                    codice_cliente: {
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.cc'/>"
                    },
                    conDatiRadio:{
                        required: "<fmt:message key='alpeniteuserregistration.consensoPrivacy'/>",
                        equalTo: "<fmt:message key='alpeniteuserregistration.consensoPrivacy'/>"
                    },
                    newsletterRadio:{
                        required: "<fmt:message key='alpeniteuserregistration.consenso'/>"
                    },
                    telefonoRadio:{
                        required: "<fmt:message key='alpeniteuserregistration.consenso'/>"
                    },
                    postaRadio:{
                        required: "<fmt:message key='alpeniteuserregistration.consenso'/>"
                    },
                    jcrCaptcha:{
                        required: "<fmt:message key='alpeniteuserregistration.errMsg.captcha'/>"
                    }
                },
                errorPlacement: function(error, element) {
                    error.css("word-wrap", "break-word").css("max-width", "250px"); /*RB Alpenite MAX WIDTH OF ERROR MESSAGE IN PX*/
                    if ( element.is(":radio") ) {
                        error.insertAfter( element.parent() );
                    }
                    else { // This is the default behavior of the script for all fields
                        error.insertAfter( element );
                    }
                    $("#loader").hide();
                    $("#loading").hide();
                    $("#loading").dialog('close');
                }
            });

            $('select[name=paese]').change(function(){
                var value = $('select[name=paese] option:selected').val();
                if(value==""){
                    $('select[name=provincia]').attr('disabled', true);
                    $('select[name=comune]').attr('disabled', true);
                }else{
                    if(value == 'IT'){
                        $('.valueProvinciaEstera').hide();
                        $('.valueComuneEstero').hide();
                        $('.valueProvincia').show();
                        $('.valueComune').show();
                        $('select[name=provincia]').attr('disabled', false);
                    }else{
                        $('.valueProvinciaEstera').show();
                        $('.valueComuneEstero').show();
                        $('.valueProvincia').hide();
                        $('.valueComune').hide();
                        $('select[name=provincia]').attr('disabled', true);
                        $('select[name=cap]').attr('disabled', true);
                    }
                }
            });

            $('select[name=provincia]').change(function(){
                var value = $('select[name=provincia] option:selected').val();
                if(value==""){
                    $('select[name=comune]').attr('disabled', true);
                }else{
                    $('select[name=comune]').attr('disabled', false);
                    $.ajax({
                        url: '${url.base}${currentNode.path}.getComuni.do',
                        type: "POST",
                        data: {"provincia": $('select[name=provincia] option:selected').val()},
                        dataType: "json",
                        success: function(json){
                            $('select[name=comune]').empty().append(function(){
                                comuni = jQuery.parseJSON(json.lista);
                                var output = '<option value="#"></option>';
                                $.each(comuni, function(index, value){
                                    output += '<option value="' + value + '">'+ value + '</option>';
                                });
                                loadCap();
                                return output;
                            });
                        }
                    });
                }
            });

            $('select[name=comune]').change(function(){
                loadCap();
            });


            // setup
            setup();

            $("#partita_iva").parent().append("<p class='partita-iva-message-IT'>inserire partita iva con IT</p>");
            $(".partita-iva-message-IT").css("display","none");
            $('input[type=radio][name=tipoUtente]').change(function() {
                if($(this).val() === "amm_condominio"){
                    if($("#partita_iva").val().length <= 0){
                        $("#partita_iva").val("IT");
                    }
                    $(".partita-iva-message-IT").css("display","block");

                }else{
                    if($("#partita_iva").val().length > 0 && $("#partita_iva").val() === "IT"){
                        $("#partita_iva").val("");
                    }
                    $(".partita-iva-message-IT").css("display","none");
                }
            });
        });


        function checkPaese(){
            var value = $('select[name=paese] option:selected').val();
            if(value==""){
                $('select[name=provincia]').attr('disabled', true);
                $('select[name=comune]').attr('disabled', true);
            }else{
                if(value == 'IT'){
                    $('.valueProvinciaEstera').hide();
                    $('.valueComuneEstero').hide();
                    $('.valueProvincia').show();
                    $('.valueComune').show();
                    $('select[name=provincia]').attr('disabled', false);
                }else{
                    $('.valueProvinciaEstera').show();
                    $('.valueComuneEstero').show();
                    $('.valueProvincia').hide();
                    $('.valueComune').hide();
                    $('select[name=provincia]').attr('disabled', true);
                    $('select[name=cap]').attr('disabled', true);
                }
            }
        }

        function submitCheck(){
            if($("#registrationForm").valid() == false){
                $("#loader").hide();
                $("#loading").hide();
                $("#loading").dialog('close');
                return false;
            }else{
                $("#registrationForm").submit();
            }

        }

        function loadCap(){
            if($('select[name=comune] option:selected').val() != null){
                $('select[name=cap]').attr('disabled', false);
                $.ajax({
                    url: '${url.base}${currentNode.path}.getCap2.do',
                    type: "POST",
                    data: {"comune": $('select[name=comune] option:selected').val()},
                    dataType: "json",
                    success: function(json){
                        $('select[name=cap]').empty().append(function(){
                            comuni = jQuery.parseJSON(json.lista);
                            var output = '';
                            $.each(comuni, function(index, value){
                                output += '<option value="' + value + '">'+ value + '</option>';
                            });
                            return output;
                        });
                    }
                });
            }
        }

        function openPopup(url, window_name){
            window.open(url, window_name,'width=993,height=993,toolbar=yes,menubar=yes,copyhistory=no');
        }

        function resetAddress(){
            //$('select[name=paese]').val("");
            $('select[name=provincia]').val("");
            $('select[name=comune] option').each(function(){
                //TODO: decommentare $(this).remove();
            });
            $('select[name=comune]').attr('disabled', true);
            checkPaese();
        }

        function setup(){
            $('select[name=provincia]').attr('disabled', true); // set the select "provincia" disabled
            var tipoUtente = $('input:radio[name=tipoUtente]:checked').val();
            if(tipoUtente == 'privato'){
                $('.aziendaEnte').hide();
                $('.codiceCliente').hide();
                $('.codFiscale').show();
                $('.piva').hide();
                $('.nomeEcognome').show();
                $('.ruolo').hide();
                $('.ragioneSociale').hide();
                $('.bloccoDati4').show();
                $('.valueProvinciaEstera').hide();
                $('.valueComuneEstero').hide();
                resetAddress();
            }
            if(tipoUtente == 'privato_cliente'){
                $('.aziendaEnte').hide();
                $('.codiceCliente').show();
                $('.codiceClienteField').show();
                $('.codFiscale').show();
                $('.piva').hide();
                $('.nomeEcognome').hide();
                $('.ruolo').hide();
                $('.ragioneSociale').hide();
                $('.bloccoDati4').hide();
                $('.valueProvinciaEstera').hide();
                $('.valueComuneEstero').hide();
                resetAddress();
            }
            if(tipoUtente == 'azienda'){
                $('.aziendaEnte').show();
                $('.codiceClienteField').hide();
                $('.codiceCliente').show();
                $('.piva').show();
                $('.codFiscale').hide();
                $('.nomeEcognome').show();
                $('.ruolo').show();
                $('.ragioneSociale').show();
                $('.bloccoDati4').show();
                $('.valueProvinciaEstera').hide();
                $('.valueComuneEstero').hide();
                resetAddress();
            }
            if(tipoUtente == 'azienda_cliente'){
                $('.aziendaEnte').hide();
                $('.codFiscale').hide();
                $('.codiceClienteField').show();
                $('.codiceCliente').show();
                $('.piva').show();
                $('.nomeEcognome').show();
                $('.ruolo').show();
                $('.ragioneSociale').show();
                $('.bloccoDati4').hide();
                $('.valueProvinciaEstera').hide();
                $('.valueComuneEstero').hide();
                resetAddress();
            }
            if(tipoUtente == 'amm_condominio'){
                $('.aziendaEnte').hide();
                $('.codiceClienteField').hide();
                $('.piva').show();
                $('.codFiscale').show();
                $('.nomeEcognome').show();
                $('.ruolo').hide();
                $('.ragioneSociale').hide();
                $('.bloccoDati4').show();
                $('.valueProvinciaEstera').hide();
                $('.valueComuneEstero').hide();
                resetAddress();
            }
        }

        function setDisplay(id, visible) {
            var o = document.getElementById(id);
            if (typeof(o) != 'undefined') o.style.display = visible ? 'block' : 'none';
            if (typeof(o) == 'undefined') alert("Element with id '" + id + "' not found.");
        }
        function setChecked(id) {
            document.getElementById(id).checked=true;
        }
    </script>
</template:addResources>

<%--
	request.setAttribute("msg", WSReturnManager.getMessageKeys(session));
--%>
<%-- 
<c:if test="${msg != null}">
	<c:forEach items="${msg}" var="messaggio" varStatus="count">
		  <c:if test="${messaggio == 'org.jahia.userregistration.NewUser_P1'}">
		   		<script type="text/javascript">
		   		window.location = "${url.base}${currentNode.properties['finalPage'].node.path}.html";
		   		</script>
		   </c:if>
	</c:forEach>
</c:if>
--%>

<div class=userRegistrationModule>
    <template:tokenizedForm>
        <form method="post" class="frm" id="registrationForm" action="<c:url value='${url.base}${currentNode.path}.newJahiaUser.do'/>" name="registrationForm">

            <input type="hidden" name="jcrNodeType" value="jnt:user"/>

            <input type="hidden" name="jcrRedirectTo" value="${url.base}${renderContext.mainResource.node.path}" />
            <input type="hidden" name="jcrNewNodeOutputFormat" value="html"/>
            <input type="hidden" name="jcrResourceID" value="${currentNode.identifier}"/>
            <!--span class="registrationString"><fmt:message key="alpeniteuserregistration.titolo.registrazione"/></span -->

            <div class="userTypeChoice search">
                <h3><fmt:message key="alpeniteuserregistration.tipologia"/></h3>
                <div id="nonCliente_${currentnode.identifier}" style="display: none">
                    <fieldset>
        <span class="customerOpt">
        <!--<input type="button" class="btn extralarge"  value="<fmt:message key="alpeniteuserregistration.button.cliente"/>"
               onclick="setChecked('privato_cliente'); setDisplay('sonoCliente_${currentnode.identifier}', true); setDisplay('nonCliente_${currentnode.identifier}', false); setup();"/>-->
        </span>
                    </fieldset>
                    <fieldset>
        <span class="privateOpt">
        <input type='radio' class="radio" name='tipoUtente' value='privato' id="privato" onclick="setup();"/>
        <fmt:message key="alpeniteuserregistration.radio.prospect_privato"/>
        </span>
                        <span class="companyOpt">
        <input type='radio' class="radio" name='tipoUtente' id='tipo' value='azienda'
               onclick="setup();" />
        <fmt:message key="alpeniteuserregistration.radio.prospect_azienda"/>
        </span>
                    </fieldset>
                </div>

                <div id="sonoCliente_${currentnode.identifier}">
                    <fieldset>
        <span class="customerOpt">
        <!--<input type="button" class="btn extralarge"  value="<fmt:message key="alpeniteuserregistration.button.non_cliente"/>"
               onclick="setChecked('privato'); setDisplay('nonCliente_${currentnode.identifier}', true); setDisplay('sonoCliente_${currentnode.identifier}', false); setup();"/>-->
        </span>
                    </fieldset>
                    <fieldset>
        <span class="privateOpt">
        <input type='radio' class="radio" name='tipoUtente' id='privato_cliente' value='privato_cliente' checked="checked"
               onclick="setup();"/>
         <fmt:message key="alpeniteuserregistration.radio.cliente_privato"/>
         </span>
                        <span class="companyOpt">
        <input type='radio' class="radio" name='tipoUtente' id='tipo' value='azienda_cliente'
               onclick="setup();"/>
         <fmt:message key="alpeniteuserregistration.radio.cliente_azienda"/>
        </span>
                        <span class="ammCondOpt">
        <input type='radio' class="radio" name='tipoUtente' id='tipo' value='amm_condominio'
               onclick="setup();"/>
         <fmt:message key="alpeniteuserregistration.radio.amm_condominio"/>
        </span>
                    </fieldset>
                </div>

            </div>



            <div class="userRegistrationForm">
                <div class="formOptions">
                    <c:if test="${not empty currentNode.properties['from']}">
                        <input type="hidden" name="from" value="${currentNode.properties['from'].string}"/>
                    </c:if>
                    <c:if test="${not empty currentNode.properties['cc']}">
                        <input type="hidden" name="cc" value="${currentNode.properties['cc'].string}"/>
                    </c:if>
                    <c:if test="${not empty currentNode.properties['bcc']}">
                        <input type="hidden" name="bcc" value="${currentNode.properties['bcc'].string}"/>
                    </c:if>
                    <c:if test="${not empty currentNode.properties['subject']}">
                        <input type="hidden" name="subject" value="${currentNode.properties['subject'].string}"/>
                    </c:if>
                    <c:if test="${not empty currentNode.properties['verificationPage']}">
                        <input type="hidden" name="verificationPage" value="${currentNode.properties['verificationPage'].string }" />
                    </c:if>
                    <input type="hidden" name="finalPage" value="${currentNode.properties['finalPage'].string }" />
                    <input type="hidden" name="redirectPage" value="${currentNode.properties['userRedirectPage'].string}" />
                    <c:choose>
                        <c:when test="${not empty currentResource.locale}">
                            <input type="hidden" name="lang" value="${currentResource.locale}" />
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="lang" value="${param.locale}" />
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="currentNode" value="${currentNode.identifier}" />

                </div>
                <div class="personalData">
                    <div class="ctn-data pag">
                        <!-- <h3><fmt:message key="alpeniteuserregistration.datiAnagraficiTitle"/></h3> -->
                        <jsp:include page="dati.jsp" />
                    </div>
                </div>

                <div class="portalData">
                    <div class="ctn-data pag">
                        <h3><fmt:message key="alpeniteuserregistration.datiAccessoPortaleTitle"/></h3>

                        <table border="0" cellpadding="0" cellspacing="0" class="data">
                            <tr>
                                <th><label for="email"><fmt:message key="mail"/></label></th>
                                <td><input type="text" name="email" id="email" value="${sessionScope.formDatas['email'][0]}"/></td>
                                <th><label for="username"><fmt:message key="username"/></label></th>
                                <td>
                                    <!-- RB Alpenite -->
                                    <div class="ctn-info">
                                        <a href="#1" class="info">
                                            <fmt:message key="altBaloonUsername"/>
                                        </a>
                                        <div class="balloon">
                                            <br />
                                            <fmt:message key="baloonTextUsername" />
                                            <a href="#1" class="close"><fmt:message key="ballonClose"/>
                                            </a>
                                        </div>
                                    </div>
                                    <!-- END RB -->
                                    <input type="text" name="username" id="username" value="${sessionScope.formDatas['username'][0]}" onkeyup="this.value = this.value.toUpperCase();">
                                    </input>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="password"><fmt:message key="password1"/></label></th>
                                <td><input type="password" name="password"  id="password"/></td>
                                <th><label for="password2"><fmt:message key="password2"/></label></th>
                                <td><input type="password" name="password2"  id="password2"/></td>
                            </tr>
                        </table>
                    </div>

                    <div class="consensi">
                        <div class="ctn-data pag">
                            <h3><fmt:message key="alpeniteuserregistration.consensiTitle"/></h3>

                            <table border="0" cellpadding="0" cellspacing="0" class="data">
                                <tr>
                                    <th class="large"><fmt:message key="consenso_trattamento_dati"/></th>
                                    <td><div><fmt:message key="si"/><input class="radio" type="radio" name="conDatiRadio" id="conDatiRadio" value="si"/><fmt:message key="no"/><input class="radio" type="radio" name="conDatiRadio" id="conDatiRadio" value="no"/></div></td>
                                </tr>
                                <tr>
                                    <th class="large"><fmt:message key="consenso_info_mail"/></th>
                                    <td><div><fmt:message key="si"/><input class="radio" type="radio" name="newsletterRadio" id="newsletterRadio" value="si"/><fmt:message key="no"/><input class="radio" type="radio" name="newsletterRadio" id="newsletterRadio" value="no"/></div></td>
                                </tr>
                                <tr>
                                    <th class="large"><fmt:message key="consenso_info_tel"/></th>
                                    <td><div><fmt:message key="si"/><input class="radio" type="radio" name="telefonoRadio" id="telefonoRadio" value="si"/><fmt:message key="no"/><input class="radio" type="radio" name="telefonoRadio" id="telefonoRadio" value="no"/></div></td>
                                </tr>
                                <tr>
                                    <th class="large"><fmt:message key="consenso_info_posta"/></th>
                                    <td><div><fmt:message key="si"/><input class="radio" type="radio" name="postaRadio" id="postaRadio" value="si"/><fmt:message key="no"/><input class="radio" type="radio" name="postaRadio" id="postaRadio" value="no"/></div></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="txt"><a onclick="openPopup('${currentNode.properties['privacyPage'].node.url}', 'privacy')" href="#"><fmt:message key="informativaPrivacy" /></a></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="txt"><a onclick="openPopup('${currentNode.properties['condizioniServizioPage'].node.url}', 'condizioni_servizio')" href="#"><fmt:message key="condizioniServizio" /></a></td>
                                </tr>
                            </table>
                        </div>
                    </div>


                    <div class="ctn-data pag">
                        <h3><fmt:message key="alpeniteuserregistration.captcha" /></h3>
                        <table border="0" cellpadding="0" cellspacing="0" class="data">
                            <tr>
                                <td colspan="3"><fmt:message key="alpeniteuserregistration.messaggioCaptcha" /></td>
                            </tr>
                            <tr>
                                <th colspan="2"><label class="jcrCaptcha" for="jcrCaptcha"><template:captcha/></label></th>
                                <td><input type="text" id="jcrCaptcha" name="jcrCaptcha"/></td>
                            </tr>
                        </table>
                    </div>
                    <hr />

                    <div class="pagination">
                        <div class="paginationNavigation">
                            <input onclick="submitCheck()" type="button" id="submitRegistrationForm" class="btn medium" value='<fmt:message key="userregistration.button"/>'/>
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </template:tokenizedForm>

</div>

