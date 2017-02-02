/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
import java.util.HashSet;
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
 *
 * @author Flo
 */
@Entity
@Table(name = "candidat")
@Inheritance(strategy = InheritanceType.JOINED)
public class Candidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name = "id_ca")
    protected int id_ca;

    @Column(name = "nom")
    private String nom;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "appartenir", joinColumns = { @JoinColumn(name = "id_ca") }, inverseJoinColumns = { @JoinColumn(name = "id_user") })
    private final Set<Competition> competition = new HashSet<>(0);
    public Set<Competition> getCompetition() {
            return this.competition;
    }
    
    /**
     * Constructeur par défault
     */
    public Candidat() {
        this.nom = "default";
    }

    /**
     * Constructeur
     * @param nom
     */
    public Candidat(String nom) {
        this.nom = nom;
    }
    
    /**
     * Setter
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     * @return nom
     */
    public String getNom(){
        return this.nom;
    }
    
    
}
