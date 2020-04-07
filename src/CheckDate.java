package Assignment2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckDate {
	
	
	public String getDay(String date) {
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date MyDate;
		try {
			MyDate = newDateFormat.parse(date);
			newDateFormat.applyPattern("EEEE");
			String finalDay = newDateFormat.format(MyDate);
			return finalDay;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "00";
	}
}
