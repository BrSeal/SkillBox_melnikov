package module02.task01_1.RoadController.core;

public class Camera
{
    public static Car getNextCar()
    {
        String randomNumber = Double.toString(Math.random()).substring(2, 5);// объявляем строковую переменную
        int randomHeight = (int) (1000 + 3500. * Math.random());//объявляем целочисленную переменную
        double randomWeight = 600 + 10000 * Math.random();//объявляем дробную переменную

        Car car = new Car();
        car.setNumber(randomNumber);
        car.setHeight(randomHeight);
        car.setWeight(randomWeight);
        car.setHasVehicle(Math.random() > 0.5);
        car.setSpecial(Math.random() < 0.15);

        return car;
    }
}