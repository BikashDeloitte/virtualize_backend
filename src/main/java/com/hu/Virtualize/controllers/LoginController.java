package com.hu.Virtualize.controllers;

import com.hu.Virtualize.commands.LoginCommand;
import com.hu.Virtualize.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCommand loginCommand) {

        Object obj = loginService.login(loginCommand);
        if(obj == null ) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
    }
}
