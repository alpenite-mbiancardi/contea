$(function() {
    
    validator = $("#formModificaDatiCatastali").validate({
        rules: {                	
            tipoParticella: {
                maxlength: 1
            },
            particellaSistemaTavolare: {
                maxlength: 10
            },
            particella: {
                maxlength: 5
            },
            subalterno: {
                maxlength: 4
            },
            sezioneUrbana: {
                maxlength: 3
            }
        },
        messages:{
            tipoParticella: {
                maxlength: "<fmt:message key='datiCatastali.tipoParticella.maxlength'/>"
            },
            particellaSistemaTavolare: {
                maxlength: "<fmt:message key='datiCatastali.particellaSistemaTavolare.maxlength'/>"
            },
            particella: {
                maxlength: "<fmt:message key='datiCatastali.particella.maxlength'/>"
            },
            subalterno: {
                maxlength: "<fmt:message key='datiCatastali.subalterno.maxlength'/>"
            },
            sezioneUrbana: {
                maxlength: "<fmt:message key='datiCatastali.sezioneUrbana.maxlength'/>"
            }
        }
    });
    
    
    $("#moduloDatiCatastali").hide();

    // creo il dialogo a partire dal modulo
    var $dialog = $("#moduloDatiCatastali").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false
    //        closeText: "X"
    });
    
    // aggiungo l'asterisco ai campi obbligatori
    $("#moduloDatiCatastali .obbligatorio").each(function() {
        $(this).prev().html($(this).prev().html()+"*");
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#bottoneModificaDatiCatastali").click(function() {
        $dialog.dialog('open');
        return false;
    });
    
    // valido il form quando viene inviato
    $("#formModificaDatiCatastali").submit(function() {
        var invia = true;
        
        // verifico i campi obbligatori
        $("#moduloDatiCatastali .obbligatorio").each(function() {
            if ($.trim($(this).val()) == "") {
                $(this).addClass("nonCompleto");
                invia = false;
            } else {
                $(this).addClass("completo");
            }
        });
        
        // valido i valori
        invia = validator.valid();
        
        if (invia) {
            $dialog.dialog('close');
        }
        return invia;
    });
    
    // creo la struttura json comune-codice
    var comuneCodice = new Array();
    
    // gestisco il campo provincia tramite ajax
    $('#provincia').change(function(){
         $('#comuneCatastale').val("");
         $('#codiceCatastale').val("");        
        var valore = $('#provincia option:selected').val();
        if(valore==""){
            $('#comuneAmministrativo').attr('disabled', true);
        }else{
            $('#comuneAmministrativo').attr('disabled', false);
            $.ajax({
                url: urlComuni + '.comuni.do',
                type: "POST",
                data: {
                    "provincia": $('#provincia option:selected').val()
                    },
                dataType: "json",
                success: function(json){
                    $('#comuneAmministrativo').empty().html(function(){
                        var output = '<option></option>';
                        $.each(json.comuni, function(index, value){
                            output += '<option value="' + value.nome + '">'+ value.nome + '</option>';
                            comuneCodice[value.nome] = value.codiceCatastale;
                        });
                        return output;
                    });
                }
            });
        }
    });
    
     $('#comuneAmministrativo').change(function(){
         var nome = $('#comuneAmministrativo option:selected').val();
         $('#comuneCatastale').val(nome);
         $('#codiceCatastale').val(comuneCodice[nome]);
     });
});