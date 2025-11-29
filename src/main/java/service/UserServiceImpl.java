package service;

import dao.UserDAO;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import util.HashUtil;


@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserEntity authenticateUser(String username, String password) {
        UserEntity user = userDAO.getUser(username);

        if (user == null){
            System.out.println("а всё");
            return null;

        }

        if (HashUtil.verify(password, user.getHashPassword())) {
            return user;
        }

        return null;
    }

    public boolean saveUserInDb(String username, String email, String password) {
        if (userDAO.isUserExist(username)){
            return false;
        }

        String hash_password = HashUtil.hashPassword(password);

        userDAO.saveNewUser(username, email, hash_password);
        return true;
    }

    public boolean isUserExist(String username){
        return userDAO.isUserExist(username);
    }

    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public boolean updateUsername(String newUsername, Long id) {
        if(newUsername == null || newUsername.trim().isBlank() || userDAO.isUserExist(newUsername)){
            return false;
        }

        userDAO.updateUsername(newUsername, id);
        return true;
    }

    @Override
    public boolean updateAvatarPath(long userId, String avatarPath) {
        return userDAO.updateAvatarPath(userId, avatarPath);
    }
}
