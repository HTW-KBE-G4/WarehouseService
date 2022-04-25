package de.htwkbeg4.WarehouseService.controller;

import de.htwkbeg4.WarehouseService.model.PCComponent;
import de.htwkbeg4.WarehouseService.repository.PCComponentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/component")
public class WarehouseServiceController {

    private PCComponentRepository repository;

    public WarehouseServiceController(PCComponentRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns all PCComponents from the Repository representating the DB Table
     * @return
     */
    @GetMapping("")
    List<PCComponent> getAll() {
        return this.repository.findAll();
    }

    /**
     * Returns the corresponding PCcomponent by given id
     * @param id the id of the PCComponent
     * @return the Compoennt or Throws NoSuchElementException...
     */
    @GetMapping("{id}")
    PCComponent getOne(@PathVariable Long id) {
        return this.repository.findById(id)
                .orElseThrow();
    }

}
