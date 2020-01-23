$(function() {
	
    
    $("#moduloInvioFatture").hide();

    // creo il dialogo a partire dal modulo
    var $dialog = $("#moduloInvioFatture").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 680,
        resizable: false
        //closeText: "X"
    });
    
    var $dialog2 = $("#modulofascicolocliente").dialog({
        autoOpen: false,
        modal: true,
        draggable: false,
        width: 960,
        resizable: false
        //closeText: "X"
    });
    
    // visualizza la pagina contenente il modulo da compilare
    $("#bottoneModificaFlag").click(function() {    	
    	$(".ui-dialog-titlebar").show(); 
    	$dialog.dialog('open'); 
    	controllaStatoCheck(); 
        return false;
    });
    // visualizza la pagina contenente il dettaglio fadcicolo cliente
    $("#bottonefascicolocliente").click(function() {    	
    	$(".ui-dialog-titlebar").show(); 
    	$dialog2.dialog('open'); 
    	controllaStatoCheck(); 
        return false;
    });
    //25022015 MarVal flag disable in caso di non ricezione fattura per mail      

    document.getElementById("copiaEmail").addEventListener("click", controllaStatoCheck);  

    function controllaStatoCheck(){
    	if(document.getElementById("copiaEmail").checked){
    		disable();
    	}
    	else{
    		enable();
    	}
    } 

    function enable() {
    		document.getElementById("pdfInEmail").setAttribute('disabled', 'disabled'); 
    		document.getElementById("pdfInEmail").setAttribute("checked", "");// per IE 
    		document.getElementById("pdfInEmail").removeAttribute("checked");// per gli altri 
    		document.getElementById("pdfInEmail").checked = false;
    	} 

    function disable() {
    		document.getElementById("pdfInEmail").removeAttribute('disabled');
    	} 
    //END25022015 MarVal flag disable in caso di non ricezione fattura per mail  

	// 07122016 controlla check sospensione invio cartaceo e email
    document.getElementById("copiaCartacea").addEventListener("click", controllaStatoSospensione);
    document.getElementById("copiaEmail").addEventListener("click", controllaStatoSospensione);


    function controllaStatoSospensione(){
    	if(document.getElementById("copiaCartacea").checked  && !document.getElementById("copiaEmail").checked){
    		 
             
             console.log("sospensione copiaCartacea abilitato e copiaEmail disabilitato");

             var $dialog2 = $("#dialogCheckSospensione").dialog({
                    autoOpen: false,
                    modal: true,
                    draggable: false,
                    width: 680,
                    resizable: false
                    //closeText: "X"
                });
            $( "#dialogCheckSospensione" ).dialog('open');


            
    	}
        /*
        if(document.getElementById("copiaCartacea").checked  && document.getElementById("copiaEmail").checked){
             
             
             console.log("sospensione copiaCartacea abilitato e copiaEmail disabilitato");

             var $dialog2 = $("#dialogCheckSospensione").dialog({
                    autoOpen: false,
                    modal: true,
                    draggable: false,
                    width: 680,
                    resizable: false
                    //closeText: "X"
                });
            $( "#dialogCheckSospensione" ).dialog('open');


            
        }
    	 
	*/
    	

    }

    $("#okSospensione").click( function()
           {
             console.log('okSospensione clicked');
            document.getElementById("copiaCartacea").setAttribute("checked", "true");// per IE 
            
            document.getElementById("copiaCartacea").checked = true;

            document.getElementById("copiaEmail").setAttribute("checked", "");// per IE 
            
            document.getElementById("copiaEmail").checked = false;
            

             $( "#dialogCheckSospensione" ).dialog('close');
           }
        );  

        $("#noSospensione").click( function()
           {
             console.log('noSospensione clicked');
             $( "#dialogCheckSospensione" ).dialog('close');
             document.getElementById("copiaCartacea").setAttribute("checked", "");// per IE 
            
            document.getElementById("copiaCartacea").checked = false;
           }
        ); 
	
	// end07122016 
});
