$(function() {
   
    // aggiungo l'asterisco ai campi obbligatori
    $("#moduloAutolettura .obbligatorio").each(function() {
        $(this).prev().html($(this).prev().html()+"*");
    });
    
    // valido il form quando viene inviato
    $("#moduloAutolettura").submit(function() {
        var invia = true;
        
        // verifico i campi obbligatori
        $("#moduloAutolettura .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        if (invia) {
            $dialogAutoletture.dialog('close');
        }
        return invia;
    });    
   
});

