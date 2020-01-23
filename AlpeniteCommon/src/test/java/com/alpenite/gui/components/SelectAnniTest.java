/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpenite.gui.components;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Lorenzo Bortolotto <lorenzo@mxlab.net>
 */
public class SelectAnniTest {
    
    public SelectAnniTest() {
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
     * Test of toString method, of class SelectAnni.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SelectAnni instance = null;
        try {
            instance = new SelectAnni("nome", "id", null, 5, 3);
            instance.setValoreSelezionato("2012");
        } catch (IOException ex) {
            Logger.getLogger(SelectAnniTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = instance.toString();
        System.out.println(result);
    }
}
