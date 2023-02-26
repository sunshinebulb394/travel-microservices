package com.travel.app.bus;

import com.travel.app.bus.auth.AuthenticationService;
import com.travel.app.bus.config.JwtService;
import com.travel.app.bus.pojo.users.Role;
import com.travel.app.bus.pojo.users.User;
import com.travel.app.bus.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class BusServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner (UserRepository userRepository,
												PasswordEncoder passwordEncoder,
												JwtService jwtService,
												AuthenticationService authenticationService){
		return args -> {
			User adminUser = new User();
			User savedAdminUser = null;
			adminUser.setEmail("baningeorge@gmail.com");
			adminUser.setRole(Role.ROLE_ADMIN);
			adminUser.setFirstname("George");
			adminUser.setLastname("Banin");
			adminUser.setAccountEnabled(true);
			adminUser.setAccountNonLocked(true);
			adminUser.setPassword(passwordEncoder.encode("wonderful143"));
			String adminToken = jwtService.generateToken(adminUser);
			if(userRepository.findByEmail(adminUser.getEmail()).isEmpty()){
				savedAdminUser = userRepository.save(adminUser);
			}
			authenticationService.saveUserToken(savedAdminUser,adminToken);
		};
	}
}
