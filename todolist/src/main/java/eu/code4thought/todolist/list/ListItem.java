package eu.code4thought.todolist.list;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
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
    @LastModifiedDate
    private Date last_updated;
    @Column(name="completed")
    private boolean completed = false;

    public ListItem() {
        this("This is the default description of this task.", new TodoList("fosterParent"));
    }

    public ListItem(String description, TodoList parent) {
        this.description = description;
        this.parentId = parent.getId();
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted() {
        this.completed = !this.isCompleted();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdated() {
        return last_updated;
    }

    public Long getId() {
        return this.id;
    }

    public Long getParentId(){
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.getParentId());
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
