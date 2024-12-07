package com.example.PoliHack.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
