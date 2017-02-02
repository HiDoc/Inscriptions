/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Flo
 */
public class hibernate {

    public static Session getSession() throws HibernateException {
        Configuration configuration = new Configuration()
                .configure("data/hibernate/database.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);
        return sessionFactory.openSession();
    }
   
    
    private static SessionFactory factory;    
   static  {
        try {
            factory = new Configuration().configure("data/hibernate/database.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    
    public static void main(String[] args) {
        
        Session session =  factory.openSession();
      
       Candidat test = new Candidat();
       test.SetNom(21,"pute", session);
       
       
       
        
    }
    
    

}
