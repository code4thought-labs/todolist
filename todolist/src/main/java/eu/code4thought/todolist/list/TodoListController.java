package eu.code4thought.todolist.list;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id) {
        return "Hello " + id;
        // Connect to: http://localhost:8080/todolist/123
        // or Curl: `curl localhost:8080/todolist/123`
    }
}