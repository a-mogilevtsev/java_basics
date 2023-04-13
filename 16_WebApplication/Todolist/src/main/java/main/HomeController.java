package main;

import main.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import main.model.ToDoRepository;
/**
 * Created by a.sosnina on 3/15/2022.
 */

@Controller
public class HomeController {

    @Autowired
    private ToDoRepository todorepository;

    @RequestMapping(value = "/")
    public String index(Model model) {
        Iterable<ToDo> todoIterable =  todorepository.findAll();
        ArrayList<ToDo> toDosList = new ArrayList<>();
        for(ToDo todo : todoIterable) {
            toDosList.add(todo);
        }
        model.addAttribute("todosCount", toDosList.size());
        model.addAttribute("todos", toDosList);
        return "index";
    }
}
