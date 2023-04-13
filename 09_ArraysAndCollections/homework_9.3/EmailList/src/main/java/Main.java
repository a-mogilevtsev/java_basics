import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    public static final String WRONG_COMMAND_ANSWER = "Неверная команда";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmailList emailList = new EmailList();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            String[] inputSplitted = input.split(" ");
            if(inputSplitted[0].equalsIgnoreCase("list")) System.out.println(emailList);
            else if (inputSplitted[0].equalsIgnoreCase("add")) {
                if (emailList.isCorrectEmail(inputSplitted[1])){
                    emailList.add(inputSplitted[1].toLowerCase());
                } else {
                    System.out.println(WRONG_EMAIL_ANSWER);
                }
            } else System.out.println(WRONG_COMMAND_ANSWER);
        }

    }

}
