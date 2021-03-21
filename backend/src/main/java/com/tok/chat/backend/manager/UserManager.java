package com.tok.chat.backend.manager;

import com.tok.chat.backend.database.user.User;
import com.tok.chat.backend.database.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    
    @Autowired
    private UserRepository userRepository;

    /**
     * Finds an existing user or creates a new one.
     * @param userName
     * @return
     */
    public User findOrCreateUser(String userName){
        User user = userRepository.findByName(userName);
        if(user != null) return user;
        return userRepository.save(new User(null, userName));
    }
}
