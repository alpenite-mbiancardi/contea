$(function() {
    
    $("#moduloDomiciliazioneBancaria").hide();

    // creo il dialogo a partire dal modulo
    var $dialog = $("#moduloDomiciliazioneBancaria").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    // aggiungo l'asterisco ai campi obbligatori
    $("#moduloDomiciliazioneBancaria .obbligatorio").each(function() {
        $(this).prev().html($(this).prev().html()+"*");
    });
    
    $("#modificaDomiciliazioneSEPA").click(function() {
    	location.href = urlLocation;
    });
    
    $("#downloadMandato").click(function(){
    	location.href = urlLocationDownloadMandato;
    });
    $("#downloadRevoca").click(function(){
    	location.href = urlLocationDownloadRevoca;
    });
    $("#uploadMandato").click(function(){
    	location.href = urlLocationUploadMandato;
    });
    $("#uploadRevoca").click(function(){
    	location.href = urlLocationUploadRevoca;
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#modificaDomiciliazione").click(function() {
    	$(".ui-dialog-titlebar").show();
    	
        $dialog.dialog('open');
        return false;
    });
    
    // valido il form quando viene inviato
    $("#formModificaDomiciliazioneBancaria").submit(function() {
        var invia = true;
        
        // verifico i campi obbligatori
        $("#moduloDomiciliazioneBancaria .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        if (invia) {
            $dialog.dialog('close');
        }
        $("#loader").hide();
        $("#loading").hide();
        $("#loading").dialog('close');
        return invia;
    });
});