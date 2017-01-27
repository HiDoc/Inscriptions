/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

import java.io.Serializable;
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
@Table(name = "candidat")
public class Candidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ca")
    private int id_ca;

    @Column(name = "nom")
    private String nom;

    public Candidat() {
        this.nom = "default";
    }

    public Candidat(String nom) {
        this.nom = nom;
    }
}
