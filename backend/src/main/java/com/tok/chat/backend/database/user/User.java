package com.tok.chat.backend.database.user;

import java.io.Serializable;

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
@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -4840089657987822738L;

    @MongoId
    private String id;
    @Field
    private String name;    

}
