package presentation.cli.menu;

import application.inscriptions.Inscriptions;
import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MainMenu {	
	
	static Inscriptions inscriptions;
	
	public static Menu getMenuUsers()
	{
		return MenuUser.getMenu("Personnes", inscriptions);
	}
	
	public static Menu getMenuTeams()
	{
		return MenuTeam.getMenu("Equipes", inscriptions);
	}
	
	public static Menu getMenuCompetitions()
	{
		return MenuCompetition.getMenu("Competitions", inscriptions);
	}
	
	public static Menu getMainMenu()
	{
		Menu menu = new Menu("Menu Principal");
		menu.ajoute(getMenuUsers());
		menu.ajoute(getMenuTeams());
		menu.ajoute(getMenuCompetitions());		
		return menu;
	}
	
	public MainMenu(Inscriptions inscriptions) {
		this.inscriptions = inscriptions;
		Menu menu = getMainMenu();
		menu.start();
	}
}
