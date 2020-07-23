package eu.code4thought.todolist.list;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ListItem {
    private String description = "";
    private int id;
    private TodoList parent;
    private Date last_updated = Calendar.getInstance().getTime();
    private boolean completed = false;

    public ListItem() {
        this("This is the default description of this task.", new TodoList("fosterParent"));
    }

    public ListItem(String description, TodoList parent) {
        this.description = description;
        this.id = System.identityHashCode(System.nanoTime());
        this.parent = parent;
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

    public int getId() {
        return this.id;
    }

    public TodoList getParent(){
        return this.parent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.parent.getId());
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
        return Objects.equals(id, other.getId());
    }
}
