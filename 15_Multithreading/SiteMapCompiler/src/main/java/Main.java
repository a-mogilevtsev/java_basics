import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by a.sosnina on 1/25/2022.
 */
public class Main {
    private SortedSet<String> links;

    public static void main(String[] args) {
        String url = "https://skillbox.ru/";
        Node parentNode = new Node(url);
        NodeLinkFinder finder = new NodeLinkFinder(parentNode);
        new ForkJoinPool().invoke(finder);

        FileWriter fw = new FileWriter("c:\\projects\\links.txt");
        List<String> formattedLinks = new ArrayList<>();
        for(String link : finder.getAllLinks()) {
            formattedLinks.add(fw.formatLink(link));
        }
        fw.writeToFile(formattedLinks);

    }


}
