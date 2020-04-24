package eu.code4thought.todolist.list;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListItem {
    private String description = "";
    private int id;
    private Date last_updated = Calendar.getInstance().getTime();
    private boolean completed = false;
    private static int next_id = 0;

    public ListItem() {
        this("This is the default description of this task.");
    }

    public ListItem(String description) {
        this.description = description;
        this.id = next_id;
        next_id++;
        this.last_updated = Calendar.getInstance().getTime();
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.last_updated = Calendar.getInstance().getTime();
    }

    public Date getLastUpdated() {
        return last_updated;
    }

    public int getId() {
        return id;
    }
}
