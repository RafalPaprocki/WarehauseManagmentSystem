package com.warehausesystem.app.warehauseSystemWeb;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.Compartment;
import com.warehausesystem.app.warehauseSystemWeb.repository.ArticleRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.CompartmentRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.WarehauseRepository;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class WarehauseSystemWebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WarehauseSystemWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WarehauseSystemWebApplication.class, args); }
	};
