package com.api.springapirest.controller;

import com.api.springapirest.entity.User;
import com.api.springapirest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user with email and password")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.login(email, password));
    }

}

