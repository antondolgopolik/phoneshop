package com.es.phoneshop.web.controller.api;

import com.es.core.dto.user.SignUpFormDto;
import com.es.core.exceptions.ValidationException;
import com.es.core.services.user.UserService;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/api/users")
public class AjaxUserController {
    @Resource
    private UserService userService;

    @Resource
    private MessageSource messageSource;
    @Resource
    private Validator signUpFormDtoValidator;

    @InitBinder
    private void initBinderQuantityDto(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(signUpFormDtoValidator);
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@Validated @RequestBody SignUpFormDto signUpFormDto,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(messageSource, bindingResult);
        }
        // Try to sign up
        userService.signUp(signUpFormDto.getUsername(), signUpFormDto.getPassword());
        // Send response
        return ResponseEntity.noContent().build();
    }
}
