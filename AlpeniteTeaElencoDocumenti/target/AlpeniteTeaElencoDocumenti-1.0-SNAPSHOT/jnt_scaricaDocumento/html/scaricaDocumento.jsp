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
    String extension = "";
    final String PDF = ".pdf";
    final String ZIP = ".zip";

    // controllo se la chiamata e'stata fatta tramite post (selezione multipla)
    // oppure tramite get (scaricamento del singolo file)
    if (request.getMethod().toLowerCase().equals("get")) {
        f = WSClient.getClient(session).getFileDocumento(
                request.getParameter("n"),
                request.getParameter("t")).getRitorno();
        //extension = PDF; // not necessary
    } // gestisco la creazione del file zippato
    else if (request.getMethod().toLowerCase().equals("post")) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        // per ogni elemento presente nell'array selezione
        for (String value : request.getParameterValues("selezione")) {
            String[] id_obj = value.split("[|]");
            if(WSClient.getClient(session).getFileDocumento(
                    id_obj[0], id_obj[1]).getRitorno()!=null)
            {
            File tmpFile = WSClient.getClient(session).getFileDocumento(
                    id_obj[0], id_obj[1]).getRitorno();
            ZipEntry entry = new ZipEntry(id_obj[2]+".pdf");
            entry.setSize(tmpFile.getBinario().length);
            int count = 0;
            try{
            	zos.putNextEntry(entry);  	
            }catch(ZipException ex){
            	boolean inserted = false;
                while(!inserted){
                	count++;
            		entry = new ZipEntry(id_obj[2]+"("+count+").pdf");
            		entry.setSize(tmpFile.getBinario().length);
            		try{
            			zos.putNextEntry(entry);
            			inserted = true;
            		}catch(ZipException e){
            			// do nothing	
            		}
            		
                }
          	}
            zos.write(tmpFile.getBinario());
            zos.closeEntry();
            }
        }

        zos.close();
        String nome_file_zip = request.getParameter("nomeFileArchivio")!=null?request.getParameter("nomeFileArchivio"):"documenti";
        f = new File(nome_file_zip+".zip", "zip", "application/zip", baos.toByteArray());
        extension = ZIP;
    }

    if (f != null) {
        response.reset();
        response.setContentType(f.getMime());
        response.setHeader("Pragma", "cache");
        response.setHeader("Cache-Control", "private");
        response.setHeader("Content-Transfer-Encoding", "binary");
        String nome_file_download = request.getParameter("name")!=null?request.getParameter("name"):request.getParameter("nomeFileArchivio")!=null?request.getParameter("nomeFileArchivio"):"documenti";
        nome_file_download = nome_file_download+extension;
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nome_file_download + "\"");
        ServletOutputStream pout = response.getOutputStream();
        pout.write(f.getBinario(), 0, f.getBinario().length);
        pout.close();
    }
	
%>