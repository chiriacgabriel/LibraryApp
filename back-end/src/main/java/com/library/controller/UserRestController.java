package com.library.controller;

import com.library.model.User;
import com.library.payload.response.MessageResponse;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserRestController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(UserRepository userRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    //Create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable int id,
                                      @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }


        User dbUser = optionalUser.get();

        if (!dbUser.getName()
                .equals(user.getName())) {
            dbUser.setName(user.getName());
        }
        if (!dbUser.getLastName()
                .equals(user.getLastName())) {
            dbUser.setLastName(user.getLastName());
        }
        if (!dbUser.getEmail()
                .equals(user.getEmail())) {
            dbUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword()
                .isEmpty() && !dbUser.getPassword()
                .equals(user.getPassword())) {
            if (user.getPassword()
                    .length() >= 6) {
                dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse(
                        "Password must be at least 6 characters long"));
            }
        }

        if (!user.getRoleSet()
                .isEmpty()) {
            dbUser.setRoleSet(user.getRoleSet());
        }
        return ResponseEntity.ok(userRepository.save(dbUser));
    }
}
