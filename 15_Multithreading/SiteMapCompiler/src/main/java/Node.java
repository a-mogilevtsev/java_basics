import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by a.sosnina on 1/27/2022.
 */
public class Node {
    private String url;
    private Set<String> childs;

    public Node(String url) {
        this.url = url;
        childs = new HashSet<>();
    }

    public String getUrl() {
        return url;
    }

    public void addChild(String link) {
        childs.add(link);
    }

    public void findChilds() {
        Document doc = null;
        //if(!checkIsChild(url)) return;
        try {
            doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).maxBodySize(0).get();
        }catch (Exception ex) {
            System.out.println(url);
            ex.printStackTrace();
        }
        if(doc!=null) {
            Elements links = doc.select("a");
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (checkIsChild(href))
                    childs.add(href);
            }
        }
    }

    public boolean checkIsChild(String link) {
        if(link.contains("https://skillbox.ru/") &&
                !link.contains("/?") &&
                !link.contains(".pdf") &&
                !link.contains("/#") &&
                !link.contains("%")) {
            return true;
        } else return false;
    }

    public Set<String> getChilds() {
        return childs;
    }


}
