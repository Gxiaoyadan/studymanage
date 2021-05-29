package com.example.studymanage.domain;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String age;
    private String identity;
}
