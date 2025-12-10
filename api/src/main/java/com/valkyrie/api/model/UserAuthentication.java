package com.valkyrie.api.model;

public class UserAuthentication {
    private String username;
    private String password;
    private String role;

    public String getUsername() {return username;}
    public UserAuthentication setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {return password;}
    public UserAuthentication setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {return role;}
    public UserAuthentication setRole(String role) {
        this.role = role;
        return this;
    }

}
