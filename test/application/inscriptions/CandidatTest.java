/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import data.hibernate.passerelle;
import java.util.Calendar;
import java.util.HashSet;
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
        passerelle.open();
    }
    
    @AfterClass
    public static void tearDownClass() {
        passerelle.close();
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
        System.out.println("Réussite de la fonction getNom");
        Candidat instance = new Candidat("nom");
        String expResult = "nom";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNom method, of class Candidat.
     */
    @Test
    public void testSetNom() {
        System.out.println("Réussite de la fonction setNom");
        String nom = "newNom";
        Candidat instance = new Candidat("nom");
        instance.setNom(nom);
        assertEquals(nom, instance.getNom());
    }

    /**
     * Test of getEquipe method, of class Candidat.
     */
    @Test
    public void testGetEquipe() {
        System.out.println("Réussite de la fonction getEquipe");
        Candidat instance = new Candidat();
        Set<Candidat> expResult = new HashSet<>(0);
        Set<Equipe> result = instance.getEquipe();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCandidats method, of class Candidat.
     */
    @Test
    public void testGetCandidats() {
        System.out.println("Réussite de la fonction getCandidats");
        Candidat instance = new Candidat();
        Set<Candidat> expResult = new HashSet<>(0);
        Set<Equipe> result = instance.getCandidats();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetition method, of class Candidat.
     */
    @Test
    public void testGetCompetition() {
        System.out.println("Réussite de la fonction getCompetition");
        Candidat instance = new Candidat();
        Set<Competition> expResult = new HashSet<>(0);
        Set<Competition> result = instance.getCompetition();
        assertEquals(expResult, result);
    }

    /**
     * Test of inscription method, of class Candidat.
     */
    @Test
    public void testInscription() {
        System.out.println("Réussite de la fonction inscription");
        Competition competition = new Competition("nom",Calendar.getInstance(),280, false);
        Candidat instance = new Candidat();
        instance.inscription(competition);
        assertTrue(competition.getCandidats().contains(instance));
    }

    /**
     * Test of desinscription method, of class Candidat.
     */
    @Test
    public void testDesinscription() {
        System.out.println("Réussite de la fonction desinscription");
        Competition competition = new Competition();
        Candidat instance = new Candidat();
        instance.inscription(competition);
        instance.desinscription(competition);
        assertFalse(instance.getCompetition().contains(competition));
    }

    /**
     * Test of addEquipe method, of class Candidat.
     */
    @Test
    public void testAddEquipe() {
        System.out.println("Réussite de la fonction addEquipe");
        Candidat candidat = (Users) new Candidat();
        Equipe instance = (Equipe) new Candidat();
        instance.addEquipe(candidat);
        assertTrue(candidat.getCandidats().contains(instance));
    }

    /**
     * Test of removeEquipe method, of class Candidat.
     */
    @Test
    public void testRemoveEquipe() {
        System.out.println("Réussite de la fonction removeEquipe");
        Candidat candidat = new Candidat();
        Candidat instance = new Candidat();
        instance.addEquipe(candidat);
        instance.removeEquipe(candidat);
        assertFalse(instance.getCandidats().contains(candidat));
    }

    /**
     * Test of isEquipe method, of class Candidat.
     */
    @Test
    public void testIsEquipe() {
        System.out.println("Réussite de la fonction isEquipe");
        Candidat instance = new Candidat();
        boolean expResult = false;
        boolean result = instance.isEquipe();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class Candidat.
     */
    @Test
    public void testRemove() {
        System.out.println("Réussite de la fonction remove");
        Candidat instance = new Candidat();
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
