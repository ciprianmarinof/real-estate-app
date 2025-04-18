package com.example.demo.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SignInResponse {

    private String username;
    private String email;
    private String token;

}
