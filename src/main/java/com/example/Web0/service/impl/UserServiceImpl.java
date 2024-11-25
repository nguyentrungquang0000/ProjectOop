package com.example.Web0.service.impl;

import com.example.Web0.dto.request.UserRegisterRequest;
import com.example.Web0.dto.request.UserRequest;
import com.example.Web0.dto.response.AuthenticationResponse;
import com.example.Web0.entities.CartEntity;
import com.example.Web0.entities.RoleEntity;
import com.example.Web0.entities.UserEntity;
import com.example.Web0.repository.RoleRepository;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.service.UserService;
//import com.example.Web0.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private JwtUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AuthenticationResponse login(UserRequest request) {
        UserEntity userEntity = userRepository.findByUsername(request.getUsername());
        if(userEntity!=null && userEntity.getPassword().equals(request.getPassword())) {
            //var token = jwtUtil.generateToken(userEntity);
            boolean isAdmin = userEntity.getRoles().stream().
                    map(RoleEntity::getRoleName).
                    collect(Collectors.joining(" ")).
                    contains("ADMIN");
            return AuthenticationResponse.builder()
//                    .token(token)
                    .authenticated(true)
                    .isAdmin(isAdmin)
                    .build();
        }
        return AuthenticationResponse.builder()
                .authenticated(false)
                .build();
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity register(UserRegisterRequest request) {
        UserEntity userEntity = modelMapper.map(request, UserEntity.class);
        userEntity.setRoles(List.of(roleRepository.findById(2L).get()));
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUser(userEntity);
        userEntity.setCart(cartEntity);
        return userRepository.save(userEntity);
    }


}
