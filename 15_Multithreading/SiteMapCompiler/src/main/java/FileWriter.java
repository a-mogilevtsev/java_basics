import java.io.File;
import java.util.List;
import java.util.Set;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by a.sosnina on 2/4/2022.
 */
public class FileWriter {
    private String url;

    public FileWriter(String url) {
        this.url = url;
    }

    public void writeToFile(List<String> linksList) {
        File linksFile = new File(url);
        try {
            OutputStream writer = new FileOutputStream(linksFile);
            for(String link : linksList) {
                writer.write(link.getBytes());
                writer.write(System.lineSeparator().getBytes());
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String formatLink(String link) {
        String[] parts = link.split("/");
        String result = "";
        int count = parts.length - 3;
        if(count > 0) {
            for (int i = 0; i < count; i++) {
                result = result.concat("\t");
            }
        }
        result = result + link;
        return result;
    }
}
