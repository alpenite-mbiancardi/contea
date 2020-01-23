package com.alpenite.tea.documentale.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.Credentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.RowIterator;

import org.apache.jackrabbit.core.security.JahiaLoginModule;
import org.apache.log4j.Logger;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.query.QueryResultWrapper;
import org.jahia.utils.zip.ZipEntry;
import org.jahia.utils.zip.ZipOutputStream;

public class DocumentUtils{
	private static final Logger	LOG						= Logger.getLogger(DocumentUtils.class);
	private final static String	DOCUMENT_CONTAINER_ID	= "jnt:contenitoreDocumento";
	
	/**
	 * Torna la lista dei contenitori dei documenti
	 * 
	 * @param session sessione JCR
	 * @param documentWrappersIds Lista degli id dei documentWrapper
	 * @return Lista dei documentWrappers
	 */
	public static List<JCRNodeWrapper> getDocumentWrappers(JCRSessionWrapper session, List<String> documentWrappersIds) {
		List<JCRNodeWrapper> result = new ArrayList<JCRNodeWrapper>();
		// Se la lista degli id non e' vuota, li recupero
		if (documentWrappersIds != null && documentWrappersIds.size() > 0) {
			// Costruisco la query
			StringBuilder qc = new StringBuilder();
			qc.append("SELECT * FROM [");
			qc.append(DOCUMENT_CONTAINER_ID);
			qc.append("] WHERE ");
			for (int i = 0; i < documentWrappersIds.size() - 1; i++) {
				qc.append("[id] = '" + documentWrappersIds.get(i) + "' OR");
			}
			qc.append("[id] = '" + documentWrappersIds.get(documentWrappersIds.size() - 1) + "'");
			LOG.debug("Query: \"" + qc.toString() + "\"");
			result = getNodes(session, qc.toString());
		}
		return result;
	}
	
	/**
	 * Effettua una query query sul JCR
	 * 
	 * @param session
	 * @param query
	 * @return
	 */
	public static List<JCRNodeWrapper> getNodes(JCRSessionWrapper session, String query) {
		List<JCRNodeWrapper> result = new ArrayList<JCRNodeWrapper>();
		QueryManager qm = session.getWorkspace().getQueryManager();
		try {
			Query q = qm.createQuery(query, Query.JCR_SQL2);
			QueryResultWrapper qrw = (QueryResultWrapper) q.execute();
			LOG.debug(qrw.getRows().getSize() + " documentsFound.");
			RowIterator i = qrw.getRows();
			while (i.hasNext()) {
				try {
					result.add((JCRNodeWrapper) i.nextRow().getNode());
				} catch (Exception ex) {
					LOG.error("Error loading the list of wrapper", ex);
				}
			}
		} catch (Exception ex) {
			LOG.error("Error loading the wrappers.", ex);
		}
		return result;
	}
	
	/**
	 * Takes a map of fileName-{@link InputStream} and the {@link OutputStream},
	 * on which it has to write.
	 * WARNING: Used in an action this method may trigger the response!!
	 * 
	 * @param fileList {@link String}-{@link InputStream} map of the files to
	 *            zip
	 * @param fos {@link OutputStream}of the OutputStream of the zip file
	 */
	public static boolean zipFiles(Map<String, InputStream> fileList, OutputStream fos) {
		byte[] buffer = new byte[1024];
		ZipOutputStream zos = null;
		try {
			
			// FileOutputStream fos = new FileOutputStream(zipName);
			zos = new ZipOutputStream(fos);
			
			LOG.debug("Output to Zip file");
			
			for (String fileName : fileList.keySet()) {
				LOG.debug("File Added : " + fileName);
				ZipEntry ze = new ZipEntry(fileName);
				zos.putNextEntry(ze);
				int tlen = 0;
				int len;
				while ((len = fileList.get(fileName).read(buffer)) > 0) {
					zos.write(buffer, 0, len);
					tlen += len;
				}
				LOG.debug("File " + fileName + " is " + tlen + "b long.");
				zos.closeEntry();
				fileList.get(fileName).close();
			}
			
			// Closing the zos triggers the download.
			// Not closing it creates an unusable zip file.
			zos.close();
			LOG.debug("Zipping Done");
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
		}
		return true;
		// return zos;
	}
	
	/**
	 * Prende la sessione di root per l'utente corrente nel workspace
	 * 
	 * @param workspace
	 * @return
	 */
	public static JCRSessionWrapper initRootSession(String workspace) {
		JCRSessionWrapper rootSession = null;
		if (rootSession == null) {
			Credentials c = JahiaLoginModule.getSystemCredentials();
			try {
				return rootSession = ServicesRegistry.getInstance().getJCRStoreService().getSessionFactory().login(c, workspace);
			} catch (Exception e) {
				LOG.warn("Impossibile inizializzare la sessione di root", e);
			}
		}
		return null;
	}
}
