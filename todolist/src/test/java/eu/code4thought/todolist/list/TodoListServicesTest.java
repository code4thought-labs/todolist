package eu.code4thought.todolist.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoListServicesTest {
    private TodoList todoList;
    @InjectMocks
    private TodoListService service;
    @Mock
    private TodoListRepository listrepo;
    @Mock
    private ListItemRepository itemrepo;
    private ListItem item;

    @BeforeEach
    void setUp(){
        System.out.println("Start");
        service = new TodoListService();
        MockitoAnnotations.initMocks(this);
        todoList = new TodoList();
        item = todoList.createItem("Test description");
    }

    @Test
    void editItem(){
        String testDescription = "Changed description";

        TodoList test = service.editItem(todoList.getName(), "Test description", testDescription);

        assertEquals(1, todoList.getLength());
        assertEquals(true, item.getDescription().equals(testDescription));

    }
}
