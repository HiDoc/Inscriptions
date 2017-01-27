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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Flo
 */
@Entity
@Table(name = "candidat")
class Candidat implements Serializable
{
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name = "id_ca")
 private int id_ca;

 @Column(name = "nom")
 private String nom;

 public Candidat(String nom)
 {
  this.nom = nom;
 }
}

public class hibernate {
 private static Session getSession() throws HibernateException
 {
  Configuration configuration = new Configuration()
    .configure("hibernate/hibernatePremierExemple/hibernatePremierExemple.cfg.xml");
  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    .applySettings(configuration.getProperties()).build();
  SessionFactory sessionFactory = configuration
    .buildSessionFactory(serviceRegistry);
  return sessionFactory.openSession();
 }

 public static void main(String[] args)
 {
  try
  {
   Session s = getSession();
   Candidat joffrey = new Candidat("Joffrey");
   Transaction t = s.beginTransaction();
   s.persist(joffrey);
   t.commit();
   s.close();
  }
  catch (HibernateException ex)
  {
   throw new RuntimeException("Probleme de configuration : "
     + ex.getMessage(), ex);
  }
 }   
}
