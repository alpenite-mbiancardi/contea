package com.alpenite.tea.sapMessages.dati;

import java.util.ArrayList;
import java.util.List;

import com.alpenite.tea.communicationLayer.ws.crm.ZptStMess;

public class Message {
	private String eReturn;
	private List<String> etMess;
	private String bp;
	private String name;
	private String surname;
	
	public String geteReturn() {
		return eReturn;
	}

	public void seteReturn(String eReturn) {
		this.eReturn = eReturn;
	}

	public String getFirstMsg(){
		if(etMess != null && etMess.size()!= 0)
			return etMess.get(0);
		return "";
	}
	
	public List<String> getEtMess() {
		return etMess;
	}

	public void setEtMess(List<String> etMess) {
		this.etMess = etMess;
	}
	
	public Message(String eReturn, List<ZptStMess> listMessage, String bp, String name, String surname){
		this.eReturn = eReturn;
		this.etMess = new ArrayList<String>();
		if(listMessage != null)
			for(ZptStMess msg : listMessage)
				etMess.add(msg.getMessage());
		this.bp = bp;
		this.name = name;
		this.surname = surname;
	}
	
	public Message(String eReturn, List<ZptStMess> listMessage, String bp){
		this(eReturn, listMessage, bp, null, null);
	}
	
	public Message(String eReturn, List<ZptStMess> listMessage){
		this(eReturn, listMessage, null);
	}
	
	
	public boolean hasMessage(){
		if(etMess != null && etMess.size() != 0)
			return true;
		return false;
	}

	public boolean isAnError(){
		if(!eReturn.equals("00"))
			return true;
		return false;
	}
	
	public boolean hasBp(){
		if(bp != null && !bp.equals(""))
			return true;
		return false;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	
}
