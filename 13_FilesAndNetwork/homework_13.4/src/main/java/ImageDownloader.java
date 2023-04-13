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
 * Created by a.sosnina on 12/15/2021.
 */
public class ImageDownloader {
    List<String> downloadedFiles;

    public ImageDownloader() {
        downloadedFiles = new ArrayList<>();
    }

    public List<String> getImageLinks(String URL) {
        List<String> imageLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements elements = doc.select("img");
            for(Element src : elements) {
                String imageLink = src.attr("abs:src");
                imageLinks.add(imageLink);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imageLinks;
    }

    public String getFileName(String link){
        int index = link.lastIndexOf("/");
        String fileName;
        fileName = link.substring(index + 1);
        return fileName;
    }

    public void downloadImage(String link) {
        String fileName = getFileName(link);
        downloadedFiles.add(fileName);
        String downloadFolder = "images\\";
        try {
            URL connection = new URL(link);
            HttpURLConnection urlconn;
            urlconn = (HttpURLConnection) connection.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.connect();
            InputStream is = urlconn.getInputStream();
            File image = new File(downloadFolder + fileName);
            OutputStream writer = new FileOutputStream(image);
            byte[] array = is.readAllBytes();
            writer.write(array);
            writer.flush();
            writer.close();
            is.close();
        } catch (Exception ex) {

        }
    }

    public List<String> getDownloadedFiles() {
        return downloadedFiles;
    }
}
