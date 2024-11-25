package com.example.Web0.service;

import com.example.Web0.dto.request.UserRegisterRequest;
import com.example.Web0.dto.request.UserRequest;
import com.example.Web0.dto.response.AuthenticationResponse;
import com.example.Web0.entities.UserEntity;

import java.util.List;


public interface UserService {
    AuthenticationResponse login(UserRequest request);
    List<UserEntity> findAll();
    UserEntity register (UserRegisterRequest request);
}
