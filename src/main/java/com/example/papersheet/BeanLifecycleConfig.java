package com.example.papersheet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration indicates that this class declares one or more @Bean methods
@Configuration
public class BeanLifecycleConfig {

    // @Bean tells Spring to manage this object as a bean
    // initMethod and destroyMethod specify custom init and destroy methods
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public GreetingService greetingService() {
        System.out.println("2. BeanLifecycleConfig: Creating GreetingService bean");
        return new GreetingService();
    }
}