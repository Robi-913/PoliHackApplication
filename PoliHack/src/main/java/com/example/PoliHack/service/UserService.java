package com.example.PoliHack.service;

import com.example.PoliHack.model.User;
import com.example.PoliHack.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateUser(String nickname, String password) {
        User user = userRepository.findByNickname(nickname);
        return user != null && user.getPassword().equals(password);
    }

    public boolean registerUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(new ObjectId().toString());
        }

        if (userRepository.findByNickname(user.getNickname()) != null) {
            return false;
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return false;
        }

        userRepository.save(user);
        return true;
    }

}