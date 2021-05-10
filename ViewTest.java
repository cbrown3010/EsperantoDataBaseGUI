/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translationdatabasegui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for View, 100% pass
 *
 * @author Charlie
 */
public class ViewTest {

    public ViewTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of menuInitialization method, of class View.
     */
    @Test
    public void testMenuInitialization() {
        System.out.println("menuInitialization");
        View instance = new View();
        instance.menuInitialization();
    }

}
