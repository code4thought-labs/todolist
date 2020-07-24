package eu.code4thought.todolist.list;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todolist")
public class TodoListController {

    @GetMapping("/")
    public Iterable<TodoList> getAll() {
        // Here there is a NullPointerException. Fix this.
        return TodoListService.findAllTodoLists();
    }

    @GetMapping("/{list}")
    public TodoList getOne(@PathVariable String list){
        return TodoListService.findTodoList(list);
    }

    @GetMapping("/{list}/{item}")
    public ListItem getOneItem(@PathVariable String list, @PathVariable String item){
        return TodoListService.findListItem(list, item);
    }

    @PostMapping("/{list}")
    public TodoList createTodoList(@PathVariable String list){
        return TodoListService.saveTodoList(new TodoList(list));
    }

    @PostMapping("/add/{list}/{item}")
    public TodoList addListItem(@PathVariable String list, @PathVariable String item){
        return TodoListService.addItem(list, item);
    }

    @DeleteMapping("/remove/{list}/{item}")
    public TodoList removeListItem(@PathVariable String list, @PathVariable String item){
        return TodoListService.removeItem(list, item);
    }

    @DeleteMapping("/remove/{list}")
    public void removeListItem(@PathVariable String list){
        TodoListService.removeList(list);
    }

    @PutMapping("/move/{list}/{item}/{target}")
    public TodoList moveTodoList(@PathVariable String list, @PathVariable String item, @PathVariable String target) {
        return TodoListService.moveItem(list, target, item);
    }

    @PutMapping("/update/{list}/{oldItem}/{newItem}")
    public TodoList updateTodoList(@PathVariable String list, @PathVariable String oldItem, @PathVariable String newItem){
                return TodoListService.editItem(list, oldItem, newItem);
    }
}