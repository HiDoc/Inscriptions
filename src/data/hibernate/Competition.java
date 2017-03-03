/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "competition")
@PrimaryKeyJoinColumn(name = "id_co", referencedColumnName = "id_ca")

public class Competition implements Serializable { 

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_co")
    private int id_co;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_d")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;

    @Column(name = "duree")
    private int duree;

    @Column(name = "enEquipe")
    private boolean enEquipe;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "participer", joinColumns = {
        @JoinColumn(name = "id_co")}, inverseJoinColumns = {
        @JoinColumn(name = "id_competition")})
    private final Set<Candidat> candidats = new HashSet<>(0);
    
    /**
     * Constructeur par défaut de la classe
     *
     */
    @SuppressWarnings("unused")
    public Competition() {
    }

    /**
     * Constructeur de la classe
     * @param nom
     * @param date_d
     * @param duree
     * @param enEquipe
     */
    public Competition(String nom, Calendar date_d, int duree, boolean enEquipe) {
        this.nom = nom;
        this.date = date_d;
        this.duree = duree;
        this.enEquipe = enEquipe;
    }

    /**
     * Getter
     * Retourne le nom de la compétition
     * @return String Nom
     */
    protected String getNom() {
        return this.nom;
    }

    /**
     * Getter
     * Retourne la date de la compétition
     * @return Calendar Date
     */
    protected Calendar getDate() {
        return this.date;
    }

    /**
     * Getter
     * Retourne le temps de la compétition en secondes
     * @return int Duree
     */
    protected int getDuree() {
        return this.duree;
    }

    /**
     * Getter
     * Retourne vrai si la compétition se déroule en équipe 
     * @return boolean enEquipe
     */
    protected boolean getEnEquipe() {
        return this.enEquipe;
    }

    /**
     * Setter
     * Modifie le nom de la compétition
     * @param nom
     */
    protected void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter
     * Modifie la date de la compétition
     * @param date
     */
    protected void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Setter
     * Modifie la durée en secondes de la compétition
     * @param duree
     */
    protected void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * Setter
     * Modifie si la compétition doit se faire en équipe
     * @param enEquipe
     */
    protected void setEnEquipe(boolean enEquipe) {
        this.enEquipe = enEquipe;
    }

    /**
     * Getter
     * @return Une liste de Candidat inscrits à la compétition
     */
    public Set<Candidat> getCandidats() {
        return this.candidats;
    }

    @Override
    public String toString() {
        return "Compétion  " + this.nom + " commençant le" + this.date + " d'une durée de " + this.duree;
    }
}
