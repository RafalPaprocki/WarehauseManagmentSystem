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
	@Bean
	ApplicationRunner init(WarehauseRepository repository, ArticleRepository rep1,
						   CompartmentRepository rep2) {
		return args -> {
			Stream.of("Jan", "Roman", "Rafał", "Jan", "Jon", "Kryk"
					).forEach(name -> {
				User user = new User();
				user.setUsername(name);
				repository.save(user);
			});

			Compartment comp2 = new Compartment();
			Set articlesA = new HashSet<Article>(){{
				add (new Article(Long.parseLong("12"),"biurowy", "czarny" ,
						23.0,34.0,"ołówek","ołowek hb", Long.parseLong("2"),
						comp2));
				add (new Article(Long.parseLong("11"),"biurowy", "zielony" ,
						23.0,34.0,"ołówek","ołowek hb", Long.parseLong("2"),
						comp2));
			}};
			comp2.setArticleSet(articlesA);

			rep2.save(comp2);
			repository.findAll().forEach(System.out::println);
		};
	}


}

