/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.inscriptions;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author Flo
 */
@Entity
@Immutable
@Table(name ="equipe")
@IdClass(Id.class)
public class Equipe extends Candidat implements Serializable {

    @Id
    @Column(name="id_ca")
    @OneToOne
    @JoinColumn(
        name="Candidat",
        referencedColumnName="id_ca")
    private int id;
    
    @Column(name="nom")
    private String nom;
    
    
}
