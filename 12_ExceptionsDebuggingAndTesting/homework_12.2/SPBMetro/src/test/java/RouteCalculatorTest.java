import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.sosnina on 11/30/2021.
 */
public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    private static StationIndex stationIndex;
    private static final String DATA_FILE = "src/main/resources/map.json";
    private static RouteCalculator routeCalculator;

    @Before
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        routeCalculator = new RouteCalculator(stationIndex);
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }


    public static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }


    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    @Override
    protected void tearDown() throws Exception {

    }



    @Test
    public void testGetRouteOnTheLine() {
        Station from = stationIndex.getStation("Чернышевская");
        Station to = stationIndex.getStation("Балтийская");
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(from);
        expectedRoute.add(stationIndex.getStation("Площадь Восстания"));
        expectedRoute.add(stationIndex.getStation("Владимирская"));
        expectedRoute.add(stationIndex.getStation("Пушкинская"));
        expectedRoute.add(stationIndex.getStation("Технологический институт"));
        expectedRoute.add(to);
        route = routeCalculator.getShortestRoute(from, to);
        Assert.assertArrayEquals(expectedRoute.toArray(), route.toArray());
    }

    @Test
    public void testGetShortestRouteOneConnection() {
        Station from = stationIndex.getStation("Чернышевская");
        Station to = stationIndex.getStation("Ладожская");
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(from);
        expectedRoute.add(stationIndex.getStation("Площадь Восстания"));
        expectedRoute.add(stationIndex.getStation("Владимирская"));
        expectedRoute.add(stationIndex.getStation("Достоевская"));
        expectedRoute.add(stationIndex.getStation("Лиговский проспект"));
        expectedRoute.add(stationIndex.getStation("Площадь Александра Невского", 4));
        expectedRoute.add(stationIndex.getStation("Новочеркасская"));
        expectedRoute.add(to);
        route = routeCalculator.getShortestRoute(from,to);
        Assert.assertArrayEquals(expectedRoute.toArray(), route.toArray());
    }

    @Test
    public void testGetShortestRouteTwoConnections() {
        Station from = stationIndex.getStation("Горьковская");
        Station to = stationIndex.getStation("Площадь Ленина");
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(from);
        expectedRoute.add(stationIndex.getStation("Невский проспект"));
        expectedRoute.add(stationIndex.getStation("Гостиный двор"));
        expectedRoute.add(stationIndex.getStation("Маяковская"));
        expectedRoute.add(stationIndex.getStation("Площадь восстания"));
        expectedRoute.add(stationIndex.getStation("Чернышевская"));
        expectedRoute.add(to);
        route = routeCalculator.getShortestRoute(from,to);
        Assert.assertArrayEquals(expectedRoute.toArray(), route.toArray());
    }

    @Test
    public void testCalculateDuration() {
        Station from = stationIndex.getStation("Горьковская");
        Station to = stationIndex.getStation("Площадь Ленина");
        route = routeCalculator.getShortestRoute(from,to);
        double duration = RouteCalculator.calculateDuration(route);
        double expected = 17.0;
        assertEquals(expected, duration);
        from = stationIndex.getStation("Петроградская");
        to = stationIndex.getStation("Электросила");
        route = routeCalculator.getShortestRoute(from,to);
        duration = RouteCalculator.calculateDuration(route);
        expected = 17.5;
        assertEquals(expected, duration);
        from = stationIndex.getStation("Невский проспект");
        to = stationIndex.getStation("Лесная");
        route = routeCalculator.getShortestRoute(from,to);
        duration = RouteCalculator.calculateDuration(route);
        expected = 19.5;
        assertEquals(expected, duration);

    }

    private static void printRoute(List<Station> route) {
        Station previousStation = null;
        for (Station station : route) {
            if (previousStation != null) {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if (!prevLine.equals(nextLine)) {
                    System.out.println("\tПереход на станцию " +
                            station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }

}
