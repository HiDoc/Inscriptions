/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;


import data.hibernate.passerelle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
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
    
         Competition instance;
        Set<Candidat> candid;
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
                 passerelle.open(); 
                instance = (Competition) passerelle.get(Competition.class,1);
                candid = instance.getCandidats();
    }
    
    @After
    public void tearDown() {
        passerelle.close();
    }
    
    /**
     * Set up a Competition with fixed value for test
     * @return a competition 
     */
    private Competition CompetitionCreator(){
        String nom = "test";
        GregorianCalendar Calendar_debut = new GregorianCalendar();
        Date date_test = new Date(95, 10, 10);
        Calendar_debut.setTime(date_test);
        date_test = new Date(96,10,10);
        Calendar Calendar_fin = new GregorianCalendar();
        Calendar_fin.setTime(date_test);
        int duree_test = 0;
        boolean en_equipe = true;
        Competition test = new Competition(nom,Calendar_debut,Calendar_fin,duree_test,en_equipe);
        return test;
         
    }
    
    private Candidat CandidatcandidatCreator(){
        Candidat _candidat = new Candidat("test");
        return _candidat;
    }
    
    
    /**
     * Test of getNom method, of class Competition.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        String expResult = "test";
        String result = this.instance.getNom();
        assertEquals(expResult, result);
       
    }
    

    /**
     * Test of getDate method, of class Competition.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        GregorianCalendar expResult = new GregorianCalendar();
        Date date_test = new Date(95, 10, 10);
        expResult.setTime(date_test);
        Calendar result = instance.getDate();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDuree method, of class Competition.
     */
    @Test
    public void testGetDuree() {
        System.out.println("getDuree");
        int expResult = 0;
        int result = instance.getDuree();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateClose method, of class Competition.
     */
    @Test
    public void testGetDateClose() {
        System.out.println("getDateClose");
        Competition instance = CompetitionCreator();
        GregorianCalendar expResult = new GregorianCalendar();
        Date date_test = new Date(96, 10, 10);
        expResult.setTime(date_test);
        Calendar result = instance.getDateClose();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of getEnEquipe method, of class Competition.
     */
    @Test
    public void testGetEnEquipe() {
        System.out.println("getEnEquipe");
        Competition instance = CompetitionCreator();
        boolean expResult = true;
        boolean result = instance.getEnEquipe();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNom method, of class Competition.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "test";
        Competition instance = new Competition();
        instance.setNom(nom);
        String result = instance.getNom();
        assertEquals(nom,result);
     
    }

    /**
     * Test of setDate method, of class Competition.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Calendar calendar = new GregorianCalendar();
        Date date_test = new Date(10,10,10);
        calendar.setTime(date_test);
        Competition instance = new Competition();
        instance.setDate(calendar);
        Calendar result = instance.getDate();
        assertEquals(calendar,result);
      
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
        int result = instance.getDuree();
        assertEquals(duree, result);
       
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
       boolean result = instance.getEnEquipe();
       assertEquals(result,enEquipe);
    }

    /**
     * Test of setDateClose method, of class Competition.
     */
    @Test
    public void testSetDateClose() {
        System.out.println("setDateClose");
       Calendar calendar = new GregorianCalendar();
        Date date_test = new Date(10,10,10);
        calendar.setTime(date_test);
        Competition instance = new Competition();
        instance.setDateClose(calendar);
        Calendar result = instance.getDateClose();
        assertEquals(calendar,result);
    }

    /**
     * Test of getCandidats method, of class Competition.
     */
    @Test
    public void testGetCandidats() {
        System.out.println("getCandidats");
        Competition competition = (Competition) passerelle.get(Competition.class, 1);
        Set<Candidat> expResult = competition.getCandidats();
        Set<Candidat> result = instance.getCandidats();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of inscriptionsOuvertes method, of class Competition.
     */
    @Test
    public void testInscriptionsOuvertes() {
        System.out.println("inscriptionsOuvertes");
        boolean expResult = false;
        boolean result = instance.inscriptionsOuvertes();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of addCandidat method, of class Competition.
     */
    @Test
    public void testAddCandidat() {
        System.out.println("addCandidat");
        Candidat candidat = (Candidat) passerelle.get(Candidat.class, 2);
        instance.addCandidat(candidat);
        passerelle.save(instance);
        assertTrue(instance.getCandidats().contains(candidat));
        
        
        
    }

    /**
     * Test of removeCandidat method, of class Competition.
     */
    @Test
    public void testRemoveCandidat() {
        System.out.println("removeCandidat");
        Candidat candidat = (Candidat) passerelle.get(Candidat.class, 1);
        instance.removeCandidat(candidat);
        passerelle.save(this.instance);
       assertTrue(!instance.getCandidats().contains(candidat));
    }
}