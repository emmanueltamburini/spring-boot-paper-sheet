package com.example.papersheet.controller;

import com.example.papersheet.UserRepository;
import com.example.papersheet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Controller indicates that this class serves as a Spring MVC controller
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // @GetMapping maps HTTP GET requests to this method
    // This method demonstrates Spring MVC concepts
    @GetMapping("/users")
    public String listUsers(Model model) {
        // Add users to the model, which will be used by the view
        model.addAttribute("users", userRepository.findAll());
        // Return the name of the view to be rendered
        return "userList";
    }

    // @RestController combines @Controller and @ResponseBody
    // This inner class demonstrates RESTful web services
    @RestController
    @RequestMapping("/api/users")
    public class UserRestController {

        // HTTP GET method to retrieve all users
        @GetMapping
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        // HTTP POST method to create a new user
        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }

        // HTTP GET method to retrieve a user by ID
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            return userRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // HTTP PUT method to update a user
        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
            if (!userRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            user.setId(id);
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }

        // HTTP DELETE method to delete a user
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            if (!userRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}