package com.valkyrie.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valkyrie.authentication.model.UserAuthentication;
import com.valkyrie.authentication.service.AuthenticationService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private AuthenticationService service;
    @Autowired
    private void service(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserAuthentication userAuth) {
        return service.signUp(userAuth);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody UserAuthentication userAuth) {
        return service.signIn(userAuth);
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserAuthentication> getUser(@RequestParam @NonNull String username) {
        return service.findUser(username);
    } 

    @DeleteMapping("/remove-user")
    public ResponseEntity<String> removeUser(@RequestParam @NonNull String username) {
        return service.removeUser(username);
    }
}
