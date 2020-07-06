package eu.code4thought.todolist.list;

import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {

    TodoList findById(long id);
}
