package com.hu.Virtualize.controllers;

import com.hu.Virtualize.entities.Roles;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.entities.UserRole;
import com.hu.Virtualize.entities.Users;
import com.hu.Virtualize.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;
    //creating users
    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity userEntity) throws Exception {
        return this.usersService.addUser(userEntity);
    }
    //public Users createUser(@RequestBody Users users) throws Exception {
//        Set<UserRole> roles = new HashSet<>();
//        Roles roles1 = new Roles();
//       if(users.getUsername()=="deloitte"){
//            roles1.setRole_id(44L);
//            roles1.setRole_name("ADMIN");
//            UserRole userRole = new UserRole();
//            userRole.setUsers(users);
//            userRole.setRoles(roles1);
//            roles.add(userRole);
//        }
//        else {
//            roles1.setRole_id(45L);
//            roles1.setRole_name("USER");
//            UserRole userRole = new UserRole();
//            userRole.setUsers(users);
//            userRole.setRoles(roles1);
//            roles.add(userRole);
//        }
//        return this.usersService.createUser(users,roles);
//    }


}
