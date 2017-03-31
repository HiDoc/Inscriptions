package application.inscriptions;
import data.hibernate.passerelle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import presentation.cli.menu.MainMenu;

public class Main {
	passerelle passerelle = new passerelle();     

	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu(Inscriptions.getInscriptions());
	}
}
