/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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

/**
 * Couche accès aux données de la classe Candidat
 * @author Flo
 */
@Entity
@Table(name = "candidat")
@Inheritance(strategy = InheritanceType.JOINED)
public class Candidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ca")
    private int id_ca;

    @Column(name = "nom")
    private String nom;
    
    /**
     * Clés plusieurs à plusieurs sur la table participer
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "participer", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_user")})
    /**
     * Crée une liste de toutes les compétitions auxquelles le candidat est inscrit
     */
    private final Set<Competition> competition = new HashSet<>(0);
    
    /**
     * Clés plusieurs à plusieurs sur la table appartenir
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appartenir", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_user")})
    /**
     * Crée une liste de toutes les équipes auxquelles le candidat est inscrit
     */
    private final Set<Candidat> equipe = new HashSet<>(0);


    /**
     * Constructeur par défault vide pour la persistance
     */
    @SuppressWarnings("unused")
    public Candidat() {
    }

    /**
     * Surcharge du constructeur
     * @param nom une chaine de caractère
     */
    public Candidat(String nom) {
        this.nom = nom;
    }
    
    /**
     * Retourne le nom d'un Candidat
     * @return nom - une chaine de caractères
     */
    public String getNom() {
        return this.nom;
    }
    private int getId(){
        return this.id_ca;
    }
    /**
     * Attribue un nouveau nom au Candidat
     * @param nom une chaine de caractères
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la liste des équipes du candidat
     * @return un Set de Candidat
     */
    public Set<Candidat> getEquipe() {
        return this.equipe;
    }
    
    /**
     * Retourne la liste des compétitions du candidat
     * @return un Set de Competition
     */
    public Set<Competition> getCompetition() {
        return this.competition;
    }
    
    @Override
    public int hashCode(){
        int hash;
        hash = getNom().hashCode();
        hash = hash * 28 + getId();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Candidat other = (Candidat) obj;
        if (this.id_ca != other.id_ca) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

}
