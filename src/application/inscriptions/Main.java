package application.inscriptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import presentation.cli.menu.MainMenu;

public class Main {
	
	public static Calendar parseDate (String strDate)
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

	public static void main(String[] args) {
		//System.out.println("hi");
		//Scanner scanner = new Scanner(System.in);
		//parseDate(scanner.next());
		// TODO Auto-generated method stub

		MainMenu mainMenu = new MainMenu(Inscriptions.getInscriptions());
	}

}
