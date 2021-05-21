package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.entities.UserRole;
import com.hu.Virtualize.entities.Users;
import com.hu.Virtualize.repositories.RolesRepository;
import com.hu.Virtualize.repositories.UserRepository;
import com.hu.Virtualize.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity addUser(UserEntity userEntity) throws Exception {
        UserEntity local = new UserEntity();
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
        else{
            local = this.userRepository.save(userEntity);
        }
        return local;
    }

    //Methods for creating Users
//    @Override
//    public Users createUser(Users users, Set<UserRole> userRoles) throws Exception {
//
//        Users local = this.usersRepository.findByusername(users.getUsername());
//        if(local!=null){
//            System.out.println("User Name already exists!");
//            throw new Exception("UserName already exists!");
//        }
//        else{
//            //Create New User
//            for (UserRole ur: userRoles){
//                rolesRepository.save(ur.getRoles());
//            }
//            users.getUserRoles().addAll(userRoles);
//            local = this.usersRepository.save(users);
//
//        }
//        return local;
//    }
}
