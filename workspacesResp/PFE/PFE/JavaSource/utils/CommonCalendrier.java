package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonCalendrier {

	public static java.util.Date nowTime() {
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy ");
		java.util.Date now=null;
		Calendar cal= Calendar.getInstance();
		try {
			now= dateFormat.parse(dateFormat.format(cal.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return now;
	}
	public static String formdate(Date date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String d=dateFormat.format(date);
			return d;
			
	}
	public static Date form(String  date) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date d=dateFormat.parse(date);
			return d;
			
	}
}
