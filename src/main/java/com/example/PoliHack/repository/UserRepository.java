package com.example.PoliHack.repository;

import com.example.PoliHack.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
