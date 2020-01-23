$(function() {
     
		$.datepicker.regional['it'] = {
			renderer: $.ui.datepicker.defaultRenderer,
			monthNames: ['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno',
				'Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'],
			monthNamesShort: ['Gen','Feb','Mar','Apr','Mag','Giu',
				'Lug','Ago','Set','Ott','Nov','Dic'],
			dayNames: ['Domenica','Luned&#236','Marted&#236','Mercoled&#236','Gioved&#236','Venerd&#236','Sabato'],
			dayNamesShort: ['Dom','Lun','Mar','Mer','Gio','Ven','Sab'],
			dayNamesMin: ['Do','Lu','Ma','Me','Gi','Ve','Sa'],
			dateFormat: 'dd/mm/yyyy',
			firstDay: 1,
			prevText: '&#x3c;Prec', prevStatus: '',
			prevJumpText: '&#x3c;&#x3c;', prevJumpStatus: '',
			nextText: 'Succ&#x3e;', nextStatus: '',
			nextJumpText: '&#x3e;&#x3e;', nextJumpStatus: '',
			currentText: 'Oggi', currentStatus: '',
			todayText: 'Oggi', todayStatus: '',
			clearText: '-', clearStatus: '',
			closeText: 'Chiudi', closeStatus: '',
			yearStatus: '', monthStatus: '',
			weekText: 'Sm', weekStatus: '',
			dayStatus: 'DD d MM',
			defaultStatus: '',
			isRTL: false
		};
		
		$.datepicker.regional['en'] = {
                closeText: 'Done',
                prevText: 'Prev',
                nextText: 'Next',
                currentText: 'Today',
                monthNames: ['January','February','March','April','May','June',
                'July','August','September','October','November','December'],
                monthNamesShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
                dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa'],
                weekHeader: 'Wk',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
          };

    // assegno il table sorter alla tabella
    //$("table#tabellaDocumenti").tablesorter();    
    if ($("#dataInizio").length !== 0) {
        $( "#dataInizio" ).datepicker({
            dateFormat: "dd/mm/yy",
            defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            maxDate: "+0d",
            minDate: "01/01/2010",
            dayNamesShort: $.datepicker.regional[ lang_picker ].dayNamesShort,
            dayNames: $.datepicker.regional[ lang_picker ].dayNames,
            monthNamesShort: $.datepicker.regional[ lang_picker ].monthNamesShort,
            monthNames: $.datepicker.regional[ lang_picker ].monthNames,
            dayNamesMin: $.datepicker.regional[ lang_picker ].dayNamesMin,
//            yearRange: "-1:+0",
            numberOfMonths: 1,
            onSelect: function( selectedDate ) {
                $( "#dataInizio" ).datepicker( "option", "minDate", selectedDate );
            }
        });    	
    }
    if ($("#dataFine").length !== 0) {
        $( "#dataFine" ).datepicker({
            dateFormat: "dd/mm/yy",
            defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            maxDate: "+1y",
            minDate: "01/01/2010",
            dayNamesShort: $.datepicker.regional[ lang_picker ].dayNamesShort,
            dayNames: $.datepicker.regional[ lang_picker ].dayNames,
            monthNamesShort: $.datepicker.regional[ lang_picker ].monthNamesShort,
            monthNames: $.datepicker.regional[ lang_picker ].monthNames,
            dayNamesMin: $.datepicker.regional[ lang_picker ].dayNamesMin,
            numberOfMonths: 1,
            onSelect: function( selectedDate ) {
                $( "#dataFine" ).datepicker( "option", "maxDate", selectedDate );
            }
        });    	
    }



    
    // nascondo i messaggi
    $("div#messaggio").hide();
    
    $("#bottoneAnnulla").click(function() {
       $("#dataInizio").val(null);
       $("#dataFine").val(null);
       $("#anno").val(null);
       $("#ricerca").submit();
    });
});
