/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

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
public class UsersTest {
    
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
        Users instance = new Users("prenom",1,"mail");
        String expResult = "prenom";
        String result = instance.getPrenom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNiveau method, of class Users.
     */
    @Test
    public void testGetNiveau() {
        System.out.println("getNiveau");
        Users instance = new Users("prenom",1,"mail");
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
        Users instance = new Users("prenom",1,"mail");
        String expResult = "mail";
        String result = instance.getMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrenom method, of class Users.
     */
    @Test
    public void testSetPrenom() {
        System.out.println("setPrenom");
        String prenom = "setPrenom";
        Users instance = new Users("prenom",1,"mail");
        instance.setPrenom(prenom);
        assertEquals("Vérifie si le prénom à été changé","setPrenom","setPrenom");
    }

    /**
     * Test of setNiveau method, of class Users.
     */
    @Test
    public void testSetNiveau() {
        System.out.println("setNiveau");
        int niveau = 0;
        Users instance = new Users("prenom",1,"mail");
        instance.setNiveau(niveau);
        assertEquals("Vérifie si le niveau à été changé",0,0);
    }

    /**
     * Test of setMail method, of class Users.
     */
    @Test
    public void testSetMail() {
        System.out.println("setMail");
        String mail = "setMail";
        Users instance = new Users("prenom",1,"mail");
        instance.setMail(mail);
        assertEquals("Vérifie si le mail à été changé","setMail","setMail");
    }
    
    /**
     * Test of Select from one User
     */
    @Test
    public void testSelectPrenom(){
        Object resultUser = (Users)passerelle.select(new Users(),2);
        System.out.println("Select User Surname");
        Users instance = (Users)resultUser;
        String prenom = instance.getPrenom();
        System.out.println(prenom);
        assertEquals("Utilisateur un", "salut", prenom );
    }
    
    /**
     * Test of Select from one User (name)
     */
    @Test
    public void testSelectNom(){
        System.out.println("Select User Name");
        Users instance = (Users)passerelle.select(new Users(),2);
        String nom = instance.getNom();
        System.out.println(nom);
        assertEquals("Utilisateur un", "test1", nom );
    }
    
}
