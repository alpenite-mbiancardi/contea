$(function() {
   
    /*function readOnly(flag) {
        // imposto in sola lettura tutti i componenti del form recapito
        $("#formRecapito :input").prop('disabled', flag);
   
        // abilito sempre il bottone modifica
        $("#bottoneModifica").prop('disabled', false);

        // abilito la conferma sse non e'readonly
        $("#bottoneConferma").prop('disabled', flag);        
        
        if (flag) {
            // nascondo il bottone conferma
            $("#bottoneConferma").hide();
        } else {
            // visualizzo il bottone conferma
            $("#bottoneConferma").fadeIn("slow");
        }
    }*/
    
   // readOnly(true);
   
    // alla pressione del bottone modifica abilito i campi disabilitati
    // e visualizzo il bottone conferma
    $("#bottoneModifica").click(function() {
        if ($(this).val() == modifica) {
            readOnly(false);
            $("#bottoneModifica").val(annullaModifica);
        } else if ($(this).val() == annullaModifica) {
            readOnly(true);
            $("#bottoneModifica").val(modifica);
        }
    });
   
    // alla pressione del bottone conferma, se e'visualizzato, invio il form
    $("#bottoneConferma").click(function() {
    		if ($("#numeroCivico").val() != "") {
			$("#formRecapito").submit();
			}else
			if(document.getElementsByClassName("error").length == 0){
			$('#numeroCivico').after("<p class='error'> Campo Obbligatorio</p>");
			}			
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
    
    $('#comune').change(function(){loadCap();});
    
    $("#recapitoModificaForm").hide();

    // creo il dialogo a partire dal modulo
    var $dialog = $("#recapitoModificaForm").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    
    // visualizza la pagina contenente il modulo da compilare
    $("#recapitoModificaButton").click(function() {
    	$(".ui-dialog-titlebar").show();
        $dialog.dialog('open');
        return false;
    });
    
   /* // valido il form quando viene inviato
    $("#formRecapito").submit(function() {
        $dialog.dialog('close');
        return true;
    });*/
    
    if($('#provincia option:selected').val() != null){
    	loadComune();
    }
    
});

function loadComune() {
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
                    if (value.nome==com) {
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

function loadCap(){
	if($('select[id=comune] option:selected').val() != null){
		$('#cap').attr('disabled', false);
		$.ajax({
        	url: urlComuni + '.getCap.do',
        	type: "POST",
        	data: {"comune": $('select[id=comune] option:selected').val()},
        	dataType: "json",
        	success: function(json){
        		$('#cap').empty().append(function(){
        			comuni = jQuery.parseJSON(json.lista);
        			var output = '';
        			$.each(comuni, function(index, value){
        				var sel = '';
                        if (value.nome == cap) {
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


