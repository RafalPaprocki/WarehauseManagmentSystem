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

import static java.lang.Math.abs;

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

    @GetMapping("/add/arti/{articleId}/{quantity}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Compartment> addArticleToCompartmentt(@PathVariable(value="articleId")String id,
                                               @PathVariable(value="quantity")Long quantity) {
       Compartment compartments = compartmentRepository.findByArticleId(Long.parseLong(id)).get(0);
       Long s = 1000 - quantity;
        List<Compartment> cm = compartmentRepository.findBySectorIdAndArticleIdAnd(compartments.getSector(), compartments.getArticle(),
                s);
        return cm;
    }


    @GetMapping("/add/article/{articleId}/{quantity}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Compartment addArticleToCompartment(@PathVariable(value="articleId")String id,
                                               @PathVariable(value="quantity")Long quantity){
        Article addingArticle = articleRepository.findArticleById(Long.parseLong(id));
        List<Compartment> compartments = compartmentRepository.findByArticleId(Long.parseLong(id));
        Compartment choosenCompartment = new Compartment();
        //jesli nei ma artykulu w zadnej skrytce
        if(compartments.size() == 0){
            List<Compartment> emptyComp = compartmentRepository.findByArticleQuantity(Long.parseLong("0"));
            if(emptyComp.size() != 0){
                choosenCompartment = emptyComp.get(0);
                choosenCompartment.setArticle(addingArticle);
                choosenCompartment.setArticleQuantity(quantity);
                compartmentRepository.save(choosenCompartment);
                return choosenCompartment;
            }
        }
        else{
            //jesli sie zmiesci w któryms z istniejacych
            for(Compartment comp : compartments){
                Long artQuantity = comp.getArticleQuantity();
                if(artQuantity + quantity < 1000){
                    comp.setArticleQuantity(artQuantity + quantity);
                    compartmentRepository.save(comp);
                    return comp;
                }
            }
            Long minNumber = Long.parseLong("200");
            Sector sector = null;
            Long minDifference = Long.parseLong("200");
            for(Compartment comp:compartments){
                Long s = 1000 - quantity;
                List<Compartment> cm = compartmentRepository.findBySectorIdAndArticleIdAnd(comp.getSector(), comp.getArticle(),
                        s);
                Long currentCompNumber = comp.getNumber();
                for(Compartment comp1 : cm){
                    if((abs(comp1.getNumber() - currentCompNumber)) < minDifference){
                        minDifference = abs(comp1.getNumber() - currentCompNumber);
                        minNumber = comp1.getNumber();
                        sector = comp1.getSector();
                    }
                }
            }

            choosenCompartment = compartmentRepository.findBySectorIdAndNumber(sector.getId(), minNumber).get(0);
            choosenCompartment.setArticle(addingArticle);
            choosenCompartment.setArticleQuantity(choosenCompartment.getArticleQuantity() + quantity);
            return compartmentRepository.save(choosenCompartment);

            //jesli sie nie zmiesci w skrytkach w ktorych juz jest

/*
            for(Compartment comp : compartments){
                Compartment nextComp = new Compartment();
                if(comp.getNumber() > (comp.getSector().getHeigh() * comp.getSector().getWidth())) {
                    nextComp = compartmentRepository.findBySectorIdAndNumber(comp.getSector().getId(),
                            comp.getNumber() + 1).get(0);
                }
                Long artQuantity = nextComp.getArticleQuantity();
                if((nextComp.getArticle().getId() == null || nextComp.getArticle().getId() == comp.getArticle().getId()) && artQuantity + quantity < 1000){
                    if(nextComp.getArticle().getId() == null){
                        nextComp.setArticle(addingArticle);
                    }
                    nextComp.setArticleQuantity(artQuantity + quantity);
                    compartmentRepository.save(nextComp);
                    return nextComp;
                }
            }
*/
        }
        System.out.println(choosenCompartment.getId());
        List<Compartment> dd = compartmentRepository.findBySectorId(Long.parseLong("1"));
        if(compartments.size() == 0){

        }
        for(Compartment i : compartments){

        }
        System.out.println("asdased");
        return choosenCompartment;
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
