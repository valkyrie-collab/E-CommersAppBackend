package com.valkyrie.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valkyrie.authentication.model.UserAuthentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserAuthentication, String> {

}
