package com.alpenite.tea.documentale.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.usermanager.JahiaUser;

import com.alpenite.gui.components.MultiSelectComuni;

public class VisualizzazioneDocumentoFiltri{
	private static Logger		LOG					= Logger.getLogger(VisualizzazioneDocumentoFiltri.class);
	private Map<String, String>	mappaComuni			= new HashMap<String, String>();
	private List<String>		listaTipiDocumento	= new ArrayList<String>();
	private String dataInizio;
	private String dataFine;
	private String dataInizioDb;
	private String dataFineDb;
	
	public VisualizzazioneDocumentoFiltri() {
		JahiaUser user = JCRSessionFactory.getInstance().getCurrentUser();
		MultiSelectComuni multiSelectComuni = null;
		
		try {
			multiSelectComuni = new MultiSelectComuni("comuni", "modImpComuni", null);
		} catch (IOException ex) {
			LOG.error("Error loading the multiSelectComuni", ex);
		}
		if (multiSelectComuni != null) {
			String[] arrayComuniDisponibili = expandList(user.getProperty("j:comuniDocumentale"));
			if(arrayComuniDisponibili!=null&&arrayComuniDisponibili.length!=0){
				for (String comune : arrayComuniDisponibili) {
					mappaComuni.put(comune, multiSelectComuni.getMappaValori().get(comune));
				}
			}
		}
		String[] arrayTipiDocumento = expandList(user.getProperty("j:listaTipiDocumentoDocumentale"));
		if(arrayTipiDocumento!=null&&arrayTipiDocumento.length!=0){
			for (String tipoDocumento : arrayTipiDocumento) {
				listaTipiDocumento.add(tipoDocumento);
			}
		}
		
		
		LOG.debug("Document Types List = "+listaTipiDocumento);
		LOG.debug("Municipalities List"+mappaComuni);
	}
	
	public Map<String, String> getMappaComuni() {
		return mappaComuni;
	}

	public void setMappaComuni(Map<String, String> mappaComuni) {
		this.mappaComuni = mappaComuni;
	}

	public List<String> getListaTipiDocumento() {
		return listaTipiDocumento;
	}

	public void setListaTipiDocumento(List<String> listaTipiDocumento) {
		this.listaTipiDocumento = listaTipiDocumento;
	}

	private String[] expandList(String property) {
		String[] result = new String[0];
		result = org.apache.commons.lang.StringUtils.split(property, ",");
		return result;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	
	
	public String getDataInizioDb(){
		if (dataInizio==null||dataInizio.isEmpty()||dataInizio.equals("null")) return "";
        String[] e = dataInizio.split("/");
        return e[2]+"-"+e[1]+"-"+e[0];
	}
	
	public String getDataFineDb(){
		if (dataFine==null||dataFine.isEmpty()||dataFine.equals("null")) return "";
        String[] e = dataFine.split("/");
        return e[2]+"-"+e[1]+"-"+e[0];
	}
}
