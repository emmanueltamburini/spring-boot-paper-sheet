package com.example.papersheet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomPropertyDemo {

    // @Value annotation injects values from application.properties
    @Value("${custom.property}")
    private String customProperty;

    public void printCustomProperty() {
        System.out.println("Custom property value: " + customProperty);
    }
}