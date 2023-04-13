import java.io.*;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        File[] source = new File(sourceDirectory).listFiles();
        try {
            for (File item : source) {
                String copyAdress = destinationDirectory + "\\" + item.getName();
                if(item.isDirectory()) {
                    File folderToCreate = new File(copyAdress);
                    folderToCreate.mkdirs();
                    copyFolder(item.getAbsolutePath(), folderToCreate.getAbsolutePath());
                } else {
                    if(item.isFile()) {
                      copyFile(item, copyAdress);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File fileToCopy, String destinationAdress) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileToCopy.getAbsolutePath()));
            PrintWriter pw = new PrintWriter(destinationAdress);
            while (br.ready()) {
                pw.write(br.readLine());
                if(br.ready()) pw.write("\\n");
            }
            br.close();
            pw.flush();
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
