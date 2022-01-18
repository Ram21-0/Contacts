package com.example.demo.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String userId;
    private String password;
    private String name;
}
