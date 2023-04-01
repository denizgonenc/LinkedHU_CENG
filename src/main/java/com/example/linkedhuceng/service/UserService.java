package com.example.linkedhuceng.service;

import com.example.linkedhuceng.dao.FollowDao;
import com.example.linkedhuceng.dao.UserDao;
import com.example.linkedhuceng.entity.Post;
import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.linkedhuceng.security.UserRole.ADMIN;
import static com.example.linkedhuceng.security.UserRole.STUDENT;
import static com.example.linkedhuceng.security.UserRole.*;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    private final FollowDao followDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, FollowDao followDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.followDao = followDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
    }

    public Optional<User> getUserById(UUID id){
        return userDao.getUserById(id);
    }

    public Optional<User> getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }

    public User registerUser(UserModel userModel) throws IOException {
        String username = userModel.getEmail().split("[@]")[0];

        // check if user exists (username is tied to emails, therefore unique
        if (userDao.getUserByUsername(username).isPresent()){
            System.out.println("User already exists");
            return null;
        }

        User user = new User(
                username,
                passwordEncoder.encode(userModel.getPassword()),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                STUDENT, // TODO SET ADMIN
                true,
                true,
                true,
                false); // TODO

        userDao.addUser(user);
        return user;
    }

    public void deleteUserById(UUID id){
        userDao.deleteUserById(id);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

    public void saveVerificationToken(String token, User user) {
        userDao.saveVerificationToken(token, user);
    }

    public User verifyUser(String token) {
        return userDao.verifyUser(token);
    }

    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username).orElseThrow();
    }

    public boolean isFollowing(User from, User to){
        return followDao.isFollowing(from, to);
    }

    public void followUser(User from, User to){
        followDao.followUser(from, to);
    }

    public void unfollowUser(User from, User to){
        followDao.unfollowUser(from, to);
    }

    public void favPost(User user, Post post){
        user.favPost(post);
        userDao.updateUser(user);
    }

    public void unfavPost(User user, Post post){
        user.unfavPost(post);
        userDao.updateUser(user);
    }

    // idk if this logic should be here but idc enough rn
    public boolean isPostFaved(User user, Post post){
        return user.getFavouritePosts().contains(post);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
