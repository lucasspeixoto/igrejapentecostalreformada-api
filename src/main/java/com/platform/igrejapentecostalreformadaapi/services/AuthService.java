package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.response.PlatformResponse;
import com.platform.igrejapentecostalreformadaapi.data.vo.auth.LoginVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.auth.RegisterVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.Role;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.PlatformException;
import com.platform.igrejapentecostalreformadaapi.repositories.RoleRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.security.JwtTokenProvider;
import com.platform.igrejapentecostalreformadaapi.utils.Messages;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Autowired
    private UserProcessService userProcessService;

    public String login(@Valid LoginVO loginVO) {

        if (!userRepository.existsByUsername(loginVO.getUsernameOrEmail())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.LOGIN_EMAIL_PASSWORD_MESSAGE);
        }

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginVO.getUsernameOrEmail(),
                                loginVO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    public PlatformResponse register(@Valid RegisterVO registerVO) {

        // Check if this user already exists
        if (userRepository.existsByUsername(registerVO.getUsername())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.REGISTER_ALREADY_EXISTS_EMAIL_MESSAGE);
        }

        // Check if this user email already exists
        if (userRepository.existsByEmail(registerVO.getEmail())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.REGISTER_ALREADY_EXISTS_USERNAME_MESSAGE);
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

            // Create started user process data
            this.createStartedUserProcess(user);

            //return "User registered successfully!.";
            HttpStatus status = HttpStatus.CREATED;

            return new PlatformResponse(
                    status.value(),
                    Messages.REGISTER_SUCCESS_MESSAGE,
                    Instant.now(),
                    Messages.REGISTER_SUCCESS_DETAIL);

        } else {
            throw new PlatformException(HttpStatus.NOT_FOUND, "User role not found!");
        }
    }

    private void createStartedUserProcess(User user) {
        this.userRepository.save(user);

        UserProcessVO startUserProcess = new UserProcessVO();

        Long userId = user.getId();

        this.userProcessService.create(startUserProcess, userId);
    }
}