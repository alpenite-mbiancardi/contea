var FORM_ID          = "documentaleListaDocumenti";
var CHECKBOXES_CLASS = "selezioneDocumento";


function esegui(action){
	var form=$('#'+FORM_ID);
	var list = form.find('.'+CHECKBOXES_CLASS+':checked');
	if(list.length>0){
		form.attr('action',baseUrl	+'.'+action+'.do');
		form.submit();
	}
	$("#loader").hide();
    $("#loading").hide();
    $("#loading").dialog('close');
}

function checkedAct(bx){
	var form=$('#'+FORM_ID);
	var list = form.find('.'+CHECKBOXES_CLASS);
	
	if(list.length>0){
		for(var i=0; i < list.length; i++) {
			list[i].checked = bx.checked;
		}
	}
}