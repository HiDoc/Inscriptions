/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

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
        System.out.println("setNom");
        String nom = "newName";
        Candidat instance = new Candidat("Name");
        instance.setNom(nom);
        assertEquals("newName", instance.getNom());
    }

    /**
     * Test of getEquipe method, of class Candidat.
     */
    @Test
    public void testGetEquipe() {
        System.out.println("getEquipe");
        Candidat instance = new Candidat();
        Set<Candidat> expResult = new HashSet<>(0);
        Set<Candidat> result = instance.getEquipe();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetition method, of class Candidat.
     */
    @Test
    public void testGetCompetition() {
        System.out.println("getCompetition");
        Candidat instance = new Candidat();
        Set<Competition> expResult = new HashSet<>(0);
        Set<Competition> result = instance.getCompetition();
        assertEquals(expResult, result);
    }
    
}
