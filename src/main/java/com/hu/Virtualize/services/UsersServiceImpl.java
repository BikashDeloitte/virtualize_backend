package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        UserEntity local = new UserEntity();
        UserEntity email = userRepository.findByUserEmail(userEntity.getUserEmail());
        UserEntity userName = userRepository.findByUserName(userEntity.getUserName());

        if(email != null){
            log.error("Email is already registered!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email exists!");
        }
        else {
            // encrypt the user password
            userEntity.setUserPassword(passwordEncoder.encode(userEntity.getUserPassword()));

            local = this.userRepository.save(userEntity);
        }
        return local;
    }

    public AdminEntity addAdmin(AdminEntity adminEntity) {
        Optional<AdminEntity> duplicateEntity = adminRepository.findByAdminEmail(adminEntity.getAdminEmail());

        // if email already available in database
        if(!duplicateEntity.isEmpty()) {
            log.error(adminEntity.getAdminEmail() + " already available in database");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already present.");
        }

        // encrypt the admin password
        adminEntity.setAdminPassword(passwordEncoder.encode(adminEntity.getAdminPassword()));
        adminEntity = adminRepository.save(adminEntity);
        return adminEntity;
    }
}
