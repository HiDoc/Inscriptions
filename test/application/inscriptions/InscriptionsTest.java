/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;
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
public class InscriptionsTest {
    
    public InscriptionsTest() {
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
     * Test of getCompetitions method, of class Inscriptions.
     */
    @Test
    public void testGetCompetitions() {
        System.out.println("getCompetitions");
        Inscriptions instance = null;
        SortedSet<Competition> expResult = null;
        SortedSet<Competition> result = instance.getCompetitions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCandidats method, of class Inscriptions.
     */
    @Test
    public void testGetCandidats() {
        System.out.println("getCandidats");
        Inscriptions instance = null;
        SortedSet<Candidat> expResult = null;
        SortedSet<Candidat> result = instance.getCandidats();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquipes method, of class Inscriptions.
     */
    @Test
    public void testGetEquipes() {
        System.out.println("getEquipes");
        Inscriptions instance = null;
        SortedSet<Candidat> expResult = null;
        SortedSet<Candidat> result = instance.getEquipes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCompetition method, of class Inscriptions.
     */
    @Test
    public void testCreateCompetition() {
        System.out.println("createCompetition");
        String nom = "";
        Calendar dateCloture = null;
        int duree = 0;
        boolean enEquipe = false;
        Inscriptions instance = null;
        instance.createCompetition(nom, dateCloture, duree, enEquipe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCandidat method, of class Inscriptions.
     */
    @Test
    public void testCreateCandidat() {
        System.out.println("createCandidat");
        String nom = "";
        String prenom = "";
        String mail = "";
        int niveau = 0;
        Inscriptions instance = null;
        instance.createCandidat(nom, prenom, mail, niveau);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEquipe method, of class Inscriptions.
     */
    @Test
    public void testCreateEquipe() {
        System.out.println("createEquipe");
        String nom = "";
        Inscriptions instance = null;
        instance.createEquipe(nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Inscriptions.
     */
    @Test
    public void testRemove_Competition() {
        System.out.println("remove");
        Competition competition = null;
        Inscriptions instance = null;
        instance.remove(competition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Inscriptions.
     */
    @Test
    public void testRemove_Candidat() {
        System.out.println("remove");
        Candidat candidat = null;
        Inscriptions instance = null;
        instance.remove(candidat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInscriptions method, of class Inscriptions.
     */
    @Test
    public void testGetInscriptions() {
        System.out.println("getInscriptions");
        Inscriptions expResult = null;
        Inscriptions result = Inscriptions.getInscriptions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reinitialiser method, of class Inscriptions.
     */
    @Test
    public void testReinitialiser() {
        System.out.println("reinitialiser");
        Inscriptions instance = null;
        Inscriptions expResult = null;
        Inscriptions result = instance.reinitialiser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recharger method, of class Inscriptions.
     */
    @Test
    public void testRecharger() {
        System.out.println("recharger");
        Inscriptions instance = null;
        Inscriptions expResult = null;
        Inscriptions result = instance.recharger();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sauvegarder method, of class Inscriptions.
     */
    @Test
    public void testSauvegarder() throws Exception {
        System.out.println("sauvegarder");
        Inscriptions instance = null;
        instance.sauvegarder();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Inscriptions.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Inscriptions instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSort method, of class Inscriptions.
     */
    @Test
    public void testGetSort() {
        System.out.println("getSort");
        ArrayList list = null;
        TreeSet expResult = null;
        TreeSet result = Inscriptions.getSort(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
