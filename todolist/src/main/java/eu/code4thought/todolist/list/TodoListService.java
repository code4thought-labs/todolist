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

    public TodoList findTodoList(String name){
        TodoList todolist = listrepo.findByName(name);
        Iterable<ListItem> it = itemrepo.findByParentId(todolist.getId());
        List<ListItem> target = new ArrayList<>();
        it.forEach(target::add);
        todolist.addItems(target);
        return todolist;
    }

    public TodoList editTodoList(String name, String target){
        TodoList todolist = findTodoList(name);
        todolist.setName(target);
        saveTodoList(todolist);
        return todolist;
    }

    public void removeList(String name){
        TodoList todolist = findTodoList(name);
        Iterable<ListItem> it = itemrepo.findByParentId(todolist.getId());
        itemrepo.deleteAll(it);
        listrepo.delete(todolist);
    }

    public void saveTodoList(TodoList todoList){
        listrepo.save(todoList);
    }

    public Iterable<TodoList> findAllTodoLists() {
        // add their items too!
        Iterable<TodoList> it = listrepo.findAll();
        for (TodoList todolist: it) {
            findTodoList(todolist.getName());
        }
        return it;
    }

    public ListItem findListItem(String parentName, String description){
        TodoList todolist = findTodoList(parentName);
        ListItem child = null;
        Iterable<ListItem> it = itemrepo.findByParentId(todolist.getId());
        for (ListItem ch: it){
            if (ch.getDescription().equals(description)){
                child = ch;
            }
        }
        return child;
    }

    public TodoList addItem(String name, String description) {
        TodoList todolist = findTodoList(name);
        // Check if item already exists
//        List<ListItem> it = itemrepo.findByParentId(todolist.getId());
        ListItem item = todolist.createItem(description);
        saveListItem(item);
        return todolist;
    }

    public TodoList removeItem(String name, String itemDescription){
        TodoList element = findTodoList(name);
        ListItem elementItem = findListItem(element.getName(), itemDescription);
        element.remove(elementItem);
        itemrepo.delete(elementItem);
        element.remove(elementItem);
        saveTodoList(element);
        return element;
    }

    public TodoList editItem(String listName, String oldDescription, String newDescription){
        TodoList todolist = findTodoList(listName);
        ListItem item = findListItem(listName, oldDescription);
        todolist.edit(item, newDescription);
        saveListItem(item);
//        saveTodoList(todolist);
        return todolist;
    }

    public TodoList markComplete(String listName, String item){
        TodoList todolist = findTodoList(listName);
        ListItem listitem = findListItem(listName, item);
        listitem.setCompleted();
        saveListItem(listitem);
        return todolist;
    }

    public TodoList moveItem(String sourceName, String itemDescription, String targetName){
        TodoList source = findTodoList(sourceName);
        TodoList target = findTodoList(targetName);
        ListItem item = findListItem(sourceName, itemDescription);
        source.move(item, target);
        saveListItem(item);
        saveTodoList(source);
        saveTodoList(target);
        return target;
    }

    public void saveListItem(ListItem item){
        itemrepo.save(item);
    }

}
