<%@page import="com.alpenite.tea.document.dati.DocumentResponse"%>
<%@page import="com.alpenite.tea.document.dati.DocumentRequest"%>
<%@page import="java.util.zip.ZipException"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.zip.ZipEntry"%>
<%@page import="java.util.zip.ZipOutputStream"%>
<%@page import="org.jahia.registries.ServicesRegistry"%>
<%@page import="com.alpenite.tea.communicationLayer.data.File"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// TODO verifico che l'utente sia connesso
    if (ServicesRegistry.getInstance().getJCRStoreService().getSessionFactory().getCurrentUser() == null) {
        // TODO: eseguo il redirect alla pagina di login
        response.sendRedirect("/");
        return;
    }

    // il file da scaricare
    File f = null;
    String extension= ".pdf";
    DocumentRequest documentRequest = new DocumentRequest("bp", request.getParameter("cc"),request.getParameter("isRevoke")) ;
    
   		com.alpenite.tea.communicationLayer.data.WSReturn<DocumentResponse> documentResponce = WSClient.getClient(session).getModuleDocument(documentRequest);
   		if(documentResponce!= null){
   		DocumentResponse document= documentResponce.getRitorno();
        String nome_file = request.getParameter("nomeFileArchivio")!=null?request.getParameter("nomeFileArchivio"):"documenti";
         f = new File(document.getNameFile(), document.getExtention() , document.getMimetyp(), document.getDocument());
   		}
   		
    if (f != null) {
        response.reset();
        response.setContentType(f.getMime());
        response.setHeader("Pragma", "cache");
        response.setHeader("Cache-Control", "private");
        response.setHeader("Content-Transfer-Encoding", "binary");
        String nome_file_download = request.getParameter("name")!=null?request.getParameter("name"):request.getParameter("nomeFileArchivio")!=null?request.getParameter("nomeFileArchivio"):"documenti";//TODO Definire nomi file
        nome_file_download = nome_file_download+extension;
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nome_file_download + "\"");
        ServletOutputStream pout = response.getOutputStream();
        pout.write(f.getBinario(), 0, f.getBinario().length);
        pout.close();
    }
	
%>