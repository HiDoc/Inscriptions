package presentation.cli.menu;

import application.inscriptions.Inscriptions;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuTeam {

	public static Menu getMenu(String name)
	{
		Menu teamMenu = new Menu(name,"e");
		teamMenu.ajoute(getOptionAdd());
		teamMenu.ajoute(getOptionShow());
		teamMenu.ajoute(getOptionEdit());
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
				//MainMenu.inscriptions.ajouterUser();
			}
		};
	}
	
	private static Option getOptionShow()
	{
		return new Option("Afficher une équipe","f",getActionShow());
	}
	
	private static Action getActionShow()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : afficher équipe");
				//MainMenu.inscriptions.afficherUser();
			}
		};
	}
	
	private static Option getOptionEdit()
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
				//MainMenu.inscriptions.editerUser();
			}
		};
	}
}
