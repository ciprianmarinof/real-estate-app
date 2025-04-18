package com.example.demo.dto.request.user;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}