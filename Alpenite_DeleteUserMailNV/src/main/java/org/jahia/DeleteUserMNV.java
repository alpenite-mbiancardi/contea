package org.jahia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import org.jahia.api.Constants;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.sites.JahiaSitesService;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.utils.LanguageCodeConverters;
import org.slf4j.Logger;

public class DeleteUserMNV {	
	
	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteUserMNV.class);
	private static String DEFAULT_LANGUAGE = "it";	   
	//ab - verifico se l'utente � stato validato in precedenza ed � membro del gruppo inserito in input
	public static boolean checkMemberNV(String user, String group, int siteID) throws PathNotFoundException, RepositoryException
	{
		boolean isMember = false;
		boolean isNotValidato = false;
		boolean isMemberNotValidato = false;		
		
		JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
	    JahiaUser trovatoUser = usrManager.lookupUser(user);
	    
	    //ab - verifica se l'utente � membro del gruppo inserito in input    
		JahiaGroupManagerService groupManagerService = ServicesRegistry.getInstance().getJahiaGroupManagerService(); 
	    JahiaSitesService site = ServicesRegistry.getInstance().getJahiaSitesService();	    
	    
	    JahiaGroup TeaGroup = groupManagerService.lookupGroup(siteID, group);	    
	    if(TeaGroup.isMember(trovatoUser))
	    	isMember = true;	    
	    
	    //ab - check validation	  		
		String checkValidato = 	trovatoUser.getProperty("j:validato");
		if(checkValidato != null)
			logger.info("checkValidato: "+checkValidato);
		else
			logger.info("checkValidato: NULL");
		//ab - verifica se l'utente � stato validato in precedenza
		if(checkValidato != null && checkValidato.equals("false"))
			isNotValidato=true;			
		//ab - se l'utente � stato validato in precedenza ed � membro
		if(isMember && isNotValidato)
		{
			isMemberNotValidato = true;
		}				
	return isMemberNotValidato;		
	}
	
	//ab - lista finale degli utenti  
	public static List<String> getUsernameList(String group, int siteID) throws PathNotFoundException, RepositoryException 
	{       
		
		List<String> userNameListFinal = new ArrayList<String>(); 
  
        JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
        List<String> userList = usrManager.getUsernameList(); 
        
        Iterator<String> iterUserList = userList.iterator();        
        while(iterUserList.hasNext())
        {
	        String currentUser = iterUserList.next(); 
	        //ab - se l'utente � l'utente � stato validato in precedenza ed � membro, allora aggiundi l'utente alla lista finale
	        if(checkMemberNV(currentUser, group, siteID))
	        	userNameListFinal.add(currentUser);            
        }                
	return userNameListFinal;
	}   
}