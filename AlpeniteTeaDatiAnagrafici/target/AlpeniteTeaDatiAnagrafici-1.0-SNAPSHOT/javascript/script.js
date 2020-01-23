$(function() {
    
	$("#moduloDatiAnagrafici").hide();

	// creo il dialogo a partire dal modulo
	var $dialogda = $("#moduloDatiAnagrafici").dialog({
		autoOpen: false,
		modal: true,
		draggable: false,
		width: 680,
		resizable: false,
		closeText: "X"
	});
    
	// aggiungo l'asterisco ai campi obbligatori
	$("#moduloDatiAnagrafici .obbligatorio").each(function() {
		$(this).prev().html($(this).prev().html()+"*");
	});
    
	// visualizza la pagina contenente il modulo da compilare
	$("#bottoneModificadatiAnagrafici").click(function() {
		$("#moduloDatiAnagrafici").dialog('open');
		return false;
	});
    
	// valido il form quando viene inviato
	$("#formModificadatiAnagrafici").submit(function() {
		var invia = true;
        
		// verifico i campi obbligatori
		$("#moduloDatiAnagrafici .obbligatorio").each(function() {
			if ($.trim($(this).val()) == "") {
				$(this).addClass("nonCompleto");
				invia = false;
			} else {
				$(this).addClass("completo");
			}
		});
        
		if (invia) {
			$dialogda.dialog('close');
		}
		return invia;
	});

	// gestisco il campo provincia tramite ajax
	$('#provincia').change(function(){
		var valore = $('#provincia option:selected').val();
		if(valore==""){
			$('#comune').attr('disabled', true);
		}else{
			$('#comune').attr('disabled', false);
			loadComune();
		}
	});
    
	$('#comune').change(function(){
		loadCap();
	});
    
	// carico i comuni
	if($('#provincia option:selected').val() != ""){
		loadComune();
	}
    
});

function loadComune() {
	if($('#provincia option:selected').val() != ""){
		$.ajax({
			url: urlComuni + '.comuni.do',
			type: "POST",
			data: {
				"provincia": $('#provincia option:selected').val()
			},
			dataType: "json",
			success: function(json){
				$('#comune').empty().html(function(){
					var output = '<option value="#"></option>';
					$.each(json.comuni, function(index, value){
						var sel = '';
						if (value.nome == com) {
							sel = "selected=\"selected\"";
						}
						output += '<option '+sel+' value="' + value.nome + '">'+ value.nome + '</option>';
					});
					loadCap();
					return output;
				});
			}
		});  
	}
}

function loadCap(){
	if($('select[id=comune] option:selected').val() != null){
		$('#cap').attr('disabled', false);
		$.ajax({
			url: urlComuni + '.getCap.do',
			type: "POST",
			data: {
				"comune": $('select[id=comune] option:selected').val()
				},
			dataType: "json",
			success: function(json){
				$('#cap').empty().append(function(){
					comuni = jQuery.parseJSON(json.lista);
					var output = '';
					$.each(comuni, function(index, value){
						var sel = '';
						if (value.nome==cap) {
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
function validateAnagrafica(formId){
	var result=true;
	$('.error').detach();
	if($('#'+formId+' #ragioneSociale1').is('input')){      //Esiste il campo ragione sociale 1 e
		if($('#'+formId+' #ragioneSociale1').val()==""){    //tale campo &egrave; non vuoto
			result = false;
			$('#'+formId+' #ragioneSociale1').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
		}
	}
	
	if($('#'+formId+' #nome').is("input") &&  //Esiste il campo "nome"
	   $('#'+formId+' #cognome').is('input')){//Esiste il campo "Cognome"
		   
		if($('#'+formId+' #nome').val()==""){//Se il campo nome è vuoto va in errore
			result = false;
			$('#'+formId+' #nome').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
		}
		if($('#'+formId+' #cognome').val()==""){//Se il campo cognome è vuoto va in errore
			result = false;
			$('#'+formId+' #cognome').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
		}
	}	
				if ( $('#'+formId+' #ncivico').val()=="" ) {
				result = false;
				console.log(result );
				$('#'+formId+' #ncivico').after("<p class='error'>"+errMsg+"</p>");//Messaggio di errore
				
		}
		
	

	if(result){
		document.forms[formId].submit();
	}
	return result;
}