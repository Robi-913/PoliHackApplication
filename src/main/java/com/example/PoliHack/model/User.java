package com.example.PoliHack.model;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
