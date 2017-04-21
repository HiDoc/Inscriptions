package application.inscriptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;


import data.hibernate.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription permet
 * de gérer les compétitions, candidats (de type equipe ou personne) ainsi que
 * d'inscrire des candidats à des compétition.
 */
public class Inscriptions implements Serializable {

    private static final long serialVersionUID = -3095339436048473524L;
    private static final String FILE_NAME = "Inscriptions.srz";
    private static Inscriptions inscriptions;

    private SortedSet<Competition> competitions = new TreeSet<>();
    private SortedSet<Candidat> candidats = new TreeSet<>();
    private SortedSet<Equipe> equipes = new TreeSet<>();
    private SortedSet<Users> users = new TreeSet<>();
    
    public Inscriptions() {
        passerelle.open();
        this.competitions = getSort((ArrayList) passerelle.table(Competition.class));
        this.candidats = getSort((ArrayList) passerelle.table(Candidat.class));
        this.equipes = getSort((ArrayList) passerelle.table(Equipe.class));
        this.users = getSort((ArrayList) passerelle.table(Users.class));
    }

    /**
     * Retourne les compétitions.
     *
     * @return une collection composée de toutes les compétitions
     */
    public SortedSet<Competition> getCompetitions() {
        return Collections.unmodifiableSortedSet(competitions);
    }

    /**
     * Retourne tous les candidats (personnes et équipes confondues).
     *
     * @return une collection composée de tout les candidats
     */
    public SortedSet<Candidat> getCandidats() {
        return Collections.unmodifiableSortedSet(candidats);
    }

    /**
     * Retourne toutes les personnes.
     *
     * @return une collections de tout les utilisateurs 
     */
    public SortedSet<Users> getPersonnes() {
    	return Collections.unmodifiableSortedSet(users);
    }

    /**
     * Retourne toutes les équipes.
     * @return une collection composée de toutes les équipes
     */
    public SortedSet<Equipe> getEquipes() {
        return Collections.unmodifiableSortedSet(this.equipes);
    }

    /**
     * Créée une compétition. Ceci est le seul moyen, il n'y a pas de
     * constructeur public dans {@link Competition}.
     *
     * @param nom le nom de la nouvelle compétition
     * @param dateDebut la date de début de la nouvelle compétition
     * @param dateCloture la date de fermeture des inscriptions de la nouvelle compétition
     * @param duree la durée en secondes de la compétition
     * @param enEquipe un booléen précisant si la compétition doit se faire en équipe
     */
    public void createCompetition(String nom, Calendar dateDebut, Calendar dateCloture,int duree, boolean enEquipe) {
        Competition newC = new Competition(nom, dateDebut, dateCloture, duree, enEquipe);
        this.competitions.add(newC);
        passerelle.save(newC);
    }
    
    public Competition createCompetition(String nom, Calendar dateDebut, int duree, boolean enEquipe) {
    	Calendar debut = dateDebut;
    	Calendar fin = (Calendar) debut.clone();
    	fin.add(Calendar.DATE, duree);
        Competition newC = new Competition(nom, debut, fin,duree, enEquipe);
        this.competitions.add(newC);
        passerelle.save(newC);
        return newC;
    }
    
    /**
     * Surcharge du constructeur de la création de compétition
     * @param newC un objet compétition qui constitue la nouvelle compétition à créer
     */
    public void createCompetition(Competition newC) {
        this.competitions.add(newC);
        passerelle.save(newC);
    }

    /**
     * Créée une Candidat de type utilisateur. 
     * 
     * @param nom le nom du nouvel utilisateur
     * @param prenom le prénom du nouvel utilisateur
     * @param mail l'adresse e-mail du nouvel utilisateur
     * @param niveau le niveau d'accès du nouvel utilisateur
     * @return un nouvel utilisateur à créer
     */
    public Users createPersonne(String nom, String prenom, String mail, int niveau) {
    	Users personne = new Users(nom, prenom, niveau, mail);
        this.candidats.add(personne);
        this.users.add(personne);
        passerelle.save(personne);
        return personne;
    }

    /**
     * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
     * de constructeur public dans {@link Equipe}.
     *
     * @param nom le nom de l'équipe
     * @return la nouvelle équipe à créer
     */
    public Equipe createEquipe(String nom) {
        Equipe equipe = new Equipe(nom);
        this.equipes.add(equipe);
        passerelle.save(equipe);
        return equipe;
    }

    /**
     * Enlève une compétition de l'application
     * @param competition la compétition à supprimer
     */
    public void remove(Competition competition) {
        competitions.remove(competition);
        passerelle.delete(competition);
    }

    void remove(Candidat candidat) {
        candidats.remove(candidat);
        passerelle.delete(candidat);
    }
    
    /**
     * Enlève un utilisateur de l'application
     * @param user l'utilisateur à supprimer
     */
    public void remove(Users user) {
        candidats.remove(user);
        users.remove(user);
        passerelle.delete(user);
    }

    /**
     * Enlève une équipe de l'application
     * @param team l'équipe à supprimer
     */
    public void remove(Equipe team) {
        candidats.remove(team);
        equipes.remove(team);
        passerelle.delete(team);
    }
    
    /**
     * Mets à jour un utilisateur
     * @param user l'utilisateur à modifier
     */
    public void edit(Users user) {
    	passerelle.update(user);
    }
    
    /**
     * Mets à jour une compétition 
     * @param compet la compétition à modifier
     */
    public void edit(Competition compet) {
    	passerelle.update(compet);
    }
    /**
     * Retourne l'unique instance de cette classe. Crée cet objet s'il n'existe
     * déjà.
     *
     * @return l'unique objet de type {@link Inscriptions}.
     */
    public static Inscriptions getInscriptions() {

        if (inscriptions == null) {
            inscriptions = readObject();
            if (inscriptions == null) {
                inscriptions = new Inscriptions();
            }
        }
        return inscriptions;
    }

    /**
     * Retourne un object inscriptions vide. Ne modifie pas les compétitions et
     * candidats déjà existants.
     *
     * @return
     */
    public Inscriptions reinitialiser() {
        inscriptions = new Inscriptions();
        return getInscriptions();
    }

    /**
     * Efface toutes les modifications sur Inscriptions depuis la dernière
     * sauvegarde. Ne modifie pas les compétitions et candidats déjà existants.
     *
     * @return
     */
    public Inscriptions recharger() {
        inscriptions = null;
        return getInscriptions();
    }

    private static Inscriptions readObject() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ois = new ObjectInputStream(fis);
            return (Inscriptions) (ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement lors
     * d'une exécution ultérieure du programme.
     *
     * @throws IOException
     */
    public void sauvegarder() throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fis = new FileOutputStream(FILE_NAME);
            oos = new ObjectOutputStream(fis);
            oos.writeObject(this);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    @Override
    public String toString() {
        return "Candidats : " + getCandidats().toString()
                + "\nCompetitions  " + getCompetitions().toString();
    }
    
    /**
     * Convertit une liste en sortedSet
     *
     * @param list
     * @return
     */
    public static TreeSet getSort(ArrayList list) {
        TreeSet set = new TreeSet(list);
        return set;
    }
    public boolean compareList(ArrayList a, ArrayList b){
        a.removeAll(b);
        return a.isEmpty();
    }
    
    public void refresh(Object o) {
    	passerelle.refresh(o);
    }
}
