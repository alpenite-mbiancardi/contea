package com.alpenite.gui.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author corrado
 *
 */
public class MultiSelectComuniTest {
	public MultiSelectComuniTest(){}
	
	@BeforeClass
	public static void setUpClass(){}
	
	@AfterClass
	public static void tearDownClass(){}
	
	@Before
	public void setUp(){}
	
	@After
	public void tearDown(){}
	
    /**
     * Test of toString method, of class SelectProvince.
     */
	@Test
	public void testToString(){
		System.out.println("toString");
		MultiSelectComuni instance = null;
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("class", "classe");
        args.put("pippo", "pluto");
        List<String> valoriSelezionati = new ArrayList<String>();
        valoriSelezionati.add("BL");
        valoriSelezionati.add("BN");
        valoriSelezionati.add("BR");
        try{
        	instance = new MultiSelectComuni("cm","cmId",args);
        	instance.setValoriSelezionati(valoriSelezionati);
        }catch(Exception ex){
            Logger.getLogger(MultiSelectComuniTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = instance.toString();
        System.out.println(result);
	}
	
}
