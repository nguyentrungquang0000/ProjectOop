package com.example.Web0.controller;

import com.example.Web0.dto.request.UserRegisterRequest;
import com.example.Web0.dto.request.UserRequest;
import com.example.Web0.dto.response.AuthenticationResponse;
import com.example.Web0.entities.UserEntity;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/token")
    public AuthenticationResponse login(@RequestBody UserRequest request) {
        return userService.login(request);
    }

//    @PostMapping("/logout")
//    public String logout() {
//        SecurityContextHolder.clearContext();
//        return "out sucess";
//    }

    @GetMapping("/users")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @GetMapping("/myinfo")
    public UserEntity findMyInfo() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserRegisterRequest request) {
        return userService.register(request);
    }
}
