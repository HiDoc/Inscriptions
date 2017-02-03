/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.awt.List;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "competition")
@PrimaryKeyJoinColumn(name = "id_co",referencedColumnName = "id_ca")

public class Competition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_co")
    private int id_co;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_d")
    private Calendar date;

    @Column(name = "duree")
    private int duree;

    @Column(name = "enEquipe")
    private boolean enEquipe;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appartenir", joinColumns = { @JoinColumn(name = "id_co") }, inverseJoinColumns = { @JoinColumn(name = "id_competition") })
    private final Set<Candidat> candidats = new HashSet<>(0);
    public Set<Candidat> getCandidats() {
            return this.candidats;
    } 
    
    public Competition() {
    }
    public Competition(String nom, Calendar date_d, int duree, boolean enEquipe) {
        this.nom = nom;
        this.date = date_d;
        this.duree = duree;
        this.enEquipe = enEquipe;
    }
    
    /*
     Getters
    */
    protected String getNom(){
        return this.nom;
    }
    protected Calendar getDate(){
        return this.date;
    }
    protected int getDuree(){
        return this.duree;
    }
    protected boolean getEnEquipe(){
        return this.enEquipe;
    }
    
    /*
     Setters
    */
    protected void setNom( String nom ){
        this.nom = nom;
    }
    protected void setDate( Calendar date ){
        this.date = date;
    }
    protected void setDuree( int duree ){
        this.duree = duree;
    }
    protected void setEnEquipe( boolean enEquipe){
        this.enEquipe = enEquipe;
    }
}
