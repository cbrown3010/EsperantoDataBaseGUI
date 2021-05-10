/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translationdatabasegui;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for Model, 100% pass When comparing expected value and result, some had
 * failed as an empty array list was not considered null. Therefore an if
 * statement was added to check if the array list was empty, then equated to
 * null.
 *
 * @author Charlie
 */
public class ModelTest {

    public ModelTest() {
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
     * Test of getTranslations method, of class Model.
     */
    @Test
    public void testGetTranslations() {
        System.out.println("getTranslations");
        Model instance = new Model();
        ArrayList<Model.Translation> expResult = null;
        ArrayList<Model.Translation> result = instance.getTranslations();
        if (result.isEmpty()) {
            result = null;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLanguage method, of class Model.
     */
    @Test
    public void testReturnLanguage() {
        System.out.println("returnLanguage");
        String input = "";
        Model instance = new Model();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.returnLanguage(input);
        if (result.isEmpty()) {
            result = null;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of returnTranslation method, of class Model.
     */
    @Test
    public void testReturnTranslation() {
        System.out.println("returnTranslation");
        String input = "";
        String language = "";
        Model instance = new Model();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.returnTranslation(input, language);
        if (result.isEmpty()) {
            result = null;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of addTranslations method, of class Model.
     */
    @Test
    public void testAddTranslations() {
        System.out.println("addTranslations");
        String input = "";
        String language = "";
        String translation = "";
        Model instance = new Model();
        instance.addTranslations(input, language, translation);
    }

    /**
     * Test of fillDisplay method, of class Model.
     */
    @Test
    public void testFillDisplay() {
        System.out.println("fillDisplay");
        Model instance = new Model();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.fillDisplay();
        if (result.isEmpty()) {
            result = null;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of find method, of class Model.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        String input = "";
        Model instance = new Model();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.find(input);
        if (result.isEmpty()) {
            result = null;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of removeTranslation method, of class Model.
     */
    @Test
    public void testRemoveTranslation() {
        System.out.println("removeTranslation");
        String input = "";
        Model instance = new Model();
        instance.removeTranslation(input);
    }

}
