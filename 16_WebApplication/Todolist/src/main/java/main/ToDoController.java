package main;

import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by a.sosnina on 2/14/2022.
 */
@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository todorepository;

    @RequestMapping(value = "/todos/", method = RequestMethod.GET)
    public List<ToDo> list() {
        Iterable<ToDo> todoIterable =  todorepository.findAll();
        ArrayList<ToDo> toDosList = new ArrayList<>();
        for(ToDo todo : todoIterable) {
            toDosList.add(todo);
        }
        return toDosList;
    }

    @RequestMapping(value = "/todos/", method = RequestMethod.POST)
    public int add(ToDo todo) {
        ToDo toDo = todorepository.save(todo);
        return toDo.getId();
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity getTodo(@PathVariable int id) {
        Optional<ToDo> toDoOptional = todorepository.findById(id);
        if(!toDoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else return new ResponseEntity(toDoOptional.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable int id) {
        todorepository.deleteById(id);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PATCH)
    public ResponseEntity updateTodo(@PathVariable int id, ToDo toDo) {
        Optional<ToDo> toDoOptional = todorepository.findById(id);
        if(!toDoOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else {
            ToDo toUpdate = toDoOptional.get();
            if(!toDo.getDescription().isEmpty()) toUpdate.setDescription(toDo.getDescription());
            if(!toDo.getDate().isEmpty()) toUpdate.setDate(toDo.getDate());
            if(toDo.isDone() != toUpdate.isDone()) toUpdate.setDone(toDo.isDone());
            todorepository.save(toUpdate);
            return new ResponseEntity(toUpdate, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
    public void putTodo(@PathVariable int id, ToDo toDo) {
        toDo.setId(id);
        todorepository.save(toDo);
    }
}
