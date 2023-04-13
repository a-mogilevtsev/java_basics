package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by a.sosnina on 3/14/2022.
 */
@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
}
