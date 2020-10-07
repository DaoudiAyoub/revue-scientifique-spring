package org.sid;

import org.sid.beans.Role;
import org.sid.beans.User;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectSpringApplication implements CommandLineRunner {
	
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		accountService.saveUser(new User("daoudi", "ayoub", "ayoub012", "1234", "ayoub@gmail.com"));
		accountService.saveUser(new User("AHMED", "dza", "ahm22", "1234", "ahmed@gmail.com"));
		accountService.saveUser(new User("Imad", "Imad", "imad012", "4444", "Imad@gmail.com"));
		accountService.saveRole(new Role("AUTEUR"));
		accountService.saveRole(new Role("COMITE"));
		accountService.saveRole(new Role("REFEREE"));
		accountService.addRoleToUser("ahm22", "AUTEUR");
		accountService.addRoleToUser("ayoub012", "COMITE");
		accountService.addRoleToUser("imad012", "REFEREE");
		
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder getBCrypt() {
		return new BCryptPasswordEncoder();
	}

}
