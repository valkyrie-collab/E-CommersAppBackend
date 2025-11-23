package com.valkyrie.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.valkyrie.authentication.model.UserAuthentication;
import com.valkyrie.authentication.repository.AuthenticationRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private AuthenticationRepository authRepo;
    @Autowired
    private void setAuthRepo(AuthenticationRepository authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        if (username == null) {
            throw new UsernameNotFoundException("USER-404");
        }

        UserAuthentication user = authRepo.findById(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("USER-404");
        }

        return CustomUserDetails.initialize(user);

    }

}
