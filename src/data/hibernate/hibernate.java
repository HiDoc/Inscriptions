
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

/**
 *
 * @author Flo
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;




public class hibernate {
/*
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
*/
    
    public static void main(String[] args) {
       
       passerelle passerelle = new passerelle();
       passerelle.open();
    
    
       Users test;
        test = new Users("jean",1,"arararararara");
        passerelle.save(test);
      
      
       
    }
}
    
    
