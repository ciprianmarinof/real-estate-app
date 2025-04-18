package com.example.demo.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignInRequest {

    @NotEmpty(message = "Please provide username")
    private String identifier;

    @NotEmpty(message = "Please provide password")
    private String password;
}
