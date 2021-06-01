package com.hu.Virtualize.controllers.login;

import com.hu.Virtualize.commands.login.LoginCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.commands.login.EmailRequest;
import com.hu.Virtualize.commands.login.EmailResponse;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.repositories.UserRepository;
import com.hu.Virtualize.services.login.AdminService;
import com.hu.Virtualize.services.login.ForgotPassword;
import com.hu.Virtualize.services.login.LoginService;
import com.hu.Virtualize.services.login.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private ForgotPassword forgotPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminService adminService;

    /**
     * This function will help you to login .
     * @param loginCommand login details
     * @return user object.
     */
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginCommand loginCommand) {

        Object obj = loginService.login(loginCommand);
        if(obj == null ) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
    }

    /**
     * This function will register the user.
     * @param userEntity user details.
     * @return user details object
     * @throws Exception when any exception come
     */
    @PostMapping("/users/add")
    public UserEntity addUser(@RequestBody UserEntity userEntity) throws Exception {
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

    /**
     * This function will send you to the email for forgot password.
     * @param emailRequest receiver email.
     * @return status
     */
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){

        boolean result = forgotPassword.sendEmail(emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getTo());
        if(result) {
            return ResponseEntity.ok(new EmailResponse("Email is sent successfully.."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent..."));
        }
    }

    @GetMapping(value = "/admin/{id}")
    public AdminEntity getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id);
    }
}
