package com.example.papersheet;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// @Service is a specialization of @Component, indicating that this class is a service
@Service
public class GreetingService implements InitializingBean, DisposableBean {

    // Constructor - first method called when creating the bean
    public GreetingService() {
        System.out.println("1. GreetingService: Constructor called");
    }

    // @PostConstruct - called after dependency injection is complete
    @PostConstruct
    public void postConstruct() {
        System.out.println("3. GreetingService: @PostConstruct method called");
    }

    // InitializingBean interface method - called after all properties are set
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("4. GreetingService: InitializingBean's afterPropertiesSet method called");
    }

    // Custom init method - will be called by Spring if specified in @Bean annotation
    public void customInit() {
        System.out.println("5. GreetingService: Custom init method called");
    }

    // Business method of the service
    public String greet() {
        return "Hello from PaperSheet application!";
    }

    // @PreDestroy - called when the container is shutting down
    @PreDestroy
    public void preDestroy() {
        System.out.println("6. GreetingService: @PreDestroy method called");
    }

    // DisposableBean interface method - called during bean destruction
    @Override
    public void destroy() throws Exception {
        System.out.println("7. GreetingService: DisposableBean's destroy method called");
    }

    // Custom destroy method - will be called by Spring if specified in @Bean annotation
    public void customDestroy() {
        System.out.println("8. GreetingService: Custom destroy method called");
    }
}