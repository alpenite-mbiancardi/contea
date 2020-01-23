$(function() {
    
    $("#datiUtentePortalePassword").hide();

    // creo il dialogo a partire dal modulo
    var $dialog = $("#datiUtentePortalePassword").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    
    // visualizza la pagina contenente il modulo da compilare
    $("#modificaDatiUtentePassword").click(function() {
        $dialog.dialog('open');
        return false;
    });
    
    // valido il form quando viene inviato
    $("#datiUtenteFormPassword").submit(function() {
        $dialog.dialog('close');
        return true;
    });
	
	    $("#datiUtentePortaleEmail").hide();

    // creo il dialogo a partire dal modulo
    var $dialog1 = $("#datiUtentePortaleEmail").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    
    // visualizza la pagina contenente il modulo da compilare
    $("#modificaDatiUtenteEmail").click(function() {
        $dialog1.dialog('open');
        return false;
    });
    
    // valido il form quando viene inviato
    $("#datiUtenteFormEmail").submit(function() {
        $dialog1.dialog('close');
        return true;
    });
});