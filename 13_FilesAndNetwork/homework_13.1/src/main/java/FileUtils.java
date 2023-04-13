import java.io.File;
import java.text.DecimalFormat;

public class FileUtils {
    private static long filesSize;
    public static long calculateFolderSize(String path) {
        filesSize = 0;
        try {
            File folder = new File(path);
            File[] contains = folder.listFiles();
            for (File item : contains) {
                if (item.isDirectory()) filesSize += calculateFolderSize(item.getAbsolutePath());
                else {
                    filesSize += item.length();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return filesSize;
    }

    public static String convertToNormalOutput(long size) {
        String postfix = "";
        double result = 0;
        if(size > Math.pow(2, 30)) {
            result = size / Math.pow(2, 30);
            postfix = " Гб";
        } else if(size > Math.pow(2, 20) ) {
            result = size / Math.pow(2, 20);
            postfix = " Мб";
        } else if(size > Math.pow(2, 10)) {
            result = size / Math.pow(2, 20);
            postfix = " Кб";
        } else {
            postfix =" Б";
        }
        DecimalFormat dF = new DecimalFormat( "#.#" );
        return dF.format(result) + " " + postfix;
    }
}
