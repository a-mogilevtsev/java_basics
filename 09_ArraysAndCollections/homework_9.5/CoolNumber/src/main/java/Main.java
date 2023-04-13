import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> coolNumbers = CoolNumbers.generateCoolNumbers();
        long startTime = System.nanoTime();
        String numberToFind = "К777НО66";
        boolean searchResult = CoolNumbers.bruteForceSearchInList(coolNumbers, numberToFind);
        long estimatedTime = System.nanoTime() - startTime;
        String isFound = searchResult ? "найден" : "не найден";
        System.out.printf("Поиск перебором: номер %s, поиск занял %dнс\n", isFound, estimatedTime);


        Collections.sort(coolNumbers);
        startTime = System.nanoTime();
        searchResult = CoolNumbers.binarySearchInList(coolNumbers, numberToFind);
        estimatedTime = System.nanoTime() - startTime;
        isFound = searchResult ? "найден" : "не найден";
        System.out.printf("Бинарный поиск: номер %s, поиск занял %dнс\n", isFound, estimatedTime);

        HashSet<String> coolNumbersSet = new HashSet<>(coolNumbers);
        startTime = System.nanoTime();
        searchResult = CoolNumbers.searchInHashSet(coolNumbersSet, numberToFind);
        estimatedTime = System.nanoTime() - startTime;
        isFound = searchResult ? "найден" : "не найден";
        System.out.printf("Поиск в HashSet: номер %s, поиск занял %dнс\n", isFound, estimatedTime);

        TreeSet<String> coolNumbersTreeSet = new TreeSet<>(coolNumbers);
        startTime = System.nanoTime();
        searchResult = CoolNumbers.searchInTreeSet(coolNumbersTreeSet, numberToFind);
        estimatedTime = System.nanoTime() - startTime;
        isFound = searchResult ? "найден" : "не найден";
        System.out.printf("Поиск в TreeSet: номер %s, поиск занял %dнс", isFound, estimatedTime);


    }

}
