package module03.task01.Cat;

public class Cat
{
    private double originWeight;
    private double weight;
    private Color color;

    private static final double MIN_WEIGHT=1000;
    private static final double MAX_WEIGHT=9000;
    private static final int EYE_COUNT=2;
    private static int count=0;
    private double foodEaten;
    private boolean isAlive;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int getCount(){
        return count;
    }
    public Cat(double weight)
    {
        this.weight = weight;
        originWeight = weight;
        foodEaten=0;
        isAlive=weight < MAX_WEIGHT && weight > MIN_WEIGHT;;
        if(isAlive) count++;
    }

    public Cat(){
        this(1500.0 + 3000.0 * Math.random());
    }

    public void meow()
    {
        if (!isAlive) System.out.println("This cat is dead");
        else {
            weight = weight - 1;
            System.out.println("Meow");
            isCatAlive();
        }
    }

    public void feed(Double amount)
    {
        if (!isAlive) System.out.println("This cat is dead");
        else {
            weight = weight + amount;
            foodEaten += amount;
            isCatAlive();
        }
    }

    public void drink(Double amount)
    {
        if (!isAlive) System.out.println("This cat is dead");
        else {
            weight = weight + amount;
            isCatAlive();
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    double getFoodEaten(){
        return foodEaten;
    }

    public void poop(){
        if (!isAlive) System.out.println("This cat is dead");
        else {
            weight -= Math.random() * 100 + 100;
            System.out.println("Cat went to toilet");
            isCatAlive();
        }
    }

    private void isCatAlive() {
        if (weight > MAX_WEIGHT || weight < MIN_WEIGHT) {
            isAlive = false;
            count--;
        }
    }

    public Cat getDeepCopy(){
        Cat copy=new Cat(weight);

        copy.originWeight=originWeight;
        copy.foodEaten=foodEaten;
        return copy;
    }
}