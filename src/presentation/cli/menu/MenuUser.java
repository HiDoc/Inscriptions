package presentation.cli.menu;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuUser {
	
	
	public static Menu getMenu(String name)
	{
		Menu userMenu = new Menu(name,"p");
		userMenu.ajoute(getOptionAdd());
		userMenu.ajoute(getOptionShow());
		userMenu.ajoute(getOptionEdit());
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
		return new Option("Afficher une Personne","f",getActionShow());
	}
	
	private static Action getActionShow()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : afficher personnes");
				//MainMenu.inscriptions.afficherUser();
			}
		};
	}
	
	private static Option getOptionEdit()
	{
		return new Option("Editer une Personne","e",getActionEdit());
	}
	
	private static Action getActionEdit()
	{
		return new Action()
		{
			public void optionSelectionnee()
			{
				System.out.println("point d'arrivée : editer personne");
				//MainMenu.inscriptions.editerUser();
			}
		};
	}
}
