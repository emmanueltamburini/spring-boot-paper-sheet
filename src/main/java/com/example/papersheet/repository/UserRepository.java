package com.example.papersheet;

import com.example.papersheet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository provides CRUD operations for the User entity
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find users by name
    List<User> findByName(String name);

    // JPQL query example
    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> findByEmailContaining(@Param("email") String email);

    // Native SQL query example
    @Query(value = "SELECT * FROM user WHERE name LIKE %:name%", nativeQuery = true)
    List<User> findByNameContaining(@Param("name") String name);
}