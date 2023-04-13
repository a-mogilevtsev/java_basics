import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a text");
    String input = scanner.nextLine();
    System.out.println(splitTextIntoWords(input));
  }
    public static String splitTextIntoWords (String text){
      //TODO реализуйте метод
      String result = "";
      String regex = "[a-zA-Z’]*";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(text);
      StringJoiner joiner = new StringJoiner("\n");
      while (matcher.find()){
        int start = matcher.start();
        int end = matcher.end();
        String word = text.substring(start, end);
        if(!word.trim().isEmpty()){
            joiner.add(word);
        }
      }
      return joiner.toString();
    }
}