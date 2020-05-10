package eu.code4thought.todolist.list;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    private List<TodoList> database = new ArrayList<TodoList>(); // initialize list with items

    @GetMapping("/{name}")
    public TodoList getOne(@PathVariable String name) {
        TodoList tempList = new TodoList();
        try{
            tempList = searchDatabase(name);
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
            // Maybe not System.out.println but return the message to user (somehow)... Explore this.
        }
        return tempList;
        // Connect to: http://localhost:8080/todolist/123
        // or Curl: `curl localhost:8080/todolist/123`
    }

    @GetMapping("/")
    public List<TodoList> getAll() {
        return this.database;
    }

    // Explore this method's appropriate home class. Does not belong among endpoints.
    private TodoList searchDatabase(String name){
        for(TodoList list : database)
        {
            // instead of looping through database, use hashtable for O(1) access.
            if (list.getName() == name){
                return list;
            }
        }
        throw new NoSuchElementException("Requested TodoList not found. To create a new todoList <guidelines>.");
    }
}