package com.hu.Virtualize.controllers;

import com.hu.Virtualize.entities.EmailRequest;
import com.hu.Virtualize.services.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordEmail {

    @Autowired
    private ForgotPassword forgotPassword;

    @RequestMapping("/welcome")
    public String welcome(){
        return "Hello, this is my Email API";
    }

    //API to send Email
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){

        boolean result = this.forgotPassword.sendEmail(emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getTo());
        if(result) {
            return ResponseEntity.ok("Sent!!!");
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not sent!!!");
        }
    }

}
