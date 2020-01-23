package com.alpenite.tea.datiCatastali.dati;

/**
 * Rappresenta i dati catastali
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class DatiCatastali {

    public DatiCatastali(String comuneAmministrativo, String comuneCatastale, String codiceCatastale, String sezioneUrbana, String particella, String particellaSistemaTavolare, String tipoParticella, String subalterno, String foglio) {
        setComuneAmministrativo(comuneAmministrativo);
        setComuneCatastale(comuneCatastale);
        setCodiceCatastale(codiceCatastale);
        setSezioneUrbana(sezioneUrbana);
        setParticella(particella);
        setParticellaSistemaTavolare(particellaSistemaTavolare);
        setTipoParticella(tipoParticella);
        setSubalterno(subalterno);
        setFoglio(foglio);
    }
    
    private String comuneAmministrativo;
    private String comuneCatastale;
    private String codiceCatastale;
    private String sezioneUrbana;
    private String particella;
    private String particellaSistemaTavolare;
    private String tipoParticella;
    private String subalterno;
    private String foglio;

    /**
     * Verifica la lunghezza della stringa inserita ed eventualmente lancia
     * un'eccezione
     * @param s la stringa da verificare
     * @param length la lunghezza
     * @param msg il messaggio contenuto nell'eccezione
     */
    private void validate(String s, int length, String msg) {
        if (s != null && s.length()>length)
            throw new IllegalArgumentException(msg);
    }
    
    public String getCodiceCatastale() {
        return codiceCatastale;
    }

    public void setCodiceCatastale(String codiceCatastale) {
        this.codiceCatastale = codiceCatastale;
    }

    public String getComuneAmministrativo() {
        return comuneAmministrativo;
    }

    public void setComuneAmministrativo(String comuneAmministrativo) {
        this.comuneAmministrativo = comuneAmministrativo;
    }

    public String getComuneCatastale() {
        return comuneCatastale;
    }

    public void setComuneCatastale(String comuneCatastale) {
        this.comuneCatastale = comuneCatastale;
    }

    public String getParticella() {
        return particella;
    }

    public void setParticella(String particella) {
        validate(particella, 5, "La particella deve essere di 5 caratteri");
        this.particella = particella;
    }

    public String getParticellaSistemaTavolare() {
        return particellaSistemaTavolare;
    }

    public void setParticellaSistemaTavolare(String particellaSistemaTavolare) {
        validate(particellaSistemaTavolare, 10, "La particella sistema tavolare deve essere di 10 caratteri");
        this.particellaSistemaTavolare = particellaSistemaTavolare;
    }

    public String getSezioneUrbana() {
        return sezioneUrbana;
    }

    public void setSezioneUrbana(String sezioneUrbana) {
        validate(sezioneUrbana, 10, "La sezione urbana deve essere di 3 caratteri");
        this.sezioneUrbana = sezioneUrbana;
    }

    public String getSubalterno() {
        return subalterno;
    }

    public void setSubalterno(String subalterno) {
        validate(subalterno, 4, "Il subalterno deve essere di 4 caratteri");
        this.subalterno = subalterno;
    }

    public String getTipoParticella() {
        return tipoParticella;
    }

    public void setTipoParticella(String tipoParticella) {
        validate(tipoParticella, 1, "Il tipo di particella deve essere di 1 carattere");
        this.tipoParticella = tipoParticella;
    }

	public String getFoglio() {
		return foglio;
	}

	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}
    
}
