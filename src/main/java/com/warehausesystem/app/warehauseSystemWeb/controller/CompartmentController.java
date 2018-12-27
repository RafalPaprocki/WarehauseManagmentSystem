package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.Exceptions.ResourceNotFoundException;
import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.Compartment;
import com.warehausesystem.app.warehauseSystemWeb.repository.ArticleRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.CompartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CompartmentController {

    @Autowired
    CompartmentRepository compartmentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/compartments")
    public Page<Compartment> getAllComponents(Pageable pageable){
        return compartmentRepository.findAll(pageable);
    }

    @GetMapping("/compartment/{id}")
    public Optional<Compartment> getCompartmentById(@PathVariable Long id){
        return compartmentRepository.findById(id);
    }
    @GetMapping("/compartments/get/article/{compartmentId}")
    public Article getArticleInCompartment(@PathVariable(value = "compartmentId") Long compartmentId){
        if(!compartmentRepository.findById(compartmentId).isPresent())
            return new Article();
        return compartmentRepository.findById(compartmentId).map(compartment -> {
            if(compartment.getArticle() != null)
                return compartment.getArticle();
            else
                return new Article();
        }).orElseThrow(() -> new ResourceNotFoundException("CompartmentId "  + compartmentId + " not found"));
    }

    @GetMapping("/compartments/{articleId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Compartment> getAllCompartmentsByArticleId(@PathVariable(value = "articleId") Long articleId,
                                                       Pageable pageable){
        return compartmentRepository.findByArticleId(articleId);
    }

    @PutMapping("/compartment/add/article/{quantity}/{id}")
    public Compartment updateCompartment(@PathVariable Long quantity,@PathVariable Long id, @Valid @RequestBody Compartment compartmentObj) {
        return compartmentRepository.findById(id).map(compartment -> {
           compartment.setArticleQuantity(compartment.getArticleQuantity() + quantity);
            return compartmentRepository.save(compartment);
        }).orElseThrow(() -> new ResourceNotFoundException("Magazyn o id " + id + " nie został znaleziony"));
    }

}
