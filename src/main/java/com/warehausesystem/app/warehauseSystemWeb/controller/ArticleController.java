package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.Exceptions.ResourceNotFoundException;
import com.warehausesystem.app.warehauseSystemWeb.model.*;
import com.warehausesystem.app.warehauseSystemWeb.repository.ArticleRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.CompartmentRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.PostRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.WarehauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/all")
    public Page<Article> getAllArticles(Pageable pageable){
        return articleRepository.findAll(pageable);
    }
/*
    @GetMapping("/articles/filter")
    public List<Article> getFilteredArticles(){
        Article article = articleRepository.findById(Long.parseLong("4")).get();

        return articleRepository.findArticleFiltered("asd","3.0");
    }
*/
    @GetMapping("/articles/filter2/{name}")
    public List<Article> getFilteredArticle(@PathVariable (value = "name") String name ){
        return articleRepository.findByNameEndingWithAndColorEndingWith(name, "raf");
    }

    @GetMapping("/article/{id}")
    public Optional<Article> getArticleById(@PathVariable (value = "id") Long id ){
        return articleRepository.findById(id);
    }

    @GetMapping("/article/search/{articleId}")
    public List<Article> findArticlesByIdLike(@PathVariable  String articleId){
        return articleRepository.findByIdLike(articleId);
    }

    @PostMapping("/article/filtr")
    public List<Article> filtrArticle(@Valid @RequestBody FilterArticle articleFiltrs){
        return articleRepository.findArticleFiltered(articleFiltrs.getName(), articleFiltrs.getColor(),
                articleFiltrs.getKind(), articleFiltrs.getId()
        );
    }
    @PostMapping("/article/add")
    public Article addArticle(@Valid @RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping("/article/{articleId}")
    public Article updatePost(@PathVariable Long articleId, @Valid @RequestBody Article articleEdit) {
        return articleRepository.findById(articleId).map(post -> {
            post.setKind(articleEdit.getKind());
            post.setColor(articleEdit.getColor());
            post.setName(articleEdit.getName());
            post.setWidth(articleEdit.getWidth());
            post.setHeigh(articleEdit.getHeigh());
            post.setDescryption(articleEdit.getDescryption());
            return articleRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Artykuł o id " + articleId + " not found"));
    }
//    @GetMapping("/posts/{postId}/comments")
//    public Page<Article> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId,
//                                                Pageable pageable) {
//        return artRepository.findByCompartmentId(postId, pageable);
//    }


//    @GetMapping("/articles/{id}")
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    public List<String> getArticles(@PathVariable(value="id")String id){
//        List<String> response = new ArrayList<>();
//        Article d = new Article();
//        for(Article i: artRepository.getByCompartmendId(Long.parseLong(id))){
//            response.add(i.toString());
//        }
//        return response;
//    }

//    @GetMapping("/art")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public String lissta(){
//        StringBuilder response = new StringBuilder();
//        response.append("ttt");
//        for(Article i: artRepository.getByCompartmendId(Long.parseLong("12"))){
//            response.append(i);
//        }
//        return response.toString();
//    }
    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<?> deletePost(@PathVariable Long articleId) {
        return articleRepository.findById(articleId).map(article -> {
            articleRepository.delete(article);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ArticleId " + articleId + " not found"));
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
