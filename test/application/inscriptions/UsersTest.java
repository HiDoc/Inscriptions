/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.hibernate.passerelle;
import java.util.Set;

import static org.junit.Assert.*;

import org.hibernate.SessionException;

/**
 *
 * @author Flo
 */
public class UsersTest {
    
	Users instance;
	
    public UsersTest() {
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
        this.instance = (Users) passerelle.get(Users.class,1);
    }
    
    /**
     * La passerelle est parfois déjà fermée par la classse Inscriptions
     */
    @After
    public void tearDown() {
    	try{
    		passerelle.close();
    	}
    	catch (SessionException e){
    		
    	}
    }

    /**
     * Test of getPrenom method, of class Users.
     */
    @Test
    public void testGetPrenom() {
        System.out.println("getPrenom");
        String expResult = "datPrenom";
        assertEquals(expResult, instance.getPrenom());
    }

    /**
     * Test of getNiveau method, of class Users.
     */
    @Test
    public void testGetNiveau() {
        System.out.println("getNiveau");
        int expResult = 1;
        int result = instance.getNiveau();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMail method, of class Users.
     */
    @Test
    public void testGetMail() {
        System.out.println("getMail");
        String expResult = "yolo@yolo.com";
        String result = instance.getMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrenom method, of class Users.
     */
    @Test
    public void testSetPrenom() {
        System.out.println("setPrenom");
        String prenom = "datPrenom";
        this.instance.setPrenom(prenom);
        passerelle.save(this.instance);
        assertEquals(prenom, ((Users) passerelle.get(Users.class, 1)).getPrenom());
    }

    /**
     * Test of setNiveau method, of class Users.
     */
    @Test
    public void testSetNiveau() {
        System.out.println("setNiveau");
        int niveau = 1;
        this.instance.setNiveau(niveau);
        passerelle.save(this.instance);
        assertEquals(niveau, ((Users)passerelle.get(Users.class, 1)).getNiveau());
    }

    /**
     * Test of setMail method, of class Users.
     */
    @Test
    public void testSetMail() {
        System.out.println("setMail");
        String mail = "yolo@yolo.com";
        this.instance.setMail(mail);
        passerelle.save(this.instance);
        assertEquals(mail, ((Users)passerelle.get(Users.class, 1)).getMail());
    }
    /**
     * Test of inserting a new user in the database
     */
    @Test
    public void testRemove() {
    	System.out.println("remove User");
        long result = (long)passerelle.count("Users");
        Users inst = (Users)passerelle.table(Users.class).get((int)result - 1);
        passerelle.delete(inst);
        long expResult = (long)passerelle.count("Users");
        assertEquals(result - 1, expResult);
    }
    
    /**
     * Test of inserting a new user in the database
     */
    @Test
    public void testCreate() {
    	System.out.println("create User");
        long result = (long)passerelle.count("Users");
    	passerelle.save(new Users("nom", "prenom",0,"mail@mail.com"));
        long expResult = (long)passerelle.count("Users");
        assertEquals(result + 1, expResult);
    }
    
    @Test
    public void testGetCompetition() {
        System.out.println("Réussite de la fonction getCompetition");
        Competition competition = (Competition) passerelle.get(Competition.class, 1);
        System.out.println((this.instance.getCompetition()));
        assertTrue(this.instance.getCompetition().contains(competition));
    }

    /**
     * Test of getEquipes method, of class Users.
     */
    @Test
    public void testGetEquipes() {
        System.out.println("getEquipes");
        Equipe instanceE = (Equipe)passerelle.get(Equipe.class, 3);
        assertTrue(this.instance.getEquipes().contains(instanceE));
    }


    /**
     * Test of addEquipe method, of class Users.
     */
    @Test
    public void testAddEquipe() {
        System.out.println("addEquipe");
        Equipe equipe = (Equipe) passerelle.get(Equipe.class,21);
        this.instance.addEquipe(equipe);
        passerelle.save(this.instance);
        assertTrue(((Users)passerelle.get(Users.class, 1)).getEquipes().contains(equipe));
    }

    /**
     * Test of removeEquipe method, of class Users.
     */
    @Test
    public void testRemoveEquipe() {
        System.out.println("removeEquipe");
        Equipe equipe = (Equipe) passerelle.get(Equipe.class, 21);
        this.instance.removeEquipe(equipe);
        passerelle.save(this.instance);
        assertFalse(((Users)passerelle.get(Users.class, 1)).getEquipes().contains(equipe));
    }

    
}
