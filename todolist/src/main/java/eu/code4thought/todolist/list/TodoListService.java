package eu.code4thought.todolist.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service ("todoListService")
public class TodoListService {
    @Autowired
    private TodoListRepository listrepo;
    @Autowired
    private ListItemRepository itemrepo;

    public TodoListService() {}

    public TodoList saveTodoList(TodoList todoList){
        TodoList element = null;
        try {
            element = listrepo.save(todoList);
        }
        catch(Exception e){
            System.out.println("Some message:"+ e.getMessage());
        }
        return element;
    }

    public ListItem saveListItem(ListItem item){
        ListItem element = null;
        try {
            element = itemrepo.save(item);
        }
        catch(Exception e){
            System.out.println("Some message about items:"+ e.getMessage());
        }
        return element;
    }

    public ListItem findListItem(String parentName, String description){
        // Next step: Use parent, so you can allow distinct items, same description, different parents.
//        TodoList parent = findTodoList(parentName);
        ListItem child = itemrepo.findByDescription(description);
        return child;
    }

    // Not needed yet, but it will make my life easier down the road.
//    public static Iterable<ListItem> findTodoListItems(String parentName){
//        TodoList parent = findTodoList(parentName);
//        return itemrepo.findByParent(parent);
//    }

    public TodoList findTodoList(String name){
        return listrepo.findByName(name);
    }

    public Iterable<TodoList> findAllTodoLists() {
        return listrepo.findAll();
    }

    public TodoList addItem(String name, String description) {
        TodoList element = findTodoList(name);
        // Check if item already exists
        ListItem item = element.createItem(description);
        saveTodoList(element); // Seems like .save() checks for existence and handles .update() too.
        saveListItem(item);
        return element;
    }

    public TodoList removeItem(String name, String itemDescription){
        TodoList element = findTodoList(name);
        ListItem elementItem = findListItem(element.getName(), itemDescription);
        element.remove(elementItem);
        itemrepo.delete(elementItem);
        return saveTodoList(element); // Seems like .save() checks for existence and handles .update() too.
    }

    public TodoList editItem(String name, String oldDescription, String newDescription){
        TodoList element = findTodoList(name);
        ListItem elementItem = findListItem(element.getName(), oldDescription);
        element.edit(elementItem, newDescription);
        // save TodoList or both TodoList and ListItem. I have to check this.
        saveListItem(elementItem);
        return saveTodoList(element); // Seems like .save() checks for existence and handles .update() too.
    }

    public TodoList moveItem(String sourceName, String targetName, String itemDescription){
        TodoList source = findTodoList(sourceName);
        TodoList target = findTodoList(targetName);
        ListItem item = findListItem(source.getName(), itemDescription);
        source.move(item, target);
        // Chose to return target for better user experience
        return saveTodoList(target); // Seems like .save() checks for existence and handles .update() too.
    }

    public void removeList(String name){
        TodoList element = findTodoList(name);
        element.removeAllItems();
        listrepo.delete(element);
    }
}
