import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) break;
            parseInput(input);
        }
    }

    public static void parseInput(String input) {
        String[] inputArray = input.split(" ");
        Actions action = getAction(inputArray[0]);
        int index = -1;
        int toDoIndex = 0;
        if (inputArray.length > 1) {
            if (isIndex(inputArray[1])) {
                index = Integer.parseInt(inputArray[1]);
                toDoIndex = 2;
            } else toDoIndex = 1;
        }
        String toDo = getToDoFromInput(inputArray, toDoIndex);
        makeAction(action, index, toDo);
    }

    public static void makeAction(Actions act, int index, String todo) {
        switch (act) {
            case ADD: todoList.add(index, todo); break;
            case EDIT: todoList.edit(todo, index); break;
            case DELETE: todoList.delete(index); break;
            case LIST:
                System.out.println(todoList.toString()); break;
            default: break;
        }
    }

    public static boolean isIndex(String input) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) return false;
        else return true;
    }

    public static Actions getAction(String act) {
        Actions action = Actions.LIST;
        if (act.equalsIgnoreCase("add")) action = Actions.ADD;
        else if(act.equalsIgnoreCase("delete")) action = Actions.DELETE;
        else if(act.equalsIgnoreCase("edit")) action = Actions.EDIT;
        return action;
     }

    public static String getToDoFromInput(String[] input, int toDoIndex) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = toDoIndex; i < input.length; i++) {
            joiner.add(input[i]);
        }
        return joiner.toString();
    }


}
