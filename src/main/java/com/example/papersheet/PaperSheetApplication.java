package com.example.papersheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

// @SpringBootApplication combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
@SpringBootApplication
public class PaperSheetApplication {

	public static void main(String[] args) {
		// Bootstrap the application and get the ApplicationContext
		// ApplicationContext is the central interface for providing configuration to the application
		ApplicationContext context = SpringApplication.run(PaperSheetApplication.class, args);

		// Retrieve a bean from the ApplicationContext
		// This demonstrates Dependency Injection - Spring manages object creation and lifetime
		GreetingService greetingService = context.getBean(GreetingService.class);
		System.out.println(greetingService.greet());

		// Demonstrate prototype scope
		// Prototype scope means a new instance is created each time the bean is requested
		ExamplePrototypeBean bean1 = context.getBean(ExamplePrototypeBean.class);
		ExamplePrototypeBean bean2 = context.getBean(ExamplePrototypeBean.class);
		System.out.println("Are prototype beans the same? " + (bean1 == bean2));
		// This will print false, as they are different instances

		// Demonstrating auto-configuration: Spring Boot automatically configures a DataSource
		// if it detects the H2 database on the classpath (added via starter dependency)
		javax.sql.DataSource dataSource = context.getBean(javax.sql.DataSource.class);
		System.out.println("DataSource configured automatically: " + (dataSource != null));

		// Using a custom property from application.properties
		CustomPropertyDemo demo = context.getBean(CustomPropertyDemo.class);
		demo.printCustomProperty();

		// Demonstrate Spring Boot Actuator
		HealthIndicator customHealthIndicator = context.getBean("customHealthIndicator", HealthIndicator.class);
		Health health = customHealthIndicator.health();
		System.out.println("Custom Health Indicator Status: " + health.getStatus());
	}

	// @Bean annotation tells Spring that this method will return an object
	// that should be registered as a bean in the Spring application context
	// @Scope("prototype") means a new instance will be created each time the bean is requested
	@Bean
	@Scope("prototype")
	public ExamplePrototypeBean examplePrototypeBean() {
		return new ExamplePrototypeBean();
	}

	// Spring Security: BCryptPasswordEncoder bean
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Spring Boot Actuator: Custom HealthIndicator
	@Bean
	public HealthIndicator customHealthIndicator() {
		return () -> Health.up().withDetail("custom", "Everything is working fine!").build();
	}
}

// A simple class to demonstrate prototype scope
class ExamplePrototypeBean {}
