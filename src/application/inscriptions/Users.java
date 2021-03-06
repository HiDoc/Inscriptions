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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import data.hibernate.passerelle;
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
     * Clés plusieurs à plusieurs sur la table participer
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
            {
            		CascadeType.DETACH
            })
    @JoinTable(name = "appartenir", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_eq")})
    private Set<Equipe> equipes = new HashSet<>(0);
    
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
     * @param prenom le prénom de l'utilisateur
     * @param niveau le niveau d'accès de l'utilisateur
     * @param mail l'adresse e-mail de l'utilisateur
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
     * @return une chaine de caractère correspondant au prénom de l'utilisateur
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
     * Getter
     * Retourne les équipes de l'utilisateur
     * @return Set d'objet Equipe
     */
    public Set<Equipe> getEquipes(){
        return this.equipes;
    }
    
    /**
     * Setter
     * Modifie le prénom de l'utilisateur
     * @param prenom le nouveau prénom de l'utilisateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Setter
     * Modifie le niveau d'accès aux droits de l'utilisateur
     * @param niveau le nouveau niveau de l'utilisateur
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * Setter
     * Modifie le mail de l'utilisateur
     * @param mail le nouveau mail de l'utilisateur
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    /**
     * Setter
     * Modifie les equipes de l'utilisateur
     * @param equipes les nouvelles équipes de l'utilisateur
     */
    public void setEquipes(Set<Equipe> equipes) {
        this.equipes = equipes;
    }

    /**
     * Ajoute l'utilisateur à une équipe
     * @param equipe une nouvelle équipe pour l'utilisateur
     */
    public void addEquipe(Equipe equipe){
        this.equipes.add(equipe);
        passerelle.update(this);
    }
    /**
     * Enlève l'utilisateur d'une équipe
     * @param equipe une équipe déjà existante à supprimer dans la liste des équipes de l'utilisateur
     */
    public void removeEquipe(Equipe equipe){
    	if(this.equipes.contains(equipe)){
    		this.equipes.remove(equipe);
    		passerelle.update(this);
    	}
    }
    
    
    @Override
    public String toString()
    {
    	return this.prenom + " " + super.getNom();
    }

}
