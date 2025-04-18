package com.example.demo.mapper;

import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User convertFromAddUserRequestToUser(AddUserRequest addUserRequest){
        User user = new User();
        user.setUsername(addUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        user.setFirstName(addUserRequest.getFirstName());
        user.setLastName(addUserRequest.getLastName());
        user.setEmail(addUserRequest.getEmail());

        return user;
    }
}
