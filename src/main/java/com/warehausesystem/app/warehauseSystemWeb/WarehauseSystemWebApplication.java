package com.warehausesystem.app.warehauseSystemWeb;

import com.warehausesystem.app.warehauseSystemWeb.repository.WarehauseRepository;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class WarehauseSystemWebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WarehauseSystemWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WarehauseSystemWebApplication.class, args); }
	@Bean
	ApplicationRunner init(WarehauseRepository repository) {
		return args -> {
			Stream.of("Jan", "Roman", "Rafał", "Jan", "Jon", "Kryk"
					).forEach(name -> {
				User user = new User();
				user.setUsername(name);
				repository.save(user);
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}
