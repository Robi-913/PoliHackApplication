package com.example.PoliHack.repository;

import com.example.PoliHack.model.LeaderBoard;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LeaderBoardRepository extends MongoRepository<LeaderBoard, String> {
}