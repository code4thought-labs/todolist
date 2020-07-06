package eu.code4thought.todolist.list;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="todolist")
public class TodoList {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private List<ListItem> items;
    private static long next_id = 0;

    public TodoList() {
        this("defaultTodolist" + String.valueOf(next_id));
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
