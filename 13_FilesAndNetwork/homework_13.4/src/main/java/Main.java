import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.sosnina on 12/14/2021.
 */
public class Main {

    public static void main(String[] args) {
        ImageDownloader downloader = new ImageDownloader();
        List<String> imageLinks = downloader.getImageLinks("https://lenta.ru/");
        for (String link : imageLinks) {
            downloader.downloadImage(link);
        }
        for(String fileName : downloader.getDownloadedFiles()){
            System.out.println(fileName);
        }
    }
}
