/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import data.hibernate.passerelle;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Couche métier des Compétitions. Permet de générer en persistance les
 * compétitions.
 *
 * @author Flo
 */
@Entity
@Table(name = "competition")

public class Competition implements Serializable, Comparable<Competition> {

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {
                CascadeType.DETACH
            })
    @JoinTable(name = "participer", joinColumns = {
        @JoinColumn(name = "id_co")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ca")})
    private final Set<Candidat> candidats = new HashSet<>(0);

    /**
     * Constructeur par défaut vide pour la persistance
     */
    @SuppressWarnings("unused")
    public Competition() {
    }

    /**
     * Surcharge du constructeur de la classe
     *
     * @param nom chaine de caractère
     * @param date_d de type Calendar
     * @param duree un nombre
     * @param enEquipe un booléen
     * @param dateClose un Calendar
     */
    public Competition(String nom, Calendar date_d, Calendar dateClose, int duree, boolean enEquipe) {
        this.nom = nom;
        this.date = date_d;
        this.duree = duree;
        this.enEquipe = enEquipe;
        this.dateClose = dateClose;
    }

    /**
     * Retourne l'id de la compétition
     *
     * @return une chaine de caractères
     */
    public int getId() {
        return this.id_co;
    }

    /**
     * Retourne le nom de la compétition
     *
     * @return une chaine de caractères
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne la date de la compétition
     *
     * @return une date de type Calendar
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Formatte la date sous forme de chaine de caractère
     * 
     * @return une chaine de caractère
     */
    public String getDateToString() {
        return new SimpleDateFormat("yyyy/MM/dd").format(this.date.getTime());
    }

    /**
     * Retourne le temps de la compétition en secondes
     *
     * @return un nombre en secondes
     */
    public int getDuree() {
        return this.duree;
    }

    /**
     * Retourne la date de fermeture des inscription
     *
     * @return une date
     */
    protected Calendar getDateClose() {
        return this.dateClose;
    }

    /**
     * Retourne vrai si la compétition se déroule en équipe
     *
     * @return un booléan
     */
    public boolean getEnEquipe() {
        return this.enEquipe;
    }

    /**
     * Modifie le nom de la compétition
     *
     * @param nom une chaine de caractères
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie la date de la compétition
     *
     * @param date de type Calendar
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Modifie la durée en secondes de la compétition
     *
     * @param duree une nombre de secondes
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * Modifie si la compétition doit se faire en équipe
     *
     * @param enEquipe de type booléan
     */
    public void setEnEquipe(boolean enEquipe) {
        this.enEquipe = enEquipe;
    }

    /**
     * Modifie la date de fermeture d'inscription de la compétition
     *
     * @param dateClose la nouvelle date de fermeture d'inscription
     */
    protected void setDateClose(Calendar dateClose) {
        this.dateClose = dateClose;
    }

    /**
     * Retourne la liste des candidats inscrits à la compétition
     *
     * @return un Set de candidats
     */
    public Set<Candidat> getCandidats() {
        return this.candidats;
    }

    /**
     * Vérifie si les inscriptions sont ouvertes
     *
     * @return booléen
     */
    protected boolean inscriptionsOuvertes() {
        return this.dateClose.after(Calendar.getInstance());
    }

    /**
     * Ajoute un candidat à la compétition
     *
     * @param candidat TODO : ajouter la vérification de si le candidat est une
     * équipe ou non
     */
    public void addCandidat(Candidat candidat) {
        candidats.add(candidat);
        passerelle.update(this);
    }

    /**
     * Enlève un candidat de la compétition
     *
     * @param candidat le candidat à enlever de la compétition
     */
    public void removeCandidat(Candidat candidat) {
        if (this.candidats.contains(candidat)) {
            this.candidats.remove(candidat);
            passerelle.update(this);
        }
    }

    /**
     * Supprime la compétition de l'application
     *
     */
    protected void remove() {
        passerelle.delete(this);
    }

    @Override
    public String toString() {
        return this.nom;
    }

    @Override
    public int compareTo(Competition competition) {
        int i = getNom().compareTo(competition.getNom());
        return i + (getId() == competition.getId() ? 0 : 1);
    }
}
