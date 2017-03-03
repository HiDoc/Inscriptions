/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Couche accès aux données de la classe Candidat
 * @author Flo
 */
@Entity
@Table(name = "candidat")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Candidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ca")
    protected int id_ca;

    @Column(name = "nom")
    private String nom;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "participer", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_user")})
    private final Set<Competition> competition = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appartenir", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_user")})
    private final Set<Candidat> equipe = new HashSet<>(0);


    /**
     * Constructeur par défault
     */
    public Candidat() {
        this.nom = "default";
    }

    /**
     * Constructeur
     * @param nom
     */
    public Candidat(String nom) {
        this.nom = nom;
    }
    
    /**
     * Getter
    * Retourne le nom d'un Candidat
    * @return nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter
     * Attribue un nouveau nom au Candidat
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter 
     * Retourne les équipes d'un candidat
     * @return Set<Candidat> Equipes
     */
    public Set<Candidat> getEquipe() {
        return this.equipe;
    }
    
    /**
     * Getter
     * Retourne les compétitions d'un Candidat
     * @return Set<Competition> Compétitions
     */
    public Set<Competition> getCompetition() {
        return this.competition;
    }
    /**
     * Ajouter un candidat
     *
     * @param nom
     * @param factory
     * @return
     */
    public Integer AddCandidat(String nom, Session factory) {

        Session session = factory;
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
     * @param factory
     */
    public void SetNom(int id, String nom, Session factory) {
        Session session = factory;
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
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Enlève un candidat
     *
     * @param id
     * @param factory
     */
    public void DropCandidat(Integer id, Session factory) {
        Session session = factory;
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
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
