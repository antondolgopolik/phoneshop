package com.es.phoneshop.web.controller.api;

import com.es.core.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/api/users")
public class AjaxUserController {
    @Resource
    private UserService userService;

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestParam String username,
                                         @RequestParam String password) {
        // Try to sign up
        userService.signUp(username, password);
        // Send response
        return ResponseEntity.noContent().build();
    }
}
