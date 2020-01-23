package org.jahia;

import org.jahia.registries.ServicesRegistry;
import org.jahia.services.usermanager.JahiaGroup;
import org.jahia.services.usermanager.JahiaGroupManagerService;
import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;


public class SbloccoUtente {	  
	
	public static boolean checkMemberTEA(String user, String group, int siteID)
	{
		boolean isMember = false;
		
		JahiaUserManagerService usrManager = ServicesRegistry.getInstance().getJahiaUserManagerService();
	    JahiaUser trovatoUser = usrManager.lookupUser(user);
	        
		JahiaGroupManagerService groupManagerService = ServicesRegistry.getInstance().getJahiaGroupManagerService();   
	        
	    JahiaGroup TeaGroup = groupManagerService.lookupGroup(siteID, group); 
	    if(TeaGroup.isMember(trovatoUser))
	    	isMember = true;
	    
		return isMember;		
	}

        
}