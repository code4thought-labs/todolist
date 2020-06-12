package eu.code4thought.todolist.list;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    private final static List<TodoList> database = new ArrayList<>();
    static {
        database.add(new TodoList("first_todoList"));
    }

    @GetMapping("/{name}")
    public TodoList getOne(@PathVariable String name){
        TodoList tempList = null;
        try{
            tempList = findTodoListByName(name);
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
            // Maybe not System.out.println but return the message to user (somehow)... Explore this.
        }
        return tempList;
        // Why this is not showing anything, but getAll() works?
        // Connect to: http://localhost:8080/todolist/123
        // or Curl: `curl localhost:8080/todolist/123`
    }

    @GetMapping("/")
    public List<TodoList> getAll() {
        return this.database;
    }

    @PostMapping("/{name}")
    public TodoList createTodoList(@PathVariable String name){
        // First check if the list already exists
        TodoList tempCreate = new TodoList(name);
        database.add(tempCreate);
        return tempCreate;
    }

    // Explore this method's appropriate home class. Does not belong among endpoints.
    private TodoList findTodoListByName(String name) throws NoSuchElementException {
        for(TodoList list : database)
        {
            // instead of looping through database, use hashtable for O(1) access.
            if (list.getName().equals(name)){
                return list;
            }
        }
        throw new NoSuchElementException("Requested TodoList not found. To create a new TodoList <guidelines>.");
    }
}