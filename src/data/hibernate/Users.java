/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
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
    
    @Id
    @OneToOne(mappedBy="Candidat")
    @Column(name = "id_us")
    private int id_us;
    
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "niveau")
    private int niveau;

    @Column(name = "mail")
    private String mail;
    
    @SuppressWarning("unused")
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

    /*
     Getters
     */
    protected String getPrenom() {
        return this.prenom;
    }

    protected int getNiveau() {
        return this.niveau;
    }

    protected String getMail() {
        return this.mail;
    }

    /*
     Setters
     */
    protected void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    protected void setDate(int niveau) {
        this.niveau = niveau;
    }

    protected void setMail(String mail) {
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
