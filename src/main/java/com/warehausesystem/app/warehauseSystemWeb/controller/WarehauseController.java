package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.model.User;
import com.warehausesystem.app.warehauseSystemWeb.repository.WarehauseRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WarehauseController {
    private WarehauseRepository repository;

    public WarehauseController(WarehauseRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/lista/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<User> lista(@PathVariable(value="id")String id){
        return repository.findAll().stream()
                .filter(this::firstTen)
                .collect(Collectors.toList());
    }

    @GetMapping("/listaaa")
    @CrossOrigin(origins = "http://localhost:4200")
    public String listaw(){
        return "Asdasd";
    }

    @PostMapping("/listaa")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<String> listaa(@RequestBody String body) throws IOException {
        return new ArrayList<String>() {{
            add("A");
            add("B");
            add(body);
        }};
    }

    private boolean firstTen(User user){
        return user.getId() < 10;
    }
}
