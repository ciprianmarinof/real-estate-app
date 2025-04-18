package com.example.demo.service.security;

import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.dto.request.user.SignInRequest;
import com.example.demo.dto.response.user.SignInResponse;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;


@Service
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleMapper userRoleMapper;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserRoleMapper userRoleMapper,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleMapper = userRoleMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;

    }


    public User getUserByEmail(String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " does not exist!"));

        return user;
    }

    public void registerUser(AddUserRequest addUserRequest){
//        if(getUserByEmail(addUserRequest.getEmail())){
//
//        }
        User user= userRoleMapper.convertFromAddUserRequestToUser(addUserRequest);
        userRepository.save(user);
    }

//    public AuthResponse login(AuthRequest request){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
//        );
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//
//        String jwt = jwtUtil.generateToken(new UserDetailsImpl(user));
//
//        return new AuthResponse(jwt);
//    }


    public SignInResponse signIn(@NotNull SignInRequest signInRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getIdentifier(),signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);


        return new SignInResponse(
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwt
        );
    }

    public void signOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
    }
}
