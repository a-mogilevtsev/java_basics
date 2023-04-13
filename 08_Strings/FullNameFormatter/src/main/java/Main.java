import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    //TODO:напишите ваш код тут, результат вывести в консоль.
    //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) break;
      String[] fio = getFioIfValid(input);
      if(fio == null) {
        System.out.println("Введенная строка не является ФИО");
      } else {
        System.out.printf("Фамилия: %s\nИмя: %s\nОтчество: %s", fio[0], fio[1], fio[2]);
      }
    }
  }

  public static String[] getFioIfValid(String input) {
    int countOfSpaces = 0;
    for (int i = 0; i < input.length(); i++) {
      if (Character.isDigit(input.charAt(i))) {
        return null;
      }else {
        if(input.charAt(i) == ' ') countOfSpaces++;
      }
    }
    int firstSpace = input.indexOf(' ');
    int secondSpace = input.lastIndexOf(' ');
    String[] fio = new String[3];
    if(countOfSpaces != 2) return null;
    else {
      fio[0] = input.substring(0, firstSpace).trim();
      fio[1] = input.substring(firstSpace, secondSpace).trim();
      fio[2] = input.substring(secondSpace).trim();
    }
    return fio;
  }
}
