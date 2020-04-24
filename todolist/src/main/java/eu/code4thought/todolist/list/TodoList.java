package eu.code4thought.todolist.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoList {
    private String name;
    private long id;
    private List<ListItem> items;
    private static int next_id = 0;

    public TodoList() {
        this("defaultTodolist" + Integer.toString(next_id));
    }

    public TodoList(String name) {
        this.name = name;
        this.id = next_id;
        next_id++;
        this.items = new ArrayList<>();
    }

    public ListItem createItem(String description) {
        ListItem new_item =  new ListItem(description);
        this.items.add(new_item);
        return new_item;
    }

    public void remove(ListItem item) {
        this.items.remove(item);
    }

    public void edit(ListItem item, String description) {
        for (ListItem listItem : this.items) {
            if(listItem.getId() == item.getId()){
                listItem.setDescription(description);
            }
        }
    }

    public int getLength() {
        return this.items.size();
    }

    public List<ListItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getName() {
        return name;
    }

    public void move(ListItem item, TodoList to) {
        to.createItem(item.getDescription());
        this.remove(item);
    }

}
