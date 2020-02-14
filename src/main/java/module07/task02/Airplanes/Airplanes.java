package module07.task02.Airplanes;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//Используя библиотеку airport.jar, распечатать время вылета и модели самолётов,
// вылетающие в ближайшие 2 часа.
public class Airplanes {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar afterCalendar = Calendar.getInstance();
        Date now = Calendar.getInstance().getTime();
        afterCalendar.add(Calendar.HOUR, 2);
        Date before = afterCalendar.getTime();

        List<Terminal> terminals = Airport.getInstance().getTerminals();

        terminals.stream()
                .flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getDate().before(before) && f.getDate().after(now) && f.getType().equals(Flight.Type.DEPARTURE))
                .forEach(f -> System.out.println(sdf.format(f.getDate()) + " " + f.getAircraft()));


    }
}

