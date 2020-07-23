package eu.code4thought.todolist.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service ("todoListService")
public class TodoListService {
    @Autowired
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

    public static TodoList findTodoList(String name){
        return database.findByName(name);
    }

    public static Iterable<TodoList> findAllTodoLists() {
        return database.findAll();
    }
}
