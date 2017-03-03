/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Flo
 */
@Entity 
@Table(name = "users")
   
public class Users extends Candidat implements Serializable {
     
    @Column(insertable = false, updatable = false, name= "id_ca")
    private int idU;
    
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "niveau")
    private int niveau;
    
    @Column(name = "mail")
    private String mail;
    
    @SuppressWarnings("unused")
    public Users() {
    }

    /**
     * Constructeur
     *
     * @param prenom
     * @param niveau
     * @param mail
     */
    public Users(String prenom, int niveau, String mail) {
        this.prenom = prenom;
        this.niveau = niveau;
        this.mail = mail;
    }

    /**
     * Getter
     * Retourne le prénom de l'utilisateur
     * @return String prenom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Getter
     * Retourne le niveau d'accès aux droits de l'utilisateur
     * @return int Niveau
     */
    protected int getNiveau() {
        return this.niveau;
    }

    /**
     * Getter
     * Retourne le mail de l'utilisateur
     * @return String mail
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Setter
     * Modifie le prénom de l'utilisateur
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Setter
     * Modifie le niveau d'accès aux droits de l'utilisateur
     * @param niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * Setter
     * Modifie le mail de l'utilisateur
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure("data/hibernate/database.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public void DropUser(Integer id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Users candidat
                    = (Users) session.load(Users.class, id);
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
}
