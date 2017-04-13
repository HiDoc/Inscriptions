/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import data.hibernate.passerelle;
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
public class EquipeTest {
    
    public EquipeTest() {
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
     * Test of getUsers method, of class Equipe.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Equipe instance = (Equipe) passerelle.get(Equipe.class,3);
        Users instanceU = (Users) passerelle.get(Users.class,1);
        Set<Users> expResult = instance.getUsers();
        assertTrue(expResult.contains(instanceU));
    }
    
    /**
     * Test of addUser method, of class Equipe.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        Equipe instance = (Equipe) passerelle.get(Equipe.class,3);
        Users instanceU = (Users) passerelle.get(Users.class,2);
        instance.addUser(instanceU);
        passerelle.save(instance);
        assertTrue((((Equipe)passerelle.get(Equipe.class,3)).getUsers()).contains(instanceU));
    }

    /**
     * Test of removeUser method, of class Equipe.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        Equipe instance = (Equipe) passerelle.get(Equipe.class,3);
        Users instanceU = (Users) passerelle.get(Users.class,2);
        instance.removeUser(instanceU);
        passerelle.save(instance);
        assertFalse((((Equipe)passerelle.get(Equipe.class,3)).getUsers()).contains(instanceU));
    }
    
}
