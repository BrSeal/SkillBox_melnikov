package module09.task05.MoscowSubway.JsonPresenters;

public class LineAndConnectionJsonPresenter implements Comparable{
    int number;
    String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public LineAndConnectionJsonPresenter(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof LineAndConnectionJsonPresenter) {
           return Integer.compare(number,((LineAndConnectionJsonPresenter) o).getNumber());
        }
        throw new IllegalArgumentException("Illegal class to compare");
    }
}
