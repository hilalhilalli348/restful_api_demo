package com.demo_project.rest.controller;

import com.demo_project.rest.detail.CustomUserDetailsService;
import com.demo_project.rest.model.User;
import com.demo_project.rest.service.UserService;
import com.demo_project.rest.util.JWTUtil;
import com.demo_project.rest.dto.UserDTOCredential;
import com.demo_project.rest.exception.ResourceIsInvalidException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Api(description = "userin sing up olması üçün controller")
@CrossOrigin
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(CustomUserDetailsService userDetailsService, UserService userService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }




    @PostMapping("/registration")
    @ApiOperation("Yeni user-in sing up olmasi")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTOCredential userDTOCredential, BindingResult bindingResult) {
        validateUser(bindingResult);
        User user = userService.createUser(userDTOCredential);
        return ResponseEntity.ok(jwtUtil.generateToken(user.getUsername(),user.getRole().getRole()));
    }



    private void validateUser(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            StringBuilder errMessage = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();


            for (FieldError fieldError : fieldErrors) {
                errMessage.append(fieldError.getDefaultMessage() + "  ");
            }


            throw new ResourceIsInvalidException(errMessage.toString());
        }

    }

}
