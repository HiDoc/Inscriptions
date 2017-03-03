package presentation.cli.menu;

import java.util.Scanner;

import application.inscriptions.Inscriptions;
import commandLine.*;

public class MainMenu {	
	
	static Scanner scanner = new Scanner(System.in);
	
	static Inscriptions inscriptions;
	
	public static Menu getMenuUsers()
	{
		return MenuUser.getMenu("Personnes");
	}
	
	public static Menu getMenuTeams()
	{
		return MenuTeam.getMenu("Equipes");
	}
	
	public static Menu getMenuCompetitions()
	{
		return MenuCompetition.getMenu("Competitions");
	}
	
	public static Menu getMainMenu()
	{
		Menu menu = new Menu("Menu Principal");
		menu.ajoute(getMenuUsers());
		menu.ajoute(getMenuTeams());
		menu.ajoute(getMenuCompetitions());
		menu.ajouteQuitter("q");
		return menu;
	}
	
	public MainMenu(Inscriptions inscriptions) {
		MainMenu.inscriptions = inscriptions;
		Menu menu = getMainMenu();
		menu.start();
	}
}
