import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес папки откуда необходимо скопировать содержимое");
        String sourceFolder = scanner.nextLine();
        System.out.println("Введите адрес папки куда необходимо скопировать содержимое");
        String destinationFolder = scanner.nextLine();
        FileUtils.copyFolder(sourceFolder, destinationFolder);


    }

}
