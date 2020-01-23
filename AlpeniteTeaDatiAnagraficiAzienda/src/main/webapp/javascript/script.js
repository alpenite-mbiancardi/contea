$(function() {
    
    $("#moduloDatiAnagraficiAzienda").hide();

    // creo il dialogo a partire dal modulo
    var $dialogdaa = $("#moduloDatiAnagraficiAzienda").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
		closeText: "X"
    });
    
    // aggiungo l'asterisco ai campi obbligatori
    $("#moduloDatiAnagraficiAzienda .obbligatorio").each(function() {
        $(this).prev().html($(this).prev().html()+"*");
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#bottoneModificadatiAnagraficiAzienda").click(function() {
    	$("#moduloDatiAnagraficiAzienda").dialog('open');
        return false;
    });
    
    // valido il form quando viene inviato
    $("#formModificadatiAnagraficiAzienda").submit(function() {
        var invia = true;
        
        // verifico i campi obbligatori
        $("#moduloDatiAnagraficiAzienda .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        if (invia) {
            $dialogdaa.dialog('close');
        }
        return invia;
    });
    
 // gestisco il campo provincia tramite ajax
    $('#provinciaAzienda').change(function(){
        var valore = $('#provinciaAzienda option:selected').val();
        if(valore==""){
            $('#comuneAzienda').attr('disabled', true);
        }else{
            $('#comuneAzienda').attr('disabled', false);
            loadComuneAzienda();
        }
    });
    
    $('#comuneAzienda').change(function(){loadCapAzienda();});
    
 // carico i comuni
    if($('#provinciaAzienda option:selected').val() != ""){
    	loadComuneAzienda();
    }
    
});

function loadComuneAzienda() {
    $.ajax({
        url: urlComuniAzienda + '.comuni.do',
        type: "POST",
        data: {
            "provincia": $('#provinciaAzienda option:selected').val()
        },
        dataType: "json",
        success: function(json){
            $('#comuneAzienda').empty().html(function(){
                var output = '<option value="#"></option>';
                $.each(json.comuni, function(index, value){
                    var sel = '';
                    if (value.nome==comAzienda) {
                        sel = "selected=\"selected\"";
                    }
                    output += '<option '+sel+' value="' + value.nome + '">'+ value.nome + '</option>';
                });
                loadCapAzienda();
                return output;
            });
        }
    });         
}

function loadCapAzienda(){
	if($('select[id=comuneAzienda] option:selected').val() != null){
		$('#capAzienda').attr('disabled', false);
		$.ajax({
			url: urlComuniAzienda + '.getCap.do',
        	type: "POST",
        	data: {"comune": $('select[id=comuneAzienda] option:selected').val()},
        	dataType: "json",
        	success: function(json){
        		$('#capAzienda').empty().append(function(){
        			cap = jQuery.parseJSON(json.lista);
        			var output = '';
        			$.each(cap, function(index, value){
        				var sel = '';
                        if (value.nome==capAzienda) {
                            sel = "selected=\"selected\"";
                        }
        				output += '<option '+sel+' value="' + value + '">'+ value + '</option>';
        			});
        			return output;
        		});
        	}
        });
	}
}

/**
 * Function di validazione del modulo dei dati anagrafici. Ritorna true se i para
 * metri sono validi, false in caso contrario.
 * 
 * @param formId id del form da validare
 * 
 */
function validateAnagraficaAzienda(formId){
	var result=true;
	$('.error').detach();
	if($('#'+formId+' #ragioneSociale1').is('input')){      //Esiste il campo ragione sociale 1 e
		if($('#'+formId+' #ragioneSociale1').val()==""){    //tale campo &egrave; non vuoto
			result = false;
			$('#'+formId+' #ragioneSociale1').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
		}
	}
	
	/*if($('#'+formId+' #nome').is("input") &&  //Esiste il campo "nome"
	   $('#'+formId+' #cognome').is('input')){//Esiste il campo "Cognome"
		   
		if($('#'+formId+' #nome').val()==""){//Se il campo nome è vuoto va in errore
			result = false;
			$('#'+formId+' #nome').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
		}
		if($('#'+formId+' #cognome').val()==""){//Se il campo cognome è vuoto va in errore
			result = false;
			$('#'+formId+' #cognome').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
		}
	}*/
	if( $('#'+formId+' #ncivico').is('input')){//Esiste il campo "extncivico")
		if($('#'+formId+' #ncivico').val()==""){
				result = false;
				$('#'+formId+' #ncivico').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
				}

		}
		
	
		if(result){
		document.forms[formId].submit();
	}
	return result;
}

