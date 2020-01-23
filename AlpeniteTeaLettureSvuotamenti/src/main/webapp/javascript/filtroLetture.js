$(function() {
    
    $("table#tabellaLetture").tablesorter();
    
    $("#autolettura").hide();
    
    // creo il dialogo a partire dal modulo
    var $dialogAutoletture = $("#autolettura").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#bottoneAutolettura").click(function() {
        $dialogAutoletture.dialog('open');
        return false;
    });
        
});
