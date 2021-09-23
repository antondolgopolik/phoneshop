package com.es.core.services.user;

public interface UserService {

    String getUsername();

    boolean isAuthenticated();

    void signUp(String username, String password);
}
