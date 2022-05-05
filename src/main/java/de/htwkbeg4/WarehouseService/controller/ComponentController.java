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

    /**
     * Returns all PCComponents from the Repository representating the DB Table
     * @return the list of all components
     */
    @GetMapping("")
    List<PCComponent> getAll() {
        return this.repository.findAll();
    }
}
