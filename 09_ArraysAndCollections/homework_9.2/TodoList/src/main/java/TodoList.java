import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> toDoList;

    public TodoList() {
        toDoList = new ArrayList<>();
    }

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        toDoList.add(todo);
        System.out.printf("Добавлено дело '%s' \n", todo);
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if(isCorrectNumberToAdd(index)) {
            toDoList.add(index, todo);
            System.out.printf("Добавлено дело '%s' \n", todo);
        }
        else {
            toDoList.add(todo);
            System.out.printf("Добавлено дело '%s'\n", todo);
        }
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if(isCorrectNumberToEdit(index)) {
            String beforeEdit = toDoList.get(index);
            toDoList.remove(index);
            toDoList.add(index, todo);
            System.out.printf("Дело '%s' заменено на '%s' \n",beforeEdit, todo);

        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if(isCorrectNumberToEdit(index)) {
            String beforDelete = toDoList.get(index);
            toDoList.remove(index);
            System.out.printf("Дело '%s' удалено \n", beforDelete);
        }
        else System.out.println ("Дело с таким номером не существует \n");
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return toDoList;
    }

    public boolean isCorrectNumberToAdd(int number) {
        if(number > toDoList.size() || number < 0) return false;
        else return true;
        }


    public boolean isCorrectNumberToEdit(int number) {
        if(number > toDoList.size() - 1) return false;
        else return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < toDoList.size(); i++) {
            result = result +  "\n" + i + " " + toDoList.get(i);
        }
        return result;
    }
}
