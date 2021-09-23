package com.es.phoneshop.web.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return "loginPage";
    }
}
