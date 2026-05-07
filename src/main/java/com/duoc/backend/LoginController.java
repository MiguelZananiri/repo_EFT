package com.duoc.backend;
import com.duoc.backend.JWTAuthenticationConfig;
import com.duoc.backend.user.MyUserDetailsService;
import com.duoc.backend.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    private final JWTAuthenticationConfig jwtAuthenticationConfig;
    private final MyUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(
            JWTAuthenticationConfig jwtAuthenticationConfig,
            MyUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(loginRequest.getUsername());

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                userDetails.getPassword())) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid login");
        }

        String token =
                jwtAuthenticationConfig.getJWTToken(loginRequest.getUsername());

        return ResponseEntity.ok(token);
    }
}