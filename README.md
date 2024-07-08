# Spring Boot paperSheet

This is a basic Spring Boot application named PaperSheet.

## How to run

Here's a README for your Spring Boot project, assuming you don't have any tools installed:

### Prerequisites

Before you begin, ensure you have the following installed on your system:

1. Java Development Kit (JDK) 21
   - Download and install from: <https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html>

2. Apache Maven
   - Download and install from: <https://maven.apache.org/download.cgi>

### Running the Application

Follow these steps to run the application:

1. Open a command prompt or terminal.

2. Navigate to the project's root directory (where the `pom.xml` file is located).

3. Build the project using Maven:

   ```batch
   mvn clean package
   ```

4. Run the application:

   ```batch
   java -jar target/papersheet-0.0.1-SNAPSHOT.jar
   ```

5. The application should now be running. By default, it will be accessible at `http://localhost:8080`.

### Development

To make changes to the project:

1. Open the project in your preferred Java IDE.

2. The main application class is `PaperSheetApplication.java` located in the `src/main/java/com/example/papersheet` directory.

3. Make your desired changes.

4. Rebuild and run the application using the steps in the "Running the Application" section.

## Testing

To run the tests for this application:

1. In the project's root directory, execute:

   ```batch
   mvn test
   ```

## Additional Information

- This project uses Spring Boot version 3.3.1.
- It includes dependencies for Spring Web and Spring Boot Test.
- The project is configured to use Java 21.

For more information on Spring Boot, visit: <https://spring.io/projects/spring-boot>

This README provides instructions on how to set up and run your Spring Boot application, assuming the user doesn't have any tools installed initially. It covers the installation of necessary tools (JDK and Maven), building and running the application, and basic information about the project structure and testing.

## Index

1. Spring Core concepts:
   - Dependency Injection: A design pattern where objects receive their dependencies instead of creating them.
   - Inversion of Control: The framework controls the flow of the program and object creation.
   - Bean lifecycle: The stages a Spring bean goes through from creation to destruction.

2. Spring Boot basics:
   - Auto-configuration: Spring Boot automatically configures your application based on dependencies.
   - Starter dependencies: Pre-configured dependencies to simplify the build configuration.
   - Application properties: Configuration files to customize Spring Boot applications.

3. Spring MVC:
   - Controllers: Handle incoming HTTP requests.
   - RequestMapping: Maps web requests to specific handler methods.
   - Model, View, and ViewResolver: Components for rendering web pages.

4. RESTful web services:
   - @RestController: Combines @Controller and @ResponseBody, simplifying REST API creation.
   - HTTP methods: GET (retrieve), POST (create), PUT (update), DELETE (remove) for CRUD operations.
   - ResponseEntity: Represents an HTTP response, including headers, body, and status code.

5. Spring Data JPA:
   - Repositories: Interfaces for CRUD operations on entities.
   - Entity mappings: Annotations to map Java objects to database tables.
   - JPQL and native queries: Ways to write custom database queries.

6. Spring Security:
   - Authentication: Verifies the identity of a user.
   - Authorization: Determines what actions an authenticated user can perform.
   - JWT: A compact way to securely transmit information between parties as a JSON object.

7. Spring Boot Testing:
   - Unit testing: Testing individual components in isolation.
   - Integration testing: Testing components working together.

8. Spring Boot Actuator:
   - Provides built-in endpoints for monitoring and managing your application.

9. Profiles and configuration:
   - Ways to define environment-specific configurations.

10. Logging:
    - SLF4J: A facade for various logging frameworks.
    - Logback: The default logging framework in Spring Boot.

11. Exception handling:
    - @ControllerAdvice: Allows you to handle exceptions across the whole application.
    - @ExceptionHandler: Used to define the handling method for specific exceptions.

12. Validation:
    - Bean Validation: A standardized way to add constraints to object models via annotations.

13. Caching:
    - Annotations to easily add caching to methods, improving performance.

14. Microservices concepts:
    - Service discovery: Automatically detects services on a network.
    - API Gateway: A server that acts as an API front-end, receiving API requests and routing them.
    - Circuit Breaker: A design pattern to prevent cascading failures in distributed systems.

15. Spring Boot with databases:
    - Configuring and connecting to databases.
    - Managing database transactions.

16. Messaging:
    - Integrating messaging systems like JMS, RabbitMQ, or Kafka for asynchronous communication.

## Resume

