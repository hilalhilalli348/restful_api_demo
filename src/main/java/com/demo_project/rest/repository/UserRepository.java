package com.demo_project.rest.repository;

import com.demo_project.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    Optional<User> findUserById(int id);
    Optional<User> findByUsername(String username);



 }
