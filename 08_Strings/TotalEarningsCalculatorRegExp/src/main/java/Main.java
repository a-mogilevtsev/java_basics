import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    //TODO: напишите ваш код, результат вывести в консоль
    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    System.out.println(calculateSalarySum(text));
  }


  public static int calculateSalarySum(String text){
    //TODO: реализуйте
    int result = 0;
    String regexp = "\\s[0-9]*\\s";
    Pattern pattern = Pattern.compile(regexp);
    Matcher matcher = pattern.matcher(text);
    while(matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      result += Integer.parseInt(text.substring(start, end).trim());
    }
    return result;
  }

}