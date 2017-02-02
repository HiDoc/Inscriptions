/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.awt.List;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "competition")
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
