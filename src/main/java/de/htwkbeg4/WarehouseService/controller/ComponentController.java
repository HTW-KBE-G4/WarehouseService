package de.htwkbeg4.WarehouseService.controller;

import de.htwkbeg4.WarehouseService.model.PCComponent;
import de.htwkbeg4.WarehouseService.repository.PCComponentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private PCComponentRepository repository;

    public ComponentController(PCComponentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<PCComponent> getAllComponentsFromDatabase() {
        return this.repository.findAll();
    }
}
