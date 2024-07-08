package com.example.papersheet.controller;

import com.example.papersheet.handlers.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionDemoController {

    @GetMapping("/test-exception")
    public String testException() {
        // This will throw an exception
        throw new RuntimeException("This is a test exception");
    }

    @GetMapping("/test-custom-exception")
    public String testCustomException() throws CustomException {
        // This will throw a custom exception
        throw new CustomException("This is a custom exception");
    }
}