import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        List<Character> letters = Arrays.asList('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х');
        List<String> coolNumbers = new ArrayList<>();
        //XNNNYZR — пример: A111BC197, У777HC66.
        for(int x = 0; x < letters.size(); x++) {
            for(int n = 1; n < 10; n++){
                for (int y = 0; y < letters.size(); y++) {
                    for (int z = 0; z < letters.size(); z++){
                        for (int r = 10; r < 200; r++) {
                            String number = letters.get(x) +
                                    String.valueOf(n) + n + n + letters.get(y) + letters.get(z) + r;
                            if( coolNumbers.size()< 2100000) coolNumbers.add(number);
                        }
                    }
                }
            }
        }
        return coolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        return list.contains(number);
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        return Collections.binarySearch(sortedList, number) >= 0;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
