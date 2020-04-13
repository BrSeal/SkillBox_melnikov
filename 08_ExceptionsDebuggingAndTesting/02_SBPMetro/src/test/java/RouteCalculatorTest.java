

import Exceptions.NoRouteException;
import Exceptions.RouteHasNullElementsException;
import core.Line;
import core.Station;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends Assert
{
	
	
	static ArrayList<Station> stations;
	static StationIndex stationIndex;
	static RouteCalculator calculator;
	Station from, to;
	
	/**
	 * Line 0 stations 0 - 3
	 * Line 1 stations 4 - 7
	 * Line 2 stations 8 - 11
	 * <p>
	 * connections 2-5, 6-9, 5-11
	 */
	@BeforeClass
	public static void setRouteCalculator()
	{
		ArrayList<Line> lines = new ArrayList<>();
		stations = new ArrayList<>();
		stationIndex = new StationIndex();
		
		for (int i = 0; i < 3; i++)
		{
			lines.add(new Line(i, "L" + i));
			for (int j = i * 4; j < i * 4 + 4; j++)
			{
				stations.add(new Station(j + "", lines.get(i)));
				lines.get(i).addStation(stations.get(j));
			}
		}
		stations.forEach(s -> stationIndex.addStation(s));
		lines.forEach(l -> stationIndex.addLine(l));
		
		stationIndex.addConnection(getList("2", "5"));
		stationIndex.addConnection(getList("6", "9"));
		stationIndex.addConnection(getList("5", "11"));
		
		calculator = new RouteCalculator(stationIndex);
	}
	
	@Test (expected = NoRouteException.class)
	public void calcDurationEmptyRoad() throws NoRouteException
	{
		RouteCalculator.calculateDuration(getList());
	}
	
	@Test (expected = NoRouteException.class)
	public void calcDurationNullRoute() throws NoRouteException
	{
		RouteCalculator.calculateDuration(null);
	}
	
	@Test (expected = RouteHasNullElementsException.class)
	public void calcDurationNullElements1() throws NoRouteException
	{
		RouteCalculator.calculateDuration(getList("not existing station"));
	}
	
	@Test (expected = RouteHasNullElementsException.class)
	public void calcDurationNullElements2() throws NoRouteException
	{
		RouteCalculator.calculateDuration(getList("5", "8", null));
	}
	
	@Test
	public void calcDurationOneLine1() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0"));
		assertEquals(0d, actual, 0.0001);
	}
	
	@Test
	public void calcDurationOneLine2() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "1"));
		assertEquals(2.5, actual, 0.0001);
	}
	
	@Test
	public void calcDurationOneLine3() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "1", "2"));
		assertEquals(5d, actual, 0.0001);
	}
	
	@Test
	public void calcDurationManyLines1() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "4"));
		assertEquals(3.5, actual, 0.0001);
	}
	
	@Test
	public void calcDurationManyLines2() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "1", "4"));
		assertEquals(6d, actual, 0.0001);
	}
	
	@Test
	public void calcDurationManyLines3() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "1", "4", "5"));
		assertEquals(8.5, actual, 0.0001);
	}
	
	@Test
	public void calcDurationManyLines4() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "4", "8"));
		assertEquals(7d, actual, 0.0001);
	}
	
	@Test
	public void calcDurationManyLines5() throws NoRouteException
	{
		double actual = RouteCalculator.calculateDuration(getList("0", "1", "4", "5", "8", "9"));
		assertEquals(14.5, actual, 0.0001);
	}
	
	//=====================================================================//
	@Test (expected = RouteHasNullElementsException.class)
	public void getShortestRouteNullTest1() throws NoRouteException
	{
		calculator.getShortestRoute(null, null);
	}
	
	@Test (expected = RouteHasNullElementsException.class)
	public void getShortestRouteNullTest2() throws NoRouteException
	{
		calculator.getShortestRoute(stations.get(0), null);
	}
	
	@Test (expected = RouteHasNullElementsException.class)
	public void getShortestRouteNullTest3() throws NoRouteException
	{
		calculator.getShortestRoute(null, stations.get(0));
	}
	
	//=====================================================================//
	
	@Test (expected = NoRouteException.class)
	public void getShortestRouteNoRoute() throws NoRouteException
	{
		Line line = new Line(5, "qwerty");
		from = new Station("qqq", line);
		to = stations.get(0);
		calculator.getShortestRoute(from, to);
	}
	
	@Test
	public void getShortestRouteOneLine1() throws NoRouteException
	{
		from = to = stations.get(0);
		assertEquals(getList("0"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteOneLine2() throws NoRouteException
	{
		from = stations.get(0);
		to = stations.get(1);
		assertEquals(getList("0", "1"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteOneLine3() throws NoRouteException
	{
		from = stations.get(0);
		to = stations.get(3);
		
		assertEquals(getList("0", "1", "2", "3"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteOneLineReverse1() throws NoRouteException
	{
		from = stations.get(1);
		to = stations.get(0);
		
		assertEquals(getList("1", "0"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteOneLineReverse2() throws NoRouteException
	{
		from = stations.get(3);
		to = stations.get(0);
		assertEquals(getList("3", "2", "1", "0"), calculator.getShortestRoute(from, to));
	}
	
	//=======================================================================//
	
	@Test
	public void getShortestRouteMultiLine1() throws NoRouteException
	{
		from = stations.get(2);
		to = stations.get(5);
		assertEquals(getList("2", "5"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLine2() throws NoRouteException
	{
		from = stations.get(0);
		to = stations.get(5);
		assertEquals(getList("0", "1", "2", "5"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLine3() throws NoRouteException
	{
		from = stations.get(1);
		to = stations.get(9);
		assertEquals(getList("1", "2", "5", "6", "9"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLine4() throws NoRouteException
	{
		from = stations.get(7);
		to = stations.get(11);
		assertEquals(getList("7", "6", "5", "11"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLineReversed1() throws NoRouteException
	{
		from = stations.get(5);
		to = stations.get(2);
		assertEquals(getList("5", "2"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLineReversed2() throws NoRouteException
	{
		from = stations.get(5);
		to = stations.get(0);
		assertEquals(getList("5", "2", "1", "0"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLineReversed3() throws NoRouteException
	{
		from = stations.get(9);
		to = stations.get(1);
		assertEquals(getList("9", "6", "5", "2", "1"), calculator.getShortestRoute(from, to));
	}
	
	@Test
	public void getShortestRouteMultiLineReversed4() throws NoRouteException
	{
		from = stations.get(11);
		to = stations.get(7);
		assertEquals(getList("11", "5", "6", "7"), calculator.getShortestRoute(from, to));
	}
	
	private static List<Station> getList(String... stations)
	{
		List<Station> result = new ArrayList<>();
		for (String stationName : stations)
		{
			result.add(stationIndex.getStation(stationName));
		}
		return result;
	}
}