import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    Map<String, String> phoneBook = new HashMap<>();

    public void addContact(String phone, String name) {
        if(isCorrectName(name) && isCorrectPhoneNumber(phone)) {
            phoneBook.put(phone, name);
            System.out.println("Контакт сохранен!");
        }
    }

    public String getContactByPhone(String phone) {
        String name = phoneBook.get(phone);
        return name + " - " + phone;
    }

    public Set<String> getContactByName(String name) {
        TreeSet<String> set = new TreeSet<>();
        Set<String> phones = phoneBook.keySet();
        StringJoiner joiner = new StringJoiner(", ");
        for(String phone : phones) {
            String contactName = phoneBook.get(phone);
            if(name.equalsIgnoreCase(contactName.toLowerCase())) {
                joiner.add(phone);
            }
        }
        String contact = name + " - " + joiner.toString();
        set.add(contact);
        return set;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> set = new TreeSet<>();
        Map<String, String> nameAndPhones = new TreeMap<>();
        Set<String> phones = phoneBook.keySet();
        for(String phone : phones) {
            String contactName = phoneBook.get(phone);
            if(!nameAndPhones.containsKey(contactName)) nameAndPhones.put(contactName, phone);
            else {
                String phoneNew = nameAndPhones.get(contactName) + ", " + phone;
                nameAndPhones.put(contactName, phoneNew);
            }
        }
        for(String name : nameAndPhones.keySet()) {
            String contactInfo = name +" - " + nameAndPhones.get(name);
            set.add(contactInfo);
        }
        return set;
    }

    public boolean isCorrectPhoneNumber(String phone) {
        Pattern phonePattern = Pattern.compile("^7[0-9]{10}");
        Matcher matcher = phonePattern.matcher(phone);
        return matcher.matches();
    }

    public boolean isCorrectName(String name) {
        Pattern namePattern = Pattern.compile("[a-zA-Zа-яА-ЯёЁ]+");
        Matcher matcher = namePattern.matcher(name);
        return matcher.find();
    }

    @Override
    public String toString() {
        Set<String> contactsSet = getAllContacts();
        StringJoiner joiner = new StringJoiner("\n");
        for(String contact : contactsSet) {
            joiner.add(contact);
        }
        return joiner.toString();
    }

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}