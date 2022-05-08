package de.htwkbeg4.WarehouseService.controller;

import de.htwkbeg4.WarehouseService.model.Product;
import de.htwkbeg4.WarehouseService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<Product> getAllProductsFromDatabase() {
        return this.productRepository.findAll();
    }
}
