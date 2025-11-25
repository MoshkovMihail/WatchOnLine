package service;

import entity.RoomEntity;
import entity.ToDoListEntity;

public interface ToDoListService {
    boolean createEmptyToDoList(RoomEntity room);

    boolean addTaskInToDoList(ToDoListEntity list);


}
