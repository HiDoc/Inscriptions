package presentation.cli.menu;

import java.util.ArrayList;
import java.util.List;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.ActionListe;
import utilitaires.ligneDeCommande.Liste;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuTeam {

	public static Menu getMenu(String name)
	{
		Menu teamMenu = new Menu(name,"e");
		teamMenu.ajoute(getOptionAdd());
		teamMenu.ajoute(getOptionShow());
		teamMenu.ajouteRevenir("r");
		teamMenu.ajouteQuitter("q");
		return teamMenu;
	}
	
	private static Option getOptionAdd()
	{
		return new Option("Ajouter une équipe","a",getActionAdd());
	}
	
	private static Action getActionAdd()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : ajouter equipe");
				//MainMenu.inscriptions.ajouterteam();
			}
		};
	}
	
	private static Option getOptionShow()
	{
		ArrayList<String> teams = new ArrayList<>();
		teams.add("Team Death Star");
		teams.add("Fuck it YOLO");
		teams.add("Team Captain Planet");
		Liste<String> menu = getListeteams(teams);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(false);
		return menu;
		
	}
	
	private static Liste<String> getListeteams(final List<String> teams)
	{
		Liste<String> liste = new Liste<>("Sélectionner une équipe", "s", 
				getActionListeteams(teams));
		return liste;
	}
	
	private static ActionListe<String> getActionListeteams(final List<String> teams)
	{
		return new ActionListe<String>()
		{
			public List<String> getListe()
			{
				return teams;
			}

			// Vide, car on souhaite créer manuellement chaque option.
			public void elementSelectionne(int indice, String element){}

			// Retourne l'option associée à element.
			public Option getOption(final String team)
			{

				Menu menuTeam = new Menu("Options pour "+team, null);
				menuTeam.ajoute(getOptionEdit(team));
				menuTeam.ajoute(getOptionDelete(teams, team));
				menuTeam.ajoute(showCompetsList(team));
				menuTeam.ajoute(showUsersList(team));
				menuTeam.setRetourAuto(true);
				menuTeam.ajouteRevenir("r");
				return menuTeam;
						
			}
		};
	}
	
	private static Liste<String> showCompetsList(String team)
	{
		ArrayList<String> compets = new ArrayList<>();
		compets.add("Volley");
		compets.add("Curling");
		compets.add("Foot");
		Liste<String> menu = getListCompets(compets);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(false);
		return menu;
	}
	
	private static Liste<String> getListCompets(List<String> compets)
	{
		Liste<String> liste = new Liste<>("Ajouter à une compétition","a",getActionListCompets(compets));
		return liste;
	}
	
	private static ActionListe<String> getActionListCompets(final List<String> compets)
	{
		return new ActionListe<String>()
		{

			@Override
			public List<String> getListe() {
				return compets;
			}

			@Override
			public void elementSelectionne(int indice, String element) {}

			@Override
			public Option getOption(String compet) {
				// TODO Auto-generated method stub
				return new Option(compet, null, new Action()
				{
					// Action exécutée si l'option est sélectionnée.
					public void optionSelectionnee()
					{
						System.out.println("point d'arrivé : ajouter une équipe à une compétition");
					}
				});
			}
			
		};
	}
	
	private static Option getOptionEdit(String team)
	{
		return new Option("Editer une équipe","e",getActionEdit());
	}
	
	private static Action getActionEdit()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : editer équipe");
				//MainMenu.inscriptions.editerteam();
			}
		};
	}
	
	private static Option getOptionDelete(List<String> teams, String team)
	{
		return new Option("Effacer une équipe", "d", getActionDelete());
	}
	
	private static Action getActionDelete()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : effacer équipe");
			}
		};
	}
	
	private static Option showUsersList(String compet)
	{
		ArrayList<String> users = new ArrayList<>();
		users.add("Joffrey");
		users.add("Nick Fury");
		users.add("Batman");
		Liste<String> menu = getListeusers(users);
		menu.ajouteRevenir("r");
		menu.setRetourAuto(false);
		return menu;
		
	}
	
	private static Liste<String> getListeusers(final List<String> users)
	{
		Liste<String> liste = new Liste<>("Afficher les utilisateurs", "u", 
				getActionListeusers(users));
		return liste;
	}
	
	private static ActionListe<String> getActionListeusers(final List<String> users)
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
				menuCompet.ajoute(getAdduserOption(user));
				menuCompet.ajoute(getDeleteuserOption(users, user));
				menuCompet.setRetourAuto(true);
				menuCompet.ajouteRevenir("r");
				return menuCompet;
						
			}
		};
	}
	
	private static Option getAdduserOption(String user)
	{
		return new Option("Ajouter l'utilisateur à l'équipe","a",getAddUserAction());
	}
	
	private static Action getAddUserAction()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("Point d'arrivée : Ajouter un utilisateur à une équipe");
			}
		};
	}
	
	private static Option getDeleteuserOption(List<String> users, String user)
	{
		return new Option("Enlever l'utilisateur de l'équipe","e",getDeleteUserAction(users, user));
	}
	
	private static Action getDeleteUserAction(List<String> users, String user)
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				users.remove(user);
				System.out.println("Point d'arrivée : enlever un utilisateur d'une équipe");
			}
		};
	}
	
}
