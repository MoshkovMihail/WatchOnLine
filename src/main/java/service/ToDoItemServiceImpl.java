package service;

import dao.ToDoItemDAO;
import entity.ToDoItemEntity;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
public class ToDoItemServiceImpl implements ToDoItemService{
    private final ToDoItemDAO toDoItemDAO;

    @Override
    public List<ToDoItemEntity> findByListId(long listId) {
        return toDoItemDAO.findByListId(listId);
    }

    @Override
    public ToDoItemEntity create(long listId, String text, Timestamp deadline) {
        return toDoItemDAO.create(listId, text, deadline);
    }

    @Override
    public boolean deleteById(long id) {
        return toDoItemDAO.deleteById(id);
    }

    public void toggleDone(long itemId) {
        toDoItemDAO.toggleDone(itemId);
    }
}
