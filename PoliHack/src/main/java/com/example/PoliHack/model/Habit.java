package com.example.PoliHack.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Habit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habit {
    @Id
    private ObjectId id;
    private String title;
    private Integer value;
    private String description;
}