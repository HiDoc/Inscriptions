/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import data.hibernate.passerelle;
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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "competition")


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
    
    @Column(name = "date_close")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dateClose;

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
     * Constructeur par défaut vide pour la persistance
     */
    @SuppressWarnings("unused")
    public Competition() {
    }

    /**
     * Surcharge du constructeur de la classe
     * @param nom chaine de caractère
     * @param date_d de type Calendar
     * @param duree un nombre
     * @param enEquipe un booléen
     * @param dateClose un Calendar
     */
    public Competition(String nom, Calendar date_d, int duree, boolean enEquipe,Calendar dateClose) {
        this.nom = nom;
        this.date = date_d;
        this.duree = duree;
        this.enEquipe = enEquipe;
        this.dateClose = dateClose;
    }

    /**
     * Retourne le nom de la compétition
     * @return une chaine de caractères
     */
    protected String getNom() {
        return this.nom;
    }

    /**
     * Retourne la date de la compétition
     * @return une date de type Calendar
     */
    protected Calendar getDate() {
        return this.date;
    }

    /**
     * Retourne le temps de la compétition en secondes
     * @return un nombre en secondes
     */
    protected int getDuree() {
        return this.duree;
    }
    
    /**
     * Retourne la date de fermeture des inscription
     * @return une date
     */
    protected Calendar getDateClose(){
        return this.dateClose;
    }
    /**
     * Retourne vrai si la compétition se déroule en équipe 
     * @return un booléan
     */
    protected boolean getEnEquipe() {
        return this.enEquipe;
    }

    /**
     * Modifie le nom de la compétition
     * @param nom une chaine de caractères
     */
    protected void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie la date de la compétition
     * @param date de type Calendar
     */
    protected void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Modifie la durée en secondes de la compétition
     * @param duree une nombre de secondes
     */
    protected void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * Modifie si la compétition doit se faire en équipe
     * @param enEquipe de type booléan
     */
    protected void setEnEquipe(boolean enEquipe) {
        this.enEquipe = enEquipe;
    }
    
    /**
     * Modifie la date de fermeture d'inscription de la compétition
     * @param dateClose
     */
    protected void setDateClose(Calendar dateClose){
        this.dateClose = dateClose;
    }

    /**
     * Retourne la liste des candidats inscrits à la compétition
     * @return un Set de candidats 
     */
    public Set<Candidat> getCandidats() {
        return this.candidats;
    }
    
    /**
     * Vérifie si les inscriptions sont ouvertes
     * @return booléen
     */
    protected boolean inscriptionsOuvertes(){
        return this.dateClose.after(Calendar.getInstance());
    }
    
    /**
     * Ajoute un candidat à la compétition
     * @param candidat
     * TODO : ajouter la vérification de si le candidat est une équipe ou non
     */
    public void addCandidat(Candidat candidat){
        if(!this.enEquipe){
            this.candidats.add(candidat);
            passerelle.save(this.candidats); 
        }
        else throw new RuntimeException();
    }

    /**
     * Enlève un candidat de la compétition
     * @param candidat
     */
    public void removeCandidat(Candidat candidat){
        this.candidats.remove(candidat);
        passerelle.save(this.candidats);
    }
    
    /**
     * Supprime la compétition de l'application
     * 
     */
    protected void remove(){
        passerelle.delete(this);
    }
    
    @Override
    public String toString() {
        return "Compétion  " + this.nom + " commençant le" + this.date + " d'une durée de " + this.duree;
    }
}
