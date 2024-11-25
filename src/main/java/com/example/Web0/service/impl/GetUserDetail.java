package com.example.Web0.service.impl;

import com.example.Web0.entities.RoleEntity;
import com.example.Web0.entities.UserEntity;
import com.example.Web0.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserDetail implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<RoleEntity> roleList = user.getRoles();
        for(RoleEntity role : roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
            roles.add(authority);
        }
        return new User(username, user.getPassword(), roles);

    }
}
