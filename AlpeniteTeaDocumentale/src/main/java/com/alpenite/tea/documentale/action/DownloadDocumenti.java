package com.alpenite.tea.documentale.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.json.JSONObject;

import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;
import com.alpenite.tea.documentale.utils.DocumentUtils;

public class DownloadDocumenti extends Action{
	private static Logger	LOG			= Logger.getLogger(DownloadDocumenti.class);
	private static String	WRAPPER_IDS	= "id";
	
	@Override
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
		LOG.debug("Entering action DownloadDocumenti");
		HttpServletResponse response = renderContext.getResponse();
		OutputStream output = response.getOutputStream();
		List<String> nodeNamesList = getNodeNames(parameters);
		Map<String, InputStream> documentList = getDocuments(nodeNamesList, session);
		if (documentList.size() > 1) {
			response.addHeader("Content-Disposition", "attachment;filename=\"documents.zip\"");
			response.setContentType("application/zip");
			DocumentUtils.zipFiles(documentList,output);
		} else if (documentList.size() == 1) {
			response.setContentType("application/download");
			String outputName = documentList.keySet().iterator().next();
			response.addHeader("Content-Disposition", "attachment;filename=\""+outputName+"\"");
			IOUtils.copy(documentList.get(outputName), output);
		} else {
			LOG.info("No document found.");
			WSReturnManager.evaluate(new WSReturn<String>(Constants.ERROR_LIST_FILE_NULL, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
		}
		LOG.debug("File length: "+response.getBufferSize()+"b.");
		//output.close();
		
		// Give document
		LOG.debug("Exiting action DownloadDocumenti");
		return new ActionResult(ActionResult.OK.getResultCode(), null, new JSONObject());
	}
	
	/**
	 * Returns the list of the parameters
	 * 
	 * @param parameters
	 * @return
	 */
	private List<String> getNodeNames(Map<String, List<String>> parameters) {
		List<String> result = new ArrayList<String>();
		result = parameters.get(WRAPPER_IDS);
		return result;
	}
	
	/**
	 * Returns the list of the documents into the uuids
	 * 
	 * @param nodeNamesList
	 * @param session
	 * @return
	 */
	private Map<String, InputStream> getDocuments(List<String> nodeNamesList, JCRSessionWrapper session) {
		Map<String, InputStream> result = new HashMap<String, InputStream>();
		List<JCRNodeWrapper> containersWrappers = DocumentUtils.getDocumentWrappers(session, nodeNamesList);
		for (JCRNodeWrapper wrapper : containersWrappers) {
			// wrapper.getFileContent().downloadFile();
			try {
				NodeIterator fileIterator = wrapper.getNodes();
				for (NodeIterator it = fileIterator; it.hasNext();) {
					JCRNodeWrapper fileNode = (JCRNodeWrapper) it.next();
					if (fileNode.isNodeType("jnt:file")) {
						String filename = calculateName(fileNode.getName(),result);
						result.put(filename, fileNode.getFileContent().downloadFile());
					}
				}
			} catch (RepositoryException ex) {
				LOG.error("Error while accessing the file of the documentWrapper " + wrapper.getName(), ex);
			}
		}
		return result;
	}
	/**
	 * Calcola e torna il primo nome libero nella mappa 
	 * @param name
	 * @param map
	 * @return
	 */
	private String calculateName(String fileName, Map<String, InputStream> map) {
		int dotPosition = fileName.lastIndexOf(".");
		String name = "";
		String extension = "";
		if(dotPosition>0){
			name     =fileName.substring(0,dotPosition);
			extension=fileName.substring(dotPosition);
		} else{
			name=fileName;
		}
		String result="";
		if(map.containsKey(name+extension)){
			int i = 1;
			while(map.containsKey(name+"-"+i+extension)){
				i++;
			}
			result+=name+"-"+i+extension;
		} else {
			result=name+extension;
		}
		return result;
	}
}
