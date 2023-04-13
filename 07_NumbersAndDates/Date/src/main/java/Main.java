import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        int day = 12;
        int month = 3;
        int year = 1996;
        System.out.println(collectBirthdays(year, month, day));
    }

    public static String collectBirthdays(int year, int month, int day) {
        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        //LocalDate birthDay = LocalDate.of(year, month ,day);
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - E");
        short index = 0;
        String result = "";
        for(int i = year; i<= now.getYear(); i++) {
            LocalDate next = LocalDate.of(i, month,day);
            if(next.isBefore(now) || next.isEqual(now)){
                result += index + " - " + formatter.format(next) + System.lineSeparator();
            }
            index++;
        }
        return result;
    }
}