### 1. Spring Core concepts

   a) Dependency Injection (DI):
   Instead of creating dependencies within a class, they are injected from outside.

   Example:

   ```java
   @Service
   public class UserService {
       private final UserRepository userRepository;

       @Autowired
       public UserService(UserRepository userRepository) {
           this.userRepository = userRepository;
       }

       // Use userRepository in methods
   }
   ```

   b) Inversion of Control (IoC):
   The framework manages object creation and lifecycle.

   Example:

   ```java
   @Component
   public class EmailService {
       // Spring creates and manages this bean
   }
   ```

   c) Bean lifecycle:
   Beans go through initialization, use, and destruction phases.

   Example:

   ```java
   @Component
   public class DatabaseConnection implements InitializingBean, DisposableBean {
       @Override
       public void afterPropertiesSet() throws Exception {
           // Initialize connection
       }

       @Override
       public void destroy() throws Exception {
           // Close connection
       }
   }
   ```

### 2. Spring Boot basics

   a) Auto-configuration:
   Spring Boot automatically configures your application based on classpath dependencies.

   Example: Adding `spring-boot-starter-data-jpa` to your pom.xml will auto-configure JPA repositories.

   b) Starter dependencies:
   Pre-configured dependencies to simplify build configuration.

   Example in pom.xml:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

   c) Application properties:
   Configuration files to customize Spring Boot applications.

   Example (application.properties):

   ```properties
   server.port=8080
   spring.datasource.url=jdbc:mysql://localhost:3306/mydb
   ```

### 3. Spring MVC

   a) Controllers:
   Handle incoming HTTP requests.

   Example:

   ```java
   @Controller
   public class HomeController {
       @GetMapping("/")
       public String home() {
           return "home";
       }
   }
   ```

   b) RequestMapping:
   Maps web requests to specific handler methods.

   Example:

   ```java
   @Controller
   @RequestMapping("/users")
   public class UserController {
       @GetMapping("/{id}")
       public String getUser(@PathVariable Long id) {
           // Handle get user request
       }
   }
   ```

   c) Model, View, and ViewResolver:
   Components for rendering web pages.

   Example:

   ```java
   @Controller
   public class ProductController {
       @GetMapping("/products")
       public String listProducts(Model model) {
           model.addAttribute("products", productService.getAllProducts());
           return "productList";  // View name
       }
   }
   ```

### 4. RESTful web services

   a) @RestController:
   Combines @Controller and @ResponseBody.

   Example:

   ```java
   @RestController
   @RequestMapping("/api/users")
   public class UserApiController {
       @GetMapping("/{id}")
       public User getUser(@PathVariable Long id) {
           // Return User object, automatically serialized to JSON
       }
   }
   ```

   b) HTTP methods:
   GET, POST, PUT, DELETE for CRUD operations.

   Example:

   ```java
   @RestController
   @RequestMapping("/api/posts")
   public class PostApiController {
       @GetMapping
       public List<Post> getAllPosts() { /* ... */ }

       @PostMapping
       public Post createPost(@RequestBody Post post) { /* ... */ }

       @PutMapping("/{id}")
       public Post updatePost(@PathVariable Long id, @RequestBody Post post) { /* ... */ }

       @DeleteMapping("/{id}")
       public void deletePost(@PathVariable Long id) { /* ... */ }
   }
   ```

   c) ResponseEntity:
   Represents an HTTP response, including headers, body, and status code.

   Example:

   ```java
   @GetMapping("/{id}")
   public ResponseEntity<User> getUser(@PathVariable Long id) {
       User user = userService.findById(id);
       if (user != null) {
           return ResponseEntity.ok(user);
       } else {
           return ResponseEntity.notFound().build();
       }
   }
   ```

### 5. Spring Data JPA

   a) Repositories:
   Interfaces for CRUD operations on entities.

   Example:

   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
       List<User> findByLastName(String lastName);
   }
   ```

   b) Entity mappings:
   Annotations to map Java objects to database tables.

   Example:

   ```java
   @Entity
   @Table(name = "users")
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(name = "first_name")
       private String firstName;

       @Column(name = "last_name")
       private String lastName;

       // Getters and setters
   }
   ```

   c) JPQL and native queries:
   Ways to write custom database queries.

   Example:

   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
       @Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
       User findByFullName(String firstName, String lastName);

       @Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
       List<User> findUsersOlderThan(int age);
   }
   ```

Certainly. I'll expand on the next five topics (6-10) with examples.

