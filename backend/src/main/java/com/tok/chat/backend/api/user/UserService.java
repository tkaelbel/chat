package com.tok.chat.backend.api.user;

import javax.validation.Valid;

import com.tok.chat.backend.database.user.User;
import com.tok.chat.backend.manager.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000","http://localhost:8080"}, maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserService {
    
    @Autowired
    private UserManager userManager;

    @PostMapping()
    public User createUser(@Valid @RequestBody UserCreateCommand command){
        return this.userManager.findOrCreateUser(command.getName());
    }
}
