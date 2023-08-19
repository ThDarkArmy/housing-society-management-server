package tda.darkarmy.housingsocietymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tda.darkarmy.housingsocietymanagement.dto.UserDto;
import tda.darkarmy.housingsocietymanagement.model.User;
import tda.darkarmy.housingsocietymanagement.service.UserService;

@SpringBootApplication
public class HousingsocietymanagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HousingsocietymanagementApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		UserDto user = new UserDto("Team23", "team23@gmail.com", "8899889988", "password", "ADMIN", "Mumbai");

		userService.createUser(user);

		UserDto secretary = new UserDto("Secretary", "secretary@gmail.com", "8899889988", "password", "SECRETARY", "Bengaluru");

		userService.createUser(secretary);

		UserDto owner = new UserDto("Owner", "owner@gmail.com", "8899889988", "password", "USER", "Bengaluru");

		userService.createUser(owner);
	}
}
