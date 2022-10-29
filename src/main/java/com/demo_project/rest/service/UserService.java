package com.demo_project.rest.service;

import com.demo_project.rest.dto.UserDTOCredential;
import com.demo_project.rest.dto.UserDTOUpdate;
import com.demo_project.rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(int id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();

    User createUser(UserDTOCredential userDTOCredential);

    void deleteUser(int id);

    void updateUser(UserDTOUpdate userDTOUpdate);

    boolean checkAuthPassword(String rawPassword,String hashPassword);


}
