package com.example.linkedhuceng.dao;

import com.example.linkedhuceng.entity.User;
import com.example.linkedhuceng.entity.VerificationToken;
import com.example.linkedhuceng.repository.TokenRepository;
import com.example.linkedhuceng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Repository("User")
public class ConcreteUserDao implements UserDao{
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public ConcreteUserDao(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    // switch to jpa method
    @Override
    public Optional<User> getUserByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user){
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id){
        userRepository.delete(getUserById(id).orElseThrow());
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public User verifyUser(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null){
            return null; // No such token
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0){
            tokenRepository.delete(verificationToken); // Expired
            return null;
        }

        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
        return user;
    }
}
