/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.hibernate.Users;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
public class selectUser {
    
    /**
     * Construit un nouvel objet de type Users
     * 
     */
    private static SessionFactory factory;
    public List<Users> results; 
    
    static {
        try {
            factory = new Configuration().configure("data/hibernate/database.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
    Session session = factory.openSession();
    public selectUser() {
        this.results = session.createCriteria(Users.class).list();
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
        session.close();
    }

    /**
     * Vérifie si l'utilisateur un à comme prénom "salut"
     */
    @Test
    public void testUserOne(){
        String beEquals = this.results.get(1).getPrenom();
        System.out.println(beEquals);
        assertEquals("Utilisateur un", "salut", beEquals );
    }
    @Test
    public void testUserTwo(){
        String beEquals = this.results.get(1).getNom();
        System.out.println(beEquals);
        assertEquals("Utilisateur un", "Joffrey", beEquals );
    }
}
