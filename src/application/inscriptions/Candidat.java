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
import javax.persistence.MappedSuperclass;
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
        @JoinColumn(name = "id_user")})
    /*
     * Crée une liste de toutes les compétitions auxquelles le candidat est inscrit
     */
    private final Set<Competition> competition = new HashSet<>(0);
    
    /*
     * Clés plusieurs à plusieurs sur la table appartenir
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appartenir", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_user")})
    /**
     * Crée une liste de toutes les équipes auxquelles le candidat est inscrit
     */
    private final Set<Equipe> equipe = new HashSet<>(0);


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
     * Retourne la liste des équipes du candidat
     * @return un Set de Candidat
     */
    public Set<Equipe> getEquipe() {
        return this.equipe;
    }

    /**
     * Retourne la liste des candidats inscrits dans cette équipe
     * @return un Set de candidat
     */
    public Set<Equipe> getCandidats(){
        return this.equipe;
    }
    /**
     * Retourne la liste des compétitions du candidat
     * @return un Set de Competition
     */
    public Set<Competition> getCompetition() {
        return this.competition;
    }
    
    /**
     * Inscrit un candidat à une compétition
     * @param competition
     */
    public void inscription(Competition competition){
        competition.addCandidat(this);
    }

    /**
     * Désinscrit un candidat à une compétition. Lance une erreur si le candidat 
     * est déjà inscrit à cette compétition
     * @param competition
     */
    public void desinscription(Competition competition){
        if(!this.competition.contains(competition))
            competition.removeCandidat(this);
        else throw new RuntimeException("le candidat est déjà inscrit");
    }
    
    /**
     * Ajoute une équipe à un candidat ou un candidat à une équipe
     * Override dans la classe Equipe
     * @param candidat
     */
    public void addEquipe(Candidat candidat){
        if(!this.equipe.contains((Equipe)candidat)){
            this.equipe.add((Equipe) candidat);
        }
        else throw new RuntimeException("le candidat est déjà inscrit");
    }

    /**
     * Enlève une équipe à un candidat ou un candidat à une équipe
     * @param candidat
     */
    public void removeEquipe(Candidat candidat){
    if(this.equipe.contains((Equipe)candidat)){
            this.equipe.remove((Equipe)candidat);
        }
        else throw new RuntimeException("le candidat n'est pas inscrit");    
    }

    /**
     * Vérifie si le candidat est une équipe ou un utilisateur
     * @return booléen
     */
    public boolean isEquipe(){
        return passerelle.isEquipe(this.id_ca);
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
