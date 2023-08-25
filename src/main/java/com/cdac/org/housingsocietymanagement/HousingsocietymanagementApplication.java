package com.cdac.org.housingsocietymanagement;

import com.cdac.org.housingsocietymanagement.dto.UserDto;
import com.cdac.org.housingsocietymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HousingsocietymanagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HousingsocietymanagementApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		UserDto user = new UserDto("Team23", "team23@gmail.com", "8899889988", "password", "ADMIN", "Mumbai", true);

		userService.createUser(user);

		UserDto secretary = new UserDto("Secretary", "secretary@gmail.com", "8899889988", "password", "SECRETARY", "Bengaluru", true);
		secretary.setVerified(true);

		userService.createUser(secretary);

		UserDto owner = new UserDto("Owner", "owner@gmail.com", "8899889988", "password", "USER", "Bengaluru", true);
		owner.setVerified(true);
		userService.createUser(owner);
	}
}
