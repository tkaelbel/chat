package com.tok.chat.backend.api.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageCreateCommand {
    private String text;
    private String userId;
}
