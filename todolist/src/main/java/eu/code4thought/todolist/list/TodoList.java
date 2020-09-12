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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="todolist_id")
    private Long id;
    @Column(name="todolist_name")
    private String name;
    @Transient
    private List<ListItem> items;

    public TodoList() {
        this("defaultTodolist");
    }

    public TodoList(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public ListItem createItem(String description) {
        ListItem new_item =  new ListItem(description, this);
        this.items.add(new_item);
        return new_item;
    }

    public void addItem(ListItem item){
        this.items.add(item);
    }

    public void addItems(List<ListItem> itlist){
        for (ListItem it: itlist){
            this.items.add(it);
        }
    }

    public void edit(ListItem item, String description) {
        for (ListItem listItem : this.items) {
            if(listItem.equals(item)){
                listItem.setDescription(description);
                break;
            }
        }
    }

    public void move(ListItem item, TodoList to) {
        for (ListItem listItem : this.items) {
            if(listItem.equals(item)){
                item.setParentId(to.getId());
                to.addItem(item);
                this.remove(item);
                break;
            }
        }
    }

    public void remove(ListItem item) {
        this.items.remove(item);
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

    public void setName(String newName){
        this.name = newName;
    }

    public Long getId() { return this.id;}

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
