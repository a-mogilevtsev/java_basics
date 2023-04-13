import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.sosnina on 12/22/2021.
 */
public class JsonInputOutput {

    private static final String DATA_FILE = "src/main/resources/map.json";
    private static StationIndex stationIndex;

    public StationIndex createStationIndex() {
    stationIndex = new StationIndex();
    try{
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

        JSONArray linesArray = (JSONArray) jsonData.get("lines");
        parseLines(linesArray);

        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        parseStations(stationsObject);
    }catch (Exception ex) {
        ex.printStackTrace();
    }
        return stationIndex;
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject -> {
            Line line = stationIndex.getLine((String) lineNumberObject);
            JSONArray stationsArray = (JSONArray) stationsObject.get((String)lineNumberObject);
            stationsArray.forEach(stationName->{
                Station station = new Station((String) stationName, line);
                line.addStation(station);
                stationIndex.addStation(station);
            });
        });

        }


    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject->{
            JSONObject lineJsonObject = (JSONObject) lineObject;
            String number = (String) lineJsonObject.get("number");
            String name = (String) lineJsonObject.get("name");
            Line line = new Line(number, name);
            stationIndex.addLine(line);
        });

    }

    private String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void writeJsonFile(StationIndex stationIndex) {
        JSONObject linesStations = new JSONObject();
        JSONArray lines = new JSONArray();
        for(String lineNum : stationIndex.getNumber2line().keySet()){
            Line line = stationIndex.getLine(lineNum);
            List<Station> stations = line.getStations();
            List<String> stationsNameList = new ArrayList<>();
            stations.forEach(station -> {
                stationsNameList.add(station.getName());
            });
            linesStations.put(line.getNumber(), stationsNameList);
            JSONObject arrayElement = new JSONObject();
            arrayElement.put("number", line.getNumber());
            arrayElement.put("name", line.getName());
            lines.add(arrayElement);
        }
        JSONObject stationsJson = new JSONObject();
        stationsJson.put("stations", linesStations);
        stationsJson.put("lines", lines);
        try {
            FileWriter file = new FileWriter(DATA_FILE);
            file.write(stationsJson.toJSONString());
            file.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
