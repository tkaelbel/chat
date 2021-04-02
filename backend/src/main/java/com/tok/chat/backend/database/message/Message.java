package com.tok.chat.backend.database.message;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 6357108073005642866L;

    public Message(String userName, String usedId, String text, Date date) {
        this(null, userName, usedId, text, date);
    }

    @MongoId
    private String id;
    @Field
    private String userName;
    @Field
    private String userId;
    @Field
    private String text;
    @Field
    private Date date;
  
}
