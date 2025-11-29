package service;

import config.ConnectionManager;
import entity.ToDoItemEntity;

import java.sql.*;
import java.util.List;

public interface ToDoItemService {
    List<ToDoItemEntity> findByListId(long listId);

    ToDoItemEntity create(long listId, String text, Timestamp deadline);

    boolean deleteById(long id);

    void toggleDone(long itemId);
}
