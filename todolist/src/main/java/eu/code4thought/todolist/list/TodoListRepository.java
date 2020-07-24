package eu.code4thought.todolist.list;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository ("todoListRepository")
public interface TodoListRepository extends CrudRepository<TodoList, Integer> {

    Optional<TodoList> findById(int id);
    TodoList findByName(String name);
    Iterable<TodoList> findAll();
    void delete(TodoList list);
    void deleteAll();
}
