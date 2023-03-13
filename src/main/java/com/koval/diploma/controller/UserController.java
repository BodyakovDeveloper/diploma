package com.koval.diploma.controller;

import com.koval.diploma.entity.User;
import com.koval.diploma.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String getUser() {

        return "Hello from api/v1/user/{id}";
    }

    @PostMapping
    public ResponseEntity<Long> saveUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().body(user.getId());
    }
}