### 6. Spring Security

   a) Authentication:
   Verifies the identity of a user.

   Example (Basic configuration):

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.inMemoryAuthentication()
               .withUser("user").password("{noop}password").roles("USER")
               .and()
               .withUser("admin").password("{noop}admin").roles("ADMIN");
       }
   }
   ```

   b) Authorization:
   Determines what actions an authenticated user can perform.

   Example:

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http.authorizeRequests()
               .antMatchers("/", "/home").permitAll()
               .antMatchers("/admin/**").hasRole("ADMIN")
               .anyRequest().authenticated()
               .and()
               .formLogin();
       }
   }
   ```

   c) JWT (JSON Web Tokens):
   A compact way to securely transmit information between parties as a JSON object.

   Example (JWT generation):

   ```java
   @Service
   public class JwtUtil {
       private String SECRET_KEY = "secret";

       public String generateToken(UserDetails userDetails) {
           Map<String, Object> claims = new HashMap<>();
           return createToken(claims, userDetails.getUsername());
       }

       private String createToken(Map<String, Object> claims, String subject) {
           return Jwts.builder().setClaims(claims).setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
       }
   }
   ```

### 7. Spring Boot Testing

   a) Unit testing:
   Testing individual components in isolation.

   Example:

   ```java
   @RunWith(MockitoJUnitRunner.class)
   public class UserServiceTest {
       @Mock
       private UserRepository userRepository;

       @InjectMocks
       private UserService userService;

       @Test
       public void testGetUserById() {
           User user = new User(1L, "John", "Doe");
           when(userRepository.findById(1L)).thenReturn(Optional.of(user));

           User result = userService.getUserById(1L);

           assertEquals(user, result);
       }
   }
   ```

   b) Integration testing:
   Testing components working together.

   Example:

   ```java
   @RunWith(SpringRunner.class)
   @SpringBootTest
   @AutoConfigureMockMvc
   public class UserControllerIntegrationTest {
       @Autowired
       private MockMvc mockMvc;

       @Test
       public void testGetUser() throws Exception {
           mockMvc.perform(get("/api/users/1"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.name").value("John Doe"));
       }
   }
   ```

### 8. Spring Boot Actuator

   Provides built-in endpoints for monitoring and managing your application.

   Example (application.properties):

   ```properties
   management.endpoints.web.exposure.include=*
   management.endpoint.health.show-details=always
   ```

   Accessing endpoints:

- Health check: `http://localhost:8080/actuator/health`
- Metrics: `http://localhost:8080/actuator/metrics`
- Info: `http://localhost:8080/actuator/info`

### 9. Profiles and configuration

   a) Application.properties vs Application.yml:
   Different ways to define configurations.

   Example (application.properties):

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mydb
   spring.datasource.username=root
   spring.datasource.password=password
   ```

   Equivalent (application.yml):

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/mydb
       username: root
       password: password
   ```

   b) Environment-specific configurations:
   Using profiles to manage different environments.

   Example (application-dev.properties):

   ```properties
   server.port=8080
   logging.level.root=DEBUG
   ```

   Example (application-prod.properties):

   ```properties
   server.port=80
   logging.level.root=INFO
   ```

   Activating a profile:

   ```java
   @SpringBootApplication
   public class MyApplication {
       public static void main(String[] args) {
           SpringApplication app = new SpringApplication(MyApplication.class);
           app.setAdditionalProfiles("dev");
           app.run(args);
       }
   }
   ```

### 10. Logging in Spring Boot

a) SLF4J:
    A facade for various logging frameworks.

Example:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        logger.info("Creating user: {}", user.getUsername());
        // ... logic to create user
        logger.debug("User created successfully");
        return user;
    }
}
```

b) Logback:
The default logging framework in Spring Boot.

Example (logback-spring.xml):

```xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
    </root>
</configuration>
```

### 11. Exception handling

a) @ControllerAdvice:
Allows you to handle exceptions across the whole application.

Example:

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

b) @ExceptionHandler:
Used to define the handling method for specific exceptions.

Example:

```java
@RestController
public class UserController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        // ... logic that might throw UserNotFoundException
    }
}
```

### 12. Validation

Bean Validation: A standardized way to add constraints to object models via annotations.

Example:

```java
public class UserDto {
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age should not be less than 18")
    private int age;

    // Getters and setters
}

@RestController
public class UserController {
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        // Process the validated user
        return ResponseEntity.ok("User created successfully");
    }
}
```

### 13. Caching

Annotations to easily add caching to methods, improving performance.

Example:

```java
@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users");
    }
}

