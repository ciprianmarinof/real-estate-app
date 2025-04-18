package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public Integer getId(){return user.getUserId();}

    public String getPassword(){return user.getPassword();}

    public String getEmail(){return user.getEmail();}

    public String getUsername(){return user.getUsername();}

    public Collection<? extends GrantedAuthority> mapRolesToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToGrantedAuthorities(user.getRoles());
    }



}
