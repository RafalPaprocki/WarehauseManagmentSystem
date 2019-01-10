package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.Exceptions.ResourceNotFoundException;
import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.Compartment;
import com.warehausesystem.app.warehauseSystemWeb.model.Sector;
import com.warehausesystem.app.warehauseSystemWeb.repository.ArticleRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.CompartmentRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.SectorRepository;
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

    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping("/compartments")
    public Page<Compartment> getAllComponents(Pageable pageable){
        return compartmentRepository.findAll(pageable);
    }

    @GetMapping("/compartment/{id}/{sector}")
    public Compartment getCompartmentById(@PathVariable Long id, @PathVariable String sector){
        Sector sec = sectorRepository.findBySector("Sektor " + sector);
        Compartment comp = compartmentRepository.findBySectorIdAndNumber(sec.getId(),id ).get(0);
        return comp;
    }

    @GetMapping("/add/article/{articleId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void addArticleToCompartment(@PathVariable(value="articleId")String id){
        List<Compartment> compartments = compartmentRepository.findByArticleId(Long.parseLong(id));
        System.out.println("asdasd");
        List<Compartment> dd = compartmentRepository.findBySectorId(Long.parseLong("1"));
        if(compartments.size() == 0){

        }
        for(Compartment i : compartments){

        }
        System.out.println("asdased");
    }

    @GetMapping("/compartments/get/article/{sector}/{number}")
    public Article getArticleInCompartmentWithSector(@PathVariable(value = "sector") String sectorName,
                                                               @PathVariable(value = "number") Long number) {
        Sector sec = sectorRepository.findBySector("Sektor " + sectorName);
       List<Compartment> a = compartmentRepository.findBySectorIdAndNumber(sec.getId(), number);
       Compartment comp = a.get(0);
       return comp.getArticle();
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

    @GetMapping("/compartment/add/article/{quantity}/{id}/{sector}")
    public Compartment updateCompartment(@PathVariable Long quantity, @PathVariable Long id,
                                         @PathVariable String sector) {
        Sector sec = sectorRepository.findBySector("Sektor " + sector);
        Compartment comp = compartmentRepository.findBySectorIdAndNumber(sec.getId(), id).get(0);
        comp.setArticleQuantity(comp.getArticleQuantity() + quantity);
        return compartmentRepository.save(comp);
    }

}
