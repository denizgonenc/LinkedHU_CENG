package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserById(UUID id);
    void addUser(User user);
    void deleteUserById(UUID id);
    void updateUser(User user);
    void saveVerificationToken(String token, User user);
    User verifyUser(String token);
    List<User> getAllUsers();

}
