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
        // Here there is a NullPointerException. Fix this.
        return service.findAllTodoLists();
    }

    @GetMapping("/{list}")
    public TodoList getOne(@PathVariable String list){
        return service.findTodoList(list);
    }

    @GetMapping("/{list}/{item}")
    public ListItem getOneItem(@PathVariable String list, @PathVariable String item){
        return service.findListItem(list, item);
    }

    @PostMapping("/{list}")
    public TodoList createTodoList(@PathVariable String list){
        return service.saveTodoList(new TodoList(list));
    }

    @PostMapping("/add/{list}/{item}")
    public TodoList addListItem(@PathVariable String list, @PathVariable String item){
        return service.addItem(list, item);
    }

    @DeleteMapping("/remove/{list}/{item}")
    public TodoList removeListItem(@PathVariable String list, @PathVariable String item){
        return service.removeItem(list, item);
    }

    @DeleteMapping("/remove/{list}")
    public void removeListItem(@PathVariable String list){
        service.removeList(list);
    }

    @PutMapping("/move/{list}/{item}/{target}")
    public TodoList moveTodoList(@PathVariable String list, @PathVariable String item, @PathVariable String target) {
        return service.moveItem(list, target, item);
    }

    @PutMapping("/update/{list}/{oldItem}/{newItem}")
    public TodoList updateTodoList(@PathVariable String list, @PathVariable String oldItem, @PathVariable String newItem){
        return service.editItem(list, oldItem, newItem);
    }
}