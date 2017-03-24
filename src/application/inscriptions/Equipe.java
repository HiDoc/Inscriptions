/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;;
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
@Table(name ="equipe")
public class Equipe extends Candidat implements Serializable {
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appartenir", joinColumns = {
        @JoinColumn(name = "id_ca")}, inverseJoinColumns = {
        @JoinColumn(name = "id_equipe")})
    private final Set<Users> users = new HashSet<>(0);

    /**
     * Retourne la liste des candidats inscrits dans cette équipe
     * @return un Set de candidat
     */
    public Set<Users> getUsers() {
        return users;
    }
    
    /**
     * Ajouter un candidat à l'équipe
     * @param candidat
     */
    public void addUser(Users candidat){
        if(!this.users.contains((Users)candidat)){
            this.users.add((Users) candidat);
        }
        else throw new RuntimeException("le candidat est déjà inscrit");
    }

    /**
     * Enlever un candidat de l'équipe
     * @param candidat 
     */
    public void removeUser(Candidat candidat){
    if(this.users.contains((Users)candidat)){
            this.users.remove((Users)candidat);
        }
        else throw new RuntimeException("le candidat n'est pas inscrit");    
    }
    
    
    
}
