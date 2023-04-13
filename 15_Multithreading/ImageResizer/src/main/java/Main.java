import java.io.File;
import java.util.Arrays;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "c:\\projects\\temp\\imageSource\\";
        String dstFolder = "c:\\projects\\temp\\imageDest";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int cores = Runtime.getRuntime().availableProcessors();
        int partSize = files.length/cores;
        int from = 0;
        int to = 0;
        for(int i = 0; i < cores; i++) {
            from = partSize * i;
            if(i == cores - 1) {
                to = files.length;
            }else {
                to = partSize * (i + 1);
            }
            File[] filesPart = Arrays.copyOfRange(files, from, to);
            ImageResizer resizer = new ImageResizer(filesPart, newWidth, dstFolder, System.currentTimeMillis());
            resizer.start();
        }
        System.out.println(cores);

    }
}
