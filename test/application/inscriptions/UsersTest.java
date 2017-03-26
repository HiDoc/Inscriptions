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

import static org.junit.Assert.*;

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
    
    @After
    public void tearDown() {
        passerelle.close();
    }

    /**
     * Test of getPrenom method, of class Users.
     */
    @Test
    public void testGetPrenom() {
        System.out.println("getPrenom");
        String expResult = "prenom_1";
        assertEquals(expResult, instance.getPrenom());
    }

    /**
     * Test of getNiveau method, of class Users.
     */
    @Test
    public void testGetNiveau() {
        System.out.println("getNiveau");
        int expResult = 0;
        int result = instance.getNiveau();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMail method, of class Users.
     */
    @Test
    public void testGetMail() {
        System.out.println("getMail");
        String expResult = "nom_1prenom_1@mail.com";
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
        instance.setPrenom(prenom);
        assertEquals(prenom, instance.getPrenom());
    }

    /**
     * Test of setNiveau method, of class Users.
     */
    @Test
    public void testSetNiveau() {
        System.out.println("setNiveau");
        int niveau = 1;
        instance.setNiveau(niveau);
        assertEquals(niveau, instance.getNiveau());
    }

    /**
     * Test of setMail method, of class Users.
     */
    @Test
    public void testSetMail() {
        System.out.println("setMail");
        String mail = "yolo@yolo.om";
        instance.setMail(mail);
        assertEquals(mail, instance.getMail());
    }
    
}
