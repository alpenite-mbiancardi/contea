function verifyInsertion(){
	if(confirm(window.messaggi.inserireDocumento)){
		if(confirm(window.messaggi.inviareEmail)){
			$('#uploadDocument .sendEmail').val(true);
		} else {
			$('#uploadDocument .sendEmail').val(false);
		}
		$('#uploadDocument').submit();
	} else {
		return false;
	}
}