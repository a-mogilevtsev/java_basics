package main;

import main.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by a.sosnina on 2/14/2022.
 */
public class Storage {
    private static int currentId = 0;
    private static ConcurrentHashMap<Integer, ToDo> todoStorage = new ConcurrentHashMap<>();

    public static List<ToDo> getAllTodos() {
        ArrayList<ToDo> allTodos = new ArrayList<>();
        allTodos.addAll(todoStorage.values());
        return allTodos;
    }

    public static int add(ToDo todo) {
        currentId++;
        todo.setId(currentId);
        todoStorage.put(currentId, todo);
        return currentId;
    }

    public static ToDo getTodo(int id) {
        return todoStorage.get(id);
    }

    public static void deleteTodo(int id) {
        todoStorage.remove(id);
    }

    public static void updateTodo(int id, ToDo todo) {
        todoStorage.put(id, todo);
    }
}
