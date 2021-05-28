package com.hu.Virtualize.services;

import com.hu.Virtualize.commands.LoginCommand;
import com.hu.Virtualize.enums.UserTypeCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        // encrypt the password
        loginCommand.setPassword(passwordEncoder.encode(loginCommand.getPassword()));
        log.info(loginCommand.getPassword());
        // for user
        if(loginCommand.getType().equals(UserTypeCommand.USER.toString())) {
            UserEntity userEntity = userRepository.findByUserEmail(loginCommand.getId());

            // when user enter invalid user id or password
            if(userEntity == null || passwordEncoder.matches(loginCommand.getPassword(), userEntity.getUserPassword())) {
                log.info("Not valid user details");
                return null;
            }
            return userEntity;
        } else {
            // for admin login
            Optional<AdminEntity> adminEntity = adminRepository.findByAdminEmail(loginCommand.getId());


            // when admin enter wrong id password
            if(adminEntity.isEmpty() || passwordEncoder.matches(loginCommand.getPassword(), adminEntity.get().getAdminPassword())) {
                log.info("Not valid admin details");
                return null;
            }
            return adminEntity.get();
        }
    }
}
