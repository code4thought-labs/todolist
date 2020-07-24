package eu.code4thought.todolist.list;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository ("listItemRepository")
public interface ListItemRepository extends CrudRepository<ListItem, Integer> {

    Optional<ListItem> findById(int id);
    ListItem findByDescription(String description);
    Iterable<ListItem> findByParent(TodoList parent);
    Iterable<ListItem> findAll();
    void delete(ListItem item);
    void deleteAll();
}
