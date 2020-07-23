package eu.code4thought.todolist.list;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository ("todoListRepository")
public interface TodoListRepository extends CrudRepository<TodoList, Long> {

    TodoList findById(int id);
    TodoList findByName(String name);
    Iterable<TodoList> findAll();
}

//@Repository
//public class TodoListRepositoryImpl implements TodoListRepository {
//    do i need to implement this?
//}