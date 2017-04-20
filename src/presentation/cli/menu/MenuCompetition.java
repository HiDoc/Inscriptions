package presentation.cli.menu;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import application.inscriptions.Main;
import utilitaires.ligneDeCommande.*;


public class MenuCompetition {
	
	public static Menu getMenu(String name)
	{
		Menu competMenu = new Menu(name,"c");
		competMenu.ajoute(getOptionAdd());
		competMenu.ajoute(getActionShow());
		competMenu.ajouteRevenir("r");
		competMenu.ajouteQuitter("q");
		return competMenu;
	}
	
	private static Option getOptionAdd()
	{
		return new Option("Ajouter une compétition","a",getActionAdd());
	}
	
	private static Action getActionAdd()
	{
		return () -> {
                    System.out.println("Nom :");
                    String name = MainMenu.scanner.next();
                    //System.out.println("Date(yyyy/mm/jj) :");
                    //String strDate = MainMenu.scanner.next();
                    //Calendar date = Main.parseDate(strDate);
                    System.out.println("Durée :");
                    String strDuree = MainMenu.scanner.next();
                    int duree = Integer.parseInt(strDuree);
                    System.out.println("En équipe(o/n) :");
                    String team = MainMenu.scanner.next();
                    boolean boolTeam = "y".equals((String)team);
                };
	}
	

	
	 
	private static Liste<String> getActionShow()
	{
		ArrayList<String> compets = new ArrayList<>();
		compets.add("voley");
		compets.add("foot");
		compets.add("hand");
		Liste<String> menu = getListeCompet(compets);
		menu.ajouteRevenir("r");
		return menu;
	}
	
	private static Liste<String> getListeCompet(final List<String> compets)
	{
		Liste<String> liste = new Liste<>("Sélectionner une compétition", "s", 
				getActionListeCompets(compets));
		return liste;
	}
	
	private static ActionListe<String> getActionListeCompets(final List<String> compets)
	{
		return new ActionListe<String>()
		{
			// Retourne les éléments affichés dans le menu.
			public List<String> getListe()
			{
				return compets;
			}

			// Vide, car on souhaite créer manuellement chaque option.
			public void elementSelectionne(int indice, String element){}

			// Retourne l'option associée à element.
			public Option getOption(final String compet)
			{

				Menu menuCompet = new Menu("Option pour "+compet, null);
				menuCompet.ajoute(getEditCompetOption(compet));
				menuCompet.ajoute(getDeleteCompetOption(compets, compet));
				menuCompet.ajoute(getShowCandidatsOption(compet));
//				menuCompet.setRetourAuto(true);
				menuCompet.ajouteRevenir("r");
				return menuCompet;
						
			}
		};
	}
	
	private static Option getDeleteCompetOption(List<String> compets, String compet)
	{
		return new Option("Effacer la compétition", "d",getDeleteCompetAction(compets, compet));
	}
	
	private static Action getDeleteCompetAction(List<String> compets, String compet)
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				compets.remove(compet);
				
			}
		};
	}
	
	private static Option getEditCompetOption(String compet)
	{
		return new Option("Editer la compétition","e",getActionEdit());
	}
	
	private static Action getActionEdit()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : editer compétition");
				//MainMenu.inscriptions.editerUser();
			}
		};
	}
	

	private static Option getShowCandidatsOption(String compet)
	{
		ArrayList<String> candidats = new ArrayList<>();
		candidats.add("Joffrey");
		candidats.add("Nick Fury");
		candidats.add("Batman");
		Liste<String> menu = getListeCandidats(candidats);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(false);
		return menu;
		
	}
	
	private static Liste<String> getListeCandidats(final List<String> candidats)
	{
		Liste<String> liste = new Liste<>("Afficher les candidats", "a", 
				getActionListeCandidats(candidats));
		return liste;
	}
	
	private static ActionListe<String> getActionListeCandidats(final List<String> candidats)
	{
		return new ActionListe<String>()
		{
			// Retourne les éléments affichés dans le menu.
			public List<String> getListe()
			{
				return candidats;
			}

			// Vide, car on souhaite créer manuellement chaque option.
			public void elementSelectionne(int indice, String element){}

			// Retourne l'option associée à element.
			public Option getOption(final String candidat)
			{

				Menu menuCompet = new Menu("Option pour "+candidat, null);
				menuCompet.ajoute(getAddCandidatOption(candidat));
				menuCompet.ajoute(getDeleteCandidatOption(candidats, candidat));
				menuCompet.setRetourAuto(true);
				menuCompet.ajouteRevenir("r");
				return menuCompet;
						
			}
		};
	}
	
	private static Option getAddCandidatOption(String candidat)
	{
		return new Option("Ajouter le candidat à la compétition","a",getAddCandidatAction());
	}
	
	private static Action getAddCandidatAction()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("Point d'arrivée : Ajouter candidat à une compétition");
			}
		};
	}
	
	private static Option getDeleteCandidatOption(List<String> candidats, String candidat)
	{
		return new Option("Enlever le candidat de la compétition","e",getDeleteCandidatAction(candidats, candidat));
	}
	
	private static Action getDeleteCandidatAction(List<String> candidats, String candidat)
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				candidats.remove(candidat);
				System.out.println("Point d'arrivée : enlever un candidat d'une compétition");
			}
		};
	}
}
