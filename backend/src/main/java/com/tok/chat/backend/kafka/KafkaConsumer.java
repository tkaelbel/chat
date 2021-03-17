package com.tok.chat.backend.kafka;

import com.tok.chat.backend.database.message.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

// @Component
public class KafkaConsumer {
    
    // @Autowired
    // private SimpMessagingTemplate template;

    // @KafkaListener(topics = "chat", groupId = "group-id")
    // public void listen(Message message){
    //     template.convertAndSend(message);
    // }
}
