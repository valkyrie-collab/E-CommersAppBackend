package com.valkyrie.authentication.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valkyrie.authentication.config.TokenConfig;
import com.valkyrie.authentication.model.UserAuthentication;
import com.valkyrie.authentication.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private AuthenticationRepository authRepo;
    @Autowired
    private void setAuthRepo(AuthenticationRepository authRepo) {
        this.authRepo = authRepo;
    }

    private AuthenticationManager authManager;
    @Autowired
    private void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    // private Authentication authentication;
    // @Autowired
    // private void setAuthentication(Authentication authentication) {
    //     this.authentication = authentication;
    // }

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {
        this.config = config;
    }

    @NonNull
    private String decoder(String word) {
        return new String(Base64.getDecoder().decode(word));
    }

    public ResponseEntity<String> signUp(UserAuthentication userAuth) {
        String username = userAuth.getUsername();

        if (username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username not found..");
        }

        userAuth.setPassword(
            new BCryptPasswordEncoder(12).encode(userAuth.getPassword()))
            .setRole("ROLE_" + userAuth.getRole().toUpperCase());

        authRepo.save(userAuth);

        return authRepo.existsById(username)?
            ResponseEntity.status(HttpStatus.OK).body("The User saved successfully") : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user not saved successfully..");
    }

    public ResponseEntity<String> signIn(UserAuthentication userAuth) {
        String token = null;
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(userAuth.getUsername(), userAuth.getPassword())
        );

        if (authentication.isAuthenticated()) {
            token = config.generateToken(userAuth.getUsername(), authentication.getAuthorities());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(token);
    }

    public ResponseEntity<UserAuthentication> findUser(@NonNull String username) {
        UserAuthentication user;
        HttpStatus status;

        user = authRepo.findById(username).orElse(null);
        status = HttpStatus.BAD_REQUEST;

        if (user != null) {
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(user);
    }

    @Transactional
    public ResponseEntity<String> removeUser(@NonNull String username) {
        Boolean exist;

        username = decoder(username);
        exist = authRepo.existsById(username);

        if (!exist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already been deleted");
        }

        authRepo.deleteById(username);
        exist = authRepo.existsById(username);

        return exist? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion is not possible..") : 
            ResponseEntity.status(HttpStatus.OK).body("Deletion is successful");
    }

}
