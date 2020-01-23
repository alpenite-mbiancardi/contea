package com.alpenite.gui.components;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class SelectProvinceTest {
    
    public SelectProvinceTest() {
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

    /**
     * Test of toString method, of class SelectProvince.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SelectProvince instance = null;
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("class", "classe");
        args.put("pippo", "pluto");
        try {
            instance = new SelectProvince("pv", "pvId", args);
            instance.setValoreSelezionato("BL");
        } catch (IOException ex) {
            Logger.getLogger(SelectProvinceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = instance.toString();
        System.out.println(result);
    }
}
