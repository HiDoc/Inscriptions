/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

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
public class CandidatTest {
    
    public CandidatTest() {
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
     * Test of getNom method, of class Candidat.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Candidat instance = new Candidat();
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNom method, of class Candidat.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "";
        Candidat instance = new Candidat();
        instance.setNom(nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquipe method, of class Candidat.
     */
    @Test
    public void testGetEquipe() {
        System.out.println("getEquipe");
        Candidat instance = new Candidat();
        Set<Candidat> expResult = null;
        Set<Candidat> result = instance.getEquipe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetition method, of class Candidat.
     */
    @Test
    public void testGetCompetition() {
        System.out.println("getCompetition");
        Candidat instance = new Candidat();
        Set<application.inscriptions.Competition> expResult = null;
        Set<application.inscriptions.Competition> result = instance.getCompetition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inscription method, of class Candidat.
     */
    @Test
    public void testInscription() {
        System.out.println("inscription");
        Competition competition = null;
        Candidat instance = new Candidat();
        instance.inscription(competition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desinscription method, of class Candidat.
     */
    @Test
    public void testDesinscription() {
        System.out.println("desinscription");
        Competition competition = null;
        Candidat instance = new Candidat();
        instance.desinscription(competition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEquipe method, of class Candidat.
     */
    @Test
    public void testAddEquipe() {
        System.out.println("addEquipe");
        Candidat instance = new Candidat();
        instance.addEquipe();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Candidat.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Candidat instance = new Candidat();
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
