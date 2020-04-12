package core;

public class Car {
    private String number; // объявляем строковую переменную
    private int height;      //объявляем целочисленную переменную
    private double weight;   //объявляем дробную переменную
    private boolean hasVehicle;//объявляем булеву переменную
    private boolean isSpecial;//объявляем булеву переменную

    public String getNumber() {
        return number;
    }

    void setNumber(String number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHasVehicle() {
        return hasVehicle;
    }

    void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    void setSpecial(boolean special) {
        isSpecial = special;
    }

    public String toString() {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";// объявляем строковую переменную
        return "\n=========================================\n" + special + "Автомобиль с номером " + number + ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг" + (hasVehicle ? " с прицепом" : "");
    }
}