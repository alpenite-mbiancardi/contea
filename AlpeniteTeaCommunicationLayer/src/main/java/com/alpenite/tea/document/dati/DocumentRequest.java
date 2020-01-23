package com.alpenite.tea.document.dati;

import javax.xml.bind.annotation.XmlElement;

import com.alpenite.tea.sapMessages.dati.Message;

public class DocumentRequest {

	
	    private String businessPartner;
	    private String accountContractual;
	    private String isRevoke;
	    
	    
		public DocumentRequest(String businessPartner,
				String accountContractual, String isRevoke) {
			super();
			this.businessPartner = businessPartner;
			this.accountContractual = accountContractual;
			this.isRevoke = isRevoke;
		}
		
		public String getBusinessPartner() {
			return businessPartner;
		}
		public void setBusinessPartner(String businessPartner) {
			this.businessPartner = businessPartner;
		}
		public String getAccountContractual() {
			return accountContractual;
		}
		public void setAccountContractual(String accountContractual) {
			this.accountContractual = accountContractual;
		}
		public String getIsRevoke() {
			return isRevoke;
		}
		public void setIsRevoke(String isRevoke) {
			this.isRevoke = isRevoke;
		}
		
		    
		    
		    
			
}
