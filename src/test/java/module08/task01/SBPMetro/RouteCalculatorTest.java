package module08.task01.SBPMetro;

import junit.framework.TestCase;
import module08.task01.SBPMetro.core.Line;
import module08.task01.SBPMetro.core.Station;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;

    @Override
    protected void setUp() {
        route = new ArrayList<>();
        Line line1 = new Line(1, "One");
        Line line2 = new Line(2, "Two");

        route.add(new Station("Onion", line1));
        route.add(new Station("Orbit", line1));
        route.add(new Station("Ore", line1));
        route.add(new Station("Towel", line2));
        route.add(new Station("Transport", line2));
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(2.5, actual);
    }
}