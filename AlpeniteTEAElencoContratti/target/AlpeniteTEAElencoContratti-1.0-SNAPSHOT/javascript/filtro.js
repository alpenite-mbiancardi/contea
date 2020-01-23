$(function() {
    
    // creo l'azione da eseguire per filtrare la tabella
    var doFiltra = function(){
        // prelevo il valore del campo filtro
        var valore = $("#filtro").val();
        t = $('table#tabellaContratti');
        $.uiTableFilter( t, valore );

        // visualizzo o nascondo il messaggio in base al valore
        if (valore != "") {
            $("div#messaggio").html(msg + ": " + valore);
            $("#messaggio").fadeIn("slow");
        } else
            $("#messaggio").fadeOut("slow");
    }    
    
    // assegno il table sorter alla tabella
    $("table#tabellaContratti").tablesorter();
    
    // nascondo i messaggi
    $("div#messaggio").hide();
        
    // alla pressione del bottone conferma o alla pressione dell'invio sul
    // campo filtro applica i filtri impostati
    $("#bottoneConferma").click(doFiltra);
    $("#filtro").keydown(function(event) {
        if (event.which == 13)
            $("#bottoneConferma").click();
    });
        
    // alla pressione del bottone annulla vengono azzerati i filtri  e confermato il filtro  
    $("#bottoneAnnulla").click(function() {
        $("#filtro").prop("value", "");
        $("#bottoneConferma").click();
    });
});
