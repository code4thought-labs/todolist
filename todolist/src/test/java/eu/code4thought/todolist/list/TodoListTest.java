package eu.code4thought.todolist.list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {
    private static TodoList todoList;

    @BeforeAll
    static void setUp(){
        todoList = new TodoList();
    }

    @Test
    void createItem() {
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);

        assertEquals(1, todoList.getLength());
        assertEquals(item, todoList.getItems().get(0));
        assertEquals(true, item.getDescription().equals(testDescription));
    }

    @Test
    void remove() {
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);
        todoList.remove(item);

        assertEquals(0, todoList.getLength());
    }

    @Test
    void edit() {
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);

        String differentDescription = "Different description.";
        todoList.edit(item, differentDescription);

        assertEquals(item.getDescription(), differentDescription);
    }

    @Test
    void move() {
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);

        TodoList to = new TodoList();

        todoList.move(item, to);

        assertEquals(1,to.getLength());
        assertEquals(0,todoList.getLength());
        assertEquals(true, item.getDescription().equals(testDescription));
    }
}