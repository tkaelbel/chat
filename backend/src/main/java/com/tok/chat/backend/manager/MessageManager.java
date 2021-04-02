package com.tok.chat.backend.manager;

import java.util.Date;
import java.util.List;
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

    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByDate();
    }

    public Message save(String name, String text) {
        User user = userRepository.findByName(name);
        return messageRepository.save(new Message(user.getName(), user.getId(), text, new Date()));
    }

    public void send(Message message) {
        chatMessageBus.emit(message);
    }

}
