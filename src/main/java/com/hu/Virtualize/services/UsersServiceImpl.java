package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.RolesRepository;
import com.hu.Virtualize.repositories.UserRepository;
import com.hu.Virtualize.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private static Logger log = LoggerFactory.getLogger(UsersService.class);

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
            log.error("Email is already registered!");
            throw new Exception("Email exists!");
        }
        if(userName!=null) {
            log.error("UserName is already taken. Try new one!");
            throw new Exception("UserName exists!");
        }
        else {
            local = this.userRepository.save(userEntity);
        }
        return local;
    }
}
