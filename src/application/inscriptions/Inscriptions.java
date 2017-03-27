package application.inscriptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
    private SortedSet<Candidat> equipes = new TreeSet<>();
    private SortedSet<Users> users = new TreeSet<>();
    
    public Inscriptions() {
        passerelle.open();
        this.competitions = getSort((ArrayList) passerelle.table(Competition.class));
        this.candidats = getSort((ArrayList) passerelle.table(Candidat.class));
        this.equipes = getSort((ArrayList) passerelle.table(Equipe.class));
        this.users = getSort((ArrayList) passerelle.table(Users.class));
        passerelle.close();
    }

    /**
     * Retourne les compétitions.
     *
     * @return
     */
    public SortedSet<Competition> getCompetitions() {
        return Collections.unmodifiableSortedSet(competitions);
    }

    /**
     * Retourne tous les candidats (personnes et équipes confondues).
     *
     * @return
     */
    public SortedSet<Candidat> getCandidats() {
        return Collections.unmodifiableSortedSet(candidats);
    }

    /**
     * Retourne toutes les personnes.
     *
     * @return
     */
    public SortedSet<Users> getPersonnes() {
    	return Collections.unmodifiableSortedSet(users);
//        return getSort((ArrayList) passerelle.table(Users.class));
    }

    /**
     * Retourne toutes les équipes.
     * @return Un SortedSet
     */
    public SortedSet<Candidat> getEquipes() {
        return Collections.unmodifiableSortedSet(this.equipes);
    }

    /**
     * Créée une compétition. Ceci est le seul moyen, il n'y a pas de
     * constructeur public dans {@link Competition}.
     *
     * @param nom
     * @param dateDebut
     * @param dateCloture
     * @param duree
     * @param enEquipe
     */
    public void createCompetition(String nom, Calendar dateDebut, Calendar dateCloture,int duree, boolean enEquipe) {
        Competition newC = new Competition(nom, dateDebut, dateCloture, duree, enEquipe);
        this.competitions.add(newC);
        passerelle.save(newC);
    }
    
    public Competition createCompetition(String nom, String dateDebut, int duree, boolean enEquipe) {
    	Calendar debut = this.parseDate(dateDebut);
    	Calendar fin = debut;
    	fin.add(Calendar.DATE, duree);
        Competition newC = new Competition(nom, debut, fin,duree, enEquipe);
        this.competitions.add(newC);
        passerelle.open();
        passerelle.save(newC);
        passerelle.close();
        return newC;
    }
    
    /**
     * Surcharge du constructeur de la création de compétition
     * @param newC
     */
    public void createCompetition(Competition newC) {
        this.competitions.add(newC);
        passerelle.save(newC);
    }

    /**
     * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
     * de constructeur public dans {@link Personne}.
     *
     * @param nom
     * @param prenom
     * @param mail
     * @param niveau
     */
    public Users createPersonne(String nom, String prenom, String mail, int niveau) {
    	passerelle.open();
    	Users personne = new Users(nom, prenom, niveau, mail);
        this.candidats.add(personne);
        this.users.add(personne);
        passerelle.save(personne);
        passerelle.close();
        return personne;
    }

    /**
     * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
     * de constructeur public dans {@link Equipe}.
     *
     * @param nom
     */
    public void createEquipe(String nom) {
        Candidat equipe = new Candidat(nom);
        this.equipes.add(equipe);
        passerelle.save(equipe);
    }

    void remove(Competition competition) {
        competitions.remove(competition);
        passerelle.delete(competition);
    }

    void remove(Candidat candidat) {
        candidats.remove(candidat);
        passerelle.delete(candidat);
    }
    
    public void remove(Users user) {
        candidats.remove(user);
        users.remove(user);
        passerelle.open();
        passerelle.delete(user);
        passerelle.close();
    }
    
    public void edit(Users user) {
    	passerelle.open();
    	passerelle.update(user);
    	passerelle.close();
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

	private Calendar parseDate (String strDate)
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try
		{
			date = sdf.parse(strDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return Calendar.getInstance();	
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
}
