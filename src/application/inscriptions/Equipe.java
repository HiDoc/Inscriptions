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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import data.hibernate.passerelle;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "equipe")
public class Equipe extends Candidat implements Serializable {

    /*
     * Clés plusieurs à plusieurs sur la table participer
     */
	@ManyToMany(fetch = FetchType.LAZY,
            cascade =
            {
            		CascadeType.DETACH
            })
    @JoinTable(name = "appartenir", joinColumns = {
        @JoinColumn(name = "id_eq")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ca")})
    private Set<Users> utilisateurs = new HashSet<>(0);
    
    /**
     * Clé un à plusieurs sur la table users
     * TODO : implémenter la clé un à plusieurs pour notifier le chef d"équipe 
     */
//    @OneToMany
//    @JoinTable( name= "Users", joinColums = { 
//        @JoinColumn(name="id_chef")}, inverseJoinColums = { 
//        @JoinColumn(name="id_ca")})
//    private Users chefEquipe;
    
    
    /**
     * Retourne une liste d'utilisateurs
     * @return un Set d'objet User
     */
    public Set<Users> getUsers() {
        return this.utilisateurs;
    }

    /**
     * Modifie les utilisateurs d'une équipe
     * @param utilisateurs les utilisateurs qui composent l'équipe
     */
    public void setUtilisateurs(Set<Users> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    /**
     * Ajoute un utilisateur à l'équipe
     * @param user l'utilisateur à ajouter à l'équipe
     */
    public void addUser(Users user) {
        this.utilisateurs.add(user);
        passerelle.update(this);
    }

    /**
     * Enlève un utilisateur d'une équipe
     * @param user l'utilisateur à enlever de l'équipe
     */
    public void removeUser(Users user) {
    	if(this.utilisateurs.contains(user)){
    		this.utilisateurs.remove(user);
    		passerelle.update(this);
    	}
    }

    /**
     * Constructeur vide pour la persistance
     */
    @SuppressWarnings("unused")
    public Equipe() {
    }

    /**
     * Constructeur
     *
     * @param nom de la classe mère Candidat
     */
    public Equipe(String nom) {
        super(nom);
    }

}
