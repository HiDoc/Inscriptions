package presentation.cli.menu;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import application.inscriptions.Inscriptions;
import application.inscriptions.Main;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.ActionListe;
import utilitaires.ligneDeCommande.Liste;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

import data.hibernate.Competition;

public class MenuCompetition {
	
	private static Competition competition;

	public static Menu getMenu(String name)
	{
		Menu competMenu = new Menu(name,"c");
		competMenu.ajoute(getOptionAdd());
		competMenu.ajoute(getActionShow());
		competMenu.ajoute(getOptionEdit());
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
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("Nom :");
				String name = MainMenu.scanner.next();
				System.out.println("Date(yyyy/mm/jj) :");
				String strDate = MainMenu.scanner.next();
				Calendar date = Main.parseDate(strDate);
				System.out.println("Durée :");
				String strDuree = MainMenu.scanner.next();
				int duree = Integer.parseInt(strDuree);
				System.out.println("En équipe(o/n) :");
				String team = MainMenu.scanner.next();
				boolean boolTeam = (team) == "y" ? true : false;
				MainMenu.inscriptions.makeCompetition(name, date, duree, boolTeam);
			}
		};
	}
	

	
	 
	private static Liste getActionShow()
	{
		ArrayList<String> compets = new ArrayList<>();
		compets.add("voley");
		compets.add("foot");
		compets.add("hand");
		
//		Liste<String> menu = new Liste<String>("Liste des Competitions","l", new ActionListe<String>()
//		{
//			// Retourne la liste des personnes formant le menu
//			public List<String> getListe()
//			{
//				return compets;
//			}
//
//			// Exécutée automatiquement lorsqu'un élément de liste est sélectionné
//			public void elementSelectionne(int indice, String element)
//			{
//				System.out.println("Vous avez sélectionné "+ element+ ", qui a l'indice " + indice);
//				
//			}
//
//			// Retourne l'option que l'on souhaite créer, null si l'on préfère que l'option soit 
//			// crée automatiquement
//			public Menu getOption(final String element)
//			{
//				return null;
//			}
//		});
		Liste<String> menu = getListeCompet(compets);
		menu.ajouteRevenir("r");
		return menu;
	}
	
	private static Liste<String> getListeCompet(final List<String> compets)
	{
		Liste<String> liste = new Liste<>("Effacer une compétition", "d", 
				getActionListePersonnes(compets));
		return liste;
	}
	
	private static ActionListe<String> getActionListePersonnes(final List<String> compets)
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
				// Crée une option, le raccourci est laissé null car il sera écrasé par l'indice
				return new Option("Effacer " + compet, null, new Action()
				{
					// Action exécutée si l'option est sélectionnée.
					public void optionSelectionnee()
					{
						compets.remove(compet);
						
					}
				});
			}
		};
	}
	
	private static Option getOptionEdit()
	{
		return new Option("Editer une compétition","e",getActionEdit());
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
}
