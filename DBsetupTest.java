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
 * Test for DBsetup, 100% pass
 *
 * @author Charlie
 */
public class DBsetupTest {

    public DBsetupTest() {
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
     * Test of establishConnection method, of class DBsetup.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        DBsetup.establishConnection();
    }

    /**
     * Test of createTable method, of class DBsetup.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        DBsetup.establishConnection();
        DBsetup.createTable();
    }

}
