package eu.code4thought.todolist.list;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    @Autowired
    private TodoListService service;

    @GetMapping("/")
    public Iterable<TodoList> getAll() {
        return service.findAllTodoLists();
    }

    @GetMapping("/{list}")
    public TodoList getOne(@PathVariable String list){
        return service.findTodoList(list);
    }

    @PostMapping("/{list}")
    public TodoList createTodoList(@PathVariable String list, @RequestParam String action){
        TodoList todolist = new TodoList(list);
        if (Objects.equals(action, "create")) {
            service.saveTodoList(todolist);
        }
        return todolist;
    }

    // TODO: Not saved in the database
    @PutMapping("/{list}")
    public TodoList editTodoList(@PathVariable String list, @RequestParam String action, @RequestParam String target){
        TodoList todolist = null;
        if (Objects.equals(action, "rename")){
            todolist = service.editTodoList(list, target);
        }
        return todolist;
    }

    // TODO: Not working
    @DeleteMapping("/{list}")
    public void removeListItem(@PathVariable String list, @RequestParam String action){
        if (Objects.equals(action, "remove")){
            service.removeList(list);
        }
    }

    @GetMapping("/{list}/{item}")
    public ListItem getOneItem(@PathVariable String list, @PathVariable String item){
        return service.findListItem(list, item);
    }

    @PostMapping("/{list}/{item}")
    public TodoList addListItem(@PathVariable String list, @PathVariable String item, @RequestParam String action){
        TodoList todolist = null;
        if (Objects.equals(action, "add")){
            todolist = service.addItem(list,item);
        }
        return todolist;
    }

    // TODO: Returns wrong state of todolist. Probably not saved.
    // TODO: To not be error prone, interface with constants. See Actions.java.
    @PutMapping("/{list}/{item}")
    public TodoList moveTodoList(@PathVariable String list, @PathVariable String item, @RequestParam String action, @RequestParam(required = false) String target) {
        TodoList todolist = new TodoList(list);
        if (Objects.equals(action.toLowerCase(), Actions.MOVE)){
            todolist = service.moveItem(list, item, target);
        }
        else if (Objects.equals(action, "update")) {
            todolist = service.editItem(list, item, target);
        }
        else if (Objects.equals(action, "complete")) {
            todolist = service.markComplete(list, item);
        }
        return todolist;
    }

    // TODO: Returns wrong state of todolist. Probably not saved.
    @DeleteMapping("/{list}/{item}")
    public TodoList removeListItem(@PathVariable String list, @PathVariable String item, @RequestParam String action){
        TodoList todolist = new TodoList(list);
        if (Objects.equals(action, "remove")){
            todolist = service.removeItem(list,item);
        }
        return todolist;
    }
}