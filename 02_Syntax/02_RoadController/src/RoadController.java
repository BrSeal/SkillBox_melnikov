import core.Camera;
import core.Car;

import java.util.Scanner;

/**
 * Программа считывает с консоли количество машин, затем генерирует эти машины с разными параметрами и
 * либо пропускает их через КПП и выводит стоимость проезда , либо нет. Спецтранспорт пропускается по
 * определению, если он влезает в габариты.
 * Если не пропускает, то пишет причину по которой не пропускает
 */

public class RoadController
{
	private static double passengerCarMaxWeight = 3500.0; // kg
	private static int passengerCarMaxHeight = 2000; // mm
	
	// controllerMaxHeight - высота КПП, меняем ее с 4 на 3,5 метра
	private static int controllerMaxHeight = 3500; // mm
	
	private static int passengerCarPrice = 100; // RUB
	private static int cargoCarPrice = 250; // RUB
	private static int vehicleAdditionalPrice = 200; // RUB
	
	public static void main(String[] args) {
		System.out.println("Сколько автомобилей сгенерировать?");
		
		Scanner scanner = new Scanner(System.in);
		int carsCount = scanner.nextInt();
		
		for (int i = 0; i < carsCount; i++) {
			Car car = Camera.getNextCar();
			System.out.println(car);
			
			//Пропускаем автомобили спецтранспорта бесплатно
			//исправляем ошибку: если спецтранспорт выше чем высота КПП, то не пропускаем
			if (car.isSpecial() && car.getHeight() <= controllerMaxHeight) {
				openWay();
				continue;
			}
			
			
			//Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
			int price = calculatePrice(car);
			if (price == -1) {
				continue;
			}
			
			System.out.println("Общая сумма к оплате: " + price + " руб.");
		}
	}
	
	/**
	 * Расчёт стоимости проезда исходя из массы и высоты
	 */
	private static int calculatePrice(Car car) {
		int carHeight = car.getHeight();
		int price;
		
		//перенесено из блока if-else, так как пассажирская машина должна быть ниже грузовой и весить меньше грузовой
		double carWeight = car.getWeight();
		if (carHeight > controllerMaxHeight) {
			blockWay("высота вашего ТС превышает высоту пропускного пункта!");
			return -1;
		}
		
		// добавляем проверку на грузовую машину по высоте ИЛИ по весу
		else if (carHeight > passengerCarMaxHeight || carWeight > passengerCarMaxWeight) {
			//Грузовой автомобиль
			
			//ошибка: перепутаны местами цены на грузовой и легковой автомобиль
			price = cargoCarPrice;
		}
		//легковой автомобиль
		else {
			price = passengerCarPrice;
		}
		//добавляем проверку на прицеп
		if (car.isHasVehicle()) {
			price = price + vehicleAdditionalPrice;
		}
		return price;
	}
	
	/**
	 * Открытие шлагбаума
	 */
	private static void openWay() {
		System.out.println("Шлагбаум открывается... Счастливого пути!");
	}
	
	/**
	 * Сообщение о невозможности проезда
	 */
	private static void blockWay(String reason) {
		System.out.println("Проезд невозможен: " + reason);
	}
}