package eu.code4thought.todolist.list;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="todolist")
    private List<ListItem> items;

    public TodoList() {
        this("defaultTodolist");
    }

    public TodoList(String name) {
        this.name = name;
        this.id = System.identityHashCode(System.nanoTime()); // Not beautiful, please make @GeneratedValue work.
        this.items = new ArrayList<>();
    }

    public ListItem createItem(String description) {
        ListItem new_item =  new ListItem(description, this);
        this.items.add(new_item);
        return new_item;
    }

    public void remove(ListItem item) {
        this.items.remove(item);
    }

    public void removeAllItems(){
        for (ListItem listItem : this.items) {
            remove(listItem);
        }
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
        return this.name;
    }

    public int getId() { return this.id;}

    public void move(ListItem item, TodoList to) {
        to.createItem(item.getDescription());
        this.remove(item);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TodoList other = (TodoList) obj;
        return Objects.equals(this.id, other.getId());
    }

}
