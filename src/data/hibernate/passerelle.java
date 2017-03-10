/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.hibernate;

/**
 *
 * @author Armand
 */
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class passerelle {

    private static Session session = null;
    private static SessionFactory sessionFactory = null;
    private static final String CONF_FILE = "data/hibernate/database.cfg.xml";

    static {
        try {
            Configuration configuration = new Configuration()
                    .configure(CONF_FILE);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            throw new RuntimeException("Probleme de configuration : "
                    + ex.getMessage(), ex);
        }
    }

    /**
     * Ouvre un nouvel objet Session
     */
    public static void open() {
        session = sessionFactory.openSession();
    }

    /**
     * Ferme l'objet session
     */
    public static void close() {
        session.close();
    }

    public static void delete(Object o) {
        Transaction tx = session.beginTransaction();
        session.delete(o);
        tx.commit();
    }

    public static void save(Object o) {
        Transaction tx = session.beginTransaction();
        session.save(o);
        tx.commit();
    }

    /**
     * Enregistre l'objet dans la base de données et renvoie l'ID de la bdd
     * @param o
     * @return int l'ID de l'objet
     */
    public static int saveAndId(Object o) {
        Transaction tx = session.beginTransaction();
        int id = (int)session.save(o);
        tx.commit();
        return id;
    }

    /**
     * Compte le nombre d'objets dans une table
     * @param className une chaine de caractère
     * @return le nombre d'entrée dans une table spécifiée
     */
    public static int count(String className) {
        Query query = session.createQuery("from " + className);
        return query.list().size();
    }
    /**
     * Recherche dans les tables une entrée avec un ID
     * @param <T>
     * @param o un objet
     * @param id un nombre
     * @return un objet spécifique sélectionné par son id
     */
    public static <T> Object select(Object o, int id) {
        return session.get(o.getClass(), id);
    }

    /**
     * Selectionne une table par rapport à l'objet mis en paramètre
     * @param <T>
     * @param o un objet
     * @return une liste d'objet de la classe de l'objet spécifié en paramètre
     */
    public static <T> List<T> table(Object o){
        return session.createCriteria(o.getClass()).list();
    }
    /**
     * Surcharge de la méthode table
     * @param <T>
     * @param className
     * @return une liste d'objet de la classe spécififée en chaine de caractères
     */
    public static <T> List<T> table(String className) {
        Query query = session.createQuery("from " + className);
        return new ArrayList<>((List<T>) query.list());
    }
    /**
     * Accès à la vue équipe
     * @param id
     * @return boolean
     */
    public static boolean isEquipe(int id){
        Query query = session.createQuery("from equipe where id_ca = " + id);
        return query.list().size() > 0;
    }
}