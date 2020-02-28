package module08.task01.SBPMetro;

import module08.task01.SBPMetro.Exceptions.NoRouteException;
import module08.task01.SBPMetro.Exceptions.RouteHasNullElementsException;
import module08.task01.SBPMetro.core.Line;
import module08.task01.SBPMetro.core.Station;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends Assert {


    static ArrayList<Station> stations;
    static StationIndex stationIndex;
    static RouteCalculator calculator;

    List<Station> expected;
    Station from,to;

/**
    Line 0 stations 0 - 3
    Line 1 stations 4 - 7
    Line 2 stations 8 - 11

    connections 2-5, 6-9, 5-11
 */
    @BeforeClass
    public static void setRouteCalculator() {
        ArrayList<Line> lines=new ArrayList<>();
        stations=new ArrayList<>();
        stationIndex=new StationIndex();

        for (int i = 0; i < 3; i++) {
            lines.add(new Line(i,"L"+i));
            for (int j = i*4; j < i*4+4; j++)
            {
                stations.add(new Station(i+"-"+ j,lines.get(i)));
                lines.get(i).addStation(stations.get(j));
            }
        }

        ArrayList<Station> connections=new ArrayList<>();
        connections.add(stations.get(2));
        connections.add(stations.get(5));
        stationIndex.addConnection(connections);

        connections.clear();
        connections.add(stations.get(6));
        connections.add(stations.get(9));
        stationIndex.addConnection(connections);

        connections.clear();
        connections.add(stations.get(5));
        connections.add(stations.get(11));
        stationIndex.addConnection(connections);

        stations.forEach(s->stationIndex.addStation(s));
        lines.forEach(l->stationIndex.addLine(l));

        calculator=new RouteCalculator(stationIndex);

    }

    @Before
    public void setExpected(){
        expected = new ArrayList<>();
    }

    @Test(expected = NoRouteException.class)
    public void calcDurationEmptyRoad() throws NoRouteException {
        RouteCalculator.calculateDuration(expected);
    }

    @Test(expected = NoRouteException.class)
    public void calcDurationNullRoute() throws NoRouteException {
        RouteCalculator.calculateDuration(null);
    }

    @Test(expected = RouteHasNullElementsException.class)
    public void calcDurationNullElements1() throws NoRouteException {
        expected.add(null);
        RouteCalculator.calculateDuration(expected);
    }

    @Test(expected = RouteHasNullElementsException.class)
    public void calcDurationNullElements2() throws NoRouteException {
        expected.add(stations.get(5));
        expected.add(stations.get(8));
        expected.add(null);
        RouteCalculator.calculateDuration(expected);
    }

    @Test
    public void calcDurationOneLine1() throws NoRouteException {
        expected.add(stations.get(0));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(0d, actual, 0.0001);
    }

    @Test
    public void calcDurationOneLine2() throws NoRouteException{
        expected.add(stations.get(0));
        expected.add(stations.get(1));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(2.5, actual, 0.0001);
    }

    @Test
    public void calcDurationOneLine3() throws NoRouteException {
        expected.add(stations.get(0));
        expected.add(stations.get(1));
        expected.add(stations.get(2));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(5d, actual, 0.0001);
    }

    @Test
    public void calcDurationManyLines1()  throws NoRouteException{
        expected.add(stations.get(0));
        expected.add(stations.get(4));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(3.5, actual, 0.0001);
    }

    @Test
    public void calcDurationManyLines2()  throws NoRouteException {
        expected.add(stations.get(0));
        expected.add(stations.get(1));
        expected.add(stations.get(4));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(6d, actual, 0.0001);
    }

    @Test
    public void calcDurationManyLines3()  throws NoRouteException {
        expected.add(stations.get(0));
        expected.add(stations.get(1));
        expected.add(stations.get(4));
        expected.add(stations.get(5));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(8.5, actual, 0.0001);
    }

    @Test
    public void calcDurationManyLines4()  throws NoRouteException {
        expected.add(stations.get(0));
        expected.add(stations.get(4));
        expected.add(stations.get(8));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(7d, actual, 0.0001);
    }

    @Test
    public void calcDurationManyLines5()  throws NoRouteException {
        expected.add(stations.get(0));
        expected.add(stations.get(1));
        expected.add(stations.get(4));
        expected.add(stations.get(5));
        expected.add(stations.get(8));
        expected.add(stations.get(9));

        double actual = RouteCalculator.calculateDuration(expected);

        assertEquals(14.5, actual, 0.0001);
    }

    //=====================================================================//
    @Test(expected = RouteHasNullElementsException.class)
    public void getShortestRouteNullTest1() throws NoRouteException {
        calculator.getShortestRoute(null,null);
    }

    @Test(expected = RouteHasNullElementsException.class)
    public void getShortestRouteNullTest2() throws NoRouteException {
        calculator.getShortestRoute(stations.get(0),null);
    }

    @Test (expected = RouteHasNullElementsException.class)
    public void getShortestRouteNullTest3() throws NoRouteException {
        calculator.getShortestRoute(null,stations.get(0));
    }

    //=====================================================================//

    @Test (expected = NoRouteException.class)
    public void getShortestRouteNoRoute() throws NoRouteException {
        Line line=new Line(5,"qwerty");
        from=new Station("qqq", line);
        to=stations.get(0);
        calculator.getShortestRoute(from,to);
    }

    @Test
    public void getShortestRouteOneLine1()  throws NoRouteException{
        from=to=stations.get(0);
        expected.add(from);
        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteOneLine2()  throws NoRouteException{
        from=stations.get(0);
        to=stations.get(1);

        expected.add(from);
        expected.add(to);

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteOneLine3()  throws NoRouteException{
        from=stations.get(0);
        to=stations.get(3);

        expected.add(stations.get(0));
        expected.add(stations.get(1));
        expected.add(stations.get(2));
        expected.add(stations.get(3));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteOneLineReverse1()  throws NoRouteException{
        from=stations.get(1);
        to=stations.get(0);

        expected.add(from);
        expected.add(to);

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteOneLineReverse2()  throws NoRouteException{
        from=stations.get(3);
        to=stations.get(0);

        expected.add(stations.get(3));
        expected.add(stations.get(2));
        expected.add(stations.get(1));
        expected.add(stations.get(0));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    //=======================================================================//

    @Test
    public void getShortestRouteMultiLine1() throws NoRouteException{
        from=stations.get(2);
        to=stations.get(5);
        expected.add(from);
        expected.add(to);

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLine2() throws NoRouteException{
        from=stations.get(0);
        to=stations.get(5);

        expected.add(stations.get(0));
        expected.add(stations.get(1));
        expected.add(stations.get(2));
        expected.add(stations.get(5));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLine3() throws NoRouteException{
        from=stations.get(1);
        to=stations.get(9);

        expected.add(stations.get(1));
        expected.add(stations.get(2));
        expected.add(stations.get(5));
        expected.add(stations.get(6));
        expected.add(stations.get(9));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLine4() throws NoRouteException{
        from=stations.get(7);
        to=stations.get(11);

        expected.add(stations.get(7));
        expected.add(stations.get(6));
        expected.add(stations.get(5));
        expected.add(stations.get(11));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLineReversed1() throws NoRouteException{
        from=stations.get(5);
        to=stations.get(2);
        expected.add(from);
        expected.add(to);

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLineReversed2() throws NoRouteException{
        from=stations.get(5);
        to=stations.get(0);

        expected.add(stations.get(5));
        expected.add(stations.get(2));
        expected.add(stations.get(1));
        expected.add(stations.get(0));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLineReversed3() throws NoRouteException{
        from=stations.get(9);
        to=stations.get(1);

        expected.add(stations.get(9));
        expected.add(stations.get(6));
        expected.add(stations.get(5));
        expected.add(stations.get(2));
        expected.add(stations.get(1));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }

    @Test
    public void getShortestRouteMultiLineReversed4() throws NoRouteException{
        from=stations.get(11);
        to=stations.get(7);

        expected.add(stations.get(11));
        expected.add(stations.get(5));
        expected.add(stations.get(6));
        expected.add(stations.get(7));

        assertEquals(expected,calculator.getShortestRoute(from,to));
    }
}