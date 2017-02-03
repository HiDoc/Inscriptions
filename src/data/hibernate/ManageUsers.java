/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Armand
 */
public class ManageUsers extends ManageCandidat {
    
      private static SessionFactory factory;
    
   static  {
        try {
            factory = new Configuration().configure("data/hibernate/database.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    /**
     * Supprime un utilisateur
     * @param id
     */
    public void DropUser(Integer id){
      Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Users candidat
                    = (Users) session.load(Users.class, id);
         session.delete(candidat); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }
     
    /**
     * Renvoi la liste de tout les utilisateurs
     * @return
     */
    public List<Users> getUsers(){
        Session session = factory.openSession();
        Query query = session.createQuery("from Users"); 
        List<Users> list = query.list(); 
        return list;
   }
    /**
     * Afficher les utilisateurs
     */
    public void showUsers(){
        List<Users> list = getUsers();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * Afficher les utilisateurs
     * @param list
     */
    public void showUsers(List<Users> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
