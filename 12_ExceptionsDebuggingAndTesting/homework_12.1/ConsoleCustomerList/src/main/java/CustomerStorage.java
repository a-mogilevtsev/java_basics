import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private static final String regex = "^(([0-9A-Za-z]{1}[-0-9A-z\\.]{1,}[0-9A-Za-z]{1}))@([-A-Za-z]{1,}\\.){1,2}[-A-Za-z]{2,}";
    private static final Pattern pattern = Pattern.compile(regex);

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        String[] components = data.split("\\s+");
        if(components.length != 4) throw new IllegalArgumentException("Неправильный формат ввода данных");
        if(!isValidEmailCheck(components[2]))  throw new IllegalArgumentException("Неправильный формат адреса email");
        if(!isValidPhoneCheck(components[3])) throw new IllegalArgumentException("Неправильный формат телефонного номера");
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public boolean isValidEmailCheck(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPhoneCheck(String phone) {
        phone.replaceAll("/([- _()+])?/", "");
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯёЁ]");
        Matcher matcher = pattern.matcher(phone);
        if(matcher.find()) throw new IllegalArgumentException("Неверный номер");
        if (phone.length() == 11) {
            if (phone.charAt(0) != '7' && phone.charAt(0) != '8') throw new IllegalArgumentException("Неверный номер");
        } else return true;
        return false;
    }


    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}