

import com.skillbox.airport.Airport;

public class Loader
{
	
	public static void main(String[] args) {
		Airport airport = Airport.getInstance();
		System.out.printf("В аэропорту находится %d самолетов", airport.getAllAircrafts().size());
		
	}
}
