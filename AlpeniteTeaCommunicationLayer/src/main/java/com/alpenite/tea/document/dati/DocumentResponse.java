package com.alpenite.tea.document.dati;

import javax.xml.bind.annotation.XmlElement;

import com.alpenite.tea.sapMessages.dati.Message;


public class DocumentResponse {

	
	    protected byte[] document;
	    protected String extention;
	    protected String mimetyp;
	    protected String nameFile;
	    
	    
	    
		public DocumentResponse(byte[] document, String extention,
				String mimetyp, String nameFile, String eReturn, Message etMess) {
			super();
			this.document = document;
			this.extention = extention;
			this.mimetyp = mimetyp;
			this.nameFile = nameFile;
		}
		public DocumentResponse() {
			super();
		}
		public byte[] getDocument() {
			return document;
		}
		public void setDocument(byte[] document) {
			this.document = document;
		}
		public String getExtention() {
			return extention;
		}
		public void setExtention(String extention) {
			this.extention = extention;
		}
		public String getMimetyp() {
			return mimetyp;
		}
		public void setMimetyp(String mimetyp) {
			this.mimetyp = mimetyp;
		}
		public String getNameFile() {
			return nameFile;
		}
		public void setNameFile(String nameFile) {
			this.nameFile = nameFile;
		}
	
}
