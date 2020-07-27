package eu.code4thought.todolist.list;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="listitem")
public class ListItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "listitem_id")
    private Long id;
    @Column(name="listitem_desc")
    private String description = "";
    @Column(name = "todolist_id")
    public Long parentId;
    @Column(name="last_updated")
    private Date last_updated = Calendar.getInstance().getTime();
    @Column(name="completed")
    private boolean completed = false;

    public ListItem() {
        this("This is the default description of this task.", new TodoList("fosterParent"));
    }

    public ListItem(String description, TodoList parent) {
        this.description = description;
        // this.id = System.identityHashCode(System.nanoTime());
        this.parentId = parent.getId();
        this.last_updated = Calendar.getInstance().getTime();
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.last_updated = Calendar.getInstance().getTime();
    }

    public Date getLastUpdated() {
        return last_updated;
    }

    public Long getId() {
        return this.id;
    }

    public Long getParent(){
        return this.parentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.getParent());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        ListItem other = (ListItem) obj;
        return Objects.equals(this.getId(), other.getId());
    }
}
