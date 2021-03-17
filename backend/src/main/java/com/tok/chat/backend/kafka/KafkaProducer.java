package com.tok.chat.backend.kafka;

import com.tok.chat.backend.database.message.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements ChatMessageBus {

    // @Autowired
    // private KafkaTemplate<String, Message> kafkaTemplate;

    @Override
    public void emit(Message message) {
        // kafkaTemplate.send("chat", message);
    }
    
}
