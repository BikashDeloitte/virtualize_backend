package com.hu.Virtualize.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    /**
     * This function will help you to check the health status of backend.
     * @return status
     */
    @GetMapping({"","/"})
    public String index() {
        return "Hello virtualize";
    }
}
