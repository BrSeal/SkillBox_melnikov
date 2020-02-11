package module02.task01_2.RoadController;

import module02.task01_1.RoadController.core.Camera;
import module02.task01_1.RoadController.core.Car;


import java.util.Scanner;

/**
 * Программа считывает с консоли количество машин, затем генерирует эти машины с разными параметрами и
 * либо пропускает их через КПП и выводит стоимость проезда , либо нет.
 * Если не пропускает, то пишет причину по которой не пропускает
 */

public class RoadController
{
    private static double passengerCarMaxWeight = 3500.0; // kg     инициализируем закрытую статическую дробную переменную двойной точности
    private static int passengerCarMaxHeight = 2000; // mm          инициализируем закрытую статическую целочисленную переменную
    
    // controllerMaxHeight - высота КПП, меняем ее с 4 на 3,5 метра
    private static int controllerMaxHeight = 3500; // mm            инициализируем закрытую статическую целочисленную переменную

    private static int passengerCarPrice = 100; // RUB              инициализируем закрытую статическую целочисленную переменную
    private static int cargoCarPrice = 250; // RUB                  инициализируем закрытую статическую целочисленную переменную
    private static int vehicleAdditionalPrice = 200; // RUB         инициализируем закрытую статическую целочисленную переменную

    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);                 //инстанцируем объект типа Scanner
        int carsCount = scanner.nextInt();                        //объявляем int-переменную и инициализируем ее числом, которое вернет нам метод nextInt объекта scanner

        for(int i = 0; i < carsCount; i++)     // инициализируется "дежурная" переменная i, на которой завязано условие выхода из цикла
        {
            Car car = Camera.getNextCar();  // инстанцируется локальный объект   класса Car
            System.out.println(car);
            
            if (car.isSpecial()&&car.getHeight() <= controllerMaxHeight)
            {
             
                openWay();
                continue;
            }
            
            
            int price = calculatePrice(car);  // объявляем переменную price и инициализируем ее результатом работы метода calculatePrice()
            if(price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        int carHeight = car.getHeight();         // объявляем целочисленную переменную carHeight и присваиваем ей значение переменной height объекта car
        int price; // объявляем локальную переменную price. Idea ругается на инициализацию, так как она переписывается другим значением во всех случаях
        
      
        double carWeight = car.getWeight(); // объявляем и инициализируем дробную переменную значением переменной weight объекта car
        if (carHeight > controllerMaxHeight)
        {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        }
        
     
        else if (carHeight > passengerCarMaxHeight||carWeight > passengerCarMaxWeight)
        {
            //Грузовой автомобиль
           
               
                price = cargoCarPrice;
                if (car.isHasVehicle()) {
                    price = price + vehicleAdditionalPrice;
                }
        }
        //легковой автомобиль
        else {
            price = passengerCarPrice;
            //добавляем проверку на прицеп
            if (car.isHasVehicle()) {
                price = price + vehicleAdditionalPrice;
            }
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}