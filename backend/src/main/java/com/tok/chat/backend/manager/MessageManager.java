package com.tok.chat.backend.manager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tok.chat.backend.database.message.Message;
import com.tok.chat.backend.database.message.MessageRepository;
import com.tok.chat.backend.database.user.User;
import com.tok.chat.backend.database.user.UserRepository;
import com.tok.chat.backend.kafka.ChatMessageBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatMessageBus chatMessageBus;


    public List<Message> getAllMessages(){
        return messageRepository.findAllByOrderByDate();
    }

    public Message save(String userId, String text){
        Optional<User> user = userRepository.findById(userId);
        var realUser = user.orElseThrow(() -> new IllegalArgumentException("User couldn't be found"));
        return messageRepository.save(new Message(realUser.getName(), userId, text, new Date()));
    }

    public void send(Message message){
        chatMessageBus.emit(message);
    }

}
