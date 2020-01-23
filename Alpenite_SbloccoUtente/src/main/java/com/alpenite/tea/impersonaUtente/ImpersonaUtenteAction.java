package com.alpenite.tea.impersonaUtente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;

import com.alpenite.tea.communicationLayer.WSClient;
import com.alpenite.tea.communicationLayer.WSReturnManager;
import com.alpenite.tea.communicationLayer.data.Constants;
import com.alpenite.tea.communicationLayer.data.WSReturn;

public class ImpersonaUtenteAction extends Action {
	
    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
    	
        JahiaUserManagerService userManagerService = ServicesRegistry.getInstance().getJahiaUserManagerService();
    	String username = getParameter(parameters, "username");
    	String group = getParameter(parameters, "group");
    	String groupAmmCond = getParameter(parameters, "groupAmmCond");
        String impersonaUtenteMessaggio = "";
        String currentUser = getParameter(parameters, "currentUser");
        
        //com.alpenite.tea.impersonaUtente.ImpersonaUtenteAction
        //com.alpenite.tea.impersonaUtente.ImpersonaUtenteAction
        
        if ( username == null  || username == "" ) 
        	impersonaUtenteMessaggio = Constants.ERRORE_GENERICO; //"No user specified";
        
        JahiaUser user = userManagerService.lookupUser(username);
        if ( user == null)
        	impersonaUtenteMessaggio = Constants.ERRORE_GENERICO; //"No user found";
        
        if ( group == null)
        	impersonaUtenteMessaggio = Constants.ERRORE_GENERICO; //"group not defined";

        if( user!= null && group != null && !user.isMemberOfGroup(renderContext.getSite().getID(), group))
        	impersonaUtenteMessaggio = Constants.ERRORE_GENERICO;
        
        if (impersonaUtenteMessaggio != "") {
        	WSReturnManager.evaluate(new WSReturn<String>(impersonaUtenteMessaggio, this.getClass().getName(), "return" , null), req.getSession());
        	return new ActionResult(HttpServletResponse.SC_ACCEPTED,(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
        }
        
        req.getSession().removeAttribute("UTINFSESSION");
        req.getSession().removeAttribute("BPCSESSION");
        req.getSession().removeAttribute("BPUSESSION");
        WSClient.getClient(req.getSession()).setBpUtenza(null);
        WSClient.getClient(req.getSession()).setBpCliente(null);
        
        // add bputenza
        WSClient.getClient(req.getSession()).setBpUtenza(user.getProperty("j:bp"));
        req.getSession().setAttribute("BPUSESSION", user.getProperty("j:bp"));
        // add bpcliente se non è amministratore di condominio
        if(!user.isMemberOfGroup(renderContext.getSite().getID(), groupAmmCond)){
        	WSClient.getClient(req.getSession()).setBpCliente(user.getProperty("j:bp"));
        	req.getSession().setAttribute("BPCSESSION", user.getProperty("j:bp"));
        }
        // add tipoBp
    	userManagerService.lookupUser(currentUser).setProperty("j:tipoBP", user.getProperty("j:tipoBP"));
        
    	//String testoHelpDesk = username;
        req.getSession().setAttribute("UTINFSESSION", user.getUsername());
        
        WSReturnManager.evaluate(new WSReturn<String>(Constants.SUCCESS, this.getClass().getName(), "return" , new ArrayList<Object>() ), req.getSession());
        
        return new ActionResult(ActionResult.OK.getResultCode(),(session.getNodeByIdentifier(parameters.get("userredirectpage").get(0))).getPath());
    }    
}


