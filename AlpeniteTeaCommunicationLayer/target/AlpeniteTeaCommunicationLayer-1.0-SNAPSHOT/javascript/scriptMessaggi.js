$(function() {
    
    // creo il dialogo
    var $dialog = $(".popup").dialog({
        autoOpen: true,
        modal: true,
        draggable: false,
        width: 400,
        resizable: false,
        closeText: "Chiudi"
    });
    
//    $dialog.dialog('open');
    
    // chiudo la finestra con i messaggi
    $(".popup #bottoneConferma").click(function() {
        $dialog.dialog('close');
    });
    
});