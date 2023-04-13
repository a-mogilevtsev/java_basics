public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        String[] words = line.split(" ");
        words = ReverseArray.reverse(words);
        for(String word : words) {
            System.out.println(word);
        }
    }
}
