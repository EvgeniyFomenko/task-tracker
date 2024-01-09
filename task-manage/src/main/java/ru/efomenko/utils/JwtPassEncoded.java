package ru.efomenko.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtPassEncoded {
    private final PasswordEncoder passwordEncoder;

    public String encoded(String pass) {
        return passwordEncoder.encode(pass);
    }

}
