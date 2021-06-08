package com.hu.Virtualize.services.login;

import com.hu.Virtualize.commands.login.LoginCommand;
import com.hu.Virtualize.enums.UserTypeCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    @Transactional
    @Override
    public Object login(LoginCommand loginCommand) {
        // for user
        if(loginCommand.getType().equals(UserTypeCommand.USER.toString())) {
            UserEntity userEntity = userRepository.findByUserEmail(loginCommand.getId());

            // when user enter invalid user id or password
            if(userEntity == null || !passwordEncoder.matches(loginCommand.getPassword(), userEntity.getUserPassword())) {
                log.info("Not valid user details");
                return null;
            }
            return userEntity;
        } else {
            // for admin login
            Optional<AdminEntity> adminEntity = adminRepository.findByAdminEmail(loginCommand.getId());

            // when admin enter wrong id password
            if(adminEntity.isEmpty() || !passwordEncoder.matches(loginCommand.getPassword(), adminEntity.get().getAdminPassword())) {
                log.info("Not valid admin details");
                return null;
            }
            return adminEntity.get();
        }
    }

    /**
     * This function will check the email is valid or not.
     * @param loginCommand login details.
     * @return status (true or false) if available then return true, otherwise return false.
     */
    @Transactional
    public Boolean validEmail(LoginCommand loginCommand) {
        if(loginCommand.getType().equals(UserTypeCommand.USER.toString())) {
            UserEntity user = userRepository.findByUserEmail(loginCommand.getId());
            return user != null;
        } else {
            Optional<AdminEntity> adminEntity = adminRepository.findByAdminEmail(loginCommand.getId());
            return adminEntity.isPresent();
        }
    }

    /**
     * This function will update the password.
     * @param loginCommand login details
     * @return update object
     */
    @Transactional
    public Object updatePassword(LoginCommand loginCommand) {
        // encrypt the password
        loginCommand.setPassword(passwordEncoder.encode(loginCommand.getPassword()));

        // for user
        if(loginCommand.getType().equals(UserTypeCommand.USER.toString())) {
            UserEntity userEntity = userRepository.findByUserEmail(loginCommand.getId());

            userEntity.setUserPassword(loginCommand.getPassword());
            userEntity = userRepository.save(userEntity);

            return userEntity;
        } else {
            // for admin login
            Optional<AdminEntity> adminEntity = adminRepository.findByAdminEmail(loginCommand.getId());

            AdminEntity admin = adminEntity.get();
            admin.setAdminPassword(loginCommand.getPassword());

            admin = adminRepository.save(admin);

            return admin;
        }
    }
}
