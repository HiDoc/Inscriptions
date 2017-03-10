/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import java.util.Calendar;
import java.util.Set;
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
public class CompetitionTest {
    
    public CompetitionTest() {
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
     * Test of getNom method, of class Competition.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Competition instance = new Competition();
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Competition.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Competition instance = new Competition();
        Calendar expResult = null;
        Calendar result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDuree method, of class Competition.
     */
    @Test
    public void testGetDuree() {
        System.out.println("getDuree");
        Competition instance = new Competition();
        int expResult = 0;
        int result = instance.getDuree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateClose method, of class Competition.
     */
    @Test
    public void testGetDateClose() {
        System.out.println("getDateClose");
        Competition instance = new Competition();
        Calendar expResult = null;
        Calendar result = instance.getDateClose();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnEquipe method, of class Competition.
     */
    @Test
    public void testGetEnEquipe() {
        System.out.println("getEnEquipe");
        Competition instance = new Competition();
        boolean expResult = false;
        boolean result = instance.getEnEquipe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNom method, of class Competition.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "";
        Competition instance = new Competition();
        instance.setNom(nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Competition.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Calendar date = null;
        Competition instance = new Competition();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDuree method, of class Competition.
     */
    @Test
    public void testSetDuree() {
        System.out.println("setDuree");
        int duree = 0;
        Competition instance = new Competition();
        instance.setDuree(duree);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnEquipe method, of class Competition.
     */
    @Test
    public void testSetEnEquipe() {
        System.out.println("setEnEquipe");
        boolean enEquipe = false;
        Competition instance = new Competition();
        instance.setEnEquipe(enEquipe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateClose method, of class Competition.
     */
    @Test
    public void testSetDateClose() {
        System.out.println("setDateClose");
        Calendar dateClose = null;
        Competition instance = new Competition();
        instance.setDateClose(dateClose);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCandidats method, of class Competition.
     */
    @Test
    public void testGetCandidats() {
        System.out.println("getCandidats");
        Competition instance = new Competition();
        Set<Candidat> expResult = null;
        Set<Candidat> result = instance.getCandidats();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inscriptionsOuvertes method, of class Competition.
     */
    @Test
    public void testInscriptionsOuvertes() {
        System.out.println("inscriptionsOuvertes");
        Competition instance = new Competition();
        boolean expResult = false;
        boolean result = instance.inscriptionsOuvertes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCandidat method, of class Competition.
     */
    @Test
    public void testAddCandidat() {
        System.out.println("addCandidat");
        Candidat candidat = null;
        Competition instance = new Competition();
        instance.addCandidat(candidat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCandidat method, of class Competition.
     */
    @Test
    public void testRemoveCandidat() {
        System.out.println("removeCandidat");
        Candidat candidat = null;
        Competition instance = new Competition();
        instance.removeCandidat(candidat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Competition.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Competition instance = new Competition();
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Competition.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Competition instance = new Competition();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
