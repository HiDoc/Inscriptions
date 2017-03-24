/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Flo
 */
@Entity 
@Table(name = "users")
   
public class Users extends Candidat implements Serializable {
    
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "niveau")
    private int niveau;
    
    @Column(name = "mail")
    private String mail;
    
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
    private final Set<Equipe> equipes = new HashSet<>(0);

    
    /**
     * Constructeur vide pour la persistance
     */
    @SuppressWarnings("unused")
    public Users() {
    }

    /**
     * Constructeur
     *
     * @param nom de la classe mère Candidat
     * @param prenom
     * @param niveau
     * @param mail
     */
    public Users(String nom,String prenom, int niveau, String mail) {
        super(nom);
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
    /**
     * Retourne la liste des équipes du candidat
     * @return un Set de Candidat
     */
    public Set<Equipe> getEquipe() {
        return this.equipes;
    }
    /**
     * Ajouter un candidat à l'équipe
     * @param equipe
     */
    public void addEquipe(Equipe equipe){
        if(!this.equipes.contains((Equipe)equipe)){
            this.equipes.add((Equipe) equipe);
        }
        else throw new RuntimeException("le candidat est déjà inscrit");
    }

    /**
     * Enlever un candidat de l'équipe
     * @param equipe
     */
    public void removeEquipe(Equipe equipe){
    if(this.equipes.contains((Equipe)equipe)){
            this.equipes.remove((Equipe)equipe);
        }
        else throw new RuntimeException("le candidat n'est pas inscrit");    
    }

}
