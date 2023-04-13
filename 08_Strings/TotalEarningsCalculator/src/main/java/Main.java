public class Main {

  public static void main(String[] args) {
    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль
    int result = 0;
    for(int i = 0; i < text.length(); i++) {
      if (Character.isDigit(text.charAt(i))) {
        int end = text.indexOf(" ", i);
        result += Integer.parseInt(text.substring(i, end));
        i = end;
      }
    }
    System.out.println(result);
  }

}