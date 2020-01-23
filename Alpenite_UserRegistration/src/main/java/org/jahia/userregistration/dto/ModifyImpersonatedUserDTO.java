package org.jahia.userregistration.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.userregistration.UserModify;
import org.jahia.userregistration.utils.Constants;

import com.alpenite.gui.components.MultiSelectComuni;

public class ModifyImpersonatedUserDTO{
	private static Logger		LOG					= Logger.getLogger(ModifyImpersonatedUserDTO.class);
	private static String		DEFAULT_WORKSPACE	= "default";
	private MultiSelectComuni	listaComuni			= null;
	private List<String>		listaTipiDocumento	= new ArrayList<String>();
	private String				name				= "";
	private String				email				= "";
	private boolean				showForm			= true;
	
	public ModifyImpersonatedUserDTO(HttpSession session) {
		JCRSessionWrapper rootSession = UserModify.initRootSession(DEFAULT_WORKSPACE);
		try {
			listaComuni = new MultiSelectComuni("comuni", "modImpComuni", null);
		} catch (IOException ex) {
			LOG.error("Error loading the MultiSelectComuni class", ex);
			showForm = false;
		}
		String impersonatedUserName = null;
		JahiaUser impersonatedUser = null;
		if (rootSession == null || showForm == false) {
			showForm = false;
			LOG.info("Impossible to get Root Session.");
		} else if ((impersonatedUserName = (String) session.getAttribute("UTINFSESSION")) == null) {
			showForm = false;
			LOG.info("Impersonated user not found");
		} else if ((impersonatedUser = ServicesRegistry.getInstance().getJahiaUserManagerService().lookupUser(impersonatedUserName)) == null) {
			showForm = false;
			LOG.info("User " + impersonatedUserName + " not found");
		} else {
			name = impersonatedUser.getName();
			email = impersonatedUser.getProperty("j:email");
			String paramComuni = impersonatedUser.getProperty(Constants.COMUNI);
			String paramTipiDocumento = impersonatedUser.getProperty(Constants.TIPI_DOCUMENTO);
			listaTipiDocumento = getParameterList(paramTipiDocumento);
			List<String> listaComuniSelezionati = getParameterList(paramComuni);
			listaComuni.setValoriSelezionati(listaComuniSelezionati);
			LOG.debug(this.toString());
		}
	}
	
	/**
	 * Effettua lo split dei valori e li salva in una lista.
	 * Se il parametro è vuoto o nullo restituisce una lista vuota.
	 * 
	 * @param parameter
	 * @return
	 */
	private List<String> getParameterList(String parameter) {
		List<String> result = new ArrayList<String>();
		if (parameter != null && !parameter.equals("")) {
			String[] array = org.apache.commons.lang.StringUtils.split(parameter, Constants.MULTI_VALUE_SEPARATOR);
			for (String string : array) {
				result.add(string);
			}
		}
		return result;
	}
	
	public MultiSelectComuni getListaComuni() {
		return listaComuni;
	}
	
	public void setListaComuni(MultiSelectComuni listaComuni) {
		this.listaComuni = listaComuni;
	}
	
	public List<String> getListaTipiDocumento() {
		return listaTipiDocumento;
	}
	
	public void setListaTipiDocumento(List<String> listaTipiDocumento) {
		this.listaTipiDocumento = listaTipiDocumento;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isShowForm() {
		return showForm;
	}
	
	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	@Override
	public String toString() {
		return "ModifyImpersonatedUser:[" +
				name + "," +
				email+","+
				listaTipiDocumento+","+
				listaComuni+
				"]";
	}
}
