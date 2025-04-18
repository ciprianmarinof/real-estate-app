package com.example.demo.controller;

import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.dto.request.user.SignInRequest;
import com.example.demo.dto.response.user.SignInResponse;
import com.example.demo.service.security.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody @Valid AddUserRequest addUserRequest) {

        authService.registerUser(addUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> singIn(@RequestBody @Valid SignInRequest signInRequest){
        SignInResponse signInResponse = authService.signIn(signInRequest);

        return ResponseEntity.ok().body(signInResponse);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(HttpServletRequest request, HttpServletResponse response){

        request.getSession().invalidate();

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
