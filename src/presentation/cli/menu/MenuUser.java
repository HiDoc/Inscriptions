package presentation.cli.menu;

import java.util.ArrayList;
import java.util.List;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.ActionListe;
import utilitaires.ligneDeCommande.Liste;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuUser {
	
	
	public static Menu getMenu(String name)
	{
		Menu userMenu = new Menu(name,"p");
		userMenu.ajoute(getOptionAdd());
		userMenu.ajoute(getOptionShow());
		userMenu.ajouteRevenir("r");
		userMenu.ajouteQuitter("q");
		return userMenu;
	}
	
	private static Option getOptionAdd()
	{
		return new Option("Ajouter une personne","a",getActionAdd());
	}
	
	private static Action getActionAdd()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : ajouter personne");
				//MainMenu.inscriptions.ajouterUser();
			}
		};
	}
	
	private static Option getOptionShow()
	{
		ArrayList<String> users = new ArrayList<>();
		users.add("Joffrey");
		users.add("Nick Fury");
		users.add("Batman");
		Liste<String> menu = getListeUsers(users);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(false);
		return menu;
		
	}
	
	private static Liste<String> getListeUsers(final List<String> users)
	{
		Liste<String> liste = new Liste<>("Sélectionner un utilisateur", "s", 
				getActionListeUsers(users));
		return liste;
	}
	
	private static ActionListe<String> getActionListeUsers(final List<String> users)
	{
		return new ActionListe<String>()
		{
			// Retourne les éléments affichés dans le menu.
			public List<String> getListe()
			{
				return users;
			}

			// Vide, car on souhaite créer manuellement chaque option.
			public void elementSelectionne(int indice, String element){}

			// Retourne l'option associée à element.
			public Option getOption(final String user)
			{

				Menu menuCompet = new Menu("Option pour "+user, null);
				menuCompet.ajoute(getEditUserOption(user));
				menuCompet.ajoute(getDeleteUserOption(users, user));
				menuCompet.ajoute(addUserToCompetOption(user));
				menuCompet.ajoute(addUserToTeamOption(user));
				menuCompet.setRetourAuto(true);
				menuCompet.ajouteRevenir("r");
				return menuCompet;
						
			}
		};
	}

	private static Option addUserToCompetOption(String user)
	{
		ArrayList<String> compets = new ArrayList<>();
		compets.add("Voley");
		compets.add("Curling");
		compets.add("Foot");
		Liste<String> menu = getListeCompets(compets);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(true);
		return menu;
	}
	
	private static Liste<String> getListeCompets(final List<String> compets)
	{
		Liste<String> liste = new Liste<>("Ajouter à une compétition", "c", 
				getActionListeCompets(compets));
		return liste;
	}
	
	private static ActionListe<String> getActionListeCompets(List<String> compets)
	{
		
		return new ActionListe<String>()
		{
			public List<String> getListe()
			{
				return compets;
			}

			public void elementSelectionne(int indice, String element){}
			
			public Option getOption(final String compet)
			{
				// Crée une option, le raccourci est laissé null car il sera écrasé par l'indice
				return new Option(compet, null, new Action()
				{
					// Action exécutée si l'option est sélectionnée.
					public void optionSelectionnee()
					{
						System.out.println("point d'arrivé : ajouter une personne à une compétition");
					}
				});
			}
		};
	}
	
	private static Option addUserToTeamOption(String user)
	{
		ArrayList<String> teams = new ArrayList<>();
		teams.add("Team Death Star");
		teams.add("Fuck it YOLO");
		teams.add("Team Captain Planet");
		Liste<String> menu = getListeTeams(teams);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(true);
		return menu;
	}
	
	private static Liste<String> getListeTeams(final List<String> Teams)
	{
		Liste<String> liste = new Liste<>("Ajouter a une équipe", "t", 
				getActionListeTeams(Teams));
		return liste;
	}
	
	private static ActionListe<String> getActionListeTeams(List<String> Teams)
	{
		
		return new ActionListe<String>()
		{
			public List<String> getListe()
			{
				return Teams;
			}

			public void elementSelectionne(int indice, String element){}
			
			public Option getOption(final String team)
			{
				return new Option(team, null, new Action()
				{
					public void optionSelectionnee()
					{
						System.out.println("point d'arrivé : ajouter une personne à une équipe");
					}
				});
			}
		};
	}
	
	private static Option getDeleteUserOption(List<String> users, String user)
	{
		return new Option("Effacer l'utilisateur", "d",getDeleteCompetAction(users, user));
	}
	
	private static Action getDeleteCompetAction(List<String> users, String user)
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				users.remove(user);
				
			}
		};
	}
	
	private static Option getEditUserOption(String user)
	{
		return new Option("Editer l'utilisateur","e",getActionEdit());
	}
	
	private static Action getActionEdit()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : editer utilisateur");
				//MainMenu.inscriptions.editerUser();
			}
		};
	}
}
