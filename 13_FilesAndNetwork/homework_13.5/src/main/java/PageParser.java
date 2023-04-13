import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by a.sosnina on 12/16/2021.
 */
public class PageParser {
    private String URL;
    private final StationIndex stationIndex;
    public Document doc;

    public PageParser(String URL, StationIndex stationIndex) {
        this.URL = URL;
        this.stationIndex = stationIndex;
    }

    public Document connectToUrl() {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).maxBodySize(0).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        this.doc = doc;
        return doc;
    }

    public void parseLines() {
        Elements elements = doc.select("span[data-line]");
        for(Element element:elements) {
            Line line = new Line(element.attr("data-line"),element.text());
            stationIndex.addLine(line);
        }
    }

    public void parseStations() {
        for (Line line : stationIndex.getNumber2line().values()) {
            String cssQuery = String.format("div[data-line=%s]", line.getNumber());
            Elements elements = doc.select(cssQuery);
            for (Element element : elements) {
                Element pElement = element.child(0);
                Element lastPelement = pElement.lastElementSibling();
                while (pElement!=lastPelement) {
                    Element aElement = pElement.child(0);
                    String stationTitle =  aElement.getElementsByAttributeValue("class", "name").text();
                    Station station = new Station(stationTitle, line);
                    line.addStation(station);
                    stationIndex.addStation(station);
                    pElement = pElement.nextElementSibling();
                }
                Element aElement = pElement.child(0);
                String stationTitle =  aElement.getElementsByAttributeValue("class", "name").text();
                Station station = new Station(stationTitle, line);
                line.addStation(station);
                stationIndex.addStation(station);
            }
        }
    }

    public static void printElements(Elements elements) {
        for(Element element : elements) {
            System.out.println(element.text());
        }
    }
}
