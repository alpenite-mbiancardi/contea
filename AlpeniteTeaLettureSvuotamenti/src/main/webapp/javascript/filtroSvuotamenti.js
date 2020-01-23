$(function() {
    
    $("table#tabellaSvuotamenti").tablesorter();
    
    $("#listaBidoni").hide();

    // creo il dialogo a partire dal modulo
    var $dialogBidoni = $("#listaBidoni").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
        
    var $dialogSacchetti = $("#listaSacchetti").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#bottoneVisualizzaBidoni").click(function() {
    	$dialogBidoni.dialog('open');
        return false;
    });
    
    
    $("#bottoneVisualizzaSacchetti").click(function() {
    	$dialogSacchetti.dialog('open');
        return false;
    });
    
});
