package com.example.papersheet.service;

import com.example.papersheet.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void createUser(@Valid User user) {
        // Logic to create user
    }
}