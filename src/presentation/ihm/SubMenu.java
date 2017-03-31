package presentation.ihm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class SubMenu implements Comparable{
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected Calendar parseDate (String strDate)
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
}
