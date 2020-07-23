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
    // @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="todolist_id")
    private int id;
    @Column(name="todolist_name")
    private String name;
    @Transient
    private List<ListItem> items;

    public TodoList() {
        this("defaultTodolist");
    }

    public TodoList(String name) {
        this.name = name;
        this.id = System.identityHashCode(System.nanoTime());
        this.items = new ArrayList<>();
    }

    public ListItem createItem(String description) {
        ListItem new_item =  new ListItem(description);
//      alternatively  item's father = this list
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
