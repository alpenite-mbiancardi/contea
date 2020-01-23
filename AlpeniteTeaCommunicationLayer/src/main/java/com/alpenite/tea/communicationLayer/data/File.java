package com.alpenite.tea.communicationLayer.data;

/**
 * Rappresenta un file scaricato dal server
 * 
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class File {

    public File(String nome, String estensione, String mime, byte[] binario, String fileSize) {
        this.nome = nome;
        this.estensione = estensione;
        this.mime = mime;
        this.binario = binario;
        this.fileSize=fileSize;
        
    }
    
    public File(String nome, String estensione, String mime, byte[] binario) {
        this.nome = nome;
        this.estensione = estensione;
        this.mime = mime;
        this.binario = binario;
        
    }
    
    private String nome;
    private String estensione;
    private String mime;
    private byte[] binario;
    protected String fileSize;
    
    
    
    public byte[] getBinario() {
        return binario;
    }

    public void setBinario(byte[] binario) {
        this.binario = binario;
    }

    public String getEstensione() {
        return estensione;
    }

    public void setEstensione(String estensione) {
        this.estensione = estensione;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
    
    
    
}
