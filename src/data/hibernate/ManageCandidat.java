/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

/**
 *
 * @author Armand
 */
public class ManageCandidat {

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure("data/hibernate/database.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    /**
     * Ajouter un candidat
     *
     * @param nom
     * @return id
     */
    public Integer AddCandidat(String nom) {

        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            Candidat candidat = new Candidat(nom);
            id = (Integer) session.save(candidat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * Update le nom du candidat
     *
     * @param id
     * @param nom
     */
    public void SetNom(int id, String nom) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Candidat candidat
                    = (Candidat) session.load(Candidat.class, id);
            candidat.setNom(nom);
            session.update(candidat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Supprime un candidat
     *
     * @param id
     */
    public void DropCandidat(Integer id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Candidat candidat
                    = (Candidat) session.load(Candidat.class, id);
            session.delete(candidat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Fait une liste de tout les candidats
     *
     * @return list<>
     */
    public List<Candidat> getCandidats() {
        Session session = factory.openSession();
        Query query = session.createQuery("from Candidat");
        List<Candidat> list = query.list();
        return list;
    }

    /**
     * Afficher les candidats
     */
    public void showCandidats() {
        List<Candidat> list = getCandidats();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * Afficher les candidats
     *
     * @param list
     */
    public void showCandidats(List<Candidat> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

}
