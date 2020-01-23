package com.alpenite.tea.richiestabackoffice.ws;

import java.util.Hashtable;

public class DropDownDatas {

	public static Hashtable<String, String> fistLevel = new Hashtable<String, String>();

	// not sctrictly needed if i use reflection ... but reflection is inefficent
	public static Hashtable<String, Hashtable<String, String>> secondLevel = new Hashtable<String, Hashtable<String, String>>();

	public static Hashtable<String, String> CA_P_1 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_2 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_3 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_4 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_5 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_6 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_7 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_8 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_9 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_10 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_P_11 = new Hashtable<String, String>();

	public static Hashtable<String, String> CA_C_1 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_2 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_3 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_4 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_5 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_6 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_7 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_8 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_9 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_10 = new Hashtable<String, String>();
	public static Hashtable<String, String> CA_C_11 = new Hashtable<String, String>();

	static {
		fistLevel.put("1", "ACQUA");
		fistLevel.put("2", "POZZO");
		fistLevel.put("3", "ENERGIA ELETTRICA");
		fistLevel.put("4", "GAS");
		fistLevel.put("5", "TLR");
		fistLevel.put("6", "TIA");
		fistLevel.put("7", "GESTIONE VERDE");
		fistLevel.put("8", "ILLUMINAZIONE PUBBLICA");
		fistLevel.put("9", "ILLUMINAZIONE VOTIVA");
		fistLevel.put("10", "MANUTENZIONE STRADE");
		fistLevel.put("11", "RACCOLTE");

		secondLevel.put("CA_C_1", CA_C_1);
		secondLevel.put("CA_C_2", CA_C_2);
		secondLevel.put("CA_C_3", CA_C_3);
		secondLevel.put("CA_C_4", CA_C_4);
		secondLevel.put("CA_C_5", CA_C_5);
		secondLevel.put("CA_C_6", CA_C_6);
		secondLevel.put("CA_C_7", CA_C_7);
		secondLevel.put("CA_C_8", CA_C_8);
		secondLevel.put("CA_C_9", CA_C_9);
		secondLevel.put("CA_C_10", CA_C_10);
		secondLevel.put("CA_C_11", CA_C_11);

		secondLevel.put("CA_P_1", CA_P_1);
		secondLevel.put("CA_P_2", CA_P_2);
		secondLevel.put("CA_P_3", CA_P_3);
		secondLevel.put("CA_P_4", CA_P_4);
		secondLevel.put("CA_P_5", CA_P_5);
		secondLevel.put("CA_P_6", CA_P_6);
		secondLevel.put("CA_P_7", CA_P_7);
		secondLevel.put("CA_P_8", CA_P_8);
		secondLevel.put("CA_P_9", CA_P_9);
		secondLevel.put("CA_P_10", CA_P_10);
		secondLevel.put("CA_P_11", CA_P_11);

		// prospect

		CA_P_1.put("CA_P_1_1", "Preventivo allacciamento");
		CA_P_1.put("CA_P_1_2", "Suggerimenti e segnalazioni");
		CA_P_1.put("CA_P_1_3", "Altro (descrivere brevemente nelle note)");

		CA_P_2.put("CA_P_2_1", "Suggerimenti e segnalazioni");
		CA_P_2.put("CA_P_2_2", "Altro (descrivere brevemente nelle note)");

		CA_P_3.put("CA_P_3_1", "Richiesta offerta commerciale");
		CA_P_3.put("CA_P_3_2", " Preventivo allacciamento");
		CA_P_3.put("CA_P_3_3", "Suggerimenti e segnalazioni");
		CA_P_3.put("CA_P_3_4", " Altro (descrivere brevemente nelle note) ");

		CA_P_4.put("CA_P_4_1", "Richiesta offerta commerciale");
		CA_P_4.put("CA_P_4_2", "Suggerimenti e segnalazioni");
		CA_P_4.put("CA_P_4_3", "Altro (descrivere brevemente nelle note) ");

		CA_P_5.put("CA_P_5_1", "Preventivo allacciamento");
		CA_P_5.put("CA_P_5_2", "Suggerimenti e segnalazioni");
		CA_P_5.put("CA_P_5_3", "Altro (descrivere brevemente nelle note) ");

		CA_P_6.put("CA_P_6_1", "Suggerimenti e segnalazioni");
		CA_P_6.put("CA_P_6_2", " Altro (descrivere brevemente nelle note) ");

		CA_P_7.put("CA_P_7_1", "Alberature Aree Pubbliche");
		CA_P_7.put("CA_P_7_2", "Arredi e Attrezzature");
		CA_P_7.put("CA_P_7_3", "Derattizzazione Aree Pubbliche");
		CA_P_7.put("CA_P_7_4", "Diserbo Cittadino Aree Pubbliche");
		CA_P_7.put("CA_P_7_5", "Disinfestazione");
		CA_P_7.put("CA_P_7_6", "Fontanelle ed Idranti in Aree Verdi");
		CA_P_7.put("CA_P_7_7", "Manutenzione Aree Verdi");
		CA_P_7.put("CA_P_7_8", " Monumenti in Aree Verdi");
		CA_P_7.put("CA_P_7_9", "Viabilità in Aree Verdi");
		CA_P_7.put("CA_P_7_10", "Suggerimenti e Segnalazioni");
		CA_P_7.put("CA_P_7_11", "Altro (Descrivere Brevemente nelle Note)");

		CA_P_8.put("CA_P_8_1", "Danneggiamento Quadro Elettronico");
		CA_P_8.put("CA_P_8_2", "Illuminazione Pubblica Spenta");
		CA_P_8.put("CA_P_8_3", "Malfunzionamenti Punti Luce");
		CA_P_8.put("CA_P_8_4", "Palo Luce Divelto");
		CA_P_8.put("CA_P_8_5", "Suggerimenti e Segnalazioni");
		CA_P_8.put("CA_P_8_6", "Altro (Descrivere Brevemente nelle Note)  ");
		
		CA_P_9.put("CA_P_9_1", "Suggerimenti e segnalazioni");
		CA_P_9.put("CA_P_9_2", "Altro (descrivere brevemente nelle note) ");
		
		CA_P_10.put("CA_P_10_1", "Acciottolato Mosso");
		CA_P_10.put("CA_P_10_2", "Buche su Sede Stradale");
		CA_P_10.put("CA_P_10_3", "Buche sul Marciapiede");
		CA_P_10.put("CA_P_10_4", "Caduta Calcinacci/Tegole");
		CA_P_10.put("CA_P_10_5", "Cedimento Sede Stradale");
		CA_P_10.put("CA_P_10_6", "Chiusino da Sistemare");
		CA_P_10.put("CA_P_10_7", "Dissuasore/Parapetto da Ripristinare");
		CA_P_10.put("CA_P_10_8", "Impianto Semaforico");
		CA_P_10.put("CA_P_10_9", "Malfunzionamento Bagno Pubblico");
		CA_P_10.put("CA_P_10_10", "Segnaletica Orizzontale");
		CA_P_10.put("CA_P_10_11", "Segnaletica Verticale");
		CA_P_10.put("CA_P_10_12", "Sversamento Materiale su Sede");
		CA_P_10.put("CA_P_10_13", "Suggerimenti e Segnalazioni");
		CA_P_10.put("CA_P_10_14", "Altro (Descrivere Brevemente nelle Note) ");

		CA_P_11.put("CA_P_11_1", "Richiesta Offerta Commerciale");
		CA_P_11.put("CA_P_11_2", "Contenitori Danneggiati");
		CA_P_11.put("CA_P_11_3", "Contenitori Maleodoranti");
		CA_P_11.put("CA_P_11_4", "Mancata Raccolta/Svuotamento");
		CA_P_11.put("CA_P_11_5", "Richiesta Contenitori");
		CA_P_11.put("CA_P_11_6", "Rifiuti Abbandonati");
		CA_P_11.put("CA_P_11_7", "Spazzamento");
		CA_P_11.put("CA_P_11_8", "Info Raccolta Differenziata");
		CA_P_11.put("CA_P_11_9", "Info Conferimenti in Piazzola");
		CA_P_11.put("CA_P_11_10", "Richiesta Svuotamento");
		CA_P_11.put("CA_P_11_11", "Prenotazione Ingombranti (Mantova)");
		CA_P_11.put("CA_P_11_12", "Suggerimenti e Segnalazioni");
		CA_P_11.put("CA_P_11_13", "Altro (Descrivere Brevemente nelle Note) ");

		// cliente

		CA_C_1.put("CA_C_1_1", "Preventivo allacciamento");
		CA_C_1.put("CA_C_1_2", "Chiarimenti su Fattura");
		CA_C_1.put("CA_C_1_3", "Chiarimenti su Letture");
		CA_C_1.put("CA_C_1_4", "Modalità di Fatturazione");
		CA_C_1.put("CA_C_1_5", "Richiesta copia Fattura");
		CA_C_1.put("CA_C_1_6", "Richiesta Verifica/Rimborso");
		CA_C_1.put("CA_C_1_7", "Verifica Richiesta di Domiciliazione Bancaria");
		CA_C_1.put("CA_C_1_8", "Richiesta Riduzioni");
		CA_C_1.put("CA_C_1_9", "Suggerimenti e segnalazioni");
		CA_C_1.put("CA_C_1_10", "Altro (descrivere brevemente nelle note)");

		CA_C_2.put("CA_C_2_1", "Chiarimenti su Fattura");
		CA_C_2.put("CA_C_2_2", "Chiarimenti su Letture");
		CA_C_2.put("CA_C_2_3", "Modalità di Fatturazione");
		CA_C_2.put("CA_C_2_4", "Richiesta copia Fattura");
		CA_C_2.put("CA_C_2_6", "Verifica Richiesta di Domiciliazione Bancaria");
		CA_C_2.put("CA_C_2_7", "Richiesta Riduzioni");
		CA_C_2.put("CA_C_2_8", "Suggerimenti e Segnalazioni");
		CA_C_2.put("CA_C_2_9", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_3.put("CA_C_3_1", "Richiesta offerta commerciale");
		CA_C_3.put("CA_C_3_2", "Preventivo allacciamento");
		CA_C_3.put("CA_C_3_3", "Chiarimenti su Fattura");
		CA_C_3.put("CA_C_3_4", "Chiarimenti su Letture");
		CA_C_3.put("CA_C_3_5", "Modalità di Fatturazione");
		CA_C_3.put("CA_C_3_6", "Richiesta copia Fattura");
		CA_C_3.put("CA_C_3_7", "Richiesta Verifica/Rimborso");
		CA_C_3.put("CA_C_3_8", "Verifica Richiesta di Domiciliazione Bancaria");
		CA_C_3.put("CA_C_3_9", "Richiesta Riduzioni");
		CA_C_3.put("CA_C_3_10", "Suggerimenti e Segnalazioni");
		CA_C_3.put("CA_C_3_11", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_4.put("CA_C_4_1", "Richiesta offerta commerciale");
		CA_C_4.put("CA_C_4_2", "Chiarimenti su Fattura");
		CA_C_4.put("CA_C_4_3", "Chiarimenti su Letture");
		CA_C_4.put("CA_C_4_4", "Modalità di Fatturazione");
		CA_C_4.put("CA_C_4_5", "Richiesta copia Fattura");
		CA_C_4.put("CA_C_4_6", "Richiesta Verifica/Rimborso");
		CA_C_4.put("CA_C_4_7", "Verifica Richiesta di Domiciliazione Bancaria");
		CA_C_4.put("CA_C_4_8", "Richiesta Riduzioni");
		CA_C_4.put("CA_C_4_9", "Suggerimenti e Segnalazioni");
		CA_C_4.put("CA_C_4_10", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_5.put("CA_C_5_1", "Preventivo Allacciamento");
		CA_C_5.put("CA_C_5_2", "Chiarimenti su Fattura");
		CA_C_5.put("CA_C_5_3", "Chiarimenti su Letture");
		CA_C_5.put("CA_C_5_4", "Modalità di Fatturazione");
		CA_C_5.put("CA_C_5_5", "Richiesta copia Fattura");
		CA_C_5.put("CA_C_5_6", "Richiesta Verifica/Rimborso");
		CA_C_5.put("CA_C_5_7", "Verifica Richiesta di Domiciliazione Bancaria");
		CA_C_5.put("CA_C_5_8", "Richiesta Riduzioni");
		CA_C_5.put("CA_C_5_9", "Suggerimenti e Segnalazioni");
		CA_C_5.put("CA_C_5_10", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_6.put("CA_C_6_1", "Chiarimenti su Fattura");
		CA_C_6.put("CA_C_6_2", "Chiarimenti su Letture");
		CA_C_6.put("CA_C_6_3", "Modalità di Fatturazione");
		CA_C_6.put("CA_C_6_4", "Richiesta copia Fattura");
		CA_C_6.put("CA_C_6_5", "Richiesta Verifica/Rimborso");
		CA_C_6.put("CA_C_6_6", "Verifica Richiesta di Domiciliazione Bancaria");
		CA_C_6.put("CA_C_6_7", "Richiesta Compostiera");
		CA_C_6.put("CA_C_6_8", "Sopralluogo TIA");
		CA_C_6.put("CA_C_6_9", "Richiesta Riduzioni");
		CA_C_6.put("CA_C_6_10", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_7.put("CA_C_7_1", "Alberature Aree Pubbliche");
		CA_C_7.put("CA_C_7_2", "Arredi e Attrezzature");
		CA_C_7.put("CA_C_7_3", "Derattizzazione Aree Pubbliche");
		CA_C_7.put("CA_C_7_4", "Diserbo Cittadino Aree Pubbliche");
		CA_C_7.put("CA_C_7_5", " Disinfestazione");
		CA_C_7.put("CA_C_7_6", "Fontanelle ed Idranti in Aree Verdi");
		CA_C_7.put("CA_C_7_7", "Manutenzione Aree Verdi");
		CA_C_7.put("CA_C_7_8", "Monumenti in Aree Verdi");
		CA_C_7.put("CA_C_7_9", "Viabilità in Aree Verdi");
		CA_C_7.put("CA_C_7_10", "Suggerimenti e Segnalazioni");
		CA_C_7.put("CA_C_7_11", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_8.put("CA_C_8_1", "Danneggiamento Quadro Elettronico");
		CA_C_8.put("CA_C_8_2", "Illuminazione Pubblica Spenta");
		CA_C_8.put("CA_C_8_3", "Malfunzionamenti Punti Luce");
		CA_C_8.put("CA_C_8_4", "Palo Luce Divelto");
		CA_C_8.put("CA_C_8_5", "Suggerimenti e Segnalazioni");
		CA_C_8.put("CA_C_8_6", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_9.put("CA_C_9_1", "Suggerimenti e Segnalazioni");
		CA_C_9.put("CA_C_9_2", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_10.put("CA_C_10_1", "Acciottolato Mosso");
		CA_C_10.put("CA_C_10_2", "Buche su Sede Stradale");
		CA_C_10.put("CA_C_10_3", "Buche sul Marciapiede");
		CA_C_10.put("CA_C_10_4", "Caduta Calcinacci/Tegole");
		CA_C_10.put("CA_C_10_5", "Cedimento Sede Stradale");
		CA_C_10.put("CA_C_10_6", "Chiusino da Sistemare");
		CA_C_10.put("CA_C_10_7", "Dissuasore/Parapetto da Ripristinare");
		CA_C_10.put("CA_C_10_8", "Impianto Semaforico");
		CA_C_10.put("CA_C_10_9", "Malfunzionamento Bagno Pubblico");
		CA_C_10.put("CA_C_10_10", "Segnaletica Orizzontale");
		CA_C_10.put("CA_C_10_11", "Segnaletica Verticale");
		CA_C_10.put("CA_C_10_12", "Sversamento Materiale su Sede");
		CA_C_10.put("CA_C_10_13", "Suggerimenti e Segnalazioni");
		CA_C_10.put("CA_C_10_14", "Altro (Descrivere Brevemente nelle Note) ");

		CA_C_11.put("CA_C_11_1", "Richiesta Offerta Commerciale");
		CA_C_11.put("CA_C_11_2", "Contenitori Danneggiati");
		CA_C_11.put("CA_C_11_3", "Contenitori Maleodoranti");
		CA_C_11.put("CA_C_11_4", "Mancata Raccolta/Svuotamento");
		CA_C_11.put("CA_C_11_5", "Richiesta Contenitori");
		CA_C_11.put("CA_C_11_6", "Rifiuti Abbandonati");
		CA_C_11.put("CA_C_11_7", "Spazzamento");
		CA_C_11.put("CA_C_11_8", "Info Raccolta Differenziata");
		CA_C_11.put("CA_C_11_9", "Info Conferimenti in Piazzola");
		CA_C_11.put("CA_C_11_10", "Richiesta Svuotamento");
		CA_C_11.put("CA_C_11_11", "Prenotazione Ingombranti (Mantova)");
		CA_C_11.put("CA_C_11_12", "Suggerimenti e Segnalazioni");
		CA_C_11.put("CA_C_11_13", "Altro (Descrivere Brevemente nelle Note) ");

	}

}
