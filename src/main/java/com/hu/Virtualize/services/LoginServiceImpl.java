package com.hu.Virtualize.services;

import com.hu.Virtualize.commands.LoginCommand;
import com.hu.Virtualize.commands.UserTypeCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public LoginServiceImpl(UserRepository userRepository,AdminRepository adminRepository ) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * This function will check the details is available or not in database.
     * If available then it will return the details, other return null.
     * @param loginCommand login details
     * @return status
     */
    @Override
    public Object login(LoginCommand loginCommand) {
        // for user
        if(loginCommand.getType().equals(UserTypeCommand.USER.toString())) {
            Optional<UserEntity> userEntity = userRepository.findByUserEmailAndUserPassword(loginCommand.getId(),loginCommand.getPassword());

            // when user enter invalid user id or password
            if(userEntity.isEmpty()) {
                log.info("Not valid user details");
                return null;
            }
            return userEntity.get();
        } else {
            // for admin login
            Optional<AdminEntity> adminEntity = adminRepository.findByAdminEmailAndAdminPassword(loginCommand.getId(),loginCommand.getPassword());

            // when admin enter wrong id password
            if(adminEntity.isEmpty()) {
                log.info("Not valid admin details");
                return null;
            }
            return adminEntity.get();
        }
    }
}
