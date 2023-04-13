/**
 * Created by a.sosnina on 12/16/2021.
 */
public class Main {

    public static void main(String[] args) {
        readJsonMap();
    }
    public static void parseSite(){
        String Url = "https://www.moscowmap.ru/metro.html#lines";
        StationIndex stationIndex = new StationIndex();
        PageParser parser = new PageParser(Url, stationIndex);
        parser.connectToUrl();
        parser.parseLines();
        parser.parseStations();
        stationIndex.printLinesAndStations();
        JsonInputOutput.writeJsonFile(stationIndex);
    }

    public static void readJsonMap(){
        JsonInputOutput jsonIO = new JsonInputOutput();
        StationIndex stationIndex = jsonIO.createStationIndex();
        stationIndex.printLinesAndStations();
    }
}
