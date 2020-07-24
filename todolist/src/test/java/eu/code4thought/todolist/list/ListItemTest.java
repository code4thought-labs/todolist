package eu.code4thought.todolist.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ListItemTest {
    private ListItem listItem;

    @BeforeEach
    void setUp(){
        System.out.println("Start");
        listItem = new ListItem();
    }

    @Test
    void equals() {
        System.out.println("Testing equals()...");
        ListItem to = new ListItem();

        assertFalse(listItem == to);
    }
}
