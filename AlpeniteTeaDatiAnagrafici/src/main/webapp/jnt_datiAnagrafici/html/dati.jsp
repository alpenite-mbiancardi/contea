<%@page import="com.alpenite.gui.components.SelectProvince"%>
<%@page import="com.alpenite.gui.components.SelectCountries"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
<template:addResources type="inlinejavascript">
	<script type="text/javascript">
		$(document).ready(function() {
			$('select[name=paese]').change(function() {
				var value = $('select[name=paese] option:selected').val();
				if (value == "") {
					$('select[name=provincia]').attr('disabled', true);
					$('select[name=comune]').attr('disabled', true);
				} else {
					if (value == 'IT') {
						$('.valueProvinciaEstera').hide();
						$('.valueComuneEstero').hide();
						$('.valueProvincia').show();
						$('.valueComune').show();
						$('select[name=provincia]').attr('disabled', false);
					} else {
						$('.valueProvinciaEstera').show();
						$('.valueComuneEstero').show();
						$('.valueProvincia').hide();
						$('.valueComune').hide();
						$('select[name=provincia]').attr('disabled', true);
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
                    			var output = '';
                    			$.each(comuni, function(index, value){
                    				output += '<option value="' + value + '">'+ value + '</option>';
                    			});
                    			return output;
                    		});
                    	}
                    });
            	}
            });
		});
	</script>
</template:addResources>
 --%>
<tr>
    <th><label for="phone"><fmt:message key="telefono" /></label></th>
    <td>
    	<!--  MODIFICA UNISYS 16-1-14 -->
			<c:choose>
			<c:when test="${fn:length(datiAnagrafici.prefissoTel) > 0}">
				<c:set var="prfixTel" value="${datiAnagrafici.prefissoTel}"/>
			</c:when> 
			<c:otherwise>
				<c:set var="prfixTel" value="+39"/>
			</c:otherwise>
		</c:choose>
		
		<input style="width: 30px!important;" type="text" name="prefixTel" class="small" id="prefixTel" value="${prfixTel}" />   	    	
    	<input type="text" name="telefono" id="telefono" value="${datiAnagrafici.telefono}" />
    </td>
    <th><label for="phone"><fmt:message key="cellulare" /></label></th>
    <td>
    
    	<c:choose>
			<c:when test="${fn:length(datiAnagrafici.prefissoCel) > 0}">
				<c:set var="prfixCel" value="${datiAnagrafici.prefissoCel}"/>
			</c:when> 
			<c:otherwise>
				<c:set var="prfixCel" value="+39"/>
			</c:otherwise>
		</c:choose>
		
		<input style="width: 30px!important;" type="text" name="prefixCel" class="small" id="prefixCel" value="${prfixCel}" />
		<input type="text" name="cellulare" id="cellulare" value="${datiAnagrafici.cellulare}" />
						    	
		<!--  MODIFICA UNISYS fine-->
    </td>
</tr>

<%--
<div class="bloccoDati3">
	<div class="telefono">
		<span class="label"> <label for="telefono"><fmt:message
					key="telefono" /></label>
		</span> <span class="value"> 
		<input type="text" name="prefixTel" id="prefixTel" value="${datiAnagrafici.prefissoTel}" />
		<input type="text" name="telefono"
			id="telefono" value="${datiAnagrafici.telefono}" />
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
