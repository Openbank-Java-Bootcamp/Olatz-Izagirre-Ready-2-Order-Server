package com.ironhack.finalprojectserver;

import com.ironhack.finalprojectserver.DTO.UserSignupDTO;
import com.ironhack.finalprojectserver.model.Role;
import com.ironhack.finalprojectserver.service.impl.RoleService;
import com.ironhack.finalprojectserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class FinalProjectServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectServerApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Autowired
	public Environment environment;

	@Bean
	CommandLineRunner run(RoleService roleService, UserService userService) {
		return args -> {if(!Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			roleService.saveRole(new Role("ADMIN"));
			roleService.saveRole(new Role("CHEF"));
			roleService.saveRole(new Role("WAITER"));


			userService.saveUser(new UserSignupDTO("Manager","admin@gmail.com","P@ssw0rd","ADMIN"));

		}};
	}

}
