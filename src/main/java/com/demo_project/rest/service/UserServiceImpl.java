package com.demo_project.rest.service;

import com.demo_project.rest.model.Role;
import com.demo_project.rest.repository.RoleRepository;
import com.demo_project.rest.dto.UserDTOCredential;
import com.demo_project.rest.dto.UserDTOUpdate;
import com.demo_project.rest.exception.ResourceConflictException;
import com.demo_project.rest.exception.ResourceIsEmptyException;
import com.demo_project.rest.exception.ResourceNotFountException;
import com.demo_project.rest.model.User;
import com.demo_project.rest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserById(int id) {
        Optional<User> user = userRepository.findUserById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFountException("id = "+id+" olan user movcut deyil!");
        }
        return user;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.size() == 0) {
            throw new ResourceIsEmptyException("Table-da qeyd yoxdur !");

        }
        return userList;
    }

    @Override
    public User createUser(UserDTOCredential userDTOCredential) {

        if (userRepository.findByUsername(userDTOCredential.getUsername()).isPresent()){
            throw new ResourceConflictException(userDTOCredential.getUsername() + " bu adlı user mövcutdur,başqa ad istifadə edin!");
        }

        User user = convertToUser(userDTOCredential);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        enrichUser(user);
        return  userRepository.save(user);
    }

    private void enrichUser(User user) {

        Role userRole = roleRepository.findByRole("ROLE_USER");

        if (userRole == null) {
            Role newRole = new Role("ROLE_USER");
            roleRepository.save(newRole);
            user.setRole(newRole);
            return;
        }

        user.setRole(userRole);
    }

    private User convertToUser(UserDTOCredential userDTOCredential) {
        return modelMapper.map(userDTOCredential, User.class);
    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.findUserById(id).isEmpty()){
            throw new ResourceNotFountException("id = "+id + " olan user mövcut deyil!");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(UserDTOUpdate userDTOUpdate) {
        Optional<User> userFromDb = userRepository.findUserById(userDTOUpdate.getId());

        if (userFromDb.isEmpty()) {
            throw new ResourceNotFountException("Belə user mövcut deyil!");
        }

        userFromDb.get().setUsername(
                userDTOUpdate.getUsername() == null ?
                userFromDb.get().getUsername() : userDTOUpdate.getUsername()
        );

        userFromDb.get().setPassword(
                userDTOUpdate.getPassword() == null ?
                userFromDb.get().getPassword() : userDTOUpdate.getPassword()
        );

        userRepository.save(userFromDb.get());


    }

    @Override
    public boolean checkAuthPassword(String rawPassword, String hashPassword) {
        return passwordEncoder.matches(rawPassword,hashPassword);
    }


}