@Service
public class UserService {
    @Cacheable("users")
    public User getUserById(Long id) {
        // This will be called only if the result is not in the cache
        return userRepository.findById(id).orElse(null);
    }

    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        // This will always be called and the result will be put in the cache
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        // This will remove the user from the cache
        userRepository.deleteById(id);
    }
}
```

### 14. Microservices concepts

a) Service discovery (Eureka):
Automatically detects services on a network.

Example:

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

// In a microservice
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

b) API Gateway:
A server that acts as an API front-end, receiving API requests and routing them.

Example using Spring Cloud Gateway:

```java
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user_route", r -> r.path("/users/**")
                .uri("lb://user-service"))
            .route("order_route", r -> r.path("/orders/**")
                .uri("lb://order-service"))
            .build();
    }
}
```

c) Circuit Breaker (Resilience4j):
A design pattern to prevent cascading failures in distributed systems.

Example:

```java
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetUser")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    public User fallbackGetUser(Long id, Throwable throwable) {
        return new User(id, "Fallback User", "fallback@example.com");
    }
}
```

### 15. Spring Boot with databases

a) Database configuration:
Configuring and connecting to databases.

Example (application.properties for MySQL):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

b) Transaction management:
Managing database transactions.

Example:

```java
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
        // If any exception occurs, the transaction will be rolled back
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
```

### 16. Messaging with Spring Boot

Integrating messaging systems like JMS, RabbitMQ, or Kafka for asynchronous communication.

a) JMS (Java Message Service):

Example with ActiveMQ:

```java
@Configuration
public class JmsConfig {
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }
}

@Service
public class MessageService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("myQueue", message);
    }
}

@Component
public class MessageReceiver {
    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
```

b) RabbitMQ:

Example:

```java
@Configuration
public class RabbitMQConfig {
    @Bean
    Queue queue() {
        return new Queue("myQueue", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("myExchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("myRoutingKey");
    }
}

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend("myExchange", "myRoutingKey", message);
    }
}

@Component
public class RabbitMQReceiver {
    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
```

c) Kafka:

Example:

```java
@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("myTopic", message);
    }
}

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group myGroup: " + message);
    }
}
```

These examples demonstrate how to configure and use different messaging systems in Spring Boot applications. Each messaging system has its own strengths and use cases:

- JMS is a Java API that provides a common way for Java programs to create, send, receive, and read messages.
- RabbitMQ is a message broker that supports multiple messaging protocols and can be used for complex routing scenarios.
- Kafka is designed for high-throughput, distributed streaming platforms and is often used for building real-time data pipelines and streaming applications.

The choice between these messaging systems depends on your specific requirements, such as message throughput, persistence, routing capabilities, and scalability needs.

## Exhibit

### Differences and similarities, and how the work combine between Spring Data JPA and Hibernate

Similarities:

1. Both are used for Object-Relational Mapping (ORM) in Java applications.
2. They both aim to simplify database operations and reduce boilerplate code.
3. Both support the JPA (Java Persistence API) specification.

Differences:

1. Level of Abstraction:
   - Hibernate is an ORM framework and a JPA provider.
   - Spring Data JPA is an additional layer of abstraction on top of JPA providers like Hibernate.

2. Scope:
   - Hibernate focuses on ORM functionality.
   - Spring Data JPA provides repository abstractions and query methods in addition to ORM capabilities.

3. Configuration:
   - Hibernate requires more detailed configuration.
   - Spring Data JPA simplifies configuration through Spring Boot's auto-configuration.

4. Query Methods:
   - Hibernate uses HQL (Hibernate Query Language) or Criteria API for complex queries.
   - Spring Data JPA allows defining query methods by method names or using @Query annotation.

How they work together:

1. JPA Provider:
   Spring Data JPA uses Hibernate as its default JPA provider. When you use Spring Data JPA, you're often indirectly using Hibernate under the hood.

2. Configuration:
   Spring Boot's auto-configuration sets up Hibernate when you use Spring Data JPA, reducing the need for explicit Hibernate configuration.

3. Entity Mapping:
   You use JPA annotations (which Hibernate implements) to map your entities:

   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       private String name;

       // getters and setters
   }
   ```

4. Repository Layer:
   Spring Data JPA provides repository interfaces that you can extend:

   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
       List<User> findByName(String name);
   }
   ```

5. Query Execution:
   When you call a method on a Spring Data JPA repository:
   - Spring Data JPA generates the appropriate query.
   - It then uses Hibernate to execute the query and map the results to objects.

6. Transaction Management:
   Spring manages transactions, which are then carried out by Hibernate.

7. Customization:
   You can still use Hibernate-specific features when needed:

   ```java
   @Repository
   public class CustomUserRepository {
       @PersistenceContext
       private EntityManager entityManager;

       public List<User> findUsersWithComplexCriteria() {
           Session session = entityManager.unwrap(Session.class);
           Criteria criteria = session.createCriteria(User.class);
           // Add complex criteria
           return criteria.list();
       }
   }
   ```

In summary, Spring Data JPA provides a higher-level abstraction that simplifies data access patterns, while Hibernate does the heavy lifting of ORM operations. Spring Data JPA generates queries and manages the repository layer, while Hibernate executes these queries and handles the actual database interactions. This combination allows developers to work at a higher level of abstraction with Spring Data JPA, while still having the power and flexibility of Hibernate when needed.
