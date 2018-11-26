package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import com.warehausesystem.app.warehauseSystemWeb.repository.ArticleRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.WarehauseRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ArticleController {
    private ArticleRepository artRepository;
    private WarehauseRepository repository;

    public ArticleController(WarehauseRepository repository, ArticleRepository artRepository){
        this.artRepository = artRepository;
        this.repository = repository;
    }

    public ArticleController(){

    }

    @GetMapping("/articles/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Optional<Article> lissta(@PathVariable(value="id")String id){
        return artRepository.findById(Long.parseLong(id));
    }

    @GetMapping("/lista/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<String> lista(@PathVariable(value="id")String id){
        return new ArrayList<String>(){{
            add("A");
            add("B");
        }};
    }

    private boolean firstTen(Article user){
        return user.getId() < 10;
    }
}
