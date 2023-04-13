import java.time.LocalDate;
import java.time.Period;

public class Main {

  public static void main(String[] args) {
    LocalDate javaBirthDay = LocalDate.of(1996, 1,21);
    System.out.println(getPeriodFromBirthday(javaBirthDay));
  }

  private static String getPeriodFromBirthday(LocalDate birthday) {
    LocalDate now = LocalDate.now();
    String result = "";
    Period difference = Period.between(birthday, now);
    result = difference.getYears() + "  years, " + difference.getMonths() + " months, " + difference.getDays() + " days";
    return result;
  }

}
