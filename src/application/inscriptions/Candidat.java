/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import data.hibernate.passerelle;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Couche accès aux données de la classe Candidat
 * @author Flo
 */
@Entity
@Table(name = "candidat")
@Inheritance(strategy = InheritanceType.JOINED)
public class Candidat implements Serializable, Comparable<Candidat> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ca")
    private int id_ca;

    @Basic(fetch=EAGER)
    @Column(name = "nom")
    private String nom;
    
    /*
     * Clés plusieurs à plusieurs sur la table participer
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "participer", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_co")})
    /*
     * Crée une liste de toutes les compétitions auxquelles le candidat est inscrit
     */
    private final Set<Competition> competitions = new HashSet<>(0);

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
     * Retourne l'id d'un candidat
     * @return int 
     */
    protected int getId() {
        return this.id_ca;
    }
    /**
     * Retourne le nom d'un Candidat
     * @return nom - une chaine de caractères
     */
    public String getNom() {
        return this.nom;
    }
    /**
     * Attribue un nouveau nom au Candidat
     * @param nom une chaine de caractères
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Retourne la liste des compétitions du candidat
     * @return un Set de Competition
     */
    public Set<Competition> getCompetition() {
        return this.competitions;
    }
    
    /**
     * Inscrit un candidat à une compétition     * 
     * @param competition
     */
    public void inscription(Competition competition){
        competitions.add(competition);
    }

    /**
     * Désinscrit un candidat à une compétition. Lance une erreur si le candidat 
     * est déjà inscrit à cette compétition
     * @param competition
     */
    public void desinscription(Competition competition){
        if (competitions.contains(competition)){
            competitions.remove(competition);
        }
    }
    
    /**
     * Supprime un candidat de l'application
     */
    public void remove(){
        passerelle.delete(this);
    }
    
    @Override
    public String toString() {
    	return id_ca+":"+nom;
    }

    @Override
    public int compareTo(Candidat candidat) {
        int i = getNom().compareTo(candidat.getNom());
        return i + (getId() == candidat.getId() ? 0 : 1) ;
    }


}
