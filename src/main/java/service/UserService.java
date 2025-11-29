package service;

import entity.UserEntity;

public interface UserService {
    UserEntity authenticateUser(String username, String password);

    boolean saveUserInDb(String username, String email, String password);

    boolean isUserExist(String username);

    void deleteUserById(Long id);

    boolean updateUsername(String newUsername, Long id);

    boolean updateAvatarPath(long userId, String avatarPath);
}
