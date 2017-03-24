/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Flo
 */
public class passerelleTest {
    
    public passerelleTest() {
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
     * Test of open method, of class passerelle.
     */
    @Test
    public void testOpen() {
        System.out.println("open");
        passerelle.open();
    }

    /**
     * Test of close method, of class passerelle.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        passerelle.close();
    }

    /**
     * Test of delete method, of class passerelle.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Object o = null;
        passerelle.delete(o);
    }

    /**
     * Test of save method, of class passerelle.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Object o = null;
        passerelle.save(o);
    }

    /**
     * Test of saveAndId method, of class passerelle.
     */
    @Test
    public void testSaveAndId() {
        System.out.println("saveAndId");
        Object o = null;
        int expResult = 0;
        int result = passerelle.saveAndId(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of count method, of class passerelle.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        String className = "";
        int expResult = 0;
        int result = passerelle.count(className);
        assertEquals(expResult, result);
    }

    /**
     * Test of select method, of class passerelle.
     */
    @Test
    public void testSelect() {
        }

    /**
     * Test of table method, of class passerelle.
     */
    @Test
    public void testTable_Object() {
        System.out.println("table");
        Object o = null;
        List expResult = null;
        List result = passerelle.table(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of table method, of class passerelle.
     */
    @Test
    public void testTable_String() {
        System.out.println("table");
        String className = "";
        List expResult = null;
        List result = passerelle.table(className);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEquipe method, of class passerelle.
     */
    @Test
    public void testIsEquipe() {
        System.out.println("isEquipe");
        int id = 0;
        boolean expResult = false;
        boolean result = passerelle.isEquipe(id);
        assertEquals(expResult, result);
    }
    
}
