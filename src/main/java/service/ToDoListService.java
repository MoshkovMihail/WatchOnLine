package service;

import entity.ToDoListEntity;

import java.util.List;

public interface ToDoListService {

    List<ToDoListEntity> findByRoomId(long roomId);

    ToDoListEntity create(long roomId, long userId, String name);
}