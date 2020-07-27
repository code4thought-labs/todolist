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

    public void saveTodoList(TodoList todoList){
        listrepo.save(todoList);
    }

    public void saveListItem(ListItem item){
        itemrepo.save(item);
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
        TodoList todolist = listrepo.findByName(name);
        Iterable<ListItem> it = itemrepo.findByParentId(todolist.getId());
        List<ListItem> target = new ArrayList<>();
        it.forEach(target::add);
        todolist.addItems(target);
        return todolist;
    }

    public Iterable<TodoList> findAllTodoLists() {
        // add their items too!
        Iterable<TodoList> it = listrepo.findAll();
        for (TodoList todolist: it) {
            findTodoList(todolist.getName());
        }
        return it;
    }

    public TodoList addItem(String name, String description) {
        TodoList todolist = findTodoList(name);
        // Check if item already exists
        ListItem item = todolist.createItem(description);
        saveListItem(item);
//        saveTodoList(todolist); // Seems like .save() checks for existence and handles .update() too.
        return todolist;
    }

    public TodoList removeItem(String name, String itemDescription){
        TodoList element = findTodoList(name);
        ListItem elementItem = findListItem(element.getName(), itemDescription);
        element.remove(elementItem);
        itemrepo.delete(elementItem);
        saveTodoList(element); // Seems like .save() checks for existence and handles .update() too.
        return element;
    }

    public TodoList editItem(String name, String oldDescription, String newDescription){
        TodoList element = findTodoList(name);
        ListItem elementItem = findListItem(element.getName(), oldDescription);
        element.edit(elementItem, newDescription);
        // save TodoList or both TodoList and ListItem. I have to check this.
        saveListItem(elementItem);
        saveTodoList(element); // Seems like .save() checks for existence and handles .update() too.
        return element;
    }

    public TodoList moveItem(String sourceName, String targetName, String itemDescription){
        TodoList source = findTodoList(sourceName);
        TodoList target = findTodoList(targetName);
        ListItem item = findListItem(source.getName(), itemDescription);
        source.move(item, target);
        // Chose to return target for better user experience
        saveTodoList(target); // Seems like .save() checks for existence and handles .update() too.
        return target;
    }

    public void removeList(String name){
        TodoList element = findTodoList(name);
        element.removeAllItems();
        // deleteAll(element.items); implement it. not right.
        listrepo.delete(element);
    }
}
