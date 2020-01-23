$(function() {
    
    $("#modificaIndirizzoFornitura").hide();
$("#modificaContatto").hide();

    // creo il dialogo a partire dal modulo
    var $dialogIndForn = $("#modificaIndirizzoFornitura").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });

var $dialogIndForn2 = $("#modificaContatto").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    

    
    // aggiungo l'asterisco ai campi obbligatori
    $("#modificaIndirizzoFornitura .obbligatorio").each(function() {
        $(this).prev().html($(this).prev().html()+"*");
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#bottoneModificaIndirizzo").click(function() {
        $dialogIndForn.dialog('open');
        return false;
    });

    $("#bottoneModificaTipoContatto").click(function() {
        $dialogIndForn2.dialog('open');
        return false;
    });




    
    // valido il form quando viene inviato
    $("#formModificaIndirizzo").submit(function() {
        var invia = true;
        
        // verifico i campi obbligatori
        $("#modificaIndirizzoFornitura .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        if (invia) {
            $dialogIndForn.dialog('close');
        }
        return invia;
    });
});
/*
$("#modificaIndirizzoFornitura ").submit(function() {
        var invia = true;
               // verifico i campi obbligatori
        $("#modificaIndirizzoFornitura .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        if (invia) {
	
            $dialogIndForn.dialog('close');
		
        }
        return invia;
*/


 /* valido il form quando viene inviato*/
 
 
 $(document).ready(function(){
    $("#bottoneConfermaNumeroCivico").click(function() {
        var invia = true;
        if($('#numeroCivicoFornitura').val()==""){
			if(document.getElementsByClassName("error").length == 0){
			$('#numeroCivicoFornitura').after("<p class='error'> Campo Obbligatorio</p>");
			}
			invia = false;
		} 

        // verifico i campi obbligatori
        $("#modificaIndirizzoFornitura .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        if (invia) {
	
            $dialogIndForn.dialog('close');
		
        }
        return invia;
    }); 


$("#bottoneConfermaTipoContatto").click(function() {
        var invia = true;
        
        
        if (invia) {
	
            $dialogIndForn.dialog('close');
		
        }
        return invia;
    }); 
 });
