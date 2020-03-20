package module09.task05.MoscowSubway.JsonPresenters;

public class ConnectionJsonPresenter implements Comparable {
    private String station;
    private int line;

    public String getStation() {
        return station;
    }

    public int getLine() {
        return line;
    }

    public ConnectionJsonPresenter(int line, String station) {
        this.line = line;
        this.station = station;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof ConnectionJsonPresenter) {
            return Integer.compare(line, ((ConnectionJsonPresenter) o).getLine());
        }
        throw new IllegalArgumentException("Illegal class to compare");
    }
}
