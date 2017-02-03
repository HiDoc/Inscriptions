/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "users")
@PrimaryKeyJoinColumn(name = "id_us", referencedColumnName="id_ca")

public class Users extends Candidat implements Serializable {

    
    
   

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "niveau")
    private int niveau;

    @Column(name = "mail")
    private String mail;

    public Users() {
    }

    public Users(String prenom, int niveau, String mail) {
        this.prenom = prenom;
        this.niveau = niveau;
        this.mail = mail;
    }
    
    /*
     Getters
    */
    protected String getPrenom(){
        return this.prenom;
    }
    protected int getNiveau(){
        return this.niveau;
    }
    protected String getMail(){
        return this.mail;
    }
    
    /*
     Setters
    */
    protected void setPrenom( String prenom ){
        this.prenom = prenom;
    }
    protected void setDate( int niveau ){
        this.niveau = niveau;
    }
    protected void setMail( String mail ){
        this.mail = mail;
    }
    @Override
    public String toString(){
        return this.GetNom() + ' ' + this.prenom + "\n Adresse mail : " + this.mail; 
    }

}
