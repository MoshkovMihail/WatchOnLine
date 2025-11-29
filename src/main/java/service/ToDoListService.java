package service;

import config.ConnectionManager;
import entity.ToDoListEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ToDoListService {

    List<ToDoListEntity> findByRoomId(long roomId);

    ToDoListEntity create(long roomId, long userId, String name);

    boolean deleteById(long id, long user_id);
}