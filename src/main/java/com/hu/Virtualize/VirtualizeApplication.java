package com.hu.Virtualize;

import com.hu.Virtualize.entities.Roles;
import com.hu.Virtualize.entities.UserRole;
import com.hu.Virtualize.entities.Users;
import com.hu.Virtualize.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
public class VirtualizeApplication implements CommandLineRunner {

	@Autowired
	private UsersService usersService;

	public static void main(String[] args) {
		SpringApplication.run(VirtualizeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Started!");
//		Users users = new Users();
//		users.setUsername("praveen236");
//		users.setFirstName("Praveen");
//		users.setLastName("Tripathi");
//		users.setEmail("pravtripathi@deloitte.com");
//		users.setPhone("7905432848");
//		users.setPassword("123");
//
//		Roles roles1 = new Roles();
//		roles1.setRole_id(44L);
//		roles1.setRole_name("ADMIN");
//
//		Set<UserRole> userRoles= new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRoles(roles1);
//		userRole.setUsers(users);
//		userRoles.add(userRole);

//		Users users1 = this.usersService.createUser(users,userRoles);
//		System.out.println(users1.getFirstName());

	}
}
