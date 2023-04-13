import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    String result = "";
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
      String[] fio = input.split(" ");
      if (fio.length!=3) result = "Введенная строка не является ФИО";
      else {
        result = String.format("Фамилия: %s%nИмя: %s%nОтчество: %s", fio[0], fio[1], fio[2]);
        Pattern pattern = Pattern.compile("[^а-яА-ЯёЁ-]");
        for (int i = 0; i < 3; i++) {
          Matcher matcher = pattern.matcher(fio[i]);
          if (matcher.find()) result = "Введенная строка не является ФИО";
        }
      }
      }
      System.out.println(result);
    }
  }

