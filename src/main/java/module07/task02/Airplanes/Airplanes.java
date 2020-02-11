package module07.task02.Airplanes;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Используя библиотеку airport.jar, распечатать время вылета и модели самолётов, вылетающие в ближайшие 2 часа.
public class Airplanes
{
	public static void main(String[] args)
	{
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		Calendar afterCalendar = Calendar.getInstance();
		Date now=Calendar.getInstance().getTime();
		afterCalendar.add(Calendar.HOUR,2);
		Date before=afterCalendar.getTime();
		
		Airport airport = Airport.getInstance();
		airport.getTerminals().forEach(terminal->
				terminal.getFlights().stream().filter(f ->
					f.getType().equals(Flight.Type.DEPARTURE) &&
							f.getDate().before(before)&&
							f.getDate().after(now))
					.forEach(flight ->
							System.out.println(hourFormat.format(flight.getDate())+" "+ flight.getAircraft())));
		
	}
}

