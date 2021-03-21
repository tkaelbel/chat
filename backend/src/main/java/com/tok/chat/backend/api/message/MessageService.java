package com.tok.chat.backend.api.message;

import java.util.List;

import javax.validation.Valid;

import com.tok.chat.backend.database.message.Message;
import com.tok.chat.backend.manager.MessageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:8100"}, maxAge = 3600)
@RestController
@RequestMapping("/messages")
public class MessageService {
    
    @Autowired
    private MessageManager messageManager;

    @GetMapping()
    public List<Message> getMessages() {
        return messageManager.getAllMessages();
    }

    @PostMapping("/new")
    public void addNewMessage(@Valid @RequestBody MessageCreateCommand command){
        Message message = messageManager.save(command.getUserId(), command.getText());
        messageManager.send(message);
    }
}
