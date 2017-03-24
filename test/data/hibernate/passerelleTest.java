/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import application.inscriptions.Candidat;
import application.inscriptions.Competition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    
    Candidat candidat = new Candidat("name");
    Competition competition = CompetitionCreator();
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
        Competition test = new Competition(nom,Calendar_debut, Calendar_fin,duree_test,en_equipe);
        return test;
         
    }
    public passerelleTest() {
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
     * Test of open method, of class passerelle.
     */
    @Test
    public void testOpen() {
    }

    /**
     * Test of close method, of class passerelle.
     */
    @Test
    public void testClose() {
    }

    /**
     * Test of delete method, of class passerelle.
     */
    @Test
    public void testDelete() {
        long before = passerelle.count("Candidat");
        passerelle.save(candidat);
        long after = passerelle.count("Candidat");
        assertEquals(before , after - 1);
    }

    /**
     * Test of save method, of class passerelle.
     */
    @Test
    public void testSave() {
        long before = passerelle.count("Candidat");
        passerelle.save(this.candidat);
        long after = passerelle.count("Candidat");
        assertEquals(before + 1 , after);
    }

    /**
     * Test of saveAndId method, of class passerelle.
     */
    @Test
    public void testSaveAndId() {
        System.out.println("saveAndId");
        long expResult = passerelle.count("Candidat");
        int result = passerelle.saveAndId(this.candidat);
        assertEquals(expResult + 1, result);
    }

    /**
     * Test of count method, of class passerelle.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        long result = passerelle.count("Candidat");
        assertTrue(result > 0);
    }

    /**
     * Test of select method, of class passerelle.
     */
    @Test
    public void testGet() {
        System.out.println("select");
        int id = passerelle.saveAndId(this.candidat);
        Candidat expCandidat = (Candidat) passerelle.get(Candidat.class, id);
        assertTrue(this.candidat.compareTo(expCandidat) == 0);
    }

    /**
     * Test of table method, of class passerelle.
     */
    @Test
    public void testTable_Object() {
        System.out.println("table");
        long expResult = passerelle.count("Candidat");
        List result = passerelle.table(Candidat.class);
        assertEquals(expResult, result.size());
    }


}
