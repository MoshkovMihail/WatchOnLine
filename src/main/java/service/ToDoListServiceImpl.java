package service;

import dao.ToDoListDAO;
import entity.ToDoListEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListDAO toDoListDAO;

    @Override
    public List<ToDoListEntity> findByRoomId(long roomId) {
        return toDoListDAO.findByRoomId(roomId);
    }

    @Override
    public ToDoListEntity create(long roomId, long userId, String name) {
        return toDoListDAO.create(roomId, name, userId);
    }

    @Override
    public boolean deleteById(long id, long user_id) {
        return toDoListDAO.deleteById(id, user_id);
    }
}