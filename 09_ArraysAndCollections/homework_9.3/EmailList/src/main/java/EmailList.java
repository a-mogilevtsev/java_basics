import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {
    private final Set<String> emailList;
    private static final String regex = "^(([0-9A-Za-z]{1}[-0-9A-z\\.]{1,}[0-9A-Za-z]{1}))@([-A-Za-z]{1,}\\.){1,2}[-A-Za-z]{2,}";
    private static final Pattern pattern = Pattern.compile(regex);
    public EmailList() {
        emailList = new TreeSet<>();
    }

    public EmailList(TreeSet<String> emailList) {
        this.emailList = emailList;
    }

    public void add(String email) {
        if(isCorrectEmail(email))
        emailList.add(email.toLowerCase());
    }

    public List<String> getSortedEmails() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(emailList);
        return list;
    }

    public boolean isCorrectEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for(String email : emailList){
            joiner.add(email.toLowerCase());
        }
        return joiner.toString();
    }
}
