var FORM_ID          = "formDocumenti";
var CHECKBOXES_CLASS = "selezioneCls";

function checkedAct(bx){
	var form=$('#'+FORM_ID);
	var list = form.find('.'+CHECKBOXES_CLASS);
	
	if(list.length>0){
		for(var i=0; i < list.length; i++) {
			list[i].checked = bx.checked;
		}
	}
}