import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int boxNum = Integer.parseInt(boxes);
        int truck = 0;
        int cont = 0;
        int box = 1 ;
        int placeForBoxes = 0;
        int placeForCont = 1;
        while (box <= boxNum){
                if(placeForBoxes == 0){
                    cont++;
                    placeForCont--;
                    if (placeForCont == 0) {
                        truck++;
                        placeForCont = 12;
                        System.out.println("Грузовик: " + truck);
                    }
                    placeForBoxes = 27;
                    System.out.println("\tКонтейнер: " + cont);
                }
                System.out.println("\t\tЯщик: " + box);
                box++;
                placeForBoxes--;
            }
        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + truck + " шт.");
        System.out.println("контейнеров - " + cont + " шт.");


    }
}
