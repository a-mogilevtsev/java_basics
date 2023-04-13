import java.util.Scanner;
import java.util.Set;

public class Main {
    public static final String ENTRY_MESSAGE = "Введите номер, имя или команду:";
    public static final String PHONE_NOT_EXIST = "Такого номера нет в телефонной книге.";
    public static final String INVALID_INPUT = "Неверный формат ввода";
    public static PhoneBook book = new PhoneBook();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println(ENTRY_MESSAGE);
            String input = scanner.nextLine();
            if(input.equals("0")) break;
            if(input.equalsIgnoreCase("list")) {
                System.out.println(book);
            } else {
                if(book.isCorrectName(input)) {
                    actionIfInputedName(input);
                } else {
                    if (book.isCorrectPhoneNumber(input)) {
                        actionIfInputedPhone(input);
                    }
                }
            }
        }
    }

    public static void actionIfInputedPhone(String phone) {
        if(!book.phoneBook.containsKey(phone)) {
            System.out.println(PHONE_NOT_EXIST);
            System.out.println("Введите имя абонента для номера - " + phone);
            String input = scanner.nextLine();
            if(book.isCorrectName(input)) book.addContact(phone, input);
            else System.out.println(INVALID_INPUT);
        } else {
           String name = book.phoneBook.get(phone);
            System.out.println(name + " - " + phone);
        }
    }

    public static void actionIfInputedName(String name) {
        if(!book.phoneBook.containsValue(name))  {
            System.out.println("Введите номер телефона для абонента " + name);
            String phone = scanner.nextLine();
            if(book.isCorrectPhoneNumber(phone))
            book.addContact(phone,name);
            else System.out.println(INVALID_INPUT);
        } else {
            Set<String> set = book.getContactByName(name);
            for(String contact : set) {
                System.out.println(contact);
            }
        }
    }
}
