package com.valkyrie.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.valkyrie.api.model.UserAuthentication;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private AuthenticationFeignController feignController;
    @Autowired
    private void setFeignController(AuthenticationFeignController feignController) {
        this.feignController = feignController;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthentication user;
        ResponseEntity<UserAuthentication> response;
        
        if (username == null) {
            throw new UsernameNotFoundException("USER-404");
        }

        response = feignController.getUser(username);

        if (response == null) {
            throw new UsernameNotFoundException("USER-404");
        }

        user = response.getBody();

        if (user == null) {
            throw new UsernameNotFoundException("USER-404");
        }

        return CustomUserDetails.initialize(user);

    }

}
