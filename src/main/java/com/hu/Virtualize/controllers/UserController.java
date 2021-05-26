package com.hu.Virtualize.controllers;

import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.UserRepository;
import com.hu.Virtualize.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService usersService;

    /**
     * This function will register the user.
     * @param userEntity user details.
     * @return user details object
     * @throws Exception when any exception come
     */
    @PostMapping("/users/add")
    public UserEntity addUser(@RequestBody UserEntity userEntity) throws Exception {
        UserEntity email = this.userRepository.findByUserEmail(userEntity.getUserEmail());
        UserEntity userName = this.userRepository.findByUserName(userEntity.getUserName());

        if(email!=null){
            System.out.println("Email is already registered!");
            throw new Exception("Email exists!");
        }
        if(userName!=null){
            System.out.println("UserName is already taken. Try new one!");
            throw new Exception("UserName exists!");
        }
        return usersService.addUser(userEntity);
    }

    /**
     * This function help admin to register yourself.
     * @param adminEntity admin details.
     * @return admin details
     */
    @PostMapping("/admin/add")
    public ResponseEntity<?> addAdmin(@RequestBody AdminEntity adminEntity) {
        AdminEntity admin = usersService.addAdmin(adminEntity);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}
