$(function() {
    
    // nascondo i div per la rimozione e l'aggiunta dei condomini
    $("#moduloAggiungiCondomini").hide();
    $("#moduloRimuoviCondomini").hide();
    
    // creo i dialoghi a partire dai moduli
    var $dialogAggiungi = $("#moduloAggiungiCondomini").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });    
    var $dialogRimuovi = $("#moduloRimuoviCondomini").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false,
        closeText: "X"
    });
    
    // al click dei bottoni visualizzo i relativi dialogi
    $("#bottoneAggiungiCondomini").click(function() {
        $dialogAggiungi.dialog('open');
        return false;
    });
    $("#bottoneRimuoviCondomini").click(function() {
        $dialogRimuovi.dialog('open');
        return false;
    });
    
    // assegno il table sorter alle tabelle
    $("table#tabellaCondomini").tablesorter();
    $("table#tabellaRimuoviCondomini").tablesorter();
    
    // alla selezione di un radio eseguo il submit del form formSelezionaCondominio
    $("#formSelezionaCondominio #selezione").click(function() {
        $("#formSelezionaCondominio").submit();
    })
    
    $("#moduloAggiungiCondomini #bottoneAggiungiCondominioLista").click(function() {
        $.ajax({
            url: urlAggiungiLista,
            type: "POST",
            data: {
                "ragioneSociale1": $('#moduloAggiungiCondomini #ragioneSociale1').val(),
                "ragioneSociale2": $('#moduloAggiungiCondomini #ragioneSociale2').val(),
                "paese": $('#moduloAggiungiCondomini #paese').val(),
                "provincia": $('#moduloAggiungiCondomini #provincia').val(),
                "comune": $('#moduloAggiungiCondomini #comune').val(),
                "frazione": $('#moduloAggiungiCondomini #frazione').val(),
                "cap": $('#moduloAggiungiCondomini #cap').val(),
                "via": $('#moduloAggiungiCondomini #via').val(),
                "numeroCivico": $('#moduloAggiungiCondomini #numeroCivico').val(),
                "estensioneNumeroCivico":  $('#moduloAggiungiCondomini #estensioneNumeroCivico').val(),
                "contoContrattuale": $('#moduloAggiungiCondomini #contoContrattuale').val()
            },
            dataType: "json",
            success: function(json){
                var selezionato = '<td class="colonna selezione"><input type="checkbox" checked="checked" name="selezione" id ="selezione" value="'+json.codiceBP+'" /></td>';
                var nome = '<td class="colonna nome">'+json.ragioneSociale1+' '+ json.ragioneSociale2+'</td>';
                var indirizzo = '<td class="colonna indirizzo">'+json.via+', '+json.numeroCivico+'</td>';
                var comune = '<td class="colonna comune">'+json.comune+'</td>';
                var riga = selezionato+nome+indirizzo+comune;
                $('#formAggiungiCondomini #tabellaCondominiRighe').append('<tr>'+riga+'</tr>');
                $('#formAggiungiCondominiLista #bottoneReset').click();
            }
        });
    });    
});
