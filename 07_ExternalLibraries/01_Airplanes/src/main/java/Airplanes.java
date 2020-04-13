import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Используя библиотеку airport.jar, распечатать время вылета и модели самолётов,
// вылетающие в ближайшие 2 часа.
public class Airplanes
{
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar afterCalendar = Calendar.getInstance();
		Date now = Calendar.getInstance().getTime();
		afterCalendar.add(Calendar.HOUR, 2);
		Date before = afterCalendar.getTime();
		
		Airport.getInstance().getTerminals()
                .stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(f ->
                        f.getDate().before(before) &&
                        f.getDate().after(now) &&
                        f.getType().equals(Flight.Type.DEPARTURE))
                
                
                .forEach(f -> System.out.println(sdf.format(f.getDate()) + " " + f.getAircraft()));
	}
}

