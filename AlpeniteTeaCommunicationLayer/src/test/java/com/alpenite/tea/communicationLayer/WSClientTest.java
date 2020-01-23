/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpenite.tea.communicationLayer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class WSClientTest {
    
    public WSClientTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of getClient method, of class WSClient.
//     */
//    @Test
//    public void testGetClient() throws Exception {
//        System.out.println("getClient");
//        WSClient expResult = null;
//        WSClient result = WSClient.getClient();
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aggiungiContoContrattuale method, of class WSClient.
//     */
//    @Test
//    public void testAggiungiContoContrattuale() {
//        System.out.println("aggiungiContoContrattuale");
//        String bp = "";
//        String ccNuovo = "";
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.aggiungiContoContrattuale(bp, ccNuovo);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of comunicaAutolettura method, of class WSClient.
//     */
//    @Test
//    public void testComunicaAutolettura() {
//        System.out.println("comunicaAutolettura");
//        String bp = "";
//        String cc = "";
//        String autolettura = "";
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.comunicaAutolettura(bp, cc, autolettura);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDatiCatastali method, of class WSClient.
//     */
//    @Test
//    public void testGetDatiCatastali() {
//        System.out.println("getDatiCatastali");
//        String bp = "";
//        String contratto = "";
//        String cc = "";
//        WSClient instance = null;
//        DatiCatastali expResult = null;
//        DatiCatastali result = instance.getDatiCatastali(bp, contratto, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDatiCatastali method, of class WSClient.
//     */
//    @Test
//    public void testSetDatiCatastali() {
//        System.out.println("setDatiCatastali");
//        String bp = "";
//        String cc = "";
//        DatiCatastali datiCatastali = null;
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.setDatiCatastali(bp, cc, datiCatastali);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getComuni method, of class WSClient.
//     */
//    @Test
//    public void testGetComuni() {
//        System.out.println("getComuni");
//        String provincia = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getComuni(provincia);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getElencoContratti method, of class WSClient.
//     */
//    @Test
//    public void testGetElencoContratti() {
//        System.out.println("getElencoContratti");
//        String bp = "";
//        List<String> tipoContratto = null;
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getElencoContratti(bp, tipoContratto);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRecapito method, of class WSClient.
//     */
//    @Test
//    public void testGetRecapito() {
//        System.out.println("getRecapito");
//        String bp = "";
//        String cc = "";
//        WSClient instance = null;
//        Recapito expResult = null;
//        Recapito result = instance.getRecapito(bp, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDomiciliazioneBancaria method, of class WSClient.
//     */
//    @Test
//    public void testGetDomiciliazioneBancaria() {
//        System.out.println("getDomiciliazioneBancaria");
//        String bp = "";
//        String cc = "";
//        WSClient instance = null;
//        DomiciliazioneBancaria expResult = null;
//        DomiciliazioneBancaria result = instance.getDomiciliazioneBancaria(bp, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDatiAnagrafici method, of class WSClient.
//     */
//    @Test
//    public void testGetDatiAnagrafici() {
//        System.out.println("getDatiAnagrafici");
//        String bp = "";
//        boolean azienda = false;
//        WSClient instance = null;
//        DatiAnagrafici expResult = null;
//        DatiAnagrafici result = instance.getDatiAnagrafici(bp, azienda);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDatiAnagrafici method, of class WSClient.
//     */
//    @Test
//    public void testSetDatiAnagrafici() {
//        System.out.println("setDatiAnagrafici");
//        String bp = "";
//        String tipoBp = "";
//        DatiAnagrafici datiAnagrafici = null;
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.setDatiAnagrafici(bp, tipoBp, datiAnagrafici);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFlagCartaceoEmail method, of class WSClient.
//     */
//    @Test
//    public void testGetFlagCartaceoEmail() {
//        System.out.println("getFlagCartaceoEmail");
//        String bp = "";
//        String cc = "";
//        WSClient instance = null;
//        String[] expResult = null;
//        String[] result = instance.getFlagCartaceoEmail(bp, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFlagCartaceoEmail method, of class WSClient.
//     */
//    @Test
//    public void testSetFlagCartaceoEmail() {
//        System.out.println("setFlagCartaceoEmail");
//        String bp = "";
//        String cc = "";
//        boolean cartaceo = false;
//        boolean email = false;
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.setFlagCartaceoEmail(bp, cc, cartaceo, email);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of modificaRecapito method, of class WSClient.
//     */
//    @Test
//    public void testModificaRecapito() {
//        System.out.println("modificaRecapito");
//        String bp = "";
//        String cc = "";
//        Recapito r = null;
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.modificaRecapito(bp, cc, r);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getElencoDocumenti method, of class WSClient.
//     */
//    @Test
//    public void testGetElencoDocumenti() {
//        System.out.println("getElencoDocumenti");
//        String bp = "";
//        String cc = "";
//        String tipo = "";
//        String dataDa = "";
//        String dataA = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getElencoDocumenti(bp, cc, tipo, dataDa, dataA);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFileDocumento method, of class WSClient.
//     */
//    @Test
//    public void testGetFileDocumento() {
//        System.out.println("getFileDocumento");
//        String bp = "";
//        String fileId = "";
//        String fileObj = "";
//        WSClient instance = null;
//        File expResult = null;
//        File result = instance.getFileDocumento(bp, fileId, fileObj);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getIndirizzoFornitura method, of class WSClient.
//     */
//    @Test
//    public void testGetIndirizzoFornitura() {
//        System.out.println("getIndirizzoFornitura");
//        String bp = "";
//        String contratto = "";
//        String cc = "";
//        WSClient instance = null;
//        Recapito expResult = null;
//        Recapito result = instance.getIndirizzoFornitura(bp, contratto, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setIndirizzoFornitura method, of class WSClient.
//     */
//    @Test
//    public void testSetIndirizzoFornitura() {
//        System.out.println("setIndirizzoFornitura");
//        String bp = "";
//        String cc = "";
//        String numeroCivico = "";
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.setIndirizzoFornitura(bp, cc, numeroCivico);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDettagliContrattoAcqua method, of class WSClient.
//     */
//    @Test
//    public void testGetDettagliContrattoAcqua() {
//        System.out.println("getDettagliContrattoAcqua");
//        String bp = "";
//        String contratto = "";
//        String cc = "";
//        WSClient instance = null;
//        DettagliContrattoAcqua expResult = null;
//        DettagliContrattoAcqua result = instance.getDettagliContrattoAcqua(bp, contratto, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDettagliContrattoAmbiente method, of class WSClient.
//     */
//    @Test
//    public void testGetDettagliContrattoAmbiente() {
//        System.out.println("getDettagliContrattoAmbiente");
//        String bp = "";
//        String contratto = "";
//        String cc = "";
//        WSClient instance = null;
//        DettagliContrattoAmbiente expResult = null;
//        DettagliContrattoAmbiente result = instance.getDettagliContrattoAmbiente(bp, contratto, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDettagliContrattoGas method, of class WSClient.
//     */
//    @Test
//    public void testGetDettagliContrattoGas() {
//        System.out.println("getDettagliContrattoGas");
//        String bp = "";
//        String contratto = "";
//        String cc = "";
//        WSClient instance = null;
//        DettagliContrattoGas expResult = null;
//        DettagliContrattoGas result = instance.getDettagliContrattoGas(bp, contratto, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDettagliContrattoTeleriscaldamento method, of class WSClient.
//     */
//    @Test
//    public void testGetDettagliContrattoTeleriscaldamento() {
//        System.out.println("getDettagliContrattoTeleriscaldamento");
//        String bp = "";
//        String contratto = "";
//        String cc = "";
//        WSClient instance = null;
//        DettagliContrattoTeleriscaldamento expResult = null;
//        DettagliContrattoTeleriscaldamento result = instance.getDettagliContrattoTeleriscaldamento(bp, contratto, cc);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLetture method, of class WSClient.
//     */
//    @Test
//    public void testGetLetture() {
//        System.out.println("getLetture");
//        String bp = "";
//        String contratto = "";
//        String anno = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getLetture(bp, contratto, anno);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSvuotamenti method, of class WSClient.
//     */
//    @Test
//    public void testGetSvuotamenti() {
//        System.out.println("getSvuotamenti");
//        String bp = "";
//        String contratto = "";
//        String anno = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getSvuotamenti(bp, contratto, anno);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getElencoBidoni method, of class WSClient.
//     */
//    @Test
//    public void testGetElencoBidoni() {
//        System.out.println("getElencoBidoni");
//        String bp = "";
//        String contratto = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getElencoBidoni(bp, contratto);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEmail method, of class WSClient.
//     */
//    @Test
//    public void testAddEmail() {
//        System.out.println("addEmail");
//        String bp = "";
//        String email = "";
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.addEmail(bp, email);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getElencoServiceRequest method, of class WSClient.
//     */
//    @Test
//    public void testGetElencoServiceRequest() {
//        System.out.println("getElencoServiceRequest");
//        String bp = "";
//        String dataDa = "";
//        String dataA = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getElencoServiceRequest(bp, dataDa, dataA);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getElencoCondomini method, of class WSClient.
//     */
//    @Test
//    public void testGetElencoCondomini() {
//        System.out.println("getElencoCondomini");
//        String bp = "";
//        WSClient instance = null;
//        List expResult = null;
//        List result = instance.getElencoCondomini(bp);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aggiungiRimuoviCondomini method, of class WSClient.
//     */
//    @Test
//    public void testAggiungiRimuoviCondomini() {
//        System.out.println("aggiungiRimuoviCondomini");
//        String bp = "";
//        List<Condominio> condomini = null;
//        boolean aggiunta = false;
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.aggiungiRimuoviCondomini(bp, condomini, aggiunta);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cancellaUsername method, of class WSClient.
//     */
//    @Test
//    public void testCancellaUsername() {
//        System.out.println("cancellaUsername");
//        String bp = "";
//        WSClient instance = null;
//        String expResult = "";
//        String result = instance.cancellaUsername(bp);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of creaUsername method, of class WSClient.
//     */
//    @Test
//    public void testCreaUsername_6args() {
//        System.out.println("creaUsername");
//        String cc = "";
//        String username = "";
//        String email = "";
//        String codiceFiscale = "";
//        String partitaIva = "";
//        DatiAnagrafici datiAnagrafici = null;
//        WSClient instance = null;
//        Message expResult = null;
//        Message result = instance.creaUsername(cc, username, email, codiceFiscale, partitaIva, datiAnagrafici);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of controllaProspect method, of class WSClient.
//     */
//    @Test
//    public void testControllaProspect() {
//        System.out.println("controllaProspect");
//        String tipoBP = "";
//        String email = "";
//        String partitaIva = "";
//        WSClient instance = null;
//        Message expResult = null;
//        Message result = instance.controllaProspect(tipoBP, email, partitaIva);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of creaProspect method, of class WSClient.
//     */
//    @Test
//    public void testCreaProspect() {
//        System.out.println("creaProspect");
//        String username = "";
//        String email = "";
//        DatiAnagrafici datiAnagrafici = null;
//        WSClient instance = null;
//        Message expResult = null;
//        Message result = instance.creaProspect(username, email, datiAnagrafici);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of controllaUsername method, of class WSClient.
//     */
//    @Test
//    public void testControllaUsername() {
//        System.out.println("controllaUsername");
//        String tipoBI = "";
//        String cc = "";
//        String email = "";
//        String codiceFiscale = "";
//        String partitaIva = "";
//        WSClient instance = null;
//        Message expResult = null;
//        Message result = instance.controllaUsername(tipoBI, cc, email, codiceFiscale, partitaIva);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of effetuaRichiestaBackoffice method, of class WSClient.
//     */
//    @Test
//    public void testEffetuaRichiestaBackoffice() {
//        System.out.println("effetuaRichiestaBackoffice");
//        String bp = "";
//        boolean cliente = false;
//        String testoRichiesta = "";
//        String categoria1 = "";
//        String categoria2 = "";
//        WSClient instance = null;
//        Message expResult = null;
//        Message result = instance.effetuaRichiestaBackoffice(bp, cliente, testoRichiesta, categoria1, categoria2);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of creaUsername method, of class WSClient.
//     */
//    @Test
//    public void testCreaUsername_String_String() {
//        System.out.println("creaUsername");
//        String bp = "";
//        String username = "";
//        WSClient instance = null;
//        Message expResult = null;
//        Message result = instance.creaUsername(bp, username);
//        assertEquals(expResult, result);
//        // T ODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
