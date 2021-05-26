package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.entities.UserRole;
import com.hu.Virtualize.entities.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Set;

public interface UsersService {
    //creating users

    //public Users createUser(Users users, Set<UserRole> userRoles) throws Exception;
    public UserEntity addUser(UserEntity userEntity) throws Exception;
}
