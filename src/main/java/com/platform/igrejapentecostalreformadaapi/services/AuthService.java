package com.platform.igrejapentecostalreformadaapi.services;


import com.platform.igrejapentecostalreformadaapi.data.vo.LoginVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.RegisterVO;
import com.platform.igrejapentecostalreformadaapi.entities.Role;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.PlatformException;
import com.platform.igrejapentecostalreformadaapi.repositories.RoleRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class AuthService {

    private final Logger logger = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(@Valid LoginVO loginVO) {

        if (!userRepository.existsByUsername(loginVO.getUsernameOrEmail())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, "Username or Email does not exists!");
        }

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginVO.getUsernameOrEmail(),
                                loginVO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    public String register(@Valid RegisterVO registerVO) {

        // Check if this user already exists
        if (userRepository.existsByUsername(registerVO.getUsername())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, "Username is already exists!");
        }

        // Check if this user email already exists
        if (userRepository.existsByEmail(registerVO.getEmail())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, "Email is already exists!");
        }

        User user = new User();

        user.setName(registerVO.getName());
        user.setEmail(registerVO.getEmail());
        user.setUsername(registerVO.getUsername());
        user.setPassword(passwordEncoder.encode(registerVO.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (roleRepository.findByName("ROLE_USER").isPresent()) {
            Role userRole = roleRepository.findByName("ROLE_USER").get();

            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);

            return "User registered successfully!.";
        } else {
            throw new PlatformException(HttpStatus.NOT_FOUND, "User role not found!");
        }
    }
}