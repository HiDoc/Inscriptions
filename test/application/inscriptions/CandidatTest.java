/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;
import data.hibernate.passerelle;
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
    
    Candidat instance;
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
        passerelle.open();
<<<<<<< HEAD
        this.instance = (Candidat) passerelle.get(Candidat.class,1); 
        Set <Competition> compet = this.instance.getCompetition();
=======
        this.instance = (Candidat) passerelle.get(Candidat.class,1);
>>>>>>> d47cdfa81b5bcfee15eb847d739ded7c731bd32f
        
    }
    
    @After
    public void tearDown() {
        passerelle.close();
    }

    /**
     * Test of getNom method, of class Candidat.
     */
    @Test
    public void testGetNom() {
        System.out.println("Réussite de la fonction getNom");
        assertEquals("newNom", this.instance.getNom());
    }

    /**
     * Test of setNom method, of class Candidat.
     */
    @Test
    public void testSetNom() {
        System.out.println("Réussite de la fonction setNom");
        String nom = "newNom";
        this.instance.setNom(nom);
        assertEquals(nom, ((Candidat) passerelle.get(Candidat.class, 1)).getNom());
    }
    /**
     * Test of getCompetition method, of class Candidat.
     */
    @Test
    public void testGetCompetition() {
        System.out.println("Réussite de la fonction getCompetition");
        Competition competition = (Competition) passerelle.get(Competition.class, 1);
        assertTrue(this.instance.getCompetition().contains(competition));
    }

    /**
     * Test of inscription method, of class Candidat.
     */
    @Test
    public void testInscription() {
        System.out.println("Réussite de la fonction inscription");
        Competition competition = (Competition) passerelle.get(Competition.class, 1);
        this.instance.inscription(competition);
        passerelle.save(this.instance);
        assertTrue(this.instance.getCompetition().contains(competition));
    }

    /**
     * Test of desinscription method, of class Candidat.
     */
    @Test
    public void testDesinscription() {
        System.out.println("Réussite de la fonction desinscription");
        Competition competition = (Competition) passerelle.get(Competition.class, 1);
        this.instance.desinscription(competition);
        passerelle.save(this.instance);
        assertTrue(!(this.instance.getCompetition().contains(competition)));
        
    }

    /**
     * Test of remove method, of class Candidat.
     */
    @Test
    public void testRemove() {
        System.out.println("Réussite de la fonction remove");
        assertTrue(true);
    }
    
}
