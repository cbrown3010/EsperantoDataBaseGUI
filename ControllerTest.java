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
 * Test for controller, 100% pass DBsetup.establishConnections() was required to
 * test successfully
 *
 * @author Charlie
 */
public class ControllerTest {

    public ControllerTest() {
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
     * Test of SaveTranslations method, of class Controller.
     */
    @Test
    public void testSaveTranslations() {
        DBsetup.establishConnection();
        System.out.println("SaveTranslations");
        Controller instance = new Controller();
        instance.SaveTranslations();
    }

    /**
     * Test of LoadTranslation method, of class Controller.
     */
    @Test
    public void testLoadTranslation() {
        DBsetup.establishConnection();
        System.out.println("LoadTranslation");
        Controller instance = new Controller();
        instance.LoadTranslation();
    }

}
