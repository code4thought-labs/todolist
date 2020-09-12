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

    @PutMapping("/{list}")
    public TodoList editTodoList(@PathVariable String list, @RequestParam String action, @RequestParam String target){
        TodoList todolist = null;
        if (Objects.equals(action, "rename")){
            todolist = service.editTodoList(list, target);
        }
        return todolist;
    }

    @DeleteMapping("/{list}")
    public void removeList(@PathVariable String list, @RequestParam String action){
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

    // TODO: Update action returns todolist of double the real size, but not in DB. Duplication of entries.
    @PutMapping("/{list}/{item}")
    public TodoList editTodoListItem(@PathVariable String list, @PathVariable String item, @RequestParam String action, @RequestParam(required = false) String target) {
        TodoList todolist = new TodoList(list);
        if (Objects.equals(action.toLowerCase(), Actions.MOVE)){
            todolist = service.moveItem(list, item, target);
        }
        else if (Objects.equals(action.toLowerCase(), Actions.UPDATE)) {
            todolist = service.editItem(list, item, target);
        }
        else if (Objects.equals(action.toLowerCase(), Actions.MARK_COMPLETE)) {
            todolist = service.markComplete(list, item);
        }
        return todolist;
    }

    @DeleteMapping("/{list}/{item}")
    public TodoList removeListItem(@PathVariable String list, @PathVariable String item, @RequestParam String action){
        TodoList todolist = new TodoList(list);
        if (Objects.equals(action, "remove")){
            todolist = service.removeItem(list,item);
        }
        return todolist;
    }
}