package com.tok.chat.backend.database.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
    User findByName(String name);
    
    Optional<User> findById(String id);
    
    User save(String name);
    
}
