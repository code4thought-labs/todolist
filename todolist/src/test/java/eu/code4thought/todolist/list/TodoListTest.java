package eu.code4thought.todolist.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {
    private TodoList todoList;

    @BeforeEach
    void setUp(){
        System.out.println("Start");
        todoList = new TodoList();
    }

    @Test
    void createItem() {
        System.out.println("Testing createItem()...");
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);

        assertEquals(1, todoList.getLength());
        assertEquals(item, todoList.getItems().get(0));
        assertEquals(true, item.getDescription().equals(testDescription));
    }

    @Test
    void remove() {
        System.out.println("Testing remove()...");
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);
        todoList.remove(item);

        assertEquals(0, todoList.getLength());
    }

    @Test
    void edit() {
        System.out.println("Testing edit()...");
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);

        String differentDescription = "Different description.";
        todoList.edit(item, differentDescription);

        assertEquals(differentDescription, item.getDescription());
        assertEquals(1,todoList.getLength());
    }

    @Test
    void move() {
        System.out.println("Testing move()...");
        String testDescription = "Test description.";
        ListItem item = todoList.createItem(testDescription);

        TodoList to = new TodoList("newTodoList");

        todoList.move(item, to);

        assertEquals(1,to.getLength());
        assertEquals(0,todoList.getLength());
        assertEquals(true, item.getDescription().equals(testDescription));
    }

    @Test
    void equals() {
        System.out.println("Testing equals()...");
        TodoList to = new TodoList();

        assertFalse(todoList == to);
    }
}