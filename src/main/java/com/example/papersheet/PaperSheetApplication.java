package com.example.papersheet;

import com.example.papersheet.models.User;
import com.example.papersheet.service.CacheableService;
import com.example.papersheet.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @SpringBootApplication combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
@SpringBootApplication
public class PaperSheetApplication {

	private static final Logger logger = LoggerFactory.getLogger(PaperSheetApplication.class);

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

		// Demonstrate profiles
		ProfileDemo profileDemo = context.getBean(ProfileDemo.class);
		profileDemo.printProfile();

		// Demonstrate logging
		logger.info("Application started successfully");
		logger.debug("This is a debug message");
		logger.error("This is an error message");

		// Demonstrate validation
		UserService userService = context.getBean(UserService.class);
		try {
			userService.createUser(new User());
		} catch (Exception e) {
			logger.error("Validation error: " + e.getMessage());
		}

		// Demonstrate caching
		CacheableService cacheableService = context.getBean(CacheableService.class);
		logger.info("First call result: " + cacheableService.getCachedData());
		logger.info("Second call result (should be cached): " + cacheableService.getCachedData());

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

	// Demonstrate profiles
	@Bean
	@Profile("dev")
	public ProfileDemo devProfileDemo() {
		return new ProfileDemo("dev");
	}

	@Bean
	@Profile("prod")
	public ProfileDemo prodProfileDemo() {
		return new ProfileDemo("prod");
	}
}

// A simple class to demonstrate prototype scope
class ExamplePrototypeBean {}

class ProfileDemo {
	private String profile;

	public ProfileDemo(String profile) {
		this.profile = profile;
	}

	public void printProfile() {
		System.out.println("Active profile: " + profile);
	}
}