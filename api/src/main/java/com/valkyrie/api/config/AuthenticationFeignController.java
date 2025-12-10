package com.valkyrie.api.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.valkyrie.api.model.AuthenticationByPass;
import com.valkyrie.api.model.UserAuthentication;

@FeignClient(value = "AUTHENTICATION", configuration = AuthenticationByPass.class)
public interface AuthenticationFeignController {

    @GetMapping("/authentication/get-user")
    public ResponseEntity<UserAuthentication> getUser(@RequestParam @NonNull String username);
    
}
