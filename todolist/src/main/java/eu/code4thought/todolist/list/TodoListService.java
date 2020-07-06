package eu.code4thought.todolist.list;

public class TodoListService {
    private static TodoListRepository database;

    public TodoListService(TodoListRepository database) {
        this.database = database;
    }

    public static TodoList saveTodoList(TodoList  todoList){
        TodoList element = null;
        try {
            element = database.save(todoList);
        }
        catch(Exception e){
            System.out.println("Some message:"+ e.getMessage());
        }
        return element;
    }
}
