package com.alpenite.tea.domiciliazioneBancaria;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.URLResolver;
import org.jahia.tools.files.FileUpload;
import org.json.JSONObject;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.File;
import com.alpenite.tea.communicationLayer.data.WSReturn;

public class SendFileToSapAction extends Action {

	private int maxSize = 2097152;
	private int maxfile = 3;

	private WSReturn<String> msg;
	private String result;
	private static Logger LOG = Logger.getLogger(SendFileToSapAction.class);

	@Override
	public ActionResult doExecute(HttpServletRequest req,
			RenderContext renderContext,
			org.jahia.services.render.Resource resource,
			JCRSessionWrapper session, Map<String, List<String>> parameters,
			URLResolver urlResolver) throws Exception {
		
		JCRNodeWrapper node= resource.getNode();
		 
		String urlreturn="";
		String sendResult="";
		FileUpload fileUpload = (FileUpload) req
				.getAttribute(FileUpload.FILEUPLOAD_ATTRIBUTE);
		try {
            
			if (node.hasProperty("afterConfirmPage") && node.getProperty("afterConfirmPage") != null) {
				urlreturn =renderContext.getServletPath() +"/"+ urlResolver.getWorkspace()+"/"+urlResolver.getLocale().getLanguage() +node.getProperty("afterConfirmPage").getNode().getPath()+".html";
            }
        } catch (RepositoryException ex) {
        	LOG.error("the properies ");
        	urlreturn= null;
        }
		
		
		
		LOG.info("Check on file in list is present");
		if (fileUpload.getFileItems().isEmpty()) {
			WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_LIST_FILE_NULL, this.getClass().getName(), Constants.ERROR_LIST_FILE_NULL , new ArrayList<String>()), req.getSession());
    		LOG.info("Errore parametri - non ci sono file");
			return new ActionResult(HttpServletResponse.SC_ACCEPTED,null);
		}
		
		String cc = parameters.get("cc").get(0);
		String revoke = parameters.get("isRevoke").get(0);
		List<File> files = new ArrayList<File>();
		File temp;
		for (int i = 0; i < 3; i++) {
			LOG.info("scan the list of files and analyze individually in a file size and extension");
			String n = "fileField" + i;
			DiskFileItem diskFile = fileUpload.getFileItems().get(n);
			if(diskFile!=null){
				if (diskFile != null) {
					LOG.info("file si present and size is not null");
					byte[] bytes = diskFile.get();
					Tika tika = new Tika();
					String mime = tika.detect(diskFile.getInputStream());
					String ext = FilenameUtils.getExtension(diskFile.getName());
					if (!Constants.EXTENSIONS.contains(ext.toUpperCase())) {
						WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_EXENTION_TYPE, this.getClass().getName(), Constants.ERROR_EXENTION_TYPE , new ArrayList<String>()), req.getSession());
			    		LOG.info("Errore parametri - file di estensione non corretta");
			    		return new ActionResult(HttpServletResponse.SC_ACCEPTED,null);
						
					}
					temp = new File(diskFile.getName(), ext, mime, diskFile.get(), ""+diskFile.getSize());
					if (temp != null) {
						if (diskFile.getSize() > 0 && (diskFile.getSize()< maxSize)) {
							files.add(temp);
						}
						else {
							WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_MAX_DIM, this.getClass().getName(), Constants.ERROR_MAX_DIM , new ArrayList<String>()), req.getSession());
				    		LOG.info("Errore parametri - file di dimensione non consentita");
							return new ActionResult(HttpServletResponse.SC_ACCEPTED,null);
						}
					}
				}
			}
		}
	
		if(files.size()==fileUpload.getFileItems().size()){
			sendResult = WSClient.getClient(req.getSession()).sendFilesToSap(files, cc,isRevoke(revoke));
		}
		else {
			WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_MAX_DIM, this.getClass().getName(), Constants.ERROR_MAX_DIM , new ArrayList<String>()), req.getSession());
    		LOG.info("Errore parametri - file di dimensione non consentita");
    		new ActionResult(HttpServletResponse.SC_ACCEPTED,null);
		}
		
		if(sendResult.equals("00")){
			WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS_UPLOAD_FILE, this.getClass().getName(), Constants.SUCCESS_UPLOAD_FILE , new ArrayList<String>()), req.getSession());
			LOG.info("SR creata e file caricato");
		}else{
			WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_GENERIC, this.getClass().getName(), Constants.ERROR_GENERIC , new ArrayList<String>()), req.getSession());
			LOG.info("Errore nel WS");
		}
		return new ActionResult(HttpServletResponse.SC_ACCEPTED,urlreturn,true,null);
	
	}
	/**
	 * Funzione che converte le stringa in un booleano compatibile con sistema
	 * SAP
	 * 
	 * @param rev
	 * @return "" o x
	 */
	private String isRevoke(String rev) {

		if (rev.equalsIgnoreCase("true")) {
			return "X";
		} else
			return "";
	}

}
