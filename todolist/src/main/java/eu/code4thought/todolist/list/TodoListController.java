package eu.code4thought.todolist.list;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    @GetMapping("/{description}")
    public TodoList getOne(@PathVariable String description) {
        return new TodoList(description);
        // Connect to: http://localhost:8080/todolist/123
        // or Curl: `curl localhost:8080/todolist/123`
    }
}