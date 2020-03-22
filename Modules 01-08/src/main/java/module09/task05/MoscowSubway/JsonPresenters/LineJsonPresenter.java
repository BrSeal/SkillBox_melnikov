package module09.task05.MoscowSubway.JsonPresenters;

public class LineJsonPresenter implements Comparable {
    private String color;
    private String name;
    private int number;

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public LineJsonPresenter(int number, String name, String color) {
        this.number = number;
        this.color = color;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof LineJsonPresenter) {
            return Integer.compare(number, ((LineJsonPresenter) o).getNumber());
        }
        throw new IllegalArgumentException("Illegal class to compare");
    }
}
