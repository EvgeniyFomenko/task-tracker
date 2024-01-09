package ru.efomenko.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.efomenko.dto.JwtRequest;
import ru.efomenko.dto.JwtResponse;
import ru.efomenko.dto.RegistrationUserDto;
import ru.efomenko.dto.UserDto;
import ru.efomenko.service.UserServiceImpl;
import ru.efomenko.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/auth")
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("auth with authRequest = {}", authRequest);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);

        return JwtResponse.builder().token(token).build();

    }

    @PostMapping("/registration")
    public UserDto createUser(@RequestBody RegistrationUserDto registrationUserDto) {
        log.info("Registration with regUserSet = {}", registrationUserDto);

        return userService.createUser(registrationUserDto);
    }
}
