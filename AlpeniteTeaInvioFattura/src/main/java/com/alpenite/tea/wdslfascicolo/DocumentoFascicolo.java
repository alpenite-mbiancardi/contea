package com.alpenite.tea.wdslfascicolo;





public class DocumentoFascicolo implements Comparable<DocumentoFascicolo>{

    public DocumentoFascicolo(String SezioneIt, String SezioneEn, String DefinizioneIt, String DefinizioneEn, String Estensione, 
    		String Data, String ArcDocId, String Tech1 , String Tech2 , String Tech3, String SapObject) {
    	this.SezioneIt = SezioneIt;
    	this.SezioneEn = SezioneEn;
    	this.DefinizioneIt = DefinizioneIt;
    	this.DefinizioneEn = DefinizioneEn;
    	this.Estensione = Estensione;
    	this.Data = Data;
    	this.ArcDocId = ArcDocId;
    	this.Tech1 = Tech1;
    	this.Tech2= (Tech2);
    	this.Tech3= Tech3; 
    	this.SapObject = SapObject;
    	
    }

    

	private String SezioneIt, SezioneEn , DefinizioneIt , DefinizioneEn , Estensione , Data, ArcDocId, Tech1, Tech2,Tech3, SapObject;
    


    public String getSezioneIt() {
        return SezioneIt;
    }

    public void setSezioneIt(String s) {
        this.SezioneIt = s;
    }
    
    public String getSezioneEn() {
        return SezioneEn;
    }

    public void setSezioneEn(String s) {
        this.SezioneEn = s;
    }
    
    public String getDefinizioneIt() {
        return DefinizioneIt;
    }

    public void setDefinizioneIt(String s) {
        this.DefinizioneIt = s;
    }
    
    public String getDefinizioneEn() {
        return DefinizioneEn;
    }

    public void setDefinizioneEn(String s) {
        this.DefinizioneEn = s;
    }
    
    
    
    
    
    public String getData() {
        return Data;
    }

    public void seData(String s) {
        this.Data = s;
    }
    
    public String getEstensione() {
        return Estensione;
    }

    public void setEstensione(String s) {
        this.Estensione = s;
    }
    
    public String getArcDocId() {
        return ArcDocId;
    }

    public void setArcDocId(String s) {
        this.ArcDocId = s;
    }
    
    public String getTech1() {
        return Tech1;
    }

    public void setTech1(String s) {
        this.Tech1 = s;
    }
    
    public String getTech2() {
        return Tech2;
    }

    public void setTech2(String s) {
        this.Tech2 = s;
    }
    
    public String getTech3() {
        return Tech3;
    }

    public void setTech3(String s) {
        this.Tech3 = s;
    }
    
    public String getSapObject() {
        return SapObject;
    }

    public void setSapObject(String s) {
        this.SapObject = s;
    }
    
	public int compareTo(DocumentoFascicolo o) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	
}
