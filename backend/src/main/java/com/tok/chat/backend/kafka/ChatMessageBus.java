package com.tok.chat.backend.kafka;

import com.tok.chat.backend.database.message.Message;

public interface ChatMessageBus {
    void emit(Message message);
}
