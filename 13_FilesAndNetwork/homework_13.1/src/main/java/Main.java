import java.util.Scanner;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Введите путь до папки:");
        String folder = scanner.nextLine().trim();
        long filesSize = FileUtils.calculateFolderSize(folder);
        System.out.printf("Размер папки %s составляет %s", folder, FileUtils.convertToNormalOutput(filesSize));
    }


}
