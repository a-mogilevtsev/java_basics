import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
      String regex = "[^0-9]";
      input = input.replaceAll( regex, "");
      String result;
      if (input.length() == 11) {
        if(input.charAt(0) != '7' && input.charAt(0) != '8') result = "Неверный формат номера";
        else {
          result = input.replaceAll("^8", "7");
        }
      } else {
        if(input.length() == 10) result = "7" + input;
        else result = "Неверный формат номера";
      }
      //System.out.println(result);
      System.out.println(formatPhoneNumber(result));;
    }
  }

  public static String formatPhoneNumber(String number) {
    String formattedPhone = "";
    if(!number.equals("Неверный формат номера")) {
      String regex = "(\\d{1})(\\d{3})(\\d{3})(\\d{2})(\\d{2})";
      formattedPhone = Pattern.compile(regex).matcher(number).replaceAll("+$1 ($2) $3 $4-$5");
    } else return number;
    return formattedPhone;
  }
}
