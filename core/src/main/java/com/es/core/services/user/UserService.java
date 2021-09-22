package com.es.core.services.user;

import org.springframework.security.core.Authentication;

public interface UserService {

    String getUsername();

    boolean isAuthenticated();

    void signUp(String username, String password);
}
