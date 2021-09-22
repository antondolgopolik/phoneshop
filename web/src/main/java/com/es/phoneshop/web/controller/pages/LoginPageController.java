package com.es.phoneshop.web.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return "loginPage";
    }
}
